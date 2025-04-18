package com.elfproxy;


import com.elfproxy.service.ProxyPoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot Application Entry Point.
 *
 * @author ElfProxy
 */
@Slf4j
@SpringBootApplication
public class ProxyPoolServerStart {

    public static void main(String[] args) {
        SpringApplication.run(ProxyPoolServerStart.class, args);
        log.info("Proxy MCP Server started successfully.");
    }


    @Bean
    public ToolCallbackProvider proxyTools(ProxyPoolService proxyPoolService) {
        return MethodToolCallbackProvider.builder().toolObjects(proxyPoolService).build();
    }
}
