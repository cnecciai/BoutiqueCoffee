/*CLARK
  When a 'SALE' transaction occurs, ensure that the customer has paid
  the correct amount for that coffee type * quantity */
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
        customer_paid = (NEW.purchase_portion * 100 + NEW.redeem_portion) / 100;

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




/*CLARK
  WHEN A CUSTOMER IS INSERTED INTO THE CUSTOMER RELATION
  THEY SHOULD THEN BE ADDED TO THE LOYALTY SYSTEM AUTOMATICALLY*/
CREATE OR REPLACE FUNCTION loyalty_relation_assign()
RETURNS TRIGGER AS $$
    BEGIN
        INSERT INTO COFFEE_BOUTIQUE.loyalty_program VALUES (NEW.CUSTOMER_ID);
        RETURN NEW;
    END;
    $$ language PLPGSQL;

DROP TRIGGER IF EXISTS loyalty_assign ON COFFEE_BOUTIQUE.CUSTOMER;
CREATE TRIGGER loyalty_assign
    AFTER INSERT ON COFFEE_BOUTIQUE.CUSTOMER
    FOR EACH ROW
    EXECUTE FUNCTION loyalty_relation_assign();





/*CLARK
BEFORE A SALE OCCURS WE SHOULD CHECK TO MAKE SURE THAT
IF THE CUSTOMER IS TRYING TO REDEEM POINTS FOR A PURCHASE
THAT THEY HAVE THE NECESSARY POINTS

WE SHOULD TAKE THE CUSTOMER ID FROM SALE THAT IS TRYING TO MAKE THE PURCHASE,
THEN GO AND CHECK IF THEY HAVE ENOUGH TO MAKE THE PURCHASE, IF NOT, REJECT
IF YES, THEN SUBTRACT THAT AMOUNT AND ALLOW FOR SALE
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





/*CLARK
AFTER A SALE OCCURS WE SHOULD ADD TO THE CUSTOMERS PROFILE
THE CORRECT AMOUNT OF POINTS EARNED - POINTS CAN ONLY BE ADDED TO A
PROFILE AFTER A TRANSACTION OCCURS
*/
CREATE OR REPLACE FUNCTION add_points()
RETURNS TRIGGER AS $$
    DECLARE
        total_reward_points_earned DECIMAL(12,2); --- <-- QUANTITY * REWARD_POINTS
        amount_points_each_coffee DECIMAL(12,2);
    BEGIN

    SELECT C.reward_points INTO amount_points_each_coffee
    FROM COFFEE_BOUTIQUE.coffee AS C
    WHERE C.coffee_id = NEW.COFFEE_ID;

    total_reward_points_earned = NEW.QUANTITY * amount_points_each_coffee;

    UPDATE COFFEE_BOUTIQUE.CUSTOMER
    SET points_earned = customer.points_earned + total_reward_points_earned
    WHERE customer_id = NEW.CUSTOMER_ID;

    RETURN NEW;
    END;
    $$ language PLPGSQL;

DROP TRIGGER IF EXISTS add_redeem_points ON COFFEE_BOUTIQUE.CUSTOMER;
CREATE TRIGGER add_redeem_points
    AFTER INSERT ON COFFEE_BOUTIQUE.SALE
    FOR EACH ROW
    WHEN ( NEW.redeem_portion = 0 )
    EXECUTE FUNCTION add_points();
