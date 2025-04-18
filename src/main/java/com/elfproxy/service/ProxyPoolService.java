package com.elfproxy.service;

import cn.hutool.core.util.StrUtil;
import com.elfproxy.domain.GetIpRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * proxy pool service
 *
 * @author ElfProxy
 */
@Slf4j
@Service
public class ProxyPoolService {


    private final ObjectMapper objectMapper;
    private final HttpService httpService;

    public ProxyPoolService(ObjectMapper objectMapper, HttpService httpService) {
        this.objectMapper = objectMapper;
        this.httpService = httpService;
    }

    /**
     * Set a key-value pair in Redis with optional expiration time
     *
     * @param jsonArgs JSON string containing key, value and optional expireSeconds
     * @return Operation result message
     */
    @Tool(name = "getProxy", description = "Retrieve an IP address from the proxy pool and use the 'code' field to tell me which country's code you need. I will extract the IP address from the code and provide it to you")
    public String getProxy(String jsonArgs) {
        log.info("Received setValue request: {}", jsonArgs);
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonArgs);
            String code = jsonNode.findValue("code").asText();
            if (StrUtil.isBlank(code)) {
                return "The code field cannot be empty! What needs to be filled in is the country code";
            }
            GetIpRequest getIpRequest = new GetIpRequest();
            getIpRequest.setCountry(code.toLowerCase());
            return httpService.getProxy(getIpRequest);
        } catch (IOException e) {
            return "Error parsing JSON arguments: " + e.getMessage();
        }
    }
}
