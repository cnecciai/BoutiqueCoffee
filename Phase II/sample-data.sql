/*
Phase#2 - Coffee Boutique
Clark, Daniel, Dharma
*/
---------------------------------------------------------
---------------------- Sample Data ----------------------
---------------------------------------------------------

/*All sample data can be run prior/during Java program execution*/

-------------------3 stores-------------------
INSERT INTO COFFEE_BOUTIQUE.STORE VALUES (1, 'Coffee_store#1', 'sitting', 123.456, 654.321);
INSERT INTO COFFEE_BOUTIQUE.STORE VALUES (2, 'Coffee_store#2', 'kiosk', 010.999, 101.555);
INSERT INTO COFFEE_BOUTIQUE.STORE VALUES (3, 'Coffee_store#3', 'sitting', 135.789, 246.789);

-------------------5 Loyalty Levels-------------------
INSERT INTO COFFEE_BOUTIQUE.LOYALTY_PROGRAM VALUES (0, 'basic', 1);
INSERT INTO COFFEE_BOUTIQUE.LOYALTY_PROGRAM VALUES (1, 'bronze', 1.1);
INSERT INTO COFFEE_BOUTIQUE.LOYALTY_PROGRAM VALUES (2, 'silver', 1.2);
INSERT INTO COFFEE_BOUTIQUE.LOYALTY_PROGRAM VALUES (3, 'gold', 1.3);
INSERT INTO COFFEE_BOUTIQUE.LOYALTY_PROGRAM VALUES (4, 'platinum', 1.4);
INSERT INTO COFFEE_BOUTIQUE.LOYALTY_PROGRAM VALUES (5, 'diamond', 1.5);

-------------------20 Customers-------------------
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (1, 'Abraham', 'Arden', 'S', '5', 'MAR', '7624244937' ,'mobile' , 0, 5);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (2, 'Garnett', 'Douglas', 'J', '10', 'OCT', '7917796026' ,'home' , 0, 4);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (3, 'Foster', 'Russel', 'T', '18', 'JAN', '7809572136','work' , 0, 3);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (4, 'Lorne', 'Roscoe', 'T', '8', 'SEP', '1804299101' ,'mobile' , 0, 2);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (5, 'Emery', 'Meredith', 'S', '7', 'APR', '7272235975' ,'home' , 0, 1);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (6, 'Neville', 'Cordell', 'P', '3', 'APR', '5685916762' ,'work' , 0, 0);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (7, 'Clarence', 'Marion', 'R', '5', 'MAY', '3607124246' , 'other', 0, 5);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (8, 'Damian', 'Mortimer', 'B', '4', 'JUN', '4139241499' ,'mobile' , 0, 4);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (9, 'Ellis', 'Monroe', 'B', '1', 'OCT', '1818151108' ,'mobile' , 0, 3);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (10, 'Wilfrid', 'Jeremiah', 'W', '10', 'JUL','2096352347' , 'mobile', 0, 2);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (11, 'Jerold', 'Randolf', 'M', '19', 'DEC', '7276414045' ,'work' , 0, 5);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (12, 'Elroy', 'Edgar', 'C', '24', 'MAR','6154143341' ,'home' , 0, 0);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (13, 'Alexander', 'Warren', 'S', '2', 'SEP','2262515106','mobile' , 0, 2);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (14, 'Cavan', 'Foster', 'B', '2', 'DEC', '2256168614', 'home', 0, 4);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (15, 'Brandt', 'Sunny', 'E', '7', 'AUG','4525933738' ,'work' , 0, 0);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (16, 'Zach', 'Leslie', 'B', '6', 'NOV', '7016289514' ,'other' , 0, 2);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (17, 'Lindsay', 'Adrian', 'F', '13', 'FEB', '1982016789' , 'work', 0, 1);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (18, 'Elton', 'Emmett', 'B', '19', 'FEB', '6242266339','home' , 0, 1);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (19, 'Bryon', 'Garnet', 'W', '15', 'NOV','1859613893' ,'mobile' , 0, 0);
INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (20, 'Ford', 'Phillip', 'A', '13', 'AUG', '5449106245', 'work',0, 5);

-------------------12 COFFEES-------------------
INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (1, 'Caffe Latte', 'Italian origin made with espresso and steamed milk', 'Canada', 1, 2.95, 29.5, 295);
INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (2, 'Caffe Mocha', 'Chocolate-flavoured warm beverage', 'Hungary', 2, 3.65, 36.5, 365);
INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (3, 'White Chocolate Mocha', 'Varient on Caffee Mocha w/ white chocolate', 'United States', 3, 4.15, 41.5, 415);
INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (4, 'Freshly Brewed Coffee', 'Basic', 'Indonesia', 4, 4.65, 46.5, 465);
INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (5, 'Cinnamon Dolce Latte', 'Cinnamon dolce-flavored syrup', 'Brazil', 5, 7.30, 73.0, 730);
INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (6, 'Skinny Vanilla Latte', 'Vanilla almond milk/soy milk', 'Russia', 6, 3.45, 34.5, 345);
INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (7, 'Caramel Macchiato', 'A Boutique Coffee favorite!', 'France', 7, 7.45, 74.5, 745);
INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (8, 'Caramel Flan Latte', 'Freshly steamed milk and rich flan flavored syrup', 'Monaco', 8, 7.15, 71.5, 715);
INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (9, 'Flat White', 'Blend of micro-foamed milk poured over espresso', 'Denmark', 9, 7.80, 78.0, 780);
INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (10, 'Skinny Peppermint Mocha', 'Basic Coffee', 'Australia', 10, 7.15, 71.5, 715);
INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (11, 'Pumpkin Spice Latte', 'Voted #1 by all women', 'Japan', 11, 7.30, 73.0, 730);
INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (12, 'Toasted Graham Latte', 'Graham cracker goodness', 'Peru', 12, 7.95, 79.5, 795);
INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (13, 'Toasted Graham Latte 2', 'Graham cracker goodness 2', 'Peru 2', 12, 7.61, 76.1, 761);


-------------------3 PROMOTIONS-------------------
INSERT INTO COFFEE_BOUTIQUE.PROMOTION VALUES (1, 'Grand Opening', '10-15-2022', '11-15-2022');
INSERT INTO COFFEE_BOUTIQUE.PROMOTION VALUES (2, 'Fall Specials', '09-02-2022', '11-01-2022');
INSERT INTO COFFEE_BOUTIQUE.PROMOTION VALUES (3, 'Winter Extravaganza', '11-15-2022', '2-01-2023');

-------------------3 CARRIES-------------------
INSERT INTO COFFEE_BOUTIQUE.CARRIES VALUES (1, 1);
INSERT INTO COFFEE_BOUTIQUE.CARRIES VALUES (2, 2);
INSERT INTO COFFEE_BOUTIQUE.CARRIES VALUES (3, 3);


-------------------3 PROMOTES-------------------
INSERT INTO COFFEE_BOUTIQUE.PROMOTES VALUES (1, 1);
INSERT INTO COFFEE_BOUTIQUE.PROMOTES VALUES (2, 2);
INSERT INTO COFFEE_BOUTIQUE.PROMOTES VALUES (3, 3);

-------------------50 SALES-------------------

INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (1, 1, 1, 1, 2, '2022-08-02 10:45:12', 5.90, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (2, 2, 2, 2, 1, '2022-07-04 11:32:23', 3.65, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (3, 3, 3, 3, 2, '2022-06-06 12:10:10', 8.30, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (4, 4, 1, 4, 3, '2022-05-08 14:22:26', 13.95, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (5, 5, 2, 5, 1, '2022-07-10 15:12:54', 7.30, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (6, 6, 3, 6, 1, '2022-06-12 17:23:55', 3.45, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (7, 7, 1, 7, 1, '2022-05-14 11:57:58', 7.45, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (8, 8, 2, 8, 2, '2022-07-16 12:12:24', 14.30, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (9, 9, 3, 9, 1, '2022-06-18 13:28:24', 7.80, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (10, 10, 1, 10, 1, '2022-05-20 14:18:24', 7.15, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (11, 11, 2, 11, 2, '2022-08-22 08:19:25', 14.60, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (12, 12, 3, 12, 3, '2022-07-24 09:26:26', 23.85, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (13, 13, 1, 1, 1, '2022-06-26 07:34:47', 2.95, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (14, 14, 2, 2, 1, '2022-05-28 06:33:48', 3.65, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (15, 15, 3, 3, 1, '2022-08-01 06:38:34', 4.15, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (16, 16, 1, 4, 2, '2022-07-03 08:37:36', 9.30, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (17, 17, 2, 5, 2, '2022-06-05 10:43:34', 14.60, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (18, 18, 3, 6, 1, '2022-05-07 10:46:14', 3.45, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (19, 19, 1, 7, 2, '2022-08-09 12:48:18', 14.90, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (20, 20, 2, 8, 1, '2022-07-11 10:19:14', 7.15, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (21,  1, 3, 9, 1, '2022-06-13 12:10:18', 7.80, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (24, 4, 3, 12, 1, '2022-05-15 10:12:14', 7.95, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (25, 5, 1, 1, 1, '2022-08-17 13:14:49', 2.95, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (26, 6, 2, 2, 2, '2022-07-19 14:24:45', 7.30, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (27, 7, 3, 3, 3, '2022-06-21 15:26:44', 12.45, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (28, 8, 1, 4, 4, '2022-05-23 16:23:53', 18.60, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (29, 9, 2, 5, 4, '2022-08-25 17:29:54', 29.20, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (30, 10, 3, 6, 1, '2022-07-27 12:23:55', 3.45, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (31, 11, 1, 7, 1, '2022-06-29 18:23:37', 7.45, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (32, 12, 2, 8, 1, '2022-05-02 14:54:36', 7.15, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (33, 13, 3, 9, 2, '2022-08-04 19:52:34', 15.60, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (34, 14, 1, 10, 2, '2022-07-06 11:21:54', 14.30, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (35, 15, 2, 11, 1, '2022-06-08 11:51:29', 7.30, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (36, 16, 3, 12, 2, '2022-05-10 11:52:24', 15.9, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (37, 17, 1, 1, 1, '2022-08-12 12:12:45', 2.95, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (38, 18, 2, 2, 1, '2022-07-14 14:02:44', 3.65, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (39, 19, 3, 3, 1, '2022-06-16 16:03:46', 4.15, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (40, 20, 1, 4, 1, '2022-05-18 14:23:24', 4.65, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (41, 1, 2, 5, 2, '2022-08-20 12:23:24', 14.60, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (42, 2, 3, 6, 3, '2022-07-22 09:54:28', 10.35, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (43, 3, 1, 7, 2, '2022-06-24 08:22:38', 14.90, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (44, 4, 2, 8, 1, '2022-05-26 17:28:34', 7.15, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (45, 5, 3, 9, 2, '2022-08-28 05:29:38', 15.60, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (46, 6, 1, 10, 2, '2022-07-01 06:43:14', 14.30, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (47, 7, 2, 11, 1, '2022-06-03 07:42:16', 7.30, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (48, 8, 3, 12, 1, '2022-05-05 09:49:17', 7.95, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (49, 9, 1, 1, 1, '2022-08-07 09:50:56', 2.95, 0);
INSERT INTO COFFEE_BOUTIQUE.SALE VALUES (50, 10, 2, 2, 1, '2022-07-09 10:34:54', 3.65, 0);

