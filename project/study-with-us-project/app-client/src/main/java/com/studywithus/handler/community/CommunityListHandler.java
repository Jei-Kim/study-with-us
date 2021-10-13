package com.studywithus.handler.community;

import java.util.Collection;

import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;

public class CommunityListHandler implements Command {

	CommunityDao communityDao;

	public CommunityListHandler(CommunityDao communityDao) {
		this.communityDao = communityDao;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[커뮤니티 / 조회]");

		Collection<Community> communityList = communityDao.findAll();

		// communityDao.request("community.selectList", null);
		if (communityList == null) {
			System.out.println("커뮤니티 게시글이 존재하지 않습니다.\n");
			return;
		}

		int categoryInfo = 0;
		int categoryQa = 0;
		int categoryTalk = 0;

		for (Community community : communityList) {
			if (community.getCategory() == 1) {
				System.out.println();
				if (categoryInfo == 0) {
					System.out.println("*** 정보 ***");
				}
				System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
						community.getNo(), community.getTitle(), community.getWriter().getEmail(),
						community.getRegisteredDate(), community.getViewCount(), community.getLike());

				categoryInfo++;

			} 
			else if (community.getCategory() == 2) {
				if (categoryQa == 0) {
					System.out.println();
					System.out.println("*** 질문 ***");
				}
				System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
						community.getNo(), community.getTitle(), community.getWriter().getEmail(),
						community.getRegisteredDate(), community.getViewCount(), community.getLike());

				categoryQa++;

			}
			else if (community.getCategory() == 3) {
				if (categoryTalk == 0) {
					System.out.println();
					System.out.println("*** 스몰톡 ***");
				}
				System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
						community.getNo(), community.getTitle(), community.getWriter().getEmail(),
						community.getRegisteredDate(), community.getViewCount(), community.getLike());

				categoryTalk++;

			} else {
				return;
			}
		} System.out.println();
	}
}
