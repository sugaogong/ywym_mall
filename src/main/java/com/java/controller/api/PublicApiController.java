package com.java.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.entity.YwymUser;
import com.java.entity.response.ReturnUser;
import com.java.service.YwymUserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.common.constance.ApiConstance;
import com.java.sys.common.basic.controller.BaseController;
import com.java.sys.common.basic.result.BaseResult;
import com.java.sys.common.utils.Tool;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.servlet.ModelAndView;

@Api(value="api-public-controller",description="通用业务")
@SpringBootApplication
@RequestMapping("/api/publicApiController")
public class PublicApiController extends BaseController{

    @Resource
    private YwymUserService userService;

    @ApiOperation(value = "重定向（供公众号后台使用）")
    @RequestMapping(value = "/redirectByUnionId", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = ReturnUser.class)})
    public String redirectByUnionId(HttpServletRequest request, HttpServletResponse response,
                                    @ApiParam(value = "微信unionId", required = true) @RequestParam(value = "unionId", required = true) String unionId,
                                    @ApiParam(value = "昵称", required = false) @RequestParam(value = "nickname", required = false) String nickname,
                                    @ApiParam(value = "头像", required = false) @RequestParam(value = "headImg", required = false) String headImg,
                                    @ApiParam(value = "性别：0-未知 1-男 2-女", required = false) @RequestParam(value = "gender", required = false) Integer gender) {
        YwymUser user = userService.getBy("union_id", unionId);
        if (user == null) {
            user = new YwymUser();
            user.setUnionId(unionId);
            user.setNickname(Tool.emojiConvert(nickname));
            user.setHeadImg(headImg);
            user.setGender(gender);
            user.setScore(0.0);
            user.setTotalGetScore(0.0);
            user.setTotalUseScore(0.0);
            user.setLocked(ZERO);
            userService.save(user);
        }
        return "/WEB-INF/index.jsp";
    }


}
