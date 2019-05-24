package shop.noldaga.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.common.util.ConstPool;
import shop.noldaga.common.util.Util;
import shop.noldaga.member.service.MemberServiceImpl;
import shop.noldaga.member.vo.MemberVo;

@WebServlet("/signup3")
public class Signup3 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(ConstPool.MEMBER_PATH + "/signup3.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String email = req.getParameter("email");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String address = req.getParameter("address") + " " + req.getParameter("address2");
		String tel = req.getParameter("tel");
		String ratingStr = req.getParameter("rating");
		int rating = ratingStr == null || ratingStr.equals("") ? 0 : Integer.parseInt(ratingStr);
		
		MemberVo vo = new MemberVo();
		vo.setEmail(email);
		vo.setPw(shop.noldaga.common.util.SecurityUtil.encryptSHA256(pw));
		vo.setName(name);
		vo.setAddress(address);
		vo.setTel(tel);
		vo.setRating(rating);
		new MemberServiceImpl().join(vo); 
		
		// 메일발송
		Util.sendMail(email);
		
		resp.sendRedirect("signup4");
		
	}

}
