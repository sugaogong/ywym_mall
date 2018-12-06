package com.java.controller.api;

import com.java.common.constance.ApiConstance;
import com.java.common.interceptor.annotation.DDOSValidate;
import com.java.sys.common.basic.controller.BaseController;
import com.java.sys.common.basic.result.BaseResult;
import com.java.sys.common.cache.CacheUtil;
import com.java.sys.common.tag.SysTree;
import com.java.sys.common.utils.SysFile;
import com.java.sys.common.utils.Tool;
import io.swagger.annotations.*;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.util.*;


/*
 * @SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan
 * @Configuration：提到@Configuration就要提到他的搭档@Bean。使用这两个注解就可以创建一个简单的spring配置类，可以用来替代相应的xml配置文件
 * @EnableAutoConfiguration：能够自动配置spring的上下文，试图猜测和配置你想要的bean类，通常会自动根据你的类路径和你的bean定义自动配置
 * @ComponentScan：会自动扫描指定包下的全部标有@Component的类，并注册成bean，当然包括@Component下的子注解@Service,@Repository,@Controller
 */
@Api(value="api-test-controller",description="测试接口（仅供后台调试使用）")
@SpringBootApplication
@RequestMapping("/api/testApiController")
public class TestApiController extends BaseController{


	@ApiOperation(value = "查看验证码（调试）")
	@RequestMapping(value = "/listVCode", method = RequestMethod.POST)
	@ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
	public ResponseEntity<BaseResult> listVCode(HttpServletRequest request, HttpServletResponse response) {
		return buildSuccessInfo(CacheUtil.entries("sms_test_map"));
	}

	@ApiIgnore
	@ApiOperation(value = "描述aa")
	@RequestMapping(value = "/test1", method = RequestMethod.POST)
	@ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})	
	public ResponseEntity<BaseResult> test1(HttpServletRequest request,HttpServletResponse response,
			@ApiParam(value = "aa",required = true) @RequestParam(value="aa",required=false) String aa){
		return buildSuccessInfo(null);
	}

	@ApiIgnore
	//@LoginValidate//登录校验拦截器注解
	@ApiOperation(value = "描述bb")
	@RequestMapping(value = "/test2", method = RequestMethod.POST)
	@ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})	
	public ResponseEntity<BaseResult> test2(HttpServletRequest request,HttpServletResponse response,
			@ApiParam("aa") @RequestParam(value="aa",required=false) String aa){
		return buildSuccessInfo(null);
	}

	@ApiIgnore
	@DDOSValidate
	@ApiOperation(value = "描述cc")
	@RequestMapping(value = "/test3", method = RequestMethod.POST)
	@ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})	
	public ResponseEntity<BaseResult> test3(HttpServletRequest request,HttpServletResponse response,
			@ApiParam("aa") @RequestParam(value="aa",required=false) String aa){
		return buildSuccessInfo(null);
	}

	@ApiIgnore
	@DDOSValidate(second = 5)
	@ApiOperation(value = "描述dd")
	@RequestMapping(value = "/test4", method = RequestMethod.POST)
	@ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})	
	public ResponseEntity<BaseResult> test4(HttpServletRequest request,HttpServletResponse response,
			@ApiParam("aa") @RequestParam(value="aa",required=false) String aa){
		return buildSuccessInfo(null);
	}

	@ApiIgnore
	@ApiOperation(value = "testUploadFile")
	@RequestMapping(value = "/testUploadFile", method = RequestMethod.POST)
	@ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
	public ResponseEntity<BaseResult> testUploadFile(HttpServletRequest request,HttpServletResponse response,
		@ApiParam(value = "file") @RequestParam(value="file",required=false) MultipartFile file){
		if(file == null){
			return buildFailedInfo("file is null");
		}
		SysFile sysFile = Tool.uploadFile(file,null);
		Map<String,Object> map = new HashMap<>();
		map.put("path",sysFile.path);
		map.put("url",Tool.toUrl(sysFile.path));
		return buildSuccessInfo(map);
	}

	@ApiIgnore
	@ApiOperation(value = "refreshSYS")
	@RequestMapping(value = "/refreshSYS", method = RequestMethod.POST)
	@ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
	public ResponseEntity<BaseResult> refreshSYS(HttpServletRequest request,HttpServletResponse response,
		@ApiParam("aa") @RequestParam(value="aa",required=false) String aa){
		CacheUtil.refreshMenu();
		CacheUtil.refreshRealm();
		return buildSuccessInfo(null);
	}


	@ApiIgnore
	//配合标签<sys:tree/>使用，如：<sys:tree url="ajax路径" name="字段名称" value="${字段回显 }" required="填true、false或者required"/>
	@ApiOperation(value = "getTreeList")
	@RequestMapping(value = "/getTreeList", method = RequestMethod.POST)
	@ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})	
	public ResponseEntity<BaseResult> getTreeList(HttpServletRequest request,HttpServletResponse response,
			@ApiParam("aa") @RequestParam(value="aa",required=false) String aa){
		List<SysTree> list1 = new ArrayList<SysTree>();//一级列表
		List<SysTree> list2 = new ArrayList<SysTree>();//二级列表
		List<SysTree> list3 = new ArrayList<SysTree>();//三级列表
		//节点元素
		SysTree tree1 = new SysTree("id1", "name1");
		SysTree tree2 = new SysTree("id2", "name2");
		SysTree tree3 = new SysTree("id3", "name3");
		SysTree tree1A = new SysTree("id1a", "name1a");
		SysTree tree1B = new SysTree("id1b", "name1b");
		SysTree tree1B1 = new SysTree("id1b1", "name1b1");
		//二级列表其中一个元素放进三级列表
		list3.add(tree1B1);
		tree1B.setChildren(list3);
		//一级列表其中一个元素放进二级列表
		list2.add(tree1A);
		list2.add(tree1B);
		tree1.setChildren(list2);
		//把一级元素放进一级列表
		list1.add(tree1);
		list1.add(tree2);
		list1.add(tree3);
		//返回一级列表
		return buildSuccessInfo(list1);
	}



	@ApiIgnore
	@RequestMapping(value = "/toExcel", method = RequestMethod.GET)
	public String toExcel(HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttribute){
		try{
			Tool.info("---- toExcel2");
			OutputStream os = response.getOutputStream();
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition","attachment;filename="+System.currentTimeMillis()/1000+".xlsx");
			response.setContentType("application/msexcel");
			
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet();
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("标题a");
			row.createCell(1).setCellValue("标题b");
			row.createCell(2).setCellValue("标题c");
			//sheet.autoSizeColumn(column);
			
			workbook.write(os);
			workbook.close();
			os.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}



	@ApiIgnore
	@ApiOperation(value = "readFromExcel")
	@RequestMapping(value = "/readFromExcel", method = RequestMethod.POST)
	@ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})	
	public ResponseEntity<BaseResult> readFromExcel(HttpServletRequest request,HttpServletResponse response,
			@ApiParam("file") @RequestParam(value="file",required=false) MultipartFile file){
		if(file != null && file.getSize() > 0){
			String suffix = Tool.getFileSuffix(file);
			if(Tool.notIn(suffix, ".xls",".xlsx")) {
				return buildFailedInfo("文件后缀必须是xls或者xlsx");
			}

			// 读取上传的excel文件
			File f = Tool.toFile(file);
			if(f == null){
				return buildFailedInfo("函数toFile发生错误");
			}
			try {
				XSSFWorkbook workbook = new XSSFWorkbook(f);
				for(int i=0;i<workbook.getNumberOfSheets();i++) {
					XSSFSheet sheet = workbook.getSheetAt(i);
					if(sheet != null) {
						for(int r=0;r<=sheet.getLastRowNum();r++){
							XSSFRow row = sheet.getRow(r);
							if(row != null) {
								for(int c=0;c<row.getLastCellNum();c++) {
									XSSFCell cell = row.getCell(c);
									String value = getExcelValue(cell);
									System.out.print(value+" ("+c+")| ");
								}
								System.out.println("--- : "+r);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return buildSuccessInfo(null);
	}
	
	
	public String getExcelValue(XSSFCell cell) {
		if(cell != null && cell.getCellType() != XSSFCell.CELL_TYPE_BLANK) {
			String value = null;
			if(cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
				value = cell.getStringCellValue();
			}else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				if(HSSFDateUtil.isCellDateFormatted(cell)) {
					value = Tool.formatDate(cell.getDateCellValue(), null);
				}else {
					value = Tool.double2String(cell.getNumericCellValue(), null);
				}
			}else {
				return "-1";
			}
			return value;
		}
		return null;
			
	}

}
