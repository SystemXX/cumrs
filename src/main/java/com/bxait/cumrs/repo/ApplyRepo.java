package com.bxait.cumrs.repo;

import com.bxait.cumrs.entity.model.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ApplyRepo extends JpaRepository<Apply,Long> {

    @Query("from Apply where inviter = ?1 or invited = ?1")
    List<Apply> findMyApply(String stuid);

    @Query("from Apply where inviter = ?1")
    Apply findByInviter(String inviter);

    @Query("from Apply where inviter = ?1 and invited = ?2")
    Apply findByInviterAndInvited(String inviter,String invited);

    /**
     * 删除相关邀请
     * @param inviter
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("delete Apply where inviter = ?1 or invited = ?1")
    void deleteApply(String inviter);
}
