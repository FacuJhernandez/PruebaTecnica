package com.dto;

import com.model.Team;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Builder
public class TeamDTO {


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    private String country;
    private String league;



    public Team toEntity(){
        return Team.builder()
        .id(this.id)
        .name(this.name)
        .country(this.country)
        .league(this.league)
        .build();
    }

    public static TeamDTO teamToTeamDTO(Team team){
        return TeamDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .country(team.getCountry())
                .league(team.getLeague())
                .build();

    }


}
