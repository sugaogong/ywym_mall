package com.java.controller.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import springfox.documentation.annotations.ApiIgnore;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.sys.common.basic.controller.BaseController;
import com.java.sys.common.page.SysPage;
import com.java.sys.common.utils.Tool;
import com.java.entity.YwymLogLogin;
import com.java.service.YwymLogLoginService;

@ApiIgnore
@Controller
@RequestMapping("/sys/ywymLogLoginWebController")
public class YwymLogLoginWebController extends BaseController{
	@Resource
	private YwymLogLoginService ywymLogLoginService;
	
	@ModelAttribute
	public YwymLogLogin get(@RequestParam(required=false) String id) {
		YwymLogLogin entity = null;
		if (Tool.isNotBlank(id)){
			entity = ywymLogLoginService.get(id);
		}
		if (entity == null){
			entity = new YwymLogLogin();
		}
		return entity;
	}
	
	@RequiresPermissions("ywym:log:login:login:view")
	@RequestMapping("/list")
	public String list(YwymLogLogin ywymLogLogin,Model model,HttpServletRequest request,HttpServletResponse response){
		SysPage<YwymLogLogin> page = ywymLogLoginService.findPage(ywymLogLogin,request);
		model.addAttribute("page", page);
		return "/WEB-INF/views/project/ywymLogLoginList.jsp";
	}
	
	@RequiresPermissions("ywym:log:login:login:edit")
	@RequestMapping("/form")
	public String form(YwymLogLogin ywymLogLogin, Model model) {
		model.addAttribute("entity", ywymLogLogin);
		return "/WEB-INF/views/project/ywymLogLoginForm.jsp";
	}
	
	@RequiresPermissions("ywym:log:login:login:edit")
	@RequestMapping("/save")
	public String save(YwymLogLogin ywymLogLogin, Model model, RedirectAttributes redirectAttributes) {
		ywymLogLoginService.save(ywymLogLogin);
		addMessage("保存成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymLogLoginWebController/list";
	}
	
	@RequiresPermissions("ywym:log:login:login:delete")
	@RequestMapping("/delete")
	public String delete(YwymLogLogin ywymLogLogin, RedirectAttributes redirectAttributes) {
		ywymLogLoginService.delete(ywymLogLogin);
		addMessage("删除成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymLogLoginWebController/list";
	}
}
