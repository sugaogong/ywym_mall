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
import com.java.entity.YwymAddress;
import com.java.service.YwymAddressService;

@ApiIgnore
@Controller
@RequestMapping("/sys/ywymAddressWebController")
public class YwymAddressWebController extends BaseController{
	@Resource
	private YwymAddressService ywymAddressService;
	
	@ModelAttribute
	public YwymAddress get(@RequestParam(required=false) String id) {
		YwymAddress entity = null;
		if (Tool.isNotBlank(id)){
			entity = ywymAddressService.get(id);
		}
		if (entity == null){
			entity = new YwymAddress();
		}
		return entity;
	}
	
	@RequiresPermissions("ywym:address:address:view")
	@RequestMapping("/list")
	public String list(YwymAddress ywymAddress,Model model,HttpServletRequest request,HttpServletResponse response){
		SysPage<YwymAddress> page = ywymAddressService.findPage(ywymAddress,request);
		model.addAttribute("page", page);
		return "/WEB-INF/views/project/ywymAddressList.jsp";
	}
	
	@RequiresPermissions("ywym:address:address:edit")
	@RequestMapping("/form")
	public String form(YwymAddress ywymAddress, Model model) {
		model.addAttribute("entity", ywymAddress);
		return "/WEB-INF/views/project/ywymAddressForm.jsp";
	}
	
	@RequiresPermissions("ywym:address:address:edit")
	@RequestMapping("/save")
	public String save(YwymAddress ywymAddress, Model model, RedirectAttributes redirectAttributes) {
		ywymAddressService.save(ywymAddress);
		addMessage("保存成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymAddressWebController/list";
	}
	
	@RequiresPermissions("ywym:address:address:delete")
	@RequestMapping("/delete")
	public String delete(YwymAddress ywymAddress, RedirectAttributes redirectAttributes) {
		ywymAddressService.delete(ywymAddress);
		addMessage("删除成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymAddressWebController/list";
	}
}
