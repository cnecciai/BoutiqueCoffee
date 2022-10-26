--- TEAM NAME: CARPE DATA
--- TEAM MEMBERS: CLARK, DARMA,DANIEL

---CREATE SCHEMA
DROP SCHEMA IF EXISTS COFFEE_BOUTIQUE CASCADE ;
CREATE SCHEMA IF NOT EXISTS COFFEE_BOUTIQUE;

---DROP SCHEMAS
DROP SCHEMA IF EXISTS STORE CASCADE;
DROP SCHEMA IF EXISTS COFFEE CASCADE;
DROP SCHEMA IF EXISTS CUSTOMER CASCADE;
DROP SCHEMA IF EXISTS PHONE CASCADE;
DROP SCHEMA IF EXISTS SALE CASCADE;
DROP SCHEMA IF EXISTS PARTICIPATES CASCADE;
DROP SCHEMA IF EXISTS PROMOTION CASCADE;
DROP SCHEMA IF EXISTS PROMOTES CASCADE;

DROP DOMAIN IF EXISTS type_store CASCADE;
DROP DOMAIN IF EXISTS level CASCADE;
DROP DOMAIN IF EXISTS phone_enum CASCADE;
DROP DOMAIN IF EXISTS months CASCADE;

CREATE DOMAIN COFFEE_BOUTIQUE.type_store AS VARCHAR(7)
    CHECK ( (VALUE IN ('sitting', 'kiosk')) );

CREATE TABLE IF NOT EXISTS COFFEE_BOUTIQUE.STORE(
    store_ID integer,
    name varchar(50) UNIQUE NOT NULL,
    store_type COFFEE_BOUTIQUE.type_store,
    gps_Long float,
    gps_Lat float,

    CONSTRAINT PK_STORE PRIMARY KEY(store_ID)
    );


CREATE TABLE IF NOT EXISTS COFFEE_BOUTIQUE.COFFEE(
    coffee_ID integer,
    name varchar(50),
    description varchar(250),
    country_of_origin varchar(60),
    intensity integer
        CHECK ( intensity >= 1 AND intensity <= 12 ),
    price float,
    reward_points float,
    redeem_points float,

    CONSTRAINT PK_COFFEE PRIMARY KEY(coffee_ID)

    );



CREATE DOMAIN COFFEE_BOUTIQUE.level AS varchar(10)
    CHECK ( (VALUE IN ('basic', 'bronze', 'silver', 'gold', 'platinum', 'diamond')) );

CREATE DOMAIN COFFEE_BOUTIQUE.months AS varchar(3)
    CHECK ( (VALUE IN ('JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC')) );

CREATE TABLE IF NOT EXISTS COFFEE_BOUTIQUE.CUSTOMER(
    customer_ID integer,
    first varchar(50),
    last varchar(50),
    middle_initial char(1),
    day_of_birth char(2),
    month_of_birth COFFEE_BOUTIQUE.months,
    loyalty_level COFFEE_BOUTIQUE.level,
    points_earned float,

    CONSTRAINT PK_CUSTOMER PRIMARY KEY(customer_ID)

    );


CREATE DOMAIN COFFEE_BOUTIQUE.phone_enum AS varchar(6)
    CHECK ( (VALUE IN ('home', 'mobile', 'work', 'other')) );

CREATE TABLE IF NOT EXISTS COFFEE_BOUTIQUE.PHONE(
    customer_ID integer,
    phone_number varchar(16),
    phone_type COFFEE_BOUTIQUE.phone_enum,

    CONSTRAINT PK_PHONE PRIMARY KEY (customer_ID),

    FOREIGN KEY (customer_ID) REFERENCES COFFEE_BOUTIQUE.CUSTOMER (customer_ID)
        ON UPDATE CASCADE ON DELETE CASCADE

    );



/*WE SHOULD PROBABLY JUST USE A 1 POINT - 1 CENT SORT OF POLICY
  KEEPS IT SIMPLISTIC AND MANAGEABLE

  SO ESSENTIALLY WE'LL HAVE TO DERIVE PURCHASE_PORTION FROM COFFEE'S PRICE - REDEEM_PORTION
  FOR THE FINAL COST...

  -- ON DELETE NO ACTION BECAUSE EVEN IF THE PREVIOUS INFORMATION HAS CHANGED,
  WE'LL PROBABLY STILL WANT TO STORE THAT INFORMATION IN THE DATABASE AS A SALE
  */
CREATE TABLE IF NOT EXISTS COFFEE_BOUTIQUE.SALE(
    sale_ID integer,
    customer_ID integer,
    store_ID integer,
    coffee_ID integer,
    quantity integer,
    purchase_time time,
    purchase_portion float,
    redeem_portion float,

    CONSTRAINT PK_SALE PRIMARY KEY (sale_ID),

    FOREIGN KEY (customer_ID) REFERENCES COFFEE_BOUTIQUE.CUSTOMER (customer_id)
        ON UPDATE CASCADE ON DELETE NO ACTION ,

    FOREIGN KEY (store_ID) REFERENCES COFFEE_BOUTIQUE.STORE (store_id)
        ON UPDATE CASCADE ON DELETE NO ACTION ,

    FOREIGN KEY (coffee_ID) REFERENCES COFFEE_BOUTIQUE.COFFEE (coffee_id)
        ON UPDATE CASCADE ON DELETE NO ACTION

    );



--- Use a trigger here for checking multiple tables
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (1, 1, 1, 1, 1, '13:30', 7.30, 0);


--- This constraint will make it so that the end_date cannot be before the start date...
--- For obvious reasons
CREATE TABLE IF NOT EXISTS COFFEE_BOUTIQUE.PROMOTION(
    promotion_ID integer,
    name varchar(50),
    start_date date,
    end_date date
        CHECK ( PROMOTION.end_date >= PROMOTION.start_date ),

    CONSTRAINT PK_PROMOTION PRIMARY KEY (promotion_ID)

    );


CREATE TABLE IF NOT EXISTS COFFEE_BOUTIQUE.PARTICIPATES(
    promotion_ID integer,
    store_ID integer,

    CONSTRAINT PK_PARTICPATES PRIMARY KEY (promotion_ID, store_ID),

    FOREIGN KEY (promotion_ID) REFERENCES COFFEE_BOUTIQUE.PROMOTION (promotion_id)
        ON UPDATE CASCADE ON DELETE CASCADE,

    FOREIGN KEY (store_ID) REFERENCES COFFEE_BOUTIQUE.STORE (store_ID)
        ON UPDATE CASCADE ON DELETE CASCADE

    );


CREATE TABLE IF NOT EXISTS COFFEE_BOUTIQUE.PROMOTES(
    promotion_ID integer,
    coffee_ID integer,

    CONSTRAINT PK_PROMOTES PRIMARY KEY (promotion_ID, coffee_ID),

    FOREIGN KEY (promotion_ID) REFERENCES COFFEE_BOUTIQUE.PROMOTION (promotion_id)
        ON UPDATE CASCADE ON DELETE CASCADE,

    FOREIGN KEY (coffee_ID) REFERENCES COFFEE_BOUTIQUE.COFFEE (coffee_id)
        ON UPDATE CASCADE ON DELETE CASCADE

    );


INSERT INTO COFFEE_BOUTIQUE.STORE VALUES (1, 'Coffee_store#1', 'sitting', 123.456, 654.321);
INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (1, 'Vanilla Bean', 'Basic Coffee', 'Canada', 3, 7.30, 5, 50);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (1, 'Clark', 'Necciai', 'P', '10', 'OCT', NULL, 0);
INSERT INTO COFFEE_BOUTIQUE.PHONE VALUES (1, '724-317-7412', 'work');