package shop.noldaga.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import shop.noldaga.board.vo.BoardImg;
import shop.noldaga.board.vo.BoardVo;
import shop.noldaga.common.util.MyBatisSessionManager;

public class BoardServiceImpl implements BoardService{
	SqlSession session = MyBatisSessionManager.getSqlSession();
	
	public void write(BoardVo vo) {
		session.insert("board.write",vo);
		session.commit();
	}
	
	public List<BoardVo> list(String category, int from, int to) {
		Map<String, Object> map = new HashMap<>();
		map.put("category", category);
		map.put("from", from);
		map.put("to", to);
		return session.selectList("board.list", map);
	}

	public BoardVo get(int boardidx) {
		return session.selectOne("board.get", boardidx);
	}
	
	public void modify(BoardVo vo) {
		session.update("board.modify", vo);
		session.commit();
		
	}
	
	public void delete(int boardidx) {
		session.delete("board.delete",boardidx);
		session.commit();
	}
	
	public List<BoardVo> listReview(int psidx, int from, int to) {
		Map<String, Object> map = new HashMap<>();
		map.put("psidx", psidx);
		map.put("from", from);
		map.put("to", to);
		return session.selectList("pension.listReview",map);
	}
	
	public List<BoardImg> listReviewImg(int boardidx){
		return session.selectList("pension.listReviewImg",boardidx);
	}
	
	public Map<String, Object> reviewOverall(int psidx) {
		return session.selectOne("pension.reviewOverall",psidx);
	}
	
	public boolean writeReview(BoardVo vo) {
		try {
			session.insert("pension.writeReview",vo);
			List<BoardImg> list = vo.getImgList();
			list.forEach(boardImg -> {
				boardImg.setBoardIdx(vo.getIdx());
				session.insert("pension.writeReviewImg",boardImg);
			});
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
		return true;
	}
}
