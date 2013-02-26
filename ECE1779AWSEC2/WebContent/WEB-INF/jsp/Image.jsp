<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Image Page </title>
</head>
 
<body>
    
	<img src="https://s3.amazonaws.com/group14_images/<s:property value="image.key1" />" alt="">
	<img src="https://s3.amazonaws.com/group14_images/<s:property value="image.key2" />" alt="">
	<img src="https://s3.amazonaws.com/group14_images/<s:property value="image.key3" />" alt="">
	<img src="https://s3.amazonaws.com/group14_images/<s:property value="image.key4" />" alt="">
</body>
</html>