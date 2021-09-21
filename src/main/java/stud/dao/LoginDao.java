package stud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import stud.model.Login;
import stud.model.Student;

public class LoginDao {
	public static Connection getConnection() {
		Connection con =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public Student chackUser(Login user) {
		Student stud = null;
		Connection con =null;
		PreparedStatement st = null;
		try {
			con = getConnection();
		    String sql = "SELECT s.studentid, s.name,s.mobile,s.branch,l.type FROM login l, student s where l.loginid=? and l.password=? and l.loginid=s.loginid";
		    st = con.prepareStatement(sql);
		    st.setString(1, user.getUserId());
		    st.setString(2, user.getPassword());
		    ResultSet rs = st.executeQuery();
		    if(rs.next()) {
		    	stud = new Student();
		    	stud.setStudentid(rs.getInt("s.studentid"));
		    	stud.setLoginName(user.getUserId());
		    	stud.setName(rs.getString("s.name"));
		    	stud.setMobile(rs.getString("s.mobile"));
		    	stud.setBranch(rs.getString("s.branch"));
		    	stud.setType(rs.getString("l.type"));
		    }
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return stud;
	}
	public static void main (String[] args){
		try {
			Login user = new Login();
			user.setUserId("bikash@gmail.com");
			user.setPassword("bikash");
			Student stud = new LoginDao().chackUser(user);
			System.out.println("Name is" + stud.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
