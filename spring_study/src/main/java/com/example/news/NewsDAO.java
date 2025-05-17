package com.example.news;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db";
	
	//DB연결 가져오는 메서드, DBCP 사용하는게 좋음
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//뉴스 기사 목록 전체를 가져오는 메서드
	public List<News> getAll() throws Exception {
		Connection conn = open();
		List<News> newsList = new ArrayList<>();
		
		// H2DB가 업데이트되면서 SQL 쿼리가 수정되어야만 한다.
		String sql = "SELECT aid, title, CAST(PARSEDATETIME(date, 'yyyy-MM-dd HH:mm:ss.SSSSSS') AS TIMESTAMP(0)) "
				+ "as cdate FROM news";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		try(conn; ps; rs) {
			while(rs.next()) {
				News n = new News();
				n.setAid(rs.getInt("aid"));
				n.setTitle(rs.getString("title"));
				n.setDate(rs.getString("cdate"));
				newsList.add(n);
			}
			return newsList;
		}
	}
	
	//뉴스 한 개를 클릭했을 때 세부 내용을 보여주는 메서드
	public News getNews(int aid) throws SQLException {
		Connection conn = open();
		News n = new News();
		String sql = "SELECT aid, title, img, CAST(PARSEDATETIME(date, 'yyyy-MM-dd HH:mm:ss.SSSSSS') AS TIMESTAMP(0)) "
				+ "as cdate, content from news where aid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, aid);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		try(conn; ps; rs) {
				n.setAid(rs.getInt("aid"));
				n.setTitle(rs.getString("title"));
				n.setImg(rs.getString("img"));
				n.setDate(rs.getString("cdate"));
				n.setContent(rs.getString("content"));
				ps.executeQuery();
				return n;
		}
	}
	
	//뉴스 추가 메서드
	public void addNews(News n) throws Exception {
		Connection conn = open();
		String sql = "INSERT INTO news(title, img, date, content) VALUES(?, ?, CURRENT_TIMESTAMP(), ?)";
		PreparedStatement ps = conn.prepareStatement(sql);

		try(conn; ps) {
			ps.setString(1, n.getTitle());
			ps.setString(2, n.getImg());
			ps.setString(3, n.getContent());
			ps.executeUpdate();
		}
	}
	
	//뉴스 삭제 메서드
	public void delNews(int aid) throws Exception {
		Connection conn = open();
		String sql = "DELETE FROM news WHERE aid=?";
		PreparedStatement ps = conn.prepareStatement(sql);

		try(conn; ps) {
			ps.setInt(1, aid);
			if(ps.executeUpdate() == 0) {
				throw new SQLException("DB에러 발생");
			}
		}
	}
}

