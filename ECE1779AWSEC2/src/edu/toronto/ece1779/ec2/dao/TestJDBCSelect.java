package edu.toronto.ece1779.ec2.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class TestJDBCSelect {
	public static void main(String args[]){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://ece1779db.cf2zhhwzx2tf.us-east-1.rds.amazonaws.com/ece1779group4";
			Connection con = DriverManager.getConnection(url, "group4", "6954992182");
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery("select * from users");	
		    while(rs.next()){
		    	String name=rs.getString("login");
		    	String address=rs.getString("password");
		    	System.out.println(name+" "+ address);
		    }
		    rs.close();
		    stmt.close();
		    con.close();
		}catch (Exception e){
			System.out.println(e);
		}
	}
}
