package shop.noldaga.pension.vo;

import lombok.Data;

/**
 * 
 * @author 장우영
 * 
 * 
 * -염윤호
 * 펜션 방별 최저가 가격 추가됨(lowPrice)
 */

@Data
public class PensionVo {
	private int psidx;
	private String pstitle;
	private String pscont;
	private int comnum;
	private int acc;
	private String preaddr;
	private String curaddr;
	private String pickup;
	private String calltel;
	private String email;
	private String longitude;
	private String latitude;
	private String oridx;
	private int lowPrice;
	
}