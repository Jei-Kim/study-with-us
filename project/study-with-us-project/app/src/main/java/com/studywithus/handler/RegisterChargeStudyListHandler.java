package com.studywithus.handler;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Study;

public class RegisterChargeStudyListHandler implements Command {

  HashMap<String, List<Study>> registerChargeStudyMap;

  public RegisterChargeStudyListHandler(HashMap<String, List<Study>> registerChargeStudyMap) {
    this.registerChargeStudyMap = registerChargeStudyMap;
  }

  @Override
  public void execute() {
    System.out.println("[마이 페이지 / 나의 활동 / 내 스터디 / 생성 유료 스터디]");
    List<Study> registerChargeStudyList = registerChargeStudyMap.get(AuthLoginHandler.getLoginUser().getId());

    for (Study chargeStudy : registerChargeStudyList) {
      System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", chargeStudy.getNo(), chargeStudy.getTitle(),
          chargeStudy.getWriter().getName(), chargeStudy.getRegisteredDate(), chargeStudy.getViewCount(),
          chargeStudy.getLike());
    }
  }
}
