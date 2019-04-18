package com.bxait.cumrs.repo;
import com.bxait.cumrs.entity.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

    @Query("from Student where stuid = ?1")
    Student findStuByStuId(String stuId);

    @Query("from Student where stuid in ?1")
    List<Student> findStuByStuIds(List<String> stuIds);

    /**
     * 更新所在队伍状态
     * @param stuid
     * @param occpy
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("update Student set occupy = ?2 where stuid = ?1")
    void updateStuOccupyInTeam(String stuid,String occpy);

    /**
     * 更新锁定状态
     * @param stuid
     * @param lock
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("update Student set locked = ?2 where stuid = ?1")
    void updateStuLock(String stuid,String lock);

    /**
     * 查询不包括自身的不再队伍中的成员
     * @param stuid
     * @param occupy
     * @return
     */
    @Query("from Student where stuid != ?1 and occupy = ?2")
    List<Student> findAllByNotStuidAAndOccupy(String stuid, String occupy);
}
