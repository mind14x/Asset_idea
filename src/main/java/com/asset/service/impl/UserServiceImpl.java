package com.asset.service.impl;

import com.asset.dto.UserLoginRequest;
import com.asset.dto.UserLoginResponse;
import com.asset.mapper.UserMapper;
import com.asset.model.User;
import com.asset.service.UserService;
import com.asset.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        User user = userMapper.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (user == null) {
            throw new RuntimeException("Invalid username or password");
        }
        if (!"active".equalsIgnoreCase(user.getStatus())) {
            throw new RuntimeException("User is not active");
        }
        String token = JwtUtil.generateToken(user.getUsername(), user.getRole(), 604800);
        return new UserLoginResponse(token, user.getUsername(), user.getRole(),user.getStatus());
    }

    @Override
    public User createUser(User user) {
        userMapper.insertUser(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User updateUser(User user) {
        userMapper.updateUser(user);
        return user;
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }




    @Override
    public User findUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public boolean isUserActive(String username) {
        User user = findUserByUsername(username);
        return user != null && "active".equalsIgnoreCase(user.getStatus());
    }

}
