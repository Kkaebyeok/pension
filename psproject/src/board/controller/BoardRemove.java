package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardDao;
import common.util.Util;
import member.vo.Member;

@WebServlet("/boardRemove")
public class BoardRemove extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession() != null && req.getSession().getAttribute("member") !=null && ((Member)req.getSession().getAttribute("member")).getRating() == 2) {
			int boardIdx = Util.getParameterNumber(req.getParameter("idx"));
			new BoardDao().delete(boardIdx);
		}
		resp.sendRedirect("boardList");
	}

}
