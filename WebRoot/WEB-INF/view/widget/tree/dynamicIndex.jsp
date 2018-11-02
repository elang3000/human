<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统" />
</head>
<smart:body>
	<script>
		function test(event, treeId, treeNode) {
	        alert(treeNode.tId + ", " + treeNode.name);
	       };
	</script>
	<smart:dynamicTree id="treeArea" ruleKey="${ruleKey}" customEvent="true" funcType="onClick" funcName="test" />
</smart:body>
</html>