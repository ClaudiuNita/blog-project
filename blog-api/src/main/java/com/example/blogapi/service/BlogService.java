package com.example.blogapi.service;

import com.example.blogapi.DTO.UserDTO;
import com.example.blogapi.model.Role;
import com.example.blogapi.model.User;
import com.example.blogapi.model.UserDetails;
import com.example.blogapi.repository.RoleRepository;
import com.example.blogapi.repository.UserDetailsRepository;
import com.example.blogapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BlogService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;

    public List<User> addElementsInUserRoleTable(){

        Optional<User> userOptional = userRepository.findById(1L);
        Optional<Role> roleOptional = roleRepository.findById(1L);

        if(userOptional.isPresent() && roleOptional.isPresent()){

            User user = userOptional.get();
            Role role = roleOptional.get();

            role.addUser(user);

            roleRepository.save(role);
        }

        return roleOptional.get().getUsers();
    }

    public List<UserDTO> getAllUsers(){

        List<User> users = new ArrayList<>(userRepository.findAll());
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user:users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmailAddress());
            userDTO.setPassword(user.getPassword());

            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

//    public UserDTO getUserById(Long id) {
//
//        try {
//            User user = userRepository.findById(id).get();
//            return new UserDTO(user.getId(), user.getEmailAddress(), user.getPassword());
//        } catch (NoSuchElementException e){
//            log.error("" + e);
//        }
//
//        return null;
//    }


    public UserDTO getUserById(Long id){

            User user = userRepository.findById(id).get();
            System.out.println("ajunge aici");
            return new UserDTO(user.getId(), user.getEmailAddress(), user.getPassword());
    }

    public void addUser(String email){

        userRepository.save(new User(email));
    }

    public User updateUserById(Long id, String email){

        User user = userRepository.getById(id);
        user.setEmailAddress(email);
        return userRepository.saveAndFlush(user);
    }

    @Resource
    private UserDetailsRepository userDetailsRepository;

    public void deleteUserById(Long id){

        for (Long i = 1L; i<= userDetailsRepository.count(); i++){
            UserDetails userDetails = userDetailsRepository.getById(i);

            if(userDetails.getUser().getId() == id){
                userDetailsRepository.deleteById(id);
                break;
            }
        }

        userRepository.deleteById(id);
    }
}
