package com.bxait.cumrs.repo;

import com.bxait.cumrs.entity.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Long> {

    @Query("from Teacher where teaid = ?1")
    Teacher findTeaByTeaId(String teaId);

    /**
     * 查询不包括自身的不再队伍中的成员
     * @param teaId
     * @param occupy
     * @return
     */
    @Query("from Teacher where teaId != ?1 and occupy = ?2")
    List<Teacher> findAllByNotTeaidAAndOccupy(String teaId,String occupy);


    /**
     * 更新队伍状态
     * @param teaid
     * @param occpy
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("update Teacher set occupy = ?2 where teaId = ?1")
    void updateTeaOccupyInTeam(String teaid,String occpy);

    /**
     * 授权
     * @param id
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("update Teacher set type = 'C' where id = ?1")
    void empower(Long id);

    /**
     * 取消授权
     * @param id
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("update Teacher set type = 'B' where id = ?1")
    void revoke(Long id);

    /**
     * 更新锁定状态
     * @param teaid
     * @param lock
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("update Teacher set locked = ?2 where teaId = ?1")
    void updateTeaLock(String teaid,String lock);
}
