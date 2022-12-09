/* Trigger #1 - WHEN A 'SALE' TRANSACTION OCCURS, ENSURE
   THAT CUSTOMER HAS PAID CORRECT AMOUNT BY CHECK BELOW*/
  CREATE OR REPLACE FUNCTION correct_amount_check()
RETURNS TRIGGER AS $$
    DECLARE
        total_purchase_amount DECIMAL(12,2);
        customer_paid DECIMAL(12,2);
    BEGIN
        SELECT price into total_purchase_amount
        FROM COFFEE_BOUTIQUE.COFFEE AS C
        WHERE NEW.coffee_ID = C.coffee_id;

        total_purchase_amount = NEW.quantity * total_purchase_amount;
        customer_paid = (NEW.purchase_portion + NEW.redeem_portion / 100);

        IF total_purchase_amount = customer_paid THEN
            RETURN NEW;
        end if;
        RAISE EXCEPTION 'Sale Rejected - Incorrect Amount';
    END;
    $$ language PLPGSQL;

DROP TRIGGER IF EXISTS sale_amount_trigger ON COFFEE_BOUTIQUE.SALE;
CREATE TRIGGER sale_amount_trigger
    BEFORE INSERT ON COFFEE_BOUTIQUE.SALE
    FOR EACH ROW
    EXECUTE FUNCTION correct_amount_check();


/*Trigger #2 - WHEN A CUSTOMER ADDS COFFEE FROM THE
  JAVA PROGRAM, ENSURE THAT IT IS CONSISTENT WITH OUR
  SPECIFIED SCHEMA DESIGN*/
CREATE OR REPLACE FUNCTION customer_add_coffee_assign()
RETURNS TRIGGER AS $$
    BEGIN
        IF NEW.price <> (NEW.reward_points / 10)  THEN
        RAISE EXCEPTION 'Reward Points must be 10x the price of coffee';
        end if;

        IF NEW.price <> (NEW.redeem_points / 100) THEN
        RAISE EXCEPTION 'Redeem Points must be 100x the price of coffee';
        end if;
        RETURN NEW;

    END;
    $$ language PLPGSQL;

DROP TRIGGER IF EXISTS coffee_add ON COFFEE_BOUTIQUE.COFFEE;
CREATE TRIGGER coffee_add
    BEFORE INSERT ON COFFEE_BOUTIQUE.COFFEE
    FOR EACH ROW
    EXECUTE FUNCTION customer_add_coffee_assign();


/* Trigger #3
- BEFORE A SALE OCCURS, ENSURE THAT
IF THE CUSTOMER IS TRYING TO REDEEM POINTS FOR A PURCHASE,
THAT THEY HAVE THE NECESSARY POINTS EARNED.

IF NOT, REJECT.
IF YES, THEN SUBTRACT THAT AMOUNT AND ALLOW FOR SALE.
*/
CREATE OR REPLACE FUNCTION enough_points_check()
RETURNS TRIGGER AS $$
    DECLARE
        amount_trying_to_redeem DECIMAL(12,2);
        amount_redeemable DECIMAL(12,2);
    BEGIN
        amount_trying_to_redeem = NEW.REDEEM_PORTION;

        SELECT points_earned into amount_redeemable
        FROM COFFEE_BOUTIQUE.CUSTOMER AS C
        WHERE C.customer_id = NEW.CUSTOMER_ID;

        IF amount_trying_to_redeem > amount_redeemable THEN
            RAISE EXCEPTION 'NOT ENOUGH POINTS';
        end if;

        UPDATE COFFEE_BOUTIQUE.CUSTOMER
        SET points_earned = amount_redeemable - amount_trying_to_redeem
        WHERE customer_id = NEW.CUSTOMER_ID;
        RETURN NEW;
    END;
    $$ language PLPGSQL;

DROP TRIGGER IF EXISTS customer_redeem_check ON COFFEE_BOUTIQUE.SALE;
CREATE TRIGGER customer_redeem_check
    BEFORE INSERT ON COFFEE_BOUTIQUE.SALE
    FOR EACH ROW
    WHEN ( NEW.redeem_portion > 0 )
    EXECUTE FUNCTION enough_points_check();

/* Trigger #4
- AFTER A SALE OCCURS WE SHOULD ADD TO THE CUSTOMERS PROFILE
THE CORRECT AMOUNT OF POINTS EARNED,
- POINTS CAN ONLY BE ADDED TO A PROFILE AFTER A TRANSACTION OCCURS. WE CAN
   NOW ALSO CONSIDER A CUSTOMER'S BOOSTER FACTOR WHEN MULTIPLYING FOR THEIR
   'POINTS_EARNED'
-THIS TRIGGER ACTIVATES WHEN A CUSTOMER IS NOT REDEEMING A COFFEE WITH POINTS(REDEEM_PORTION=0)
*/
CREATE OR REPLACE FUNCTION add_points()
RETURNS TRIGGER AS $$
    DECLARE
        total_reward_points_earned DECIMAL(12,2); --- <-- QUANTITY * REWARD_POINTS
        amount_points_each_coffee DECIMAL(12,2);
        booster_level INTEGER;
        mult_amt DECIMAL(12,2);
    BEGIN

    SELECT C.reward_points INTO amount_points_each_coffee
    FROM COFFEE_BOUTIQUE.coffee AS C
    WHERE C.coffee_id = NEW.COFFEE_ID;

    SELECT loyalty_level INTO booster_level
    FROM COFFEE_BOUTIQUE.customer AS C
    WHERE C.customer_ID = NEW.customer_ID;

    SELECT booster_factor INTO mult_amt
    FROM COFFEE_BOUTIQUE.loyalty_program
    WHERE level_ID = booster_level;

    total_reward_points_earned = NEW.QUANTITY * amount_points_each_coffee * mult_amt;

    UPDATE COFFEE_BOUTIQUE.CUSTOMER
    SET points_earned = customer.points_earned + total_reward_points_earned
    WHERE customer_id = NEW.CUSTOMER_ID;

    RETURN NEW;
    END;
    $$ language PLPGSQL;

DROP TRIGGER IF EXISTS add_redeem_points ON COFFEE_BOUTIQUE.SALE;
CREATE TRIGGER add_redeem_points
    AFTER INSERT ON COFFEE_BOUTIQUE.SALE
    FOR EACH ROW
    WHEN ( NEW.redeem_portion = 0 )
    EXECUTE FUNCTION add_points();