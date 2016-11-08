<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function popUp(){
	if(document.getElementById('fname').value==null || document.getElementById('fname').value=='')
		alert("First Name cannot be empty");
	else if(document.getElementById('lname').value==null || document.getElementById('lname').value=='')
		alert("Last Name cannot be empty");
	else if(document.getElementById('email').value==null || document.getElementById('email').value=='')
		alert("Email Id cannot be empty");
	else if(document.getElementById('pwd').value==null || document.getElementById('pwd').value=='')
		alert("Password cannot be empty");
	else if(document.getElementById('cpwd').value==null || document.getElementById('cpwd').value=='')
		alert("Confirm Password cannot be empty");
	else if(document.getElementById('pwd').value!=document.getElementById('cpwd').value){
		alert('Password and Confirm Password not matched');
	}
	else if(document.getElementById('pet').value==null || document.getElementById('pet').value==''||document.getElementById('mothername').value==null || document.getElementById('mothername').value=='')
		alert("Security Questions cannot be empty");
	else if(document.getElementById('cpwd').value==null || document.getElementById('cpwd').value=='')
		alert("Confirm Password cannot be empty");
	else{
		alert("Account created successfully");
		document.getElementById('newuserform').submit();
	}
	
}
</script>
</head>

<body bgcolor="#F5ECCE" >
<form action="StoreUserInfoServlet" id="newuserform" name="newuserform" method="POST">
<font size="4">
<p align='right'><a href='/MediaSharing/index.jsp'>Home</a></p>
<font size="6" color="red"><p align="center">Create your New Account</p></font>

<table>
<tr style="height:40px;"><td style="width:40px;"></td><td style="width:300px;">Enter First Name:</td><td><input type="text" name="fname" id="fname"></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td style="width:300px;">Enter Last Name:</td><td><input type="text" name="lname" id="lname"></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td style="width:300px;">Enter your Email Id:</td><td><input type="text" name="email" id="email">&nbsp;&nbsp;<font size="2">(your email id will serve as your username)</font></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td style="width:300px;">Enter Password:</td><td><input type="password" name="pwd" id="pwd"><br/><br/></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td style="width:300px;">Confirm Password:</td><td><input type="password" name="cpwd" id="cpwd"></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td style="width:300px;">Enter Phone No:</td><td><input type="text" name="phone" id="phone"></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td style="width:300px;">Enter Security Questions:</td><td></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td style="width:300px;">Which is your favourite pet?:</td><td><input type="text" name="pet" id="pet"></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td style="width:300px;">What is your mother's last name?:</td><td><input type="text" name="mothername" id="mothername"></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td></tr>
<tr style="height:40px;"><td colspan="3" align="center"><input type="button" value="Submit" onclick="popUp();"></td></tr>

</table>
</font>
</form>
</body>
</html>