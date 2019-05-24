package shop.noldaga.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import shop.noldaga.board.vo.BoardVo;
import shop.noldaga.pension.vo.PensionVo;

@WebServlet("/test")
public class TestServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String resource = "shop/noldaga/mapper/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory =  new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = factory.openSession();
		
		int count = sqlSession.selectOne("board.test");
		System.out.println(count);
		
		PensionVo vo = sqlSession.selectOne("board.test2", 1);
		System.out.println(vo);
		
		PensionVo vo2 = sqlSession.selectOne("board.test3", vo);
		System.out.println(vo2);
		
		HashMap<String, String> map = new HashMap<>();
		map.put("psidx", "47");
		map.put("to", "20");
		map.put("from", "1");
		List<BoardVo> boards = sqlSession.selectList("board.test4", map);
		/*List<BoardVo> boards2 = sqlSession.selectList("board.test4", new Object[] {"47","20","1"});*/
		
		boards.forEach(System.out::println);
		/*boards2.forEach(System.out::println);*/
	}
}
