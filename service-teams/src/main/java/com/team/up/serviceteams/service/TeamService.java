package com.team.up.serviceteams.service;

import com.team.up.serviceteams.repo.model.Userclass.User;
import com.team.up.serviceteams.exceptions.TeamNotFoundException;
import com.team.up.serviceteams.repo.TeamRepository;
import com.team.up.serviceteams.repo.model.Team;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    private RestTemplate restTemplate;

    public List<Team> getTeams(){
        return teamRepository.findAll();
    }

    public Team saveTeam(Team newTeam){
        return teamRepository.save(newTeam);
    }

    public Team getTeamsById(Long id){
        Optional<Team> teams = teamRepository.findById(id);
        if(teams.isPresent()){
            return teams.get();
        }
        throw new TeamNotFoundException();
    }

    public Team updateTeamsById(Long id, Team updatedTeam) {
        Optional<Team> teams = teamRepository.findById(id);
        if(teams.isPresent()){
            Team oldTeam = teams.get();
            updateTeams(oldTeam, updatedTeam);
            return teamRepository.save(oldTeam);
        }
        throw new TeamNotFoundException();
    }

    public String getTeamOwner(Long team_id) {

        Team team = getTeamsById(team_id);
        long user_id = team.getOwner();
        User user = restTemplate.getForObject(
                "http://users:8080/users/" + user_id ,
                User.class
        );
        return user.getNickname();
    }
    private void updateTeams(Team oldTeam, Team updatedTeam) {
        oldTeam.setName(updatedTeam.getName());
        oldTeam.setOwner(updatedTeam.getOwner());
    }

    public String deleteTeamsById(Long id) {
        teamRepository.deleteById(id);
        return "Team was successfully deleted!";
    }
}

