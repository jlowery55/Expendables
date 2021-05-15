<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Open DB Form</title>
<link rel="stylesheet" href="myStyle.css">
</head>
<body>
<%@ include file="head.jsp" %>
<h1>Open The Database</h1>

<form action="open.jsp" method="post">   
    User:<br>
   <input type="text" name="user" value="ex367" size="10" required><br>
   Password:<br>
   <input type="password" name="pw" value="" size="10" ><br>
   Location:<br>
   <input type="radio" name="location" value="on"> On Campus <br>
   <input type="radio" name="location" value="off"> Off Campus <br><br>

   <input type="submit" value="Login">
</form>

</body>
</html>