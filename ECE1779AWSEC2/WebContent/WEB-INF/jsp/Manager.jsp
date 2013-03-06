<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Welcome Manager</title>
</head>
 
<body>
    <h2>Hello Manager: <s:property value="username" />...!</h2>
    <s:actionerror />
    <hr/>
    
    <div>
    	<h3>You have the following workers: </h3>
    	<s:iterator value="workers">
    		Worker ID:<s:property value="id" />
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		CPU Usage:<s:property value="cpuUsage" />
    		<br/>
    	</s:iterator>
    		
    	<s:form action="refresh.action" method="post">
    		<tr><s:submit method="refresh" key="label.refresh" align="center" /></tr>	
   		</s:form>
    </div>
    
    <hr/>
     
    <s:form action="adjustWorkerPool.action" method="post">
    	<table>
    		<tr><s:textfield name="increaseNumber" key="label.increase" size="1" /></tr>
    		<tr><s:textfield name="reduceNumber" key="label.reduce" size="1" /></tr>
    		<tr><s:submit method="adjustWorkerPool" key="label.adjustWorkerPool" align="center" /></tr>	
    	</table>
    </s:form>
    
    <hr/>
    
    <s:form action="configure.action" method="post">
    	<s:textfield name="thresholdGrow" key="label.threshold.grow" size="5" />
    	<s:textfield name="thresholdShrink" key="label.threshold.shrink" size="5" />
    	<s:textfield name="ratioExpand" key="label.ratio.expand" size="5" />
    	<s:textfield name="ratioShrink" key="label.ratio.shrink" size="5" />
    	<s:submit method="configure" key="label.configure" align="center" />
    </s:form>

</body>
</html>