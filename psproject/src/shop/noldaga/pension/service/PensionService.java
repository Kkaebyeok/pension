
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

import java.util.List;
import java.util.Map;
import shop.noldaga.pension.vo.PensionVo;
import shop.noldaga.reserve.vo.ReserveVo;

public interface PensionService {
	// index에 쓰이는 메서드
	public List<PensionVo> indexList();
	
	//type별 펜션에서 사용되는 메서드
	public List<PensionVo> readList();
	
	public List<PensionVo> selectPension(String search,int type, int from, int to);
	
	public int selectPensionCount(String search, int type);
	
	public List<Map<String, String>> detailPension(String psidx, String startDate, String endDate);
	
	public int roomImgCount(int rmidx);

	public boolean reserve(ReserveVo vo);

	public ReserveVo reserveDetail(ReserveVo vo);
	
}
