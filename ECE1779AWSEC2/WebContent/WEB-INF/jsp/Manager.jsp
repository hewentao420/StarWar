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
	padding-left: 10px;
	padding-right: 10px;
}

#topContent {
	height: 100px;
	margin-bottom:10px;
}

input {
	float: left;
}

#topContent #rightTopContent {
	font-family: Palatino Linotype, Franklin Gothic Medium, Arial;
	line-height: 105px;
	font-size: 21px;
	text-align: right;
	overflow:hidden;
}

.grid_8 {
	width: 620px;
	margin-left: 10px;
	margin-right: 10px;
	float: right;
}

.grid_4 {
	width: 300px;
	margin-left: 10px;
	float: left;
	margin-right: 10px;
}

#topContent #rightTopContent a {
	margin-right: 10px;
	margin-left: 10px;
}

#refresh_label_refresh {
	width: 150px;
}

#adjustWorkerPool_label_adjustWorkerPool {
	width: 150px;
}

#configure_label_configure {
	width: 150px;
}

.tdLabel {
	width: 350px;
}

h1 {
	font-family: Arial, Helvetica, sans-serif;
	color: #0A65C9;
	font-size: 40px;
	font-weight: bold;
	padding: 0px;
	margin: 0px;
	margin-bottom: 10px;
}
</style>
<script type="text/javascript">
	function numbersOnly(myfield, e) {
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else if (e)
			key = e.which;
		else
			return true;
		keychar = String.fromCharCode(key);

		// control keys
		if ((key == null) || (key == 0) || (key == 8) || (key == 9)
				|| (key == 13) || (key == 27))
			return true;

		// numbers
		else if ((("0123456789.").indexOf(keychar) > -1))
			return true;
		// decimal point jump
		else
			return false;
	}

	function integersOnly(myfield, e) {
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else if (e)
			key = e.which;
		else
			return true;
		keychar = String.fromCharCode(key);

		// control keys
		if ((key == null) || (key == 0) || (key == 8) || (key == 9)
				|| (key == 13) || (key == 27))
			return true;

		// numbers
		else if ((("0123456789").indexOf(keychar) > -1))
			return true;
		// decimal point jump
		else
			return false;
	}

	function clearField(field) {
		document.getElementsByName(field)[0].value = "";
	}

	function formatPercent(field) {
		var y = document.getElementsByName(field)[0].value.substring(0, 4);
		var x = parseFloat(y.substring(0, 2) + "." + y.substring(2)).toFixed(2);
		document.getElementsByName(field)[0].value = x;
	}
</script>

</head>

<body>
	<div id="topContent">
		<div class="grid_4">
			<h1>Cloud Images</h1>
			<h2>
				Hello,
				<s:property value="username" />
				!
			</h2>
		</div>
		<div class="grid_8" id="rightTopContent">
			<a href="./">home</a> | <a href="./jsp/Contact.jsp">contact</a>
		</div>
	</div>
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
				<s:textfield name="increaseNumber" key="label.increase" size="1"
					onkeypress="return integersOnly(this, event);"
					onkeydown="clearField('reduceNumber')" maxlength="2" />
			</tr>
			<tr>
				<s:textfield name="reduceNumber" key="label.reduce" size="1"
					onkeypress="return integersOnly(this, event);"
					onkeydown="clearField('increaseNumber')" maxlength="2" />
			</tr>
			<tr>
				<s:submit method="adjustWorkerPool" key="label.adjustWorkerPool"
					align="center" />
			</tr>
		</table>
	</s:form>

	<hr />

	<s:form action="configure.action" method="post">
		<s:textfield name="thresholdGrow" key="label.threshold.grow" size="5"
			onkeypress="return numbersOnly(this, event);"
			onchange="formatPercent('thresholdGrow')" />
		<s:textfield name="thresholdShrink" key="label.threshold.shrink"
			size="5" onkeypress="return numbersOnly(this, event);"
			onchange="formatPercent('thresholdShrink')" />
		<s:textfield name="ratioExpand" key="label.ratio.expand" size="5"
			onkeypress="return integersOnly(this, event);" maxlength="1" />
		<s:textfield name="ratioShrink" key="label.ratio.shrink" size="5"
			onkeypress="return integersOnly(this, event);" maxlength="1" />
		<s:submit method="configure" key="label.configure" align="center" />
	</s:form>

</body>
</html>