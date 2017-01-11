package echoweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import echoweb.bean.WebUserBean;
import echoweb.service.WebUserService;

@Controller
public class MainController extends AbstractController{
	
	@Autowired
	WebUserService userService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return null;
	}
	
	@RequestMapping("/listusers")
	@ResponseBody
	public  List<WebUserBean> listUsers(){
		
		return userService.getUserList();
	}
	
	@RequestMapping("/adduser")
	@ResponseBody
	public  List<WebUserBean> addUser(@RequestBody WebUserBean userBean){
		userService.updateUser(userBean);
		return userService.getUserList();
	}

	@RequestMapping("/deleteuser")
	@ResponseBody
	public  List<WebUserBean> deleteUser(@RequestBody WebUserBean userBean){
		userService.deleteUser(userBean.getEmail());
		return userService.getUserList();
	}
}
