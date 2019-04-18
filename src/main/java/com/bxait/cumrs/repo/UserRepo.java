package com.bxait.cumrs.repo;

import com.bxait.cumrs.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query("from User where userName = ?1")
    User findUserByUserName(String userName);
}
