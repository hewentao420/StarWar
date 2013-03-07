<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Welcome Manager</title>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	color: #F1F5F8;
	background: #000000 url(images/background.png) repeat-x;
	padding-left:10px;
	padding-right:10px;
}

input {
	float: left;
}

#refresh_label_refresh{
	width:150px;
}

#adjustWorkerPool_label_adjustWorkerPool{
	width:150px;
}

#configure_label_configure{
	width:150px;
}
 
.tdLabel{
	width:350px;
}

h1{font-family: Arial, Helvetica, sans-serif;
	color: #0A65C9;
	font-size: 40px;
	font-weight: bold;
	padding: 0px;
	margin: 0px;
	margin-bottom: 10px;}
</style>
</head>

<body>

	<h1>Cloud Images</h1>
	<h2>
		Hello,
		<s:property value="username" />
	</h2>
	<s:actionerror />
	<hr />

	<div>
		<h3>You have the following workers:</h3>
		<s:iterator value="workers">
    		Worker ID:<s:property value="id" />
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		CPU Usage:<s:property value="cpuUsage" />
			<br />
		</s:iterator>

		<s:form action="refresh.action" method="post">
			<tr>
				<s:submit method="refresh" key="label.refresh" align="center" />
			</tr>
		</s:form>
	</div>

	<hr />

	<s:form action="adjustWorkerPool.action" method="post">
		<table>
			<tr>
				<s:textfield name="increaseNumber" key="label.increase" size="1" />
			</tr>
			<tr>
				<s:textfield name="reduceNumber" key="label.reduce" size="1" />
			</tr>
			<tr>
				<s:submit method="adjustWorkerPool" key="label.adjustWorkerPool"
					align="center" />
			</tr>
		</table>
	</s:form>

	<hr />

	<s:form action="configure.action" method="post">
		<s:textfield name="thresholdGrow" key="label.threshold.grow" size="5" />
		<s:textfield name="thresholdShrink" key="label.threshold.shrink"
			size="5" />
		<s:textfield name="ratioExpand" key="label.ratio.expand" size="5" />
		<s:textfield name="ratioShrink" key="label.ratio.shrink" size="5" />
		<s:submit method="configure" key="label.configure" align="center" />
	</s:form>

</body>
</html>