<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Image Page </title>
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
	height: 105px;
}

.container{
	margin-left: auto;
	margin-right: auto;
	width: 960px;
}

#logo {
	width: 650px;
}

#logo img {
	float: left;
	width: 644px;
	border: 3px solid #ccc;
}

#login {
	float: left;
	width:290px;
	margin-left:20px;
}

#login #form1 {
	float: left;
	width: 100%;
}

.tdLabel {
	width: 130px;
}

label{
	font-size:13px;	
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

input{
	float:left;
}

#images {
	text-align:center;
}

#images img{
	max-width:960px;
	margin-bottom:10px;
}
</style>
</head>
 
<body>
    <div class="container" id="topContent">
		<div class="grid_4">
			<h1>Cloud Images</h1>
			<h2>
				Image display
			</h2>
		</div>
		<div class="grid_8" id="rightTopContent">
			<a href="./">home</a> | <a href="#">contact</a>
		</div>
	</div> 	
	
    <div class="container" id="images">
    	<div>Original Picture</div>
		<img src="https://s3.amazonaws.com/group14_images/<s:property value="image.key1" />" alt="">
    	<div>Transformation One</div>
		<img src="https://s3.amazonaws.com/group14_images/<s:property value="image.key2" />" alt="">
    	<div>Transformation Two</div>
		<img src="https://s3.amazonaws.com/group14_images/<s:property value="image.key3" />" alt="">
    	<div>Transformation Three</div>
		<img src="https://s3.amazonaws.com/group14_images/<s:property value="image.key4" />" alt="">
	</div>
</body>
</html>