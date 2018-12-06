package com.java.controller.api;

import com.java.common.constance.ApiConstance;
import com.java.common.interceptor.annotation.LoginValidate;
import com.java.common.sms.LingKaiSms;
import com.java.common.utils.TokenUtil;
import com.java.common.weixin.WeixinTool;
import com.java.config.YwymConfig;
import com.java.entity.YwymLogLogin;
import com.java.entity.YwymUser;
import com.java.entity.response.ReturnUser;
import com.java.service.YwymLogLoginService;
import com.java.service.YwymUserService;
import com.java.sys.common.basic.controller.BaseController;
import com.java.sys.common.basic.result.BaseResult;
import com.java.sys.common.cache.CacheUtil;
import com.java.sys.common.constance.SysApiConstance;
import com.java.sys.common.utils.Tool;
import io.swagger.annotations.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "api-login-controller", description = "注册登录")
@SpringBootApplication
@RequestMapping("/api/loginApiController")
public class LoginApiController extends BaseController {
    @Resource
    private YwymUserService userService;
    @Resource
    private YwymLogLoginService logLoginService;

    @ApiIgnore
    @ApiOperation(value = "APP登录")
    @RequestMapping(value = "/loginAPP", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> loginAPP(HttpServletRequest request, HttpServletResponse response,
                                               @ApiParam(value = "用户名", required = false) @RequestParam(value = "username", required = false) String username,
                                               @ApiParam(value = "密码", required = false) @RequestParam(value = "password", required = false) String password) {
        //业务获取用户id
        String userId = "userId";
        //设置token过期时间为30天后
        Date expireTime = Tool.datePlu(new Date(), 30);
        //生成token
        String token = TokenUtil.makeTokenAPP(userId, expireTime);
        //更新缓存里面该用户的token
        TokenUtil.updateTokenAPP(userId, token);
        Map<String, Object> map = new HashMap<String, Object>();
        //map.put("user",user);//用户信息
        map.put("token", token);//服务器生成的token
        return buildSuccessInfo(map);
    }


    @ApiIgnore
    @ApiOperation(value = "WEB登录")
    @RequestMapping(value = "/loginWEB", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> loginWEB(HttpServletRequest request, HttpServletResponse response,
                                               @ApiParam(value = "用户名", required = false) @RequestParam(value = "username", required = false) String username,
                                               @ApiParam(value = "密码", required = false) @RequestParam(value = "password", required = false) String password) {
        //业务获取用户id
        String userId = "userId";
        //设置token过期时间为30天后
        Date expireTime = Tool.datePlu(new Date(), 30);
        //生成token
        String token = TokenUtil.makeTokenAPP(userId, expireTime);
        //更新缓存里面该用户的token
        TokenUtil.updateTokenWEB(userId, token);
        Map<String, Object> map = new HashMap<String, Object>();
        //map.put("user",user);//用户信息
        map.put("token", token);//服务器生成的token
        return buildSuccessInfo(map);
    }


    @ApiOperation(value = "获取短信验证码，有效时间5分钟,不需要token")
    @RequestMapping(value = "/smsCode", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> smsCode(HttpServletRequest request, HttpServletResponse response,
                                              @ApiParam(value = "手机号码", required = true) @RequestParam(value = "phone", required = true) String phone,
                                              @ApiParam(value = "类型：1注册，2重设密码，3其他", required = false) @RequestParam(value = "type", required = false) String type) {
        try {
            if (!Tool.isPhone(phone)) {
                return buildFailedInfo(ApiConstance.PARAM_PHONE_ERROR);
            }
            if (Tool.notIn(type, "1", "2", "3")) {
                return buildFailedInfo(ApiConstance.PARAM_ERROR);
            }
            String code = Tool.random6();
            if ("1".equals(type)) {
                YwymUser user = userService.getBy("username", phone);
                if (user != null) {
                    return buildFailedInfo(ApiConstance.USER_EXIST);
                }
                //将code放到redis缓存中
                CacheUtil.set(phone, code, 5 * 60);
                //发短信
                sendMsg(phone, code);
                //展示到调试Map
                CacheUtil.hset("sms_test_map", phone, code);
                CacheUtil.expire("sms_test_map", 5 * 60);
            }else if ("2".equals(type)) {
                YwymUser user = userService.getBy("username", phone);
                if (user == null) {
                    return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
                }
                //将code放到redis缓存中
                CacheUtil.set(phone, code, 5 * 60);
                //发短信
                sendMsg(phone, code);
                //展示到调试Map
                CacheUtil.hset("sms_test_map", phone, code);
                CacheUtil.expire("sms_test_map", 5 * 60);
            }else {
                //将code放到redis缓存中
                CacheUtil.set(phone, code, 5 * 60);
                //发短信
                sendMsg(phone, code);
                //展示到调试Map
                CacheUtil.hset("sms_test_map", phone, code);
                CacheUtil.expire("sms_test_map", 5 * 60);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return buildFailedInfo("系统繁忙！");
        }
        return buildSuccessInfo(null);
    }

    public void sendMsg(String phone, String code) throws Exception {
        System.out.println(code);
        LingKaiSms.sendMsg(phone, "您此次验证码为" + code + "，请在页面中输入以完成验证。【积分商城】");
        //短信统计
        String param = "signName=" + "积分商城" + "&content=" + phone + "您此次验证码为" + code + "，请在页面中输入以完成验证。";
        String url = " http://47.75.166.219:8080/sendMsg/api/sendMsg/sendMsg ";
        Tool.post(param, url);
    }

    @ApiOperation(value = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnUser.class)})
    public ResponseEntity<BaseResult> register(HttpServletRequest request, HttpServletResponse response,
                                               @ApiParam(value = "手机号码", required = true) @RequestParam(value = "phone", required = true) String phone,
                                               @ApiParam(value = "验证码", required = true) @RequestParam(value = "code", required = true) String code,
                                               @ApiParam(value = "微信openId", required = true) @RequestParam(value = "openId", required = true) String openId,
                                               @ApiParam(value = "加密后的密码（大写）", required = true) @RequestParam(value = "password", required = true) String password) {
        try {
            if (Tool.isBlank(phone, password)) {
                return buildFailedInfo("手机号码或密码为空！");
            }
            YwymUser _u = new YwymUser();
            _u.setUsername(openId);
            List<YwymUser> list = userService.findList(_u);
            if (list != null & list.size() > 0) {
                return buildFailedInfo(ApiConstance.USER_EXIST);
            }
            YwymUser user = userService.getBy("open_id", openId);
            if (user == null) {
                return buildFailedInfo("系统错误！");
            }
            // 将手机号码存入缓存中,取得时候可以从缓存中取
            String pcode = (String) CacheUtil.get(phone);
            if (Tool.isBlank(pcode) || !pcode.equals(code)) {
                return buildFailedInfo(ApiConstance.VCODE_ERROR);
            }
            user.setUsername(phone);
            user.setPhone(phone);
            user.setPassword(password);
            userService.save(user);
            return buildSuccessInfo(userService.getReturnUser(user));

        } catch (Exception e) {
            e.printStackTrace();
            return buildFailedInfo(SysApiConstance.BASE_FAIL_CODE);
        }
    }

    @ApiOperation(value = "登录byPhone")
    @RequestMapping(value = "/loginByPhone", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnUser.class)})
    public ResponseEntity<BaseResult> loginByPhone(HttpServletRequest request, HttpServletResponse response,
                                                   @ApiParam(value = "账号", required = true) @RequestParam(value = "phone", required = true) String phone,
                                                   @ApiParam(value = "加密后的密码（大写）", required = true) @RequestParam(value = "password", required = true) String password) {
        YwymUser user = userService.getBy("username", phone);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        if (!password.equals(user.getPassword())) {
            return buildFailedInfo(ApiConstance.ACCOUNT_ERROR);
        }
        // 冻结校验
        if (ONE.equals(user.getLocked())) {
            return buildFailedInfo(ApiConstance.USER_LOCKED);
        }
        user.setLoginStatus(ONE);
        userService.save(user);
        ReturnUser data = userService.getReturnUser(user);
        Date expireTime = Tool.datePlu(new Date(), 30);
        String token = TokenUtil.makeTokenWEB(user.getId(), expireTime);
        TokenUtil.updateTokenWEB(user.getId(), token);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", data);
        map.put("token", token);
        //登录日志
        YwymLogLogin _log = new YwymLogLogin();
        _log.setUserId(user.getId());
        _log.setCreateDateStart(Tool.getDateBegin(new Date()));
        _log.setCreateDateEnd(Tool.getDateEnd(new Date()));
        int count = logLoginService.getCount(_log);
        if (count == 0) {
            logLoginService.save(new YwymLogLogin(user.getId()));
        }
        return buildSuccessInfo(map);
    }

    @ApiOperation(value = "重置登录密码")
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnUser.class)})
    public ResponseEntity<BaseResult> resetPassword(HttpServletRequest request, HttpServletResponse response,
                                                    @ApiParam(value = "手机号码", required = true) @RequestParam(value = "phone", required = true) String phone,
                                                    @ApiParam(value = "验证码", required = true) @RequestParam(value = "code", required = true) String code,
                                                    @ApiParam(value = "加密后的密码（大写）", required = true) @RequestParam(value = "password", required = true) String password) {
        YwymUser user = userService.getBy("username", phone);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        // 将手机号码存入缓存中,取得时候可以从缓存中取
        String pcode = (String) CacheUtil.get(phone);
        if (Tool.isBlank(pcode) || !pcode.equals(code)) {
            return buildFailedInfo(ApiConstance.VCODE_ERROR);
        }
        user.setPassword(password);
        userService.save(user);
        return buildSuccessInfo(userService.getReturnUser(user));
    }

    @LoginValidate
    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> logout(HttpServletRequest request, HttpServletResponse response,
                                             @ApiParam(value = "用户id", required = true) @RequestParam(value = "userId", required = true) String userId,
                                             @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                             @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB) {
        YwymUser user = userService.get(userId);
        if (user != null) {
            user.setLoginStatus(ZERO);
            userService.save(user);
        }
        TokenUtil.clearTokenWEB(userId);
        return buildSuccessInfo(null);
    }

    @ApiOperation(value = "微信登录")
    @RequestMapping(value = "/loginByWeiXin", method = RequestMethod.GET)
    public ResponseEntity<BaseResult> loginByWeiXin() {
        String uri = "http://jf.biqr.cn";
        String openUrl = WeixinTool.getOpenUrl(uri);
        return buildSuccessInfo(openUrl);
    }

    @ApiOperation(value = "根据code获取用户信息")
    @RequestMapping(value = "/getUserByCode", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> getUserByCode(String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> dataMap = WeixinTool.getMapByCode(code);
        System.out.println("-----------code:" + code);
        String openId = dataMap.get("openId");
        System.out.println("-----------openId:" + openId);
        YwymUser user = userService.getBy("open_id", openId);
        if (user == null) {
            user = new YwymUser();
            user.setGender(0);
            user.setHeadImg(YwymConfig.DEFAULT_HEAD_IMG);
            user.setScore(0.0);
            user.setOpenId(openId);
            user.setTotalGetScore(0.0);
            user.setTotalUseScore(0.0);
            user.setLocked(ZERO);
            userService.save(user);
        }
        // 冻结校验
        if (ONE.equals(user.getLocked())) {
            return buildFailedInfo(ApiConstance.USER_LOCKED);
        }
        Map<String, Object> map = new HashMap<>();
        ReturnUser data = userService.getReturnUser(user);
        Date expireTime = Tool.datePlu(new Date(), 30);
        String token = TokenUtil.makeTokenWEB(user.getId(), expireTime);
        TokenUtil.updateTokenWEB(user.getId(), token);
        map.put("user", data);
        map.put("token", token);
        //登录日志
        YwymLogLogin _log = new YwymLogLogin();
        _log.setUserId(user.getId());
        _log.setCreateDateStart(Tool.getDateBegin(new Date()));
        _log.setCreateDateEnd(Tool.getDateEnd(new Date()));
        int count = logLoginService.getCount(_log);
        if (count == 0) {
            logLoginService.save(new YwymLogLogin(user.getId()));
        }
        return buildSuccessInfo(map);
    }

}
