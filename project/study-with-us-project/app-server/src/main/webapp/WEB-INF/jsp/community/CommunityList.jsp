<%@page import="com.studywithus.domain.Community"%>
<%@page import="java.util.Collection"%>
<%@page import="com.studywithus.dao.CommunityDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 조회</title>

<base target="_self"/>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<link rel="stylesheet" href="${contextPath}/css/bootstrap.css">

<link rel="stylesheet" href="${contextPath}/css/theme.css">

<link rel="stylesheet" href="${contextPath}/css/community/CommunityList.css">

<link rel="stylesheet" href="${contextPath}/css/footer.css">

<style>
table {
table-layout: fixed;
word-break : break-all;
}

.icon {
  width : 15px;
}

ul {
  list-style : none;
}

.icon_td {
  width : 30px;
}

.modal {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal_overlay {
  position:fixed;
  width: 100%;
  height: 100%;
}

input.form_input_title !important{
    margin-top: 150px;
    margin-bottom: 50px;
}

.modal_content {
  text-align : center;
  position: fixed;
  top: 30%;
  left: 40%;
  width: 400px;
  height: 450px;
  background-color: white;
  padding: 30px 0px;
  border-radius: 10px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px
    rgba(0, 0, 0, 0.23);
}

h1 {
  margin: 0;
}

.hidden {
  display: none;
}

.total_page_count {
  text-align : center;
}

.page_count {
  color : black;
  font-size : 15px;
  margin-left : 3px;
}

.add-button {
  border-radius: 10px;
  font-size: 14px;

 }
 
</style>

</head>

<body>
	<div class="container">
		<jsp:include page="../header.jsp"></jsp:include>

			<!-- 검색창 -->
			<div class="search-view">
			<form class="search-form" action="search">
				<input type="search" name="keyword" placeholder="키워드를 검색해주세요." class="search-input">
				<input type="hidden" name="categoryNo" value="${categoryNo}" >
				<button type="submit" class="search-button">
						<img class="reading-glasses-icon" src="${contextPath}/img/search icon.png"></button>
			  </form> 
			</div> <!--검색창-->

		<!-- 글쓰기 버튼 -->
		<div class="add-button">
			<button id="open">글쓰기</button>
		</div>

		<!-- 모달창 -->
		<!--이벤트 발생 시 hidden 삭제-->
		<div class="modal hidden">
			<!--모달 활성화 시 흐린 배경 표현-->
			<div class="modal_overlay">
				<!--모달 화면-->
				<div class="modal_content">
					<div class="form_category_menu">
						<a class="info_box" href='form?no=0'>정보</a> 
						<a class="qa_box" href='form?no=1'>질문</a>
						<a class="talk_box" href='form?no=2'>스몰톡</a>
					</div>
					<div class="form_box">
						<form action='add' target="CommunityList.jsp" method='post'>
							<input type='hidden' name='category' value='${categoryNo}'>
							<input class="form_input_title" type='text' name='title' size=40
								maxlength=30 placeholder="제목을 입력하세요.">
							<textarea class="input_content" name="content" id="textarea"
								cols="40" rows="5"></textarea>
							<div class="form_buttons">
								<input type="submit" onclick="offClick()" value="등록"> 
								<input id="close" type="button" value="취소">
							</div>
						</form>
					</div>
				</div> <!-- modal_content -->
			</div> <!-- modal_overlay -->
		</div> <!-- modal_hidden -->


		<div class="main-wrapper">
			<div class="side_menu">
				<ul class="categorys">
					<li><a class="info_text" href='list?no=0&pageNo=1'>정보 커뮤니티</a><br>
					<li><a class="qa_text" href='list?no=1&pageNo=1'>질문 커뮤니티</a><br>
					<li><a class="talk_text" href='list?no=2&pageNo=1'>스몰톡 커뮤니티</a><br>
				</ul>
			</div>

			<div class="lists">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">카테고리</th>
							<th scope="col">제목</th>
							<th scope="col">작성자</th>
							<th scope="col">등록일</th>
							<th scope="col"> </th>
							<th scope="col"> </th>
							<th scope="col"> </th>
						</tr>
					</thead>

					<c:forEach items="${communityList}" var="community">
						<c:choose>
							<c:when test="${community.category eq 0}">
								<c:set var="type" value="정보" />
							</c:when>

							<c:when test="${community.category eq 1}">
								<c:set var="type" value="질문" />
							</c:when>

							<c:when test="${community.category eq 2}">
								<c:set var="type" value="스몰톡" />
							</c:when>
						</c:choose>
						<tbody>
							<tr>
								<td>${type}</td>
								<td><a href='detail?no=${community.no}'>${community.title}</a></td>
								<td>${community.writer.nickname}</td>
								<td>${community.registeredDate}</td>
								<td class="icon_td">
								  <img class="icon" src="${contextPath}/img/eyeIcon.png">
  							  <span>${community.viewCount}</span>
                </td>
                <td class="icon_td">
								  <img class="icon" src="${contextPath}/img/fillingHeartIcon.png">
								  <span>${community.like}</span>
                </td>
                <td class="icon_td">
								  <img class="icon" src="${contextPath}/img/speechBalloon.png">
  							  <span>${community.commentCount}</span>
                </td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="total_page_count">
		<c:if test="${previousBtn eq 0}">
		<a href='list?no=${categoryNo}&pageNo=${startNo-3}'>previous</a>
		</c:if>
		<c:forEach var="i" begin="${startNo}" end="${endNo}">
		    <a class="page_count" href='list?no=${categoryNo}&pageNo=${i}'>${i}</a>
    </c:forEach>
		
		  <c:if test="${nextBtn eq 1}">
    <a href='list?no=${categoryNo}&pageNo=${startNo + 3}'>next</a>
    </c:if>
		</div>
		  
	<jsp:include page="../footer.jsp"></jsp:include>
	</div> <!--container-->

  <script>
  const openBtn = document.getElementById('open');
  //onModal button
  const closeBtn = document.getElementById('close');
  //offModal button
  const modal = document.querySelector('.modal');
  //HTML에서의 모달 최상위 요소
  const overlay = document.querySelector('.modal_overlay');
  //모달창이 활성화되면 흐린 배경을 표현하는 요소
  
  const openModal = () => {
	     var uid = '<%=session.getAttribute("loginUser")%>';
	    
	    if(uid == 'null') {
	      alert("로그인이 필요합니다.");
	      
	    } else {
    modal.classList.remove('hidden');
	      
  }
  }
  const closeModal = () => {
    modal.classList.add('hidden');
  }
  openBtn.addEventListener('click', openModal);
  //onModal
  closeBtn.addEventListener('click', closeModal);
  //모달창 내부의 닫기 버튼
  //overlay.addEventListener('click', closeModal);
  //모달창 영역 밖
    </script>

	<script>
	let type;
	let box;
	let text;
	
	const changeColor = () => {
	  if (${categoryNo} === 0) {
	    text = document.querySelector('.info_text');
	    box =  document.querySelector('.info_box');
	    text.classList.add('ctry_text_color');
	    box.classList.add('ctry_box_color');
	    
	  } else if (${categoryNo} === 1) {
	    text = document.querySelector('.qa_text');
	    box =  document.querySelector('.qa_box');
	    text.classList.add('ctry_text_color');
	    box.classList.add('ctry_box_color');
	    
	  } else {
	    text = document.querySelector('.talk_text');
	    box =  document.querySelector('.talk_box');
	    text.classList.add('ctry_text_color');
	    box.classList.add('ctry_box_color');
	  }
	}
	
	changeColor();
var trList = document.querySelectorAll("tbody tr"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    window.location.href = e.currentTarget.querySelector("a").href;
  };
});
  </script>
  
</body>
</html>