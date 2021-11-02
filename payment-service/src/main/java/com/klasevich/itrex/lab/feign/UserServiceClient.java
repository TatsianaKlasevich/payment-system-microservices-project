package com.klasevich.itrex.lab.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @RequestMapping(value="/users/{userId}", method = RequestMethod.GET)
    UserResponseDTO getUserById(@PathVariable("userId") Long userId);
}
