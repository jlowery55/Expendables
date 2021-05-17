package dbUtil;
/**
 * This class provides some basic methods for accessing a MariaDB database.
 * It uses Java JDBC and the MariaDB JDBC driver, mariadb-java-client-2.5.4.jar
 * to open and modify the DB. 
 */

//import the java.sql package to use JDBC methods and classes
import java.sql.*;

/**
 * @author Expendables
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
	
	public void openDB(String username, String password) {		
		String url = "jdbc:mariadb://mal.cs.plu.edu:3306/ex367_2021?user="+ username +"&password="+password;
			
			try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println("using url:"+url);
			System.out.println("problem connecting to MariaDB: "+ e.getMessage());			
			//e.printStackTrace();
		}
	}
	
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

	//USER STORY 1:
	/**
	* This method updates a study group's meeting time
	* Written by Joey Lowery
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
            System.out.println("Problem with updateStudyGroupTime " + e.getMessage() + sql);
   	 }
        	return result;
   	 }
	
	//USER STORY 2:
	/**
	*   This method returns a list of courses, the course ID and the students who want to 
	*   join a study group for that class.
	*   Written by Alex Richter
	*   @return Result set with course name, course ID and interested students
	*/
	public ResultSet studentsInterested() 
	{
		ResultSet rset = null;
		String sql = null;

		try {
			//create a SQL string for the statement
			
			Statement stmt = conn.createStatement();
			
			sql = "SELECT CourseName, INTERESTED_IN.CourseID, Count(*) AS '#Interested' " + 
			      "FROM COURSE, INTERESTED_IN " + 
			      "WHERE COURSE.CourseID = INTERESTED_IN.CourseID " +
			      "GROUP BY COURSE.CourseID";
				
			rset = stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			System.out.println("Problem with studentsInterested " + e.getMessage() + sql);
		}

		return rset;
	}//studentsInterested
	
	public ResultSet studentsInterestedAdmin() 
	{
		ResultSet rset = null;
		String sql = null;

		try {
			//create a SQL string for the statement
			Statement stmt = conn.createStatement();
			sql = "SELECT C.CourseName, I.CourseID, Fname, Lname " + 
				"FROM COURSE AS C, INTERESTED_IN AS I, STUDENTS AS S " + 
				"WHERE I.StudentID = S.ID AND C.CourseID = I.CourseID " +
				 "ORDER BY C.CourseName";
				
			rset = stmt.executeQuery(sql);

		} catch (SQLException e) {
			System.out.println("Problem with studentsInterestedAdmin " + e.getMessage() + sql);
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
	
	
	
	
	//USER STORY 5: 
	/**
	 * This method will add a Student to INTERESTED_IN who is interested in participating in a Study Group for a Course
	 * Written by Travis Dunn
	 * @param StudentID is the ID of the Student
	 * @param CourseID is the ID of the Course
	 * @return num the number of tuples successfully inserted
	 */
	public int showInterest(String StudentID, String CourseID) {
		int num = 0;
		String sql = null;
		
		try {
			sql = "INSERT INTO INTERESTED_IN " +
				  "VALUES (?, ?) ";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.clearParameters();
			pstmt.setString(1,  StudentID);
			pstmt.setString(2,  CourseID);
			
			num = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Problem with showInterest" + e.getMessage() + sql);
		}
		
		return num;
	}
	
	
	
	
	//USER STORY 6: 
	/**
	 * This method will add a Student to a Study Group based on the Course they've selected
	 * Written by Travis Dunn
	 * @param StudentID is the ID of the Student
	 * @param StudyGroupID is the ID of the Study Group
	 * @return num the number of tuples successfully inserted
	 */
	public int joinStudyGroup(String StudentID, int StudyGroupID) {
		String sql = null;
		int num = 0;
		
		try {
			sql = "INSERT INTO IN_GROUP " +
				  "VALUES(?, ?) ";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, StudentID);
			pstmt.setInt(2, StudyGroupID);
			
			num = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Problem with joinStudyGroup" + e.getMessage() + sql);
		}
		
		return num;
	}
	
	
	//END USER STORIES*********************************************************************************************
	
	/**
	 * Helper method for User Story 4 (Leave Study Group). This method  lists the study group ID,
	 * the course ID, the meeting times and days, the location, and the adminstartor's name for
	 * each study group that the student is participating in. 
	 * Written by Vicky Krastev
	 * @param StudentID, the student ID for the current student
	 * @return rs, a ResultSet of the study groups along with their information for the student. 
	 */
	public ResultSet getStudyGroups(String StudentID) {
		String sql = null;
		ResultSet rs = null;
		
		try {
			sql = "SELECT sg2.StudyGroupID, sg2.CourseID, sg2.MeetingTimes, sg2.MeetingDays, sg2.Location, a2.fname, a2.lname "
					+ "FROM STUDY_GROUP as sg2, ADMIN as a2 "
					+ "WHERE sg2.AdminID = a2.ID and sg2.StudyGroupID IN "
					+ "(SELECT ig2.SG_ID "
					+ "FROM  IN_GROUP as ig2 "
					+ "WHERE ig2.StudentID = ?) and a2.fname IN "
					+ "(SELECT a1.fname "
					+ "FROM ADMIN as a1 "
					+ "WHERE a1.ID IN "
					+ "(Select sg1.AdminID "
					+ "from STUDY_GROUP as sg1 "
					+ "where sg1.StudyGroupID IN "
					+ "(select ig1.SG_ID "
					+ "from IN_GROUP as ig1 "
					+ "where ig1.StudentID = ?)))";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			pstmt.setString(1, StudentID);
			pstmt.setString(2, StudentID);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("Problem with getStudyGroups" + e.getMessage() + sql);
		}
		
		return rs;
	}
	
	//Helper Method to Display Study Group Times (Written by Joey)
	public ResultSet displaySGInfo(int sgID) {
		ResultSet rs= null;
		String sql=null;
		try {
			//create a SQL string for the statement
			sql = "SELECT * "
					+ "FROM STUDY_GROUP "
					+ "WHERE StudyGroupID=? ";
				
			      PreparedStatement pstmt = conn.prepareStatement(sql);
			      pstmt.clearParameters();
			      pstmt.setInt(1, sgID);
                  rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("Problem with displaySGInfo " + e.getMessage() + sql);
		}
		return rs;
	}
     /**
     * Helper method
     * @return
     */
    public ResultSet studyGroups() {
        ResultSet rset = null;
        String sql = null;

        try {
            sql = "SELECT StudyGroupID, CourseID " +
                  "FROM STUDY_GROUP " + 
                  "ORDER BY StudyGroupID ";

            Statement stmt = conn.createStatement();
            rset = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("problem with studyGroups()" + e.getMessage() + sql);
        }

        return rset;
    }
	
	/* 
	 * 
	* Helper method that selects all first names, and last name's from Admin, and determines if the name inputted is an Admin.
	@param (String fname, String lname)
	* (Written by Ashwin)
	*/
		
	public boolean isAdmin(String fname, String lname)
	{
		ResultSet rset = null;
		String sql = null;
		boolean x = false;
			
		try {
			sql = " SELECT fname, lname" +
				" FROM ADMIN";
						
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.clearParameters();
			rset = pstmt.executeQuery();
			String name1 = "";
			String name2 = "";
				
			while(rset.next()) {
				name1 = rset.getString("fname");
				name2 = rset.getString("lname");
					if(name1.equalsIgnoreCase(fname) && name2.equalsIgnoreCase(lname)) 
					{
						x = true;
						break;
					}
					else if(name1 != fname || name2 != lname)
					{
						x = false;
						continue;
						
					}
				
				}
			} catch (SQLException e)
			{
				System.out.println("Problem with isAdmin(fname,lname)" + e.getMessage() + sql);
			}
					
			
			return x;
		}
	
	
    //Helper method to check passwords (written by Joey)
	public boolean checkPassword(boolean inAdmin, boolean inStudent, String inputID, String password) {
		boolean bo=false;
		String sql = null;
		if(inAdmin) {
		 try {

	            sql = "SELECT ID "
	            	+ "FROM ADMIN "
	            	+ "WHERE ID=? AND Password=sha2(?, 224)";
	            PreparedStatement pstmt = conn.prepareStatement(sql);

	            pstmt.clearParameters();
	            pstmt.setString(1, inputID);
	            pstmt.setString(2, password);;
	            ResultSet rs=pstmt.executeQuery();
	            String idCounter;
	            
	            	while(rs.next()){
	            		idCounter = rs.getString("ID");
	            		if( idCounter.equals(inputID)) {
	            			bo=true;
	            		}
	            	}
	            
	            

	        } catch (SQLException e) {
	            System.out.println("Problem with checkPassword " + e.getMessage() + sql);
	   	 }
		
		}
		if(inStudent) {
			 try {

		            sql = "SELECT ID "
		            	+ "FROM STUDENTS "
		            	+ "WHERE ID=? AND Password=sha2(?, 224)";
		            PreparedStatement pstmt = conn.prepareStatement(sql);

		            pstmt.clearParameters();
		            pstmt.setString(1, inputID);
		            pstmt.setString(2, password);
		            ResultSet rs=pstmt.executeQuery();
		            String idCounter;
		            
		            	while(rs.next()){
		            		idCounter = rs.getString("ID");
		            		if( idCounter.equals(inputID)) {
		            			bo=true;
		            		}
		            	}
		            
		            

		        } catch (SQLException e) {
		            System.out.println("Problem with checkPassword " + e.getMessage() + sql);
		   	 }
			
			}
		return bo;
	}
	
	
	//Helper method to get all Student IDs (written by Travis)
    public boolean isStudent(String inputID) {
        boolean boo = false;
        String sql = null;

        try {
            sql = "SELECT ID " +
                  "FROM STUDENTS ";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.clearParameters();
            ResultSet rs = pstmt.executeQuery();

            String idCounter;
            while(rs.next()) {
                idCounter = rs.getString("ID");

                if(idCounter.equals(inputID))
                    boo = true;
            }
        } catch (SQLException e) {
            System.out.println("Problem with getStudentID " + e.getMessage() + sql);
        }
        return boo;
    }
	
	
	//Helper method to get all Admin IDs and check if the current ID is an admin's (Written by Joey)
	public boolean isAdmin(String inputID) {
		boolean bo=false;
		String sql = null;
		 try {

	            sql = "SELECT ID "
	            	+ "FROM ADMIN ";
	            PreparedStatement pstmt = conn.prepareStatement(sql);

	            pstmt.clearParameters();
	            ResultSet rs=pstmt.executeQuery();
	            String idCounter;
	            	while(rs.next()){
	            		idCounter = rs.getString("ID");
	            		if( idCounter.equals(inputID)) {
	            			bo=true;
	            		}
	            	}
	            
	            

	        } catch (SQLException e) {
	            System.out.println("Problem with isAdmin(inputID) " + e.getMessage() + sql);
	   	 }
		
		
		return bo;
	}

	

}// Utilities class
