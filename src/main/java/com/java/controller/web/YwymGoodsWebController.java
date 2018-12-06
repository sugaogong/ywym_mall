package com.java.controller.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.common.constance.MyConstance;
import com.java.config.YwymConfig;
import com.java.entity.YwymCatlog;
import com.java.entity.YwymImg;
import com.java.entity.YwymLogHandle;
import com.java.service.YwymCatlogService;
import com.java.service.YwymLogHandleService;
import com.java.sys.common.basic.result.BaseResult;
import com.java.sys.entity.SysUser;
import com.java.sys.service.SysUserService;
import org.springframework.http.ResponseEntity;
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
import com.java.entity.YwymGoods;
import com.java.service.YwymGoodsService;

import java.util.List;

@ApiIgnore
@Controller
@RequestMapping("/sys/ywymGoodsWebController")
public class YwymGoodsWebController extends BaseController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private YwymGoodsService ywymGoodsService;
    @Resource
    private YwymCatlogService ywymCatlogService;
    @Resource
    private YwymLogHandleService logHandleService;


    @ModelAttribute
    public YwymGoods get(@RequestParam(required = false) String id) {
        YwymGoods entity = null;
        if (Tool.isNotBlank(id)) {
            entity = ywymGoodsService.get(id);
        }
        if (entity == null) {
            entity = new YwymGoods();
        }
        return entity;
    }

    @RequiresPermissions("ywym:goods:goods:view")
    @RequestMapping("/list")
    public String list(YwymGoods ywymGoods, Model model, HttpServletRequest request, HttpServletResponse response) {
        ywymGoods.setOrderBy("a.rank ASC");
        SysPage<YwymGoods> page = ywymGoodsService.findPage(ywymGoods, request);
        List<YwymGoods> list = page.getList();
        for (YwymGoods goods : list) {
            String status = goods.getIsShelve();
            //如果礼品是上架状态，但是库存达预警库存，则显示售罄
            if (ONE.equals(status) && goods.getStock() - goods.getWarnStock() <= 0) {
                status = "2";
            }
            if (ZERO.equals(status)) {
                goods.setIsIndex(ZERO);
            }
            goods.setStatus(status);
            goods.setIcon(Tool.toUrl(goods.getIcon()));
            YwymCatlog catlog = ywymCatlogService.get(goods.getCatlogId());
            if (catlog != null) {
                goods.setCatlogName(catlog.getName());
            }
        }
        model.addAttribute("catlogList", ywymCatlogService.findList(null));
        model.addAttribute("page", page);
        return "/WEB-INF/views/project/ywymGoodsList.jsp";
    }

    @RequiresPermissions("ywym:goods:goods:edit")
    @RequestMapping("/form")
    public String form(YwymGoods ywymGoods, Model model) {
        ywymGoods.setIcon(Tool.toUrl(ywymGoods.getIcon()));
        if (Tool.isNotBlank(ywymGoods.getId())) {
            YwymCatlog catlog = ywymCatlogService.get(ywymGoods.getCatlogId());
            ywymGoods.setRank(ywymGoods.getRank().replace(catlog.getRank(), ""));
            YwymImg _i = new YwymImg(ywymGoods.getId(), YwymConfig.IMG_GOODS_IMG);
            List<YwymImg> imgList = imgService.findList(_i);
            for (YwymImg img : imgList) {
                img.setImg(Tool.toUrl(img.getImg()));
            }
            model.addAttribute("imgList", imgList);
        }
        model.addAttribute("catlogList", ywymCatlogService.findList(null));
        model.addAttribute("entity", ywymGoods);
        return "/WEB-INF/views/project/ywymGoodsForm.jsp";
    }

    @RequiresPermissions("ywym:goods:goods:edit")
    @RequestMapping("/save")
    public String save(YwymGoods ywymGoods, Model model, RedirectAttributes redirectAttributes,
                       @RequestParam(value = "img_icon", required = false) MultipartFile icon,
                       @RequestParam(value = "img_img", required = false) MultipartFile[] img) {
        //新建礼品
        if (Tool.isBlank(ywymGoods.getId())) {
            ywymGoods.setIsShelve(YwymConfig.GOODS_LOWER);//下架状态
            ywymGoods.setIsIndex(ZERO);//首页不展示
            ywymGoods.setSaleNum(0);
        }
        if (icon != null && icon.getSize() > 0) {
            String url = Tool.uploadFile(icon, MyConstance.UPLOAD_IMG_DIR).getPath();
            if (Tool.isNotBlank(url)) {
                ywymGoods.setIcon(url);
            }
        }
        if (Tool.isBlank(ywymGoods.getSpec())) {
            ywymGoods.setSpec("默认");
        }
        YwymCatlog catlog = ywymCatlogService.get(ywymGoods.getCatlogId());
        ywymGoods.setRank(catlog.getRank() + ywymGoods.getRank());
        ywymGoodsService.save(ywymGoods);
        if (img != null && img.length > 0) {
            uploadImgs(img, ywymGoods.getId(), YwymConfig.IMG_GOODS_IMG);
        }
        addMessage("保存成功", SUCCESS, redirectAttributes);
        return "redirect:/sys/ywymGoodsWebController/list";
    }

    @RequiresPermissions("ywym:goods:goods:delete")
    @RequestMapping("/delete")
    public String delete(YwymGoods ywymGoods, RedirectAttributes redirectAttributes) {
        ywymGoodsService.delete(ywymGoods);
        addMessage("删除成功", SUCCESS, redirectAttributes);
        return "redirect:/sys/ywymGoodsWebController/list";
    }

    /**
     * 处理多张图片
     *
     * @param id
     * @return
     */
    @RequiresPermissions("ywym:goods:goods:delete")
    @RequestMapping("/delImg")
    public ResponseEntity<BaseResult> delImg(String id) {
        if (Tool.isNotBlank(id)) {
            YwymImg img = imgService.get(id);
            imgService.delete(img);
        }
        return buildSuccessInfo(null);
    }

    /**
     * 设置上下架
     *
     * @param ywymGoods
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("ywym:goods:goods:edit")
    @RequestMapping("/upperOrLower")
    public String upperOrLower(YwymGoods ywymGoods, RedirectAttributes redirectAttributes) {
        SysUser currentUser = sysUserService.getCurrentUser();
        ywymGoodsService.save(ywymGoods);
        YwymLogHandle logHandle = new YwymLogHandle();
        logHandle.setUserId(currentUser.getId());
        logHandle.setUsername(currentUser.getUsername());
        String stauts;
        if ("1".equals(ywymGoods.getIsShelve())) {
            stauts = "上架";
        } else {
            stauts = "下架";
        }
        logHandle.setContent(stauts + "了" + ywymGoods.getName());
        String type;
        if ("1".equals(ywymGoods.getType())) {
            type = "实物";
        } else {
            type = "虚拟";
        }
        logHandle.setType(type);
        logHandle.setScore("积分" + ywymGoods.getNowScore());
        logHandle.setRemark(stauts + "成功！");
        logHandleService.save(logHandle);
        addMessage("操作成功", SUCCESS, redirectAttributes);
        return "redirect:/sys/ywymGoodsWebController/list";
    }

    /**
     * 设置是否首页展示
     *
     * @param ywymGoods
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/authIndex")
    public ResponseEntity<BaseResult> authIndex(YwymGoods ywymGoods, RedirectAttributes redirectAttributes) {
        ywymGoodsService.save(ywymGoods);
        return buildSuccessInfo(SUCCESS);
    }
}
