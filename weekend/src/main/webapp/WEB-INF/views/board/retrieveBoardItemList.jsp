<%@ include file="/base/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/base/common/error.jsp"
	pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#write").click(function() {
			$(location).attr('href',"<c:url value='/board/createBoardItemForm.do?boardId=1'/>");
		});
	});
</script>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1>News board</h1>
			<!-- <p>
				<a class="btn btn-primary btn-lg" role="button">Learn more
					&raquo;</a>
			</p> -->
		</div>
	</div>

	<div class="container">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Number</th>
					<th style="text-align: center;">Title</th>
					<th>Hit Count</th>
					<th>Register Name</th>
					<th>Register Date</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${searchCondition.totalItemCount > 0 }">
					<c:forEach var="entity" items="${result}">
						<tr>
							<td align="center">${entity.num }</td>
							<td style="padding-left: 10">${entity.title }</td>
							<td align="center">${entity.hitCount }</td>
							<td align="center">${entity.registerName }</td>
							<td align="center">${entity.registerDate }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${searchCondition.totalItemCount == 0 }">
					<tr>
						<td colspan="5" align="center">There is no result list.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<button id="write" type="button" class="btn btn-primary">Write</button>
	</div>
</body>
</html>