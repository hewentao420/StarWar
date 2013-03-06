<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Amazon AWS</title>
</head>
 
<body>
<h2>Welcome to Amazon EC2</h2>
<s:actionerror />
<s:form action="login.action" method="post">
    <s:textfield name="username" key="label.username" size="20" />
    <s:password name="password" key="label.password" size="20" />
    <s:submit method="authenticate" key="label.login" align="center" />
</s:form>

<s:form action="createAccount.action" method="post">
    <s:textfield name="username" key="label.username" size="20" />
    <s:password name="password" key="label.password" size="20" />
    <s:password name="retypedPassword" key="label.password.retype" size="20" />
    <s:submit method="createAccount" key="label.createAccount" align="center" />
</s:form>

</body>
</html>