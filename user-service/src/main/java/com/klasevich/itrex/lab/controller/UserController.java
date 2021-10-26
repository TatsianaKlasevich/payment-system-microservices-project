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

    @GetMapping("/{id}")
    public UserResponseDTO getAccount(@PathVariable Integer id){
        return new UserResponseDTO(userService.getUserById(id));
    }

    @PostMapping("/")
    public Integer createUser(@RequestBody UserRequestDTO userRequestDTO){
        return userService.createUser(userRequestDTO.getEmail(),userRequestDTO.getPassword(),userRequestDTO.getName(),
                userRequestDTO.getSecondName(),userRequestDTO.getSurname(),userRequestDTO.getDateOfBirth(),
                userRequestDTO.getIdentityPassportNumber(),userRequestDTO.getPhoneNumber(), userRequestDTO.getRoles(),
                userRequestDTO.getCards());
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Integer id,
            @RequestBody UserRequestDTO userRequestDTO){
        return new UserResponseDTO(id, userRequestDTO.getEmail(),userRequestDTO.getPassword(),userRequestDTO.getName(),
                userRequestDTO.getSecondName(),userRequestDTO.getSurname(),userRequestDTO.getDateOfBirth(),
                userRequestDTO.getIdentityPassportNumber(),userRequestDTO.getPhoneNumber(), userRequestDTO.getRoles(),
                userRequestDTO.getCards());
    }

    @DeleteMapping("/{id}")
    public UserResponseDTO deleteUser(@PathVariable Integer id){
        return new UserResponseDTO(userService.deleteUser(id));
    }
}
