package com.team.up.serviceteams.api;

import com.team.up.serviceteams.repo.model.Team;
import com.team.up.serviceteams.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(value = "/teams")
    public ResponseEntity<List<Team>> getTeam(){
        return ResponseEntity.ok(teamService.getTeams());
    }

    @PostMapping(value = "/teams")
    public ResponseEntity<Team> postTeam(@RequestBody Team newTeam){
        return ResponseEntity.ok(teamService.saveTeam(newTeam));
    }

    @GetMapping(value = "/teams/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable Long id){
        try{
            return ResponseEntity.ok(teamService.getTeamsById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping(value = "/teams/users/{team_id}")
    public ResponseEntity getTeamOwner(@PathVariable Long team_id){
        try{
            return ResponseEntity.ok(teamService.getTeamOwner(team_id));
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value = "/teams/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team updatedTeam){
        try{
            return ResponseEntity.ok(teamService.updateTeamsById(id, updatedTeam));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/teams/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id){
        return ResponseEntity.ok(teamService.deleteTeamsById(id));
    }
}
