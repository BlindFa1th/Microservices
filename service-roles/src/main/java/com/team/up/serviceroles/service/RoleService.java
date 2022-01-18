package com.team.up.serviceroles.service;

import com.team.up.serviceroles.repo.RoleRepository;
import com.team.up.serviceroles.repo.model.Role;
import org.springframework.stereotype.Service;

import com.team.up.serviceroles.exceptions.RoleNotFoundException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    private RestTemplate restTemplate;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
    public Role saveRole(Role newRole){
        return roleRepository.save(newRole);
    }
    public Role getRolesById(Long id){
        Optional<Role> roles = roleRepository.findById(id);
        if(roles.isPresent()){
            return roles.get();
        }
        throw new RoleNotFoundException();
    }

    public Role updateRolesById(Long id, Role updatedRole) {
        Optional<Role> roles = roleRepository.findById(id);
        if(roles.isPresent()){
            Role oldRole = roles.get();
            updateRoles(oldRole, updatedRole);
            return roleRepository.save(oldRole);
        }
        throw new RoleNotFoundException();
    }

    private void updateRoles(Role oldRole, Role updatedRole) {
        oldRole.setName(updatedRole.getName());
    }

    public String deleteRolesById(Long id) {
        roleRepository.deleteById(id);
        return "Role was successfully deleted!";
    }
}
