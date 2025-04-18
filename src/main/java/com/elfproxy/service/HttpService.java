package com.elfproxy.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.elfproxy.config.ProxyConfig;
import com.elfproxy.domain.GetIpRequest;
import com.elfproxy.util.ObjectToMapConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * http request service
 *
 * @author ElfProxy
 */
@Slf4j
@Service
public class HttpService {

    private final ProxyConfig proxyConfig;

    public HttpService(ProxyConfig proxyConfig) {
        this.proxyConfig = proxyConfig;
    }


    /**
     * get proxy
     *
     * @param getIpRequest request parameters
     * @return agent information
     */
    public String getProxy(GetIpRequest getIpRequest) {
        getIpRequest.setApiKey(proxyConfig.getKey());
        getIpRequest.setCountry(getIpRequest.getCountry() + "_cityRandom");
        try (
                HttpResponse httpResponse = HttpRequest.get("https://api.elfproxy.com/dynamicIp/findApi")
                        .timeout(20 * 1000)
                        .form(ObjectToMapConverter.convertToMap(getIpRequest))
                        .execute()
        ) {
            String body = httpResponse.body();
            log.info("obtain proxy information:{}", body);
            return body;
        } catch (Exception e) {
            log.error("failed to obtain proxy information:{}", e.getMessage());
            return "Failed to obtain proxy information with error message::" + e.getMessage();
        }
    }

}
