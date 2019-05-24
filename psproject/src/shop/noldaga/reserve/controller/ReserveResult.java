package shop.noldaga.reserve.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.common.util.ConstPool;
import shop.noldaga.reserve.service.ReserveServiceImpl;

@WebServlet("/ReserveResult")
public class ReserveResult extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int rsidx = 0;
		if (req.getParameter("rsidx") != null) {
			try {
				rsidx = Integer.parseInt(req.getParameter("rsidx"));
			} catch (NullPointerException | NumberFormatException e) {}
		}
		req.setAttribute("vo", new ReserveServiceImpl().getReserve(rsidx));
		req.getRequestDispatcher(ConstPool.RESERVE_PATH + "/result.jsp").forward(req, resp);
	}

}
