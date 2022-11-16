
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


    //RETRIEVE THE MAXIMUM ID FROM THE PROMOTIONS TABLE, +1, THEN ASSIGN THAT TO THE NEW ID
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
        PreparedStatement prep_statement = conn.prepareStatement(" SELECT * FROM COFFEE_BOUTIQUE.PROMOTES  JOIN COFFEE_BOUTIQUE.CARRIES USING(promotion_ID) JOIN COFFEE_BOUTIQUE.PROMOTION USING(promotion_ID) WHERE store_ID = ? and coffee_id = ? ");
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

  };
  //Task #13 - X
  public static void task_13(Connection conn) throws
          SQLException, ClassNotFoundException{

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
