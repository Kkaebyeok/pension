package shop.noldaga.member.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import shop.noldaga.common.util.MyBatisSessionManager;
import shop.noldaga.member.vo.MemberVo;

/**
 * 
 *  @author 서재진
 *
 */

public class MemberServiceImpl implements MemberService{
	private SqlSession session = MyBatisSessionManager.getSqlSession(true);
	
	public boolean isMember(String email) {
		return session.selectOne("member.isMember", email) == null ? false : true;
	}

	public boolean isAuth(String email) {
		return session.selectOne("member.isAuth", email) == null ? false : true;
	}
	
	public MemberVo login(String email, String pw) {
		pw = shop.noldaga.common.util.SecurityUtil.encryptSHA256(pw);
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("pw", pw);
		return session.selectOne("member.login", map);
	}

	public void join(MemberVo vo) {
		session.insert("member.join", vo);
	}
	
	public boolean authenticate(String email) {
		return session.update("member.authenticate", email) > 0;
	}
	
	public void resign(String email) {
		session.delete("member.resign", email);
	}
		
	public void mypage(MemberVo vo) {
		session.update("member.mypage", vo);
	}
}

