package shop.noldaga.pension.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.common.util.ConstPool;
import shop.noldaga.common.util.Util;
import shop.noldaga.pension.service.PensionServiceImpl;

import static shop.noldaga.common.util.Util.*;

@WebServlet("/detail.do")
public class DetailServlet extends HttpServlet {
	Date today = new Date(); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PensionServiceImpl dao = new PensionServiceImpl();
		String psidx = req.getParameter("psidx");
		String startDate = req.getParameter("startdate");
		String endDate = req.getParameter("enddate");
		
		if (isNotValidDate(startDate) || isDateStr(startDate)) {
			startDate = new SimpleDateFormat("yyyy-MM-dd").format(today); // 오늘
		}
		
		if (isNotValidDate(endDate) || startDate.compareTo(endDate) >= 0 || isDateStr(endDate)) {
			Calendar endcal = Calendar.getInstance();
			endcal.setTime(toDate(startDate));
			endcal.add(Calendar.DATE, 1);
			endDate = new SimpleDateFormat("yyyy-MM-dd").format(endcal.getTime());
		}
		
		System.out.println("start : " + startDate);
		System.out.println("end : " + endDate);
		
		List<Map<String, String>> list = dao.detailPension(psidx, startDate, endDate);
		
		/*list.forEach(System.out::println);*/
		//list.size();
		
		req.setAttribute("dao", list.get(0));
		req.setAttribute("list", list);
		req.getRequestDispatcher(ConstPool.PENSION_PATH + "/pensionDetail.jsp").forward(req, resp);
	}
	
}