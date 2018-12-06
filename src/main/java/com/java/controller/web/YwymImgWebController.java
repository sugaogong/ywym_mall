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
import com.java.entity.YwymImg;
import com.java.service.YwymImgService;

@ApiIgnore
@Controller
@RequestMapping("/sys/ywymImgWebController")
public class YwymImgWebController extends BaseController{
	@Resource
	private YwymImgService ywymImgService;
	
	@ModelAttribute
	public YwymImg get(@RequestParam(required=false) String id) {
		YwymImg entity = null;
		if (Tool.isNotBlank(id)){
			entity = ywymImgService.get(id);
		}
		if (entity == null){
			entity = new YwymImg();
		}
		return entity;
	}
	
	@RequiresPermissions("ywym:img:img:view")
	@RequestMapping("/list")
	public String list(YwymImg ywymImg,Model model,HttpServletRequest request,HttpServletResponse response){
		SysPage<YwymImg> page = ywymImgService.findPage(ywymImg,request);
		model.addAttribute("page", page);
		return "/WEB-INF/views/project/ywymImgList.jsp";
	}
	
	@RequiresPermissions("ywym:img:img:edit")
	@RequestMapping("/form")
	public String form(YwymImg ywymImg, Model model) {
		model.addAttribute("entity", ywymImg);
		return "/WEB-INF/views/project/ywymImgForm.jsp";
	}
	
	@RequiresPermissions("ywym:img:img:edit")
	@RequestMapping("/save")
	public String save(YwymImg ywymImg, Model model, RedirectAttributes redirectAttributes) {
		ywymImgService.save(ywymImg);
		addMessage("保存成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymImgWebController/list";
	}
	
	@RequiresPermissions("ywym:img:img:delete")
	@RequestMapping("/delete")
	public String delete(YwymImg ywymImg, RedirectAttributes redirectAttributes) {
		ywymImgService.delete(ywymImg);
		addMessage("删除成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymImgWebController/list";
	}
}
