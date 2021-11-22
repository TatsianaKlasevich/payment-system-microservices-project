package com.klasevich.itrex.lab.feign;

import com.klasevich.itrex.lab.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-service", configuration = FeignConfig.class)
public interface UserServiceClient {

    //    @PreAuthorize("hasAuthority('read_user')")
    @RequestMapping(value = "/users/v1/{userId}", method = RequestMethod.GET)
    UserResponseDTO getUserById(@PathVariable("userId") Long userId);
}
