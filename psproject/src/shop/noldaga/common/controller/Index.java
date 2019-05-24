package shop.noldaga.common.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.common.util.ConstPool;
import shop.noldaga.pension.service.PensionServiceImpl;
import shop.noldaga.pension.vo.ChargeVo;
import shop.noldaga.pension.vo.PensionVo;
/**
 * 
 * @author 염윤호
 * 19-03-29
 * 
 * 
 */
@WebServlet("/index")
public class Index extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<PensionVo> list = new PensionServiceImpl().indexList();
//		list.forEach(System.out::println);
		req.setAttribute("list", list);
		req.getRequestDispatcher(ConstPool.SOURCE + "/index.jsp").forward(req, resp);
	}
}

