<%@ include file="/base/common/taglibs.jsp"%>
<%@ page language="java" 
	pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="<c:url value='/base/css/login.css' />" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		$('#toggle-login').click(function(){
		  $('#login').toggle();
		});
	</script>
</head>
<body>
	<span href="#" class="button" id="toggle-login">Log in</span>
	<div id="login">
		<div id="triangle"></div>
		<h1>Log in</h1>
		<c:url var="post_url" value="/login/loginProcess.do" />
		<form:form action="${post_url }" method="post" commandName="login">
			<form:input path="userId" placeholder="Email" />
			<form:password path="password" placeholder="Password" />
			<input type=submit value="Log in" />
		</form:form>
	</div>
</body>
</html>