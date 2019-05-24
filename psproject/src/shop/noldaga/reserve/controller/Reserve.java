package shop.noldaga.reserve.controller;

import static shop.noldaga.common.util.Util.isDateStr;
import static shop.noldaga.common.util.Util.isNotValidDate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.common.util.ConstPool;
import shop.noldaga.common.util.Util;
import shop.noldaga.pension.service.PensionServiceImpl;
import shop.noldaga.reserve.service.ReserveServiceImpl;
import shop.noldaga.reserve.vo.ReserveVo;

@WebServlet("/reserve")
public class Reserve extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PensionServiceImpl dao = new PensionServiceImpl();
		String psidx = req.getParameter("psidx");
		String rmidx = req.getParameter("rmidx");
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		
		if (psidx == null || rmidx == null 
				|| isNotValidDate(startDate) || isNotValidDate(endDate)
				|| isDateStr(startDate) || isDateStr(endDate)
				|| startDate.compareTo(endDate) >= 0) {
			System.out.println("잘못된접근1"); // forward
			// 404로 보낼것
			req.getRequestDispatcher(ConstPool.SOURCE + "/404.jsp").forward(req, resp);
		}
		int pi = 0;
		int ri = 0;
		
		try {
			pi = Integer.parseInt(psidx);
			ri = Integer.parseInt(rmidx);

			ReserveVo vo = new ReserveVo();
			vo.setPsidx(pi);
			vo.setRmidx(ri);
			vo.setStartdate(startDate);
			vo.setEnddate(endDate);
//			System.out.println(vo);
			vo = dao.reserveDetail(vo);
//			System.out.println(vo);
			
			req.setAttribute("vo", vo);
			req.getRequestDispatcher(ConstPool.RESERVE_PATH + "/reserve.jsp").forward(req, resp);
		} catch (Exception e) {
			System.out.println("잘못된 접근2"); // forward
			// 404로 보낼것
			req.getRequestDispatcher(ConstPool.SOURCE + "/404.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ReserveVo vo = new ReserveVo();
		int psidx = Util.getParameterNumber(req.getParameter("psidx"));
		int rmidx = Util.getParameterNumber(req.getParameter("rmidx"));
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String startdate = req.getParameter("startdate");
		String enddate = req.getParameter("enddate");
		int money = Util.getParameterNumber(req.getParameter("money"));
		int moneyunit = Util.getParameterNumber(req.getParameter("moneyunit"));
		String pstitle = req.getParameter("pstitle");
		String rmtitle = req.getParameter("rmtitle");
		int days = Util.getParameterNumber(req.getParameter("days"));
		String tel = req.getParameter("tel");
		 
		vo.setPsidx(psidx);
		vo.setRmidx(rmidx);
		vo.setEmail(email);
		vo.setName(name);
		vo.setStartdate(startdate);
		vo.setEnddate(enddate);
		vo.setMoney(money);
		vo.setMoneyunit(moneyunit);
		vo.setPstitle(pstitle);
		vo.setRmtitle(rmtitle);
		vo.setDays(days);
		vo.setTel(tel);

		// session 여부 체크
		
		// 기존예약 비교
		 
		ReserveServiceImpl dao = new ReserveServiceImpl();
		
		// 예약 insert
		dao.insert(vo);

		resp.sendRedirect("ReserveResult?rsidx=" + vo.getRsidx());
			 
	}
}
