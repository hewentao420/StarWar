<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Welcome User</title>
</head>
 
<body>
    <h2>Hello User: <s:property value="username" />...!</h2>
	<s:actionerror />
    <h2>Upload your image...!</h2>
    <s:form action="uploadImage.action" method="POST" enctype="multipart/form-data">
        <s:file name="fileUpload" key="label.selectFile" size="40" />
        <s:submit value="submit" name="submit" />
    </s:form>
    
    <h2>Your images...!</h2>
	<s:iterator value="images" >
		<a href="/ECE1779AWSEC2/seeImage.action?imageId=<s:property value="id" />">
			<img src="https://s3.amazonaws.com/group14_images/<s:property value="key2" />" alt="" width="32" height="32">
		</a>
	</s:iterator>
</body>
</html>