package com.example.calenderBackEnd.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServices {
@Autowired
    UserRepository userRepository;
    public User getuserById(long id){
        return userRepository.getReferenceById(id);
    }
    public void saveuser(User user){
        userRepository.save(user);
    }
    public void removeuser(long id){
        userRepository.deleteById(id);
    }
    public List<User> getusers(){
        return userRepository.findAll();
    }
    public List<User> getusersByEmailPassword(String email,String password){
        return userRepository.findUserByEmailPassword(email,password);
    }
    public List<User> getusersByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
