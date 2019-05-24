package shop.noldaga.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.noldaga.member.service.MemberServiceImpl;
import shop.noldaga.member.vo.MemberVo;

@WebServlet("/resign")

public class ResignServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session  =req.getSession();
		if (session.getAttribute("member") == null) {
			resp.sendRedirect("modify");
		}
		
		MemberVo vo = (MemberVo)session.getAttribute("member");
		new MemberServiceImpl().resign(vo.getEmail());
		session.invalidate();
		
		resp.sendRedirect("index");
	}
}

	



	

