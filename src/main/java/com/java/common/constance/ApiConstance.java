package com.java.common.constance;

import com.java.sys.common.constance.SysApiConstance;

public class ApiConstance extends SysApiConstance {
	
	
	public static String getMessage(int errorCode){
		return map.get(errorCode);
	}
	
	public final static int EMPTY_NAME = 2001;
	public final static int EMPTY_PASSWORD = 2002;
	public final static int CATLOGID_NOT_EXIST=3001;
	public final static int GOODS_NOT_EXIST=3002;
	public final static int GOODS_STOCK_NOT=3003;




	static{
		map.put(EMPTY_NAME, "账号不能为空");
		map.put(EMPTY_PASSWORD,"密码不能为空");
		map.put(CATLOGID_NOT_EXIST,"分类不存在");
		map.put(GOODS_NOT_EXIST,"礼品不存在");
		map.put(GOODS_STOCK_NOT,"礼品库存不足");
	}
	
	
	
}
