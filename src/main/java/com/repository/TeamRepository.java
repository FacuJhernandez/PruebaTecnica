package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.model.Team;

public interface TeamRepository  extends JpaRepository<Team,Long>{


    @Query("Select e from Team e where Lower(e.name) like Lower(Concat('%',:name,'%'))")
    public List<Team> getTeamByName(@Param("name") String nombre);

    
} 
