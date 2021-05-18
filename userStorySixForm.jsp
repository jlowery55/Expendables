<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="myUtil" class="dbUtil.Utilities" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="myStyle.css">
<title>Form for User Story 6</title>
</head>

<body> 
<% if (myUtil.getConn()==null) { %>
   <jsp:forward page="openForm.jsp"></jsp:forward>
<% } %>  

<%@ include file="headStudent.jsp" %>

<%ResultSet rs = myUtil.studyGroups(); %>

<h1>Form to Join a Study Group </h1>
<form action="userStorySixHandler.jsp" method="post">

Select a Study Group: 
<select name="sgID">
	<%while (rs.next()) { %>
		<option value=<%=rs.getString(1) %> > <%=rs.getString(1) + " - " + rs.getString(2) %> </option>
		
		<%} //end while %>
</select>

<br>
<input type="submit">
</form>

</body>
</html>
