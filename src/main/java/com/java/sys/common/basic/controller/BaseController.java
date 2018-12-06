package com.java.sys.common.basic.controller;

import com.java.common.constance.MyConstance;
import com.java.entity.YwymImg;
import com.java.service.YwymImgService;
import com.java.sys.common.utils.Tool;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.common.constance.ApiConstance;
import com.java.sys.common.basic.result.BaseResult;
import com.java.sys.common.tag.SysMessage;

import javax.annotation.Resource;

public abstract class BaseController {

	@Resource
	protected YwymImgService imgService;
	
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";

	public static final  String ONE ="1";
	public static final  String ZERO ="0";

	public static final Integer PAGE = 0;	//默认当前页
	public static final Integer PAGE_SIZE =10; //页面大小


	/**
	 * API接口返回成功的实体
	 * @param resultData 实体数据
	 * @return ResponseEntity<BaseResult>
	 */
	protected ResponseEntity<BaseResult> buildSuccessInfo(Object resultData) {
		BaseResult result = new BaseResult(ApiConstance.BASE_SUCCESS_CODE, ApiConstance.getMessage(ApiConstance.BASE_SUCCESS_CODE), resultData);
		return new ResponseEntity<BaseResult>(result, HttpStatus.OK);
	}
	
	/**
	 * API接口返回失败的实体
	 * @param errorCode 错误代码号
	 * @return ResponseEntity<BaseResult>
	 */
	protected ResponseEntity<BaseResult> buildFailedInfo(int errorCode) {
		BaseResult result = new BaseResult(errorCode, ApiConstance.getMessage(errorCode), null);
		return new ResponseEntity<BaseResult>(result, HttpStatus.OK);
	}
	
	/**
	 * 方法名：buildFailedInfo
	 * 详述：API接口返回失败的实体
	 * @param errorMsg 自定义错误提示
	 * @return
	 */
	protected ResponseEntity<BaseResult> buildFailedInfo(String errorMsg) {
		BaseResult result = new BaseResult(ApiConstance.BASE_FAIL_CODE, errorMsg, null);
		return new ResponseEntity<BaseResult>(result, HttpStatus.OK);
	}
	
	/**
	 * 重定向返回系统提示信息
	 * @param message 提示内容
	 * @param redirectAttributes 重定向属性对象
	 * @param type ：success 成功信息，error 错误信息
	 */
	protected void addMessage(String message, String type, RedirectAttributes redirectAttributes) {
		SysMessage sysMessage = new SysMessage(message, type);
		redirectAttributes.addFlashAttribute("message", sysMessage);
	}
	
	/**
	 * 重定向返回系统提示信息
	 * @param message 提示内容
	 * @param model mvc模型对象
	 * @param type ：success 成功信息，error 错误信息
	 */
	protected void addMessage(String message, String type, Model model) {
		SysMessage sysMessage = new SysMessage(message, type);
		model.addAttribute("message", sysMessage);
	}

	/**
	 * 删除多张照片
	 *
	 * @param imgs
	 */
	public void delImgs(String imgs) {
		if (Tool.isNotBlank(imgs)) {
			String[] ids = imgs.split(",");
			for (String id : ids) {
				YwymImg img = imgService.get(id);
				if (img != null) {
					imgService.delete(img);
				}
			}
		}
	}

	/**
	 * 上传多张图片
	 *
	 * @param files
	 * @param id
	 * @param type
	 */
	public void uploadImgs(MultipartFile[] files, String id, String type) {
		if (files != null && files.length > 0) {
			for (MultipartFile file : files) {
				if (file != null && file.getSize() > 0) {
					YwymImg img = new YwymImg();
					img.setObjectId(id);
					img.setType(type);
					img.setImg(Tool.uploadFile(file, MyConstance.UPLOAD_IMG_DIR).getPath());
					img.setRank("9999");
					imgService.save(img);
				}
			}
		}
	}
}
