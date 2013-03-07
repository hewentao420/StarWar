<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	color: #F1F5F8;
	background: #000000 url(images/background.png) repeat-x;
}

#topContent #rightTopContent {
	font-family: Palatino Linotype, Franklin Gothic Medium, Arial;
	line-height: 105px;
	font-size: 21px;
	text-align: right;
}

.container .grid_8 {
	width: 620px;
	display: inline;
	margin-left: 10px;
	margin-right: 10px;
	float: left;
}

.container .grid_4 {
	width: 300px;
	margin-left: 10px;
	float: left;
	margin-right: 10px;
	display: inline;
}

#topContent #rightTopContent a {
	margin-right: 10px;
	margin-left: 10px;
}

a,a:visited {
	color: #228FEA;
	text-decoration: none;
}

h1 {
	color: #0A65C9;
	font-size: 40px;
	font-weight: bold;
	padding: 0px;
	margin: 0px;
	margin-bottom: 10px;
}

h2 {
	padding: 0px;
	padding-left: 20px;
	margin-top: -10px;
	color: white;
	font-size: 20px;
	font-weight: bold;
}

#topContent {
	height: 85px;
}

.container {
	margin-left: auto;
	margin-right: auto;
	width: 960px;
	margin-bottom: 20px;
	overflow:hidden;
}

.tdLabel {
	width: 150px;
}

label {
	font-size: 13px;
}

#form1_label_login {
	width: 120px;
	margin-top: 10px;
}

#form2_label_createAccount {
	margin-top: 10px;
	width: 120px;
}

.login_titile {
	font-size: 25px;
	color: #228FEA;
}

.title {
	margin-left: 30px;
	font-size: 18px;
	font-weight: bold;
}
.content{
	margin-left:30px;
}

input{
	float:left;
	width:150px;
}

.image-fram{
	text-align:center;
	float:left;
	height:90px;
	width:90px; 
	margin:4px; 
}
.image{
	border: 3px solid #ccc;
	min-width:60px;
	max-width:80px;
	max-height:80px;
}

</style>
<title>Welcome User</title>
</head>

<body>
	<div class="container" id="topContent">
		<div class="grid_4">
			<h1>Cloud Images</h1>
			<h2>
				Hello,
				<s:property value="username" />
				!
			</h2>
		</div>
		<div class="grid_8" id="rightTopContent">
			<a href="./">home</a> | <a href="#">contact</a>
		</div>
	</div>
	<div class="container">
		<div class="title">Your images...!</div>
		<div class="content">
			<s:iterator value="images">
				<div class="image-fram">
					<a
						href="/ECE1779AWSEC2/seeImage.action?imageId=<s:property value="id" />">
						<img class="image"
						src="https://s3.amazonaws.com/group14_images/<s:property value="key2" />"
						alt="">
					</a>
				</div>

			</s:iterator>
		</div>
	</div>
	<div class="container" id="upload">
		<s:actionerror />
		<div class="title">Upload your image...!</div>

		<div class="content">
			<s:form action="uploadImage.action" method="POST"
				enctype="multipart/form-data">
				<s:file name="fileUpload" key="label.selectFile" size="40" />
				<s:submit value="submit" name="submit" />
			</s:form>
		</div>
	</div>
</body>
</html>