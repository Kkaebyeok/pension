package shop.noldaga.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.common.util.ConstPool;
import shop.noldaga.member.service.MemberServiceImpl;
import shop.noldaga.member.vo.MemberVo;

@WebServlet("/mypage")
public class Mypage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(ConstPool.MEMBER_PATH + "/mypage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");	
		String name = req.getParameter("name");
		String address= req.getParameter("address");
		String tel= req.getParameter("tel");

	    MemberVo vo = new MemberVo();
        vo.setEmail(email); 
		vo.setName(name);
		vo.setAddress(address);
		vo.setTel(tel);
		
		new MemberServiceImpl().mypage(vo);
		req.getSession().setAttribute("member", vo);
		
		resp.sendRedirect("mypage");
	}
}
