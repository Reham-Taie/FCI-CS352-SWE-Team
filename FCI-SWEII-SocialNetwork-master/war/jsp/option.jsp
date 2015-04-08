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
	<br>
	<form action="/social/requests" method="post">
		<input type="submit" value="sendrequest">
	</form>
	</br>

	<br>
	<form action="/social/viewsearch" method="post">

		<input type="submit" value="acceptrequest">
	</form>
	</br>

	<br>
	<form action="/social/viewsearch" method="post">

		<input type="submit" value="Search User">
	</form>
	</br>

	<br>
	<form action="/social/signout" method="post">
		<br> <input type="submit" value="Signout">
	</form>
	</br>

</body>
</html>


