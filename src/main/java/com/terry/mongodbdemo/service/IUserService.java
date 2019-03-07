package com.terry.mongodbdemo.service;

import com.terry.mongodbdemo.dto.User;

import java.util.List;

public interface IUserService {

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> getAllUser();

    /**
     * 按名字查找用户
     *
     * @param username
     * @return
     */
    List<User> getUserByName(String username);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    User addUser(User user);

    /**
     * 删除用户
     *
     * @param username
     */
    void deleteByUserName(String username);

    /**
     * 更新用户
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 有事务的更新
     *
     * @param user
     */
    void transactionUpdate(User user);

}
