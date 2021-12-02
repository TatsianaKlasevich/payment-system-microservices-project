package com.klasevich.itrex.lab.service;

import com.klasevich.itrex.lab.persistance.entity.AuthUserDetail;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.persistance.repository.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String USERNAME_EXCEPTION_MESSAGE = "Username or password wrong";

    private final UserDetailRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<User> optionalUser = userDetailRepository.findByUsername(name);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException(USERNAME_EXCEPTION_MESSAGE));

        UserDetails userDetails = new AuthUserDetail(optionalUser.get());
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }
}
