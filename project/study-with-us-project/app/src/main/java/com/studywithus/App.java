package com.studywithus;

import com.studywithus.domain.NewMember;
import com.studywithus.handler.ChargeStudyHandler;
import com.studywithus.handler.MentorHandler;
import com.studywithus.handler.NewMemberHandler;
import com.studywithus.menuList.MenuList3;
import com.studywithus.util.Prompt;

public class App {

	public static void main(String[] args) {

		MentorHandler mentorHandler = new MentorHandler();
		NewMemberHandler newMemberHandler = new NewMemberHandler();
		ChargeStudyHandler chargeStudyHandler = new ChargeStudyHandler();
		NewMember[] loginInfo = new NewMember[5];
		MenuList3 menuList = new MenuList3();

		Main : while(true) {
			int input = menuList.mainMenuList();
			if (input == 0) {
				System.out.println("종료되었습니다.");
				return;
			} else if (input == 1) { // 비회원 조회하기
				menuList.noneMemberMenuList();
				continue Main;
			} else if (input == 2) { // 로그인
				try{
					input = menuList.loginExecute(loginInfo);
				} catch (Exception e) {
					System.out.println();
					System.out.println("일치하는 로그인 정보가 없습니다.");
					continue;
				}

				if (input == 0){
					continue Main;
				} else if (input == 2) {
					menuList.adminMenuList();
					continue Main;
				}

				// 로그인 성공 후 메뉴
				Member : while(true) {
					input = menuList.memberMenuList(); 
					if (input == 0) {
						System.out.println("로그아웃이 완료되었습니다.");
						continue Main;
					} else if (input == 1) { // 무료 스터디 메뉴
						menuList.freeStudyMenuList();
						continue Member;
					}else if (input == 2) { // 2. 유료스터디 메뉴
						Charge : while (true) {
							menuList.chargeStudyMenuList();
							if (input == 1) { 
								chargeStudyHandler.add(); // 1. 생성
							} else if (input == 2) { 
								chargeStudyHandler.list(); // 2. 조회
							} else if (input == 3) {
								chargeStudyHandler.detail(); // 3. 상세보기
								while(true) {
									System.out.println("[유료 스터디 상세보기]\n");
									System.out.println("0. 이전");
									System.out.println("1. 결제하기");
									input = Prompt.inputInt("메뉴를 선택해주세요> ");

									if (input == 1) {
										String input1 = Prompt.inputString("유료 스터디를 결제 하시겠습니까? (y/N)");
										if (input1.equalsIgnoreCase("n") || input1.length() == 0) {
											System.out.println(" 유료 스터디 결제를 취소하셨습니다.");
											continue;
										}
										else {
											System.out.println("------------------------------------");
											System.out.println("결제 이용약관입니다.\n"
													+ "(＼(＼     \n"
													+ "(  -.- )~♥\n"
													+ " O_(\")(\")");
											System.out.println("------------------------------------");
											String input11 = Prompt.inputString("이용약관에 동의하십니까? (y/N) ");
											if (input11.equalsIgnoreCase("n") || input11.length() == 0) {
												System.out.println("결제 이용약관 동의를 취소하셨습니다.");
												return;
											}
										}
										System.out.println("유료 스터디 결제가 완료 되었습니다.");
									}
									continue Charge;
								} 
							}
							else if (input == 4) {
								chargeStudyHandler.update(); // 4. 수정
							} else if (input == 5) {
								chargeStudyHandler.delete(); // 5. 삭제
							} else if (input == 0) {
								continue;
							}else {
								System.out.println("잘못된 번호입니다.");
								continue Charge;
							}
						}

					} else if (input == 3) { // 관심목록 메뉴
						menuList.interestMenuList();
						continue Member;
					} else if (input == 5) {
						mentorHandler.add();
					} else if (input == 4) { // 커뮤니티 메뉴
						Community : while (true) {
							input = menuList.communityMenuList();

							if(input == 1) { // 커뮤니티-질문 메뉴 
								System.out.println("[커뮤니티 / 질문]\n");
								menuList.communityQaMenuList();
								continue Community;                 
							} else if(input == 2) { //커뮤니티-정보 메뉴
								System.out.println("[커뮤니티 / 정보]\n");
								menuList.communityInfoMenuList();
								continue Community;
							} else if(input == 3) { //커뮤니티-스몰톡 메뉴
								System.out.println("[커뮤니티 / 스몰톡]\n");
								menuList.communityTalkMenuList();
								continue Community;  
							} else if(input == 0) { // 커뮤니티 메뉴에서 이전
								continue Member;
							} else {
								System.out.println("잘못된 번호입니다.");
								continue Community;
							}
						}
					}else {
						System.out.println("잘못된 번호입니다.");
						continue;
					}
				}

				// 회원가입
			} else if (input == 3) {
				newMemberHandler.add(loginInfo);   
				continue;

				// 메인 메뉴에서 종료
			} else if (input == 4) {
				System.out.println("종료되었습니다.");
				return;
			} else {
				System.out.println("잘못된 번호입니다.");
			}
		}
	}

}