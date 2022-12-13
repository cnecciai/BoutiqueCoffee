/*
cd C:\Users\clark\OneDrive\Desktop\CS1555\BoutiqueCoffee\Phase III
javac -cp "postgresql-42.5.0.jar;." BCDriver.java
java -cp "postgresql-42.5.0.jar;." BCDriver
*/
import java.util.Scanner;
import java.util.Properties;
import java.sql.*;

public class BCDriver {
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
  System.out.print("Press enter to begin testing...");
  String inp = cont.nextLine();
  System.out.println("---\n");

  //Task #1 - Add 2 New store X
  System.out.println("Simulating Task #1...");
  task_1(conn);
  System.out.println("\n");

  //Task #2 - Add 3 new coffee X
  System.out.println("Simulating Task #2...");
  task_2(conn);
  System.out.println("\n");

  //Task #13 - Display Coffee List X
  System.out.println("Simulating Task #13...");
  task_13(conn);
  System.out.println("\n");

  //Task #3 - Schedule Promotion for Coffee - X
  System.out.println("Simulating Task #3...");
  task_3(conn);
  System.out.println("\n");

  //Task #4 - Add offer to new store - X
  System.out.println("Simulating Task #4...");
  task_4(conn);
  System.out.println("\n");

  //Task #5 - List all stores with promotions - x
  System.out.print("Press enter to begin testing task#5...\n");
  task_5(conn);
  System.out.println();

  //Task #6 - Check if store has promotions - both options -
  System.out.print("Press enter to begin testing task#6...");
  task_6(conn);
  System.out.println();

  //Task#7
  //Task #7 - Check for closest store -
  System.out.print("Press enter to begin testing task#7...");
  task_7(conn);
  System.out.println();


  //Task #8 - Add/Set Loyalty Level - then update it
  //Task #8 - Update Loyalty Level - then update it
  System.out.print("Press enter to begin testing task#8...");
  inp = cont.nextLine(); System.out.println("");
  task_8(conn);
  System.out.println();

  //Task #9 - Add 2 new customer
  System.out.print("Simulating Task #9...");
  task_9(conn);
  System.out.println();

  //Task #10 - Show Loyalty Points of customer
  System.out.print("Press enter to begin testing task#10...");
  task_10(conn);
  System.out.println();

  //Task #11 - Produce List of loyal customers
  System.out.print("Simulating Task #11...");
  task_11(conn);
  System.out.println();

  //Task #12 - Add purchase for 2 customer - 45 days from today
  System.out.print("Press enter to begin testing task#12...");
  inp = cont.nextLine();
  task_12(conn);
  System.out.println();

  //Task #14 - List coffee with specified coffee name keywords
  System.out.print("Simulating Task #14...");
  task_14(conn);
  System.out.println();

  //Task #15 - List IDS of top stores with revenus for X months
  System.out.print("Simulating Task #15...");
  task_15(conn);
  System.out.println();

  //Task #16 List IDs of top K customer shavnig spent most money
  System.out.print("Simulating Task #16...");
  task_16(conn);
  System.out.println();
  }

// Query Methods for BCDriver //

  //Task #1
  public static void task_1(Connection conn) {
    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Adding a first store----\n");
    String name = "ExampleStore#1";
    String storeType = "sitting";
    String gpsLong = "12345.54321";
    String gpsLat = "98765.56789";

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

    System.out.println("\n----Adding a second store----\n");
    String name2 = "ExampleStore#2";
    String storeType2 = "kiosk";
    String gpsLong2 = "333.666";
    String gpsLat2 = "999.111";

    try {
      // generate unique ID
      Statement st2 = conn.createStatement();
      String query12 = "SELECT MAX(store_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.STORE";
      ResultSet res12 = st2.executeQuery(query12);
      String storeID2 = "";
      while (res12.next()) {
        storeID2 = res12.getString("ID");
        if (storeID2 == null) {
          storeID2 = "0";
        }
      }
      // prepare statement
      PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.STORE VALUES (?, ?, ?, ?, ?)");
      stmt2.setString(1, storeID2);
      stmt2.setString(2, name2);
      stmt2.setString(3, storeType2);
      stmt2.setString(4, gpsLong2);
      stmt2.setString(5, gpsLat2);
      // try adding the store
      conn.setAutoCommit(false);
      st2.executeUpdate(stmt2.toString());
      conn.commit();
      System.out.println("ADDED TO DATABASE SUCCESSFULLY - STORE_ID: " + storeID2);
    } catch (SQLException e1) {
      try {
        System.out.println("ERROR: STORE NOT ADDED TO DATABASE");
        conn.rollback();
      }
      catch(SQLException e2) { System.out.println(e2.toString()); }
    }
  };

  //Task #2
  public static void task_2(Connection conn) {
    //Declaring Task
    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Adding a first Coffee----\n");
    //Processing Queries
    try{
      //Getting Coffee ID
      Statement st = conn.createStatement();
      String searchQuery = "SELECT MAX(coffee_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.COFFEE";
      ResultSet searchRes = st.executeQuery(searchQuery);
      String coffeeID = "";
      while (searchRes.next()){
        coffeeID = searchRes.getString("ID");
        if (coffeeID == null) {
          coffeeID = "0";
        }
      }
      //Create Insert Statement
      PreparedStatement insert = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
      insert.setString(1, coffeeID);
      insert.setString(2, "Caffe Lattee");
      insert.setString(3, "Italian origin made with espresso and steamed milk");
      insert.setString(4, "Canada");
      insert.setString(5, "1");
      insert.setString(6, "2.95");
      insert.setString(7, "29.5");
      insert.setString(8, "295");

      //Attempt to Insert
      conn.setAutoCommit(false);
      st.executeUpdate(insert.toString());
      conn.commit();
      System.out.println("Added to Database Successfully - CoffeID: " + coffeeID);
    }
    catch (SQLException e1){
      try {
        //System.out.println(e1.toString());
        System.out.println("ERROR: COFFEE NOT ADDED TO DATABASE");
        conn.rollback();
      }
      catch(SQLException e2){
        System.out.println(e2.toString());
      }
    }
    System.out.println("\n----Adding a second Coffee----\n");
    //Processing Queries
    try{
      //Getting Coffee ID
      Statement st_2 = conn.createStatement();
      String searchQuery_2 = "SELECT MAX(coffee_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.COFFEE";
      ResultSet searchRes_2 = st_2.executeQuery(searchQuery_2);
      String coffeeID_2 = "";
      while (searchRes_2.next()){
        coffeeID_2 = searchRes_2.getString("ID");
        if (coffeeID_2 == null) {
          coffeeID_2 = "0";
        }
      }
      //Create Insert Statement
      PreparedStatement insert_2 = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
      insert_2.setString(1, coffeeID_2);
      insert_2.setString(2, "Caffe Mocha");
      insert_2.setString(3, "Chocolate-flavoured warm beverage");
      insert_2.setString(4, "Hungary");
      insert_2.setString(5, "6");
      insert_2.setString(6, "3.65");
      insert_2.setString(7, "36.5");
      insert_2.setString(8, "365");

      //Attempt to Insert
      conn.setAutoCommit(false);
      st_2.executeUpdate(insert_2.toString());
      conn.commit();
      System.out.println("Added to Database Successfully - CoffeID: " + coffeeID_2);
    }
    catch (SQLException e1){
      try {
        //System.out.println(e1.toString());
        System.out.println("ERROR: COFFEE NOT ADDED TO DATABASE");
        conn.rollback();
      }
      catch(SQLException e2){
        System.out.println(e2.toString());
      }
    }

    System.out.println("\n----Adding a third Coffee----\n");
    //Processing Queries
    try{
      //Getting Coffee ID
      Statement st_3 = conn.createStatement();
      String searchQuery_3 = "SELECT MAX(coffee_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.COFFEE";
      ResultSet searchRes_3 = st_3.executeQuery(searchQuery_3);
      String coffeeID_3 = "";
      while (searchRes_3.next()){
        coffeeID_3 = searchRes_3.getString("ID");
        if (coffeeID_3 == null) {
          coffeeID_3 = "0";
        }
      }
      //Create Insert Statement
      PreparedStatement insert_3 = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.COFFEE VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
      insert_3.setString(1, coffeeID_3);
      insert_3.setString(2, "White Chocolate Mocha");
      insert_3.setString(3, "Varient on Caffee Mocha w/ white chocolate");
      insert_3.setString(4, "United States");
      insert_3.setString(5, "12");
      insert_3.setString(6, "4.15");
      insert_3.setString(7, "41.5");
      insert_3.setString(8, "415");

      //Attempt to Insert
      conn.setAutoCommit(false);
      st_3.executeUpdate(insert_3.toString());
      conn.commit();
      System.out.println("Added to Database Successfully - CoffeID: " + coffeeID_3);
    }
    catch (SQLException e1){
      try {
        //System.out.println(e1.toString());
        System.out.println("ERROR: COFFEE NOT ADDED TO DATABASE");
        conn.rollback();
      }
      catch(SQLException e2){
        System.out.println(e2.toString());
      }
    }
  };

  //Task #3
  public static void task_3(Connection conn) throws SQLException, ClassNotFoundException {

    Scanner scan = new Scanner(System.in);

    System.out.println("\n----Scheduling a Promotion----\n");
    String promotion_name = "Brian's Winter Promotion";
    String start_date = "12-15-2022";
    String end_date = "01-09-2023";
    String coffee_id = "0";

    Statement st = conn.createStatement();
    String query1 = "SELECT MAX(promotion_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.PROMOTION";
    ResultSet res1 = st.executeQuery(query1);
    String promotion_ID = "";
    if (!res1.isBeforeFirst()) {
      promotion_ID = "0";
    } else {
      while (res1.next()) {
          promotion_ID = res1.getString("ID");
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
        st.executeUpdate(prep_statement.toString());
        st.executeUpdate(prep_statement2.toString());
        conn.commit();
        System.out.println("ADDED TO DATABASE SUCCESSFULLY - PROMOTION_ID: " + promotion_ID);
      }
        catch (SQLException e1) {
          try {
            System.out.println("ERROR: PROMOTION NOT ADDED TO DATABASE");
            conn.rollback();
          }
          catch(SQLException e2) { System.out.println(e2.toString()); }
        }
  };

  //Task #4
  public static void task_4(Connection conn) {
    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Adding a Promotion to a Store----\n");
    // get user input
    String promotionID = "0";
    String storeID = "0";

    try {
      // prepare statement
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
        conn.rollback();
      }
      catch(SQLException e2) { System.out.println(e2.toString()); }
    }
  };

  //Task #5 - X
  public static void task_5(Connection conn) {
    //Presenting Choices
    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Promotional Offers----");
    System.out.println("(1)List All Stores with Promotions");

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
    System.out.println("(2)List All Stores Promoting Specific Coffee");
    choice = "2";
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
  };

  //Task #6
  public static void task_6(Connection conn) throws SQLException, ClassNotFoundException {

      System.out.println("\n----Promotional Offers----\n");
      System.out.println("(1) - List all promotional offers for a store");
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
        System.out.println("(2) - Offers on a specific coffee at a store");
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
  };

  //Task #7
  public static void task_7(Connection conn) {
    Scanner scan = new Scanner(System.in);

    System.out.println("\n----Finding Closest Stores----\n");
    System.out.println("(1) - Find any closest store");
    String choice = "1";

    // any closest store
    if (choice.equals("1")) {
      // get user input
      String gpsLat = "75000.0";
      String gpsLong = "10000.0";

      System.out.println("Checking Lat:75000 and Long:10000");
      // get stores sorted by distance
        try {
            Statement st = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement(" SELECT name, SQRT((gps_lat - ?)^2 + (gps_long - ?)^2) AS distance FROM COFFEE_BOUTIQUE.STORE ORDER BY distance");
            stmt.setString(1, gpsLat);
            stmt.setString(2, gpsLong);
            ResultSet result = st.executeQuery(stmt.toString());
            // print the closest store.
            if (!result.isBeforeFirst()) {
                System.out.println("No stores to list.");
                return;
            }
            result.next();
            System.out.println("Closest Store: " + result.getString("name"));
            // if there is a tie, print all stores that are tied.
            float minDist = result.getFloat("distance");
            float eps = 0.0000001f;
            while(result.next() && Math.abs(result.getFloat("distance") - minDist) < eps) {
                System.out.println("Closest Store: " + result.getString("name"));
            }
        } catch (SQLException e1) {
            while (e1 != null) {
                System.out.println("Message = "+ e1.toString());
                e1 = e1.getNextException();
            }
        }
        // get user input
        String gpsLat2 = "0.0";
        String gpsLong2 = "0.0";

        System.out.println("Checking Lat:0 and Long:0");
        // get stores sorted by distance
          try {
              Statement st2 = conn.createStatement();
              PreparedStatement stmt2 = conn.prepareStatement(" SELECT name, SQRT((gps_lat - ?)^2 + (gps_long - ?)^2) AS distance FROM COFFEE_BOUTIQUE.STORE ORDER BY distance");
              stmt2.setString(1, gpsLat2);
              stmt2.setString(2, gpsLong2);
              ResultSet result2 = st2.executeQuery(stmt2.toString());
              // print the closest store.
              if (!result2.isBeforeFirst()) {
                  System.out.println("No stores to list.");
                  return;
              }
              result2.next();
              System.out.println("Closest Store: " + result2.getString("name"));
              // if there is a tie, print all stores that are tied.
              float minDist2 = result2.getFloat("distance");
              float eps2 = 0.0000001f;
              while(result2.next() && Math.abs(result2.getFloat("distance") - minDist2) < eps2) {
                  System.out.println("Closest Store: " + result2.getString("name"));
              }
          } catch (SQLException e1) {
              while (e1 != null) {
                  System.out.println("Message = "+ e1.toString());
                  e1 = e1.getNextException();
              }
          }
    }
    // closest store with promotion
    else if (choice.equals("2")) {
      // get user input
      System.out.println("Checking with Lat:10000, Long:75000, and promotion ID: 0");
      String gpsLat = "75000.0";
      String gpsLong = "10000.0";
      String promotionID = "0";
        // get stores sorted by distance
        try {
            Statement st = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement(" SELECT COFFEE_BOUTIQUE.STORE.name, SQRT((gps_lat - ?)^2 + (gps_long - ?)^2) AS distance FROM COFFEE_BOUTIQUE.CARRIES NATURAL JOIN COFFEE_BOUTIQUE.STORE WHERE promotion_id = ? ORDER BY distance");
            stmt.setString(1, gpsLat);
            stmt.setString(2, gpsLong);
            stmt.setString(3, promotionID);
            ResultSet result = st.executeQuery(stmt.toString());
            // print the closest store.
            System.out.println("---------------------------------------------");
            if (!result.isBeforeFirst()) {
                System.out.println("No stores to list.");
                return;
            }

            result.next();
            System.out.println("Closest Store: " + result.getString("name"));
            // if there is a tie, print all stores that are tied.
            float minDist = result.getFloat("distance");
            float eps = 0.0000001f;
            while(result.next() && Math.abs(result.getFloat("distance") - minDist) < eps) {
                System.out.println("Closest Store: " + result.getString("name"));
            }
        } catch (SQLException e1) {
            while (e1 != null) {
                System.out.println("Message = "+ e1.toString());
                e1 = e1.getNextException();
            }
        }
    }
    else {
      System.out.println("ERROR: CHOOSE EITHER 1 OR 2");
      return;
    }





    System.out.println("\n----Finding Closest Stores----\n");
    System.out.println("(2) - Find closest store with specific promotion");
    System.out.print("Choice: ");
    choice = "2";

    // any closest store
    if (choice.equals("1")) {
      // get user input
      String gpsLat = "75000.0";
      String gpsLong = "10000.0";

      System.out.println("Checking Lat:75000 and Long:10000");
      // get stores sorted by distance
        try {
            Statement st = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement(" SELECT name, SQRT((gps_lat - ?)^2 + (gps_long - ?)^2) AS distance FROM COFFEE_BOUTIQUE.STORE ORDER BY distance");
            stmt.setString(1, gpsLat);
            stmt.setString(2, gpsLong);
            ResultSet result = st.executeQuery(stmt.toString());
            // print the closest store.
            if (!result.isBeforeFirst()) {
                System.out.println("No stores to list.");
                return;
            }
            result.next();
            System.out.println("Closest Store: " + result.getString("name"));
            // if there is a tie, print all stores that are tied.
            float minDist = result.getFloat("distance");
            float eps = 0.0000001f;
            while(result.next() && Math.abs(result.getFloat("distance") - minDist) < eps) {
                System.out.println("Closest Store: " + result.getString("name"));
            }
        } catch (SQLException e1) {
            while (e1 != null) {
                System.out.println("Message = "+ e1.toString());
                e1 = e1.getNextException();
            }
        }
        // get user input
        String gpsLat2 = "0.0";
        String gpsLong2 = "0.0";

        System.out.println("Checking Lat:0 and Long:0");
        // get stores sorted by distance
          try {
              Statement st2 = conn.createStatement();
              PreparedStatement stmt2 = conn.prepareStatement(" SELECT name, SQRT((gps_lat - ?)^2 + (gps_long - ?)^2) AS distance FROM COFFEE_BOUTIQUE.STORE ORDER BY distance");
              stmt2.setString(1, gpsLat2);
              stmt2.setString(2, gpsLong2);
              ResultSet result2 = st2.executeQuery(stmt2.toString());
              // print the closest store.
              if (!result2.isBeforeFirst()) {
                  System.out.println("No stores to list.");
                  return;
              }
              result2.next();
              System.out.println("Closest Store: " + result2.getString("name"));
              // if there is a tie, print all stores that are tied.
              float minDist2 = result2.getFloat("distance");
              float eps2 = 0.0000001f;
              while(result2.next() && Math.abs(result2.getFloat("distance") - minDist2) < eps2) {
                  System.out.println("Closest Store: " + result2.getString("name"));
              }
          } catch (SQLException e1) {
              while (e1 != null) {
                  System.out.println("Message = "+ e1.toString());
                  e1 = e1.getNextException();
              }
          }
    }
    // closest store with promotion
    else if (choice.equals("2")) {
      // get user input
      System.out.println("Checking with Lat:75000, Long:10000, and promotion ID: 0");
      String gpsLat = "75000.0";
      String gpsLong = "10000.0";
      String promotionID = "0";
        // get stores sorted by distance
        try {
            Statement st = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement(" SELECT COFFEE_BOUTIQUE.STORE.name, SQRT((gps_lat - ?)^2 + (gps_long - ?)^2) AS distance FROM COFFEE_BOUTIQUE.CARRIES NATURAL JOIN COFFEE_BOUTIQUE.STORE WHERE promotion_id = ? ORDER BY distance");
            stmt.setString(1, gpsLat);
            stmt.setString(2, gpsLong);
            stmt.setString(3, promotionID);
            ResultSet result = st.executeQuery(stmt.toString());
            // print the closest store.
            System.out.println("---------------------------------------------");
            if (!result.isBeforeFirst()) {
                System.out.println("No stores to list.");
                return;
            }

            result.next();
            System.out.println("Closest Store: " + result.getString("name"));
            // if there is a tie, print all stores that are tied.
            float minDist = result.getFloat("distance");
            float eps = 0.0000001f;
            while(result.next() && Math.abs(result.getFloat("distance") - minDist) < eps) {
                System.out.println("Closest Store: " + result.getString("name"));
            }
        } catch (SQLException e1) {
            while (e1 != null) {
                System.out.println("Message = "+ e1.toString());
                e1 = e1.getNextException();
            }
        }
    }
    else {
      System.out.println("ERROR: CHOOSE EITHER 1 OR 2");
      return;
    }



  };

  //Task #8
  public static void task_8(Connection conn) {
    //Collecting User Input

    String levelName = "";
    String boostFactor = "";

    try{
      //Getting List of Level Names
      Statement st = conn.createStatement();
      String searchQuery = "SELECT level_ID, level_name FROM COFFEE_BOUTIQUE.LOYALTY_PROGRAM";
      ResultSet searchRes = st.executeQuery(searchQuery);
      //Checking if Given Level Name Exists
      boolean levelExists = false;
      String levelID = "";
      while(searchRes.next()){
        if (levelName.equals(searchRes.getString("level_name"))){
          levelExists = true;
          levelID = searchRes.getString("level_ID");
        }
      }
      if(levelExists){
        System.out.println("----Updating Booster Factor----");

        Statement st2 = conn.createStatement();
        PreparedStatement prep_statement = conn.prepareStatement("UPDATE COFFEE_BOUTIQUE.LOYALTY_PROGRAM SET booster_factor = ? WHERE level_ID = ?");
        prep_statement.setString(1, boostFactor);
        prep_statement.setString(2, levelID);
        try{
          st2.executeUpdate(prep_statement.toString());
        } catch(Exception e1) {
          System.out.println(e1.toString());
        }
      }
      else{
        System.out.println("----Adding Loyalty Level----");

        Statement st2 = conn.createStatement();
        String maxIDQuery = "SELECT MAX(level_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.LOYALTY_PROGRAM";
        ResultSet maxID = st.executeQuery(maxIDQuery);
        levelID = "";
        while(maxID.next()){
          levelID = maxID.getString("ID");
          if (levelID == null) {
            levelID = "0";
          }
        }

        Statement st3 = conn.createStatement();
        PreparedStatement prep_statement = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.LOYALTY_PROGRAM VALUES (?,?,?)");
        prep_statement.setString(1, "0");
        prep_statement.setString(2, "bronze");
        prep_statement.setString(3, "1.5");

        try {
          conn.setAutoCommit(false);
          st3.executeUpdate(prep_statement.toString());
          conn.commit();
          conn.setAutoCommit(true);
        } catch (Exception e1) {
          System.out.println(e1.toString());
        }
      }
    }
    catch (SQLException e1){
      System.out.println(e1.toString());
    }

    System.out.print("Update Loyalty Level: Press Enter");
    Scanner scan = new Scanner(System.in); String input = scan.nextLine();

    levelName = "bronze";
    boostFactor = "";

    try{
      //Getting List of Level Names
      Statement st = conn.createStatement();
      String searchQuery = "SELECT level_ID, level_name FROM COFFEE_BOUTIQUE.LOYALTY_PROGRAM";
      ResultSet searchRes = st.executeQuery(searchQuery);
      //Checking if Given Level Name Exists
      boolean levelExists = false;
      String levelID = "";
      while(searchRes.next()){
        if (levelName.equals(searchRes.getString("level_name"))){
          levelExists = true;
          levelID = searchRes.getString("level_ID");
        }
      }
      if(levelExists){
        System.out.println("----Updating Booster Factor----");

        Statement st2 = conn.createStatement();
        PreparedStatement prep_statement = conn.prepareStatement("UPDATE COFFEE_BOUTIQUE.LOYALTY_PROGRAM SET booster_factor = ? WHERE level_ID = ?");
        prep_statement.setString(1, "1.0");
        prep_statement.setString(2, "0");
        try{
          conn.setAutoCommit(false);
          st2.executeUpdate(prep_statement.toString());
          conn.commit();
        } catch(Exception e1) {
          System.out.println(e1.toString());
        }
      }
      else{
        System.out.println("----Adding Loyalty Level----");

        Statement st2 = conn.createStatement();
        String maxIDQuery = "SELECT MAX(level_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.LOYALTY_PROGRAM";
        ResultSet maxID = st.executeQuery(maxIDQuery);
        levelID = "";
        while(maxID.next()){
          levelID = maxID.getString("ID");
          if (levelID == null) {
            levelID = "0";
          }
        }

        Statement st3 = conn.createStatement();
        PreparedStatement prep_statement = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.LOYALTY_PROGRAM VALUES (?,?,?)");
        prep_statement.setString(1, "0");
        prep_statement.setString(2, "bronze");
        prep_statement.setString(3, "1.5");

        try {
          conn.setAutoCommit(false);
          st3.executeUpdate(prep_statement.toString());
          conn.commit();
          conn.setAutoCommit(true);
        } catch (Exception e1) {
          System.out.println(e1.toString());
        }
      }
    }
    catch (SQLException e1){
      System.out.println(e1.toString());
    }
    System.out.print("Add Diamond Loyalty Level: Press Enter");
    input = scan.nextLine();

    levelName = "diamond";
    boostFactor = "";

    try{
      //Getting List of Level Names
      Statement st = conn.createStatement();
      String searchQuery = "SELECT level_ID, level_name FROM COFFEE_BOUTIQUE.LOYALTY_PROGRAM";
      ResultSet searchRes = st.executeQuery(searchQuery);
      //Checking if Given Level Name Exists
      boolean levelExists = false;
      String levelID = "";
      while(searchRes.next()){
        if (levelName.equals(searchRes.getString("level_name"))){
          levelExists = true;
          levelID = searchRes.getString("level_ID");
        }
      }
      if(levelExists){
        System.out.println("----Updating Booster Factor----");

        Statement st2 = conn.createStatement();
        PreparedStatement prep_statement = conn.prepareStatement("UPDATE COFFEE_BOUTIQUE.LOYALTY_PROGRAM SET booster_factor = ? WHERE level_ID = ?");
        prep_statement.setString(1, "2.0");
        prep_statement.setString(2, "0");
        try{
          conn.setAutoCommit(false);
          st2.executeUpdate(prep_statement.toString());
          conn.commit();
        } catch(Exception e1) {
          System.out.println(e1.toString());
        }
      }
      else{
        System.out.println("----Adding Loyalty Level----");

        Statement st2 = conn.createStatement();
        String maxIDQuery = "SELECT MAX(level_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.LOYALTY_PROGRAM";
        ResultSet maxID = st.executeQuery(maxIDQuery);
        levelID = "";
        while(maxID.next()){
          levelID = maxID.getString("ID");
          if (levelID == null) {
            levelID = "0";
          }
        }

        Statement st3 = conn.createStatement();
        PreparedStatement prep_statement = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.LOYALTY_PROGRAM VALUES (?,?,?)");
        prep_statement.setString(1, "1");
        prep_statement.setString(2, "diamond");
        prep_statement.setString(3, "2");

        try {
          conn.setAutoCommit(false);
          st3.executeUpdate(prep_statement.toString());
          conn.commit();
        } catch (Exception e1) {
          System.out.println(e1.toString());
        }
      }
    }
    catch (SQLException e1){
      System.out.println(e1.toString());
    }

  };

  //Task #9
  public static void task_9(Connection conn) throws SQLException, ClassNotFoundException {

    System.out.println("");
    String first_name = "Panos";
    String last_name = "Chrysanthis";
    String middle_inital = "K";
    String day_of_birth = "1";
    String month_of_birth = "JAN";
    String phone_number = "1234567890";
    String phone_type = "mobile";
    Statement st = conn.createStatement();
    String query1 = "SELECT MAX(customer_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.CUSTOMER";
    int customer_ID = 0;
    ResultSet res1;
    try {
      res1 = st.executeQuery(query1);
    if (!res1.isBeforeFirst()) {
        customer_ID = 0;
      } else {
        while (res1.next()) {
          customer_ID = res1.getInt("ID");
        }
      }
    } catch (SQLException e1) {
    while (e1 != null) {
      System.out.println("Message = "+ e1.toString());
      e1 = e1.getNextException();
      }
    }
    conn.setAutoCommit(true);

    Statement st2 = conn.createStatement();
    PreparedStatement prep_statement = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (?,?,?,?,?,?,?,?,?,?)");
    prep_statement.setString(1, String.valueOf(customer_ID));
    prep_statement.setString(2, first_name);
    prep_statement.setString(3, last_name);
    prep_statement.setString(4, middle_inital);
    prep_statement.setString(5, day_of_birth);
    prep_statement.setString(6, month_of_birth);
    prep_statement.setString(7, phone_number);
    prep_statement.setString(8, phone_type);
    prep_statement.setString(9, "1500");
    prep_statement.setString(10, "1");

    try {
        st2.executeUpdate(prep_statement.toString());
        System.out.println("New Customer ID: " + customer_ID);
      }
        catch (SQLException e1) {
          while (e1 != null) {
            System.out.println(e1.toString());
            e1 = e1.getNextException();
          }
        }

   first_name = "Brain";
   last_name = "Nixon";
   middle_inital = "T";
   day_of_birth = "15";
   month_of_birth = "OCT";
   phone_number = "9876543210";
   phone_type = "work";

   Statement stq = conn.createStatement();
   query1 = "SELECT MAX(customer_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.CUSTOMER";
   customer_ID = 0;
   try {
     res1 = stq.executeQuery(query1);
   if (!res1.isBeforeFirst()) {
       customer_ID = 0;
     } else {
       while (res1.next()) {
         customer_ID = res1.getInt("ID");
       }
     }
   } catch (SQLException e1) {
   while (e1 != null) {
     System.out.println("Message = "+ e1.toString());
     e1 = e1.getNextException();
     }
   }

   st2 = conn.createStatement();
   prep_statement = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (?,?,?,?,?,?,?,?,?,?)");
   prep_statement.setString(1, String.valueOf(customer_ID));
   prep_statement.setString(2, first_name);
   prep_statement.setString(3, last_name);
   prep_statement.setString(4, middle_inital);
   prep_statement.setString(5, day_of_birth);
   prep_statement.setString(6, month_of_birth);
   prep_statement.setString(7, phone_number);
   prep_statement.setString(8, phone_type);
   prep_statement.setString(9, "150");
   prep_statement.setString(10, "0");

   try {
       st2.executeUpdate(prep_statement.toString());
       System.out.println("New Customer ID: " + customer_ID);
     }
       catch (SQLException e1) {
         while (e1 != null) {
           System.out.println(e1.toString());
           e1 = e1.getNextException();
         }
       }
  };

  //Task #10
  public static void task_10(Connection conn) {

    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Displaying Loyalty Points----\n");
    // get user input
    String customerID = "0";
    try {
      // prepare statement
      Statement st = conn.createStatement();
      PreparedStatement stmt = conn.prepareStatement("SELECT points_earned FROM COFFEE_BOUTIQUE.CUSTOMER WHERE customer_ID = ?");
      stmt.setString(1, customerID);
      // get loyalty points
      ResultSet result = st.executeQuery(stmt.toString());
      if (!result.isBeforeFirst()) {
        System.out.println("No customer exists with that ID.");
        return;
      }
      result.next();
      float loyaltyPoints = result.getFloat("points_earned");
      System.out.println(loyaltyPoints + " points");
    } catch (SQLException e1) { System.out.println("ERROR: COULD NOT FETCH LOYALTY POINTS"); }
  };

  //Task #11
  public static void task_11(Connection conn) {
    //Presenting Choices
    System.out.println("\n----Customers Ranked by Loyalty----");

    //Processing Queries
    try{
      //Getting Coffee ID
      Statement st = conn.createStatement();
      String searchQuery = "SELECT first, last, middle_initial FROM COFFEE_BOUTIQUE.CUSTOMER ORDER BY loyalty_level DESC, points_earned DESC";
      ResultSet searchRes = st.executeQuery(searchQuery);
      int rank = 1;
      while(searchRes.next()){
        System.out.println(rank + ". " + searchRes.getString("first") + " " + searchRes.getString("middle_initial") + ". " + searchRes.getString("last"));
        rank++;
      }
    }
    catch (SQLException e1){
        System.out.println(e1.toString());
    }
  };

  //Task #12
  public static void task_12(Connection conn) throws SQLException, ClassNotFoundException {

    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Adding two Purchases for Panos at Store #0----\n");
    String customer = "0";
    String store = "0";
    String timepurchase = "2022-07-15 12:12:12";
    System.out.print("How many different types of coffees: ");
    int numberOfTypes = scan.nextInt(); scan.nextLine();

    if (numberOfTypes < 0) {
      System.out.println("Please enter an amount greater than 0 next time...");
      return;
    }

    //Necessary containers for information
    String arr_coffee_ids[] = new String[numberOfTypes];
    String arr_coffee_purchase[] = new String[numberOfTypes];
    float cost_of_coffee_type[] = new float[numberOfTypes];
    String arr_coffee_redeem[] = new String[numberOfTypes];
    float redeem_cost[] = new float[numberOfTypes];
    String total_quantity_per_coffee[] = new String[numberOfTypes];

    //Get user input
    for (int x = 0; x < numberOfTypes; x++) {
      System.out.print("For Coffee #" + (x + 1) +", enter Coffee ID: ");
      arr_coffee_ids[x] = scan.nextLine();
      System.out.print("For Coffee ID: " + arr_coffee_ids[x] +", how many coffees do you wish to purchase without redeeming points: ");
      arr_coffee_purchase[x] = scan.nextLine();
      System.out.print("For Coffee ID: " + arr_coffee_ids[x] +", how many coffees do you wish to redeem with points: ");
      arr_coffee_redeem[x] = scan.nextLine();
      total_quantity_per_coffee[x] = String.valueOf(Integer.parseInt(arr_coffee_purchase[x]) + Integer.parseInt(arr_coffee_redeem[x]));
    }

    //Ensure correct input
    System.out.println("Order Received\n--------------\nCoffee | Purchase | Redeem | Total\n--------------------------------");
    for (int y = 0; y < numberOfTypes; y++) {
      System.out.println(arr_coffee_ids[y] + "      | " +
      arr_coffee_purchase[y] + "        | " +
      arr_coffee_redeem[y]  + "      | " +
      total_quantity_per_coffee[y]);
    }

    //If incorrect input...
    System.out.print("Is your order correct? (Y/N): ");
    String corr_inp = scan.nextLine();
    if (corr_inp.equalsIgnoreCase("y")) {
    } else {
      System.out.println("Wrong order... Exiting");
      return;
    }

    /* While loop - query for each of those coffees id's respective points */
    ResultSet result;
    Statement st = conn.createStatement();
    float total_points_needed_to_redeem = 0;
    for (int z = 0; z < numberOfTypes; z++) {
      PreparedStatement prep_statement = conn.prepareStatement(" SELECT price, redeem_points FROM COFFEE_BOUTIQUE.COFFEE WHERE coffee_ID = ? ");
      prep_statement.setString(1, arr_coffee_ids[z]);
      try {
          result = st.executeQuery(prep_statement.toString());
          if (!result.isBeforeFirst()) {
            System.out.println("This coffee doesn't exist!");
            return;
          }
          while (result.next()) {
            redeem_cost[z] = result.getFloat("redeem_points");
            cost_of_coffee_type[z] = result.getFloat("price");
            total_points_needed_to_redeem += redeem_cost[z] * Integer.parseInt(arr_coffee_redeem[z]);
            }
        }
          catch (SQLException e1) {
            while (e1 != null) {
              System.out.println("Message = "+ e1.toString());
              e1 = e1.getNextException();
              }
            }
          }

    /*Check to see if the customer has enough points */
    Statement st2 = conn.createStatement();
    ResultSet result2;
    float customer_points = 0;
    PreparedStatement prep_statement2 = conn.prepareStatement("SELECT points_earned FROM COFFEE_BOUTIQUE.CUSTOMER WHERE  customer_ID = ? ");
    prep_statement2.setString(1, customer);
    try {
        result2 = st2.executeQuery(prep_statement2.toString());
        if (!result2.isBeforeFirst()) {
          System.out.println("Can't find this customer!");
          return;
        }
        while (result2.next()) {
          customer_points = result2.getFloat("points_earned");
          }
      }
        catch (SQLException e1) {
          while (e1 != null) {
            System.out.println("Message = "+ e1.toString());
            e1 = e1.getNextException();
            }
          }
    if (customer_points < total_points_needed_to_redeem) {
      System.out.println("This customer doesn't have enough points to redeem the coffees!");
    }

    //Get maximum sale_ID from COFFEE_BOUTIQUE.sale
    Statement st_3 = conn.createStatement();
    String query_3 = "SELECT MAX(sale_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.SALE";
    int sale_ids[] = new int[numberOfTypes];
    sale_ids[0] = 0;
    ResultSet res3;
    try {
      res3 = st.executeQuery(query_3);
    if (!res3.isBeforeFirst()) {
        sale_ids[0] = 0;
      } else {
        while (res3.next()) {
          sale_ids[0] = res3.getInt("ID");
        }
      }
    } catch (SQLException e1) {
    while (e1 != null) {
      System.out.println("Message = "+ e1.toString());
      e1 = e1.getNextException();
      }
    }

    if (numberOfTypes > 1) {
      int ind = 0;
      for (int sales = sale_ids[0]; ind < numberOfTypes; sales++) {
        sale_ids[ind] = sales;
        ind = ind + 1;
      }
    }

    //Everything is ready to executeUpdate
    float redeem_portion = 0;
    float purchase_portion = 0;
    PreparedStatement prep_statement_3;
    PreparedStatement query_arr[] = new PreparedStatement[numberOfTypes];
    for (int trans_num = 0; trans_num < numberOfTypes; trans_num++) {
      prep_statement_3 = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.SALE VALUES ( ?, ?, ?, ?, ?, ?, ?, ?) ");
      prep_statement_3.setString(1, String.valueOf(sale_ids[trans_num]));
      prep_statement_3.setString(2, customer);
      prep_statement_3.setString(3, store);
      prep_statement_3.setString(4, arr_coffee_ids[trans_num]);
      prep_statement_3.setString(5, total_quantity_per_coffee[trans_num]);
      prep_statement_3.setString(6, timepurchase);
      purchase_portion = Integer.parseInt(arr_coffee_purchase[trans_num]) * cost_of_coffee_type[trans_num];
      prep_statement_3.setString(7, String.valueOf(purchase_portion));
      redeem_portion = Integer.parseInt(arr_coffee_redeem[trans_num]) * redeem_cost[trans_num];
      prep_statement_3.setString(8, String.valueOf(redeem_portion));
      query_arr[trans_num] = prep_statement_3;
    }

    //Statements are prepared, prepare to query
    Statement st_sale = conn.createStatement();
    try {
      conn.setAutoCommit(false);
      for (int fin = 0; fin < numberOfTypes; fin++) {
        st_sale.executeUpdate(query_arr[fin].toString());
        }
        conn.commit();
        for (int sub = 0; sub < numberOfTypes; sub++) {
          System.out.println("New Sale Added Successfully with Sale ID: " + sale_ids[sub]);
        }
      }
        catch (SQLException e1) {
          conn.rollback();
          System.out.println("Error: Rollback Initiated");
          while (e1 != null) {
            System.out.println(e1.toString());
            e1 = e1.getNextException();
          }
        }




    System.out.println("\n----Adding Purchase for Brain at Store #1---\n");
    customer = "1";
    store = "1";
    timepurchase = "2022-08-15 12:12:12";
    System.out.print("How many different types of coffees: ");
    numberOfTypes = scan.nextInt(); scan.nextLine();

    if (numberOfTypes < 0) {
      System.out.println("Please enter an amount greater than 0 next time...");
      return;
    }

    //Necessary containers for information
    String arr_coffee_ids_2[] = new String[numberOfTypes];
    String arr_coffee_purchase_2[] = new String[numberOfTypes];
    float cost_of_coffee_type_2[] = new float[numberOfTypes];
    String arr_coffee_redeem_2[] = new String[numberOfTypes];
    float redeem_cost_2[] = new float[numberOfTypes];
    String total_quantity_per_coffee_2[] = new String[numberOfTypes];

    //Get user input
    for (int x = 0; x < numberOfTypes; x++) {
      System.out.print("For Coffee #" + (x + 1) +", enter Coffee ID: ");
      arr_coffee_ids_2[x] = scan.nextLine();
      System.out.print("For Coffee ID: " + arr_coffee_ids_2[x] +", how many coffees do you wish to purchase without redeeming points: ");
      arr_coffee_purchase_2[x] = scan.nextLine();
      System.out.print("For Coffee ID: " + arr_coffee_ids_2[x] +", how many coffees do you wish to redeem with points: ");
      arr_coffee_redeem_2[x] = scan.nextLine();
      total_quantity_per_coffee_2[x] = String.valueOf(Integer.parseInt(arr_coffee_purchase_2[x]) + Integer.parseInt(arr_coffee_redeem_2[x]));
    }

    //Ensure correct input
    System.out.println("Order Received\n--------------\nCoffee | Purchase | Redeem | Total\n--------------------------------");
    for (int y = 0; y < numberOfTypes; y++) {
      System.out.println(arr_coffee_ids_2[y] + "      | " +
      arr_coffee_purchase_2[y] + "        | " +
      arr_coffee_redeem_2[y]  + "      | " +
      total_quantity_per_coffee_2[y]);
    }

    //If incorrect input...
    System.out.print("Is your order correct? (Y/N): ");
    corr_inp = scan.nextLine();
    if (corr_inp.equalsIgnoreCase("y")) {
    } else {
      System.out.println("Wrong order... Exiting");
      return;
    }


    total_points_needed_to_redeem = 0;
    for (int z = 0; z < numberOfTypes; z++) {
      PreparedStatement prep_statement = conn.prepareStatement(" SELECT price, redeem_points FROM COFFEE_BOUTIQUE.COFFEE WHERE coffee_ID = ? ");
      prep_statement.setString(1, arr_coffee_ids_2[z]);
      try {
          result = st.executeQuery(prep_statement.toString());
          if (!result.isBeforeFirst()) {
            System.out.println("This coffee doesn't exist!");
            return;
          }
          while (result.next()) {
            redeem_cost_2[z] = result.getFloat("redeem_points");
            cost_of_coffee_type_2[z] = result.getFloat("price");
            total_points_needed_to_redeem += redeem_cost_2[z] * Integer.parseInt(arr_coffee_redeem_2[z]);
            }
        }
          catch (SQLException e1) {
            while (e1 != null) {
              System.out.println("Message = "+ e1.toString());
              e1 = e1.getNextException();
              }
            }
          }


    Statement st2_2 = conn.createStatement();
    ResultSet result2_2;
    customer_points = 0;
    PreparedStatement prep_statement2_2 = conn.prepareStatement("SELECT points_earned FROM COFFEE_BOUTIQUE.CUSTOMER WHERE  customer_ID = ? ");
    prep_statement2_2.setString(1, customer);
    try {
        result2_2 = st2_2.executeQuery(prep_statement2_2.toString());
        if (!result2_2.isBeforeFirst()) {
          System.out.println("Can't find this customer!");
          return;
        }
        while (result2_2.next()) {
          customer_points = result2_2.getFloat("points_earned");
          }
      }
        catch (SQLException e1) {
          while (e1 != null) {
            System.out.println("Message = "+ e1.toString());
            e1 = e1.getNextException();
            }
          }
    if (customer_points < total_points_needed_to_redeem) {
      System.out.println("This customer doesn't have enough points to redeem the coffees!");
    }

    //Get maximum sale_ID from COFFEE_BOUTIQUE.sale
    String query_3_2 = "SELECT MAX(sale_ID) + 1 AS ID FROM COFFEE_BOUTIQUE.SALE";
    int sale_ids_2[] = new int[numberOfTypes];
    sale_ids_2[0] = 0;
    ResultSet res3_2;
    try {
      res3_2 = st.executeQuery(query_3_2);
    if (!res3_2.isBeforeFirst()) {
        sale_ids_2[0] = 0;
      } else {
        while (res3_2.next()) {
          sale_ids_2[0] = res3_2.getInt("ID");
        }
      }
    } catch (SQLException e1) {
    while (e1 != null) {
      System.out.println("Message = "+ e1.toString());
      e1 = e1.getNextException();
      }
    }

    if (numberOfTypes > 1) {
      int ind = 0;
      for (int sales = sale_ids_2[0]; ind < numberOfTypes; sales++) {
        sale_ids_2[ind] = sales;
        ind = ind + 1;
      }
    }

    //Everything is ready to executeUpdate
    redeem_portion = 0;
    purchase_portion = 0;
    PreparedStatement prep_statement_3_2;
    PreparedStatement query_arr_2[] = new PreparedStatement[numberOfTypes];
    for (int trans_num = 0; trans_num < numberOfTypes; trans_num++) {
      prep_statement_3_2 = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.SALE VALUES ( ?, ?, ?, ?, ?, ?, ?, ?) ");
      prep_statement_3_2.setString(1, String.valueOf(sale_ids_2[trans_num]));
      prep_statement_3_2.setString(2, customer);
      prep_statement_3_2.setString(3, store);
      prep_statement_3_2.setString(4, arr_coffee_ids_2[trans_num]);
      prep_statement_3_2.setString(5, total_quantity_per_coffee_2[trans_num]);
      prep_statement_3_2.setString(6, timepurchase);
      purchase_portion = Integer.parseInt(arr_coffee_purchase_2[trans_num]) * cost_of_coffee_type_2[trans_num];
      prep_statement_3_2.setString(7, String.valueOf(purchase_portion));
      redeem_portion = Integer.parseInt(arr_coffee_redeem_2[trans_num]) * redeem_cost_2[trans_num];
      prep_statement_3_2.setString(8, String.valueOf(redeem_portion));
      query_arr_2[trans_num] = prep_statement_3_2;
    }

    //Statements are prepared, prepare to query
    Statement st_sale_2 = conn.createStatement();
    try {
      conn.setAutoCommit(false);
      for (int fin = 0; fin < numberOfTypes; fin++) {
        st_sale_2.executeUpdate(query_arr_2[fin].toString());
        }
        conn.commit();
        for (int sub = 0; sub < numberOfTypes; sub++) {
          System.out.println("New Sale Added Successfully with Sale ID: " + sale_ids_2[sub]);
        }
      }
        catch (SQLException e1) {
          conn.rollback();
          System.out.println("Error: Rollback Initiated");
          while (e1 != null) {
            System.out.println(e1.toString());
            e1 = e1.getNextException();
          }
        }

  };

  //Task #13
  public static void task_13(Connection conn) throws SQLException, ClassNotFoundException {

    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Displaying full coffee menu & info----\n");
    Statement st = conn.createStatement();
    String query1 = "SELECT * FROM COFFEE_BOUTIQUE.COFFEE";
    ResultSet res1;

    try {
      res1 = st.executeQuery(query1);
      if (!res1.isBeforeFirst()) {
        System.out.println("No items are currently offered by BoutiqueCoffee");
        return;
      }

      String coffee_ID = "";
      String coffee_name = "";
      String description = "";
      String country_of_origin = "";
      String intensity = "";
      String price = "";
      String reward_points = "";
      String redeem_points = "";

      System.out.printf("|%-8s", "Coffee ID"  );
      System.out.printf("|%-25s", "Name" );
      System.out.printf("|%-50s", "Description" );
      System.out.printf("|%-18s", "Country of Origin");
      System.out.printf("|%-10s", "Intensity" );
      System.out.printf("|%-10s", "Price" );
      System.out.printf("|%-10s", "Reward Points");
      System.out.printf("|%-10s", "Redeem Points|");
      System.out.println("\n|---------|-------------------------|--------------------------------------------------|------------------|----------|----------|-------------|-------------|");

      while (res1.next()) {

          coffee_ID = res1.getString("coffee_ID");
          coffee_name = res1.getString("name");
          description = res1.getString("description");
          country_of_origin = res1.getString("country_of_origin");
          intensity = res1.getString("intensity");
          price = res1.getString("price");
          reward_points = res1.getString("reward_points");
          redeem_points = res1.getString("redeem_points");

          System.out.printf("|%-9s", coffee_ID  );
          System.out.printf("|%-25s", coffee_name );
          System.out.printf("|%-50s", description );
          System.out.printf("|%-18s", country_of_origin);
          System.out.printf("|%-10s", intensity );
          System.out.printf("|$%-9.2f", Float.parseFloat(price) );
          System.out.printf("|%-13s", reward_points);
          System.out.printf("|%-10s", redeem_points);
          System.out.println("");

      }
      System.out.println("|---------|-------------------------|--------------------------------------------------|------------------|----------|----------|-------------|-------------|\n");
    } catch(SQLException e1) {
      while (e1 != null) {
        System.out.println(e1.toString());
        e1 = e1.getNextException();
      }
    }

  };

  //Task #14
  public static void task_14(Connection conn) {
    //Presenting Choices
    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Coffee Search w/ keywords: White & Chocolate, Intensity: 12----");
    String intensity = "12";
    String key1 = "white";
    String key2 = "chocolate";

    try{
      Integer.parseInt(intensity);
    }
    catch (Exception notAnInt){
      System.out.println("Not a valid Intensity");
      return;
    }

    try{
      //Getting Coffee ID
      Statement st = conn.createStatement();
      String searchQuery = "SELECT coffee_ID, name FROM COFFEE_BOUTIQUE.COFFEE WHERE intensity = " + intensity;
      ResultSet searchRes = st.executeQuery(searchQuery);
      boolean results = false;
      while(searchRes.next()){
        //Print Results
        String name = searchRes.getString("name");
        if(name.toLowerCase().contains(key1) && name.toLowerCase().contains(key2)){
          results = true;
          System.out.println("\nCoffee: " + name + " ID: "+ searchRes.getString("coffee_ID"));
        }
      }
      if(!results)
        System.out.println("No Coffee satisfies these conditions");
    }
    catch (SQLException e1){
        System.out.println(e1.toString());
    }

  };

  //Task #15
  public static void task_15(Connection conn) {

    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Listing Top Stores (One Month Back)----");
    String k = "2";
    String x = "1";
    System.out.println();

    int places = 0;
    int inDays = 0;

    try{
      places = Integer.parseInt(k);
      inDays = Integer.parseInt(x) * 30;
    }
    catch (Exception notAnInt){
      System.out.println("Not valid Numbers");
      return;
    }

    try{
      //Getting Current Time
      Statement st = conn.createStatement();
      String timeQuery = "SELECT p_date " + "- " + inDays + "AS min_date FROM COFFEE_BOUTIQUE.CLOCK";
      ResultSet timeRes = st.executeQuery(timeQuery);
      timeRes.next();
      String min_date = timeRes.getString("min_date");

      //Getting Results
      Statement st2 = conn.createStatement();
      String searchQuery = "SELECT Store_ID, SUM(purchase_portion) AS Revenue FROM COFFEE_BOUTIQUE.SALE WHERE purchase_time > '" + min_date + "' GROUP BY Store_ID ORDER BY Revenue DESC";
      ResultSet searchRes = st2.executeQuery(searchQuery);

      String prevRev = ""; //the previous revenue value
      int consec = 1; //# of consecutive stores with the same revenue
      for(int i=0; i<places; i++){
        if(!searchRes.next())
          break;
        String storeID = searchRes.getString("Store_ID");
        String revenue = searchRes.getString("Revenue");
        int place = i;
        if (revenue.equals(prevRev)){
          place -= consec;
          consec++;
        }
        else
          consec = 1;
        System.out.print((place+1) + ". Store ID: " + storeID + " Revenue: $"); System.out.printf("%.2f", Float.parseFloat(revenue));
        System.out.println("");
        prevRev = revenue;
      }
      while(searchRes.next()){
        String revenue = searchRes.getString("Revenue");
        String storeID = searchRes.getString("Store_ID");
        if(!revenue.equals(prevRev)){
          break;
        }
        else{
          System.out.print(places + ". Store ID: " + storeID + " Revenue: $"); System.out.printf("%.2f", Float.parseFloat(revenue));
          System.out.println();
        }
      }
    }
    catch (SQLException e1){
        System.out.println(e1.toString());
    }


    System.out.println("\n----Listing Top Stores (3 Months Back)----\n");
    k = "2";
    x = "3";
    System.out.println();

    places = 0;
    inDays = 0;

    try{
      places = Integer.parseInt(k);
      inDays = Integer.parseInt(x) * 30;
    }
    catch (Exception notAnInt){
      System.out.println("Not valid Numbers");
      return;
    }

    try{
      //Getting Current Time
      Statement st = conn.createStatement();
      String timeQuery = "SELECT p_date " + "- " + inDays + "AS min_date FROM COFFEE_BOUTIQUE.CLOCK";
      ResultSet timeRes = st.executeQuery(timeQuery);
      timeRes.next();
      String min_date = timeRes.getString("min_date");

      //Getting Results
      Statement st2 = conn.createStatement();
      String searchQuery = "SELECT Store_ID, SUM(purchase_portion) AS Revenue FROM COFFEE_BOUTIQUE.SALE WHERE purchase_time > '" + min_date + "' GROUP BY Store_ID ORDER BY Revenue DESC";
      ResultSet searchRes = st2.executeQuery(searchQuery);

      String prevRev = ""; //the previous revenue value
      int consec = 1; //# of consecutive stores with the same revenue
      for(int i=0; i<places; i++){
        if(!searchRes.next())
          break;
        String storeID = searchRes.getString("Store_ID");
        String revenue = searchRes.getString("Revenue");
        int place = i;
        if (revenue.equals(prevRev)){
          place -= consec;
          consec++;
        }
        else
          consec = 1;
        System.out.print((place+1) + ". Store ID: " + storeID + " Revenue: $"); System.out.printf("%.2f", Float.parseFloat(revenue)); System.out.println();
        prevRev = revenue;
      }
      while(searchRes.next()){
        String revenue = searchRes.getString("Revenue");
        String storeID = searchRes.getString("Store_ID");
        if(!revenue.equals(prevRev)){
          break;
        }
        else{
          System.out.print(places + ". Store ID: " + storeID + " Revenue: $"); System.out.printf("%.2f",  Float.parseFloat(revenue)); System.out.println();
        }
      }
    }
    catch (SQLException e1){
        System.out.println(e1.toString());
    }

  };

  //Task #16
  public static void task_16(Connection conn) {

    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Listing Top Customers (Two Customers/One Month)----");
    String k = "2";
    String x = "1";
    System.out.println();

    int places = 0;
    int inDays = 0;

    try{
      places = Integer.parseInt(k);
      inDays = Integer.parseInt(x) * 30;
    }
    catch (Exception notAnInt){
      System.out.println("Not valid Numbers");
      return;
    }

    try{
      //Getting Current Time
      Statement st = conn.createStatement();
      String timeQuery = "SELECT p_date " + "- " + inDays + "AS min_date FROM COFFEE_BOUTIQUE.CLOCK";
      ResultSet timeRes = st.executeQuery(timeQuery);
      timeRes.next();
      String min_date = timeRes.getString("min_date");

      //Getting Results
      Statement st2 = conn.createStatement();
      String searchQuery = "SELECT Customer_ID, SUM(purchase_portion) AS Revenue FROM COFFEE_BOUTIQUE.SALE WHERE purchase_time > '" + min_date + "' GROUP BY customer_ID ORDER BY Revenue DESC";
      ResultSet searchRes = st2.executeQuery(searchQuery);

      String prevRev = ""; //the previous revenue value
      int consec = 1; //# of consecutive stores with the same revenue
      for(int i=0; i<places; i++){
        if(!searchRes.next())
          break;
        String custID = searchRes.getString("Customer_ID");
        String revenue = searchRes.getString("Revenue");
        int place = i;
        if (revenue.equals(prevRev)){
          place -= consec;
          consec++;
        }
        else
          consec = 1;
        System.out.print((place+1) + ". Customer ID: " + custID + " Revenue: $"); System.out.printf("%.2f",  Float.parseFloat(revenue)); System.out.println();
        prevRev = revenue;
      }
      while(searchRes.next()){
        String revenue = searchRes.getString("Revenue");
        String custID = searchRes.getString("Customer_ID");
        if(!revenue.equals(prevRev)){
          break;
        }
        else{
          System.out.print(places + ". Customer ID: " + custID + " Revenue: $"); System.out.printf("%.2f", Float.parseFloat(revenue));System.out.println();
        }
      }
    }
    catch (SQLException e1){
        System.out.println(e1.toString());
    }


    System.out.println("\n----Listing Top Customers (Two Customers/Three Months)----");
    k = "2";
    x = "3";
    System.out.println();

    places = 0;
    inDays = 0;

    try{
      places = Integer.parseInt(k);
      inDays = Integer.parseInt(x) * 30;
    }
    catch (Exception notAnInt){
      System.out.println("Not valid Numbers");
      return;
    }

    try{
      //Getting Current Time
      Statement st = conn.createStatement();
      String timeQuery = "SELECT p_date " + "- " + inDays + "AS min_date FROM COFFEE_BOUTIQUE.CLOCK";
      ResultSet timeRes = st.executeQuery(timeQuery);
      timeRes.next();
      String min_date = timeRes.getString("min_date");

      //Getting Results
      Statement st2 = conn.createStatement();
      String searchQuery = "SELECT Customer_ID, SUM(purchase_portion) AS Revenue FROM COFFEE_BOUTIQUE.SALE WHERE purchase_time > '" + min_date + "' GROUP BY customer_ID ORDER BY Revenue DESC";
      ResultSet searchRes = st2.executeQuery(searchQuery);

      String prevRev = ""; //the previous revenue value
      int consec = 1; //# of consecutive stores with the same revenue
      for(int i=0; i<places; i++){
        if(!searchRes.next())
          break;
        String custID = searchRes.getString("Customer_ID");
        String revenue = searchRes.getString("Revenue");
        int place = i;
        if (revenue.equals(prevRev)){
          place -= consec;
          consec++;
        }
        else
          consec = 1;
        System.out.print((place+1) + ". Customer ID: " + custID + " Revenue: $");  System.out.printf("%.2f", Float.parseFloat(revenue));System.out.println();
        prevRev = revenue;
      }
      while(searchRes.next()){
        String revenue = searchRes.getString("Revenue");
        String custID = searchRes.getString("Customer_ID");
        if(!revenue.equals(prevRev)){
          break;
        }
        else{
          System.out.print(places + ". Customer ID: " + custID + " Revenue: $");  System.out.printf("%.2f", Float.parseFloat(revenue));System.out.println();
        }
      }
    }
    catch (SQLException e1){
        System.out.println(e1.toString());
    }

  };
}
