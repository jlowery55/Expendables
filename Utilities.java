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
	/**
	* This method updates a study group's meeting time
	* 
	*@param meetingTime the new meeting time of the group in format (HH:MM:SS)
	*@param courseID string representing the course ID, i.e. CSCI144
	*@param studyGroupID integer representing the study group ID, i.e. 1, 2, 3
	*@return integer representing number of tuples affected
	*
	*/
	public int updateStudyGroupTime(String meetingTime, String courseID, int studyGroupID) {
        int result =0;
        String sql = null;
        try {

            sql = "UPDATE STUDY_GROUP " + 
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
			sql = "SELECT CourseName, INTERESTED_IN.CourseID, Count(*) AS '#Interested' " + 
			      "FROM COURSE, INTERESTED_IN " + 
			      "WHERE COURSE.CourseID = INTERESTED_IN.CourseID " +
			      "GROUP BY COURSE.CourseID";
				
			      dStatement pstmt = conn.prepareStatement(sql);
                              rset = pstmt.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("createStatement " + e.getMessage() + sql);
		}

		return rset;
	}//studentsInterested
	
	
	
	/**
	 * USER STORY 3 (Ashwin, but like it was everyone because this problem was hard):
	 * THIS METHOD WILL LIST COURSES TO SHOW WHICH ONES THE ADMIN IS ABLE TO TUTOR THAT
	 * THEY ARE NOT ALREADY TUTORING. 
	 * @param fname: first name of the the Admin
	 * @param lname: last name of the Admin
	 * @return a list of courses the individual admin can tutor
	 * 
	 * 
	 */
	public ResultSet adminTutor(String fname, String lname)
		{
			
			ResultSet rset = null;
			String sql = null;
			
			try {
				sql = "SELECT A.Fname, A.Lname, CourseName, T.CourseID" + 
						" FROM TUTORABLE as T, COURSE as C, ADMIN as A" + 
						" WHERE T.CourseID = C.CourseID AND A.ID = T.AdminID AND T.AdminID =" + 
						" (SELECT ID" + 
						" FROM ADMIN as A1" +
						" WHERE A1.fname = ? and A1.lname = ?) and T.CourseID NOT IN" + 
						" (SELECT CourseID" + 
						" FROM STUDY_GROUP" + 
						" WHERE AdminID = " +
						" (SELECT ID" + 
						" FROM ADMIN as A1" + 
						" WHERE A1.Fname = ? and A1.Lname = ?))";
				

				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.clearParameters();
				pstmt.setString(1, fname);
				pstmt.setString(2, lname);
				pstmt.setString(3, fname);
				pstmt.setString(4, lname);
				
				rset = pstmt.executeQuery();
			} catch (SQLException e)
			{
				System.out.println("createStatement" + e.getMessage());
				System.out.println("sql" + sql);
			}
			
			return rset;
		}
	
	
	
	
	
	//USER STORY 4:
	/**
	 * This method creates an SQL statement to delete a student from IN_GROUP
	 * wherever the StudentID equals the student's ID and the specified study group.
	 * Written by Vicky Krastev
	 * @param student is the student's ID
	 * @param sGNum is the chosen student group
	 * @return the number of groups that the student left
	 */
	public int leaveStudyGroup(String student, int sGNum) {
		int leaveNum = 0;
		String sql = null;

		try {
			// create a Statement and an SQL string for the statement
			sql = "DELETE FROM IN_GROUP " +
				  "WHERE StudentID = ? AND SG_ID= ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, student);
			pstmt.setInt(2, sGNum);
			leaveNum = pstmt.executeUpdate();
		} catch (SQLException e) {
				System.out.println("createStatement " + e.getMessage());
				System.out.println("sql:" + sql);
		}
		
		return leaveNum;
	}// leaveStudyGroup
	
	
	
	
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
		String sql2 = null;
		
		try {
			sql = "INSERT INTO INTERESTED_IN " +
				  "VALUES (?, ?) ";
			
			sql2 = "SELECT StudentID, CourseID " +
				   "FROM INTERESTED_IN " +
				   "WHERE StudentID = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			
			pstmt.clearParameters();
			pstmt.setInt(1,  StudentID);
			pstmt.setString(2,  CourseID);
			
			pstmt2.clearParameters();
			pstmt2.setInt(1,  StudentID);
			
			pstmt.executeUpdate();
			rset = pstmt2.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("createStatement" + e.getMessage() + sql);
			System.out.println("createStatement2" + e.getMessage() + sql2);
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
	public int joinStudyGroup(int StudentID, int StudyGroupID) {
		ResultSet rset = null;
		String sql = null;
		int num = 0;
		
		try {
			sql = "INSERT INTO IN_GROUP " +
				  "VALUES(?, ?) ";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setInt(1, StudentID);
			pstmt.setInt(2, StudyGroupID);
			
			num = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement" + e.getMessage() + sql);
		}
		
		return num;
	}
	
	
	//END USER STORIES*********************************************************************************************
	
	

	

}// Utilities class
