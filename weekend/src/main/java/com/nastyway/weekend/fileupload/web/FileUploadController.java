package com.nastyway.weekend.fileupload.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nastyway.weekend.fileupload.model.FileUpload;
import com.nastyway.weekend.fileupload.service.FileUploadService;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {
	
	@Value("${fileupload.base.path}")
	private String basePath;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	/**
	 * 파일 업로드 
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value="/uploadFile.do", produces="application/json")
	public FileUpload uploadProcess(MultipartHttpServletRequest req, HttpServletResponse response) throws IOException {
	    //servletContext = req.getSession().getServletContext();
		
		Iterator<String> filenames = req.getFileNames();
		
		while(filenames.hasNext()) {
			System.out.println(filenames.next());
		}
	    MultipartFile multipartFile = req.getFile("file");
	    
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
	    File savedFilePath = new File(targetPathDir + "/" + targetFileName);
	     
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
	    response.setContentType("text/plain;charset=UTF-8");
	    response.setHeader("Cache-Control", "no-chche");
	    
	    return fileUpload;
	     
	}

}
