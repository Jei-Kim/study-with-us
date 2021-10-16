package com.studywithus.handler.freestudy;

import com.studywithus.dao.FreeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class FreeStudyMemberApproveHandler implements Command {

  FreeStudyDao freeStudyDao;

  public FreeStudyMemberApproveHandler(FreeStudyDao freeStudyDao) {
    this.freeStudyDao = freeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int no = (int) request.getAttribute("freeNo");

    // requestAgent.request("member.selectOneByEmail", email);
    // Member Applicant = requestAgent.getObject(Member.class);
    // Applicant.setMentor(true);

    Study freeStudy = freeStudyDao.findByNo(no);

    if (freeStudy == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    AuthLogInHandler.userAccessLevel |= Menu.ACCESS_MEMBER;

    while (true) {
      String input = Prompt.inputString("해당 회원을 멤버로 승인하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println();
        System.out.println("멤버 승인이 취소되었습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {
        freeStudy.getParticipants().add(AuthLogInHandler.getLoginUser());
        freeStudyDao.update(freeStudy);
        System.out.println();
        System.out.println("멤버 승인이 완료되었습니다.");
        return;

      } else {
        System.out.println();
        System.out.println("다시 입력하세요.\n");
        continue;
      }
    }
    // System.out.println("무료스터디 멤버 승인이 완료되었습니다.");
  }
}
