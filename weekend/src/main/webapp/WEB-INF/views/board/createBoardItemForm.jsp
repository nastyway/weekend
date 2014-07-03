<%@ include file="/base/common/taglibs.jsp"%>
<%@ page language="java" 
	pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="<c:url value='/base/ckeditor/ckeditor.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/base/css/bootstrap-formhelpers.min.css' />">
<script src="<c:url value='/base/js/bootstrap-formhelpers.js' /> "></script>

<!-- blueimp Gallery styles -->
<link rel="stylesheet" href="<c:url value='/base/css/blueimp-gallery.min.css'/>">
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="<c:url value='/base/jqueryFileUpload/css/jquery.fileupload.css'/>">
<link rel="stylesheet" href="<c:url value='/base/jqueryFileUpload/css/jquery.fileupload-ui.css'/>">

<script type="text/javascript">

	var uploadFileNumber = 0;

	$(document).ready(function(){
		CKEDITOR.replace( 
			'editor1',	{
				filebrowserBrowseUrl: "<c:url value='/fileupload/showImge.do'/>",
			    filebrowserUploadUrl: "<c:url value='/fileupload/ckImageUpload.do'/>"
	        }
		); 
		
		$("#cancel").click(function() {
			location.href="<c:url value='/board/retrieveBoardItemList.do?boardId=${boardItem.boardId}&pageIndex=1'/>";
		});
		
		$('#datepickers').bfhdatepicker('toggle');
		$('#datepickers1').bfhdatepicker('toggle');
		
		$("#fileupload").bootstrapValidator({
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	            title: {
	                validators: {
	                    notEmpty: {
	                        message: 'The title is required and cannot be empty'
	                    }
	                }
	            },
	            startDate: {
	                validators: {
	                    notEmpty: {
	                        message: 'Start date is required and cannot be empty'
	                    },
	                    date: {
	                        format: 'y-m-d',
	                        message: 'The value is not a valid date'
	                    }
	                }
	            },
	            endDate: {
	                validators: {
	                    notEmpty: {
	                        message: 'End date is required and cannot be empty'
	                    },
	                    date: {
	                        format: 'y-m-d',
	                        message: 'The value is not a valid date'
	                    }
	                }
	            }
	        },
	        message: 'This value is not valid',
	        submitHandler: function(validator, form, submitButton) {
	        	
	        	//날짜비교를 한다. 종료일은 시작일보다 이전일 수 없다.
				var startDate = $("#bfh-datepicker1").val();
	        	var endDate = $("#bfh-datepicker2").val();
	        	if(startDate > endDate) {
	        		alert("종료일은 시작일보다 이전일 수 없다.");
	        		return false;
	        	} else {
	        		
	        	}
	        	var editor_data = CKEDITOR.instances.editor1.getData();
				$("#contents").attr('value',editor_data);
				
				//글을 등록한다.
				validator.defaultSubmit();
	        }
	    });
		
		'use strict';

		// Initialize the jQuery File Upload widget:
		$('#fileupload').fileupload({
		// Uncomment the following to send cross-domain cookies:
			//xhrFields: {withCredentials: true},
			url: "<c:url value='/fileupload/upload'/>",
			formData:{extra:1},
			autoUpload: false,
			fileInput: $("input:file")
		}).on("fileuploadadd",function(e,data){
		}).on('fileuploaddone', function (e, data) {
		$.each(data.result.files, function (index, file) {
			//여기까지오면 파일이 다 올라감.
			//파일아이디를 다 넣고
			$('<input>').attr('type','hidden').attr('name','fileIdList[]').val(file.fileId).appendTo('form');
			uploadFileNumber -= 1;
		});

		if(uploadFileNumber==0){
				//섭밋을 한다.
				$("#submitItemHidden").click();
			}
		});

		// Enable iframe cross-domain access via redirect option:
		$('#fileupload').fileupload(
			'option',
			'redirect',
			window.location.href.replace(
				/\/[^\/]*$/,
				'/cors/result.html?%s'
			)
		);

		$("#writeItem").click(function(e) {
	
			if($(".btn.btn-primary.start").length>0){
				uploadFileNumber = $(".btn.btn-primary.start").length;
		
				$(".btn.btn-primary.start").each(function(index,element){
					$(this).trigger("click");
				});
			} else {
				alert("사진을 업로드 해주세요!");
			}
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<div class="container">
				<h1>Create News board Item</h1>
			</div>
		</div>
	
		<c:url var="post_url" value="/board/createBoardItem.do" />
		<form class="form-horizontal" id="fileupload" action="${post_url }" method="POST" enctype="multipart/form-data" role="form">
			<input type="hidden" name="boardId" id="boardId" value="${boardItem.boardId }"/>
			<div class="form-group">
				<label for="title" class="col-sm-2 control-label">Title</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="title" name="title" placeholder="Title" />
				</div>
			</div>
			<div class="form-group">
				<label for="datepickers1" class="col-sm-2 control-label">Start Date</label>
				<div class="col-sm-10 bfh-datepicker" data-name="startDate" id="bfh-datepicker1" data-format="y-m-d" data-date="today">
					<input id="datepickers" type="text" class="datepicker" />
				</div>
			</div>
			<div class="form-group">
				<label for="datepickers2" class="col-sm-2 control-label">End Date</label>
				<div class="col-sm-10 bfh-datepicker" data-name="endDate" id="bfh-datepicker2" data-format="y-m-d" data-date="today">
					<input id="datepickers1" type="text" class="datepicker" />
				</div>
			</div>
			<div class="form-group">
				<label for="editor1" class="col-sm-2 control-label">Content</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="contents" id="editor1" rows="10" cols="80">
					</textarea>
				</div>
			</div>
			<div class="form-group">
				<label for="editor1" class="col-sm-2 control-label">Images</label>
				<div class="col-sm-10">
					<div class="row fileupload-buttonbar">
			            <div class="col-lg-7">
			                <!-- The fileinput-button span is used to style the file input field as button -->
			                <span class="btn btn-success fileinput-button">
			                    <i class="glyphicon glyphicon-plus"></i>
			                    <span>Add files...</span>
			                    <input type="file" name="files[]" multiple>
			                </span>
			                <!-- The global file processing state -->
			                <span class="fileupload-process"></span>
			            </div>
			            <!-- The global progress state -->
			            <div class="col-lg-5 fileupload-progress fade">
			                <!-- The global progress bar -->
			                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
			                    <div class="progress-bar progress-bar-success" style="width:0%;"></div>
			                </div>
			                <!-- The extended global progress state -->
			                <div class="progress-extended">&nbsp;</div>
			            </div>
			            
		   		 	</div>
	   		 	</div>
			</div>
			<div class="form-group">
				<!-- The table listing the files available for upload/download -->
				<label class="col-sm-2 control-label"></label>
				<div class="col-sm-10">
	            	<table role="presentation" class="table table-striped"><tbody class="files"></tbody></table>
	            </div>
			</div>
			<div class="form-group">
				<ul class="list-inline pull-right">
					<li><button type="button" id="writeItem" class="btn btn-info">Submit</button></li>
					<button type="submit" id="submitItemHidden" class="btn btn-info" style="display:none;">Submit</button>
					<li><button type="button" id="cancel" class="btn btn-default">Cancel</button></li>
				</ul>
			</div>
		</form>
	</div>
	
<!-- The template to display files available for upload -->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error text-danger"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="btn btn-primary start" disabled style="display:none;">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start</span>
                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td>
        <td>
            <p class="name">
                {% if (file.url) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="<c:url value='/base/jqueryFileUpload/js/vendor/jquery.ui.widget.js'/>"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="<c:url value='/base/jqueryFileUpload/js/tmpl.min.js'/>"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="<c:url value='/base/jqueryFileUpload/js/load-image.min.js'/>"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="<c:url value='/base/jqueryFileUpload/js/canvas-to-blob.min.js'/>"></script>
<!-- blueimp Gallery script -->
<script src="<c:url value='/base/jqueryFileUpload/js/jquery.blueimp-gallery.min.js'/>"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="<c:url value='/base/jqueryFileUpload/js/jquery.iframe-transport.js'/>"></script>
<!-- The basic File Upload plugin -->
<script src="<c:url value='/base/jqueryFileUpload/js/jquery.fileupload.js'/>"></script>
<!-- The File Upload processing plugin -->
<script src="<c:url value='/base/jqueryFileUpload/js/jquery.fileupload-process.js'/>"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="<c:url value='/base/jqueryFileUpload/js/jquery.fileupload-image.js'/>"></script>
<!-- The File Upload audio preview plugin -->
<script src="<c:url value='/base/jqueryFileUpload/js/jquery.fileupload-audio.js'/>"></script>
<!-- The File Upload video preview plugin -->
<script src="<c:url value='/base/jqueryFileUpload/js/jquery.fileupload-video.js'/>"></script>
<!-- The File Upload validation plugin -->
<script src="<c:url value='/base/jqueryFileUpload/js/jquery.fileupload-validate.js'/>"></script>
<!-- The File Upload user interface plugin -->
<script src="<c:url value='/base/jqueryFileUpload/js/jquery.fileupload-ui.js'/>"></script>
</body>
</html>
