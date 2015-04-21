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
	<form action="/social/Hashtag" method="post">
     	Hashtag     : <input type="text" name="Hashtag" /> <br>
		
		Post         : <input type="text" name="Post" /> <br>
		
		
		
		 <br> <input type="submit" value="Post"> <br>
		

	</form>
	
	
	<form action="/social/Hashtagcount" method="post">
     	Hashtag     : <input type="text" name="Hashtag" /> <br>
		
		
		
		
		 <br> <input type="submit" value="Get Number Of Posts"> <br>
		

	</form>
</body>
</html>