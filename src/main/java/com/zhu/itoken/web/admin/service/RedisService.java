package com.zhu.itoken.web.admin.service;

import com.zhu.itoken.web.admin.service.fullback.RedisServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "itoken-service-redis",fallbackFactory = RedisServiceFallBack.class)
public interface RedisService {

    @RequestMapping(value = "/put",method = RequestMethod.POST)
    String put(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("seconds") long seconds);
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    String get(@RequestParam("key") String key);
}
