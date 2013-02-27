<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Welcome Manager</title>
</head>
 
 
<body>
    <h2>Hello Manager: <s:property value="username" />...!</h2>
    
    <!-- START: Test manipulating workers -->
    <h5>WARNING: Be careful!</h5>
    <h5>Every time you click "Create Instances" button, a new EC2 instance will be created.</h5>
    <h5>Every time you click "Start Instances" button, two hard coded existing instances will be started.</h5>
    <h5>Every time you click "Stop Instances" button, the two hard coded existing instances will be stopped.</h5>
    
    <s:form action="createInstances.action" method="post">
    	<s:submit method="createInstances" key="label.createInstances" align="center" />
	</s:form>
    <s:form action="startInstances.action" method="post">
    	<s:submit method="startInstances" key="label.startInstances" align="center" />
	</s:form>
    <s:form action="stopInstances.action" method="post">
    	<s:submit method="stopInstances" key="label.stopInstances" align="center" />
	</s:form>	
    <s:form action="watchCloud.action" method="post">
    	<s:submit method="watchCloud" key="label.watchCloud" align="center" />
	</s:form>		
	<!-- END: Test manipulating workers -->

</body>
</html>