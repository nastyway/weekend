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
	var deletePictureList = new Array();

	$(document).ready(function(){
		
		$("#bfh-datepicker1").val("${boardItem.startDate}");
		$("#bfh-datepicker2").val("${boardItem.endDate}");
		
		CKEDITOR.replace( 
			'editor1',	{
				filebrowserBrowseUrl: "<c:url value='/fileupload/showImge.do'/>",
			    filebrowserUploadUrl: "<c:url value='/fileupload/ckImageUpload.do'/>"
	        }
		); 
		
		$("#cancel").click(function() {
			window.history.back();
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
	            },
	            date: {
	                validators: {
	                    notEmpty: {
	                        message: 'Date is required and cannot be empty'
	                    }
	                }
	            },
	            coordinate: {
	                validators: {
	                    notEmpty: {
	                        message: 'Coordinate is required and cannot be empty'
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
			
			if(uploadFileNumber==0){
				//섭밋을 한다.
				$("#submitItemHidden").click();
			}
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
		
		// Load existing files:
        $('#fileupload').addClass('fileupload-processing');
        $.ajax({
            // Uncomment the following to send cross-domain cookies:
            //xhrFields: {withCredentials: true},
            url: "<c:url value='/fileupload/retrieveFileList/${boardItem.itemId}'/>",
            dataType: 'json',
            context: $('#fileupload')[0]
        }).always(function () {
            $(this).removeClass('fileupload-processing');
        }).done(function (result) {
            $(this).fileupload('option', 'done')
                .call(this, $.Event('done'), {result: result});
         	
            //지워질 데이터를 배열에 저장한 후 마지막에 한꺼번에 처리한다.
            $(".btn.btn-danger.delete").click(function() {
	            //deleteUrl
	            var deleteUrl = $(this).attr("var");
	            deletePictureList.push(deleteUrl);
            });
        });
        
        $(".btn.btn-danger.delete").click(function() {
	       	//deleteUrl
	       	var deleteUrl = $(this).attr("var");
	       	deletePictureList.push(deleteUrl);
       	});
        
		$("#writeItem").click(function(e) {
			if($(".btn.btn-primary.start").length>0 || $('.btn.btn-danger.delete').length>0){
				//지울이미지가 있으면 먼저 삭제하고,
				if(deletePictureList.length>0){
					for(var i=0; i<deletePictureList.length; i++) {
						$.ajax({
							type:"POST",
							url : deletePictureList[i],
							success: function(result){
								console.debug(result);
							},
							error: function(result){
								console.debug(result);
							}
						});
					}
				}
				uploadFileNumber = $(".btn.btn-primary.start").length;
				if(uploadFileNumber==0 && $('.btn.btn-danger.delete').length>0){
					$("#submitItemHidden").click();
				} else {
					$(".btn.btn-primary.start").each(function(index,element){
						$(this).trigger("click");
					});	
				}
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
	
		<c:url var="post_url" value="/board/updateBoardItem.do" />
		<form class="form-horizontal" id="fileupload" action="${post_url }" method="POST" enctype="multipart/form-data" role="form">
			<input type="hidden" name="boardId" id="boardId" value="${boardItem.boardId }"/>
			<input type="hidden" name="itemId" id="itemId" value="${boardItem.itemId }"/>
			<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex }"/>
			<div class="form-group">
				<label for="title" class="col-sm-2 control-label">Title</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="title" name="title" placeholder="Title" value="${boardItem.title }"/>
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
				<label for="date" class="col-sm-2 control-label">Date</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="date" name="date" placeholder="2014년 10월 7일 7:00pm" value="${boardItem.date }"/>
				</div>
			</div>
			<div class="form-group">
				<label for="coordinate" class="col-sm-2 control-label">Coordinate</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="coordinate" name="coordinate" placeholder="37.543710,126.951887" value="${boardItem.coordinate }"/>
				</div>
			</div>
			<div class="form-group">
				<label for="editor1" class="col-sm-2 control-label">Content</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="contents" id="editor1" rows="10" cols="80">
					${boardItem.contents }
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
			                    <input type="file" name="files[]">
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
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}" style="max-height: 100px; max-width: 100px;"></a>
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
                <button class="btn btn-danger delete" delete" var="{%=file.deleteUrl%}">
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
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
