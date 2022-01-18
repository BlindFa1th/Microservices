package com.team.up.serviceusers.service;

import com.team.up.serviceusers.repo.model.Role;
import com.team.up.serviceusers.exceptions.UserNotFoundException;
import com.team.up.serviceusers.repo.UserRepository;
import com.team.up.serviceusers.repo.model.Userclass.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private RestTemplate restTemplate;


    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public User saveUser(User newUser){
        return userRepository.save(newUser);
    }
    public User getUsersById(Long id){
        Optional<User> users = userRepository.findById(id);
        if(users.isPresent()){
            return users.get();
        }
        throw new UserNotFoundException();
    }

    public String getUserRoles(Long user_id) {

        User user = getUsersById(user_id);
        long role_id = user.getRole_id();
        Role role = restTemplate.getForObject(
                "http://roles:8081/roles/" + role_id ,
                Role.class
        );
        return role.getName();
    }

    public User updateUsersById(Long id, User updatedUser) {
        Optional<User> users = userRepository.findById(id);
        if(users.isPresent()){
            User oldUser = users.get();
            updateUsers(oldUser, updatedUser);
            return userRepository.save(oldUser);
        }
        throw new UserNotFoundException();
    }

    private void updateUsers(User oldUser, User updatedUser) {
        oldUser.setNickname(updatedUser.getNickname());
        oldUser.setPassword(updatedUser.getPassword());
        oldUser.setEmail(updatedUser.getEmail());
        oldUser.setRole_id(updatedUser.getRole_id());
        oldUser.setTeam_id(updatedUser.getTeam_id());
    }

    public String deleteUsersById(Long id) {
        userRepository.deleteById(id);
        return "User was successfully deleted!";
    }
}


