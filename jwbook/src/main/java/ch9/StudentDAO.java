package ch9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	Connection conn = null;
	PreparedStatement ps;
	
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db";
	
	public void open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void close() {
		try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void insert(Student s) {
		open();
		String sql = "INSERT INTO student(username, univ, birth, email) values(?, ?, ?, ?)";
	
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, s.getUsername());
			ps.setString(2, s.getUniv());
			ps.setDate(3, s.getBirth());
			ps.setString(4, s.getEmail());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}		
	}
	
	public List<Student> getAll() {
		open();
		List<Student> students = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement("select * from student");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setUsername(rs.getString("username"));
				s.setUniv(rs.getString("univ"));
				s.setBirth(rs.getDate("birth"));
				s.setEmail(rs.getString("email"));
				
				students.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return students;
	}
}
