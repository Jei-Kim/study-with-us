package com.studywithus.handler.calendar;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ExamCalendarDetailHandler extends AbstractCalendarHandler {

  List<Calendar> examCalendar;

  public ExamCalendarDetailHandler(List<Calendar> examCalendarList) {
    super(examCalendarList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 시험일정 / 상세보기]\n");

    int no = Prompt.inputInt("번호를 입력하세요. > ");
    Calendar examCalendar = findByNo(no);

    System.out.println();

    if (examCalendar == null) {
      System.out.println("해당 번호의 시험일정이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", examCalendar.getTitle());
    System.out.printf("작성자: %s\n", examCalendar.getWriter().getName());
    System.out.printf("내용: %s\n", examCalendar.getContent());
    System.out.printf("시험일: %d-%d-%d\n", examCalendar.getYyyy(), examCalendar.getMm(), examCalendar.getDd());
    System.out.println();

    request.setAttribute("no", no);

    // 관리자인 경우
    if (examCalendar.getWriter() == AuthLogInHandler.getLoginUser()) {
      System.out.println("1. 변경");
      System.out.println("2. 삭제");
      System.out.println("0. 이전\n");
    }

    while (true) {
      int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");

      if (input == 1) {
        request.getRequestDispatcher("/examCalendar/update").forward(request);
        return;

      } else if (input == 2) {
        request.getRequestDispatcher("/examCalendar/delete").forward(request);
        return;

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 메뉴 번호입니다.\n");
        continue;
      }
    }
  }
}
