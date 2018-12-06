package com.java.controller.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.common.constance.MyConstance;
import com.java.entity.YwymGoods;
import com.java.service.YwymGoodsService;
import org.springframework.web.multipart.MultipartFile;
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
import com.java.entity.YwymCatlog;
import com.java.service.YwymCatlogService;

import java.util.List;

@ApiIgnore
@Controller
@RequestMapping("/sys/ywymCatlogWebController")
public class YwymCatlogWebController extends BaseController{
	@Resource
	private YwymCatlogService ywymCatlogService;
	@Resource
	private YwymGoodsService goodsService;
	
	@ModelAttribute
	public YwymCatlog get(@RequestParam(required=false) String id) {
		YwymCatlog entity = null;
		if (Tool.isNotBlank(id)){
			entity = ywymCatlogService.get(id);
		}
		if (entity == null){
			entity = new YwymCatlog();
		}
		return entity;
	}
	
	@RequiresPermissions("ywym:catlog:catlog:view")
	@RequestMapping("/list")
	public String list(YwymCatlog ywymCatlog,Model model,HttpServletRequest request,HttpServletResponse response){
		ywymCatlog.setOrderBy("a.rank ASC");
		SysPage<YwymCatlog> page = ywymCatlogService.findPage(ywymCatlog,request);
		List<YwymCatlog> list = page.getList();
		if (list != null && list.size() > 0){
			for(YwymCatlog catlog :list){
				catlog.setIcon(Tool.toUrl(catlog.getIcon()));
			}
		}
		model.addAttribute("page", page);
		return "/WEB-INF/views/project/ywymCatlogList.jsp";
	}
	
	@RequiresPermissions("ywym:catlog:catlog:edit")
	@RequestMapping("/form")
	public String form(YwymCatlog ywymCatlog, Model model) {
		ywymCatlog.setIcon(Tool.toUrl(ywymCatlog.getIcon()));
		model.addAttribute("entity", ywymCatlog);
		return "/WEB-INF/views/project/ywymCatlogForm.jsp";
	}
	
	@RequiresPermissions("ywym:catlog:catlog:edit")
	@RequestMapping("/save")
	public String save(YwymCatlog ywymCatlog, Model model, RedirectAttributes redirectAttributes,
					   @RequestParam(value = "img", required = false) MultipartFile img) {
		if (img != null && img.getSize() > 0) {
			String url = Tool.uploadFile(img, MyConstance.UPLOAD_IMG_DIR).getPath();
			if (Tool.isNotBlank(url)) {
				ywymCatlog.setIcon(url);
			}
		}
		if (Tool.isNotBlank(ywymCatlog.getId())){
			YwymGoods _goods = new YwymGoods();
			_goods.setCatlogId(ywymCatlog.getId());
			List<YwymGoods> goodsList = goodsService.findList(_goods);
			if (goodsList != null && goodsList.size() > 0){
				for(YwymGoods goods :goodsList){
					goods.setRank(goods.getRank().replaceAll("[a-zA-Z]",ywymCatlog.getRank()));
					goodsService.save(goods);
				}
			}
		}
		ywymCatlogService.save(ywymCatlog);
		addMessage("保存成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymCatlogWebController/list";
	}
	
	@RequiresPermissions("ywym:catlog:catlog:delete")
	@RequestMapping("/delete")
	public String delete(YwymCatlog ywymCatlog, RedirectAttributes redirectAttributes) {
		YwymGoods _g = new YwymGoods();
		_g.setCatlogId(ywymCatlog.getId());
		int count = goodsService.getCount(_g);
		if (count > 0){
			addMessage("该分类已被礼品绑定，请先解除绑定后再操作！", ERROR, redirectAttributes);
			return "redirect:/sys/ywymCatlogWebController/list";
		}
		ywymCatlogService.delete(ywymCatlog);
		addMessage("删除成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/ywymCatlogWebController/list";
	}
}
