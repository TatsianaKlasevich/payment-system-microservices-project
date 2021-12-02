package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.UserRequestDTO;
import com.klasevich.itrex.lab.controller.dto.UserResponseDTO;
import com.klasevich.itrex.lab.mapper.UserRequestDTOToUserMapper;
import com.klasevich.itrex.lab.mapper.UserToUserResponseDTOMapper;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final UserToUserResponseDTOMapper userToUserResponseDTOMapper;

    @GetMapping("{userId}")
    @ApiOperation("Get user by id")
    @PreAuthorize("hasAuthority('read_user')")
    public UserResponseDTO getUser(@PathVariable Long userId) {
        return userToUserResponseDTOMapper.convert(userService.getUserById(userId));
    }

    @GetMapping()
    @ApiOperation("Get all users by some page and sort")
    @PreAuthorize("hasRole('BANK_EMPLOYEE')")
    public List<UserResponseDTO> findAllUsers(Pageable pageable) {
        return userService.findAllUsers(pageable).stream()
                .map(userToUserResponseDTOMapper::convert)
                .collect(Collectors.toList());
    }

    @PostMapping()
    @ApiOperation("Create user")
    @PreAuthorize("hasAuthority('create_user')")
    public UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        User user = userRequestDTOToUserMapper.convert(userRequestDTO);
        return userToUserResponseDTOMapper.convert(userService.createUser(user));
    }

    @PutMapping("{userId}")
    @ApiOperation("Update user")
    @PreAuthorize("hasAuthority('update_user')")
    public UserResponseDTO updateUser(@PathVariable Long userId,
                                      @RequestBody @Valid UserRequestDTO userRequestDTO) {
        User user = userRequestDTOToUserMapper.convert(userRequestDTO);
        user.setUserId(userId);
        return userToUserResponseDTOMapper.convert(userService.updateUser(user));
    }

    @DeleteMapping("{userId}")
    @ApiOperation("Delete user")
    @PreAuthorize("hasAuthority('delete')")
    public UserResponseDTO deleteUser(@PathVariable Long userId) {
        return userToUserResponseDTOMapper.convert(userService.deleteUser(userId));
    }
}
