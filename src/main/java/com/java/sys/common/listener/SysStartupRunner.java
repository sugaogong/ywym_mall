package com.java.sys.common.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.java.sys.common.cache.CacheUtil;
import com.java.sys.common.utils.Tool;



@Component
public class SysStartupRunner implements CommandLineRunner{
		
		
	@Override
	public void run(String... args) throws Exception {
		//缓存初始化
		CacheUtil.init();
		Tool.info("----- SysStartupRunner->run");
	}

}
