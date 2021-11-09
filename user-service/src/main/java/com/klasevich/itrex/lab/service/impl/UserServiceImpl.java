package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.mappers.UserRequestDTOToUserMapper;
import com.klasevich.itrex.lab.persistance.dto.UserRequestDTO;
import com.klasevich.itrex.lab.persistance.dto.UserResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.persistance.repository.UserRepository;
import com.klasevich.itrex.lab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRequestDTOToUserMapper userRequestDTOToUserMapper;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Long createUser(UserRequestDTO userRequestDTO) {
        User user = userRequestDTOToUserMapper.convert(userRequestDTO);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public void updateUser(Long userId, UserRequestDTO userRequestDTO) {
        User user = userRequestDTOToUserMapper.convert(userRequestDTO);
        user.setId(userId);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void saveAll(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public List<UserResponseDTO> findAllUsers() {
        List<User> all = userRepository.findAll();
        return all.stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }
}