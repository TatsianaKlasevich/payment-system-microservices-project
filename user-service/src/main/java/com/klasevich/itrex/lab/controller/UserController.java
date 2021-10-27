package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.UserRequestDTO;
import com.klasevich.itrex.lab.controller.dto.UserResponseDTO;
import com.klasevich.itrex.lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public UserResponseDTO getUser(@PathVariable Integer userId) {
        return new UserResponseDTO(userService.getUserById(userId));
    }

    @PostMapping("/")
    public Integer createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.createPerson(userRequestDTO.getEmail(), userRequestDTO.getPassword(), userRequestDTO.getName(),
                userRequestDTO.getSecondName(), userRequestDTO.getSurname(), userRequestDTO.getDateOfBirth(),
                userRequestDTO.getIdentityPassportNumber(), userRequestDTO.getPhoneNumber(), userRequestDTO.getRoles(),
                userRequestDTO.getCards());
    }

    @PutMapping("/{userId}")
    public UserResponseDTO updateUser(@PathVariable Integer userId,
                                      @RequestBody UserRequestDTO userRequestDTO) {
        return new UserResponseDTO(userId, userRequestDTO.getEmail(), userRequestDTO.getPassword(), userRequestDTO.getName(),
                userRequestDTO.getSecondName(), userRequestDTO.getSurname(), userRequestDTO.getDateOfBirth(),
                userRequestDTO.getIdentityPassportNumber(), userRequestDTO.getPhoneNumber(), userRequestDTO.getRoles(),
                userRequestDTO.getCards());
    }

    @DeleteMapping("/{userId}")
    public UserResponseDTO deleteUser(@PathVariable Integer userId) {
        return new UserResponseDTO(userService.deleteUser(userId));
    }
}
