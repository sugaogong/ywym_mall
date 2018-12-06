package com.java.controller.api;

import com.java.common.constance.ApiConstance;
import com.java.common.interceptor.annotation.LoginValidate;
import com.java.config.YwymConfig;
import com.java.entity.*;
import com.java.entity.response.ReturnBanner;
import com.java.entity.response.ReturnCatlog;
import com.java.entity.response.ReturnGoods;
import com.java.entity.response.ReturnOrder;
import com.java.service.*;
import com.java.sys.common.basic.controller.BaseController;
import com.java.sys.common.basic.result.BaseResult;
import com.java.sys.common.utils.Tool;
import io.swagger.annotations.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "api-mall-controller", description = "积分商城")
@SpringBootApplication
@RequestMapping("/api/mallApiController")
public class MallApiController extends BaseController {

    @Resource
    private CommonService commonService;
    @Resource
    private YwymUserService userService;
    @Resource
    private YwymBannerService bannerService;
    @Resource
    private YwymCatlogService catlogService;
    @Resource
    private YwymGoodsService goodsService;
    @Resource
    private YwymAddressService addressService;
    @Resource
    private YwymOrderService orderService;
    @Resource
    private YwymLogUserService logUserService;

    @LoginValidate
    @ApiOperation(value = "商城轮播图")
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnBanner.class)})
    @RequestMapping(value = "/getBannerList", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> getBannerList(HttpServletRequest request,
                                                    @ApiParam(value = "用户id", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                    @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                    @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        YwymBanner _b = new YwymBanner();
        _b.setStatus(ONE);
        _b.setOrderBy("a.rank ASC");
        List<YwymBanner> list = bannerService.findList(_b);
        return buildSuccessInfo(bannerService.getReturnBannerList(list));
    }

    @LoginValidate
    @ApiOperation(value = "商城首页")
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnCatlog.class)})
    @RequestMapping(value = "/getIndexList", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> getIndexList(HttpServletRequest request,
                                                   @ApiParam(value = "用户id", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                   @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                   @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                   @ApiParam(value = "page", required = false) @RequestParam(value = "page", required = false) Integer page,
                                                   @ApiParam(value = "pageSize", required = false) @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        Integer first = PAGE, max = PAGE_SIZE;
        if (page != null) {
            first = page;
        }
        if (pageSize != null) {
            max = pageSize;
        }
        YwymCatlog _c = new YwymCatlog();
        _c.setFirst(first * max);
        _c.setMax(max);
        _c.setOrderBy("a.rank ASC");
        List<YwymCatlog> list = catlogService.findList(_c);
        return buildSuccessInfo(catlogService.getReturnCatlogAndGoodsList(list));
    }

    @LoginValidate
    @ApiOperation(value = "根据分类id获取礼品列表")
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnGoods.class)})
    @RequestMapping(value = "/getGoodsListByCatlogId", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> getGoodsListByCatlogId(HttpServletRequest request,
                                                             @ApiParam(value = "用户id", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                             @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                             @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                             @ApiParam(value = "礼品分类id", required = true) @RequestParam(value = "catlogId", required = true) String catlogId,
                                                             @ApiParam(value = "page", required = false) @RequestParam(value = "page", required = false) Integer page,
                                                             @ApiParam(value = "pageSize", required = false) @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        YwymCatlog catlog = catlogService.get(catlogId);
        if (catlog == null) {
            return buildFailedInfo(ApiConstance.CATLOGID_NOT_EXIST);
        }
        Integer first = PAGE, max = PAGE_SIZE;
        if (page != null) {
            first = page;
        }
        if (pageSize != null) {
            max = pageSize;
        }
        YwymGoods _goods = new YwymGoods();
        _goods.setCatlogId(catlogId);
        _goods.setIsShelve(ONE);
        _goods.setFirst(first * max);
        _goods.setMax(max);
        _goods.setOrderBy("a.is_index*1 DESC ,a.update_date DESC");
        List<YwymGoods> list = goodsService.findList(_goods);
        return buildSuccessInfo(goodsService.getReturnGoodsList(list));
    }

    @LoginValidate
    @ApiOperation(value = "获取礼品列表（搜索）")
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnGoods.class)})
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> getGoodsList(HttpServletRequest request,
                                                   @ApiParam(value = "用户id", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                   @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                   @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                   @ApiParam(value = "搜素关键字", required = false) @RequestParam(value = "keyword", required = false) String keyword,
                                                   @ApiParam(value = "page", required = false) @RequestParam(value = "page", required = false) Integer page,
                                                   @ApiParam(value = "pageSize", required = false) @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        Integer first = PAGE, max = PAGE_SIZE;
        if (page != null) {
            first = page;
        }
        if (pageSize != null) {
            max = pageSize;
        }
        YwymGoods _goods = new YwymGoods();
        _goods.setKeyword(keyword);
        _goods.setIsShelve(ONE);
        _goods.setFirst(first * max);
        _goods.setMax(max);
        List<YwymGoods> list = goodsService.findList(_goods);
        return buildSuccessInfo(goodsService.getReturnGoodsList(list));
    }

    @LoginValidate
    @ApiOperation(value = "获取礼品详情")
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnGoods.class)})
    @RequestMapping(value = "/getGoodsInfo", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> getGoodsInfo(HttpServletRequest request,
                                                   @ApiParam(value = "用户id", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                   @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                   @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                   @ApiParam(value = "礼品id", required = true) @RequestParam(value = "goodsId", required = true) String goodsId) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        YwymGoods goods = goodsService.get(goodsId);
        if (goods == null) {
            return buildFailedInfo(ApiConstance.GOODS_NOT_EXIST);
        }
        return buildSuccessInfo(goodsService.getReturnGoods(goods));
    }

    @LoginValidate
    @ApiOperation(value = "兑换礼品")
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnOrder.class)})
    @RequestMapping(value = "/exchangeGoods", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> exchangeGoods(HttpServletRequest request,
                                                    @ApiParam(value = "用户id", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                    @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                    @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                    @ApiParam(value = "礼品id", required = true) @RequestParam(value = "goodsId", required = true) String goodsId,
                                                    @ApiParam(value = "礼品规格", required = true) @RequestParam(value = "spec", required = true) String spec,
                                                    @ApiParam(value = "备注", required = true) @RequestParam(value = "remark", required = false) String remark,
                                                    @ApiParam(value = "收货地址id", required = false) @RequestParam(value = "addressId", required = false) String addressId,
                                                    @ApiParam(value = "支付密码SHA1大写，必填", required = false) @RequestParam(value = "passwordPay", required = false) String passwordPay) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        /*if (Tool.isBlank(user.getPasswordPay())) {
            return buildFailedInfo(ApiConstance.PASSWORDPAY_IS_NULL);
        }
        if (!passwordPay.equals(user.getPasswordPay())) {
            return buildFailedInfo(ApiConstance.PASSWORD_ERROR);
        }*/
        YwymGoods goods = goodsService.get(goodsId);
        if (goods == null) {
            return buildFailedInfo(ApiConstance.GOODS_NOT_EXIST);
        }
        if (goods.getStock() == 0) {
            return buildFailedInfo(ApiConstance.GOODS_STOCK_NOT);
        }
        String receName = "";
        String recePhone = "";
        String address = "";
        if (Tool.in(goods.getType(), "1")) {
            YwymAddress addr = addressService.get(addressId);
            if (addr == null) {
                return buildFailedInfo(ApiConstance.ADDRESS_NOT_EXIST);
            }
            receName = addr.getName();
            recePhone = addr.getPhone();
            address = addr.getProvName() + addr.getCityName() + addr.getDistName() + addr.getAddress();
        }
        //数量
        int num = 1;
        //处理积分和礼品库存
        try {
            commonService.scoreSubAndNumSub(userId, goodsId, num);
        } catch (Exception e) {
            return buildFailedInfo(e.getMessage());
        }
        //创建订单
        YwymOrder order = new YwymOrder();
        order.setUserId(userId);
        order.setOrderNo(Tool.makeOrderNo());
        order.setGoodsId(goodsId);
        order.setGoodsType(goods.getType());
        order.setGoodsName(goods.getName());
        order.setGoodsIcon(goods.getIcon());
        order.setScore(goods.getNowScore());
        order.setGoodsSpec(spec);
        order.setReceName(receName);
        order.setRecePhone(recePhone);
        order.setReceAddress(address);
        order.setNum(num);
        order.setRemark(remark);
        order.setStatus(YwymConfig.ORDER_STATUS_PEN);
        order.setPayTime(new Date());
        orderService.save(order);
        //用户日志
        YwymLogUser logUser = new YwymLogUser();
        logUser.setType(YwymConfig.LOG_USER_TYPE_OUT);
        logUser.setUserId(userId);
        logUser.setObjectId(order.getId());
        logUser.setScore(order.getScore());
        logUser.setRank(order.getOrderNo());
        logUser.setRemark(YwymConfig.LOG_USER_REMARK_EXCHANGE + "（" + order.getGoodsName() + "）");
        logUserService.save(logUser);
        return buildSuccessInfo(orderService.getReturnOrder(order));
    }

    @LoginValidate
    @ApiOperation(value = "确认收货")
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnGoods.class)})
    @RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> confirmOrder(HttpServletRequest request,
                                                   @ApiParam(value = "用户id", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                   @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                   @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                   @ApiParam(value = "订单id", required = true) @RequestParam(value = "orderId", required = true) String orderId) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        YwymOrder order = orderService.get(orderId);
        if (order == null) {
            return buildFailedInfo(ApiConstance.ORDER_NOT_EXIST);
        }
        order.setStatus("3");
        orderService.save(order);
        return buildSuccessInfo(SUCCESS);
    }

    @LoginValidate
    @ApiOperation(value = "获取订单列表")
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnGoods.class)})
    @RequestMapping(value = "/getOrderList", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> getOrderList(HttpServletRequest request,
                                                   @ApiParam(value = "用户id", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                   @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                   @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                   @ApiParam(value = "订单状态 0全部 1待发货 2待收货 3已完成", required = true) @RequestParam(value = "status", required = true) String status,
                                                   @ApiParam(value = "page", required = false) @RequestParam(value = "page", required = false) Integer page,
                                                   @ApiParam(value = "pageSize", required = false) @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        if (ZERO.equals(status)) {
            status = "";
        }
        Integer first = PAGE, max = PAGE_SIZE;
        if (page != null) {
            first = page;
        }
        if (pageSize != null) {
            max = pageSize;
        }
        YwymOrder _o = new YwymOrder();
        _o.setUserId(userId);
        _o.setStatus(status);
        _o.setFirst(first * max);
        _o.setMax(max);
        List<YwymOrder> list = orderService.findList(_o);
        return buildSuccessInfo(orderService.getReturnOrderList(list));
    }

    @LoginValidate
    @ApiOperation(value = "获取订单详情")
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnGoods.class)})
    @RequestMapping(value = "/getOrderInfo", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> getOrderInfo(HttpServletRequest request,
                                                   @ApiParam(value = "用户id", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                   @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                   @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                   @ApiParam(value = "订单id", required = true) @RequestParam(value = "orderId", required = true) String orderId) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        YwymOrder order = orderService.get(orderId);
        if (order == null) {
            return buildFailedInfo(ApiConstance.ORDER_NOT_EXIST);
        }
        return buildSuccessInfo(orderService.getReturnOrder(order));
    }

}
