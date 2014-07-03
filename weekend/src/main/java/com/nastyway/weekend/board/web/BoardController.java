package com.nastyway.weekend.board.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.nastyway.weekend.board.model.Board;
import com.nastyway.weekend.board.model.BoardItem;
import com.nastyway.weekend.board.model.SearchBoardCondition;
import com.nastyway.weekend.board.service.BoardItemService;
import com.nastyway.weekend.board.service.BoardService;
import com.nastyway.weekend.fileupload.model.FileMapping;
import com.nastyway.weekend.user.model.User;

@Controller("BoardController")
@RequestMapping("/board")
public class BoardController {

	@Value("${fileupload.download.image}")
	private String downloadImagePath;
	
	@Value("${fileupload.download.thumbnail.image}")
	private String downloadThumbnailPath;
	
	@Value("${weekend.baseUrl}")
	private String weekendBaseUrl;
	
	@Autowired
	private BoardItemService boardItemService;
	
	@Autowired
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	/**
	 * 게시글 목록 조회
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/retrieveBoardItemList.do")
	public ModelAndView listBoardItemView(@RequestParam("boardId") String boardId, 
			@ModelAttribute SearchBoardCondition searchCondition) throws Exception {
		
		ModelAndView mav = new ModelAndView("board/retrieveBoardItemList");
		
		Board board = boardService.getBoard(boardId);
		
		//없는 게시판이면 에러 ㅋ
		if(board==null) {
			mav.setView(new RedirectView("/weekend/base/common/error.jsp"));
		} else {
			List<BoardItem> result = boardItemService.listBoardItem(searchCondition);
			
			mav.addObject("searchCondition", searchCondition);
			mav.addObject("boardId", boardId);
			mav.addObject("result", result);
		}
		
		return mav;
		
	}
	
	/**
	 * 게시글 상세 조회 
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/retrieveBoardItemDetail.do")
	public ModelAndView retrieveBoardItemDetail(@RequestParam("boardId") String boardId, @RequestParam("itemId") String itemId, @RequestParam("pageIndex") String pageIndex) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		BoardItem param = new BoardItem();
		param.setBoardId(boardId);
		param.setItemId(itemId);
		
		BoardItem result = boardItemService.getBoardItem(param);
		
		List<FileMapping> fileList = result.getAttachFiles();
		if(fileList!=null) {
			
			List<String> uploadedImageUrl = new ArrayList<String>();
			List<String> uploadedThumbnailUrl = new ArrayList<String>();
			for(FileMapping file : fileList) {
				uploadedImageUrl.add(weekendBaseUrl+downloadImagePath+file.getFileId());
				uploadedThumbnailUrl.add(weekendBaseUrl+downloadThumbnailPath+file.getFileId());
			}
			
			result.setUploadedImagesUrl(uploadedImageUrl);
			result.setUploadedThumbnailUrl(uploadedThumbnailUrl);
		}
		
		mav.addObject("boardItem", result);
		mav.addObject("pageIndex", pageIndex);
		mav.setViewName("board/retrieveBoardItemDetail");
		
		return mav;
		
	}
	
	/**
	 * 게시글 등록 화면 
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/createBoardItemForm.do")
	public ModelAndView createBoardItemView(@RequestParam("boardId") String boardId) throws Exception {
		
		BoardItem boardItem = new BoardItem(); 
		
		ModelAndView mav = new ModelAndView();
		
		boardItem.setBoardId(boardId);
		
		mav.addObject("boardItem",boardItem);
		
		mav.setViewName("board/createBoardItemForm");
		
		return mav;
		
	}
	
	/**
	 * 게시글 등록
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/createBoardItem.do")
	public String createBoardItem(MultipartHttpServletRequest request) throws Exception {
		
		Map<String, String> params = new HashMap<String,String>();
		List<String> fileIdList = new ArrayList<String>();
		
		//넘어온 FILE ID들과 INPUT 값들을 담고,
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			
			if("fileIdList[]".equals(paramName)){
				for(String s : request.getParameterValues(paramName)){
					fileIdList.add(s);
					logger.debug("fileId : "+s);
				}
			} else {
				params.put(paramName, request.getParameter(paramName));
			}
			logger.debug("paramName : "+paramName+", value : "+ request.getParameter(paramName));
		}
		
		//작성자를 등록한다.
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		params.put("registerId",user.getUserId());
		params.put("registerName",user.getUserName());
		
		//item id를 등록한다.
		String itemId = UUID.randomUUID().toString().replaceAll("-", "");
		params.put("itemId",itemId);
		
		boardItemService.createBoardItem(params, fileIdList);
		
		return "redirect:/board/retrieveBoardItemDetail.do?boardId="+params.get("boardId")+"&itemId="+params.get("itemId");
		
	}
	
	/**
	 * 게시글 수정 화면 
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/updateBoardItemForm.do")
	public ModelAndView updateBoardItemForm(@RequestParam("boardId") String boardId, @RequestParam("itemId") String itemId) throws Exception {
		
		BoardItem param = new BoardItem();
		param.setBoardId(boardId);
		param.setItemId(itemId);
		
		BoardItem boardItem = boardItemService.getBoardItem(param);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("boardItem",boardItem);
		mav.setViewName("board/updateBoardItemForm");
		
		return mav;
		
	}
	
	/**
	 * 게시글 수정
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/updateBoardItem.do")
	public String updateBoardItem(MultipartHttpServletRequest request) throws Exception {
		
		Map<String, String> params = new HashMap<String,String>();
		List<String> fileIdList = new ArrayList<String>();
		
		//넘어온 FILE ID들과 INPUT 값들을 담고,
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			
			if("fileIdList[]".equals(paramName)){
				for(String s : request.getParameterValues(paramName)){
					fileIdList.add(s);
					logger.debug("fileId : "+s);
				}
			} else {
				params.put(paramName, request.getParameter(paramName));
			}
			logger.debug("paramName : "+paramName+", value : "+ request.getParameter(paramName));
		}
		
		//작성자를 등록한다.
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("userInfo");
//		params.put("updateId",user.getUserId());
//		params.put("updateName",user.getUserName());
		
		boardItemService.updateBoardItem(params, fileIdList);
		
		return "redirect:/board/retrieveBoardItemDetail.do?boardId="+params.get("boardId")+"&itemId="+params.get("itemId");
		
	}
	
	/**
	 * 게시글 삭제
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/deleteBoardItem.do")
	public String deleteBoardItem(@RequestParam("boardId") String boardId, @RequestParam("itemId") String itemId) {
		
		logger.info("---------------------------deleteBoardItem.do---------------------------");

		String redirectUrl = null;
		BoardItem boardItem = new BoardItem();
		boardItem.setBoardId(boardId);
		boardItem.setItemId(itemId);
		
		try {
			int result = boardItemService.deleteBoardItem(boardItem);

			if(result>0) {
				redirectUrl = "redirect:/board/retrieveBoardItemList.do?boardId="+boardId+"&pageIndex=1";
			} else {
				redirectUrl = "redirect:/board/retrieveBoardItemList.do?boardId="+boardId+"&pageIndex=1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return redirectUrl;
		
	}
}
