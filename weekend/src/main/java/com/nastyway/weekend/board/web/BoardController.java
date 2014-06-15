package com.nastyway.weekend.board.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.nastyway.weekend.board.model.Board;
import com.nastyway.weekend.board.model.BoardItem;
import com.nastyway.weekend.board.model.SearchBoardCondition;
import com.nastyway.weekend.board.service.BoardItemService;
import com.nastyway.weekend.board.service.BoardService;
import com.nastyway.weekend.fileupload.model.FileMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Value("${fileupload.download.image}")
	private String downloadImagePath;
	
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
			@RequestParam("pageIndex") int pageIndex, 
			@RequestParam(value="searchWord", required = false) String searchWord) throws Exception {
		
		ModelAndView mav = new ModelAndView("board/retrieveBoardItemList");
		
		Board board = boardService.getBoard(boardId);
		
		//없는 게시판이면 에러 ㅋ
		if(board==null) {
			mav.setView(new RedirectView("/weekend/base/common/error.jsp"));
		} else {
			SearchBoardCondition searchBoardCondition = new SearchBoardCondition();
			searchBoardCondition.setBoardId(boardId);
			searchBoardCondition.setSearchWord(searchWord);
			searchBoardCondition.setPageSize(10);
			searchBoardCondition.setPageIndex(pageIndex);
			
			List<BoardItem> result = boardItemService.listBoardItem(searchBoardCondition);
			
			mav.addObject("searchCondition", searchBoardCondition);
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
	public ModelAndView retrieveBoardItemDetail(@RequestParam("boardId") String boardId, @RequestParam("itemId") String itemId) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		BoardItem param = new BoardItem();
		param.setBoardId(boardId);
		param.setItemId(itemId);
		
		BoardItem result = boardItemService.getBoardItem(param);
		
		List<FileMapping> fileList = result.getAttachFiles();
		if(fileList.size()>0) {
			
			List<String> uploadedImageUrl = new ArrayList<String>();
			for(FileMapping file : fileList) {
//				uploadedImageUrl.add(downloadImagePath+file.getFileId());
			}
			
			result.setUploadedImagesUrl(uploadedImageUrl);
		}
		
		mav.addObject("result", result);
		
		//boardId에 따라 뷰페이지 세팅을 달리한다.
		// 1 뉴스 게시판, 2 레슨 게시판, 3 워크샵 게시판
		if("1".equals(boardId)) {
			mav.setViewName("board/retrieveNewsBoardItemDetail");
		} else if("2".equals(boardId)) {
			mav.setViewName("board/retrieveLessonBoardItemDetail");
		} else if("3".equals(boardId)) {
			mav.setViewName("board/retrieveWorkshopBoardItemDetail");
		}
		
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
}
