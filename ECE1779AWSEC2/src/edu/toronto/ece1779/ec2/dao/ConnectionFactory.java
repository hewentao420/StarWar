package edu.toronto.ece1779.ec2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.cpdsadapter.DriverAdapterCPDS;
import org.apache.tomcat.dbcp.dbcp.datasources.SharedPoolDataSource;

public class ConnectionFactory {
	
	String driverClassName = "com.mysql.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://ece1779db.cf2zhhwzx2tf.us-east-1.rds.amazonaws.com/ece1779group4";
	String dbUser = "group4";
	String dbPwd = "6954992182";
	
	SharedPoolDataSource dbcp;
	
	private static ConnectionFactory connectionFactory = null;
	
    private ConnectionFactory() {
        try {

        	DriverAdapterCPDS ds = new DriverAdapterCPDS();
 		    ds.setDriver(driverClassName);
 		    ds.setUrl(connectionUrl);
 		    ds.setUser(dbUser);
 		    ds.setPassword(dbPwd);

 		    dbcp = new SharedPoolDataSource();
 		    dbcp.setConnectionPoolDataSource(ds);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return dbcp.getConnection();
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
                connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }
    
}
