<%@page import="com.studywithus.domain.Study"%>
<%@page import="com.studywithus.dao.StudyDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>스터디위더스 : 스터디상세보기</title>

<base target="_self"/>

    <link rel="stylesheet" href="${contextPath}/css/theme.css">
    <link rel="stylesheet" href="${contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${contextPath}/css/study/StudyDetail.css">
	<link rel="stylesheet" href="${contextPath}/css/footer.css">
</head>

  <style type="text/css"> 
    .interest_icon {
      width : 50px;
    }
  </style>

<body>
	<div class="container">
		<jsp:include page="../header.jsp"></jsp:include>

		<header class="freepagetop">

		<div class="study-form-center">
			<form class = "study-form-center" action="detail">
				<fieldset class="menu">

				        <c:choose>
        <c:when test="${freeStudy.studyStatus eq 0}">
      <c:set var="type" value="모집중"/>
        </c:when>
        </c:choose>

        <c:choose>
        <c:when test="${freeStudy.studyStatus eq 1}">
      <c:set var="type" value="진행중"/>
        </c:when>
        </c:choose>

        <c:choose>
        <c:when test="${freeStudy.studyStatus eq 2}">
      <c:set var="type" value="진행완료"/>
        </c:when>
        </c:choose>
        
     <c:choose>
   <c:when test="${freeStudy.onOffLine eq 0}">
 <c:set var="type2" value="온라인"/>
   </c:when>
   </c:choose>

   <c:choose>
   <c:when test="${freeStudy.onOffLine eq 1}">
 <c:set var="type2" value="오프라인"/>
   </c:when>
   </c:choose>
   
					<span class="study-top-status-2">
						<h1 class="study-content-category">
							스터디 상세보기 <input class="input5" id='f-status' type='text'
								name='status' value='${type}' readonly>
							<span class="study-registered-date" id='f-registeredDate'>${freeStudy.registeredDate}</span>
						</h1>
					</span>


					<hr class="study-hr">

					<div class="icon-form-group" align:right;>
						<ul class="uldesign">
							<img class="icon-top" src="${contextPath}/img/category.png">
							<input class="input3" id='f-category' type='text' name='category' value='${freeStudy.category}' readonly>

							<img class="icon-top" src="${contextPath}/img/onlineIcon.png">
							<input class="input3" id='f-onOffLine' type='number' name='onOffLine' value='${type2}' readonly>

							<img class="icon-top" src="${contextPath}/img/area.png">
							<input class="input3" id='f-area' type='text' name='area' value='${freeStudy.area}' readonly>

							<img class="icon-top" src="${contextPath}/img/people.png">
							<input class="input3" id='f-members/maxMembers' type='text' name='members/maxMembers' value='${freeStudy.members} / ${freeStudy.maxMembers}' readonly>

						</ul>
					</div>

					<!--
        <div class="form-group">
          <label for='f-no'>번호</label> 
          <input id='f-no' type='text' name='no' value='${freeStudy.no}' readonly><br>
        </div>
      -->

		<div class="form-group">
			<label for='f-title'>제목</label>
			 <input id='f-title' type='text' name='title' value='${freeStudy.title}' readonly>
		</div>

		<div class="form-group">
			<label for='f-name'>작성자</label> 
			<input id='f-name' type='text' name='name' value='${freeStudy.writer.nickname}' readonly>
		</div>

		<div class="form-group">
			<label class="study-content2" for='f-content'>내용</label> 
			<input class="study-content-box" id='f-content' type='text'
				name='content' cols="69" rows="10" value='${freeStudy.content}' readonly>
		</div>

		<div class="form-group">
			<label for='f-startDate'>시작일</label> 
			<input id='f-startDate' type='date' name='startDate' value='${freeStudy.startDate}' readonly>
		</div>

		<div class="form-group">
			<label for='f-endDate'>종료일</label>
			<input id='f-endDate' type='date' name='endDate' value='${freeStudy.endDate}' readonly>
		</div>

		<!--
        <div class="form-group">
          <label for='f-category'>카테고리</label> 
          <input id='f-category' type='text' name='category' value='${freeStudy.category}' readonly>
        </div>
        
        <div class="form-group">
          <label for='f-status'>스터디 진행상태</label> 
          <input id='f-status' type='text' name='status' value='${freeStudy.studyStatus}' readonly>
        </div>
        
        <div class="form-group">
          <label for='f-area'>지역</label> 
          <input id='f-area' type='text' name='area' value='${freeStudy.area}' readonly>
        </div>
        
        <div class="form-group">
          <label for='f-onOffLine'>온오프라인</label> 
          <input id='f-onOffLine' type='number' name='onOffLine' value='${freeStudy.onOffLine}' readonly>
        </div>

        <div class="form-group">
          <label for='f-viewCount'>조회수</label> 
          <input id='f-viewCount' type='text' name='viewCount' value='${freeStudy.viewCount}' readonly>
        </div>
        
        <div class="form-group">
          <label for='f-likes'>좋아요</label> 
          <input id='f-likes' type='text' name='likes' value='${freeStudy.likes}' readonly>
        </div>
  
        <div class="form-group">
          <label for='f-maxMembers'>모집인원</label> 
          <input id='f-maxMembers' type='number' name='maxMembers' value='${freeStudy.maxMembers}' readonly>
        </div>
        
        <div class="form-group">
          <label for='f-members'>현재인원</label> 
          <input id='f-members' type='number' name='members' value='${freeStudy.members}' readonly>
        </div>
      -->

	  <!--
		  <section class="study-info-icon">
			  <div class="item2">
				  <div class="info_item">
					  <img class="icon" src="../img/fillingHeartIcon.png">
					  <p class="icon_count">${freeStudy.likes}</p>
					  
					  <img class="icon" src="../img/eyeIcon.png">
					  <p class="icon_count">${freeStudy.viewCount}</p>
					</div>
				</div>
			</section>
		-->

	<!-- 관심목록 추가/삭제 아이콘 출력 -->
    <!-- 글쓴이(0) = 비회원 -->
    <section class="study-info-icon">
      <div class="item2">
        <c:if test="${loginUser eq null}">
            <div class="info_item">
            <img class="icon" src="${contextPath}/img/fillingHeartIcon.png">
            <p class="icon_count">${freeStudy.likes}</p> 
            
            <img class="icon" src="${contextPath}/img/eyeIcon.png">
            <p class="icon_count">${freeStudy.viewCount}</p>
          </div> <!--info_item-->
        </c:if> <!--글쓴이(0) 비회원-->
      </div> <!--item2-->
    </section>

		
		<section class="study-info-icon">
			<div class="item2">
				<!-- 회원(1) = 작성자-->
				<c:if test="${freeStudy.writer.no eq loginUser.no}">
					<!-- 관심목록 추가 전 상태인 경우 추가 버튼 출력 -->
						<c:if test="${result eq 0}">
							<div class="info_item">
								<img class="icon" src="${contextPath}/img/fillingHeartIcon.png">
								<p class="icon_count">${freeStudy.likes}</p> 
								
								<img class="icon" src="${contextPath}/img/eyeIcon.png">
								<p class="icon_count">${freeStudy.viewCount}</p>

								<a href='interest/add?no=${freeStudy.no}'><img class="icon" src="${contextPath}/img/interestAdd.png"></a>
								<p class="icon_count">${freeStudy.likes}</p> 
							</div> <!--info_item-->
						</c:if> <!-- 글쓴이(1) 관심목록 추가 전 -->
				
						<!-- 관심목록 추가 상태인 경우 삭제 버튼 출력 -->
						<c:if test="${result eq 1}">
							<div class="info_item">
								<img class="icon" src="${contextPath}/img/fillingHeartIcon.png">
								<p class="icon_count">${freeStudy.likes}</p> 
								
								<img class="icon" src="${contextPath}/img/eyeIcon.png">
								<p class="icon_count">${freeStudy.viewCount}</p>

								<a href='interest/delete?no=${freeStudy.no}'><img class="icon" src="${contextPath}/img/interestDelete.png"></a>
								<p class="icon_count">${freeStudy.likes}</p> 
							</div> <!--info_item-->
						</c:if> <!-- 글쓴이(1) 관심목록 추가 상태인 경우 -->
				</c:if> <!--checkWriter eq 1-->
			</div> <!-- item2 -->
	  	</section> <!--  회원(1) = 작성자 -->
							
							
		<section class="study-info-icon">
      <div class="item2">
        <!-- 회원(2) != 작성자-->
		    <c:if test="${freeStudy.writer.no ne loginUser.no  && loginUser ne null}">

          <!-- 관심목록 추가 전 상태인 경우 추가 버튼 출력 -->
          <c:if test="${result eq 0}">
            <div class="info_item">
              <img class="icon" src="${contextPath}/img/fillingHeartIcon.png">
                <p class="icon_count">${freeStudy.likes}</p> 
                
                <img class="icon" src="${contextPath}/img/eyeIcon.png">
                <p class="icon_count">${freeStudy.viewCount}</p>

                <a href='interest/add?no=${freeStudy.no}'><img class="icon" src="${contextPath}/img/interestAdd.png"></a>
                <p class="icon_count">${freeStudy.likes}</p> 
              </div> <!--info_item-->
            </c:if> <!-- 회원(2) 관심목록 추가 전 -->
            
		<!-- 관심목록 추가 상태인 경우 삭제 버튼 출력 -->
			<c:if test="${result eq 1}">
				<div class="info_item">
					<img class="icon" src="${contextPath}/img/fillingHeartIcon.png">
					<p class="icon_count">${freeStudy.likes}</p> 
								
					<img class="icon" src="${contextPath}/img/eyeIcon.png">
					<p class="icon_count">${freeStudy.viewCount}</p>

					<a href='interest/delete?no=${freeStudy.no}'><img class="icon" src="${contextPath}/img/interestDelete.png"></a>
				</div> <!--info_item-->
			</c:if> <!-- 회원(2) 관심목록 추가 상태인 경우 -->
			
		</c:if> <!-- checkWriter eq 2 -->
	</div> <!-- item2 -->
	</section> <!--  회원(2) != 작성자 -->
	<!-- 관심목록 추가/삭제 아이콘 출력 끝!!! -->


			<!--
				<c:if test="${participateResult eq 0}">
					<div class="study-detail-input-align">
						<a class="input-button-bottom" href='list'> 목록 </a>
						<a class="input-button-bottom" href='apply?no=${freeStudy.no}'>스터디 신청</a>
					</div>
				</c:if>
				
				<c:if test="${participateResult eq 1}">
					<div class="study-detail-input-align">
						<a class="input-button-bottom" href='list'> 목록 </a>
						<a class="input-button-bottom" href='applycancel?no=${freeStudy.no}'>스터디 신청 취소</a>
					</div>
				</c:if>
				
				<div class="study-detail-input-align">
					<a class="input-button-bottom" href='list'>목록</a><br>
				</div>
				
				<c:if test="${freeStudy.writer.no eq loginUser.no}">
					<div class="study-detail-input-align">
						<a class="input-button-bottom" href='updateform?no=${freeStudy.no}'>수정</a> 
						<a class="input-button-bottom" id ="open" href='#'>삭제</a> 
					</div>
				</c:if>
			-->	

			<div class="study-bottom-button">
					<a class="input6" href='list'>목록</a><br>			
					 <c:if test="${freeStudy.writer.no eq loginUser.no}">
      <a class="input6" href='updateform?no=${freeStudy.no}'>수정</a> 
        <c:if test="${freeStudy.deleteStatus eq 0}">
          <a class="input6" id ="open" href='delete?no=${freeStudy.no}'>삭제</a> 
        </c:if>	
				    </c:if>
				
				<c:if test="${freeStudy.writer.no ne loginUser.no && loginUser ne null}">
  				<c:if test="${participateResult eq 0}">
					 <a class="input6" href='apply?no=${freeStudy.no}'>스터디 신청</a>
				  </c:if>
				</c:if>
				
				<c:if test="${participateResult eq 1}">
					<a class="input6" href='applycancel?no=${freeStudy.no}'>스터디 신청 취소</a>
				</c:if>
			</div> <!--study-bottom-button-->


				</fieldset> <!--menu-->
			</form> <!--detail-->
		</div> <!--study-form-center-->
		</header> <!--freestudy-top-->
		
		</div>   <!--container-->

	<script>
  var trList = document.querySelectorAll("li"); // 리턴 객체는 HTMLCollection 타입 객체이다.
  trList.forEach(function(trTag) {
    trTag.onclick = (e) => {
      window.location.href = e.currentTarget.querySelector("a").href;
    };
  });
  </script>
  
</body>
<!--무료스터디 제일 큰 포맷-->

</html>
