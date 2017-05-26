package echoweb.service;

import java.util.ArrayList;
import java.util.List;

import echoweb.bean.WebUserBean;

/*
 * This service implementation uses built in 
 * test data and don't use AWS database services
 * 
 */
public class WebUserServiceNoDBImpl implements WebUserService {
	
	private static List<WebUserBean> users = null;
	
	static {
		users = new ArrayList<WebUserBean>();
		WebUserBean user1 = new WebUserBean("joe.doe@gmail.com", "Joe", "Doe");
		WebUserBean user2 = new WebUserBean("jane.doe@gmail.com", "Jane", "Doe");
		users.add(user1);
		users.add(user2);
	}

	public List<WebUserBean> getUserList() {
		return users;
	}
	public void updateUser(WebUserBean userBean){
		//use email as key to find user, if user not find, 
		//create a new user
		boolean found = false;
		for(WebUserBean user : users) {
			if(user.getEmail().equalsIgnoreCase(userBean.getEmail())) {
				found = true;
				user.setFirstName(userBean.getFirstName());
				user.setLastName(userBean.getLastName());
				break;
			}
		}
		if(!found) {
			users.add(userBean);
		}
	}
	public void deleteUser(String email){
		for(WebUserBean user : users) {
			if(user.getEmail().equalsIgnoreCase(email)) {
				users.remove(user);
				break;
			}
		}
	}
}
