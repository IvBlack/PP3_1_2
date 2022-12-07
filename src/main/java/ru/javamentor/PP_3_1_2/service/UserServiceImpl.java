package ru.javamentor.PP_3_1_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.PP_3_1_2.dao.UserDao;
import ru.javamentor.PP_3_1_2.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    final private ru.javamentor.PP_3_1_2.dao.UserDao UserDao;


    @Autowired
    public UserServiceImpl(UserDao UserDao) {
        this.UserDao = UserDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return UserDao.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(int id) {
        return UserDao.getUser(id);
    }

    @Override
    @Transactional
    public void updateUser(int id, User user) {
        UserDao.updateUser(id, user);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        UserDao.saveUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        UserDao.deleteUser(id);
    }
}

