package com.elfproxy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * proxy config
 *
 * @author ElfProxy
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "api")
public class ProxyConfig {
    private String key;
}
