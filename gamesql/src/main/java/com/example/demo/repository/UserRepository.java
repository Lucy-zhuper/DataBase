package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsernameAndPwd(String username, String password);
    User findByUserName(String userName);
//    List<User> findByUsername(String username);
}
