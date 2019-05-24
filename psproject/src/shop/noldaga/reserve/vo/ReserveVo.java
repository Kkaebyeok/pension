package shop.noldaga.reserve.vo;

import lombok.Data;

@Data
public class ReserveVo {
	private int rsidx;
	private int psidx;
	private int rmidx;
	private String email;
	private String name;
	private String startdate;
	private String enddate;
	private int money;
	private int moneyunit;
	private String pstitle;
	private String rmtitle;
	private int days;
	private String tel;
}
