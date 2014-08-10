package com.nastyway.weekend.board.restful.model.mainlist;

import java.util.List;

public class MainListBody {
	
	public List<MainListReturnData> HeaderResult = null;
	
	public List<MainListReturnData1> NewsResult = null;
	
	public List<MainListReturnData2> InterviewResult = null;
	
	public MainListBody() {
	}
	
	public MainListBody(List<MainListReturnData> HeaderResult,List<MainListReturnData1> NewsResult,List<MainListReturnData2> InterviewResult) {
		this.HeaderResult = HeaderResult;
		this.NewsResult = NewsResult;
		this.InterviewResult = InterviewResult;
	}

}
