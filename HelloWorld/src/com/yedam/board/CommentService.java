package com.yedam.board;

import java.util.List;
import java.util.Scanner;

public class CommentService {
	private static Scanner scn = new Scanner(System.in);
    private static CommentJdbc commentDao = new CommentJdbc();

    
    // 댓글 달기
    public static void addComment(int boardSerial, int userSerial) {
		System.out.print("댓글 내용 입력(공백 시 취소) >> ");
		String content = scn.nextLine();
		if (content.isBlank()) {
			System.out.println("취소 되었습니다. ");
			return; // 메소드 종료.
		}
		Comment comment = new Comment(boardSerial, content, userSerial);
		if (commentDao.boardCommentInsert(comment)) {
			System.out.println("댓글 등록이 완료되었습니다..");
		} else {
			System.out.println("등록이 실패하였습니다.");
		}
	}

    // 댓글 삭제
    public static void myCommentDelete(int userSerial) {
		System.out.println("삭제를 진행할 댓글 번호를 입력해주세요. (삭제취소 : 0)");
		while (true) {
			System.out.print("선택 >> ");
			String num = scn.nextLine();
			if (commentDao.myCommentDelete(Integer.parseInt(num), userSerial)) {
				System.out.println("댓글번호 : " + num + " 댓글이 삭제되었습니다.");
				break;
			} else if (Integer.parseInt(num) == 0) {
				System.out.println("취소되었습니다.");
				break;
			} else {
				System.out.println("없는 댓글번호입니다. 다시 입력해주세요.");
				continue;
			}
		}
	}
 
    // 게시물 댓글 보기
	public static void boardComment(int serial) {
		List<Comment> list = commentDao.boardComment(serial);
		System.out.println("<===== 댓글 영역 =====>");
		for (Comment comment : list) {
			if (comment != null) {
				System.out.println(comment.boardCommentShowU() + commentDao.commentUserName(comment.getCommentSerial()) + comment.boardCommentShowD());
			}
		}
		System.out.println("<===== 댓글 영역 =====>");
	}

    // 마이페이지 - 내 댓글 리스트
	public static void myCommentList(int serial) {
		List<Comment> list = commentDao.myCommentsList(serial);
		int seqNo = 1;
		System.out.println("==========================================");
		for (Comment comment : list) {
			if (comment != null) {
				System.out.println("<" + seqNo++ + "> " + comment.myCommentListShow());
				System.out.println("==========================================");
			}
		}
	}
	
}
