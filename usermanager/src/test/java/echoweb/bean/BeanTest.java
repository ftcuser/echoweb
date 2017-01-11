package echoweb.bean;

import static org.junit.Assert.*;

import org.junit.Test;

public class BeanTest {
	
	@Test
	public void testWebUserBean() {
		WebUserBean userBean = new WebUserBean("joe@gmail.com", "Joe", "Doe");
		assertEquals("joe@gmail.com", userBean.getEmail());
		assertEquals("Joe", userBean.getFirstName());
		assertEquals("Doe", userBean.getLastName());
		
	}

}
