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
import com.java.entity.YwymLogHandle;
import com.java.service.YwymLogHandleService;

@ApiIgnore
@Controller
@RequestMapping("/sys/ywymLogHandleWebController")
public class YwymLogHandleWebController extends BaseController{
	@Resource
	private YwymLogHandleService ywymLogHandleService;
	
	@ModelAttribute
	public YwymLogHandle get(@RequestParam(required=false) String id) {
		YwymLogHandle entity = null;
		if (Tool.isNotBlank(id)){
			entity = ywymLogHandleService.get(id);
		}
		if (entity == null){
			entity = new YwymLogHandle();
		}
		return entity;
	}
	
	//@RequiresPermissions("ywym:log:handle:handle:view")
	@RequestMapping("/list")
	public String list(YwymLogHandle ywymLogHandle,Model model,HttpServletRequest request,HttpServletResponse response){
		SysPage<YwymLogHandle> page = ywymLogHandleService.findPage(ywymLogHandle,request);
		model.addAttribute("page", page);
		return "/WEB-INF/views/project/ywymLogHandleList.jsp";
	}
	
	@RequiresPermissions("ywym:log:handle:handle:edit")
	@RequestMapping("/form")
	public String form(YwymLogHandle ywymLogHandle, Model model) {
		model.addAttribute("entity", ywymLogHandle);
		return "/WEB-INF/views/project/ywymLogHandleForm.jsp";
	}
	
	@RequiresPermissions("ywym:log:handle:handle:edit")
	@RequestMapping("/save")
	public String save(YwymLogHandle ywymLogHandle, Model model, RedirectAttributes redirectAttributes) {
		ywymLogHandleService.save(ywymLogHandle);
		addMessage("保存成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymLogHandleWebController/list";
	}
	
	@RequiresPermissions("ywym:log:handle:handle:delete")
	@RequestMapping("/delete")
	public String delete(YwymLogHandle ywymLogHandle, RedirectAttributes redirectAttributes) {
		ywymLogHandleService.delete(ywymLogHandle);
		addMessage("删除成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymLogHandleWebController/list";
	}
}
