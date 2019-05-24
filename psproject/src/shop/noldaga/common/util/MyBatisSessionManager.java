package shop.noldaga.common.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSessionManager {
	private static SqlSessionFactory factory = null;
	
	static {
		String resource = "shop/noldaga/mapper/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		factory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public static SqlSession getSqlSession() {
		return factory.openSession();
	}
	
	public static SqlSession getSqlSession(boolean commit) {
		return factory.openSession(true);
	}
	
}
