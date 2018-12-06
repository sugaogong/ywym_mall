package com.java.controller.web;

import com.java.entity.*;
import com.java.service.*;
import com.java.sys.common.basic.controller.BaseController;
import com.java.sys.common.page.SysPage;
import com.java.sys.common.utils.Tool;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;
import sun.rmi.log.LogHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiIgnore
@Controller
@RequestMapping("/sys/ywymIndexWebController")
public class YwymIndexWebController extends BaseController {

    @Resource
    private YwymUserService userService;
    @Resource
    private YwymLogLoginService logLoginService;
    @Resource
    private YwymGoodsService goodsService;
    @Resource
    private YwymOrderService orderService;
    @Resource
    private YwymLogHandleService logHandleService;

    @RequiresPermissions("ywym:index:index:view")
    @RequestMapping("/list")
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        //成交总积分
        List<Map<String, Object>> listSQL1 = userService.findListSQL(" SELECT FORMAT(COALESCE(SUM(total_use_score),0),2) AS total FROM ywym_user");
        map.put("totScore", listSQL1.get(0).get("total"));
        //今日访客
        List<Map<String, Object>> listSQL2 = logLoginService.findListSQL(" SELECT COUNT(1) AS count FROM ywym_log_login WHERE create_date BETWEEN '" + Tool.dateToString(Tool.getDateBegin(new Date()), "yyyy-MM-dd HH:mm:ss") + "' AND '" + Tool.dateToString(Tool.getDateEnd(new Date()), "yyyy-MM-dd HH:mm:ss") + "'");
        map.put("visUser", listSQL2.get(0).get("count"));
        //待发货订单
        List<Map<String, Object>> listSQL3 = orderService.findListSQL(" SELECT COUNT(1) AS count FROM ywym_order WHERE status = '1'");
        map.put("penOrder", listSQL3.get(0).get("count"));
        //待收货订单
        List<Map<String, Object>> listSQL4 = orderService.findListSQL(" SELECT COUNT(1) AS count FROM ywym_order WHERE status = '2'");
        map.put("recOrder", listSQL4.get(0).get("count"));
        //已完成订单
        List<Map<String, Object>> listSQL5 = orderService.findListSQL(" SELECT COUNT(1) AS count FROM ywym_order WHERE status = '3'");
        map.put("comOrder", listSQL5.get(0).get("count"));
        //出售礼品数量
        List<Map<String, Object>> listSQL6 = goodsService.findListSQL(" SELECT COUNT(1) AS count FROM ywym_goods WHERE is_shelve ='1'");
        map.put("uppGoods", listSQL6.get(0).get("count"));
        //下架礼品数量
        List<Map<String, Object>> listSQL7 = goodsService.findListSQL(" SELECT COUNT(1) AS count FROM ywym_goods WHERE is_shelve ='0'");
        map.put("lowGoods", listSQL7.get(0).get("count"));
        //操作日志
        YwymLogHandle _handle = new YwymLogHandle();
        _handle.setFirst(0);
        _handle.setMax(11);
        List<YwymLogHandle> logHandleList = logHandleService.findList(_handle);
        map.put("logHandleList", logHandleList);
        //热门礼品排序
        YwymGoods _goods = new YwymGoods();
        _goods.setOrderBy("a.sale_num DESC");
        _goods.setFirst(0);
        _goods.setMax(8);
        List<YwymGoods> goodsList = goodsService.findList(_goods);
        map.put("goodsList", goodsList);
        model.addAttribute("map", map);
        return "/WEB-INF/views/project/ywymIndex.jsp";
    }

}
