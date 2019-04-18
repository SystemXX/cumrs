package com.bxait.cumrs.services;

import com.bxait.cumrs.entity.model.Team;
import org.springframework.web.bind.annotation.RequestBody;


public interface TeamService {

    String create(Team team)throws Exception;

    String dissolution(Team team)throws Exception;
}
