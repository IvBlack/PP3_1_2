package ru.javamentor.PP_3_1_2.dao;

import ru.javamentor.PP_3_1_2.model.User;
import java.util.List;


public interface UserDao {

    public List<User> getAllUsers();

    public User getUser(int id);
    public void updateUser(int id, User user);

    public void saveUser(User customer);
    public void deleteUser(int id);
}
