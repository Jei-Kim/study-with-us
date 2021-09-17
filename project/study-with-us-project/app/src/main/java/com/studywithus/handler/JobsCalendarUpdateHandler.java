package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.util.Prompt;

public class JobsCalendarUpdateHandler extends AbstractCalendarHandler {

  public JobsCalendarUpdateHandler(List<Calendar> jobsCalendarList) {
    super(jobsCalendarList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[이달의 채용공고 / 수정]\n");

    int no = Prompt.inputInt("번호: ");
    Calendar jobsCalendar = findByNo(no);

    System.out.println();

    if (jobsCalendar == null) {
      System.out.println();
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", jobsCalendar.getTitle()));
    String content = Prompt.inputString(String.format("[%s] 수정할 내용: ", jobsCalendar.getContent()));
    String input = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");

    System.out.println();

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println();
      System.out.println("채용공고 수정을 취소하였습니다.");
      return;
    }

    jobsCalendar.setTitle(title);
    jobsCalendar.setContent(content);

    System.out.println();
    System.out.println("채용 공고를 수정하였습니다.");
  }
}
