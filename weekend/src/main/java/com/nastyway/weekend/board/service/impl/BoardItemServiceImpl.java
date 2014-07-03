package com.nastyway.weekend.board.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nastyway.weekend.board.dao.BoardItemDao;
import com.nastyway.weekend.board.model.BoardItem;
import com.nastyway.weekend.board.model.SearchBoardCondition;
import com.nastyway.weekend.board.service.BoardItemService;
import com.nastyway.weekend.fileupload.dao.FileUploadDao;
import com.nastyway.weekend.fileupload.model.FileMapping;
import com.nastyway.weekend.fileupload.model.FileUpload;

@Service("BoardItemService")
public class BoardItemServiceImpl implements BoardItemService {
	
	@Autowired
	private BoardItemDao boardItemDao;
	
	@Autowired
	private FileUploadDao fileUploadDao;

	@Override
	public List<BoardItem> listBoardItem(SearchBoardCondition searchBoardCondition) {
		
		//����Ʈ�� ������ ���Ѵ�.
		int count = boardItemDao.countBoardItem(searchBoardCondition);
		searchBoardCondition.setTotalItemCount(count);
		//�� ������ ������ ���Ѵ�.
		searchBoardCondition.setTotalPageSize((int)Math.ceil(count/(float)searchBoardCondition.getPageSize()));
		//������ ù��° ������ ���Ѵ�.
		searchBoardCondition.setStartPageIndex(searchBoardCondition.getPageSize()*(searchBoardCondition.getPageIndex()-1)+1);
		//������ ������ ������ ���Ѵ�.
		searchBoardCondition.setEndPageIndex(searchBoardCondition.getPageSize()*(searchBoardCondition.getPageIndex()));
		
		if(searchBoardCondition.getBoardId().contains(",")) {
			List<Integer> boardIdList = new ArrayList<Integer>();
			String[] bil = searchBoardCondition.getBoardId().split(",");
			for(String s : bil){
				boardIdList.add(Integer.getInteger(s));
			}
		}
		
		List<BoardItem> listBoardItem = boardItemDao.listBoardItem(searchBoardCondition);
		
		for(BoardItem entity : listBoardItem) {
			List<FileMapping> attachFiles = boardItemDao.getMappedFileInfo(entity.getItemId());
			
			if(attachFiles.size()>0) {
				entity.setAttachFiles(attachFiles);
			}
		}
		
		return listBoardItem;
	}

	@Override
	public BoardItem getBoardItem(BoardItem param) {
		BoardItem item = boardItemDao.getBoardItem(param);
		
		List<FileMapping> attachFiles = boardItemDao.getMappedFileInfo(item.getItemId());
		
		if(attachFiles.size()>0) {
			item.setAttachFiles(attachFiles);
		}
		return item;
	}

	@Override
	public void createBoardItem(Map<String,String> params, List<String> fileIdList) {
		
		boardItemDao.createBoardItem(params);
		
		//÷�������� ������ ���ν�Ų��.
		if(fileIdList.size()>0) {
			for(String s : fileIdList) {
				FileUpload file = new FileUpload();
				file.setFileId(s);
				file.setItemId(params.get("itemId"));
				
				fileUploadDao.createMapping(file);
			}
		}
	}
	
	@Override
	public int deleteBoardItem(BoardItem boardItem) {
		return boardItemDao.deleteBoardItem(boardItem);
	}

	@Override
	public List<BoardItem> listBoardSearchItem(BoardItem boardItem) {
		return null;
	}

	@Override
	public int countListBoardSearchItem(BoardItem boardItem) {
		return 0;
	}

	@Override
	public void updateBoardItem(Map<String, String> params, List<String> fileIdList) {
		boardItemDao.updateBoardItem(params);
		
		//÷�������� ������ ���ν�Ų��.
		if(fileIdList.size()>0) {
			for(String s : fileIdList) {
				FileUpload file = new FileUpload();
				file.setFileId(s);
				file.setItemId(params.get("itemId"));
				
				fileUploadDao.createMapping(file);
			}
		}
		
	}

}
