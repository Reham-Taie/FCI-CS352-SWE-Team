<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>

<p> Welcome to your profile  ${it.name} </p>

<form action="/social/post" method="post">
  Write your post : <input type="text" name="post" /> <br>
   Send To : <input type="text" name="Post_To" /> <br>
  
  <br> <input type="submit" value="Post"> 
  </form>

</body>