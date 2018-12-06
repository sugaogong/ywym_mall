package com.java.controller.api;

import com.java.common.constance.ApiConstance;
import com.java.common.interceptor.annotation.LoginValidate;
import com.java.config.YwymConfig;
import com.java.entity.YwymAddress;
import com.java.entity.YwymLogUser;
import com.java.entity.YwymUser;
import com.java.entity.response.ReturnAddress;
import com.java.entity.response.ReturnLogUser;
import com.java.entity.response.ReturnUser;
import com.java.service.YwymAddressService;
import com.java.service.YwymLogUserService;
import com.java.service.YwymUserService;
import com.java.sys.common.basic.controller.BaseController;
import com.java.sys.common.basic.result.BaseResult;
import com.java.sys.common.cache.CacheUtil;
import com.java.sys.common.utils.Tool;
import io.swagger.annotations.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "api-user-controller", description = "用户信息")
@SpringBootApplication
@RequestMapping("/api/userApiController")
public class UserApiController extends BaseController {

    @Resource
    private YwymUserService userService;
    @Resource
    private YwymAddressService addressService;
    @Resource
    private YwymLogUserService logUserService;


    @LoginValidate
    @ApiOperation(value = "获取用户信息")
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnUser.class)})
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> getUserInfo(HttpServletRequest request,
                                                  @ApiParam(value = "用户id", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                  @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                  @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        return buildSuccessInfo(userService.getReturnUser(user));
    }

    @LoginValidate
    @ApiOperation(value = "编辑用户信息")
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnUser.class)})
    @RequestMapping(value = "/editUserInfo", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> editUserInfo(HttpServletRequest request,
                                                  @ApiParam(value = "用户id", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                  @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                  @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                  @ApiParam(value = "头像", required = false) @RequestParam(value = "headImg", required = false) String headImg,
                                                  @ApiParam(value = "昵称", required = false) @RequestParam(value = "nickname", required = false) String nickname,
                                                  @ApiParam(value = "性别：0-未知 1-男 2-女", required = false) @RequestParam(value = "gender", required = false) String gender) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        if (Tool.isNotBlank(gender) && Tool.notIn(gender, "0","1","2")){
            return buildFailedInfo(ApiConstance.PARAM_ERROR);
        }
        if (Tool.isNotBlank(headImg)){
            user.setHeadImg(headImg);
        }
        if (Tool.isNotBlank(nickname)){
            user.setNickname(Tool.emojiConvert(nickname));
        }
        if (Tool.isNotBlank(gender)){
            user.setGender(Integer.parseInt(gender));
        }
        userService.save(user);
        return buildSuccessInfo(userService.getReturnUser(user));
    }

    @LoginValidate
    @ApiOperation(value = "绑定手机号码")
    @RequestMapping(value = "/bindPhone", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnUser.class)})
    public ResponseEntity<BaseResult> bindPhone(HttpServletRequest request, HttpServletResponse response,
                                                      @ApiParam(value = "userId", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                      @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                      @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                      @ApiParam(value = "验证码", required = true) @RequestParam(value = "code", required = true) String code,
                                                      @ApiParam(value = "手机号码", required = true) @RequestParam(value = "phone", required = true) String phone) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        // 将手机号码存入缓存中,取得时候可以从缓存中取
        String pcode = (String) CacheUtil.get(phone);
        if (Tool.isBlank(pcode) || !pcode.equals(code)) {
            return buildFailedInfo(ApiConstance.VCODE_ERROR);
        }
        user.setUsername(phone);
        user.setPhone(phone);
        userService.save(user);
        return buildSuccessInfo(userService.getReturnUser(user));
    }

    @LoginValidate
    @ApiOperation(value = "添加收货地址")
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnAddress.class)})
    public ResponseEntity<BaseResult> addAddress(HttpServletRequest request, HttpServletResponse response,
                                                 @ApiParam(value = "userId", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                 @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                 @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                 @ApiParam(value = "收货人", required = true) @RequestParam(value = "name", required = true) String name,
                                                 @ApiParam(value = "收货人联系电话", required = true) @RequestParam(value = "phone", required = true) String phone,
                                                 @ApiParam(value = "省id", required = false) @RequestParam(value = "provId", required = false) String provId,
                                                 @ApiParam(value = "省", required = true) @RequestParam(value = "provName", required = true) String provName,
                                                 @ApiParam(value = "市id", required = false) @RequestParam(value = "cityId", required = false) String cityId,
                                                 @ApiParam(value = "市", required = true) @RequestParam(value = "cityName", required = true) String cityName,
                                                 @ApiParam(value = "区id", required = false) @RequestParam(value = "distId", required = false) String distId,
                                                 @ApiParam(value = "区", required = true) @RequestParam(value = "distName", required = true) String distName,
                                                 @ApiParam(value = "详细地址", required = true) @RequestParam(value = "address", required = true) String address,
                                                 @ApiParam(value = "省市区编码", required = false) @RequestParam(value = "areaCode", required = false) String areaCode,
                                                 @ApiParam(value = "标签", required = false) @RequestParam(value = "mark", required = false) String mark,
                                                 @ApiParam(value = "默认地址开关 0关 1开", required = true) @RequestParam(value = "isDefault", required = true) String isDefault) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        if (Tool.notIn(isDefault, "0", "1")) {
            return buildFailedInfo(ApiConstance.PARAM_ERROR);
        }
        if (!Tool.isPhone(phone)){
            return buildFailedInfo(ApiConstance.PARAM_ERROR);
        }
        YwymAddress addr = new YwymAddress();
        addr.setUserId(userId);
        addr.setName(name);
        addr.setPhone(phone);
        addr.setProvId(provId);
        addr.setProvName(provName);
        addr.setCityId(cityId);
        addr.setCityName(cityName);
        addr.setDistId(distId);
        addr.setDistName(distName);
        addr.setAddress(address);
        addr.setAreaCode(areaCode);
        addr.setMark(mark);
        if (ONE.equals(isDefault)) {
            YwymAddress _addr = new YwymAddress();
            _addr.setUserId(userId);
            List<YwymAddress> list = addressService.findList(_addr);
            if (list != null && list.size() > 0) {
                for (YwymAddress addre : list) {
                    addre.setIsDefault(ZERO);
                    addressService.save(addre);
                }
            }
        }
        addr.setIsDefault(isDefault);
        addressService.save(addr);
        return buildSuccessInfo(addressService.getReturnAddress(addr));
    }

    @LoginValidate
    @ApiOperation(value = "获取收货地址列表")
    @RequestMapping(value = "/getAddressList", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnAddress.class)})
    public ResponseEntity<BaseResult> getAddressList(HttpServletRequest request, HttpServletResponse response,
                                                     @ApiParam(value = "userId", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                     @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                     @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        YwymAddress _addr = new YwymAddress();
        _addr.setUserId(userId);
        _addr.setOrderBy("a.is_default DESC");
        List<YwymAddress> addrList = addressService.findList(_addr);
        return buildSuccessInfo(addressService.getReturnAddressList(addrList));
    }

    @LoginValidate
    @ApiOperation(value = "获取收货地址详情")
    @RequestMapping(value = "/getAddressInfo", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnAddress.class)})
    public ResponseEntity<BaseResult> getAddressInfo(HttpServletRequest request, HttpServletResponse response,
                                                     @ApiParam(value = "userId", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                     @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                     @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                     @ApiParam(value = "地址id", required = true) @RequestParam(value = "addressId", required = true) String addressId) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        YwymAddress addr = addressService.get(addressId);
        if (addr == null) {
            return buildFailedInfo(ApiConstance.ADDRESS_NOT_EXIST);
        }
        return buildSuccessInfo(addressService.getReturnAddress(addr));
    }

    @LoginValidate
    @ApiOperation(value = "获取默认收货地址")
    @RequestMapping(value = "/getDefaultAddress", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnAddress.class)})
    public ResponseEntity<BaseResult> getDefaultAddress(HttpServletRequest request, HttpServletResponse response,
                                                     @ApiParam(value = "userId", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                     @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                     @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        YwymAddress _addr = new YwymAddress();
        _addr.setUserId(userId);
        _addr.setIsDefault(ONE);
        List<YwymAddress> addrList = addressService.findList(_addr);
        YwymAddress address = null;
        if (addrList != null && addrList.size() > 0){
            address = addrList.get(0);
        }
        return buildSuccessInfo(addressService.getReturnAddress(address));
    }

    @LoginValidate
    @ApiOperation(value = "修改收货地址")
    @RequestMapping(value = "/editAddress", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnAddress.class)})
    public ResponseEntity<BaseResult> editAddress(HttpServletRequest request, HttpServletResponse response,
                                                  @ApiParam(value = "userId", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                  @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                  @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                  @ApiParam(value = "地址id", required = true) @RequestParam(value = "addressId", required = true) String addressId,
                                                  @ApiParam(value = "收货人", required = false) @RequestParam(value = "name", required = false) String name,
                                                  @ApiParam(value = "收货人联系电话", required = false) @RequestParam(value = "phone", required = false) String phone,
                                                  @ApiParam(value = "省id", required = false) @RequestParam(value = "provId", required = false) String provId,
                                                  @ApiParam(value = "省", required = false) @RequestParam(value = "provName", required = false) String provName,
                                                  @ApiParam(value = "市id", required = false) @RequestParam(value = "cityId", required = false) String cityId,
                                                  @ApiParam(value = "市", required = false) @RequestParam(value = "cityName", required = false) String cityName,
                                                  @ApiParam(value = "区id", required = false) @RequestParam(value = "distId", required = false) String distId,
                                                  @ApiParam(value = "区", required = false) @RequestParam(value = "distName", required = false) String distName,
                                                  @ApiParam(value = "详细地址", required = false) @RequestParam(value = "address", required = false) String address,
                                                  @ApiParam(value = "省市区编码", required = false) @RequestParam(value = "areaCode", required = false) String areaCode,
                                                  @ApiParam(value = "标签", required = false) @RequestParam(value = "mark", required = false) String mark,
                                                  @ApiParam(value = "默认地址开关 0关 1开", required = false) @RequestParam(value = "isDefault", required = false) String isDefault) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        if (Tool.isNotBlank(isDefault)) {
            if (Tool.notIn(isDefault, "0", "1")) {
                return buildFailedInfo(ApiConstance.PARAM_ERROR);
            }
        }
        if (!Tool.isPhone(phone)){
            return buildFailedInfo(ApiConstance.PARAM_ERROR);
        }
        YwymAddress addr = addressService.get(addressId);
        if (addr == null) {
            return buildFailedInfo(ApiConstance.ADDRESS_NOT_EXIST);
        }
        if (Tool.isNotBlank(name)) {
            addr.setName(name);
        }
        if (Tool.isNotBlank(phone)) {
            addr.setPhone(phone);
        }
        if (Tool.isNotBlank(provId)) {
            addr.setProvId(provId);
        }
        if (Tool.isNotBlank(provName)) {
            addr.setProvName(provName);
        }
        if (Tool.isNotBlank(cityId)) {
            addr.setCityId(cityId);
        }
        if (Tool.isNotBlank(cityName)) {
            addr.setCityName(cityName);
        }
        if (Tool.isNotBlank(distId)) {
            addr.setDistId(distId);
        }
        if (Tool.isNotBlank(distName)) {
            addr.setDistName(distName);
        }
        if (Tool.isNotBlank(address)) {
            addr.setAddress(address);
        }
        if (Tool.isNotBlank(areaCode)){
            addr.setAreaCode(areaCode);
        }
        if (Tool.isNotBlank(mark)) {
            addr.setMark(mark);
        }
        if (Tool.isNotBlank(isDefault)) {
            if (ONE.equals(isDefault)) {
                YwymAddress _addr = new YwymAddress();
                _addr.setUserId(userId);
                List<YwymAddress> list = addressService.findList(_addr);
                if (list != null && list.size() > 0) {
                    for (YwymAddress addre : list) {
                        addre.setIsDefault(ZERO);
                        addressService.save(addre);
                    }
                }
            }
            addr.setIsDefault(isDefault);
        }
        addressService.save(addr);
        return buildSuccessInfo(addressService.getReturnAddress(addr));
    }

    @LoginValidate
    @ApiOperation(value = "删除收货地址")
    @RequestMapping(value = "/delAddress", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> delAddress(HttpServletRequest request, HttpServletResponse response,
                                                 @ApiParam(value = "userId", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                 @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                 @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                 @ApiParam(value = "地址id", required = true) @RequestParam(value = "addressId", required = true) String addressId) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        YwymAddress addr = addressService.get(addressId);
        if (addr == null) {
            return buildFailedInfo(ApiConstance.ADDRESS_NOT_EXIST);
        }
        addressService.delete(addr);
        return buildSuccessInfo(null);
    }

    @LoginValidate
    @ApiOperation(value = "获取积分明细列表")
    @RequestMapping(value = "/getLogUserList", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnLogUser.class)})
    public ResponseEntity<BaseResult> getLogUserList(HttpServletRequest request, HttpServletResponse response,
                                                     @ApiParam(value = "userId", required = true) @RequestParam(value = "userId", required = true) String userId,
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
        YwymLogUser _logUser = new YwymLogUser();
        _logUser.setUserId(userId);
        _logUser.setFirst(first * max);
        _logUser.setMax(max);
        List<YwymLogUser> list = logUserService.findList(_logUser);
        return buildSuccessInfo(logUserService.getReturnLogUserList(list));
    }

    @LoginValidate
    @ApiOperation(value = "绑定支付密码")
    @RequestMapping(value = "/bindPasswordPay", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnUser.class)})
    public ResponseEntity<BaseResult> bindPasswordPay(HttpServletRequest request, HttpServletResponse response,
                                                      @ApiParam(value = "userId", required = true) @RequestParam(value = "userId", required = true) String userId,
                                                      @ApiParam(value = "tokenAPP", required = false) @RequestParam(value = "tokenAPP", required = false) String tokenAPP,
                                                      @ApiParam(value = "tokenWEB", required = false) @RequestParam(value = "tokenWEB", required = false) String tokenWEB,
                                                      @ApiParam(value = "验证码", required = true) @RequestParam(value = "code", required = true) String code,
                                                      @ApiParam(value = "支付密码", required = true) @RequestParam(value = "passwordPay", required = true) String passwordPay) {
        YwymUser user = userService.get(userId);
        if (user == null) {
            return buildFailedInfo(ApiConstance.USER_NOT_EXIST);
        }
        //如果没有绑定手机，就需要先绑定手机
        if (Tool.isBlank(user.getUsername())){
            return buildFailedInfo(ApiConstance.PHONE_NOT_BINDED);
        }
        // 将手机号码存入缓存中,取得时候可以从缓存中取
        String pcode = (String) CacheUtil.get(user.getUsername());
        if (Tool.isBlank(pcode) || !pcode.equals(code)) {
            return buildFailedInfo(ApiConstance.VCODE_ERROR);
        }
        user.setPasswordPay(passwordPay);
        userService.save(user);
        return buildSuccessInfo(userService.getReturnUser(user));
    }


}
