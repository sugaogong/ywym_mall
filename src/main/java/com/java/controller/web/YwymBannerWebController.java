package com.java.controller.web;

import javax.annotation.Resource;

import com.java.common.constance.ApiConstance;
import com.java.common.constance.MyConstance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.entity.YwymGoods;
import com.java.service.YwymGoodsService;
import com.sun.net.httpserver.Authenticator;
import jdk.nashorn.internal.ir.annotations.Ignore;
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
import com.java.entity.YwymBanner;
import com.java.service.YwymBannerService;

import java.util.List;

@ApiIgnore
@Controller
@RequestMapping("/sys/ywymBannerWebController")
public class YwymBannerWebController extends BaseController {
    @Resource
    private YwymBannerService bannerService;
    @Resource
    private YwymGoodsService goodsService;

    @ModelAttribute
    public YwymBanner get(@RequestParam(required = false) String id) {
        YwymBanner entity = null;
        if (Tool.isNotBlank(id)) {
            entity = bannerService.get(id);
        }
        if (entity == null) {
            entity = new YwymBanner();
        }
        return entity;
    }

    @RequiresPermissions("ywym:banner:banner:view")
    @RequestMapping("/list")
    public String list(YwymBanner ywymBanner, Model model, HttpServletRequest request, HttpServletResponse response) {
        ywymBanner.setOrderBy("a.rank*1 ASC");
        SysPage<YwymBanner> page = bannerService.findPage(ywymBanner, request);
        List<YwymBanner> list = page.getList();
        for (YwymBanner banner : list) {
            banner.setImg(Tool.toUrl(banner.getImg()));
            if ("2".equals(banner.getType())){
                YwymGoods goods = goodsService.get(banner.getObjectId());
                if (goods != null) {
                    banner.setObjectId(goods.getName());
                }

            }
        }
        model.addAttribute("page", page);
        return "/WEB-INF/views/project/ywymBannerList.jsp";
    }

    @RequiresPermissions("ywym:banner:banner:edit")
    @RequestMapping("/form")
    public String form(YwymBanner ywymBanner, Model model, RedirectAttributes redirectAttributes) {
        YwymGoods _goods = new YwymGoods();
        _goods.setIsShelve(ONE);
        List<YwymGoods> goodsList = goodsService.findList(_goods);
        if (goodsList !=null && goodsList.size() > 0){
            for (YwymGoods goods :goodsList){
                goods.setIcon(Tool.toUrl(goods.getIcon()));
            }
        }
        model.addAttribute("goodsList", goodsList);
        ywymBanner.setImg(Tool.toUrl(ywymBanner.getImg()));
        model.addAttribute("entity", ywymBanner);
        //addMessage("保存成功", SUCCESS, redirectAttributes);
        return "/WEB-INF/views/project/ywymBannerForm.jsp";
    }

    @RequiresPermissions("ywym:banner:banner:edit")
    @RequestMapping("/save")
    public String save(YwymBanner ywymBanner, Model model, RedirectAttributes redirectAttributes,
                       @RequestParam(value = "banner_img", required = false) MultipartFile img) {
        if (img != null  && img.getSize() > 0) {
            String imgType = img.getContentType();
            if (!imgType.startsWith("image")) {
                model.addAttribute("entity", ywymBanner);
                addMessage("图片格式错误", ERROR, redirectAttributes);
                return "/WEB-INF/views/project/ywymBannerForm.jsp";
            }
            String url = Tool.uploadFile(img, MyConstance.UPLOAD_IMG_DIR).getPath();
            if (Tool.isNotBlank(url)) {
                ywymBanner.setImg(url);
            }
        }
        if ("1".equals(ywymBanner.getType())){
            ywymBanner.setObjectId("");
        }else {
            ywymBanner.setLink("");
        }
        bannerService.save(ywymBanner);
        addMessage("保存成功", SUCCESS, redirectAttributes);
        return "redirect:/sys/ywymBannerWebController/list";
    }

    @RequiresPermissions("ywym:banner:banner:delete")
    @RequestMapping("/delete")
    public String delete(YwymBanner ywymBanner, RedirectAttributes redirectAttributes) {
        bannerService.delete(ywymBanner);
        addMessage("删除成功", SUCCESS, redirectAttributes);
        return "redirect:/sys/ywymBannerWebController/list";
    }
}
