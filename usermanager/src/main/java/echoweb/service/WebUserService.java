package echoweb.service;

import java.util.List;

import echoweb.bean.WebUserBean;

public interface WebUserService {
	public List<WebUserBean> getUserList();
	public void updateUser(WebUserBean userBean);
	public void deleteUser(String email);
}
