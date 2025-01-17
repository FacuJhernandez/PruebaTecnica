package com.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.dto.TeamDTO;
import com.exception.NoExisteEquipoException;
import com.model.Team;
import com.repository.TeamRepository;

@SpringBootTest
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @MockitoBean
    private TeamRepository teamRepository;



    @BeforeEach
    void setUp (){
        Long id_team=  (long) 1;
        Team team = Team.builder()
                    .id(id_team)
                    .name("San Lorenzo")
                    .country("Argentina")
                    .league("bonaerense")
                    .build();
        Mockito.when(teamRepository.findById(id_team)).thenReturn(Optional.of(team));
        
    }

    @Test
    public void getTeamByIdTest(){
    
        TeamDTO team= teamService.getTeam(1l);
        assertEquals(team.getName(), "San Lorenzo");
        assertEquals(team.getLeague(), "bonaerense");
        assertEquals(team.getCountry(), "Argentina");

    }
    @Test
    public void failedGetTeamByIdTest(){
    
        
        Mockito.when(teamRepository.findById(4l)).thenReturn(Optional.empty());

        NoExisteEquipoException exception = assertThrows(NoExisteEquipoException.class,()->{
            teamService.getTeam(4l);
        });

        assertEquals("Equipo no encontrado", exception.getMessage());

    }

    @Test
    public void postTeam(){
        TeamDTO teamDto=TeamDTO.builder()                    
                .name("River")
                .country("Argentina")
                .league("campeonato")
                .build();

        Team team= new Team(1l,"River","Argentina","campeonato");
        
        Mockito.when(teamRepository.save(any(Team.class))).thenReturn(team);
        
        TeamDTO result = teamService.saveTeam(teamDto);
        assertEquals("River", result.getName());
    }

    @Test
    public void updateTeamTest(){
        TeamDTO teamDto=TeamDTO.builder()                    
                .name("Boca")
                .country("Argentina")
                .league("campeonato")
                .build();

        Team team= new Team(6l,"River","Argentina","campeonato");
        
        Mockito.when(teamRepository.findById(6l)).thenReturn(Optional.of(team));
        
        TeamDTO result = teamService.updateTeam(6l,teamDto);
        assertEquals("Boca", result.getName());

    }

    @Test
    public void ErrorUpdateTeamTest(){
        TeamDTO teamDto=TeamDTO.builder()                    
                .name("Boca")
                .country("Argentina")
                .league("campeonato")
                .build();

        Mockito.when(teamRepository.findById(4l)).thenReturn(Optional.empty());
        NoExisteEquipoException exception = assertThrows(NoExisteEquipoException.class,()->{
                    teamService.updateTeam(4l, teamDto);
        });
        assertEquals("Equipo no encontrado", exception.getMessage());

    }

    @Test
    public void deleteTeamTest(){
        Mockito.when(teamRepository.existsById(1l)).thenReturn(true);
        
        teamService.deleteTeam(1l);

        Mockito.verify(teamRepository).deleteById(1l);

    }

    @Test
    public void ErrorDeleteTeamTest(){
       
        Mockito.when(teamRepository.existsById(4l)).thenReturn(false);
        NoExisteEquipoException exception = assertThrows(NoExisteEquipoException.class,()->{
                    teamService.deleteTeam(4l);
        });
        assertEquals("Equipo no encontrado", exception.getMessage());

    }    

    @Test
    public void getAllTeamTest(){
        Team team1= new Team(1l, "River", "korea", "Argentina");
        Team team2= new Team(1l, "Boca", "Lisboa", "Argentina");

        Mockito.when(teamRepository.findAll()).thenReturn(List.of(team1,team2));

        List<Team> response= teamRepository.findAll();

        assertEquals(response.get(0).getName(), "River");
        assertEquals(response.get(1).getName(), "Boca");



    }


}
