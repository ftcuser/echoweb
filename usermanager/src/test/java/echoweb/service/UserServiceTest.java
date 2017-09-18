package echoweb.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import echoweb.bean.WebUserBean;

public class UserServiceTest {
	
	@Test
	public void serviceTest() {
		
		WebUserService service = new WebUserServiceNoDBImpl();
		WebUserBean userBean = new WebUserBean("joe@test.com", "Joe", "Smith");
		service.updateUser(userBean);
		List<WebUserBean> users = service.getUserList();
		int userCount = 0;
		for(WebUserBean user : users) {
			if("joe@test.com".equals(user.getEmail())) {
				userCount ++;
			}
		}
		assertEquals(1, userCount);
		
		service.deleteUser(userBean.getEmail());
		userCount = 0;
		try{
			Thread.sleep(2000);
		}catch(Exception e) {
			
		}
		users = service.getUserList();
		for(WebUserBean user : users) {
			if("joe@test.com".equals(user.getEmail())) {
				userCount ++;
			}
		}
		assertEquals(0, userCount);
		
	}

}
