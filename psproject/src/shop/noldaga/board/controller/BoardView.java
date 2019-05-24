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
import shop.noldaga.common.util.Util;

@WebServlet("/boardView")
public class BoardView extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardIdx = Util.getParameterNumber(req.getParameter("idx"));
		BoardVo vo = new BoardServiceImpl().get(boardIdx);
		
		req.setAttribute("vo", vo);
		req.getRequestDispatcher(ConstPool.BOARD_PATH + "/view.jsp").forward(req, resp);
	}

}
