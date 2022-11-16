
/*
cd C:\Users\clark\OneDrive\Desktop\CS1555\BoutiqueCoffee\Phase II
javac -cp "postgresql-42.5.0.jar;." DriverCB.java
java -cp "postgresql-42.5.0.jar;." DriverCB
*/
import java.util.Scanner;
import java.util.Properties;
import java.sql.*;

public class DriverCB {
public static void main(String[] args) throws
        SQLException, ClassNotFoundException {

  //Connection
  Class.forName("org.postgresql.Driver");
  String url = "jdbc:postgresql://localhost:5432/";
  Properties props = new Properties();
  props.setProperty("user", "postgres"); //cpn14
  props.setProperty("password", "BillieBoi"); //BillieBoi#25
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

  };
  //Task #2
  public static void task_2(Connection conn) {

  };
  //Task #3
  public static void task_3(Connection conn) throws
          SQLException, ClassNotFoundException {

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
    while (res1.next()) {
        promotion_ID = res1.getString("ID");
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

  };
  //Task #5
  public static void task_5(Connection conn) {

  };
  //Task #6 - X
  public static void task_6(Connection conn) throws
          SQLException, ClassNotFoundException {

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

  };
  //Task #8
  public static void task_8(Connection conn) {

  };
  //Task #9 - X
  public static void task_9(Connection conn) throws
          SQLException, ClassNotFoundException {

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
            PreparedStatement prep_statement = conn.prepareStatement("INSERT INTO COFFEE_BOUTIQUE.CUSTOMER VALUES (?,?,?,?,?,?,?,?,?)");
            prep_statement.setString(1, String.valueOf(customer_ID));
            prep_statement.setString(2, first_name);
            prep_statement.setString(3, last_name);
            prep_statement.setString(4, middle_inital);
            prep_statement.setString(5, day_of_birth);
            prep_statement.setString(6, month_of_birth);
            prep_statement.setString(7, phone_number);
            prep_statement.setString(8, phone_type);
            prep_statement.setString(9, "0");

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

  };
  //Task #11
  public static void task_11(Connection conn) {

  };
  //Task #12 - X
  public static void task_12(Connection conn) throws
          SQLException, ClassNotFoundException {

    Scanner scan = new Scanner(System.in);
    System.out.println("\n----Adding Purchase(s)----\n");
    System.out.print("Enter Customer ID: ");
    String customer = scan.nextLine();

    System.out.print("Enter Store ID: ");
    String store = scan.nextLine();

    System.out.print("Enter time of purchase (format: HH:SS): ");
    String timepurchase = scan.nextLine();

    System.out.print("How many different types of coffees: ");
    int numberOfTypes = scan.nextInt(); scan.nextLine();

    if (numberOfTypes < 0) {
      System.out.println("Please enter an amount greater than 0 next time...");
      return;
    }

    String arr_coffee_ids[] = new String[numberOfTypes];
    String arr_coffee_purchase[] = new String[numberOfTypes];
    String arr_coffee_redeem[] = new String[numberOfTypes];
    String total_amount_per_coffee[] = new String[numberOfTypes];
    int x = 0;
    while (x < numberOfTypes) {
      System.out.print("For Coffee #" + (x + 1) +", enter Coffee ID: ");
      arr_coffee_ids[x] = scan.nextLine();
      System.out.print("For Coffee ID " + arr_coffee_ids[x] +", enter number you wish to purchase without redeeming points: ");
      arr_coffee_purchase[x] = scan.nextLine();
      System.out.print("For Coffee ID " + arr_coffee_ids[x] +", enter number you wish to redeem for free: ");
      arr_coffee_redeem[x] = scan.nextLine();
      total_amount_per_coffee[x] = String.valueOf(Integer.parseInt(arr_coffee_purchase[x]) + Integer.parseInt(arr_coffee_redeem[x]));
      x = x + 1;
    }

    System.out.println("Order Received\n--------------\nCoffee | Purchase | Redeem | Total\n--------------------------------");
    for (int y = 0; y < numberOfTypes; y++) {
      System.out.println(arr_coffee_ids[y] + "      | " + arr_coffee_purchase[y] + "        | " + arr_coffee_redeem[y]  + "      | " + total_amount_per_coffee[y]);
    }

    /* While loop - query for each of those coffees id's respective points */
    ResultSet result;
    float redeem = 0;
    Statement st = conn.createStatement();
    float total_points_needed_to_redeem = 0;
    for (int z = 0; z < numberOfTypes; z++) {
      PreparedStatement prep_statement = conn.prepareStatement(" SELECT redeem_points FROM COFFEE_BOUTIQUE.COFFEE WHERE coffee_ID = ? ");
      prep_statement.setString(1, arr_coffee_ids[z]);
      try {
          result = st.executeQuery(prep_statement.toString());
          if (!result.isBeforeFirst()) {
            System.out.println("This coffee doesn't exist!");
            return;
          }
          while (result.next()) {
            redeem = result.getFloat("redeem_points");
            total_points_needed_to_redeem += redeem * Integer.parseInt(arr_coffee_redeem[z]);
            }
        }
          catch (SQLException e1) {
            while (e1 != null) {
              System.out.println("Message = "+ e1.toString());
              e1 = e1.getNextException();
              }
            }
          }
    System.out.println("Total Points Needed: " + total_points_needed_to_redeem);

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
    System.out.println("Customer has: " + customer_points);

    /*At this point, we know the customer has the correct amount of points to redeem the coffees (remember to get the diff)

    number of sales will be equal to the number of coffee types. quantity will be equal to purchase + redeem. purchase portion will come
    from the total amount of the number of coffess wanted by customer by type. */



  }
  //Task #13 - X
  public static void task_13(Connection conn) throws
          SQLException, ClassNotFoundException {

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
  //-----------------------------------
  //-----------------------------------
  //-----------------------------------

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
