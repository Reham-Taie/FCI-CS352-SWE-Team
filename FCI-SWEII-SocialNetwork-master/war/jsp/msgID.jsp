<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<form action="/social/msgcheck" method="post">
  Conversation ID : <input type="text" name="ID" /> <br>
   Message: <input type="text" name="Message" /> <br>
  <br> <input type="submit" value="Send"> 
  </form>
  
  
  
  
  <br><form action="/social/back" method="post">
<br> <input type="submit" value="Back"> 
</form> </br>
  
</body>
</html>
