
/**
 * 
 *  @author 장우영
 *	
 *
 *
 *
 *  
 */

package pension.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.util.DBManager;
import pension.vo.CartVo;
import pension.vo.ChargeVo;
import pension.vo.PensionVo;
import pension.vo.RoomVo;
import pension.vo.RoomimgVo;

public class PensionDao {
public static PensionDao dao = new PensionDao();
	
	public static PensionDao getInstance() {
		return dao;
	}

	public List<PensionVo> readList() {
		String sql = "select  PSTITLE,preaddr,psidx,oridx from  PENSION WHERE ROWNUM <= 9 order by ORIDX";
		Connection conn;
		PreparedStatement pstmt;

		ResultSet rs;
		PensionVo vo = null;
		ArrayList<PensionVo> list = new ArrayList<>();
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new PensionVo();
				vo.setPstitle(rs.getString(1));
				vo.setPreaddr(rs.getString(2));
				vo.setPsidx(rs.getInt(3));
				vo.setOridx(rs.getString(4));
				list.add(vo);
			}
			conn.close();
			pstmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<PensionVo> selectPension(String search,int type, int from, int to) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select rownum rn, a.* from( ");
		sql.append("select p.psidx, oridx, preaddr, pstitle, min(rmsize) rmsize, min(week) lowprice ");
		sql.append("from pension p ");
		sql.append("join room r on r.psidx = p.psidx ");
		sql.append("join charge c on r.rmidx = c.rmidx and week != 0 ");
		sql.append("where preaddr like '%"+ search +"%' ");
		if (type==2) {
			sql.append("and pstitle like '%스파%' ");}
		if (type==3) {
			sql.append("and pstitle like '%풀빌라%' ");}
		sql.append("group by p.psidx, oridx, preaddr, pstitle ");
		sql.append(") a ");
		if (type==4) {
			sql.append("where rmsize >30 ");}
		sql.append("order by 2 ");
		sql.append(") where rn between ? and ?");
		ArrayList<PensionVo> list = new ArrayList<>();
		Connection conn = DBManager.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, from);
			pstmt.setInt(2, to);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PensionVo vo = new PensionVo();
				vo.setOridx(rs.getString("oridx"));
				vo.setPsidx(rs.getInt("psidx"));
				vo.setPreaddr(rs.getString("preaddr"));
				vo.setPstitle(rs.getString("pstitle"));
				vo.setLowPrice(rs.getInt("lowprice"));
				list.add(vo);
			}
			conn.close();
			pstmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int selectPensionCount(String search, int type) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from( ");
		sql.append("select oridx,psidx,preaddr,pstitle, ");
		sql.append("(select min(rmsize) from room r "); 
		sql.append("where r.psidx= p.psidx group by psidx) as rmsize ");
		sql.append("from pension p ");
		sql.append("WHERE preaddr like '%"+ search +"%' ");
		if (type == 2) {
			sql.append("and PSTITLE LIKE '%스파%' ");}
		if (type == 3) {
			sql.append("and PSTITLE LIKE '%풀빌라%' ");}	
		sql.append(") ");
		if (type == 4) {
			sql.append("where rmsize >= 30 ");}
		Connection conn = DBManager.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}	
	
	public List<Map<String, String>> detailPension(String psidx, String date) {
		StringBuilder sb = new StringBuilder();
		sb.append("select "); 
		sb.append("p.psidx, oridx, preaddr, curaddr, pstitle, calltel, pickup, longitude, latitude "); 
		sb.append(", r.rmidx, rmtitle, rmsize, rmpermin, rmpermax, rorder ");
		sb.append(",(select ");
		sb.append("case when to_char(to_date(?),'d') < 6 then week "); 
		sb.append("when to_char(to_date(?),'d') = 6 then fri ");
		sb.append("else weekend ");
		sb.append("end result ");
		sb.append("from charge c where c.rmidx = r.rmidx "); 
		sb.append("and period = ( ");
		sb.append("case when to_char(to_date(?),'MM') in ('07','08','12','01') "); 
		sb.append("then 1 ");
		sb.append("else 0 ");
		sb.append("end ");
		sb.append(")) as price ");
		sb.append("from pension p ");
		sb.append("join room r on p.psidx=r.psidx ");
		sb.append("where p.psidx = ?");
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
		List<Map<String, String>> list = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, date);
			pstmt.setString(2, date);
			pstmt.setString(3, date);
			pstmt.setString(4, psidx);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				map.put("psidx", rs.getString("psidx"));
				map.put("oridx", rs.getString("oridx"));
				map.put("preaddr", rs.getString("preaddr"));
				map.put("curaddr", rs.getString("curaddr"));
				map.put("pstitle", rs.getString("pstitle"));
				map.put("calltel", rs.getString("calltel"));
				map.put("pickup", rs.getString("pickup"));
				map.put("longitude", rs.getString("longitude"));
				map.put("latitude", rs.getString("latitude"));
				map.put("rmidx", rs.getString("rmidx"));
				map.put("rmtitle", rs.getString("rmtitle"));
				map.put("rmsize", rs.getString("rmsize"));
				map.put("rmpermin", rs.getString("rmpermin"));
				map.put("rmpermax", rs.getString("rmpermax"));
				map.put("rorder", rs.getString("rorder"));
				map.put("price", rs.getString("price"));
				list.add(map);
			}
			conn.close();
			pstmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public int roomImgCount(int rmidx) {
		String sql = "select count(*) from roomimg where rmidx = ?"; 
		int i = 0;
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rmidx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				i = rs.getInt(1);
			}
			conn.close();
			pstmt.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	
	
	// index에 쓰이는 메서드
	public List<PensionVo> indexList() {
		String sql = "select * from ( " + 
				"    select psidx, pstitle, curaddr, oridx, rownum rn, ( " + 
				"        select min(week) from room r " + 
				"        join charge c on r.rmidx = c.rmidx " + 
				"        where  p2.psidx = r.psidx " + 
				"        and week <> 0 and r.psidx = p2.psidx " + 
				"        group by r.psidx) as min_week " + 
				"    from pension p2 " + 
				"    order by 1 desc " + 
				") " + 
				"order by rn desc";
		
		PensionVo vo = null;
		ArrayList<PensionVo> pensions = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn= DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vo = new PensionVo();
				vo.setPsidx(rs.getInt(1));
				vo.setPstitle(rs.getString(2));
				vo.setCuraddr(rs.getString(3));
				vo.setOridx(rs.getString(4));
				vo.setLowPrice(rs.getInt(6));
				pensions.add(vo);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {conn.close();}
				if (pstmt != null) {pstmt.close();}
				if (rs != null) {rs.close();}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return pensions;
	}
	
	// 장바구니
	public ArrayList<CartVo> getcakt(String email) {
		String sql = "select p.pstitle,p.preaddr,p.oridx ,p.psidx " + 
				"from pension p, cart c, member m " + 
				"where c.psidx=p.psidx and  m.email=c.email  " + 
				"and m.email=? " ;
		Connection conn;
		PreparedStatement pstmt;

		ResultSet rs;
		CartVo vo = null;
		ArrayList<CartVo> cartlist = new ArrayList<>();
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new CartVo();
				vo.setPstitle(rs.getString(1));
				vo.setPreaddr(rs.getString(2));
				vo.setOridx(rs.getString(3));
				vo.setPsidx(rs.getInt(4));
				cartlist.add(vo);
			}
			conn.close();
			pstmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartlist;
	}
	
	public int caktinsert(String email,int psidx) {
		boolean b = false; 
		try {
			b = !cartselect(psidx, email);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO CART VALUES (CART_SEQ.nextval,?,?) ";
		int ret = 0;		
		Connection conn;
		PreparedStatement pstmt;
		conn = DBManager.getConnection();
		try {
			if(b) { // 없을때
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				pstmt.setInt(2, psidx);
				ret = pstmt.executeUpdate();
				pstmt.close();
			}
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public void cartdelete(int psidx,String email) {
		String sql = " DELETE CART WHERE psidx = ? ";
		Connection conn;
		PreparedStatement pstmt;
		
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,psidx);
				
			pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean cartselect(int psidx,String email) throws SQLException {
		String sql = " SELECT * FROM CART WHERE psidx = ? and email = ? " ;
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
	
		conn = DBManager.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, psidx);
		pstmt.setString(2, email);
		rs = pstmt.executeQuery();
		boolean ret = rs.next();
		conn.close();
		pstmt.close();
		rs.close();
			
		return ret;
}

	
}
