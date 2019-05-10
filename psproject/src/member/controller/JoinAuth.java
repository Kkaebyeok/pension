package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.ConstPool;
import common.util.SecurityUtil;
import member.dao.MemberDao;

@WebServlet("/joinAuth")
public class JoinAuth extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		String key = req.getParameter("key");
		
		if (user == null || key == null) { // null 처리
			req.setAttribute("auth", "null");
		}
		else if (SecurityUtil.encryptSHA256(user).equals(key) && new MemberDao().isMember(user)) {
			new MemberDao().authenticate(user);
			req.setAttribute("auth", "success");
		} 
		else {
			req.setAttribute("auth", "error");
		}
		req.getRequestDispatcher(ConstPool.SOURCE + "404.jsp").forward(req, resp);
	}

}
