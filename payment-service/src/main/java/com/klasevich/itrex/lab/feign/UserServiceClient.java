package com.klasevich.itrex.lab.feign;

import com.klasevich.itrex.lab.config.FeignConfig;
import com.klasevich.itrex.lab.feign.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", configuration = FeignConfig.class)
public interface UserServiceClient {

    @GetMapping("/users/v1/{userId}")
    UserResponseDTO getUserById(@PathVariable("userId") Long userId);
}
