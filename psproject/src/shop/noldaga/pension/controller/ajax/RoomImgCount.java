package shop.noldaga.pension.controller.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.common.util.Util;
import shop.noldaga.pension.service.PensionServiceImpl;

@WebServlet("/imgCnt")
public class RoomImgCount extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int rmidx = Util.getParameterNumber(req.getParameter("rmidx"));
		resp.getWriter().print(new PensionServiceImpl().roomImgCount(rmidx));
	}
}
