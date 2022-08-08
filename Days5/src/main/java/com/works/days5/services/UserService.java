package com.works.days5.services;

import com.works.days5.entities.User;
import com.works.days5.repository.UserRepository;
import com.works.days5.utils.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    final UserRepository uRepo;

    public UserService(UserRepository uRepo) {
        this.uRepo = uRepo;
    }
    public ResponseEntity save(User user){
        Map<String ,Object> hm=new LinkedHashMap<>();
        String newPass= Util.md5(user.getPassword());
        user.setPassword(newPass);
        uRepo.save(user);
        hm.put("status",true);
        hm.put("result",user);

        return new ResponseEntity(hm, HttpStatus.OK);
    }
    public ResponseEntity login(User user){
        Map<String ,Object> hm=new LinkedHashMap<>();
        String newPass=Util.md5(user.getPassword());
        Optional<User> oUser=uRepo.findByEmailEqualsAndPasswordEquals(user.getEmail(),newPass);
        if (oUser.isPresent()){
            hm.put("status",true);
            hm.put("message","Login success");
            User u=oUser.get();
            u.setPassword("Secur");
            hm.put("result",u);
        }else{
            hm.put("status",false);
            hm.put("message","Email or Password Fail");

        }

        return new ResponseEntity(hm,HttpStatus.OK);

    }
    public ResponseEntity search(String q){
        Map<String ,Object> hm=new LinkedHashMap<>();
        List<User> ls=uRepo.findByNameContainsIgnoreCaseOrSurnameContainsIgnoreCase(q,q);
        hm.put("status",true);
        hm.put("total",uRepo.countAllBy());
        hm.put("searhtotal",ls.size());
        hm.put("users",ls);




        return new ResponseEntity(hm,HttpStatus.OK);

    }
}
