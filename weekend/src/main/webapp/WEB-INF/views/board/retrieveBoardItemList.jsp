<%@ include file="/base/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/base/common/error.jsp"
	pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="<c:url value='/base/js/jquery.twbsPagination.min.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#write").click(function() {
			$(location).attr('href',"<c:url value='/board/createBoardItemForm.do?boardId=${boardId}'/>");
		});
		
		$('#pagination-demo').twbsPagination({
			totalPages: Number("${searchCondition.totalPageSize}"),
			visiblePages: Number("${searchCondition.paginationBlock}"),
			startPage:Number("${searchCondition.pageIndex}"),
			onPageClick: function (event, page) {
				if(Number("${searchCondition.pageIndex}")!=page){
					//페이지 인덱스를 넣고,
					$("#pageIndex").val(page);
					//섭밋을 한다.
					$("#searchForm").submit();
				}
			}
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<div class="container">
				<h1>News board</h1>
			</div>
		</div>
		
		<c:url var="post_url" value="/board/retrieveBoardItemList.do" />
		<form:form id="searchForm" method="post" action="${post_url }" commandName="searchCondition">
		<!-- 폼으로 넘어갈 페이지 인덱스 -->
		<form:input path="pageIndex" type="hidden" id="pageIndex" />
		<form:input path="boardId" type="hidden" id="boardId" value="${boardId }" />
		<table class="table table-hover">
			<thead>
				<tr>
					<th style="text-align: center;" width="10%">Number</th>
					<th style="text-align: center;" width="">Title</th>
					<th style="text-align: center;" width="10%">StartDate</th>
					<th style="text-align: center;" width="10%">EndDate</th>
					<th style="text-align: center;" width="10%">Hit Count</th>
					<th style="text-align: center;" width="10%">Register Name</th>
					<th style="text-align: center;" width="10%">Register Date</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${searchCondition.totalItemCount > 0 }">
					<c:forEach var="entity" items="${result}">
						<tr>
							<td align="center"><a href="<c:url value='/board/retrieveBoardItemDetail.do?boardId=${boardId}&itemId=${entity.itemId}&pageIndex=${searchCondition.pageIndex}'/>">${entity.num }</a></td>
							<td style="padding-left: 10"><a href="<c:url value='/board/retrieveBoardItemDetail.do?boardId=${boardId}&itemId=${entity.itemId}&pageIndex=${searchCondition.pageIndex}'/>">${entity.title }</a></td>
							<td style="center"><a href="<c:url value='/board/retrieveBoardItemDetail.do?boardId=${boardId}&itemId=${entity.itemId}&pageIndex=${searchCondition.pageIndex}'/>">${entity.startDate }</a></td>
							<td style="center"><a href="<c:url value='/board/retrieveBoardItemDetail.do?boardId=${boardId}&itemId=${entity.itemId}&pageIndex=${searchCondition.pageIndex}'/>">${entity.endDate }</a></td>
							<td align="center"><a href="<c:url value='/board/retrieveBoardItemDetail.do?boardId=${boardId}&itemId=${entity.itemId}&pageIndex=${searchCondition.pageIndex}'/>">${entity.hitCount }</a></td>
							<td align="center"><a href="<c:url value='/board/retrieveBoardItemDetail.do?boardId=${boardId}&itemId=${entity.itemId}&pageIndex=${searchCondition.pageIndex}'/>">${entity.registerName }</a></td>
							<td align="center"><a href="<c:url value='/board/retrieveBoardItemDetail.do?boardId=${boardId}&itemId=${entity.itemId}&pageIndex=${searchCondition.pageIndex}'/>">${entity.registerDate }</a></td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${searchCondition.totalItemCount == 0 }">
					<tr>
						<td colspan="7" align="center">There is no result list.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<div class="text-center">
		<ul id="pagination-demo"class="pagination-sm"></ul>
		</div>
		</form:form>
		<div class="col-lg-12">
			<ul class="list-inline pull-right">
				<li><button id="write" type="button" class="btn btn-primary">Write</button></li>
			</ul>
		</div>
		
	</div>
</body>
</html>