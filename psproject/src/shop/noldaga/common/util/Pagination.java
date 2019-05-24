package shop.noldaga.common.util;

import lombok.Data;

@Data
public class Pagination {
	private int ppv; // Page per view 12개
	private int pageRange; // 한페이지에 보여줄 페이징범위
	private int totalCnt; // 총 게시글 수
	private int currPage; // 현재 페이지 번호
	
	// 계산 이후 결과값
	private int pageCnt; // 페이지 갯수
	private int from; // 쿼리 사용시 시작 위치
	private int to; // 쿼리 사용시 종료 위치
	private int fromPage; // 화면표시에 사용될 시작 페이지번호
	private int toPage; // 화면표시에 사용될 종료 페이지번호
	
	public Pagination(int ppv, int pageRange, int totalCnt, int currPage) {
		super();
		this.ppv = ppv;
		this.pageRange = pageRange;
		this.totalCnt = totalCnt;
		this.currPage = currPage;

		pageCnt = (totalCnt-1)/ ppv + 1;
		
		to = ppv * currPage;
		from = to - ppv + 1;
		
		toPage = ((currPage -1)/ pageRange + 1) * pageRange;
		fromPage = toPage - pageRange + 1;
		toPage = toPage > pageCnt ? pageCnt : toPage;
	}
}
