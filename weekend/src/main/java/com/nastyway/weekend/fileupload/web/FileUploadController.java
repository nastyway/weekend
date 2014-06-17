package com.nastyway.weekend.fileupload.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nastyway.weekend.fileupload.model.FileUpload;
import com.nastyway.weekend.fileupload.service.FileUploadService;

@Controller("FileUploadController")
@RequestMapping("/fileupload")
public class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Value("${fileupload.base.path}")
	private String basePath;
	
	@Value("${fileupload.download.image}")
	private String downloadImageUrl;
	
	/**
	 * 파일 업로드 
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value="/ckImageUpload.do",method = RequestMethod.POST)
	public void uploadProcess(MultipartHttpServletRequest req, HttpServletResponse response) throws IOException {
	    //servletContext = req.getSession().getServletContext();
		PrintWriter printWriter = null;
		
		Iterator<String> filenames = req.getFileNames();
		
		while(filenames.hasNext()) {
			String fileName = filenames.next();
			System.out.println(fileName);
			MultipartFile multipartFile = req.getFile(fileName);
			
			//날짜별로 폴더를 만들어 관리한다.
		    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMdd", Locale.KOREA );
		    Date currentTime = new Date();
		    String mTime = mSimpleDateFormat.format ( currentTime );
		    
		    String fileId = String.valueOf(UUID.randomUUID().toString().replaceAll("-", ""));
		    String usrUploadDir = mTime;
		    String originalFileName = multipartFile.getOriginalFilename();
		    String targetFileName = String.valueOf(UUID.randomUUID().toString().replaceAll("-", ""));   
		    String targetPath = basePath + usrUploadDir;
		     
		    File targetPathDir = new File(targetPath);
		    if(!targetPathDir.exists()) targetPathDir.mkdir();
		    File savedFilePath = new File(targetPathDir + "/" + targetFileName + "." + FilenameUtils.getExtension(originalFileName));
		    
		     
		    multipartFile.transferTo(savedFilePath);
		    
		    FileUpload fileUpload = new FileUpload();
		    fileUpload.setFileId(fileId);
		    fileUpload.setFileStoredName(targetFileName);
		    fileUpload.setFilePath(savedFilePath.getPath());
		    fileUpload.setFileOriginalName(originalFileName);
		    fileUpload.setFileContentType(multipartFile.getContentType());
		    fileUpload.setRegisterId("admin");
		    fileUpload.setRegisterName("admin");
		    fileUpload.setFileSize(multipartFile.getSize()+"");
		    
		    fileUploadService.createFile(fileUpload);
		    
		    // 한글 처리를 위한 response 설정
		    response.setContentType("text/html;charset=UTF-8");
		    response.setHeader("Cache-Control", "no-chche");
		    
		    try {
		    	String callback = req.getParameter("CKEditorFuncNum");
			    System.out.println("callback:" + callback);
			    printWriter = response.getWriter();
			    String serverPath = "http://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath();
			    String imagePath = serverPath + downloadImageUrl + fileId;
			    printWriter
			    .println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
			    + callback
			    + ",'"
			    + imagePath
			    + "',''"
			    + ")</script>");
			    printWriter.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				printWriter.close();
			}
		    
		    
		}
	}
	
	@RequestMapping(value = "/showImge.do")
	public void showImage(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		List<String> fileList = new ArrayList<String>();
		
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String filePath = "C:\\Weekend-Project\\fileupload\\20140617";
			out = response.getWriter();
			
			File file = new File(filePath);
			
			String callback = request.getParameter("CKEditorFuncNum");
			out.println("<script type='text/javascript' src='../ckeditor/ckeditor.js'></script>");
			out.println("<script>");
			out.println("function choose(obj){");
			out.println("window.opener.CKEDITOR.tools.callFunction(" + callback
			+ ",obj)");
			out.println("window.close();");
			out.println("}");
			out.println("</script>");
			out.println("<h2>单击图片进行选择</h2>");
			
			if (file.exists()) {
				File[] files = file.listFiles();
				for (File file2 : files) {
					fileList.add(file2.getName());
					String fileName = file2.getName();
					fileName = "../upload/" + fileName;
					out.println("<img src='" + fileName + "' onclick=\""
					+ "choose('" + fileName + "')\">");
					out.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return;
	}
	
	/**
	 * 파일 다운로드
	 * 
	 * @return ModelAndView
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/downloadFile.do", method = RequestMethod.GET)
	public void downloadFile(@RequestParam(value="fileId", required=true) String fileId, HttpServletResponse response) throws UnsupportedEncodingException {
		
		FileUpload fileInfo = fileUploadService.getFileInfo(fileId);
		
		response.setContentType(fileInfo.getFileContentType());
		response.setContentLength(Integer.parseInt(fileInfo.getFileSize()));
		response.setHeader("Content-Disposition",
                "attachment; filename=\"" + URLEncoder.encode(fileInfo.getFileOriginalName(), "UTF-8") + "\"");
		
		FileInputStream fis = null;
		
		try {
			File file = new File(fileInfo.getFilePath());
            fis = new FileInputStream(file);

            IOUtils.copy(fis, response.getOutputStream());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fis);
        }

	}
}
