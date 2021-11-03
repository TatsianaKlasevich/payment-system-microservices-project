package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.UserRequestDTO;
import com.klasevich.itrex.lab.controller.dto.UserResponseDTO;
import com.klasevich.itrex.lab.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("v1")
@RequiredArgsConstructor
@RestController("/")
@Api("User controller")
public class UserController {

    private final UserService userService;

    @GetMapping("{userId}")
    @ApiOperation("Get user")
    public UserResponseDTO getUser(@PathVariable Long userId) {
        return new UserResponseDTO(userService.getUserById(userId));
    }

    @GetMapping("v1/")
    @ApiOperation("Get all users")
    public List<UserResponseDTO> findAllUsers() {
        return userService.findAllUsers();
    }


    @PostMapping()
    @ApiOperation("Create user")
    public Long createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }

    @PutMapping("{userId}")
    @ApiOperation("Update user")
    public UserResponseDTO updateUser(@PathVariable Long userId,
                                      @RequestBody UserRequestDTO userRequestDTO) {
        return new UserResponseDTO(userService.updateUser(userId, userRequestDTO));
    }

    @DeleteMapping("{userId}")
    @ApiOperation("Delete user")
    public UserResponseDTO deleteUser(@PathVariable Long userId) {
        return new UserResponseDTO(userService.deleteUser(userId));
    }
}
