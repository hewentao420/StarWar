package edu.toronto.ece1779.ec2.web;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	public static final String USER_LOGIN_SUCCESS = "user_login_success";
	public static final String MANAGER_LOGIN_SUCCESS = "manager_login_success";
	public static final String LOGIN_FAILURE = "login_failure";
	
	private String username;
    private String password; 
 
    public String authenticate() {
    	//TODO user name needs to be retrieved from DB while manager name from web.xml
        if (this.username.equals("Tom") 
                && this.password.equals("Tom")) {
            return USER_LOGIN_SUCCESS;
        }   
        else if (this.username.equals("Jack") 
                && this.password.equals("Jack")) {
            return MANAGER_LOGIN_SUCCESS;
        }
        else {
            addActionError(getText("failure.login"));
        	return LOGIN_FAILURE;
        }
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}
