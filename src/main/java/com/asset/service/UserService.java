package com.asset.service;

import com.asset.dto.UserLoginRequest;
import com.asset.dto.UserLoginResponse;
import com.asset.model.User;

import java.util.List;

public interface UserService {
    UserLoginResponse login(UserLoginRequest request);

    User createUser(User user);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(int id);

    //校验用户是否存在，及状态是否生效
    User findUserByUsername(String username);
    boolean isUserActive(String username);

}
