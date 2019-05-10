package pension.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.ConstPool;
import pension.dao.PensionDao;

@WebServlet("/detail.do")
public class DetailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PensionDao dao = new PensionDao();
		String psidx = req.getParameter("psidx");
		String date = req.getParameter("startdate");	
		List<Map<String, String>> list = dao.detailPension(psidx, date);
		list.size();
		req.setAttribute("dao", list.get(0));
		req.setAttribute("list", list);
		req.getRequestDispatcher(ConstPool.PENSION_PATH + "/pensionDetail.jsp").forward(req, resp);
	}
}