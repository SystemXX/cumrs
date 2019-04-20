package com.bxait.cumrs.repo;

import com.bxait.cumrs.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query("from User where userName = ?1")
    User findUserByUserName(String userName);

    /**
     * 授权
     * @param username
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("update User set identity = '2' where userName = ?1")
    void empower(String username);

    /**
     *取消授权
     * @param username
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("update User set identity = '1' where userName = ?1")
    void revoke(String username);
}
