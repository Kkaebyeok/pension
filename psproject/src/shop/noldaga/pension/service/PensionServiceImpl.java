
/**
 * 
 *  @author 장우영
 *	
 *
 *
 *
 *  
 */

package shop.noldaga.pension.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import shop.noldaga.common.util.MyBatisSessionManager;
import shop.noldaga.pension.vo.PensionVo;
import shop.noldaga.reserve.vo.ReserveVo;

public class PensionServiceImpl implements PensionService{
	SqlSession session = MyBatisSessionManager.getSqlSession();
	
	
	// index에 쓰이는 메서드
	public List<PensionVo> indexList() {
		List<PensionVo> pensions = session.selectList("pension.indexList");
		return pensions;
 	}
	
	//type별 펜션에서 사용되는 메서드
	public List<PensionVo> readList() {
		return session.selectList("pension.readList");
	}
	
	public List<PensionVo> selectPension(String search,int type, int from, int to) {
		Map<String, Object> map = new HashMap<>();
		map.put("search", search);
		map.put("type", type);
		map.put("from", from);
		map.put("to", to);
		return session.selectList("pension.selectPension",map);
	}
	
	public int selectPensionCount(String search, int type) {
		Map<String, Object> map = new HashMap<>();
		map.put("search", search);
		map.put("type", type);
		return session.selectOne("pension.selectPensionCount",map);
		
	}	
	
	public List<Map<String, String>> detailPension(String psidx, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		map.put("psidx", psidx);
		map.put("startdate", startDate);
		map.put("enddate", endDate);
		return session.selectList("pension.detailPension",map);
	}
	
	
	public int roomImgCount(int rmidx) {
		return session.selectOne("pension.roomImgCount",rmidx);
	}

	public boolean reserve(ReserveVo vo) {
		return session.insert("reserve.reserve",vo) >0;
	}

	public ReserveVo reserveDetail(ReserveVo vo) {
		return session.selectOne("reserve.reserveDetail", vo);
	}
	
}
