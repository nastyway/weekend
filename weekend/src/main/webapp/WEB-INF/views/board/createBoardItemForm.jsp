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
				filebrowserBrowseUrl: "<c:url value='/fileupload/uploadFile.do'/>",
			    filebrowserUploadUrl: "<c:url value='/fileupload/uploadFile.do'/>"
	        }
		); 
		
		window.opener.CKEDITOR.tools.callFunction( funcNum, fileUrl, function() {
			alert("start");
		    // Get the reference to a dialog window.
		    var element,
		        dialog = this.getDialog();
		    // Check if this is the Image dialog window.
		    if ( dialog.getName() == 'image' ) {
		    	alert("image");
		        // Get the reference to a text field that holds the "alt" attribute.
		        element = dialog.getContentElement( 'info', 'txtAlt' );
		        // Assign the new value.
		        if ( element )
		            element.setValue( 'alt text' );
		    }
		    // Return false to stop further execution - in such case CKEditor will ignore the second argument (fileUrl)
		    // and the onSelect function assigned to a button that called the file browser (if defined).
		    //return false;
		});
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
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label for="inputEmail1" class="col-lg-2 control-label">Title</label>
				<div class="col-lg-10">
					<input type="email" class="form-control" id="inputEmail1" placeholder="Title">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword1" class="col-lg-2 control-label">Content</label>
				<div class="col-lg-10">
					<textarea class="form-control" name="editor1" id="editor1" rows="10" cols="80">
					This is my textarea to be replaced with CKEditor.
					</textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-default">Submit</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
