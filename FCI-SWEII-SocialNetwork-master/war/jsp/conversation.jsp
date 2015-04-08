<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
	<form action="/social/conversation" method="post">
		Conversation ID : <input type="text" name="ID" /> <br> Member
		Name : <input type="text" name="Name" /> <br> <input
			type="submit" value="Add">
	</form>

	<br>
	<form action="/social/msg" method="post">
		<br> <input type="submit" value="Send Message">
	</form>
	</br>


	<br>
	<form action="/social/back" method="post">
		<br> <input type="submit" value="Back">
	</form>
	</br>

</body>
</html>





