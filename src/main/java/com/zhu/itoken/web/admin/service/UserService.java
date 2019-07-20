package com.zhu.itoken.web.admin.service;

import com.zhu.itoken.common.dto.BaseResoult;
import com.zhu.itoken.web.admin.service.fullback.AdminServiceFullback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "itoken-service-admin",fallbackFactory = AdminServiceFullback.class)
public interface UserService {

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    BaseResoult login(@RequestParam(value="userName") String userName, @RequestParam(value="password") String password);
}
