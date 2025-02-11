package com.service;

import java.util.List;

import com.dto.TeamDTO;

public interface TeamService {
    
        public TeamDTO saveTeam(TeamDTO team);
        public List<TeamDTO> getAllTeam();
        public TeamDTO getTeam(Long id);
        public List<TeamDTO> getTeamsByName(String name);
        public TeamDTO updateTeam(Long  id,TeamDTO team);
        public void deleteTeam(Long id);
}
