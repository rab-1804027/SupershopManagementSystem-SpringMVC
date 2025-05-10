package com.bappi.supershopmanagementsystem.dao;

import com.bappi.supershopmanagementsystem.dto.UserDto;
import com.bappi.supershopmanagementsystem.dto.UserInfoDto;
import com.bappi.supershopmanagementsystem.enums.ApprovalStatus;
import com.bappi.supershopmanagementsystem.mapper.UserMapper;
import com.bappi.supershopmanagementsystem.model.Product;
import com.bappi.supershopmanagementsystem.model.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDao {

    UserMapper userMapper;

    UserDao(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public void save(User user) {
        entityManager.persist(user);
    }

    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    public UserDto findByUsername(String username) {
        String query = "SELECT u FROM User u WHERE u.username = :username";
        User user = entityManager.createQuery(query, User.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
        if (user != null) {
            return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
        } else {
            return null;
        }
    }

    public UserDto findByEmail(String email) {
        String query = "SELECT u FROM User u WHERE u.email = :email";
        User user = entityManager.createQuery(query, User.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
        if (user != null) {
            return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
        } else {
            return null;
        }
    }

    public void assignRole(String username, String role, ApprovalStatus status) {
        String sql = "Update User u set u.role= :role, u.approvalStatus= :status where u.username= :username";
        entityManager.createQuery(sql)
                .setParameter("role", role)
                .setParameter("status", status)
                .setParameter("username", username)
                .executeUpdate();
    }

    public List<UserInfoDto> findByApprovalStatus(ApprovalStatus status) {
        String sql = "Select u From User u where u.approvalStatus= :status";
        List<UserInfoDto> userList = new ArrayList<>();
        List<User> users = entityManager.createQuery(sql, User.class)
                .setParameter("status", status)
                .getResultList();
        if (users.isEmpty())
            return null;
        else {
            for (User user : users) {
                userList.add(userMapper.toInfoDto(user));
            }
            return userList;
        }
    }

}
