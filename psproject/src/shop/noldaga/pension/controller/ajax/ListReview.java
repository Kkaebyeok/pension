package shop.noldaga.pension.controller.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import shop.noldaga.board.service.BoardServiceImpl;
import shop.noldaga.board.vo.BoardVo;
import shop.noldaga.common.util.Pagination;
import shop.noldaga.common.util.Util;

@WebServlet("/listReview")
public class ListReview extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int psidx = Util.getParameterNumber(req.getParameter("psidx"));
		Pagination pagination = new Pagination(10, 10, 300, 1);
		BoardServiceImpl dao = new BoardServiceImpl();
		List<BoardVo> reviews = dao.listReview(psidx, pagination.getFrom(), pagination.getTo());
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(new Gson().toJson(reviews));
		
		
	}
	
}
