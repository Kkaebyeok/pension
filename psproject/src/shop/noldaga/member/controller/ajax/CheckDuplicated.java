package shop.noldaga.member.controller.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.member.service.MemberServiceImpl;

@WebServlet("/chkMemberDup")
public class CheckDuplicated extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		resp.getWriter().print(new MemberServiceImpl().isMember(email) ? 1 : 0);
	}

}
