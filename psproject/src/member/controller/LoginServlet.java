package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.util.ConstPool;
import member.dao.MemberDao;
import member.vo.Member;

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
		MemberDao dao = new MemberDao();
		
		if (dao.isAuth(email)) {
			Member vo =  new MemberDao().login(email,pw);

			if (vo ==null) { // 로그인 실패
				resp.sendRedirect("login?message=fail");
			}
			else { // 로그인 성공
				HttpSession session = req.getSession();
				session.setAttribute("member", vo);
				resp.sendRedirect("index");
			}
		}
		else {
			resp.sendRedirect("message?msg=notAuth&email=" + email);
		}
	}
}