
/*
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
      case 1:  task_1(); break;
      case 2:  task_2(); break;
      case 3:  task_3(); break;
      case 4:  task_4(); break;
      case 5:  task_5(); break;
      case 6:  task_6(); break;
      case 7:  task_7(); break;
      case 8:  task_8(); break;
      case 9:  task_9(); break;
      case 10: task_10(); break;
      case 11: task_11(); break;
      case 12: task_12(); break;
      case 13: task_13(); break;
      case 14: task_14(); break;
      case 15: task_15(); break;
      case 16: task_16(); break;
      }
    }
  } //End Main

  //-----------------------------------
  //Query Methods ---------------------
  //-----------------------------------
  //Task #1
  public static void task_1() {

  };
  //Task #2
  public static void task_2() {

  };
  //Task #3
  public static void task_3() {

  };
  //Task #4
  public static void task_4() {

  };
  //Task #5
  public static void task_5() {

  };
  //Task #6
  public static void task_6() {

  };
  //Task #7
  public static void task_7() {

  };
  //Task #8
  public static void task_8() {

  };
  //Task #9
  public static void task_9() {

  };
  //Task #10
  public static void task_10() {

  };
  //Task #11
  public static void task_11() {

  };
  //Task #12
  public static void task_12() {

  };
  //Task #13
  public static void task_13() {

  };
  //Task #14
  public static void task_14() {

  };
  //Task #15
  public static void task_15() {

  };
  //Task #16
  public static void task_16() {

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
