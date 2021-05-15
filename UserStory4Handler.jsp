<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<jsp:useBean id="myUtil"  class="dbUtil.Utilities" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Leave a Study Group</title>
<link rel="stylesheet" href="myStyle.css">
</head>
<body>

<%@ include file="head.jsp" %>

<h1>Remaining Study Groups</h1>
<% String[] studyGroup = request.getParameterValues("delete"); 
	String studentID = request.getParameter("studentID2");
   
	//get number of study groups selected
	//print studentID
	System.out.println(studentID);
	int numSG = studyGroup.length; 
	//System.out.println(numSG); 
	int sgID =0;
	int numLeft =0;
	//new sgArray
	int[] sgArray = new int[numSG]; 
	
	//parse int into new array
	for(int j =0; j< numSG; j++){
		sgID = Integer.parseInt(studyGroup[j]);
		sgArray[j] = sgID; 
	}
	int studyNum=0;
	
	//for each study group, delete from database
	for(int i =0; i<numSG; i++){
		
		//System.out.println(sgArray[i]);
		studyNum = sgArray[i];
		numLeft += myUtil.leaveStudyGroup(studentID, studyNum);
	}
	
	//get remaining groups student is in 
	
	ResultSet remaining = myUtil.getStudyGroups(studentID);
	
%> 

The number of study groups student has deleted: <%=numLeft %>. 
<p></p>
Remaining Study Groups:
<p></p>
<table>
<tr><th>Study Group ID</th> <th>Course</th><th>Meeting Time</th><th>Meeting Day</th><th> Location</th><th>Administrator</th> </tr>
<%while (remaining.next()) { %>
 <tr> <td><%= remaining.getString(1) %> </td> 
      <td><%= remaining.getString(2) %> </td> 
      <td><%= remaining.getString(3) %> </td> 
      <td><%= remaining.getString(4) %> </td> 
      <td><%= remaining.getString(5) %> </td> 
      <td><%= remaining.getString(6) + " " + remaining.getString(7) %> </td> 
      
 </tr>
<%} %>
</table>

<%if(!remaining.next()){ %>
	You are not in any more study groups. 
<%} %>


</body>
</html>