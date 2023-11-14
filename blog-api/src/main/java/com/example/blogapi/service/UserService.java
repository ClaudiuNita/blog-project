package com.example.blogapi.service;

import com.example.blogapi.DTO.UserDTO;
import com.example.blogapi.model.User;
import com.example.blogapi.model.UserDetails;
import com.example.blogapi.repository.UserDetailsRepository;
import com.example.blogapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserDetailsRepository userDetailsRepository;

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

    public UserDTO getUserById(Long id){

            User user = userRepository.findById(id).get();

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
