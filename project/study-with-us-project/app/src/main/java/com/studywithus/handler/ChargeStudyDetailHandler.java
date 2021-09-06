package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ChargeStudy;
import com.studywithus.util.Prompt;

public class ChargeStudyDetailHandler extends AbstractChargeStudyHandler{

  ChargeStudy study;


  public ChargeStudyDetailHandler(List<ChargeStudy> chargeStudyList, List<ChargeStudy> chargeInterestList) {
    super(chargeStudyList, chargeInterestList);	
  }

  @Override
  public void execute() {
    //    ChargeInterestAddHandler chargeInterestAddHandler = new ChargeInterestAddHandler(chargeInterestList);
    System.out.println("[유료 스터디 / 상세보기]\n");
    int no = Prompt.inputInt("번호? ");

    study = findByNo(no);

    if (study == null) {
      System.out.println();
      System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
      return;
    }

    System.out.printf("스터디 제목: %s\n", study.getTitle());
    System.out.printf("스터디 설명: %s\n", study.getExplanation());
    System.out.printf("지역: %s\n", study.getArea());
    System.out.printf("멘토: %s\n", study.getWriter());
    System.out.printf("가격: %s\n", study.getPrice());
    System.out.printf("등록일: %s\n", study.getRegisteredDate());

    study.setViewCount(study.getViewCount() + 1);
    System.out.printf("조회수: %d\n", study.getViewCount());


    System.out.printf("조회수: %d\n", study.getViewCount() + 1);

    System.out.println("1. 결제하기");
    System.out.println("2. 관심목록 추가하기");
    System.out.println("0. 이전");
    while(true) {

      int input = Prompt.inputInt("선택>");
      if(input == 1) {
        payHandler();
      } else if (input == 2) {
        interestAddHandler();
      } else if (input == 0) {
        return;
      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      return;
    }

  }

  private void payHandler() {
    System.out.print("[유료 스터디 / 결제]");

    String input1 = Prompt.inputString("유료 스터디를 결제 하시겠습니까? (y/N)");
    if (input1.equalsIgnoreCase("n") || input1.length() == 0) {
      System.out.println(" 유료 스터디 결제를 취소하셨습니다.");
    }
    else {
      StringBuffer dot = new StringBuffer("");

      System.out.print("------------------------------------");
      System.out.println("\n"
          + "(＼(＼     \n"
          + "(  -.- )~♥\n"
          + " O_(\")(\")");
      System.out.println("------------------------------------");
      System.out.print("결제중");
      for(int i = 0; i < 5; i++) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.print(dot.append("♥"));
      }
      System.out.println();
      System.out.println("유료 스터디 결제가 완료 되었습니다.\n");
    }
    return;
  }

  private void interestAddHandler() {
    String input = Prompt.inputString("관심 목록에 추가하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("유료 스터디 관심 목록 추가를 취소하였습니다.\n");
      return;
    }

    chargeInterestList.add(study);

    System.out.println();
    System.out.println("유료 스터디 관심 목록에 추가되었습니다.\n");
    return;
  }

}

