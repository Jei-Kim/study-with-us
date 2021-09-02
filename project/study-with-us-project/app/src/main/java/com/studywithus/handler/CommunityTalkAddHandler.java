package com.studywithus.handler;

import java.sql.Date;

import com.studywithus.domain.Community;
import com.studywithus.util.Prompt;

public class CommunityTalkAddHandler extends AbstractCommunityTalkAddHandler{

	public CommunityTalkAddHandler(List<CommunityTalk> communityTalkList) {
		super(communityTalkList);
	}
	// 스몰톡 게시글 생성
	public void add() {
		System.out.println("[커뮤니티 / 새 스몰톡 게시글]");

		Community community = new Community();

		community.setNo(Prompt.inputInt("번호? "));
		community.setTitle(Prompt.inputString("제목? "));
		community.setContent(Prompt.inputString("내용? "));
		community.setWriter(Prompt.inputString("작성자? "));
		community.setRegisteredDate(new Date(System.currentTimeMillis()));

		communityList.add(community);

		System.out.println();
		System.out.println("스몰톡 게시글 등록이 완료되었습니다.\n");
	}

}
