<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 : 나의활동</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="${contextPath}/css/bootstrap.css">

<link rel="stylesheet" href="${contextPath}/css/theme.css">

<link rel="stylesheet" href="${contextPath}/css/footer.css">

<style>

.btn {
    margin-left: 5px;
    height: 40px;
    width: 150px;
    font-size: 12px;
    border-radius: 5px;
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
.modal_content {
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

.container {
	margin-bottom: 70px;
}

.myinfo-wrapper {
	padding-bottom: 100px;
	color: #898798;
	overflow: hidden;
}

.both {
	position: fixed;
	display: absolute;
	height: 800px;
}

.userid {
	font-size: 14px;
	color: #3b3a44;
}

ul.sub, .my-menu-list {
	list-style-type: none;
	padding: 0px;
	margin: 0px;
	width: 150px;
	background: #eeeef1;
	height: 810px;
	overflow: hidden;
	position: fixed;
	border-radius: 3px;
}

ul.sub li a {
	text-decoration: none;
	padding: 10px;
	display: block;
	color: rgb(82, 71, 92);
	font-weight: bold;
}

ul.sub li a:hover {
	background: #8e8aad;
	color: #fff;
}

ul.sub li a.home {
	background: rgb(150, 144, 144);
	color: #fff;
	height: auto;
}

.cd1 {
	margin-left: 120px;
}

.divider-sub {
	display: block;
	margin-top: 10px;
	margin-bottom: 16px;
	width: 86px;
	height: 3px;
	border-radius: 40px;
	background-color: #827e9e;
}

.profile-icon {
	text-align: center;
	margin-top: 20px;
	margin-bottom: 10px;
}

.my-menu-list {
	text-align: center;
}

.my-menu-list .sub {
	margin-top: 15px
}

.content-section {
	width: 900px;
	height: 920px;
	background-color: #eeeef1;
	border-radius: 3px;
	margin-top: 0px;
	margin-left: 180px;
}

.subject {
	font-size: 20px;
	margin-left: 30px;
	padding-top: 35px;
}

.box1 {
	width: 800px;
	height: 300px;
	border-top: 2px solid rgb(153, 152, 152);
	border-radius: 3px;
	padding: 30px;
	margin-top: 15px;
	margin-left: 20px;
	box-sizing: border-box
}

input {
	all: unset;
}

.attribute {
	text-align: justify;
}

.photo {
	padding: 15px;
	font-size: 12px;
}

.nickname, .email {
	padding: 20px;
	font-size: 12px;
}

.file {
	padding: 20px;
	margin-left: 57px;
}

.pwd {
	font-size: 12px;
	padding-top: 20px;
	padding-right: 22px;
}

.ph {
	margin-top: 0px;
	padding: 20px;
	font-size: 12px;
}

.buttons {
	margin-top: 20px;
	margin-left: 25px;
	margin-bottom: 0px;
	padding-bottom: 0px;
}

.study_category{
  display : flex;
  justify-content : space-around;
  width : 100px;
  margin-top : 10px;
  margin-left : 25px;
}

.free_btn{
	background-color: #eaeafd;
	margin-left: 30px;
	margin-top: 10px;
	border-radius: 5px;
}

.charge_btn{
	background-color: #eaeafd;
	margin-left: 6px;
	margin-top: 10px;
	border-radius: 5px;
	align-items: right;
}
</style>
</head>

<body>
	
<!-- 내생스 / 유료 -->
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="col-lg-6 py-3 wow fadeInUp">

			<span class="subhead">my page</span>
			<!--<h2 class="title-section">나의 정보</h2>-->
			<div class="divider-sub"></div>


			<div class="both">
				<ul class="my-menu-list">
					<div class="profile-icon">

						<img src="../img/profile.png">
					</div>
					<li class="userid">${loginUser.nickname}님</li>
					<ul class=sub>
						<li><a href="/swu/app//user/myinfo">나의 정보</a></li>
              <li><a href="/swu/app/mypage/paymentlist">결제 내역</a></li>
						<li><a href="#">관심 목록</a></li>
						<li><a href="/swu/app/mypage/chargeregisterlist">나의 활동</a></li>
					</ul>
				</ul>
				<div class="content-section">

					<section class="all-contents">

						<div class="box0">
							<div class="subject">나의 활동</div>
						</div>

						<div class="buttons">
							<button type="button" class="btn btn-primary btn-sm" onclick="location.href='../freestudy/registerlist';">내가 등록한 스터디</button>
							<button type="button" class="btn btn-primary btn-sm" onclick="location.href='../freestudy/participatelist';">내가 참여한 스터디</button>
							<button type="button" class="btn btn-primary btn-sm">나의 게시글</button>
							<br>
						</div>
						<!-- 수정 -->
						
            <div class ="study_category">
             <a href="/swu/app/mypage/freeregisterlist"><button type="button" class="free_btn">무료</button></a>
            <a href="/swu/app/mypage/chargeregisterlist"><button type="button" class="charge_btn">유료</button></a>
            </div>						

						<div class="box1">
							<div class="table-content">
								<table class="table">
									<thead>
										<tr>
											<th scope="col">제목</th>
											<th scope="col">지역</th>
											<th scope="col">팀원 수</th>
											<th scope="col">시작일</th>
											<th scope="col">종료일</th>
											<th scope="col">신청자 관리</th>
										</tr>
									</thead>
									<c:forEach items="${studyList}" var="study">
										<tbody>
											<tr>
												<td>${study.title}</td>
												<td>${study.area}</td>
												<td>${study.members}</td>
												<td>${study.startDate}</td>
												<td>${study.endDate}</td>
												<td>링크&아이콘</td>
											</tr>
										</tbody>
									</c:forEach>
								</table>
							</div>
					</section>

				</div> <!--content-section-->

			</div> <!--both-->
		</div> <!--col-lg-6 py-3 wow fadeInUp-->

	</div>	<!-- .container -->

	
	<script src="${contextPath}/js/jquery-3.5.1.min.js"></script>

	<script src="${contextPath}/js/bootstrap.bundle.min.js"></script>

	<script src="${contextPath}/vendor/wow/wow.min.js"></script>

	<script src="${contextPath}/js/theme.js"></script>

</body>
</html>