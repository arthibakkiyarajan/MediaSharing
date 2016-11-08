<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script>
function adddiv(){
var err=<%= request.getAttribute("err")%>;
if(err==1){
	 document.getElementById('divid').innerHTML="<font color='red'>Wrong Username or Password</font>";
	}
}
</script>
<body bgcolor="#F5ECCE" align="center" onLoad="adddiv();">
<div id="divid"></div>
<form action="CheckLoginDetailsServlet" method="post">
<font size="4">
<p align="right"><a href="/MediaSharing/newUser.jsp">Click Here to create an account</a></p>
<p align="right"><a href="/MediaSharing/forgotPwd.jsp">Forgot Password?</a></p>
</font>
<br/><br/><br/><br/>
<font size="6" color="red">Welcome to Media Sharing</font><br/><br/>
<font size="4">
Username:
<input type="text" name="email"/><br/><br/>
Password:
<input type="password" name="password"/><br/><br/>
<input type="submit">
</font>
</form>
</body>
</html>