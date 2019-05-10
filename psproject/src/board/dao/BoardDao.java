package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import board.vo.BoardVo;
import common.util.DBManager;
import common.util.Util;

public class BoardDao  {
	
	public void write(BoardVo vo) {
		String sql = "insert into board(boardidx, title, cont, email) values (board_seq.nextval, ?, ?, ?)";
		Connection conn= DBManager.getConnection();
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int idx = 0;
			pstmt.setString(++idx, vo.getTitle());
			pstmt.setString(++idx, vo.getCont());
			pstmt.setString(++idx, vo.getEmail());
			pstmt.executeUpdate();
			DBManager.close(conn, pstmt);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<BoardVo> list(String category, int from, int to) {
		String sql = "select * from ( " + 
				"    select rownum rn, boardidx, title, cont, category, " + 
				"    regdate, email, score, psidx " + 
				"    from board " + 
				"	 where category = ? " + 
				"    and rownum <= ? " + 
				"    ) " + 
				"where rn >= ?";
		BoardVo vo = null;
		List<BoardVo> list = new ArrayList<>();
		Connection conn = DBManager.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, to);
			pstmt.setInt(3, from);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVo();
				vo.setIdx(rs.getInt("boardidx"));
				vo.setTitle(rs.getString("title"));
				vo.setCont(rs.getString("cont"));
				vo.setCate(rs.getInt("category"));
				vo.setRegdate(Util.displayTime(rs.getTimestamp("regdate")));
				vo.setEmail(rs.getString("email"));
				if (category.equals("3")) {
					vo.setScore(rs.getInt("score"));
					vo.setPsIdx(rs.getInt("psidx"));
				}
				list.add(vo);
			}
			DBManager.close(conn, pstmt,rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<BoardVo> listReview(int psidx, int from, int to) {
		String sql = "select * from( " + 
				"    select boardidx, title ,cont ,score, " + 
				"    case " + 
				"        when sysdate - regdate < 1 then to_char(regdate,'hh24:mi:ss') " + 
				"        else to_char(regdate, 'yy/mm/dd') " + 
				"        end regdate, " + 
				"    psidx, rownum rn, " + 
				"    concat(substr(email,1,3),'*****') email " + 
				"    from board " + 
				"    where category = 2 and psidx = ? and rownum <=? " + 
				")where rn >= ?";
		BoardVo vo = null;
		List<BoardVo> list = new ArrayList<>();
		Connection conn = DBManager.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, psidx);
			pstmt.setInt(2, to);
			pstmt.setInt(3, from);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVo();
				vo.setIdx(rs.getInt("boardidx"));
				vo.setTitle(rs.getString("title"));
				vo.setCont(rs.getString("cont"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setEmail(rs.getString("email"));
				vo.setScore(rs.getInt("score"));
				vo.setPsIdx(rs.getInt("psidx"));
				list.add(vo);
			}
			DBManager.close(conn, pstmt,rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Map<String, Object> reviewOverall(int psidx) {
		String sql = "with tmp as ( " + 
				"    select score from board where category = 2 and psidx = ? " + 
				") " + 
				"select a.*, (select round(avg(score*2))/2 from tmp) as avg, " + 
				"    (select count(score) from tmp) as cnt " + 
				"from( " + 
				"    select * from tmp pivot (count(score) for score in (1,2,3,4,5)) " + 
				") a";
		Map<String, Object> map = null;
		Connection conn = DBManager.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, psidx);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				List<Integer> list = new ArrayList<>();
				list.add(rs.getInt("5"));
				list.add(rs.getInt("4"));
				list.add(rs.getInt("3"));
				list.add(rs.getInt("2"));
				list.add(rs.getInt("1"));
				map.put("avg", rs.getString("avg"));
				map.put("cnt", rs.getString("cnt"));
				map.put("score", list);
			}
			DBManager.close(conn, pstmt,rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public BoardVo get(int boardidx) {
		String sql = "select rownum rn, boardidx, title, cont, category, regdate, email, score, psidx from board where boardidx = ?";
		BoardVo vo = null;
		Connection conn = DBManager.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardidx);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVo();
				vo.setIdx(rs.getInt("Boardidx"));
				vo.setTitle(rs.getString("title"));
				vo.setCont(rs.getString("cont"));
				vo.setCate(rs.getInt("category"));
				vo.setRegdate(Util.displayTime(rs.getTimestamp("regdate")));
				vo.setEmail(rs.getString("email"));
				if (vo.getCate() == 3) {
					vo.setScore(rs.getInt("score"));
					vo.setPsIdx(rs.getInt("psidx"));
				}
			}
			DBManager.close(conn, pstmt,rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public void modify(BoardVo vo) {
		String sql = "UPDATE BOARD SET TITLE = ?, cont = ? WHERE BOARDIDX = ?";
		Connection conn= DBManager.getConnection();
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int idx = 0;
			pstmt.setString(++idx, vo.getTitle());
			pstmt.setString(++idx, vo.getCont());
			pstmt.setInt(++idx, vo.getIdx());
			pstmt.executeUpdate();
			DBManager.close(conn, pstmt);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
		
	public void delete(int boardidx) {
		String sql = "DELETE BOARD WHERE BOARDIDX = ?";
		Connection conn = DBManager.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardidx);
			pstmt.executeUpdate();
			DBManager.close(conn, pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
}
