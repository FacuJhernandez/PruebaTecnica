package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.TeamDTO;

import com.exception.RequestInvalidaException;
import com.service.impl.TeamServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class TeamController {
    
    @Autowired
    TeamServiceImpl teamService;

    @PostMapping("/team")
    @Operation(description = "Crea a un nuevo equipo")
    public ResponseEntity<TeamDTO> saveTeam( @RequestBody TeamDTO team){
        if(team.getName().isBlank() || team.getCountry().isBlank() || team.getLeague().isBlank()){
            throw new RequestInvalidaException("la solicitud es invalida");
        }

        return new ResponseEntity<>(teamService.saveTeam(team),HttpStatus.CREATED);
    }

    @GetMapping("/teams")
    @Operation(description = "Devuelve todos los equipos")
    public ResponseEntity<List<TeamDTO>> getTeams(){
        return new ResponseEntity<>(teamService.getAllTeam(),HttpStatus.OK);
    }

    @GetMapping("/teams/{id}")
    @Operation(description = "Devuelve los detalles de un equipo específico dado su identificador único")
    public ResponseEntity<TeamDTO> getTeam(@PathVariable Long id){
        return new ResponseEntity<>(teamService.getTeam(id),HttpStatus.OK);
    }


    @GetMapping("/teams/search")
    @Operation(description = "Devuelve los detalles de un equipo específic que contiene el nombre ingresado")
    public ResponseEntity <List<TeamDTO>> getTeamByName(@RequestParam("name") String name){
        return new ResponseEntity<>(teamService.getTeamsByName(name),HttpStatus.OK);
    }

    @PutMapping("/team/{id}")
    @Operation(description = "Modifica a un equipo especifico dado un id")
    public ResponseEntity <TeamDTO> updateTeam(@PathVariable Long id ,@RequestBody TeamDTO team){
        return new ResponseEntity<>(teamService.updateTeam(id,team),HttpStatus.OK);
    }

    @DeleteMapping("/team/{id}")
    @Operation(description = "Elimina al equipo que tiene el id enviado")
    public ResponseEntity <TeamDTO> deleteTeam(@PathVariable Long id ){
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
