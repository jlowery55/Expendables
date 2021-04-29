package dbUtil;
/**
 * This program is used to test the Utilities class
 */
 
// You need to import the java.sql package to use JDBC
import java.sql.*; 
 
import java.util.Scanner;

/**
 * @author Dr. Blaha
 * 
 */
public class TestUtilities {

	// Global variables
	static Utilities testObj = new Utilities(); 		// Utilities object for testing
	static Scanner keyboard = new Scanner(System.in); 	// standard input

	public static void main(String[] args) throws SQLException {

		// variables needed for menu
		int choice;
		boolean done = false;

		while (!done) {
			System.out.println();
			displaymenu();
			choice = getChoice();
			switch (choice) {
			case 1: {
				openDefault();
				break;
			}
			case 2: {
				callGetNameSalary();
				break;
			}
			case 3: {
				callMatchName();
				break;
			}
			case 4: {
				callEmployeeByDNO();
				break;
			}
			case 5: {
				openUserpass();
				break;
			}
			case 6: {
				callMethod2();
				break;
			}
			case 7: {
				callMethod3();
				break;
			}
			case 8: {
				callMethod4();
				break;
			}
			case 9: {
				testObj.closeDB(); //close the DB connection 
				break;
			}
			case 10: {
				callMethod5();
				break;
			}
			case 11: {
				callMethod6();
				break;
			}
			case 12: {
				callMethod7();
				break;
			}
			case 13: {
				callMethod8();
				break;
			}
			case 14: {
				callMethod9();
				break;
			}
			case 15: {
				done = true;
				System.out.println("Good bye");
				break;
			}
			
			}// switch
		}

	}// main

	static void displaymenu() {
		System.out.println("1)  open default DB");
		System.out.println("2)  call getNameSalary()");
		System.out.println("3)  call matchLastName(String)");
		System.out.println("4)  call employeeByDNO()");
		System.out.println("5)  open using username and pass (opens using ex367 user and pass)");
		System.out.println("6)  call method 2");
		System.out.println("7)  call method 3");
		System.out.println("8)  call method 4");
		System.out.println("9)  close the DB");
		System.out.println("10) call method 5");
		System.out.println("11) call method 6");
		System.out.println("12) call updateMethod 1");
		System.out.println("13) call updateMethod 2");
		System.out.println("14) call updateMethod 3");
		System.out.println("15) quit");
	}

	static int getChoice() {
		String input;
		int i = 0;
		while (i < 1 || i > 15) {
			try {
				System.out.print("Please enter an integer between 1-15: ");
				input = keyboard.nextLine();
				i = Integer.parseInt(input);
				System.out.println();
			} catch (NumberFormatException e) {
				System.out.println("I SAID AN INTEGER!!!!");
			}
		}
		return i;
	}

	// open the default database;
	static void openDefault() {
		testObj.openDB();
	}

	// test getNameSalary() method
	static void callGetNameSalary() throws SQLException {
		ResultSet rs;
		System.out.println("Research Department Employees");
		System.out.println("*****************************");
		System.out.printf("LastName, FirstName        Salary\n");
		rs = testObj.getNameSalary();
		while (rs.next()) {
			System.out.printf("%-26s %s \n", rs.getString(1) + ", " + rs.getString(2), 
					                         rs.getString(3));
		}
	}

	// test matchName() method
	static void callMatchName() throws SQLException {
		ResultSet rs;
		String target;
		target = "K";
		System.out.println("\nEmployees with name that starts with " + target);
		System.out.println("***************************************************");
		System.out.printf("%-12s  %s\n", "Dept Number",   "LastName, FirstName");
		rs = testObj.matchLastName(target);
		while (rs.next()) {
			System.out.printf("    %-8s    %s\n", rs.getString(1), 
					rs.getString(2) + ", " + rs.getString(3));
		}
	}
	
	 
	//test employeeByDNO() method 
	static void callEmployeeByDNO() throws SQLException {
		ResultSet rs;
		System.out.print("Please enter a department number: ");
		String input = keyboard.nextLine();
		int dno= Integer.parseInt(input); 
		System.out.println("\nEmployees that work in department " + dno); 
		System.out.println("*******************************************");
		System.out.printf("%-12s   %s\n", "Dept Number",   "LastName, FirstName");
		rs = testObj.employeeByDNO(dno); 
		while(rs.next()){ 
			System.out.printf("    %-8s     %s\n", rs.getString(1), 
					rs.getString(2) + ", " + rs.getString(3));
			}
		
	}
	//test new Method1
	static void openUserpass() {
		testObj.openDB("ex367", "ex367");
	}
	//test method2
	static void callMethod2() throws SQLException {
		ResultSet rs;
		System.out.print("Please enter a department number: ");
		String input = keyboard.nextLine();
		int deptNum= Integer.parseInt(input); 
		System.out.println("\nMethod 2 test call for Dept. " + deptNum); 
		System.out.println("*******************************************");
		System.out.printf("%-6s   %s   %s\n", "LastName, FirstName", "ProjectNumber", "Hours");
		rs = testObj.method2(deptNum); 
		while(rs.next()){ 
			System.out.printf("    %-8s     %s\n", rs.getString(1), 
					rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4));
			}
		
	}
	//test method3
		static void callMethod3() throws SQLException {
			ResultSet rs; 
			System.out.println("\nMethod 3 test call "); 
			System.out.println("*******************************************");
			System.out.printf("%s   %s   %s   %s\n", "Project Name", "# of Employees", "Average Hours", "Sum of Hours");
			rs = testObj.method3(); 
			while(rs.next()){ 
				System.out.printf("    %-8s     %s\n", rs.getString(1), 
						rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4));
				}
			
	}
	//test method4
		static void callMethod4() throws SQLException {
			ResultSet rs; 
			System.out.print("Please enter employee's first name: ");
			String input = keyboard.nextLine();
			String empFname= input;
			System.out.println("Please enter employee's last name: ");
			String input2 = keyboard.nextLine();
			String empLname= input2;
			System.out.println("\nMethod 3 test call "); 					
			System.out.println("*******************************************");
			System.out.printf("%s   %s   %s   %s\n", "First Name", "Last Name", "Salary", "Department #");
			rs = testObj.method4(empFname, empLname); 
			while(rs.next()){ 
				System.out.printf("    %-8s     %s\n", rs.getString(1), 
						rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4));
				}
					
	}
	//test method5
		static void callMethod5() throws SQLException {
			ResultSet rs; 
			System.out.println("\nMethod 5 test call "); 
			System.out.println("*******************************************");
			System.out.printf("%-8s %s\n", "Employee", "Salary");
			rs = testObj.method5(); 
			while(rs.next()){ 
				System.out.printf("%-8s  %s\n", rs.getString(1), rs.getString(2));
				}
			
	}
		
	//test method6
		static void callMethod6() throws SQLException {
			int resultInt;
			String[][] data= new String [3][3];
			data[0][0] = "111111100";
			data[0][1] = "10";
			data[0][2] = "20";
			data[1][0] = "111111103";
			data[1][1] = "20";
			data[1][2] = "20";
			data[2][0] = "111111102";
			data[2][1] = "10";
			data[2][2] = "20";
			resultInt = testObj.method6(data); 
			System.out.println("Number of affected tuples " + resultInt);
			
	}
	
	//test method7
		static void callMethod7() throws SQLException{
			int resultInt;
			System.out.print("Please enter the employee ssn: ");
			String input = keyboard.nextLine();
			String essn=(input);
			System.out.print("Please enter the project number: ");
			String input2 = keyboard.nextLine();
			int pno= Integer.parseInt(input2); 
			System.out.print("Please enter the newly worked hours: ");
			String input3 = keyboard.nextLine();
			int hours= Integer.parseInt(input3); 
			resultInt = testObj.method7(essn, pno, hours); 
			System.out.println("Number of affected tuples " + resultInt);
		}
		
		
	//test method8
		static void callMethod8() throws SQLException{
			int resultInt;
			System.out.print("Please enter an employee's ssn");
			String input = keyboard.nextLine();
			String essn=(input);
			System.out.print("Please enter the Project Number: ");
			String input2 = keyboard.nextLine();
			int pno= Integer.parseInt(input2); 
			resultInt = testObj.method8(essn, pno); 
			System.out.println("Number of deleted tuples " + resultInt);
		}

	//test method9
		static void callMethod9() throws SQLException{
			int resultInt;
			System.out.print("Please enter the new department number: ");
			String input = keyboard.nextLine();
			int dnumber= Integer.parseInt(input); 
			System.out.print("Please enter the department location: ");
			String input2 = keyboard.nextLine();
			String dlocation=(input2);
			resultInt = testObj.method9(dnumber, dlocation); 
			System.out.println("Number of inserted tuples " + resultInt);
		}
	
		
}//MyUtilitiesTest	
