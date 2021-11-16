<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>스터디 위더스</title>
  
  <base target="_self" />

  <link rel="stylesheet" href="../css/theme.css">
  <link rel="stylesheet" href="../css/bootstrap.css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
  <link rel="stylesheet" href="../css/bootstrap.min.css">
  <link rel="stylesheet" href="../css/mdb.min.css">
  <link rel="stylesheet" href="css/footer.css">

</head>

  <style>
  label {
    margin-right: 5px;
    text-align: right;
    display: inline-block;
    width: 60px;
  }
  body {
    background: #EDECF5;
  }
  .content {
  display: flex;
  vertical-align: middle;
  display: inline-block;
  text-align: center;
  position: relative;
  -webkit-font-smoothing: antialiased;
  background-attachment: scroll;
  font-family: "Noto Sans KR", -apple-system, system-ui, BlinkMacSystemFont, "Apple SD Gothic Neo", "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", Helvetica;
  font-size: 14px;
  font-weight: bolder;
  line-height: 24px;
  padding-top: 32px;
  width: 845px;
  xheight: 1000px;
  position: relative;
  display: -ms-flexbox;
  -ms-flex-direction: column;
  flex-direction: column;
  xwidth: 100%;
  pointer-events: auto;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid rgba(0,0,0,.2);
  border-radius: 0.3rem;
  outline: 0;
  background-attachment: scroll;
  background-clip: border-box;
  background-color: rgb(255, 255, 255);
  border-bottom-left-radius: 13px;
  border-bottom-right-radius: 13px;
  border-top-left-radius: 13px;
  border-top-right-radius: 13px;
  box-shadow: rgb(0 0 0 / 15%) 0px 5px 25px 0px;
  box-sizing: border-box;
}
  .btn-primary1{
    background: #756daa;
    color:snow;
    border-radius: 70px;
    width: 500px;
  }
    .st-sns{
      font-size:5px;
    }
    .row{
      margin-left:5px;
      margin-right:5px;
    }
    
    .container{
    display: flex;
    text-align:center;
    justify-content:space-evenly;
    margin-top:15px;
    }
  .title_img{
    margin:30px 10px;
    text-align:center;
    justify-content: center;
  }   /*기존 join_ver*/
.study-with-us-loga-image { 
  margin-top: -20px;
}
.uldesign {
  padding-left: 20px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.freepagetop{
  -webkit-font-smoothing: antialiased;
  background-attachment: scroll;
  font-family: "Noto Sans KR", -apple-system, system-ui, BlinkMacSystemFont, "Apple SD Gothic Neo", "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", Helvetica;
  font-size: 14px;
  font-weight: bolder;
  line-height: 24px;
  padding-top: 32px;
  width: 845px;
  height: 1000px;
}
.menu {
  display: flex;
    vertical-align: middle;
  display: inline-block;
  text-align: center;
  position: relative;
  -webkit-font-smoothing: antialiased;
  background-attachment: scroll;
  font-family: "Noto Sans KR", -apple-system, system-ui, BlinkMacSystemFont, "Apple SD Gothic Neo", "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", Helvetica;
  font-size: 14px;
  font-weight: bolder;
  line-height: 24px;
  padding-top: 32px;
  width: 845px;
  height: 500px;
  position: relative;
  display: -ms-flexbox;
  -ms-flex-direction: column;
  flex-direction: column;
  xwidth: 100%;
  pointer-events: auto;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid rgba(0,0,0,.2);
  border-radius: 0.3rem;
  outline: 0;
  background-attachment: scroll;
    background-clip: border-box;
    background-color: rgb(255, 255, 255);
    border-bottom-left-radius: 13px;
    border-bottom-right-radius: 13px;
    border-top-left-radius: 13px;
    border-top-right-radius: 13px;
    box-shadow: rgb(0 0 0 / 15%) 0px 5px 25px 0px;
    box-sizing: border-box;
}
.pw-find-button {
  margin-top: 10px;
}
.input-button-find {
  text-decoration-line: none;
  float: center;
  text-align: center;
  width: 280px;
  height: 30px;
  border-radius: 4px;
  background-color: rgb(117, 109, 170);
  color: rgb(202, 199, 224);
  border: 2px solid rgb(117, 109, 170);
  box-shadow: 2px 2px 0px 0px rgb(77, 72, 72);
  margin: 5px;
}
.input-button-change {
  justify-content: center; 
  text-decoration-line: none;
float: center;
text-align: center;
width: 133px;
height: 30px;
border-radius: 4px;
background-color: rgb(117, 109, 170);
color: rgb(202, 199, 224);
border: 2px solid rgb(117, 109, 170);
box-shadow: 2px 2px 0px 0px rgb(77, 72, 72);
margin: 5px;
}
.input-button-cancle {
  justify-content: center; 
  text-decoration-line: none;
float: center;
text-align: center;
width: 133px;
height: 30px;
border-radius: 4px;
background-color: rgb(117, 109, 170);
color: rgb(202, 199, 224);
border: 2px solid rgb(117, 109, 170);
box-shadow: 2px 2px 0px 0px rgb(77, 72, 72);
margin: 5px;
}
.pw-content-input {
  width: 200px;
}
.pw-label {
  width: 200px;
  text-align: left;
  margin-right: -130px;
}
</style>

<body>
  <jsp:include page="../header.jsp"></jsp:include>
    <div class="container">

      <header class="freepagetop">

        <form method="post" action='resetpwd'>
        <fieldset class="menu">

        <div class=" resetpassword-header text-center">
          <h5 class="modal-title w-100 dark-grey-text font-weight-bold">&nbsp; &nbsp; 비밀번호 변경 확인</h5>
          <!-- <button type="button" class="close" data-dismiss="modal" aria-lable="close">&times;</button> -->
        </div>
      <hr>

        <div class="title_img"><img class="study-with-us-loga-image" src="../img/스터디위더스.png"></img></div>

        <div class="pw-content">
          <div class="pw-content-line">
            <label class = "pw-label" for='name'>이름</label> 
            <input class = "pw-content-input" type = "text" name ="name" placeholder="홍길동" required>
          </div>
          
          <div class="pw-content-line">
            <label class = "pw-label" for='name'>이메일</label> 
            <input class = "pw-content-input" type = "email" name ="email" placeholder="study@swu.com" required>
          </div>
          
          <div class="pw-content-line">
            <label class = "pw-label" for='name'>휴대폰번호</label> 
            <input class = "pw-content-input" type = "text" name ="phoneNumber" placeholder="010-1234-5678" required>
          </div>
          
          <div class="pw-find-button">
            <input class="input-button-find" type="submit" value="찾기"> 
          </div>
          <!-- 기존 코드 
            <div class="button">
              <button>변경</button> <a href='/swu/index'>[취소]</a> 
            </div>
          -->
        </div> <!--pw-content-->
          
          <div class="text-center mb-3">
            <button class="input-button-change" type="button">변경</button>
            <button class="input-button-cancle" type="button" href='/swu/index'>취소</button>
            <!-- 기존코드
              <a class="input-button-cancle" href='/swu/index'>취소</a> 
            -->
          </div>


    </fieldset> <!--menu-->
</form> 
</header> <!--freepagetop-->

</div> <!-- container -->

<jsp:include page="../footer.jsp"></jsp:include>
</body> <!--body-->
</html>
