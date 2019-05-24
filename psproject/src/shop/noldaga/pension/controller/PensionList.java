package shop.noldaga.pension.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.common.util.ConstPool;
import shop.noldaga.common.util.Pagination;
import shop.noldaga.common.util.Util;
import shop.noldaga.pension.service.PensionServiceImpl;
import shop.noldaga.pension.vo.PensionVo;

/**
 * 
 * @author 장우영
 * 
 * 
 * -염윤호
 * doPost 삭제함
 * 
 * @param type 1 : 전체목록, 2 : 스파, 3 : 풀빌라, 4 : 워크샾, 5 : 전체검색
 * 
 */

@WebServlet("/list")
public class PensionList extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int type = Util.getParameterNumber(req.getParameter("type"));
		PensionServiceImpl dao = new PensionServiceImpl();
		List<PensionVo> list = dao.readList();
		req.setAttribute("list", list);
		
		// 첫 페이지 로드시 기본적으로 보여줄 리스트
		
		// 1. 일반목록
		//  -검색어
		// 2. 검색결과
		//  -검색어
		
		String search = req.getParameter("search");
	
		int page = Util.getParameterNumber(req.getParameter("page"));
		
		int num = dao.selectPensionCount(search, type); // 총게시물 수
		
		Pagination pagination = new Pagination(12, 10, num, page);
		List<PensionVo> items = dao.selectPension(search, type, pagination.getFrom(), pagination.getTo());
		/*System.out.println("===========================");
		items.forEach(System.out::println);*/
		req.setAttribute("type", type);
		req.setAttribute("search", search);
		req.setAttribute("items", items);
		req.setAttribute("num", num);
		req.setAttribute("page", pagination);
		req.getRequestDispatcher(ConstPool.PENSION_PATH + "/pensionList.jsp").forward(req, resp);
	}
}
