package com.asset.mapper;

import com.asset.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int insertUser(User user);
    List<User> getAllUsers();
    int updateUser(User user);
    int deleteUser(int id);


    User getUserByUsername(String username);
}
