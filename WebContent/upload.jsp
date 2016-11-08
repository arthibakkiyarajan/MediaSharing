<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% System.out.println(request.getAttribute("err"));%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
 function textAreaVisibility(){
	 if(document.getElementById('listofpeople').checked)
         document.getElementById('ta').style.display = "block";
  	 else
         document.getElementById('ta').style.display = "none";
 }
 function beforeSubmitting(){
	 if(document.getElementById('file').value==''){
		 alert("Upload file before submitting");
		 exit;
	 }
	 /* if(document.getElementById('listofpeople').checked){
		 if(document.getElementById('ta').value==null||document.getElementById('ta').value==''){
			 alert("Enter Email Ids you want to give permission to");
		 }
	 } */
	 else
		 document.getElementById('formId').submit();;
 }
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#F5ECCE" align="center">

<form name="Mediaform" id="formId" action="UploadDownloadFileServlet" method="post" enctype="multipart/form-data">
<font size="4">
<p align='right'><a href='/MediaSharing/index.jsp'>Sign out</a></p>

<br/><br/><br/><br/><br/><br/>
<a href="DisplayFilesServlet" align="right">Click here to View Files</a><br/><br/><br/><br/>
<!-- <input checked type="radio" name="permission" value="All" onClick="textAreaVisibility();">Everybody can view file<br/>
<input type="radio" name="permission" value="None" onClick="textAreaVisibility();">Only I can view the file<br/>
<input type="radio" id="listofpeople" name="permission" value="Group Of People" onClick="textAreaVisibility();">Give Permission only to few Users<br/>
Enter Email Ids:<textarea id="ta" style="display:none;" rows="10" placeholder="Enter Emailids seperated by ;" name="emaillist" ></textarea> -->
Upload Files:&nbsp;&nbsp;<input type="file" name="file" id="file" /><br/><br/><br/><br/><br/>
<input type="button" value="Submit" onClick="beforeSubmitting();">
<input type="hidden" name="err" id="err">
</font>
</form>
</body>
</html>