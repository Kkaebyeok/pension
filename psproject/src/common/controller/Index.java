package common.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.ConstPool;
import pension.dao.PensionDao;
import pension.vo.ChargeVo;
import pension.vo.PensionVo;
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
		List<PensionVo> list = PensionDao.getInstance().indexList();
		req.setAttribute("list", list);
		req.getRequestDispatcher(ConstPool.SOURCE + "/index.jsp").forward(req, resp);
	}
}

