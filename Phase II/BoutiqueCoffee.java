
/*
Quick Copy/Paster in Terminal for Team Carpe Data
cd C:\Users\clark\OneDrive\Desktop\CS1555\BoutiqueCoffee\Phase II
javac -cp "postgresql-42.5.0.jar;." BoutiqueCoffee.java
java -cp "postgresql-42.5.0.jar;." BoutiqueCoffee

---Notes to grader/tester---

Program Operation -
-At a minimum, loyalty levels should first be inserted.
-Full Program Functionality when sample-data postgresql file run.
-Feel free to run application with username/password below.


Run schema.sql, triggers.sql, then sample-data.sql for testing

*/
import java.util.Scanner;
import java.util.Properties;
import java.sql.*;

public class BoutiqueCoffee {
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
  System.out.println("\n\n----Beginning Coffee Boutique Program----\n\n");

  //Begin Driver Program
  Scanner scan = new Scanner(System.in);
  int task_selected = -1;
  while(task_selected != 0) {
    task_selected = input_task_selection(scan);
    input_check(task_selected);
    switch(task_selected) {
      case 1:  task_1(conn); break;
      case 2:  task_2(conn); break;
      case 3:  task_3(conn); break;
      case 4:  task_4(conn); break;
      case 5:  task_5(conn); break;
      case 6:  task_6(conn); break;
      case 7:  task_7(conn); break;
      case 8:  task_8(conn); break;
      case 9:  task_9(conn); break;
      case 10: task_10(conn); break;
      case 11: task_11(conn); break;
      case 12: task_12(conn); break;
      case 13: task_13(conn); break;
      case 14: task_14(conn); break;
      case 15: task_15(conn); break;
      case 16: task_16(conn); break;
    }
  }
} //End Main

  //-----------------------------------
  //Query Methods ---------------------
  //-----------------------------------
  //Task #1
  public static void task_1(Connection conn) {
    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Adding a Store----\n");
    // get user input
    System.out.print("Name: ");
    String name = scan.nextLine();
    System.out.print("StoreType: ");
    String storeType = scan.nextLine();
    System.out.print("gpsLong: ");
    String gpsLong = scan.nextLine();
    System.out.print("gpsLat: ");
    String gpsLat = scan.nextLine();
    System.out.println("---------------------------------------------");
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
  };

  //Task #2
  /*To maintain consistency with our schema setup, the reward points must be 10x
  the price of a coffee and the redeem_points must be 100x the price of a coffee
  This constraint is checked by a trigger function within the schema trigger file*/
  public static void task_2(Connection conn) {
    //Declaring Task
    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Adding a Coffee----");

    //Gets user inputs
    System.out.print("Name: ");
    String name = scan.nextLine();
    System.out.print("Description: ");
    String description = scan.nextLine();
    System.out.print("Country of Origin: ");
    String country = scan.nextLine();
    System.out.print("Intensity: ");
    String intensity = scan.nextLine();
    System.out.print("Price: ");
    String price = scan.nextLine();
    System.out.print("Award Points: ");
    String rewardPoints = scan.nextLine();
    System.out.print("Redeem Points: ");
    String redeemPoints = scan.nextLine();
    System.out.println("---------------------------------------------");

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

  };

  //Task #3
  public static void task_3(Connection conn) throws SQLException, ClassNotFoundException {

    Scanner scan = new Scanner(System.in);

    System.out.println("\n----Scheduling a Promotion----\n");
    System.out.print("New Promotion Name: ");
    String promotion_name = scan.nextLine();
    System.out.print("Promotion Start Date - Format(MM-DD-YYYY): ");
    String start_date = scan.nextLine();
    System.out.print("Promotion End Date - Format(MM-DD-YYYY): ");
    String end_date = scan.nextLine();
    System.out.print("Coffee ID: ");
    String coffee_id = scan.nextLine();

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
    System.out.println("---------------------------------------------");
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
    System.out.print("Promotion ID: ");
    String promotionID = scan.nextLine();
    System.out.print("Store ID: ");
    String storeID = scan.nextLine();
    System.out.println("---------------------------------------------");
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

  //Task #5
  public static void task_5(Connection conn) {
    //Presenting Choices
    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Promotional Offers----");
    System.out.println("(1)List All Stores with Promotions");
    System.out.println("(2)List All Stores Promoting Specific Coffee");
    System.out.print("Choice: ");

    String choice = scan.nextLine();

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
        System.out.print("Coffee ID: ");
        String coffeeID = scan.nextLine();

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
      System.out.println("(2) - Offers on a specific coffee at a store");
      System.out.print("Choice: ");

      String store = "";
      String coffee = "";
      String rpromotion_ID = "";
      String rstore_ID = "";
      String rcoffee = "";
      String rpromotion_name = "";
      ResultSet res1;

      Scanner scan = new Scanner(System.in);
      String choice = scan.nextLine();

      if (choice.equals("1")) {
        System.out.print("Enter Store ID: ");
        store = scan.nextLine();
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

        System.out.print("Enter Store ID: ");
        store = scan.nextLine();
        System.out.print("Enter Coffee ID: ");
        coffee = scan.nextLine();
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
    System.out.println("(2) - Find closest store with specific promotion");
    System.out.print("Choice: ");
    String choice = scan.nextLine();

    // any closest store
    if (choice.equals("1")) {
      // get user input
      System.out.print("Enter GPS latitude: ");
      String gpsLat = scan.nextLine();
      System.out.print("Enter GPS longitude: ");
      String gpsLong = scan.nextLine();
      // get stores sorted by distance
        try {
            Statement st = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement(" SELECT name, SQRT((gps_lat - ?)^2 + (gps_long - ?)^2) AS distance FROM COFFEE_BOUTIQUE.STORE ORDER BY distance");
            stmt.setString(1, gpsLat);
            stmt.setString(2, gpsLong);
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
    // closest store with promotion
    else if (choice.equals("2")) {
      // get user input
      System.out.print("Enter GPS latitude: ");
      String gpsLat = scan.nextLine();
      System.out.print("Enter GPS longitude: ");
      String gpsLong = scan.nextLine();
      System.out.print("Enter promotion ID: ");
      String promotionID = scan.nextLine();
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
    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Adding/Changing Loyalty Level----");
    System.out.print("Loyalty Level Name: ");
    String levelName = scan.nextLine();
    System.out.print("Booster Factor: ");
    String boostFactor = scan.nextLine();

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
        st2.executeUpdate(prep_statement.toString());
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
        prep_statement.setString(1, levelID);
        prep_statement.setString(2, levelName);
        prep_statement.setString(3, boostFactor);
        st3.executeUpdate(prep_statement.toString());
      }
    }
    catch (SQLException e1){
      System.out.println(e1.toString());
    }
  };

  //Task #9
  public static void task_9(Connection conn) throws SQLException, ClassNotFoundException {
    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Add a new Customer----\n");
    System.out.print("First Name of Customer: ");
    String first_name = scan.nextLine();
    System.out.print("Last Name of Customer: ");
    String last_name = scan.nextLine();
    System.out.print("Middle Initial: ");
    String middle_inital = scan.nextLine();
    System.out.print("Day of Birth(Numerical Format - DD): ");
    String day_of_birth = scan.nextLine();
    System.out.print("-----------------------------------------------\nJAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DEC\nMonth of Birth(Options Above): ");
    String month_of_birth = scan.nextLine();
    System.out.print("Phone Number: ");
    String phone_number = scan.nextLine();
    System.out.print("--------------------\nmobile work phone other\nPhone Type(Options Above): ");
    String phone_type = scan.nextLine();
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
    prep_statement.setString(9, "0");
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
    System.out.print("Enter Customer ID: ");
    String customerID = scan.nextLine();
    System.out.println("---------------------------------------------");
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
    System.out.println("\n----Adding Purchase(s)----\n");
    System.out.print("Enter Customer ID: ");
    String customer = scan.nextLine();
    System.out.print("Enter Store ID: ");
    String store = scan.nextLine();
    System.out.print("Enter time of purchase (format: YYYY-MM-DD HH:MM:SS): ");
    String timepurchase = scan.nextLine();
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
      System.out.print("For Coffee ID: " + arr_coffee_ids[x] +", how many coffees do you \nwish to purchase without redeeming points: ");
      arr_coffee_purchase[x] = scan.nextLine();
      System.out.print("For Coffee ID: " + arr_coffee_ids[x] +", how many coffees do you \nwish to redeem with points: ");
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
  }

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
    System.out.println("\n----Coffee Search----");
    System.out.print("Coffee Intensity: ");
    String intensity = scan.nextLine();
    System.out.print("\nKeyword 1: ");
    String key1 = scan.nextLine().toLowerCase();
    System.out.print("\nKeyword 2: ");
    String key2 = scan.nextLine().toLowerCase();

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
    System.out.println("\n----Listing Top Stores----\n");
    System.out.print("Number of Stores to Display: ");
    String k = scan.nextLine();
    System.out.print("\nNumber of Months back: ");
    String x = scan.nextLine();
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
        System.out.print((place+1) + ". Store ID: " + storeID + " Revenue: $");
        System.out.printf("%.2f", Float.parseFloat(revenue));System.out.println();
        prevRev = revenue;
      }
      while(searchRes.next()){
        String revenue = searchRes.getString("Revenue");
        String storeID = searchRes.getString("Store_ID");
        if(!revenue.equals(prevRev)){
          break;
        }
        else{
          System.out.print(places + ". Store ID: " + storeID + " Revenue: $");
          System.out.printf("%.2f", Float.parseFloat(revenue));System.out.println();
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
    System.out.println("\n----Listing Top Customers----\n");
    System.out.print("Number of Customers to Display: ");
    String k = scan.nextLine();
    System.out.print("\nNumber of Months back: ");
    String x = scan.nextLine();
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
        System.out.print((place+1) + ". Customer ID: " + custID + " Revenue: $");
         System.out.printf("%.2f", Float.parseFloat(revenue));System.out.println();
        prevRev = revenue;
      }
      while(searchRes.next()){
        String revenue = searchRes.getString("Revenue");
        String custID = searchRes.getString("Customer_ID");
        if(!revenue.equals(prevRev)){
          break;
        }
        else{
          System.out.print(places + ". Customer ID: " + custID + " Revenue: $");
           System.out.printf("%.2f", Float.parseFloat(revenue));System.out.println();
        }
      }
    }
    catch (SQLException e1){
        System.out.println(e1.toString());
    }

  };

  // Helper Methods
  public static void input_check(int input) {
    if (input > 0 && input <= 16) {
        System.out.println("\n---Executing Task #" + input +"---");
    } else if (input == 0) {
        System.out.println("Exiting Program");
        } else {
        System.out.println("Not an option. Please try again.");
      }
  }
  public static int input_task_selection(Scanner scan) {
    System.out.println("---------------------------------------------");
    System.out.println("Select number of task you wish to perform: ");
    System.out.println("(0) - Exit Program");
    System.out.println("(1) - Add new store");
    System.out.println("(2) - Add new coffee");
    System.out.println("(3) - Add new promotion");
    System.out.println("(4) - Add promotion to store");
    System.out.println("(5) - List all stores with promotions");
    System.out.println("(6) - Check if given store has promotion");
    System.out.println("(7) - Find closest store");
    System.out.println("(8) - Add/Set loyalty level");
    System.out.println("(9) - Add new customer");
    System.out.println("(10) - Show customer loyalty points");
    System.out.println("(11) - Most loyal customers");
    System.out.println("(12) - Add a purchase");
    System.out.println("(13) - Display all coffee options");
    System.out.println("(14) - Coffees with specified intensity");
    System.out.println("(15) - Highest revenue stores");
    System.out.println("(16) - Highest spending customers");
    System.out.println("---------------------------------------------");
    System.out.print("Peform Task: ");
    int task = scan.nextInt();
    return task;
  }
}
