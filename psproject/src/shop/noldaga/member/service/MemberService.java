package shop.noldaga.member.service;

import shop.noldaga.member.vo.MemberVo;

/**
 * 
 *  @author 서재진
 *
 */

public interface MemberService {
	public boolean isMember(String email);

	public boolean isAuth(String email);
	
	public MemberVo login(String email, String pw);

	public void join(MemberVo vo);
	
	public boolean authenticate(String email);
	
	public void resign(String email);
		
	public void mypage(MemberVo vo);
}

