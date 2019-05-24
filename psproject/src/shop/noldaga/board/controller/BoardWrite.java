package shop.noldaga.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.board.service.BoardServiceImpl;
import shop.noldaga.board.vo.BoardVo;
import shop.noldaga.common.util.ConstPool;
import shop.noldaga.member.vo.MemberVo;

@WebServlet("/boardWrite")
public class BoardWrite extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(ConstPool.BOARD_PATH + "/write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession() != null && req.getSession().getAttribute("member") !=null && ((MemberVo)req.getSession().getAttribute("member")).getRating() == 2) {
			String title = req.getParameter("title");
			String cont = req.getParameter("cont");
			String email = ((MemberVo)req.getSession().getAttribute("member")).getEmail();
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setCont(cont);
			vo.setEmail(email);
			new BoardServiceImpl().write(vo);
		}
		resp.sendRedirect("boardList");
	}
}
