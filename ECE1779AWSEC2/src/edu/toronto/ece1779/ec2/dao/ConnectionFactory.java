package edu.toronto.ece1779.ec2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	String driverClassName = "com.mysql.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://ece1779db.cf2zhhwzx2tf.us-east-1.rds.amazonaws.com/ece1779group4";
	String dbUser = "group4";
	String dbPwd = "6954992182";
	
	private static ConnectionFactory connectionFactory = null;
	
    private ConnectionFactory() {
        try {
        	Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
        return conn;
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
                connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }
    
}
