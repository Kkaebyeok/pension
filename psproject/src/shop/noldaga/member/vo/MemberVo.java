package shop.noldaga.member.vo;

import lombok.Data;

/**
 * 
 * @author 서재진
 * 
 */

@Data
public class MemberVo {
	private String email;
	private String pw;
	private String name;
	private String address;
	private String tel;
	private int rating;
    private String joindate;
    private boolean auth;
}
