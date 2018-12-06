package com.java.sys.config.mvc;

import com.java.sys.common.utils.SysConfig;
import com.java.sys.common.utils.Tool;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 添加静态资源目录
 */
@Configuration
public class SysMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirs = SysConfig.getConfig("mvc.static.dir");
        if(Tool.isNotBlank(dirs)){
            String[] arr = dirs.split(",");
            if(arr != null && arr.length > 0){
                String projectPath = Tool.getProjectPath();
                for(String dir : arr){
                    registry.addResourceHandler("/" + dir + "**").addResourceLocations("file:" + projectPath + dir);
                }
            }
        }
        super.addResourceHandlers(registry);
    }
}
