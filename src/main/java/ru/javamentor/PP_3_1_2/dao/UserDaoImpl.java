package ru.javamentor.PP_3_1_2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.javamentor.PP_3_1_2.model.User;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(@Qualifier("entityManagerFactory") @NotNull EntityManagerFactory EntityFactory) {
        this.entityManager = EntityFactory.createEntityManager();
    }

    //все юзеры из БД
    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = entityManager.createQuery("from User", User.class).getResultList();
        return allUsers;
    }

    @Override
    public User getUser(int id) {
        TypedQuery<User> query = entityManager.createQuery("from User where id = :user_id", User.class)
                .setParameter("user_id", id);
        return query.getSingleResult();
    }

    //поля для апдейта юзера через temporary
    //не обязательно все 4
    @Override
    public void updateUser(int id, User user) {
        User tmp = getUser(id);
        tmp.setName(user.getName());
        tmp.setSurName(user.getSurName());
        tmp.setDepartment(user.getDepartment());
        tmp.setSalary(user.getSalary());
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
