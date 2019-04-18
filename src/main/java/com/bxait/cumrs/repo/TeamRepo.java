package com.bxait.cumrs.repo;

import com.bxait.cumrs.entity.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo extends JpaRepository<Team,Long> {

    @Query("from Team where studentOne = ?1")
    Team findTeamByStuOne(String stuOne);

    @Query("from Team where studentOne = ?1 or studentTwo = ?1 or studentThree =?1")
    Team findMyTeam(String stuid);

    @Query("from Team where teamName = ?1")
    Team findTeamByName(String teamName);

    @Query("from Team where teacherId = ?1")
    Team findTeamByTeaid(String teaId);
}
