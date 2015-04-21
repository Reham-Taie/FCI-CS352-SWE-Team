<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>

<p> Welcome b2a ya ${it.name} </p>
<p> This is should be user home page </p>
<p> Current implemented services "http://fci-swe-apps.appspot.com/rest/RegistrationService --- {requires: uname, email, password}" </p>
<p> and "http://fci-swe-apps.appspot.com/rest/LoginService --- {requires: uname,  password}" </p>
<p> you should implement sendFriendRequest service and addFriend service


<form action="/social/option" method="post">  <br>
<br> <input type="submit" value="option"> 
</form> 
<form action="/social/conversationpage" method="post">  <br>
<br> <input type="submit" value="Create Conversation"> 
</form> 

<form action="/social/timeline" method="post">  <br>
<br> <input type="submit" value=${it.name}> 
</form> 


<form action="/social/page" method="post">  <br>
<br> <input type="submit" value="Create_Page"> 
</form> 


<form action="/social/likepage" method="post">  <br>
<br> <input type="submit" value="Like/Open Page"> 
</form> 


<form action="/social/tlpost" method="post">  <br>
<br> <input type="submit" value="Timeline Post"> 
</form> 

<form action="/social/ppost" method="post">  <br>
<br> <input type="submit" value="Page Post"> 
</form> 
<form action="/social/htag" method="post">  <br>
<br> <input type="submit" value="Hashtag Post"> 
</form> 
////////////////////////////


 <P>link to send a friend message:</P><a href = "/social/message">Send Message to user</a>
  
   <P>link to send a group message:</P><a href = "/social/chatmessage">Send Group Message</a>
</body>
</html>


