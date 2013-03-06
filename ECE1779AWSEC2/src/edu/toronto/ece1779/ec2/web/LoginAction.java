package edu.toronto.ece1779.ec2.web;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.toronto.ece1779.ec2.entity.User;
import edu.toronto.ece1779.ec2.entity.Worker;
import edu.toronto.ece1779.ec2.service.ManagerService;
import edu.toronto.ece1779.ec2.service.ManagerServiceImpl;
import edu.toronto.ece1779.ec2.service.UserService;
import edu.toronto.ece1779.ec2.service.UserServiceImpl;

public class LoginAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	public static final String USER_LOGIN_SUCCESS = "user_login_success";
	public static final String MANAGER_LOGIN_SUCCESS = "manager_login_success";
	public static final String LOGIN_FAILURE = "login_failure";
	
	private String username;
    private String password; 
    private String retypedPassword;
    private List<Worker> workers;
 
    
    public String authenticate() {
    	String managerName = ServletActionContext.getServletContext().getInitParameter("managerName");
    	String managerPassword = ServletActionContext.getServletContext().getInitParameter("managerPassword");

    	UserService userService = new UserServiceImpl();
    	Map<String,Object> session = ServletActionContext.getContext().getSession();
    	
    	if(managerName.equals(username) && managerPassword.equals(password)) {
    		User user = new User(username, password);
    		session.put("user", user);
        	session.put("type","manager");
    		ManagerService managerService = new ManagerServiceImpl();	
    		this.workers = managerService.retrieveRunningWorkersInfo();
    		return MANAGER_LOGIN_SUCCESS;
    	}
    	
    	User user = new User(username, password);
    	boolean isAuthenticated = userService.authenticate(user);
    	if(isAuthenticated){
    		user = userService.getUser(username);
    		session.put("user", user);
        	session.put("type","user");
    		return USER_LOGIN_SUCCESS;
    	}
    	else {
    		addActionError(getText("failure.login"));
    		return LOGIN_FAILURE;
    	}
    }
    
    
    public String createAccount() {
    	if(!password.equals(retypedPassword)) {
    		addActionError(getText("failure.password.not.match"));
    		return LOGIN_FAILURE;
    	}
    	
    	UserService userService = new UserServiceImpl();
    	Map<String,Object> session = ServletActionContext.getContext().getSession();
    	
    	User user = new User(username, password);	
    	boolean isExistingUserName = userService.createAccount(user);
    	if(isExistingUserName){
    		addActionError(getText("failure.createAccount"));
    		return LOGIN_FAILURE;
    	}
    	else{
    		User loginUser = userService.getUser(username);
    		session.put("user", loginUser);
        	session.put("type","user");
    		return USER_LOGIN_SUCCESS;
    	}
    }
    
    public String enterLoginPage(){
    	return SUCCESS;
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

	public String getRetypedPassword() {
		return retypedPassword;
	}

	public void setRetypedPassword(String retypedPassword) {
		this.retypedPassword = retypedPassword;
	}

	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}
    
	
}
