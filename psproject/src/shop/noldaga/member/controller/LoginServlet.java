package shop.noldaga.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.noldaga.common.util.ConstPool;
import shop.noldaga.member.service.MemberServiceImpl;
import shop.noldaga.member.vo.MemberVo;

/**
 * 
 * @author 서재진
 * 
 * 
 */


@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(ConstPool.MEMBER_PATH+"/login.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pw = req.getParameter("pw");
		MemberServiceImpl dao = new MemberServiceImpl();
		MemberVo vo =  new MemberServiceImpl().login(email,pw);
		
		if (vo ==null) { // 로그인 실패
			resp.sendRedirect("login?message=fail");
		}
		else if (!dao.isAuth(email)) {
			resp.sendRedirect("message?msg=notAuth&email=" + email);
		}
		else { // 로그인 성공
			HttpSession session = req.getSession();
			session.setAttribute("member", vo);
			resp.sendRedirect("index");
		}
	}
}