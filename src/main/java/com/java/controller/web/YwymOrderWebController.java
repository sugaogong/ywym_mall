package com.java.controller.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.entity.YwymUser;
import com.java.service.YwymLogUserService;
import com.java.service.YwymUserService;
import com.java.sys.common.basic.result.BaseResult;
import org.springframework.http.ResponseEntity;
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
import com.java.entity.YwymOrder;
import com.java.service.YwymOrderService;

import java.util.Date;
import java.util.List;

@ApiIgnore
@Controller
@RequestMapping("/sys/ywymOrderWebController")
public class YwymOrderWebController extends BaseController {
    @Resource
    private YwymOrderService ywymOrderService;
    @Resource
    private YwymUserService userService;

    @ModelAttribute
    public YwymOrder get(@RequestParam(required = false) String id) {
        YwymOrder entity = null;
        if (Tool.isNotBlank(id)) {
            entity = ywymOrderService.get(id);
        }
        if (entity == null) {
            entity = new YwymOrder();
        }
        return entity;
    }

    @RequiresPermissions("ywym:order:order:view")
    @RequestMapping("/list")
    public String list(YwymOrder ywymOrder, Model model, HttpServletRequest request, HttpServletResponse response) {
        SysPage<YwymOrder> page = ywymOrderService.findPage(ywymOrder, request);
        List<YwymOrder> list = page.getList();
        if (list != null && list.size() > 0) {
            for (YwymOrder order : list) {
                order.setGoodsIcon(Tool.toUrl(order.getGoodsIcon()));
                YwymUser user = userService.get(order.getUserId());
                if (user != null){
                    order.setUsername(user.getUsername());
                }
            }
        }
        model.addAttribute("page", page);
        return "/WEB-INF/views/project/ywymOrderList.jsp";
    }

    @RequiresPermissions("ywym:order:order:edit")
    @RequestMapping("/form")
    public String form(YwymOrder ywymOrder, Model model) {
        model.addAttribute("entity", ywymOrder);
        return "/WEB-INF/views/project/ywymOrderForm.jsp";
    }

    @RequiresPermissions("ywym:order:order:edit")
    @RequestMapping("/save")
    public String save(YwymOrder ywymOrder, Model model, RedirectAttributes redirectAttributes) {
        ywymOrderService.save(ywymOrder);
        addMessage("保存成功", SUCCESS, redirectAttributes);
        return "redirect:/sys/ywymOrderWebController/list";
    }

    @RequiresPermissions("ywym:order:order:delete")
    @RequestMapping("/delete")
    public String delete(YwymOrder ywymOrder, RedirectAttributes redirectAttributes) {
        ywymOrderService.delete(ywymOrder);
        addMessage("删除成功", SUCCESS, redirectAttributes);
        return "redirect:/sys/ywymOrderWebController/list";
    }

    /**
     * 设置发货状态
     *
     * @return
     */
    @RequiresPermissions("ywym:order:order:edit")
    @RequestMapping("/send")
    public ResponseEntity<BaseResult> sendStatus(String id, String expCode, String expNo) {
        YwymOrder order = ywymOrderService.get(id);
        if ("2".equals(order.getStatus())) {
            return null;
        }
        order.setStatus("2");
        order.setSendTime(new Date());
        order.setExpCode(expCode);
        order.setExpNo(expNo);
        ywymOrderService.save(order);
        return buildSuccessInfo(null);
    }

    /**
     * 确认收货
     * @param
     * @return
     */
    @RequiresPermissions("ywym:order:order:edit")
    @RequestMapping("/confirm")
    public String confirm(YwymOrder order) {
        order.setStatus("3");
        ywymOrderService.save(order);
        return "redirect:/sys/ywymOrderWebController/list";
    }

    /**
     * 查看订单详情
     * @param
     * @return
     */
    @RequiresPermissions("ywym:order:order:edit")
    @RequestMapping("/getOrderInfo")
    public String getOrderInfo(YwymOrder ywymOrder,Model model){
        YwymUser user = userService.get(ywymOrder.getUserId());
        if (user != null){
            ywymOrder.setUsername(user.getUsername());
        }
        ywymOrder.setGoodsIcon(Tool.toUrl(ywymOrder.getGoodsIcon()));
        model.addAttribute("entity", ywymOrder);
        return "/WEB-INF/views/project/ywymOrderInfo.jsp";
    }
}
