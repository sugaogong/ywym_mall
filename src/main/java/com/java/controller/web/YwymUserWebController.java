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
import com.java.entity.YwymUser;
import com.java.service.YwymUserService;

@ApiIgnore
@Controller
@RequestMapping("/sys/ywymUserWebController")
public class YwymUserWebController extends BaseController{
	@Resource
	private YwymUserService ywymUserService;
	
	@ModelAttribute
	public YwymUser get(@RequestParam(required=false) String id) {
		YwymUser entity = null;
		if (Tool.isNotBlank(id)){
			entity = ywymUserService.get(id);
		}
		if (entity == null){
			entity = new YwymUser();
		}
		return entity;
	}
	
	@RequiresPermissions("ywym:user:user:view")
	@RequestMapping("/list")
	public String list(YwymUser ywymUser,Model model,HttpServletRequest request,HttpServletResponse response){
		SysPage<YwymUser> page = ywymUserService.findPage(ywymUser,request);
		for(YwymUser user:page.getList()){
			user.setHeadImg(Tool.toUrl(user.getHeadImg()));
		}
		model.addAttribute("page", page);
		return "/WEB-INF/views/project/ywymUserList.jsp";
	}
	
	@RequiresPermissions("ywym:user:user:edit")
	@RequestMapping("/form")
	public String form(YwymUser ywymUser, Model model) {
		model.addAttribute("entity", ywymUser);
		return "/WEB-INF/views/project/ywymUserForm.jsp";
	}
	
	@RequiresPermissions("ywym:user:user:edit")
	@RequestMapping("/save")
	public String save(YwymUser ywymUser, Model model, RedirectAttributes redirectAttributes) {
		ywymUserService.save(ywymUser);
		addMessage("保存成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymUserWebController/list";
	}
	
	@RequiresPermissions("ywym:user:user:delete")
	@RequestMapping("/delete")
	public String delete(YwymUser ywymUser, RedirectAttributes redirectAttributes) {
		ywymUserService.delete(ywymUser);
		addMessage("删除成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymUserWebController/list";
	}

	/**
	 *设置用户状态-是否锁定
	 * @param ywymUser
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ywym:user:user:edit")
	@RequestMapping("/lockOrNot")
	public String lockOrNot(YwymUser ywymUser, RedirectAttributes redirectAttributes) {
		ywymUserService.save(ywymUser);
		addMessage("操作成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymUserWebController/list";
	}
}
