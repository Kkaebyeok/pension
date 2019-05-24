package shop.noldaga.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.common.util.ConstPool;

/**
 * 
 * @author 서재진
 * 
 * 
 */

@WebServlet("/signup2")
public class Signup2 extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(ConstPool.MEMBER_PATH + "/signup2.jsp").forward(req, resp);
	}
}
