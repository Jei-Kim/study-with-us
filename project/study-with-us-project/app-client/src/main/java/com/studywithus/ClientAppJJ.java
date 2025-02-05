package com.studywithus;

import static com.studywithus.menu.Menu.ACCESS_ADMIN;
import static com.studywithus.menu.Menu.ACCESS_GENERAL;
import static com.studywithus.menu.Menu.ACCESS_LEADER;
import static com.studywithus.menu.Menu.ACCESS_LOGOUT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.studywithus.context.ApplicationContextListener;
import com.studywithus.dao.CommentDao;
import com.studywithus.dao.CommunityDao;
import com.studywithus.dao.MemberDao;
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.dao.StudyDao;
import com.studywithus.dao.impl.MariadbCommentDao;
import com.studywithus.dao.impl.MariadbCommunityDao;
import com.studywithus.dao.impl.MariadbMentorApplicationDao;
import com.studywithus.dao.impl.MybatisMemberDaoJJ;
import com.studywithus.dao.impl.NetChargeStudyDao;
import com.studywithus.dao.impl.NetPaymentDao;
import com.studywithus.dao.impl.NetReviewDao;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.chargestudy.ChargeStudyAddHandler;
import com.studywithus.handler.chargestudy.ChargeStudyDeleteRequestCancelHandler;
import com.studywithus.handler.chargestudy.ChargeStudyDeleteRequestDetailHandler;
import com.studywithus.handler.chargestudy.ChargeStudyDeleteRequestHandler;
import com.studywithus.handler.chargestudy.ChargeStudyDeleteRequestListHandler;
import com.studywithus.handler.chargestudy.ChargeStudyDetailHandler;
import com.studywithus.handler.chargestudy.ChargeStudyDetailMenuPrompt;
import com.studywithus.handler.chargestudy.ChargeStudyInterestAddHandler;
import com.studywithus.handler.chargestudy.ChargeStudyInterestDetailHandler;
import com.studywithus.handler.chargestudy.ChargeStudyListHandler;
import com.studywithus.handler.chargestudy.ChargeStudyPaymentDetailHandler;
import com.studywithus.handler.chargestudy.ChargeStudyPaymentHandler;
import com.studywithus.handler.chargestudy.ChargeStudyUpdateHandler;
import com.studywithus.handler.chargestudy.MentorApplicantApproveHandler;
import com.studywithus.handler.chargestudy.MentorApplicationAddHandler;
import com.studywithus.handler.chargestudy.MentorApplicationDetailHandler;
import com.studywithus.handler.chargestudy.ParticipateChargeStudyDetailHandler;
import com.studywithus.handler.chargestudy.ParticipateChargeStudyListHandler;
import com.studywithus.handler.chargestudy.RegisterChargeStudyDetailHandler;
import com.studywithus.handler.chargestudy.RegisterChargeStudyListHandler;
import com.studywithus.handler.chargestudy.ReviewAddHandler;
import com.studywithus.handler.chargestudy.ReviewListHandler;
import com.studywithus.handler.comment.CommentAddHandler;
import com.studywithus.handler.comment.CommentDeleteHandler;
import com.studywithus.handler.community.CommunityAddHandler;
import com.studywithus.handler.community.CommunityDeleteHandler;
import com.studywithus.handler.community.CommunityDetailHandler;
import com.studywithus.handler.community.CommunityListHandler;
import com.studywithus.handler.community.CommunitySearchHandler;
import com.studywithus.handler.community.CommunityUpdateHandler;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.handler.user.AuthLogOutHandler;
import com.studywithus.handler.user.FindEmailHandler;
import com.studywithus.handler.user.MembershipWithdrawalHandler;
import com.studywithus.handler.user.MyInfoHandler;
import com.studywithus.handler.user.ResetPasswordHandler;
import com.studywithus.handler.user.SignUpHandler;
import com.studywithus.handler.user.SnsLogInHandler;
import com.studywithus.handler.user.SnsSignUpHandler;
import com.studywithus.listener.AppInitListener;
import com.studywithus.menu.Menu;
import com.studywithus.menu.MenuGroup;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;


public class ClientAppJJ {

  Connection con;
  RequestAgent requestAgent;
  SqlSession sqlSession;

  HashMap<String, Command> commandMap = new HashMap<>();

  CommandRequest request = new CommandRequest(commandMap);

  class MenuItem extends Menu {
    String menuId;

    public MenuItem(String title, String menuId) {
      super(title);
      this.menuId = menuId;
    }

    public MenuItem(String title, int accessScope, String menuId) {
      super(title, accessScope);
      this.menuId = menuId;
    }

    @Override
    public void execute() {
      Command command = commandMap.get(menuId);
      try {
        command.execute(request);
      } catch (Exception e) {
        System.out.printf("%s 명령을 실행하는 중 오류 발생!\n", menuId);
        e.printStackTrace();
      }
    }
  }

  public ClientAppJJ() throws Exception {

    requestAgent = null;

    con =
        DriverManager.getConnection("jdbc:mysql://localhost:3306/team3db?user=team3&password=1111");

    // Mybatis의 SqlSession 객체 준비
    SqlSession sqlSession = new SqlSessionFactoryBuilder()
        .build(Resources.getResourceAsStream("com/studywithus/conf/mybatis-config.xml"))
        .openSession();

    // 데이터 관리를 담당할 DAO 객체를 준비한다.
    MemberDao memberDao = new MybatisMemberDaoJJ(sqlSession);
    StudyDao studyDao = sqlSession.getMapper(StudyDao.class);
    CommunityDao communityDao = new MariadbCommunityDao(con);
    CommentDao commentDao = new MariadbCommentDao(con);
    MentorApplicationDao mentorApplicationDao = new MariadbMentorApplicationDao(con);


    NetChargeStudyDao chargeStudyDao = new NetChargeStudyDao(requestAgent);
    ChargeStudyDetailMenuPrompt chargeStudyDetailMenuPrompt =
        new ChargeStudyDetailMenuPrompt(studyDao, request);

    NetPaymentDao paymentDao = new NetPaymentDao(requestAgent);
    NetReviewDao reviewDao = new NetReviewDao(requestAgent);


    // Command 객체 준비
    commandMap.put("/auth/logIn", new AuthLogInHandler(memberDao));
    commandMap.put("/google/logIn", new SnsLogInHandler(memberDao));
    commandMap.put("/facebook/logIn", new SnsLogInHandler(memberDao));
    commandMap.put("/kakao/logIn", new SnsLogInHandler(memberDao));
    commandMap.put("/naver/logIn", new SnsLogInHandler(memberDao));

    commandMap.put("/auth/logOut", new AuthLogOutHandler());

    commandMap.put("/auth/signUp", new SignUpHandler(memberDao));
    commandMap.put("/google/signUp", new SnsSignUpHandler(memberDao));
    commandMap.put("/facebook/signUp", new SnsSignUpHandler(memberDao));
    commandMap.put("/kakao/signUp", new SnsSignUpHandler(memberDao));
    commandMap.put("/naver/signUp", new SnsSignUpHandler(memberDao));

    commandMap.put("/find/email", new FindEmailHandler(memberDao));
    commandMap.put("/reset/password", new ResetPasswordHandler(memberDao));

    commandMap.put("/auth/membershipWithdrawal", new MembershipWithdrawalHandler(memberDao));

    commandMap.put("/myInfo/list", new MyInfoHandler());

    // commandMap.put("/freeInterest/list", new FreeStudyInterestListHandler(freeStudyList));
    // commandMap.put("/freeInterest/delete", new FreeStudyInterestDeleteHandler(freeStudyList));

    //
    // commandMap.put("/freeStudy/search", new FreeStudySearchHandler(freeStudyList));
    // commandMap.put("/freeStudy/add", new FreeStudyAddHandler(freeStudyList,
    // registerFreeStudyMap));
    // commandMap.put("/freeStudy/list", new FreeStudyListHandler(freeStudyList));
    // commandMap.put("/freeStudy/detail", new FreeStudyDetailHandler(freeStudyList));
    // commandMap.put("/freeStudy/update", new FreeStudyUpdateHandler(freeStudyList));
    // commandMap.put("/freeStudy/delete", new FreeStudyDeleteHandler(freeStudyList));
    //
    // commandMap.put("/freeStudy/apply", new FreeStudyApplyHandler(freeStudyList,
    // applyFreeStudyMap));
    // commandMap.put("/freeStudy/applyCancel",
    // new FreeStudyApplyCancelHandler(freeStudyList, applyFreeStudyMap));
    // commandMap.put("/freeStudy/applyList", new FreeStudyApplyListHandler(freeStudyList));
    // commandMap.put("/freeStudy/addInterest", new FreeStudyInterestAddHandler(freeStudyList));
    // commandMap.put("/freeStudy/deleteInterest", new
    // FreeStudyInterestDeleteHandler(freeStudyList));
    // commandMap.put("/freeStudy/registerStudyList",
    // new RegisterFreeStudyDetailHandler(registerFreeStudyMap, participateFreeStudyMap));
    // commandMap.put("/freeStudy/participateStudyList",
    // new ParticipateFreeStudyListHandler(participateFreeStudyMap));
    //

    commandMap.put("/mentorApplicant/add", new MentorApplicationAddHandler(mentorApplicationDao));
    commandMap.put("/mentorApplicant/list",
        new MentorApplicationDetailHandler(mentorApplicationDao));
    //    commandMap.put("/mentorApplication/approve",
    //        new MentorApplicantApproveHandler(mentorApplicationDao));

    commandMap.put("/chargeStudy/add",new ChargeStudyAddHandler(studyDao));
    commandMap.put("/chargeStudy/list", new ChargeStudyListHandler(studyDao));
    commandMap.put("/chargeStudy/detail", new ChargeStudyDetailHandler(studyDao, chargeStudyDetailMenuPrompt));
    commandMap.put("/chargeStudy/update", new ChargeStudyUpdateHandler(studyDao));
    commandMap.put("/chargeStudy/deleteRequest", new ChargeStudyDeleteRequestHandler(studyDao));
    commandMap.put("/chargeStudy/deleteRequestCancel", new ChargeStudyDeleteRequestCancelHandler(studyDao));
    commandMap.put("/chargeStudy/deleteRequestList", new ChargeStudyDeleteRequestListHandler(studyDao));
    commandMap.put("/chargeStudy/deleteRequestDetail", new ChargeStudyDeleteRequestDetailHandler(studyDao));
    commandMap.put("/chargeStudy/payment", new ChargeStudyPaymentHandler(paymentDao, studyDao));
    commandMap.put("/chargeStudy/paymentDetail", new ChargeStudyPaymentDetailHandler(paymentDao, studyDao));
    //    commandMap.put("/chargeStudy/paymentCancel", new ChargeStudyPaymentCancelHandler(paymentDao, studyDao));
    //    commandMap.put("/chargeStudy/paymentList", new ChargeStudyPaymentListHandler(paymentDao));
    commandMap.put("/chargeStudy/interestAdd", new ChargeStudyInterestAddHandler(studyDao));
    //    commandMap.put("/chargeStudy/interestDelete", new ChargeStudyInterestDeleteHandler(studyDao));
    commandMap.put("/chargeStudy/registerChargeStudyList",new RegisterChargeStudyListHandler(studyDao));
    commandMap.put("/chargeStudy/registerChargeStudyDetail",new RegisterChargeStudyDetailHandler(studyDao));
    commandMap.put("/chargeStudy/participateChargeStudyList", new ParticipateChargeStudyListHandler(studyDao));
    commandMap.put("/chargeStudy/participateChargeStudyDetail", new ParticipateChargeStudyDetailHandler(studyDao));
    commandMap.put("/review/add", new ReviewAddHandler(reviewDao));
    commandMap.put("/review/list", new ReviewListHandler(reviewDao));
    //

    commandMap.put("/community/add", new CommunityAddHandler(communityDao));
    commandMap.put("/community/list", new CommunityListHandler(communityDao));
    commandMap.put("/community/detail", new CommunityDetailHandler(communityDao));
    commandMap.put("/community/update", new CommunityUpdateHandler(communityDao));
    commandMap.put("/community/delete", new CommunityDeleteHandler(communityDao));
    commandMap.put("/community/search", new CommunitySearchHandler(communityDao));

    // commandMap.put("/communityQa/add", new CommunityAddHandler(communityQaList));
    // commandMap.put("/communityQa/list", new CommunityListHandler(communityQaList));
    // commandMap.put("/communityQa/detail",
    // new CommunityDetailHandler(communityQaList, "/communityQa/update", "/communityQa/delete"));
    // commandMap.put("/communityQa/update", new CommunityUpdateHandler(communityQaList));
    // commandMap.put("/communityQa/delete", new CommunityDeleteHandler(communityQaList));
    // commandMap.put("/communityQa/search", new CommunitySearchHandler(communityQaList));
    //
    // commandMap.put("/communityInfo/add", new CommunityAddHandler(communityInfoList));
    // commandMap.put("/communityInfo/list", new CommunityListHandler(communityInfoList));
    // commandMap.put("/communityInfo/detail", new CommunityDetailHandler(communityInfoList,
    // "/communityInfo/update", "/communityInfo/delete"));
    // commandMap.put("/communityInfo/update", new CommunityUpdateHandler(communityInfoList));
    // commandMap.put("/communityInfo/delete", new CommunityDeleteHandler(communityInfoList));
    // commandMap.put("/communityInfo/search", new CommunitySearchHandler(communityInfoList));
    //
    // commandMap.put("/communityTalk/add", new CommunityAddHandler(communityTalkList));
    // commandMap.put("/communityTalk/list", new CommunityListHandler(communityTalkList));
    // commandMap.put("/communityTalk/detail", new CommunityDetailHandler(communityTalkList,
    // "/communityTalk/update", "/communityTalk/delete"));
    // commandMap.put("/communityTalk/update", new CommunityUpdateHandler(communityTalkList));
    // commandMap.put("/communityTalk/delete", new CommunityDeleteHandler(communityTalkList));
    // commandMap.put("/communityTalk/search", new CommunitySearchHandler(communityTalkList));
    //
    // commandMap.put("/myPost/list",
    // new MyPostListHandler(communityQaList, communityInfoList, communityTalkList));
    // commandMap.put("/myPost/detail",
    // new MyPostDetailHandler(communityQaList, communityInfoList, communityTalkList));

    //    commandMap.put("/jobsSchedule/add", new JobsScheduleAddHandler(jobsScheduleDao));
    //    commandMap.put("/jobsSchedule/list", new JobsScheduleListHandler(jobsScheduleDao));
    //    commandMap.put("/jobsSchedule/detail", new JobsScheduleDetailHandler(jobsScheduleDao));
    //    commandMap.put("/jobsSchedule/update", new JobsScheduleUpdateHandler(jobsScheduleDao));
    //    commandMap.put("/jobsSchedule/delete", new JobsScheduleDeleteHandler(jobsScheduleDao));
    //
    //    commandMap.put("/examSchedule/add", new ExamScheduleAddHandler(examScheduleDao));
    //    commandMap.put("/examSchedule/list", new ExamScheduleListHandler(examScheduleDao));
    //    commandMap.put("/examSchedule/detail", new ExamScheduleDetailHandler(examScheduleDao));
    //    commandMap.put("/examSchedule/update", new ExamScheduleUpdateHandler(examScheduleDao));
    //    commandMap.put("/examSchedule/delete", new ExamScheduleDeleteHandler(examScheduleDao));

    commandMap.put("/mentorApplication/approve", new MentorApplicantApproveHandler(memberDao));                          
    commandMap.put("/chargeInterest/detail", new ChargeStudyInterestDetailHandler(studyDao, chargeStudyDetailMenuPrompt));                          
    commandMap.put("/comment/add", new CommentAddHandler(commentDao));                          
    commandMap.put("/comment/delete", new CommentDeleteHandler(commentDao));                          



  }

  // ------------------------------ STUDY WITH US -----------------------------------------

  // 메인 메뉴
  Menu createMainMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("STUDY WITH US");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(createSignUpMenu());
    mainMenuGroup.add(createLogInMenu());
    mainMenuGroup.add(new MenuItem("로그아웃", ACCESS_GENERAL | ACCESS_ADMIN, "/auth/logOut"));
    mainMenuGroup.add(createMyPageMenu());
    mainMenuGroup.add(createAdminPageMenu());
    mainMenuGroup.add(createFreeStudyMenu());
    mainMenuGroup.add(createChargeStudyMenu());
    mainMenuGroup.add(createCommunityMenu());
    mainMenuGroup.add(createScheduleMenu());

    return mainMenuGroup;
  }

  // 메인 메뉴 / 회원가입
  private Menu createSignUpMenu() {
    MenuGroup signUpMenu = new MenuGroup("회원가입", ACCESS_LOGOUT);

    signUpMenu.add(new MenuItem("이메일로 가입하기", "/auth/signUp"));
    signUpMenu.add(createSnsSignUpMenu());

    return signUpMenu;
  }

  // 메인 메뉴 / 회원가입 / SNS로 시작하기
  private Menu createSnsSignUpMenu() {
    MenuGroup snsSignUpMenu = new MenuGroup("SNS로 시작하기", ACCESS_LOGOUT);

    snsSignUpMenu.add(new MenuItem("구글로 시작하기", "/google/signUp"));
    snsSignUpMenu.add(new MenuItem("페이스북으로 시작하기", "/facebook/signUp"));
    snsSignUpMenu.add(new MenuItem("카카오로 시작하기", "/kakao/signUp"));
    snsSignUpMenu.add(new MenuItem("네이버로 시작하기", "/naver/signUp"));

    return snsSignUpMenu;
  }

  // 메인 메뉴 / 로그인
  private Menu createLogInMenu() {
    MenuGroup logInMenu = new MenuGroup("로그인", ACCESS_LOGOUT);

    logInMenu.add(new MenuItem("이메일 로그인", "/auth/logIn"));
    logInMenu.add(createSnsLogInMenu());
    logInMenu.add(new MenuItem("아이디 찾기", "/find/email"));
    logInMenu.add(new MenuItem("비밀번호 변경", "/reset/password"));

    return logInMenu;
  }

  // 메인 메뉴 / 로그인 / SNS로 시작하기
  private Menu createSnsLogInMenu() {
    MenuGroup createSnsLogInMenu = new MenuGroup("SNS 로그인", ACCESS_LOGOUT);

    createSnsLogInMenu.add(new MenuItem("구글로 로그인", "/google/logIn"));
    createSnsLogInMenu.add(new MenuItem("페이스북으로 로그인", "/facebook/logIn"));
    createSnsLogInMenu.add(new MenuItem("카카오로 로그인", "/kakao/logIn"));
    createSnsLogInMenu.add(new MenuItem("네이버로 로그인", "/naver/logIn"));

    return createSnsLogInMenu;
  }

  // ------------------------------ 무료 스터디 -----------------------------------------

  // 무료 스터디 메인 메뉴
  private Menu createFreeStudyMenu() {
    MenuGroup freeStudyMenu = new MenuGroup("무료 스터디");

    freeStudyMenu.add(new MenuItem("검색", "/freeStudy/search"));
    freeStudyMenu.add(new MenuItem("생성", ACCESS_GENERAL | ACCESS_LEADER, "/freeStudy/add"));
    freeStudyMenu.add(new MenuItem("조회", "/freeStudy/list"));
    freeStudyMenu.add(new MenuItem("상세보기", "/freeStudy/detail"));

    return freeStudyMenu;
  }

  // ------------------------------ 유료 스터디 -----------------------------------------

  // 유료 스터디 메인 메뉴
  private Menu createChargeStudyMenu() {
    MenuGroup chargeStudyMenu = new MenuGroup("유료 스터디");

    chargeStudyMenu.add(new MenuItem("검색", "/chargeStudy/search"));
    chargeStudyMenu.add(new MenuItem("생성", "/chargeStudy/add"));
    chargeStudyMenu.add(new MenuItem("조회", "/chargeStudy/list"));
    chargeStudyMenu.add(new MenuItem("상세보기", "/chargeStudy/detail"));
    chargeStudyMenu.add(new MenuItem("멘토 신청", ACCESS_GENERAL, "/mentorApplicant/add"));

    return chargeStudyMenu;
  }

  // ------------------------------ 커뮤니티 -----------------------------------------

  // 커뮤니티 메인 메뉴
  private Menu createCommunityMenu() {
    MenuGroup communityMenu = new MenuGroup("커뮤니티");

    communityMenu.add(createCommunityInfoMenu());
    communityMenu.add(createCommunityQaMenu());
    communityMenu.add(createCommunityTalkMenu());

    return communityMenu;
  }

  // 커뮤니티 / 질문
  private Menu createCommunityQaMenu() {
    MenuGroup communityQaMenu = new MenuGroup("질문");

    communityQaMenu.add(new MenuItem("검색", "/communityQa/search"));
    communityQaMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityQa/add"));
    communityQaMenu.add(new MenuItem("조회", "/communityQa/list"));
    communityQaMenu.add(new MenuItem("상세보기", "/communityQa/detail"));

    return communityQaMenu;
  }

  // 커뮤니티 / 정보
  private Menu createCommunityInfoMenu() {
    MenuGroup communityInfoMenu = new MenuGroup("정보");

    communityInfoMenu.add(new MenuItem("검색", "/communityInfo/search"));
    communityInfoMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityInfo/add"));
    communityInfoMenu.add(new MenuItem("조회", "/communityInfo/list"));
    communityInfoMenu.add(new MenuItem("상세보기", "/communityInfo/detail"));

    return communityInfoMenu;
  }

  // 커뮤니티 / 스몰톡
  private Menu createCommunityTalkMenu() {
    MenuGroup communityTalkMenu = new MenuGroup("스몰톡");

    communityTalkMenu.add(new MenuItem("검색", "/communityTalk/search"));
    communityTalkMenu.add(new MenuItem("생성", ACCESS_GENERAL, "/communityTalk/add"));
    communityTalkMenu.add(new MenuItem("조회", "/communityTalk/list"));
    communityTalkMenu.add(new MenuItem("상세보기", "/communityTalk/detail"));

    return communityTalkMenu;
  }

  // ------------------------------ 일정 -----------------------------------------

  private Menu createScheduleMenu() {
    MenuGroup ScheduleMenu = new MenuGroup("일정");

    ScheduleMenu.add(createJobsScheduleMenu());
    ScheduleMenu.add(createExamScheduleMenu());

    return ScheduleMenu;
  }

  private Menu createJobsScheduleMenu() {
    MenuGroup jobsScheduleMenu = new MenuGroup("이달의 채용공고");

    jobsScheduleMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/jobsSchedule/add"));
    jobsScheduleMenu.add(new MenuItem("조회", "/jobsSchedule/list"));
    jobsScheduleMenu.add(new MenuItem("상세보기", "/jobsSchedule/detail"));

    return jobsScheduleMenu;
  }

  private Menu createExamScheduleMenu() {
    MenuGroup examScheduleMenu = new MenuGroup("이달의 시험일정");

    examScheduleMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/examSchedule/add"));
    examScheduleMenu.add(new MenuItem("조회", "/examSchedule/list"));
    examScheduleMenu.add(new MenuItem("상세보기", "/examSchedule/detail"));

    return examScheduleMenu;
  }

  // ------------------------------ 마이 페이지 -----------------------------------------

  // 마이 페이지 메인
  private Menu createMyPageMenu() {
    MenuGroup myPageMenu = new MenuGroup("마이 페이지", ACCESS_GENERAL);

    myPageMenu.add(createActivityDetailMenu());
    myPageMenu.add(createInterestMenu());
    myPageMenu.add(createPaymentListMenu());
    myPageMenu.add(new MenuItem("나의 정보", "/myInfo/list"));
    myPageMenu.add(new MenuItem("회원 탈퇴", ACCESS_GENERAL, "/auth/membershipWithdrawal"));

    return myPageMenu;
  }

  // 마이 페이지 / 나의 활동
  private Menu createActivityDetailMenu() {

    MenuGroup activityDetailMenu = new MenuGroup("나의 활동", ACCESS_GENERAL);
    activityDetailMenu.add(createMyStudyMenu());
    activityDetailMenu.add(createMyPostMenu());

    return activityDetailMenu;
  }

  // 마이 페이지 / 나의 활동 / 나의 스터디
  private Menu createMyStudyMenu() {

    MenuGroup myStudyMenu = new MenuGroup("나의 스터디");
    myStudyMenu.add(createFreeStudyApplyMenu());
    myStudyMenu.add(createRegisterFreeStudyMenu());
    myStudyMenu.add(createParticipateFreeStudyMenu());
    myStudyMenu.add(createRegisterChargeStudyMenu());
    myStudyMenu.add(createParticipateChargeStudyMenu());

    return myStudyMenu;
  }

  // 마이 페이지 / 나의 활동 / 나의 게시글
  private Menu createMyPostMenu() {

    MenuGroup myPostMenu = new MenuGroup("나의 게시글");
    myPostMenu.add(new MenuItem("조회", "/myPost/list"));
    myPostMenu.add(new MenuItem("상세보기", "/myPost/detail"));

    return myPostMenu;
  }

  // 마이 페이지 / 나의 활동 / 나의 스터디 / 무료 스터디 신청 내역
  private Menu createFreeStudyApplyMenu() {

    MenuGroup freeStudyApplyMenu = new MenuGroup("무료 스터디 신청 내역", ACCESS_GENERAL);
    freeStudyApplyMenu.add(new MenuItem("조회", "/freeStudy/applyList"));

    return freeStudyApplyMenu;
  }

  // 마이 페이지 / 나의 활동 / 나의 스터디 / 내가 생성한 무료 스터디(팀장 관점)

  private Menu createRegisterFreeStudyMenu() {

    MenuGroup registerFreeStudyMenu = new MenuGroup("내가 생성한 무료 스터디", ACCESS_LEADER);
    registerFreeStudyMenu.add(new MenuItem("상세보기", "/freeStudy/registerStudyList"));
    // [삭제] 상세보기와 통합
    // registerFreeStudyMenu.add(new MenuItem("조회", "/freeStudy/registerStudyList"));
    // [삭제] 회의 후 안하기로 결정
    // registerFreeStudyMenu.add(new MenuItem("삭제", "/registerFreeStudy/delete"));

    return registerFreeStudyMenu;
  }

  // 마이 페이지 / 나의 활동 / 나의 스터디 / 내가 참여한 무료 스터디(팀원 관점)
  private Menu createParticipateFreeStudyMenu() {

    MenuGroup participateFreeStudyMenu = new MenuGroup("내가 참여한 무료 스터디", Menu.ACCESS_MEMBER);
    participateFreeStudyMenu.add(new MenuItem("조회", "/freeStudy/participateStudyList"));
    // participateFreeStudyMenu.add(new MenuItem("상세보기", "/participateFreeStudy/detail"));

    return participateFreeStudyMenu;
  }

  // 마이 페이지 / 나의 활동 / 나의 스터디 / 내가 생성한 유료 스터디
  private Menu createRegisterChargeStudyMenu() {

    MenuGroup registerChargeStudyMenu = new MenuGroup("내가 생성한 유료 스터디", ACCESS_GENERAL);
    registerChargeStudyMenu.add(new MenuItem("조회", "/chargeStudy/registerChargeStudyList"));
    registerChargeStudyMenu.add(new MenuItem("상세보기", "/chargeStudy/registerChargeStudyDetail"));

    return registerChargeStudyMenu;
  }

  // 마이 페이지 / 나의 활동 / 나의 스터디 / 내가 참여한 유료 스터디
  private Menu createParticipateChargeStudyMenu() {

    MenuGroup participateChargeStudyMenu = new MenuGroup("내가 참여한 유료 스터디", ACCESS_GENERAL);
    participateChargeStudyMenu.add(new MenuItem("조회", "/chargeStudy/participateChargeStudyList"));
    participateChargeStudyMenu
    .add(new MenuItem("상세보기", "/chargeStudy/participateChargeStudyDetail"));

    return participateChargeStudyMenu;
  }

  // 마이 페이지 / 나의 관심목록
  private Menu createInterestMenu() {

    MenuGroup interestMenu = new MenuGroup("나의 관심목록");
    interestMenu.add(createFreeInterestMenu());
    interestMenu.add(createChargeInterestMenu());

    return interestMenu;
  }

  // 마이 페이지 / 나의 관심목록 / 무료 스터디 관심목록
  private Menu createFreeInterestMenu() {

    MenuGroup freeInterestMenu = new MenuGroup("무료 스터디 관심목록");
    freeInterestMenu.add(new MenuItem("조회", "/freeInterest/list"));
    freeInterestMenu.add(new MenuItem("삭제", "/freeInterest/delete"));

    return freeInterestMenu;
  }

  // 마이 페이지 / 나의 관심목록 / 유료 스터디 관심목록
  private Menu createChargeInterestMenu() {

    MenuGroup chargeInterestMenu = new MenuGroup("유료 스터디 관심목록");
    chargeInterestMenu.add(new MenuItem("조회", "/chargeInterest/list"));
    chargeInterestMenu.add(new MenuItem("상세보기", "/chargeInterest/detail"));

    return chargeInterestMenu;
  }

  // 마이 페이지 / 나의 결제 내역
  private Menu createPaymentListMenu() {

    MenuGroup paymentListMenu = new MenuGroup("나의 결제 내역");
    paymentListMenu.add(new MenuItem("조회", "/chargeStudy/paymentList"));
    paymentListMenu.add(new MenuItem("상세보기", "/chargeStudy/paymentDetail"));

    return paymentListMenu;
  }

  // ------------------------------ 관리자 페이지 -----------------------------------------

  // 관리자 페이지 메인
  private Menu createAdminPageMenu() {

    MenuGroup adminPageMenu = new MenuGroup("관리자 페이지", ACCESS_ADMIN);
    adminPageMenu.add(createMemberManagementMenu());
    adminPageMenu.add(createStudyManagementMenu());
    adminPageMenu.add(createScheduleManagementMenu());

    return adminPageMenu;
  }

  // 관리자 페이지 / 회원 관리
  private Menu createMemberManagementMenu() {

    MenuGroup memberManagementMenu = new MenuGroup("회원 관리");
    memberManagementMenu.add(new MenuItem("멘토 승인 관리", "/mentorApplicant/list"));
    // memberManagementMenu.add(createBlackListMenu());

    return memberManagementMenu;
  }

  /*
   * // 관리자 페이지 / 회원 관리 / 블랙리스트 관리 private Menu createBlackListMenu() {
   * 
   * MenuGroup mentorManagementMenu = new MenuGroup("블랙리스트 관리"); mentorManagementMenu.add(new
   * MenuItem("", "/"));
   * 
   * return mentorManagementMenu; }
   */

  // 관리자 페이지 / 스터디 관리
  private Menu createStudyManagementMenu() {

    MenuGroup studyManagementMenu = new MenuGroup("스터디 관리");
    studyManagementMenu.add(createDeleteRequestStudyMenu());

    return studyManagementMenu;
  }

  // 관리자 페이지 / 스터디 관리 / 삭제 요청 스터디 관리
  private Menu createDeleteRequestStudyMenu() {

    MenuGroup deletedRequestMenu = new MenuGroup("삭제 요청 스터디 관리");
    deletedRequestMenu.add(new MenuItem("조회", "/chargeStudy/deleteRequestList"));
    deletedRequestMenu.add(new MenuItem("상세보기", "/chargeStudy/deleteRequestDetail"));

    return deletedRequestMenu;
  }

  // 관리자 페이지 / 일정 관리
  private Menu createScheduleManagementMenu() {

    MenuGroup ScheduleMenu = new MenuGroup("일정 관리", ACCESS_ADMIN);
    ScheduleMenu.add(createJobsScheduleManagementMenu());
    ScheduleMenu.add(createExamScheduleManagementMenu());

    return ScheduleMenu;
  }

  // 관리자 페이지 / 일정 관리 / 이달의 채용공고 관리
  private Menu createJobsScheduleManagementMenu() {
    MenuGroup jobsScheduleManagementMenu = new MenuGroup("이달의 채용공고");

    jobsScheduleManagementMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/jobsSchedule/add"));
    jobsScheduleManagementMenu.add(new MenuItem("상세보기", "/jobsSchedule/detail"));

    return jobsScheduleManagementMenu;
  }

  // 관리자 페이지 / 일정 관리 / 이달의 시험일정 관리
  private Menu createExamScheduleManagementMenu() {

    MenuGroup examScheduleManagementMenu = new MenuGroup("이달의 시험일정");
    examScheduleManagementMenu.add(new MenuItem("생성", ACCESS_ADMIN, "/examSchedule/add"));
    examScheduleManagementMenu.add(new MenuItem("상세보기", "/examSchedule/detail"));

    return examScheduleManagementMenu;
  }

  List<ApplicationContextListener> listeners = new ArrayList<>();

  // => 옵저버(리스너)를 등록하는 메서드
  public void addApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.add(listener);
  }

  // => 옵저버(리스너)를 제거하는 메서드
  public void removeApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.remove(listener);
  }

  private void notifyOnApplicationStarted() {
    HashMap<String, Object> params = new HashMap<>();
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(params);
    }
  }

  void service() {
    notifyOnApplicationStarted();
    createMainMenu().execute();
    Prompt.close();
    sqlSession.close();
  }

  public static void main(String[] args) throws Exception {
    ClientAppJJ app = new ClientAppJJ();
    app.addApplicationContextListener(new AppInitListener());
    app.service();
    Prompt.close();
  }
}
