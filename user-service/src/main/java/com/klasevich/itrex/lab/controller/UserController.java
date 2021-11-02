package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.UserRequestDTO;
import com.klasevich.itrex.lab.controller.dto.UserResponseDTO;
import com.klasevich.itrex.lab.mappers.UserRequestDTOToUserMapper;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("/")
public class UserController {

    private final UserService userService;


    @GetMapping("{userId}")
    public UserResponseDTO getUser(@PathVariable Long userId) {
        return new UserResponseDTO(userService.getUserById(userId));
    }

    @PostMapping()
    public Long createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }

    @PutMapping("{userId}")
    public UserResponseDTO updateUser(@PathVariable Long userId,
                                      @RequestBody UserRequestDTO userRequestDTO) {
        return new UserResponseDTO(userService.updateUser(userId, userRequestDTO));
    }

    @DeleteMapping("{userId}")
    public UserResponseDTO deleteUser(@PathVariable Long userId) {
        return new UserResponseDTO(userService.deleteUser(userId));
    }
}
