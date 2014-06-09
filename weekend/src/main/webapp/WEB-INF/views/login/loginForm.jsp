<%@ include file="/base/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/base/common/error.jsp"
	pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script src="//code.jquery.com/jquery-2.1.1.min.js"></script>
	<link href="/base/css/login.css" rel="stylesheet" type="text/css">
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