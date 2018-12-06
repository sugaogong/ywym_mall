package com.java.sys.common.listener;

import com.java.common.constance.MyConstance;
import com.java.sys.common.cache.CacheUtil;
import com.java.sys.common.utils.Tool;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SysApplicationListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {


    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        int port = event.getEmbeddedServletContainer().getPort();
        CacheUtil.set(MyConstance.KEY_CONFIG_SERVER_PORT,port);
    }
}
