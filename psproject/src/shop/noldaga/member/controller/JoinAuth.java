package shop.noldaga.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.common.util.ConstPool;
import shop.noldaga.common.util.SecurityUtil;
import shop.noldaga.member.service.MemberServiceImpl;

@WebServlet("/joinAuth")
public class JoinAuth extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		String key = req.getParameter("key");
		
		if (user == null || key == null) { // null 처리
			req.setAttribute("auth", "null");
		}
		else if (SecurityUtil.encryptSHA256(user).equals(key) && new MemberServiceImpl().isMember(user)) {
			new MemberServiceImpl().authenticate(user);
			req.setAttribute("auth", "success");
		} 
		else {
			req.setAttribute("auth", "error");
		}
		req.getRequestDispatcher(ConstPool.SOURCE + "404.jsp").forward(req, resp);
	}

}
