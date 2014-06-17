<%@ include file="/base/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/base/common/error.jsp"
	pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="<c:url value='/base/ckeditor/ckeditor.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		CKEDITOR.replace( 
			'editor1',	{
				filebrowserBrowseUrl: "<c:url value='/fileupload/showImge.do'/>",
			    filebrowserUploadUrl: "<c:url value='/fileupload/ckImageUpload.do'/>"
	        }
		); 
		
		$("#form").submit(function(){
			var editor_data = CKEDITOR.instances.editor1.getData(); 
			$("#contents").attr('value',editor_data);
			alert(editor_data);
		})
	});
</script>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1>Create News board Item</h1>
		</div>
	</div>
	<div class="container">
		<c:url var="post_url" value="/board/createBoardItem.do" />
		<form:form id="form" commandName="boardItem" class="form-horizontal" role="form" method="post" action="${post_url}" >
			<form:hidden path="boardId" value="${boardItem.boardId }"/>
			<div class="form-group">
				<form:label path="title" class="col-lg-2 control-label">Title</form:label>
				<div class="col-lg-10">
					<form:input path="title" type="text" class="form-control" id="title" placeholder="Title" />
				</div>
			</div>
			<div class="form-group">
				<form:label path="contents" class="col-lg-2 control-label">Content</form:label>
				<div class="col-lg-10">
					<form:hidden path="contents" id="contents" value="" />
					<textarea class="form-control" name="editor1" id="editor1" rows="10" cols="80">
					</textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-default">Submit</button>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>
