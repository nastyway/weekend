<%@ include file="/base/common/taglibs.jsp"%>
<%@ page language="java" 
	pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- <link rel="icon" href="../../favicon.ico"> -->

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="<c:url value='/base/css/bootstrap.min.css'/>">

<script src="<c:url value='/base/js/jquery-1.11.0.min.js'/>"></script>
<script src="<c:url value='/base/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/base/js/bootstrapValidator.min.js'/>"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="navbar" />
	<tiles:insertAttribute name="content" />
	<tiles:insertAttribute name="footer" />
</body>
</html>