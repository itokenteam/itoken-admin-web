package com.zhu.itoken.web.admin.service.fullback;

import com.google.common.collect.Lists;
import com.zhu.itoken.common.dto.BaseResoult;
import com.zhu.itoken.common.dto.com.zhu.itoken.common.HttpStatus;
import com.zhu.itoken.web.admin.service.UserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceFullback implements FallbackFactory<UserService> {

    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public BaseResoult login(String userName, String password) {
                return BaseResoult.notok(Lists.newArrayList(new BaseResoult.Error(""+String.valueOf(HttpStatus.BAD_STATUS.getCode()), HttpStatus.BAD_STATUS.getMessage())));
            }
        };
    }
}
