package pension.controller.ajax;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import board.dao.BoardDao;
import board.vo.BoardVo;
import common.util.Pagination;
import common.util.Util;

@WebServlet("/reviewOverall")
public class ReviewOverall extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int psidx = Util.getParameterNumber(req.getParameter("psidx"));
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(new Gson().toJson(new BoardDao().reviewOverall(psidx)));
	}
}
