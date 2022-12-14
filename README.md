
## Team: Carpe Data - Clark, Daniel, Dharma

### ***Section I: How to use BoutiqueCoffee.java***
---  
Steps to run: 
1. Clone the repository to your desktop for convenience
2. Open and run the latest Phase `schema2.sql` and `triggers.sql` files in DataGrip located within the Phase II directory.  
   (Optional: Run the additional `sample-data.sql` file)
3. Open your systems command prompt and navigate inside the Phase II folder 
4. Execute the proper instruction to compile BoutiqueCoffee.java with a compatible jar file. One is included within the folder   
   (example: `javac -cp "postgresql-42.5.0.jar;." BoutiqueCoffee.java`)
5. Execute: `java -cp "postgresql-42.5.0.jar;." BoutiqueCoffee` to begin program execution

Within terminal/command prompt, you will now be prompted with 17 options, one of which (0) is to exit the program. 

## **Notes on execution** -   

If user has chosen not to execute `sample-data.sql` file as specified above, due to constraints of database, a user may not Add a new customer (task #9) to the database prior to adding a Loyalty Level (task #8). This is due to every customer needing to be attributed a membership status. Once a loyalty level has been added, customers may be added to the database as needed.     

Below are notes of useful information for each tasked. When prompted, each task may be executed by simply entering the number of the desired task then pressing enter. If an incorrect number is entered, the user will be continually prompted for a correct number. Any other input will most likely cause a crash.    

---  

Task #1 -  (Add new store) When asked for store type, only `sitting` or `kiosk` types are accepted.    

Task #2 -  (Add new coffee) When asked for intensity, only values 1-12 are accepted. When asked for award points, value must be 10x designated price of coffee. Likewise, redeem_points must be 100x price of input for coffee.  

Task #3 -  (Add new promotion) Ensure when entering a promotion start date/promotion end date, that start is prior to end or input will be rejected. Format for input is MM-DD-YYYY.  

Task #4 -  (Add promotion to store) Three example entries are inserted if `sample-data.sql` is executed prior to starting `BoutiqueCoffee.java`. Consult DataGrip for store and promotion ids.  

Task #5 -  (List all stores with promotions) Sub-options will be provided upon execution.  

Task #6 -  (Check if given store has promotion) Sub-options will be provided upon execution.  

Task #7 -  (Find closest store) Optional stores are provided in `sample-data.sql`. Format of input in string/float for both lattitude and longitude.  

Task #8 -  (Add/Set Loyalty Level) Sub-options will be provided upon execution.   

Task #9 -  (Add new customer) Sub-options will be provided upon execution.  

Task #10 - (Show customer loyalty points) Enter customer ID to execute.   

Task #11 - (Most loyal customers).    

Task #12 -  (Add a purchase) Sub-options will be provided upon execution. Based on number of different coffee types a user enters when prompted, consecutive prompts will be displayed to the user asking how many coffees a user wishes to purchase without points. Another prompt will ask the user how many coffees they wish to redeem with points. A final prompt will be given to the user to ensure that the order is correct. If yes, enter 'y' or 'Y' or 'n' or 'N' for no. If a user does not have the apporpriate number of points to redeem, a message will be displayed to the user. If they do, transaction will execute successfully.    

Task #13 - (Show full coffee menu).    

Task #14 - (Coffees with specified intensity) Task #13 can be used to assist with keyword lookup.   

Task #15 - (Highest revenue stores) Sub-options will be provided upon execution.   

Task #16 - (Highest spending customers) Sub-options will be provided upon execution.   

   
---  

### ***Section II: How to use BCDriver.java***
---    

Steps to run: 
1. Clone the repository to your desktop for convenience
2. Open and run the latest Phase `schema3.sql` and `triggers.sql` files in DataGrip located within the Phase III directory.  
3. Open your systems command prompt and navigate inside the Phase III folder 
4. Execute the proper instruction to compile BoutiqueCoffee.java with a compatible jar file. One is included within the folder   
   (example: `javac -cp "postgresql-42.5.0.jar;." BCDriver.java`)
5. Execute: `java -cp "postgresql-42.5.0.jar;." BCDriver` to begin program execution  

ORDER OF EXECUTION & EXPECTED OUTCOME

Task #1 - STORE_ID: 0, 1 ADDED SUCCESSFULLY TO DATABASE  

Task #2 - COFFEE_ID: 0, 1, 2 ADDED SUCCESSFULLY TO DATABASE  

Task #13 - FULL MENU DISPLAY OF COFFEE W/ ID: 0, 1, 2  

Task #3 - PROMOTION_ID: 0 ADDED SUCCESSFULLY TO DATABASE  

Task #4 - ADDED PROMOTION_ID: 0 TO STORE W/ STORE_ID: 0  

Task #5   
- 1ST EXECUTION: STORE_ID: 0 WILL SHOW PROMOTION_ID: 0  
- 2ND EXECUTION: STORE_ID: 0 WILL SHOW PROMOTION_ID: 0 FOR COFFEE_ID: 0  
        
Task #6   
- 1ST EXECUTION: STORE_ID:0 WILL SHOW BRIAN'S WINTER PROMOTION  
- 2ND EXECUTION: COFFEE_ID: 0 WILL RESULT WITH PROMOTION_ID: 0 AND PROMOTION NAME: BRIAN'S WINTER PROMOTION  
        
Task #7   
- 1ST EXECUTION: CLOSEST STORE WITH LAT:75000 AND LONG:10000 WILL DISPLAY EXAMPLE_STORE#1, THEN LAT:0 AND LONG:0 WILL DISPLAY EXAMPLE_STORE#2  
- 2ND EXECUTION: EXAMPLE_STORE#1 WITH STORE_ID:0 WILL BE DISPLAYED FROM CHECK FOR SPECIFIC PROMOTION WITH LAT:75000 AND LONG:10000  
        
Task #8 - BRONZE ADDED W/ 1.5 BOOSTER FACTOR. AFTER ENTER, BOOSTER FACTOR WILL BE 1. THEN DIAMOND LOYALTY LEVEL ADDED.  

Task #9 - CUSTOMER_ID: 0, 1 ADDED SUCCESSFULLY TO DATABASE  

Task #10 - CUSTOMER LOYALTY POINTS FOR CUSTOMER_ID: 0 SHOWN TO BE 1500.0 POINTS  

Task #11 - CUSTOMERS RANKED BY LOYALTY W/ 1. PANOS THEN 2. BRIAN  

Task #12 
- TWO SEPARATE TRANSACTIONS CAN BE OBSERVED FOR PANOS, NEITHER OF WHICH EARN POINTS DUE TO USE OF REDEEMING COFFEE.  
- ONE TRANSACTION CAN BE OBSERVED FOR BRIAN, WHOSE POINTS EARNED WILL INCREASE DUE TO NOT REDEEMING COFFEE FOR FREE.   
         

Task #14 - SEARCH FOR KEYWORDS: WHITE & CHOCOLATE, INTENSITY 12 RETURNS COFFEE_ID: 2 SUCCESSFULLY  

Task #15 
- 1ST EXECUTION: STORE_ID: 1 RETURNED, AS THAT IS LATEST PURHCASE WITHIN 1 MONTH   
- 2ND EXECUTION: STORE_ID: 1, 2 RETURNED, AS THAT IS LATEST PURCHASES WITHIN 3 MONTHS  
         
Task #16 
- 1ST EXECUTION: CUSTOMER_ID: 1 RETURNED, AS THAT IS LATEST CUSTOMER REVENUE PRODUCED WITHIN 1 MONTH   
- 2ND EXECUTION: CUSTOMER_ID: 0, 1 RETURNED, AS THAT IS LATEST CUSTOMER REVENUE PRODUCED WITHIN 3 MONTHS  
         

## **Notes on execution** -   

Program contains preliminary pause prior to testing. To begin automatic execution of tasks #1-#7, press enter.  
User can now confirm in DataGrip that values were entered correctly.  

To begin testing task#8, press enter.  
Add a new loyalty program, ensuring that it was entered into DataGrip. Then continue program execution to update loyalty level and ensure change reflects in DataGrip.  
Press enter to continue executing tasks #9-#11.  

To begin testing task#12, press enter.  

Follow the prompts, entering the correct input then confirming inside DataGrip correct execution. 
For the first **Panos** purchase, DataGrip will show two separate transactions, indicating the different types of coffees/quantities for the purchase. No point difference will be seen, as using points to redeem coffee results in no points being earned. 

For the second **Brian** purchase, redeem points will be successfully added, as no points were used in redeeming a coffee.

![image](https://user-images.githubusercontent.com/44730537/207200379-e88cd6c3-5d09-4721-a1ef-8abad3c09859.png)

The remainder of the BCDriver will execute after task #12.

### ***Section III: How to use BCBenchmark.java***
---  

The benchmark program runs all tasks multiple times using random data. 

Steps to run:
1. Clone the repository to your desktop for convenience
2. Open and run the latest Phase `schema3.sql` and `triggers.sql` files in DataGrip located within the Phase III directory.  
3. The basic loyalty level must be inserted into the database (This can be done by inserting the data in sample-data.sql).
4. Open your systems command prompt and navigate inside the Phase III folder 
5. Execute the proper instruction to compile BoutiqueCoffee.java with a compatible jar file. One is included within the folder   
   (example: `javac -cp "postgresql-42.5.0.jar;." BCBenchmark.java`)
6. Execute: `java -cp "postgresql-42.5.0.jar;." BCBenchmark` to begin program execution  
7. Press Enter after the message for each benchmark test.

The program runs automatically. Some tests will succeed, but some will fail. This is expected, as the data is randomly generated. The program will output error information for the failures.
