<%@ include file="/base/common/taglibs.jsp"%>
<%@ page language="java" 
	pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="<c:url value='/base/ckeditor/ckeditor.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/base/css/bootstrap-formhelpers.min.css' />">
<script src="<c:url value='/base/js/bootstrap-formhelpers.js' /> "></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#list").click(function() {
			location.href="<c:url value='/board/retrieveBoardItemList.do?boardId=${boardItem.boardId}&pageIndex=${pageIndex}'/>";
		});
		
		$("#delete").click(function() {
			location.href="<c:url value='/board/deleteBoardItem.do?boardId=${boardItem.boardId}&itemId=${boardItem.itemId}&pageIndex=${pageIndex}'/>";
		});
		
		$("#update").click(function() {
			location.href="<c:url value='/board/updateBoardItemForm.do?boardId=${boardItem.boardId}&itemId=${boardItem.itemId}&pageIndex=${pageIndex}'/>";
		});
		
		$('li a').click(function (e) {
		    $('#myModal img').attr('src', $(this).attr('data-img-url'));
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<div class="container">
				<h1>Retrieve Board Item Detail</h1>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Title</h3>
			</div>
			<div class="panel-body">
				${boardItem.title }
			</div>
		</div>
		<!-- start & end date -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">StartDate</h3>
			</div>
			<div class="panel-body">
				${boardItem.startDate }
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">EndDate</h3>
			</div>
			<div class="panel-body">
				${boardItem.endDate }
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Date</h3>
			</div>
			<div class="panel-body">
				${boardItem.date }
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Coordinate</h3>
			</div>
			<div class="panel-body">
				${boardItem.coordinate }
			</div>
		</div>
		<hr>
		<!-- Title -->
		<c:if test="${boardItem.uploadedImagesUrl!=null }">
			<div class="col-lg-12">
				<p><strong>Main Image</strong></p>
				<p>
					<ul class="list-inline">
					<c:forEach var="entity" items="${boardItem.uploadedThumbnailUrl }" varStatus="status">
  						<li>
  						<a href="#myModal" data-toggle="modal" data-img-url="${boardItem.uploadedImagesUrl[status.index] }" >
  						<img src='${entity }' style="max-height: 200px; max-width: 200px;" class="img-thumbnail"/>
						</a>
						</li>
					</c:forEach>
					</ul>
				</p>
			</div>
		</c:if>
		<div class="col-lg-12">
			<br>
			<p>
				${boardItem.contents }
			</p>
		</div>
		<hr>
		<div class="col-lg-12">
			<ul class="list-inline pull-right">
				<li><button type="button" id="update" class="btn btn-info">Update</button></li>
				<button type="button" id="delete" class="btn btn-warning">Delete</button>
				<li><button type="button" id="list" class="btn btn-default">List</button></li>
			</ul>
		</div>
		
		<!-- Modal -->
		<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-body" style="text-align: center;">
			    <img src="#"/>
			</div>
		</div>
	</div>
</body>
</html>
