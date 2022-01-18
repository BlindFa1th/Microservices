package com.team.up.serviceroles.api;

import com.team.up.serviceroles.repo.model.Role;
import com.team.up.serviceroles.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<List<Role>> getRole(){
        return ResponseEntity.ok(roleService.getRoles());
    }

    @PostMapping(value = "/roles")
    public ResponseEntity<Role> postRole(@RequestBody Role newRole){
        return ResponseEntity.ok(roleService.saveRole(newRole));
    }

    @GetMapping(value = "/roles/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id){
        try {
            return ResponseEntity.ok(roleService.getRolesById(id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value = "/roles/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role updatedRole){
        try {
            return ResponseEntity.ok(roleService.updateRolesById(id, updatedRole));
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/roles/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id){
        return ResponseEntity.ok(roleService.deleteRolesById(id));
    }
}