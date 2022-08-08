package com.works.days5.repository;

import com.works.days5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByNameContainsIgnoreCaseOrSurnameContainsIgnoreCase(String name, String surname);


    Optional<User> findByEmailEqualsAndPasswordEquals(String email,String password);

    int countAllBy();
}