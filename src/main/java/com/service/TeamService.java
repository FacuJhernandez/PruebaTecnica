package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.TeamDTO;
import com.exception.NoExisteEquipoException;
import com.model.Team;
import com.repository.TeamRepository;


@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;


    public TeamDTO saveTeam(TeamDTO team) {
        Team response = teamRepository.save(team.toEntity());

        return TeamDTO.teamToTeamDTO(response);
    }


    public List<TeamDTO> getAllTeam() {
        List<Team> teams = (List<Team>) teamRepository.findAll();
        List<TeamDTO> response= new ArrayList<TeamDTO>();

        for(Team  team: teams){
            response.add(TeamDTO.teamToTeamDTO(team));
        }
        return response;

    }

    public TeamDTO getTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new NoExisteEquipoException("Equipo no encontrado"));

        return TeamDTO.teamToTeamDTO(team);
    }


    public List<TeamDTO> getTeamsByName(String name) {
        List<Team> teams= teamRepository.getTeamByName(name);
        List<TeamDTO> response= new ArrayList<TeamDTO>();

        for(Team team:teams){
            response.add(TeamDTO.teamToTeamDTO(team));
        }

        return response;
    }


    public TeamDTO updateTeam(Long  id,TeamDTO team) {
        Team updateTeam= teamRepository.findById(id).orElseThrow(() -> new NoExisteEquipoException("Equipo no encontrado"));

        updateTeam.setName(team.getName());
        updateTeam.setCountry(team.getCountry());
        updateTeam.setLeague(team.getLeague());
        teamRepository.save(updateTeam);

       return   TeamDTO.teamToTeamDTO(updateTeam);
    }

    public void deleteTeam(Long id){
        teamRepository.delete(teamRepository.findById(id).orElseThrow(()-> new NoExisteEquipoException("Equipo no encontrado")));

    }

}
