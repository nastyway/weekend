<%@ include file="/base/common/taglibs.jsp"%>
<%@ page language="java" 
	pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value='/base/css/bootstrap-formhelpers.min.css' />">
<script src="<c:url value='/base/js/bootstrap-formhelpers.js' /> "></script>
</head>
<body>
	<div class="container">
		<div class="col-lg-12">
			<br>
			<p>
				${boardItem.contents }
			</p>
		</div>
	</div>
</body>
</html>
