package com.thoughtworks.nho.service.impl;

import com.thoughtworks.nho.cofiguration.security.JWTUser;
import com.thoughtworks.nho.domain.User;
import com.thoughtworks.nho.dto.UserDTO;
import com.thoughtworks.nho.exception.UserExistedException;
import com.thoughtworks.nho.exception.UserNotExistException;
import com.thoughtworks.nho.repository.UserRepository;
import com.thoughtworks.nho.service.UserService;
import com.thoughtworks.nho.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> listUsers() {
        return userRepository.findAll().stream().map(user ->
                UserDTO.builder().id(user.getId()).name(user.getName())
                        .realName(user.getRealName()).build())
                .collect(Collectors.toList());
    }

    @Override
    public User create(User user) {
        user.setId(StringUtils.uuid());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.findByName(user.getName()) != null) {
            throw new UserExistedException("该用户已注册");
        }
        return userRepository.save(user);
    }

    @Override
    public User findByName(String username) {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UserNotExistException(username);
        }
        return userRepository.findByName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotExistException {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UserNotExistException("该账户未注册，请注册");
        }
        return JWTUser.builder()
                .username(user.getName())
                .password(user.getPassword())
                .build();
    }
}
