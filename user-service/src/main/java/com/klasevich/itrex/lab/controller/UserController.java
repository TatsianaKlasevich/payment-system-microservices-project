package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.UserRequestDTO;
import com.klasevich.itrex.lab.controller.dto.UserResponseDTO;
import com.klasevich.itrex.lab.mapper.UserRequestDTOToUserMapper;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("v1")
@RequiredArgsConstructor
@RestController("/")
@Api("User controller")
public class UserController {

    private final UserService userService;
    private final UserRequestDTOToUserMapper userRequestDTOToUserMapper;

    @GetMapping("{userId}")
    @ApiOperation("Get user by id")
    @PreAuthorize("hasAuthority('read_user')")
    public UserResponseDTO getUser(@PathVariable Long userId) {
        return new UserResponseDTO(userService.getUserById(userId));
    }

    @GetMapping()
    @ApiOperation("Get all users by some page and sort")
    @PreAuthorize("hasRole('BANK_EMPLOYEE')")
    public List<UserResponseDTO> findAllUsers(Pageable pageable) {
        return userService.findAllUsers(pageable).stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping()
    @ApiOperation("Create user")
    @PreAuthorize("hasAuthority('create_user')")
    public Long createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        User user = userRequestDTOToUserMapper.convert(userRequestDTO);
        return userService.createUser(user);
    }

    @PutMapping("{userId}")
    @ApiOperation("Update user")
    @PreAuthorize("hasAuthority('update_user')")
    public UserResponseDTO updateUser(@PathVariable Long userId,
                                      @RequestBody @Valid UserRequestDTO userRequestDTO) {
        User user = userRequestDTOToUserMapper.convert(userRequestDTO);
        user.setUserId(userId);
        return new UserResponseDTO(userService.updateUser(user));
    }

    @DeleteMapping("{userId}")
    @ApiOperation("Delete user")
    @PreAuthorize("hasAuthority('delete_user')")
    public UserResponseDTO deleteUser(@PathVariable Long userId) {
        return new UserResponseDTO(userService.deleteUser(userId));
    }
}
