/*
cd C:\Users\clark\OneDrive\Desktop\CS1555\BoutiqueCoffee\Phase III
javac -cp "postgresql-42.5.0.jar;." BCBenchmark.java
java -cp "postgresql-42.5.0.jar;." BCBenchmark
*/
import java.util.Scanner;
import java.util.Properties;
import java.util.Random;
import java.sql.*;
import java.text.DecimalFormat;


public class BCBenchmark {
    public static void main(String[] args) throws
            SQLException, ClassNotFoundException {

        //Connection
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/";
        Properties props = new Properties();
        props.setProperty("user", "postgres"); //cpn14
        props.setProperty("password", "BillieBoi"); //BillieBoi - Dp3008395
        Connection conn = DriverManager.getConnection(url, props);
        System.out.println("Connection made to: " + conn);
        System.out.println("\n\n----Beginning Coffee Boutique Driver Program----\n\n");

        Scanner cont = new Scanner(System.in);
        System.out.print("Press enter to begin the benchmark...");
        String inp = cont.nextLine();
        System.out.println("---\n");

        //Task #1 - Add 2 New store X
        System.out.println("Benchmarking Task #1...");
        task_1(conn);
        System.out.println("\n");

        //Task #2 - Add 3 new coffee X
        System.out.println("Benchmarking Task #2...");
        task_2(conn);
        System.out.println("\n");

        //Task #13 - Display Coffee List X
        System.out.println("Benchmarking Task #13...");
        task_13(conn);
        System.out.println("\n");

        //Task #3 - Schedule Promotion for Coffee - X
        System.out.println("Benchmarking Task #3...");
        task_3(conn);
        System.out.println("\n");

        //Task #4 - Add offer to new store - X
        System.out.println("Benchmarking Task #4...");
        task_4(conn);
        System.out.println("\n");

        //Task #5 - List all stores with promotions - x
        System.out.print("Press enter to begin benchmarking task#5...\n");
        task_5(conn);
        System.out.println();

        //Task #6 - Check if store has promotions - both options -
        System.out.print("Press enter to begin benchmarking task#6...");
        task_6(conn);
        System.out.println();

        //Task#7
        //Task #7 - Check for closest store -
        System.out.print("Press enter to begin benchmarking task#7...");
        task_7(conn);
        System.out.println();


        //Task #8 - Add/Set Loyalty Level - then update it
        //Task #8 - Update Loyalty Level - then update it
        System.out.print("Press enter to begin benchmarking task#8...");
        inp = cont.nextLine(); System.out.println("");
        task_8(conn);
        System.out.println();

        //Task #9 - Add 2 new customer
        System.out.print("Benchmarking Task #9...");
        task_9(conn);
        System.out.println();

        //Task #10 - Show Loyalty Points of customer
        System.out.print("Press enter to begin testing task#10...");
        task_10(conn);
        System.out.println();

        //Task #11 - Produce List of loyal customers
        System.out.print("Benchmarking Task #11...");
        task_11(conn);
        System.out.println();

        //Task #12 - Add purchase for 2 customer - 45 days from today
        System.out.print("Press enter to begin testing task#12...");
        inp = cont.nextLine();
        task_12(conn);
        System.out.println();

        //Task #14 - List coffee with specified coffee name keywords
        System.out.print("Benchmarking Task #14...");
        task_14(conn);
        System.out.println();

        //Task #15 - List IDS of top stores with revenus for X months
        System.out.print("Benchmarking Task #15...");
        task_15(conn);
        System.out.println();

        //Task #16 List IDs of top K customer shavnig spent most money
        System.out.print("Benchmarking Task #16...");
        task_16(conn);
        System.out.println();
    }

// Query Methods for BCBenchmark //

    //Task #1
    public static void task_1(Connection conn) {
        Random rand = new Random();
        String[] storetypes = {"sitting", "kiosk"};
        System.out.println("\n----Adding 100 stores----\n");
        for (int i = 0; i < 100; i++) {
            String name = "ExampleStore#" + rand.nextInt(100); // some inserts will fail
            String storeType = storetypes[i % storetypes.length]; // alternate storetypes
            float gpsLongNum = rand.nextFloat() * 360 - 180;
            float gpsLatNum = rand.nextFloat() * 180 - 90;
            String gpsLong = String.valueOf(gpsLongNum);
            String gpsLat = String.valueOf(gpsLatNum);

            try {
                // generate unique ID
                Statement st = conn.createStatement();
                String query1 = "SELECT MAX(store_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.STORE";
                ResultSet res1 = st.executeQuery(query1);
                String storeID = "";
                while (res1.next()) {
                    storeID = res1.getString("ID");
                    if (storeID == null) {
                        storeID = "0";
                    }
                }
                // prepare statement
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.STORE VALUES (?, ?, ?, ?, ?)");
                stmt.setString(1, storeID);
                stmt.setString(2, name);
                stmt.setString(3, storeType);
                stmt.setString(4, gpsLong);
                stmt.setString(5, gpsLat);
                // try adding the store
                conn.setAutoCommit(false);
                st.executeUpdate(stmt.toString());
                conn.commit();
                System.out.println("ADDED TO DATABASE SUCCESSFULLY - STORE_ID: " + storeID);
            } catch (SQLException e1) {
                try {
                    System.out.println("ERROR: STORE NOT ADDED TO DATABASE");
                    conn.rollback();
                }
                catch(SQLException e2) { System.out.println(e2.toString()); }
            }
        }
    };

    //Task #2
    public static void task_2(Connection conn) {
        //Declaring Task
        Random rand = new Random();
        DecimalFormat df = new DecimalFormat("##.##");
        System.out.println("\n----Adding 100 Coffees----\n");
        for (int i = 0; i < 100; i++) {
            String name = "ExampleCoffee#" + rand.nextInt(100);
            String description = "This is a description.";
            String country = "Country";
            String intensity = String.valueOf(rand.nextInt(11) + 1);
            String price = df.format(rand.nextDouble() * 30);
            String rewardPoints = df.format(rand.nextDouble() * 30);
            String redeemPoints = df.format(rand.nextDouble() * 30);
            //Processing Queries
            try {
                //Getting Coffee ID
                Statement st = conn.createStatement();
                String searchQuery = "SELECT MAX(coffee_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.COFFEE";
                ResultSet searchRes = st.executeQuery(searchQuery);
                String coffeeID = "";
                while (searchRes.next()) {
                    coffeeID = searchRes.getString("ID");
                    if (coffeeID == null) {
                        coffeeID = "0";
                    }
                }

                //Create Insert Statement
                PreparedStatement insert = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                insert.setString(1, coffeeID);
                insert.setString(2, name);
                insert.setString(3, description);
                insert.setString(4, country);
                insert.setString(5, intensity);
                insert.setString(6, price);
                insert.setString(7, rewardPoints);
                insert.setString(8, redeemPoints);

                //Attempt to Insert
                conn.setAutoCommit(false);
                st.executeUpdate(insert.toString());
                conn.commit();
                System.out.println("Added to Database Successfully - CoffeeID: " + coffeeID);
            } catch (SQLException e1) {
                try {
                    //System.out.println(e1.toString());
                    System.out.println("ERROR: COFFEE NOT ADDED TO DATABASE");
                    conn.rollback();
                } catch (SQLException e2) {
                    System.out.println(e2.toString());
                }
            }
        }
    };

    //Task #3
    public static void task_3(Connection conn) throws SQLException, ClassNotFoundException {
            Scanner scan = new Scanner(System.in);
            Random rand = new Random();
            String[] startdates = {"4-15-2020", "3-15-2021", "12-10-2022", "12-15-2022", "12-16-2022", "1-6-2023", "4-12-2023", "1-12-2023", "1-18-2023"};
            String[] enddates = {"4-15-2023", "3-15-2023", "12-10-2023", "12-15-2023", "12-16-2023", "1-6-2023", "7-21-2023", "4-30-2023", "9-9-2023"};

            System.out.println("\n----Scheduling a Promotion----\n");
            // generate promotions
            for (int i = 0; i < 100; i++) {
                Statement st1 = conn.createStatement();
                String query1 = "SELECT MAX(coffee_ID) AS ID FROM COFFEE_BOUTIQUE.COFFEE";
                ResultSet res1 = st1.executeQuery(query1);
                int maxID = 0;
                // get max coffee id for random generation
                while (res1.next()){
                    maxID = res1.getInt("ID");
                }
                // promotions
                String promotion_name = "ExamplePromotion#" + rand.nextInt(100);
                String start_date = startdates[rand.nextInt(startdates.length)];
                String end_date = enddates[rand.nextInt(enddates.length)];
                String coffee_id = String.valueOf(rand.nextInt(maxID));

                Statement st2 = conn.createStatement();
                String query2 = "SELECT MAX(promotion_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.PROMOTION";
                ResultSet res2 = st2.executeQuery(query2);
                String promotion_ID = "";
                if (!res2.isBeforeFirst()) {
                    promotion_ID = "0";
                } else {
                    while (res2.next()) {
                        promotion_ID = res2.getString("ID");
                        if (promotion_ID == null) {
                            promotion_ID = "0";
                        }
                    }
                }

                PreparedStatement prep_statement = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.PROMOTION VALUES (?,?,?,?)");
                prep_statement.setString(1, promotion_ID);
                prep_statement.setString(2, promotion_name);
                prep_statement.setString(3, start_date);
                prep_statement.setString(4, end_date);
                PreparedStatement prep_statement2 = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.PROMOTES VALUES (?,?)");
                prep_statement2.setString(1, promotion_ID);
                prep_statement2.setString(2, coffee_id);
                try {
                    conn.setAutoCommit(false);
                    st2.executeUpdate(prep_statement.toString());
                    st2.executeUpdate(prep_statement2.toString());
                    conn.commit();
                    System.out.println("ADDED TO DATABASE SUCCESSFULLY - PROMOTION_ID: " + promotion_ID);
                }
                catch (SQLException e1) {
                    try {
                        System.out.println("ERROR: PROMOTION NOT ADDED TO DATABASE");
                        System.out.println(e1);
                        conn.rollback();
                    }
                    catch(SQLException e2) { System.out.println(e2.toString()); }
                }
            }
    };

    //Task #4
    public static void task_4(Connection conn) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("\n----Adding 200 Promotions to a Store----\n"); // 200 is more likely to cause collisions
        int maxpromotionID = 0;
        int maxstoreID = 0;

        for (int i = 0; i < 200; i++) {
            try {
                // get max promotion id for random generation
                Statement st1 = conn.createStatement();
                String query1 = "SELECT MAX(promotion_ID) AS ID FROM COFFEE_BOUTIQUE.PROMOTION";
                ResultSet res1 = st1.executeQuery(query1);
                // get max promotion id for random generation
                if (!res1.isBeforeFirst()) {
                    maxpromotionID = 0;
                } else {
                    while (res1.next()) {
                        maxpromotionID = res1.getInt("ID");
                    }
                }

                // get max store id for random generation
                Statement st2 = conn.createStatement();
                String query2 = "SELECT MAX(store_ID) AS ID FROM COFFEE_BOUTIQUE.STORE";
                ResultSet res2 = st2.executeQuery(query2);
                // get max store id for random generation
                if (!res2.isBeforeFirst()) {
                    maxstoreID = 0;
                } else {
                    while (res2.next()) {
                        maxstoreID = res2.getInt("ID");
                    }
                }
                // prepare statement
                String promotionID = String.valueOf(rand.nextInt(maxpromotionID));
                String storeID = String.valueOf(rand.nextInt(maxstoreID));
                Statement st = conn.createStatement();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.CARRIES VALUES (?, ?)");
                stmt.setString(1, promotionID);
                stmt.setString(2, storeID);
                // try adding the promotion to the store
                conn.setAutoCommit(false);
                st.executeUpdate(stmt.toString());
                conn.commit();
                System.out.println("ADDED TO DATABASE SUCCESSFULLY - STORE_ID: " + storeID);
            } catch (SQLException e1) {
                try {
                    System.out.println("ERROR: PROMOTION NOT ADDED TO STORE");
                    System.out.println(e1);
                    conn.rollback();
                }
                catch(SQLException e2) { System.out.println(e2.toString()); }
            }
        }
    };

    //Task #5 - X
    public static void task_5(Connection conn) {
        //Presenting Choices
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("\n----Promotional Offers----");
        System.out.println("(1) List All Stores with Promotions");

        String choice = "1";
        switch(choice){
            case "1":
                //Processing Queries
                try{
                    //Getting Coffee ID
                    Statement st = conn.createStatement();
                    String searchQuery = "SELECT store_ID, promotion_ID FROM COFFEE_BOUTIQUE.CARRIES";
                    ResultSet searchRes = st.executeQuery(searchQuery);
                    if (searchRes == null)
                        System.out.println("No stores are currently offering any promotions");
                    while(searchRes.next()){
                        //Print Results
                        System.out.println("The Store with ID: " + searchRes.getString("store_ID") + " carries Promotion: " + searchRes.getString("promotion_ID"));
                    }
                }
                catch (SQLException e1){
                    System.out.println(e1.toString());
                }
                break;
            case "2":
                //Gets user inputs
                String coffeeID = "0";
                //Processing Queries
                try{
                    //Getting Coffee ID
                    Statement st = conn.createStatement();
                    String searchQuery = "SELECT promotion_ID FROM COFFEE_BOUTIQUE.PROMOTES WHERE coffee_ID = " + coffeeID;
                    ResultSet searchRes = st.executeQuery(searchQuery);
                    while(searchRes.next()){
                        Statement st2 = conn.createStatement();
                        String promotionID = searchRes.getString("promotion_ID");
                        String searchQuery2 = "SELECT store_ID FROM COFFEE_BOUTIQUE.CARRIES WHERE promotion_ID = " + promotionID;
                        ResultSet searchRes2 = st2.executeQuery(searchQuery2);
                        while(searchRes2.next()){
                            System.out.println("The Store with ID: " + searchRes2.getString("store_ID") + " carries Promotion: " + promotionID + " for the Coffee with ID " + coffeeID);
                        }
                    }
                }
                catch (SQLException e1){
                    System.out.println(e1.toString());
                }

                break;
            default:
                System.out.println("Not a Valid Choice");
        }

        System.out.println("\n----Promotional Offers----");
        System.out.println("(2) List All Stores Promoting Specific Coffee (x20)");
        choice = "2";
        for (int i = 0; i < 20; i++) {
            switch(choice){
                case "1":
                    //Processing Queries
                    try{
                        //Getting Coffee ID
                        Statement st = conn.createStatement();
                        String searchQuery = "SELECT store_ID, promotion_ID FROM COFFEE_BOUTIQUE.CARRIES";
                        ResultSet searchRes = st.executeQuery(searchQuery);
                        if (searchRes == null)
                            System.out.println("No stores are currently offering any promotions");
                        while(searchRes.next()){
                            //Print Results
                            System.out.println("The Store with ID: " + searchRes.getString("store_ID") + " carries Promotion: " + searchRes.getString("promotion_ID"));
                        }
                    }
                    catch (SQLException e1){
                        System.out.println(e1.toString());
                    }
                    break;
                case "2":
                    //Processing Queries
                    try{
                        //Gets user inputs
                        int maxID = 0;
                        // get max coffee id for random generation
                        Statement st1 = conn.createStatement();
                        String query1 = "SELECT MAX(coffee_ID) AS ID FROM COFFEE_BOUTIQUE.COFFEE";
                        ResultSet res1 = st1.executeQuery(query1);
                        // get max coffee id for random generation
                        while (res1.next()){
                            maxID = res1.getInt("ID");
                        }
                        String coffeeID = String.valueOf(rand.nextInt(maxID));
                        //Getting Coffee ID
                        Statement st = conn.createStatement();
                        String searchQuery = "SELECT promotion_ID FROM COFFEE_BOUTIQUE.PROMOTES WHERE coffee_ID = " + coffeeID;
                        ResultSet searchRes = st.executeQuery(searchQuery);
                        while(searchRes.next()){
                            Statement st2 = conn.createStatement();
                            String promotionID = searchRes.getString("promotion_ID");
                            String searchQuery2 = "SELECT store_ID FROM COFFEE_BOUTIQUE.CARRIES WHERE promotion_ID = " + promotionID;
                            ResultSet searchRes2 = st2.executeQuery(searchQuery2);
                            while(searchRes2.next()){
                                System.out.println("The Store with ID: " + searchRes2.getString("store_ID") + " carries Promotion: " + promotionID + " for the Coffee with ID " + coffeeID);
                            }
                        }
                    }
                    catch (SQLException e1){
                        System.out.println(e1.toString());
                    }

                    break;
                default:
                    System.out.println("Not a Valid Choice");
            }
        }
    };

    //Task #6
    public static void task_6(Connection conn) throws SQLException, ClassNotFoundException {
        Random rand = new Random();
        System.out.println("\n----Promotional Offers----\n");
        System.out.println("(1) - List all promotional offers for a store (x20)");
        String store = "";
        String coffee = "";
        String rpromotion_ID = "";
        String rstore_ID = "";
        String rcoffee = "";
        String rpromotion_name = "";
        ResultSet res1;

        Scanner scan = new Scanner(System.in);
        String choice = "1";

        if (choice.equals("1")) {
            for (int i = 0; i < 5; i++) {
                int maxstoreID = 0;
                // get max store id for random generation
                Statement st2 = conn.createStatement();
                String query2 = "SELECT MAX(store_ID) AS ID FROM COFFEE_BOUTIQUE.STORE";
                ResultSet res2 = st2.executeQuery(query2);
                // get max store id for random generation
                if (!res2.isBeforeFirst()) {
                    maxstoreID = 0;
                } else {
                    while (res2.next()) {
                        maxstoreID = res2.getInt("ID");
                    }
                }
                store = String.valueOf(rand.nextInt(maxstoreID));
                Statement st = conn.createStatement();
                PreparedStatement prep_statement = conn.prepareStatement("SELECT * FROM COFFEE_BOUTIQUE.CARRIES NATURAL JOIN COFFEE_BOUTIQUE.PROMOTION WHERE store_ID = ?");
                prep_statement.setString(1, store);

                try {
                    res1 = st.executeQuery(prep_statement.toString());
                    if (!res1.isBeforeFirst()) {
                        System.out.println("No promotions are currently offered at this store");
                    }
                    else {
                        System.out.println("QUERY SUCCESSFULL for store with id: " + store);
                        System.out.println("-----------------");
                        System.out.println("Promotion Name");
                        System.out.println("-----------------------");
                        while (res1.next()) {
                            rpromotion_name = res1.getString("name");
                            System.out.println(rpromotion_name);
                        }
                    }
                }
                catch (SQLException e1) {
                    while (e1 != null) {
                        System.out.println("Message = "+ e1.toString());
                        e1 = e1.getNextException();
                    }
                }
            }
        } else if (choice.equals("2")) {
            store = "0";
            coffee = "0";
            Statement st = conn.createStatement();
            PreparedStatement prep_statement = conn.prepareStatement(" SELECT * FROM COFFEE_BOUTIQUE.PROMOTES JOIN COFFEE_BOUTIQUE.CARRIES USING(promotion_ID) JOIN COFFEE_BOUTIQUE.PROMOTION USING(promotion_ID) WHERE store_ID = ? and coffee_id = ? ");
            prep_statement.setString(1, store);
            prep_statement.setString(2, coffee);
            try {
                res1 = st.executeQuery(prep_statement.toString());
                if (!res1.isBeforeFirst()) {
                    System.out.println("No promotions for this coffee are currently offered at this store");
                    return;
                }
                System.out.println("QUERY SUCCESSFULL");
                System.out.println("-----------------");
                System.out.println("Promotion_ID | Promotion Name");
                System.out.println("-----------------------");
                while (res1.next()) {
                    rpromotion_ID = res1.getString("promotion_ID");
                    rpromotion_name = res1.getString("name");
                    System.out.println("           "+ rpromotion_ID + " | " + rpromotion_name);
                }
            }
            catch (SQLException e1) {
                while (e1 != null) {
                    System.out.println("Message = "+ e1.toString());
                    e1 = e1.getNextException();
                }
            }
        } else /*error*/ {
            System.out.println("ERROR: CHOOSE EITHER 1 OR 2");
            return;
        }

        System.out.println("\n----Promotional Offers----\n");
        System.out.println("(2) - Offers on a specific coffee at a store (x200)");
        System.out.print("Choice: ");

        store = "";
        coffee = "";
        rpromotion_ID = "";
        rstore_ID = "";
        rcoffee = "";
        rpromotion_name = "";



        choice = "2";

        if (choice.equals("1")) {
            store = "0";
            Statement st = conn.createStatement();
            PreparedStatement prep_statement = conn.prepareStatement("SELECT * FROM COFFEE_BOUTIQUE.CARRIES INNER JOIN COFFEE_BOUTIQUE.PROMOTION ON store_ID = ?");
            prep_statement.setString(1, store);

            try {
                res1 = st.executeQuery(prep_statement.toString());
                if (!res1.isBeforeFirst()) {
                    System.out.println("No promotions are currently offered at this store");
                    return;
                }
                System.out.println("QUERY SUCCESSFULL");
                System.out.println("-----------------");
                System.out.println("Promotion Name");
                System.out.println("-----------------------");
                while (res1.next()) {
                    rpromotion_name = res1.getString("name");
                    System.out.println( rpromotion_name);
                }
            }
            catch (SQLException e1) {
                while (e1 != null) {
                    System.out.println("Message = "+ e1.toString());
                    e1 = e1.getNextException();
                }
            }

        } else if (choice.equals("2")) {
            for (int i = 0; i < 200; i++) {
                int maxstoreID = 0;
                Statement st1 = conn.createStatement();
                String query1 = "SELECT MAX(coffee_ID) AS ID FROM COFFEE_BOUTIQUE.COFFEE";
                ResultSet res3 = st1.executeQuery(query1);
                int maxcoffeeID = 0;
                // get max coffee id for random generation
                while (res3.next()){
                    maxcoffeeID = res3.getInt("ID");
                }
                // get max store id for random generation
                Statement st2 = conn.createStatement();
                String query2 = "SELECT MAX(store_ID) AS ID FROM COFFEE_BOUTIQUE.STORE";
                ResultSet res2 = st2.executeQuery(query2);
                // get max store id for random generation
                if (!res2.isBeforeFirst()) {
                    maxstoreID = 0;
                } else {
                    while (res2.next()) {
                        maxstoreID = res2.getInt("ID");
                    }
                }
                coffee = String.valueOf(rand.nextInt(maxcoffeeID));
                store = String.valueOf(rand.nextInt(maxstoreID));
                Statement st = conn.createStatement();
                PreparedStatement prep_statement = conn.prepareStatement(" SELECT * FROM COFFEE_BOUTIQUE.PROMOTES JOIN COFFEE_BOUTIQUE.CARRIES USING(promotion_ID) JOIN COFFEE_BOUTIQUE.PROMOTION USING(promotion_ID) WHERE store_ID = ? and coffee_id = ? ");
                prep_statement.setString(1, store);
                prep_statement.setString(2, coffee);
                try {
                    res1 = st.executeQuery(prep_statement.toString());
                    if (!res1.isBeforeFirst()) {
                        System.out.println("No promotions for this coffee are currently offered at this store");
                    }
                    else {
                        System.out.println("QUERY SUCCESSFULL");
                        System.out.println("-----------------");
                        System.out.println("Promotion_ID | Promotion Name");
                        System.out.println("-----------------------");
                        while (res1.next()) {
                            rpromotion_ID = res1.getString("promotion_ID");
                            rpromotion_name = res1.getString("name");
                            System.out.println("           "+ rpromotion_ID + " | " + rpromotion_name);
                        }
                    }
                }
                catch (SQLException e1) {
                    while (e1 != null) {
                        System.out.println("Message = "+ e1.toString());
                        e1 = e1.getNextException();
                    }
                }
            }
        } else /*error*/ {
            System.out.println("ERROR: CHOOSE EITHER 1 OR 2");
            return;
        }
    };

    //Task #7
    public static void task_7(Connection conn) {

    };

    //Task #8
    public static void task_8(Connection conn) {

    };

    //Task #9
    public static void task_9(Connection conn) throws SQLException, ClassNotFoundException {

    };

    //Task #10
    public static void task_10(Connection conn) {

    };

    //Task #11
    public static void task_11(Connection conn) {

    };

    //Task #12
    public static void task_12(Connection conn) throws SQLException, ClassNotFoundException {

    };

    //Task #13
    public static void task_13(Connection conn) throws SQLException, ClassNotFoundException {

    };

    //Task #14
    public static void task_14(Connection conn) {

    };

    //Task #15
    public static void task_15(Connection conn) {

    };

    //Task #16
    public static void task_16(Connection conn) {

    };
}
