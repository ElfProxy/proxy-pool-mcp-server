package com.elfproxy.service;

import com.elfproxy.domain.GetIpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HttpServiceTest {

    @Autowired
    private HttpService httpService;

    @Test
    public void testGetProxy() {
        GetIpRequest getIpRequest = new GetIpRequest();
        getIpRequest.setCountry("us");
        System.out.println(httpService.getProxy(getIpRequest));
    }

}
