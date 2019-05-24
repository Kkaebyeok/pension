package shop.noldaga.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.board.service.BoardServiceImpl;
import shop.noldaga.common.util.Util;
import shop.noldaga.member.vo.MemberVo;

@WebServlet("/boardRemove")
public class BoardRemove extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession() != null && req.getSession().getAttribute("member") !=null && ((MemberVo)req.getSession().getAttribute("member")).getRating() == 2) {
			int boardIdx = Util.getParameterNumber(req.getParameter("idx"));
			new BoardServiceImpl().delete(boardIdx);
		}
		resp.sendRedirect("boardList");
	}

}
