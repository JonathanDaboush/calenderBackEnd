package com.example.calenderBackEnd.User;

import com.example.calenderBackEnd.Util.JasyptConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserServices userServices;
    @Autowired
    JasyptConfig jasyptConfig;
    @GetMapping
    public List<User> getusers() {

        List<User> users=userServices.getusers();

        for(int i=0;i< users.size();i++){
            User user=users.get(i);
            user.setEmail(jasyptConfig.encryptor().decrypt(user.getEmail()));
            user.setPassword(jasyptConfig.encryptor().decrypt(user.getPassword()));
            users.set(i,user);
        }

        return users;
    }

    @GetMapping("getById/{id}")
    public User getuser(@PathVariable Long id) throws  IOException {
        User user=userServices.getuserById(id);
        user.setEmail(jasyptConfig.encryptor().decrypt(user.getEmail()));
        user.setPassword(jasyptConfig.encryptor().decrypt(user.getPassword()));
        return user;
    }
    @GetMapping("/{email}/{password}")
    public User getuser(@PathVariable String email,@PathVariable String password) throws  IOException {
        User user=userServices.getusersByEmailPassword(jasyptConfig.encryptor().encrypt(email),jasyptConfig.encryptor().encrypt(password)).get(0);
        user.setEmail(jasyptConfig.encryptor().decrypt(user.getEmail()));
        user.setPassword(jasyptConfig.encryptor().decrypt(user.getPassword()));
        return user;
    }
    @GetMapping("getByEmail/{email}")
    public User getuser(@PathVariable String email) throws  IOException {
        try{
            User user=userServices.getusersByEmail(jasyptConfig.encryptor().encrypt(email)).get(0);
            user.setEmail(jasyptConfig.encryptor().decrypt(user.getEmail()));
            user.setPassword(jasyptConfig.encryptor().decrypt(user.getPassword()));
            return user;
        }
        catch(Exception e){
            return null;
        }

    }
    @PostMapping
    public ResponseEntity createuser(@RequestBody Map<Object, Object> payLoad) throws URISyntaxException {
        User user=new User();
        try{
           user=userServices.getuserById((long)payLoad.get("id"));
        }
        catch(Exception e){

        }

        user.setEmail(jasyptConfig.encryptor().encrypt((String)payLoad.get("email")));
        user.setPassword(jasyptConfig.encryptor().encrypt((String)payLoad.get("password")));
        userServices.saveuser(user);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletefood(@PathVariable Long id) {
        userServices.removeuser(id);
        return ResponseEntity.ok().build();
    }
}
