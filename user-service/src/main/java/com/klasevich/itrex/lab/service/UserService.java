package com.klasevich.itrex.lab.service;

import com.klasevich.itrex.lab.entity.User;
import com.klasevich.itrex.lab.exception.UserNotFoundException;
import com.klasevich.itrex.lab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Unable to find account " +
                "with id: " + id));
    }

    public Integer createUser (String email, String password, String name, String secondName, String surname,
                               LocalDate dateOfBirth, String identityPassportNumber, String phoneNumber,
                               List<Integer> roles, List<Integer> cards){

        User user = new User(email, password, name, secondName, surname, dateOfBirth, identityPassportNumber,
                phoneNumber, roles, cards);
        return userRepository.save(user).getId();
    }

    public User updateUser(Integer id, String email, String password, String name, String secondName, String surname,
                           LocalDate dateOfBirth, String identityPassportNumber, String phoneNumber,
                           List<Integer> roles, List<Integer> cards){
        User user = new User(id, email, password, name, secondName, surname, dateOfBirth, identityPassportNumber,
                phoneNumber, roles, cards);
        return userRepository.save(user);
    }

    public User deleteUser(Integer id){
        User deletedUser = getUserById(id);
        userRepository.deleteById(id);
        return deletedUser;
    }



}
