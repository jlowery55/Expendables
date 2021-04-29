package dbUtil;
/**
 * This class provides some basic methods for accessing a MariaDB database.
 * It uses Java JDBC and the MariaDB JDBC driver, mariadb-java-client-2.5.4.jar
 * to open and modify the DB. 
 */

//import the java.sql package to use JDBC methods and classes
import java.sql.*;

/**
 * @author Dr. Blaha
 * 
 */
public class Utilities {

	// Connection object
	private Connection conn = null; 
	 
	/**
	 * @return the conn
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * Open a MariaDB DB connection where user name and password are predefined
	 * (hardwired) within the url.
	 */
	public void openDB() {
		// Connect to the database
		//replace <DB> with your team database name (e.g., kb367_2021)
		//replace <UserName> with your team user name (e.g., kb367)
		//replace <passwd> with your team password (e.g., mom)		
		String url = "jdbc:mariadb://mal.cs.plu.edu:3306/ex367_2021?user=ex367&password=ex367";
		
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println("using url:"+url);
			System.out.println("problem connecting to MariaDB: "+ e.getMessage());			
			//e.printStackTrace();
		}

	}// openDB

	/**
	 * Close the connection to the DB
	 */
	public void closeDB() {
		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			System.err.println("Failed to close database connection: " + e);
		}
	}// closeDB
	
	//START USER STORY METHOD HERE*****************************************************************************************************

	//USER STORY 1: (Joey)
	public int userStory1(String meetingTime, String courseID, int studyGroupID) {
        int result =0;
        String sql = null;
        try {

            sql = "UPDATE STUDY_GROUP1 " + 
                  "SET MeetingTimes = ? " + 
                  "WHERE CourseID = ? AND StudyGroupID=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.clearParameters();
            pstmt.setString(1, meetingTime);
            pstmt.setString(2, courseID);
            pstmt.setInt(3, studyGroupID);

            result= pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("createStatement " + e.getMessage() + sql);
   	 }
        	return result;
   	 }
	
	//USER STORY 2: (Alex)
	/**
	*   This method returns a list of courses, the course ID and the students who want to 
	*   join a study group for that class.
	*   
	*   @return Result set with course name, course ID and interested students
	*/
	public ResultSet studentsInterested() 
	{
		ResultSet rset = null;
		String sql = null;

		try {
			//create a SQL string for the statement
			sql = "SELECT CourseName, INTERESTED_IN1.CourseID, Count(*) AS '#Interested' " + 
			      "FROM COURSE1, INTERESTED_IN1 " + 
			      "WHERE COURSE1.CourseID = INTERESTED_IN1.CourseID " +
			      "GROUP BY COURSE1.CourseID";
				
			      dStatement pstmt = conn.prepareStatement(sql);
                              rset = pstmt.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("createStatement " + e.getMessage() + sql);
		}

		return rset;
	}//studentsInterested
	
	
	
	
	//USER STORY 3:
	
	
	
	
	
	//USER STORY 4:
	
	
	
	
	
	//USER STORY 5: (Travis)
	/**
	 * This method will add a Student to INTERESTED_IN who is interested in participating in a Study Group for a Course
	 * 
	 * @param StudentID is the ID of the Student
	 * @param CourseID is the ID of the Course
	 * @return ResultSet that has Student ID and Course ID (for the course the student is interested in)
	 */
	public ResultSet showInterest(int StudentID, String CourseID) {
		ResultSet rset = null;
		String sql = null;
		
		try {
			sql = "INSERT INTO INTERESTED_IN1" +
			      "VALUES (?, ?)" +
			      "SELECT StudentID, CourseID" +
			      "FROM INTERESTED_IN1" +
			      "WHERE StudentID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setInt(1, StudentID);
			pstmt.setString(2, CourseID);
			pstmt.setInt(3, StudentID);
			
			rset = pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("createStatement" + e.getMessage() + sql);
		}
		
		return rset;
	}
	
	
	
	
	//USER STORY 6: (Travis)
	/**
	 * This method will add a Student to a Study Group based on the Course they've selected
	 * 
	 * @param StudentID is the ID of the Student
	 * @param StudyGroupID is the ID of the Study Group
	 */
	public void joinStudyGroup(int StudentID, int StudyGroupID) {
		ResultSet rset = null;
		String sql = null;
		
		try {
			sql = "INSERT INTO IN_GROUP1" +
			      "VALUES(?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setInt(1, StudentID);
			pstmt.setInt(2, StudyGroupID);
			
			rset = pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("createStatement" + e.getMessage() + sql);
		}
	}
	
	
	//END USER STORIES*********************************************************************************************
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * This method creates an SQL statement to list fname, lname, salary of all
	 * employees that work in the department with dname='Research'
	 * 
	 * @return ResultSet that contains three columns lname, fname, salary of all
	 *         employees that work in the research department
	 */
	public ResultSet getNameSalary() {
		ResultSet rset = null;
		String sql = null;

		try {
			// create a Statement and an SQL string for the statement
			Statement stmt = conn.createStatement();
			sql = "SELECT lname, fname, salary " +
			      "FROM employee, department " + 
				  "WHERE dno=dnumber and dname='Research' " + 
			      "ORDER BY lname, fname";
			rset = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("createStatement " + e.getMessage() + sql);
		}

		return rset;
	}// getNameSalary

	/**
	 * This method creates an SQL statement to list fname, lname, and department
	 * number of all employees that have a last name that starts with the String
	 * target - 
	 * THIS IS AN EXAMPLE OF WHAT NOT TO DO!!!!
	 * THIS SHOULD REALLY BE DONE WITH A PREPARED STATMENT
	 * 
	 * @param target the string used to match beginning of employee's last name
	 * @return ResultSet that contains lname, fname, and department number of
	 *         all employees that have a first name that starts with target.
	 */
	public ResultSet matchLastName(String target) {
		ResultSet rset = null;
		String sql = null;

		try {
			// create a Statement and an SQL string for the statement
			Statement stmt = conn.createStatement();
			sql = "SELECT dno, lname, fname " + 
			      "FROM employee " + 
				  "WHERE lname like '" + target + "%' " + 
			      "ORDER BY dno";
			rset = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("createStatement " + e.getMessage() + sql);
		}

		return rset;

	}// matchLastName

	/**
	 * This method creates an SQL statement to list fname, lname, and department
	 * number of all employees that work in the department with number dno
	 * 
	 * @param dno the department number
	 * @return ResultSet that contains lname, fname, and department number of
	 *         all employees that work in the department number dno
	 */
	// EXAMPLE OF USING A PreparedStatement AND SETTING Parameters
	public ResultSet employeeByDNO(int dno) {
		ResultSet rset = null;
		String sql = null;

		try {
			// create a Statement and an SQL string for the statement
			sql = "SELECT dno, lname, fname FROM employee " + 
			      "WHERE dno = ? " + 
				  "ORDER BY dno";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.clearParameters();
			pstmt.setInt(1, dno); // set the 1 parameter

			rset = pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("createStatement " + e.getMessage() + sql);
		}

		return rset;
	}// employeeByDNO

	

}// Utilities class
