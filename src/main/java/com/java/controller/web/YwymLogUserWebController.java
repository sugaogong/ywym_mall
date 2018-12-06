package com.java.controller.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.entity.YwymUser;
import com.java.service.YwymUserService;
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
import com.java.entity.YwymLogUser;
import com.java.service.YwymLogUserService;

import java.util.List;

@ApiIgnore
@Controller
@RequestMapping("/sys/ywymLogUserWebController")
public class YwymLogUserWebController extends BaseController{
	@Resource
	private YwymLogUserService ywymLogUserService;
	@Resource
	private YwymUserService userService;
	
	@ModelAttribute
	public YwymLogUser get(@RequestParam(required=false) String id) {
		YwymLogUser entity = null;
		if (Tool.isNotBlank(id)){
			entity = ywymLogUserService.get(id);
		}
		if (entity == null){
			entity = new YwymLogUser();
		}
		return entity;
	}
	
	@RequiresPermissions("ywym:log:user:user:view")
	@RequestMapping("/list")
	public String list(YwymLogUser ywymLogUser,Model model,HttpServletRequest request,HttpServletResponse response){
		SysPage<YwymLogUser> page = ywymLogUserService.findPage(ywymLogUser,request);
		List<YwymLogUser> userList=page.getList();
		for(YwymLogUser logUser:userList){
			YwymUser user=userService.get(logUser.getUserId());
			if(user!=null){
				logUser.setUsername(user.getUsername());
			}
		}
		model.addAttribute("page", page);
		return "/WEB-INF/views/project/ywymLogUserList.jsp";
	}
	
	@RequiresPermissions("ywym:log:user:user:edit")
	@RequestMapping("/form")
	public String form(YwymLogUser ywymLogUser, Model model) {
		model.addAttribute("entity", ywymLogUser);
		return "/WEB-INF/views/project/ywymLogUserForm.jsp";
	}
	
	@RequiresPermissions("ywym:log:user:user:edit")
	@RequestMapping("/save")
	public String save(YwymLogUser ywymLogUser, Model model, RedirectAttributes redirectAttributes) {
		ywymLogUserService.save(ywymLogUser);
		addMessage("保存成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymLogUserWebController/list";
	}
	
	@RequiresPermissions("ywym:log:user:user:delete")
	@RequestMapping("/delete")
	public String delete(YwymLogUser ywymLogUser, RedirectAttributes redirectAttributes) {
		ywymLogUserService.delete(ywymLogUser);
		addMessage("删除成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymLogUserWebController/list";
	}

	/**
	 * 查看用户积分明细
	 * @param id
	 * @param
	 * @return
	 */
	//@RequiresPermissions("ywym:log:user:user:view")
	@RequestMapping("/gotUserScoreList")
	public String gotUserScoreList(YwymLogUser ywymLogUser,Model model,HttpServletRequest request,HttpServletResponse response,String id){
		SysPage<YwymLogUser> page = ywymLogUserService.findPage(ywymLogUser,request);
		System.out.println("list"+page.getList());
		model.addAttribute("page", page);
		return "/WEB-INF/views/project/YwymUserScoreList.jsp";
	}
}
