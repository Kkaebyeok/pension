package shop.noldaga.board.vo;


import java.util.List;

import lombok.Data;

@Data
public class BoardVo {
	private int idx;
	private String title;
	private String cont;
	private String regdate;
	private String email;
	
	private int cate; // 1. 공지 , 2. 후기
	
	private int psIdx;	//후기 작성 시 참조키
	private int score;	// 평점
	
	private List<BoardImg> imgList;
}
