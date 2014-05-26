<%@ include file="/base/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/base/common/error.jsp"
	pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:url var="post_url"  value="/login/loginProcess.do" />
	<form:form action="${post_url }" method="post" commandName="login">
		<table>
			<tr>
				<td>ID : </td>
				<td><form:input path="userId" /></td>
			</tr>
			<tr>
				<td>PW : </td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td colspan=2><input type=submit value="확인" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>