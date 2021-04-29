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
	
	//test new Method1
	static void openUserpass() {
		testObj.openDB("ex367", "ex367");
	}
	//UserStory1
    	static void callUpdateStudyGroupTime() throws SQLException{
        	int resultInt;
        	System.out.print("Please enter a new meeting time in the format (HH:MM:SS): ");
        	String input = keyboard.nextLine();
        	String meetingTime=(input);
        	System.out.println("Please enter the Course ID: ");
        	String input2 = keyboard.nextLine();
        	String courseID=(input2);
        	System.out.print("Please enter the Study Group ID: ");
        	String input3 = keyboard.nextLine();
        	int studyGroupID= Integer.parseInt(input3); 
        	resultInt = testObj.updateStudyGroupTime(meetingTime, courseID, studyGroupID); 
        	System.out.println("Number of affected tuples " + resultInt);
    	}
	
	//UserStory2
	static void callStudentInterest() throws SQLException 
	{
		ResultSet rs;
		rs = testObj.studentsInterested();
		System.out.printf("CourseName                               CourseID                    #Interested \n");
		while (rs.next()) 
		{
			System.out.printf("%-30s           %-20s             %-20s\n", rs.getString(1), 
					rs.getString(2), rs.getString(3));
		}
	}

	
		
}//MyUtilitiesTest	
