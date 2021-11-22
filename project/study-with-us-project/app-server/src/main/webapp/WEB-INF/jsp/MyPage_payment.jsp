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
.btn {
  margin-left : 5px;
  height: 40px;
  width: 109px;
  font-size: 10px;
}
.study_category{
  display : flex;
  justify-content : space-around;
  width : 100px;
  margin-top : 10px;
  margin-left : 25px;
}
</style>
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>
  <div class="container">
    <div class="col-lg-6 py-3 wow fadeInUp">

      <span class="subhead">my page</span>
      <!--<h2 class="title-section">나의 정보</h2>-->
      <div class="divider-sub"></div>


      <div class="both">
        <ul class="my-menu-list">
          <div class="profile-icon">

            <img src="../../img/profile.png"">
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
              <div class="subject">결제 내역 </div>
            </div>
            <div class="box1">
              <div class="table-content">
                <table class="table">
                  <thead>
                    <tr>
                      <th scope="col">제목</th>
                      <th scope="col">카테고리</th>
                      <th scope="col">금액</th>
                      <th scope="col">결제수단</th>
                      <th scope="col">결제일</th>
                      <th scope="col">명세서</th>
                    </tr>
                  </thead>
                  <c:forEach items="${payments}" var="payment">
                    <tbody>
                      <tr>
                        <td>${payment.study.title}</td>
                        <td>${payment.study.category}</td>
                        <td>${payment.study.price}</td>
                        <td>${payment.paymentMethod}</td>
                        <td>${payment.paymentDate}</td>
                        <td>명세서아이콘</td>
                      </tr>
                    </tbody>
                  </c:forEach>
                </table>
              </div>
          </section>
        </div>
      </div>
    </div>
  </div>  <!-- .container -->
  <script src="${contextPath}/js/jquery-3.5.1.min.js"></script>
  <script src="${contextPath}/js/bootstrap.bundle.min.js"></script>
  <script src="${contextPath}/vendor/wow/wow.min.js"></script>
  <script src="${contextPath}/js/theme.js"></script>
</body>
</html>