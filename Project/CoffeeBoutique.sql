--- TEAM NAME: CARPE DATA
--- TEAM MEMBERS: CLARK, DHARMA, DANIEL

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
    store_type COFFEE_BOUTIQUE.type_store NOT NULL,
    gps_Long float NOT NULL,
    gps_Lat float NOT NULL,

    CONSTRAINT PK_STORE PRIMARY KEY(store_ID)
    );


CREATE TABLE IF NOT EXISTS COFFEE_BOUTIQUE.COFFEE(
    coffee_ID integer,
    name varchar(50) NOT NULL,
    description varchar(250),
    country_of_origin varchar(60),
    intensity integer NOT NULL
        CHECK ( intensity >= 1 AND intensity <= 12 ),
    price float NOT NULL
        CHECK ( price >= 0 ),
    reward_points float NOT NULL DEFAULT 0
        CHECK ( reward_points >= 0 ),
    redeem_points float NOT NULL DEFAULT 0
        CHECK ( redeem_points >= 0 ),

    CONSTRAINT PK_COFFEE PRIMARY KEY(coffee_ID)

    );


CREATE DOMAIN COFFEE_BOUTIQUE.level AS varchar(10)
    CHECK ( (VALUE IN ('basic', 'bronze', 'silver', 'gold', 'platinum', 'diamond')) );

CREATE DOMAIN COFFEE_BOUTIQUE.months AS varchar(3)
    CHECK ( (VALUE IN ('JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC')) );

CREATE TABLE IF NOT EXISTS COFFEE_BOUTIQUE.CUSTOMER(
    customer_ID integer,
    first varchar(50) NOT NULL,
    last varchar(50),
    middle_initial char(1),
    day_of_birth char(2),
    month_of_birth COFFEE_BOUTIQUE.months,
    loyalty_level COFFEE_BOUTIQUE.level,
    points_earned float NOT NULL DEFAULT 0
        CHECK ( points_earned >= 0 ),

    CONSTRAINT PK_CUSTOMER PRIMARY KEY(customer_ID)

    );


CREATE DOMAIN COFFEE_BOUTIQUE.phone_enum AS varchar(6)
    CHECK ( (VALUE IN ('home', 'mobile', 'work', 'other')) );

CREATE TABLE IF NOT EXISTS COFFEE_BOUTIQUE.PHONE(
    customer_ID integer,
    phone_number varchar(16),
    phone_type COFFEE_BOUTIQUE.phone_enum NOT NULL,

    CONSTRAINT PK_PHONE PRIMARY KEY (customer_ID, phone_number),

    FOREIGN KEY (customer_ID) REFERENCES COFFEE_BOUTIQUE.CUSTOMER (customer_ID)
        ON UPDATE CASCADE ON DELETE CASCADE

    );

CREATE TABLE IF NOT EXISTS COFFEE_BOUTIQUE.SALE(
    sale_ID integer,
    customer_ID integer,
    store_ID integer,
    coffee_ID integer,
    quantity integer NOT NULL DEFAULT 1
        CHECK ( quantity >= 1 ),
    purchase_time time NOT NULL,
       --- CHECK ( purchase_time >= '08:00' and purchase_time <= '18:00' ),  <--- Considering adding time constraint
    purchase_portion float NOT NULL,
    redeem_portion float NOT NULL DEFAULT 0,

    CONSTRAINT PK_SALE PRIMARY KEY (sale_ID),

    FOREIGN KEY (customer_ID) REFERENCES COFFEE_BOUTIQUE.CUSTOMER (customer_id)
        ON UPDATE CASCADE ON DELETE NO ACTION ,

    FOREIGN KEY (store_ID) REFERENCES COFFEE_BOUTIQUE.STORE (store_id)
        ON UPDATE CASCADE ON DELETE NO ACTION ,

    FOREIGN KEY (coffee_ID) REFERENCES COFFEE_BOUTIQUE.COFFEE (coffee_id)
        ON UPDATE CASCADE ON DELETE NO ACTION

    );



CREATE TABLE IF NOT EXISTS COFFEE_BOUTIQUE.PROMOTION(
    promotion_ID integer,
    name varchar(50) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL
        CHECK ( PROMOTION.end_date >= PROMOTION.start_date ),

    CONSTRAINT PK_PROMOTION PRIMARY KEY (promotion_ID)

    );


CREATE TABLE IF NOT EXISTS COFFEE_BOUTIQUE.PARTICIPATES(
    promotion_ID integer,
    store_ID integer,

    CONSTRAINT PK_PARTICIPATES PRIMARY KEY (promotion_ID, store_ID),

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

