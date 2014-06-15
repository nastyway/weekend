<%@ include file="/base/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/base/common/error.jsp"
	pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">Weekend</a>
  </div>
  <div class="navbar-collapse collapse navbar-responsive-collapse">
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Administrator</a></li>
      <li><a href="<c:url value='/board/retrieveBoardItemList.do?boardId=2&pageIndex=1'/>">Lesson</a></li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Board <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a href="<c:url value='/board/retrieveBoardItemList.do?boardId=1&pageIndex=1'/>">News Board</a></li>
          <li><a href="<c:url value='/board/retrieveBoardItemList.do?boardId=3&pageIndex=1'/>">Workshop Board</a></li>
          <li><a href="<c:url value='/board/retrieveBoardItemList.do?boardId=4&pageIndex=1'/>">Interview Board</a></li>
          <!-- <li class="divider"></li> -->
        </ul>
      </li>
    </ul>
  </div>
</div>
</body>
</html>