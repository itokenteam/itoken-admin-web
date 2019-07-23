package com.zhu.itoken.web.admin.service.fullback;

import com.zhu.itoken.common.dto.hystrix.FallBack;
import com.zhu.itoken.web.admin.service.RedisService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisServiceFallBack implements FallbackFactory<RedisService> {
    @Override
    public RedisService create(Throwable throwable) {
        return new RedisService() {
            @Override
            public String put(String key, String value, long seconds) {
                return FallBack.badGatAway();
            }

            @Override
            public String get(String key) {
                return FallBack.badGatAway();
            }
        };
    }
}
