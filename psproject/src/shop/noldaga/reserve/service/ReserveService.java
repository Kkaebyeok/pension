package shop.noldaga.reserve.service;

import java.util.List;

import shop.noldaga.reserve.vo.ReserveVo;

public interface ReserveService {
	public void insert(ReserveVo vo);
	
	public ReserveVo getReserve(int rsidx);
	
	public List<ReserveVo> selectByEmail(String email);
	
	public void cancel(int rsidx);
}
