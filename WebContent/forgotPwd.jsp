<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script>
function changePwd(){
	if(document.getElementById('pwd').value==document.getElementById('cpwd').value){
		alert('Password Changed');
		document.getElementById('forgotForm').submit();	
	}
	else{
		alert('Password and Confirm Password dont match');
	}
		
}
function check(){
	var uname="<%=request.getAttribute("username")%>";
	if(uname!=null && uname!='null')
		document.getElementById('username').value=uname;
	var mname="<%=request.getAttribute("mothername")%>";
	var petname="<%=request.getAttribute("pet")%>";
	var invaliduser="<%=request.getAttribute("invaliduser")%>";
	if(invaliduser!=null && invaliduser!='null'){
		alert("Wrong Username");	
	}
	/* else{
		document.getElementById('invaliduser').style.display = "none";
	} */
	if(mname!=null && mname!='null') {
		document.getElementById('questions').style.display = "block";
	}
	else{
		document.getElementById('questions').style.display = "none";
	}
		
	}
function checkDetails(){
	var mname="<%=request.getAttribute("mothername")%>";
	var petname="<%=request.getAttribute("pet")%>";
	if(document.getElementById('mothername').value==mname && document.getElementById('pet').value==petname)
		document.getElementById('password').style.display = "block";
	else
		alert("Wrong Answers");
}
</script>
</head>
<body onLoad="check();" bgcolor="#F5ECCE">
<form action="PwdResetServlet" method="post" id="forgotForm">
<font size="4">
<p align='right'><a href='/MediaSharing/index.jsp'>Home</a></p>
<table>
<tr style="height:40px;"><td style="width:40px;"></td><td><font size="6" color="red"><p>Reset Your Password</p></font></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td>Enter Username:<input type="text" name="username" id="username"></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td><input type="Submit"></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td><div id="invaliduser" style="display:none">There is no account with this username. Please create an account before signing in.</div></td></tr>
</table>
<table id="questions" style="display:none">
<tr style="height:40px;"><td style="width:40px;"></td><td><font size="6" color="red">Answer Following Security Questions:</font></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td>Which is your favourite pet?:<input type="text" name="pet" id="pet"></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td>What is your mother's last name?:<input type="text" name="mothername" id="mothername"></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td><input type="button" value="Submit" onclick="checkDetails();"></td></tr>
</table>
<table id="password" style="display:none">
<tr style="height:40px;"><td style="width:40px;"></td><td>Enter Password:<input type="password" name="pwd" id="pwd"></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td>Confirm Password:<input type="password" name="cpwd" id="cpwd"></td></tr>
<tr style="height:40px;"><td style="width:40px;"></td><td><input type="button" value="Submit" onclick="changePwd();"></td></tr>
</table>
</font>
</form>
</body>
</html>