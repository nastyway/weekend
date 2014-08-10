package com.nastyway.weekend.board.restful.resources;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nastyway.common.model.Head;
import com.nastyway.weekend.board.model.BoardItem;
import com.nastyway.weekend.board.model.SearchBoardCondition;
import com.nastyway.weekend.board.restful.model.BoardParam;
import com.nastyway.weekend.board.restful.model.boarditemlist.BoardItemListBody;
import com.nastyway.weekend.board.restful.model.boarditemlist.BoardItemListHead;
import com.nastyway.weekend.board.restful.model.boarditemlist.BoardItemListReturnData;
import com.nastyway.weekend.board.restful.model.boarditemlist.BoardItemListReturnData1;
import com.nastyway.weekend.board.restful.model.mainlist.MainListBody;
import com.nastyway.weekend.board.restful.model.mainlist.MainListHead;
import com.nastyway.weekend.board.restful.model.mainlist.MainListReturnData;
import com.nastyway.weekend.board.restful.model.mainlist.MainListReturnData1;
import com.nastyway.weekend.board.restful.model.mainlist.MainListReturnData2;
import com.nastyway.weekend.board.service.BoardItemService;
import com.nastyway.weekend.fileupload.model.FileMapping;

@RestController
@RequestMapping("/rest/board")
public class BoardRestful {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardRestful.class);
	
	@Value("${fileupload.download.image}")
	private String downloadImagePath;
	
	@Value("${weekend.baseUrl}")
	private String weekendBaseUrl;
	
	@Autowired
	private BoardItemService boardItemService;
	
	@RequestMapping(value = "/retrieveMainList")
	public MainListHead retrieveRecentNewsList() throws Exception{
		
		Head head = null;
		MainListHead result = null; 
		MainListBody body = null;
		List<MainListReturnData> headerReturnList = new ArrayList<MainListReturnData>();
		List<MainListReturnData1> mainReturnList = new ArrayList<MainListReturnData1>();
		List<MainListReturnData2> interviewReturnList = new ArrayList<MainListReturnData2>();
		
		SearchBoardCondition searchBoardCondition = new SearchBoardCondition();
		searchBoardCondition.setPageSize(6);
		searchBoardCondition.setPageIndex(1);
		
		//����� �� �Խù� ��������
		searchBoardCondition.setBoardId("5");
		List<BoardItem> headerResultData = boardItemService.listBoardItem(searchBoardCondition);
		
		//�����, ������ ��ũ��
		searchBoardCondition.setBoardId("1,3");
		List<BoardItem> mainResultData = boardItemService.listBoardItem(searchBoardCondition);
		
		//����� ���ͺ�.
		searchBoardCondition.setBoardId("4");
		List<BoardItem> interviewResultData = boardItemService.listBoardItem(searchBoardCondition);
		
		if(headerResultData!=null) {
			for(BoardItem entity : headerResultData) {
				MainListReturnData returnData = new MainListReturnData();
				
				returnData.setItemId(entity.getItemId());
				returnData.setBoardId(entity.getBoardId());
				returnData.setTitle(entity.getTitle());
				returnData.setContents(entity.getContents());
				returnData.setHitCount(entity.getHitCount());
				returnData.setStartDate(entity.getStartDate());
				returnData.setEndDate(entity.getEndDate());
				returnData.setAttachedFileCount(entity.getAttachedFileCount());
				returnData.setRegisterId(entity.getRegisterId());
				returnData.setRegisterName(entity.getRegisterName());
				returnData.setRegisterDate(entity.getRegisterDate());
				
				//�̹����� �ϳ��� �� ���̴�.
				if(entity.getAttachFiles()!=null && entity.getAttachFiles().size()>0) {
					for(FileMapping file : entity.getAttachFiles()) {
						returnData.setUploadedImgUrl(weekendBaseUrl+downloadImagePath+file.getFileId());
					}
				}
				
				headerReturnList.add(returnData);
			}
		}
		if(mainResultData!=null) {
			for(BoardItem entity : mainResultData) {
				MainListReturnData1 returnData = new MainListReturnData1();
				
				returnData.setItemId(entity.getItemId());
				returnData.setBoardId(entity.getBoardId());
				returnData.setTitle(entity.getTitle());
				returnData.setContents(entity.getContents());
				returnData.setHitCount(entity.getHitCount());
				returnData.setStartDate(entity.getStartDate());
				returnData.setEndDate(entity.getEndDate());
				returnData.setAttachedFileCount(entity.getAttachedFileCount());
				returnData.setRegisterId(entity.getRegisterId());
				returnData.setRegisterName(entity.getRegisterName());
				returnData.setRegisterDate(entity.getRegisterDate());
				
				//�̹����� �ϳ��� �� ���̴�.
				if(entity.getAttachFiles()!=null && entity.getAttachFiles().size()>0) {
					for(FileMapping file : entity.getAttachFiles()) {
						returnData.setUploadedImgUrl(weekendBaseUrl+downloadImagePath+file.getFileId());
					}
				}
				
				mainReturnList.add(returnData);
			}
		}
		if(interviewResultData!=null) {
			for(BoardItem entity : interviewResultData) {
				MainListReturnData2 returnData = new MainListReturnData2();
				
				returnData.setItemId(entity.getItemId());
				returnData.setBoardId(entity.getBoardId());
				returnData.setTitle(entity.getTitle());
				returnData.setContents(entity.getContents());
				returnData.setHitCount(entity.getHitCount());
				returnData.setStartDate(entity.getStartDate());
				returnData.setEndDate(entity.getEndDate());
				returnData.setAttachedFileCount(entity.getAttachedFileCount());
				returnData.setRegisterId(entity.getRegisterId());
				returnData.setRegisterName(entity.getRegisterName());
				returnData.setRegisterDate(entity.getRegisterDate());
				
				//�̹����� �ϳ��� �� ���̴�.
				if(entity.getAttachFiles()!=null && entity.getAttachFiles().size()>0) {
					for(FileMapping file : entity.getAttachFiles()) {
						returnData.setUploadedImgUrl(weekendBaseUrl+downloadImagePath+file.getFileId());
					}
				}
				
				interviewReturnList.add(returnData);
			}
		}
		
		head = new Head("0","Okay");
		body = new MainListBody(headerReturnList, mainReturnList,interviewReturnList);
		result = new MainListHead(head,body);
		
		return result;
		
	}
	
	@RequestMapping(value = "/retrieveBoardItemList")
	public BoardItemListHead retrieveBoardItemList(@RequestBody BoardParam param) throws Exception{
		
		Head head = null;
		BoardItemListHead result = null; 
		BoardItemListBody body = null;
		
		List<BoardItemListReturnData> returnDataList = new ArrayList<BoardItemListReturnData>();
		
		SearchBoardCondition searchBoardCondition = new SearchBoardCondition();
		searchBoardCondition.setPageSize(10);
		searchBoardCondition.setPageIndex(param.getPageIndex());
		
		//����� �� �Խù� ��������
		searchBoardCondition.setBoardId(param.getBoardId());
		List<BoardItem> boardItemList = boardItemService.listBoardItem(searchBoardCondition);
		
		//Set BoardItemList
		if(boardItemList!=null) {
			for(BoardItem entity : boardItemList) {
				BoardItemListReturnData returnData = new BoardItemListReturnData();
				
				returnData.setItemId(entity.getItemId());
				returnData.setBoardId(entity.getBoardId());
				returnData.setTitle(entity.getTitle());
				returnData.setContents(entity.getContents());
				returnData.setHitCount(entity.getHitCount());
				returnData.setStartDate(entity.getStartDate());
				returnData.setEndDate(entity.getEndDate());
				returnData.setRegisterId(entity.getRegisterId());
				returnData.setRegisterName(entity.getRegisterName());
				returnData.setRegisterDate(entity.getRegisterDate());
				returnData.setDate(entity.getDate());
				returnData.setCoordinate(entity.getCoordinate());
				
				//�̹����� �ϳ��� �� ���̴�.
				if(entity.getAttachFiles()!=null && entity.getAttachFiles().size()>0) {
					for(FileMapping file : entity.getAttachFiles()) {
						returnData.setUploadedImgUrl(weekendBaseUrl+downloadImagePath+file.getFileId());
					}
				}
				
				returnDataList.add(returnData);
			}
		}
		
		//Set PageInfo
		BoardItemListReturnData1 returnData1 = new BoardItemListReturnData1();
		
		returnData1.setPageIndex(searchBoardCondition.getPageIndex());
		returnData1.setPageSize(searchBoardCondition.getPageSize());
		returnData1.setTotalItemCount(searchBoardCondition.getTotalItemCount());
		
		head = new Head("0","Okay");
		body = new BoardItemListBody(returnDataList, returnData1);
		result = new BoardItemListHead(head,body);
		
		return result;
	}
	
}
