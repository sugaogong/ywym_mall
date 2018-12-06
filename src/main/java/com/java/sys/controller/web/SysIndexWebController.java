package com.java.sys.controller.web;

import com.java.entity.YwymLogHandle;
import com.java.service.YwymLogHandleService;
import com.java.sys.entity.SysUser;
import com.java.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@ApiIgnore
@SpringBootApplication
@RequestMapping("/sys/indexWebController")
public class SysIndexWebController {

	@Resource
	private SysUserService userService;
	@Resource
	private YwymLogHandleService logHandleService;

	@RequestMapping("/")
	public String goLogin(){
		return "redirect:/sys/indexWebController/login";
	}
	
	@RequestMapping("/index")
	public String index(Model model){
		Subject subject = SecurityUtils.getSubject();
		SysUser user = userService.getCurrentUser();
		if(!subject.isAuthenticated()){
			return "redirect:/sys/indexWebController/login";
		}
		model.addAttribute("username",user.getNickname());
		return "/WEB-INF/views/sys/index.jsp";
	}
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "/WEB-INF/views/sys/login.jsp";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(Model model,
			@RequestParam(value="username",required=true) String username,
			@RequestParam(value="password",required=true) String password){
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try{
			subject.login(token);
		}catch(Exception e){
			model.addAttribute("message", e.getMessage());
			return "/WEB-INF/views/sys/login.jsp";
		}
		SysUser user = (SysUser) subject.getPrincipal();
		YwymLogHandle logHandle = new YwymLogHandle();
		logHandle.setUserId(user.getId());
		logHandle.setUsername(user.getUsername());
		logHandle.setContent("登录系统");
		logHandle.setRemark("登录成功！");
		logHandleService.save(logHandle);
		return "redirect:/sys/indexWebController/index";
	}
	
	
	@RequestMapping(value="/logout")
	public String logout(Model model){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/sys/indexWebController/login";
	}
}
