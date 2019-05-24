package shop.noldaga.board.service;

import java.util.List;
import java.util.Map;

import shop.noldaga.board.vo.BoardImg;
import shop.noldaga.board.vo.BoardVo;

public interface BoardService {
	public void write(BoardVo vo);
	
	public List<BoardVo> list(String category, int from, int to);

	public BoardVo get(int boardidx);
	
	public void modify(BoardVo vo);
	
	public void delete(int boardidx);
	
	public List<BoardVo> listReview(int psidx, int from, int to);
	
	public List<BoardImg> listReviewImg(int boardidx);
	
	public Map<String, Object> reviewOverall(int psidx);
	
	public boolean writeReview(BoardVo vo);
}
