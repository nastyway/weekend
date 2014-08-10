package com.nastyway.weekend.fileupload.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nastyway.weekend.board.web.BoardController;
import com.nastyway.weekend.fileupload.model.FileUpload;
import com.nastyway.weekend.fileupload.service.FileUploadService;

@Controller("FileUploadController")
@RequestMapping("/fileupload")
public class FileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Value("${fileupload.base.path}")
	private String basePath;
	
	@Value("${fileupload.download.image}")
	private String downloadImageUrl;
	
	@Value("${fileupload.download.thumbnail.image}")
	private String downloadThumbnailImageUrl;
	
	@Value("${fileupload.download.delete.image}")
	private String deleteImageUrl;
	
	@Value("${weekend.baseUrl}")
	private String weekendBaseUrl;
	
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
			
		    String fileId = String.valueOf(UUID.randomUUID().toString().replaceAll("-", ""));
		    String originalFileName = multipartFile.getOriginalFilename();
		    String targetFileName = String.valueOf(UUID.randomUUID().toString().replaceAll("-", ""));   
		    String targetPath = getFileTargetPath();
		     
		    File targetPathDir = new File(targetPath);
		    if(!targetPathDir.exists()) targetPathDir.mkdir();
		    File savedFilePath = new File(targetPathDir + targetFileName + "." + FilenameUtils.getExtension(originalFileName));
		    
		     
		    multipartFile.transferTo(savedFilePath);
		    
		    FileUpload fileUpload = new FileUpload();
		    fileUpload.setFileId(fileId);
		    fileUpload.setFileStoredName(targetFileName);
		    fileUpload.setFilePath(targetPath);
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
                "attachment; filename=\"" + fileInfo.getFileOriginalName() + "\"");
		
		FileInputStream fis = null;
		
		try {
			File file = new File(fileInfo.getFilePath()+fileInfo.getFileStoredName());
            fis = new FileInputStream(file);

            IOUtils.copy(fis, response.getOutputStream());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fis);
        }

	}
	
	/**
	 * 썸네일 다운로드
	 * 
	 * @return ModelAndView
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/downloadThumbnailImage/{fileId}", method = RequestMethod.GET)
	public void downloadThumbnailImage(@PathVariable String fileId, HttpServletResponse response) throws UnsupportedEncodingException {
		
		FileUpload fileInfo = fileUploadService.getFileInfo(fileId);
		
		response.setContentType(fileInfo.getFileContentType());
		response.setContentLength(Integer.parseInt(fileInfo.getThumbnailSize()));
		response.setHeader("Content-Disposition",
                "attachment; filename=\"" + fileInfo.getFileOriginalName() + "\"");
		
		FileInputStream fis = null;
		
		try {
			File file = new File(fileInfo.getFilePath()+fileInfo.getThumbnailName());
            fis = new FileInputStream(file);

            IOUtils.copy(fis, response.getOutputStream());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fis);
        }

	}
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        logger.debug("uploadPost called");
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf;
        List<FileUpload> list = new LinkedList<>();
        
        while (itr.hasNext()) {
            mpf = request.getFile(itr.next());
            logger.debug("Uploading {}", mpf.getOriginalFilename());
            
            String fileId = String.valueOf(UUID.randomUUID().toString().replaceAll("-", ""));
            String newFilenameBase = UUID.randomUUID().toString().replaceAll("-", "");
            String originalFileExtension = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf("."));
            String newFilename = newFilenameBase + originalFileExtension;
            String contentType = mpf.getContentType();
            
            File newFile = new File(getFileTargetPath() + newFilename);
            try {
                mpf.transferTo(newFile);
                
                BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile), 290);
                String thumbnailFilename = newFilenameBase + "-thumbnail.png";
                File thumbnailFile = new File(getFileTargetPath() + thumbnailFilename);
                ImageIO.write(thumbnail, "png", thumbnailFile);
                
                FileUpload fileUpload = new FileUpload();
    		    fileUpload.setFileId(fileId);
    		    fileUpload.setFileStoredName(newFilename);
    		    fileUpload.setFilePath(getFileTargetPath());
    		    fileUpload.setFileOriginalName(mpf.getOriginalFilename());
    		    fileUpload.setThumbnailName(thumbnailFilename);
    		    fileUpload.setThumbnailSize(thumbnailFile.length()+"");
    		    fileUpload.setFileContentType(contentType);
    		    fileUpload.setRegisterId("admin");
    		    fileUpload.setRegisterName("admin");
    		    fileUpload.setFileSize(mpf.getSize()+"");
    		    
    		    fileUploadService.createFile(fileUpload);
    		    
    		    list.add(fileUpload);
            } catch(IOException e) {
                logger.error("Could not upload file "+mpf.getOriginalFilename(), e);
            }
        }
        
        Map<String, Object> files = new HashMap<>();
        files.put("files", list);
        return files;
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public @ResponseBody List<Map<String, Object>> delete(@PathVariable String id) {
        FileUpload fileUpload = fileUploadService.getFileInfo(id);
        
        File imageFile = new File(getFileTargetPath() +fileUpload.getFileStoredName());
        logger.debug(imageFile.getPath());
        imageFile.delete();
        File thumbnailFile = new File(getFileTargetPath()+fileUpload.getThumbnailName());
        thumbnailFile.delete();
        
        fileUploadService.deleteFile(id);
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        Map<String, Object> success = new HashMap<String, Object>();
        success.put("success", true);
        results.add(success);
        return results;
    }
    
    @RequestMapping(value = "/retrieveFileList/{itemId}", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> list(@PathVariable String itemId) {
        List<FileUpload> list = fileUploadService.retrieveFileList(itemId);
        for(FileUpload image : list) {
            image.setUrl(weekendBaseUrl+downloadImageUrl+image.getFileId());
            image.setThumbnailUrl(weekendBaseUrl+downloadThumbnailImageUrl+image.getFileId());
            image.setDeleteUrl(weekendBaseUrl+deleteImageUrl+image.getFileId());
            image.setDeleteType("DELETE");
        }
        Map<String, Object> files = new HashMap<String, Object>();
        files.put("files", list);
        logger.debug("Returning: {}", files);
        return files;
    }
    
    public String getFileTargetPath() {
    	//날짜별로 폴더를 만들어 관리한다.
	    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMdd", Locale.KOREA );
	    Date currentTime = new Date();
	    String mTime = mSimpleDateFormat.format ( currentTime );
	    String usrUploadDir = mTime;
	    String targetPath = basePath + usrUploadDir+"/";
	     
	    File targetPathDir = new File(targetPath);
	    if(!targetPathDir.exists()) targetPathDir.mkdir();
	    
	    return targetPath;
    }
}
