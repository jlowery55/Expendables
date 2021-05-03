package dbUtil;
/**
 * This program is used to test the Utilities class
 */
 
// You need to import the java.sql package to use JDBC
import java.sql.*; 
 
import java.util.Scanner;

/**
 * @author Expendables
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
				openUserpass();
				break;
			}
			case 2: {
				callUpdateStudyGroupTime();
				break;
			}
			case 3: {
				callStudentInterest();
				break;
			}
			case 4: {
				callAdminTutor();
				break;
			}
			case 5: {
				callLeaveStudyGroup();
				break;
			}
			case 6: {
				callShowInterest();
				break;
			}
			case 7: {
				callJoinStudyGroup();
				break;
			}
			case 8: {
				done = true;
				System.out.println("Good bye");
				break;
			}
			
			}// switch
		}

	}// main

	static void displaymenu() {
		System.out.println("1)  open default DB");
		System.out.println("2)  call updateStudyGroupTime");
		System.out.println("3)  call studentInterest");
		System.out.println("4)  call adminTutor");
		System.out.println("5)  call leaveStudyGroup");
		System.out.println("6)  call showInterest");
		System.out.println("7)  call joinStudyGroup");
		System.out.println("8) quit");
	}

	static int getChoice() {
		String input;
		int i = 0;
		while (i < 1 || i > 8) {
			try {
				System.out.print("Please enter an integer between 1-8: ");
				input = keyboard.nextLine();
				i = Integer.parseInt(input);
				System.out.println();
			} catch (NumberFormatException e) {
				System.out.println("I SAID AN INTEGER!!!!");
			}
		}
		return i;
	}
	
	//open DB
	static void openUserpass() {
		testObj.openDB("ex367", "ex367");
	}
	//UserStory1
    	static void callUpdateStudyGroupTime() throws SQLException{
        	int resultInt;
        	System.out.print("Please enter a new meeting time in the format (HH:MM:SS): ");
        	String input = keyboard.nextLine();
        	String meetingTime=(input);
        	System.out.print("Please enter the Course ID: ");
        	String input2 = keyboard.nextLine();
        	String courseID=(input2);
        	System.out.print("Please enter the Study Group ID: ");
        	String input3 = keyboard.nextLine();
        	int studyGroupID= Integer.parseInt(input3); 
        	resultInt = testObj.updateStudyGroupTime(meetingTime, courseID, studyGroupID); 
        	System.out.println("Number of updated study group times " + resultInt);
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

	
	//UserStory3
	//test adminTutor() method
	static void callAdminTutor() throws SQLException{
		ResultSet rs;
        System.out.print("Please enter a fname: ");
        String input = keyboard.nextLine();
        System.out.print("Please enter a lname: ");
        String input1 = keyboard.nextLine();
        System.out.println("Admin can tutor");
        System.out.println("fname        lname        CourseName                    CourseID");
        rs = testObj.adminTutor(input, input1);
        while(rs.next())
        {
            System.out.printf("%-8s     %s    %-30s  %s\n", rs.getString(1), 
                    rs.getString(2), rs.getString(3), rs.getString(4));
        }
	}
	
	//UserStory4
	//test leaveStudyGroup() method
	static void callLeaveStudyGroup() throws SQLException {
		int numLeft = 0;
		System.out.print("Please enter your Student ID: ");
		String studentID = keyboard.nextLine();
		System.out.print("Please enter the Study Group you wish to leave: ");
		String studygroupID = keyboard.nextLine();
		int studygroupID2 = Integer.parseInt(studygroupID);
		System.out.println();
		numLeft = testObj.leaveStudyGroup(studentID, studygroupID2);
		System.out.println("Number of students leaving study group " +studygroupID2 +": " + numLeft);

		
	}
	
	//UserStory5
	 // test showInterest() method
    static void callShowInterest() throws SQLException {
        int num;
        System.out.print("Please enter your Student ID: ");
        String studentID = keyboard.nextLine();

        System.out.print("Please enter the CourseID: ");
        String courseID = keyboard.nextLine();

        System.out.print("\nStudent interested in joining a study group for " + courseID +"\n");

        num = testObj.showInterest(studentID, courseID);
        System.out.print("Number of students newly showing interest: " + num);
    }
	
	//UserStory6
 // test joinStudyGroup() method
    static void callJoinStudyGroup() throws SQLException {
        int num = 0;
        System.out.print("Please enter your Student ID: ");
        String studentID = keyboard.nextLine();

        System.out.print("Please enter the StudyGroup ID: ");
        String sgID = keyboard.nextLine();
        int sgID2=Integer.parseInt(sgID);

        System.out.print("\nStudent joining study group " + sgID + "\n");

        num = testObj.joinStudyGroup(studentID, sgID2);
        System.out.println("Number of students that joined a study group: " + num );
        }
	
	
}//MyUtilitiesTest	
