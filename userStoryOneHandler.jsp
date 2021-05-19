<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="myUtil" class="dbUtil.Utilities" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="myStyle.css">
<title>Handler for User Story 1</title>
</head>

<body> 
<% if (myUtil.getConn()==null) { %>
   <jsp:forward page="openForm.jsp"></jsp:forward>
<% } %>  

<%@ include file="headAdmin.jsp" %>
<h1>Updated Study Group</h1>
<% String checker;
   String meetingTime = request.getParameter("meetingTime");
   if(meetingTime.isEmpty()){
	   checker = "Meeting Time is empty, please input a value";
   session.setAttribute("SES_CHECK", checker);%>
   <jsp:forward page="userStoryOneForm.jsp"></jsp:forward>
   <%}
   String courseID = request.getParameter("courseID");
   if(courseID.isEmpty()){
	   checker = "Course ID is empty, please input a value";
   session.setAttribute("SES_CHECK", checker);%>
   <jsp:forward page="userStoryOneForm.jsp"></jsp:forward>
   <%}
   String sgID = request.getParameter("studyGroupID");
   if(sgID.isEmpty()){
	   checker = "Study Group ID is empty, please input a value";
   session.setAttribute("SES_CHECK", checker);%>
   <jsp:forward page="userStoryOneForm.jsp"></jsp:forward>
   <%}
   		int sgID2=0;
   		try{
   			sgID2 = Integer.parseInt(sgID);
   		}catch(Exception e){
   			checker="Study Group ID must be an Integer besides 0";
   		   session.setAttribute("SES_CHECK", checker);
   		%><jsp:forward page="userStoryOneForm.jsp"></jsp:forward>
   		<%}
   		if(sgID2==0){
   		   checker="Study Group ID must not be an Integer besides 0";
   		   session.setAttribute("SES_CHECK", checker); 
   	   }
   int result = myUtil.updateStudyGroupTime(meetingTime,courseID,sgID2);
   if(result==1){
	   checker="";
	   session.setAttribute("SES_CHECK", checker); 
	   out.println("You updated the meeting time for group " + sgID);
   }
   else{
   checker = "There was an error, please check if your inputs are valid";
   session.setAttribute("SES_CHECK", checker); %>
	   <jsp:forward page="userStoryOneForm.jsp"></jsp:forward>
   <%} %>
   <% ResultSet rset=myUtil.displaySGInfo(sgID2);
%>

<table>
<tr><th>Study Group ID</th> <th>Course ID</th> <th>Meeting Time</th> <th>Meeting Days</th> <th>Location</th> <th>AdminID</th> </tr>
<%while (rset.next()) { %>
 <tr> <td><%= rset.getString(1) %> </td> 
 	  <td><%= rset.getString(2) %> </td> 
 	  <td><%= rset.getString(3) %> </td> 
      <td><%= rset.getString(4) %> </td> 
      <td><%= rset.getString(5) %> </td>
      <td><%= rset.getString(6) %> </td> 
 </tr>
<%} %>
</table>

</body>
</html>
