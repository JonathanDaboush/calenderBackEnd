package com.example.calenderBackEnd.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value =  " SELECT *  "
            +  " FROM user  "
            +  " WHERE email = :email and password=:password "
            , nativeQuery = true)
    List<User> findUserByEmailPassword(String email,String password);
    @Query(value =  " SELECT *  "
            +  " FROM user  "
            +  " WHERE email = :email "
            , nativeQuery = true)
    List<User> findUserByEmail(String email);
}
