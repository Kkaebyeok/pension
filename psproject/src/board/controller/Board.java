package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardDao;
import board.vo.BoardVo;
import common.util.ConstPool;
import common.util.Pagination;
import common.util.Util;

@WebServlet("/boardList")
public class Board extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String category = Util.getParameterNumber(req.getParameter("cate")) + "";
		Pagination pagination = new Pagination(20, 10, 300, 1);
//		System.out.println(pagination);
		List<BoardVo> list = new BoardDao().list(category, pagination.getFrom(), pagination.getTo());
//		System.out.println(list);
		
		req.setAttribute("list", list);
		req.getRequestDispatcher(ConstPool.BOARD_PATH + "/list.jsp").forward(req, resp);
	}

}
