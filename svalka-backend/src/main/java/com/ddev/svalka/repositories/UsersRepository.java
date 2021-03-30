package com.ddev.svalka.repositories;

import com.ddev.svalka.entities.Gender;
import com.ddev.svalka.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface UsersRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE lower(u.firstName)= :firstName")
    User findUsersByFirstName(String firstName);

//    @Query("SELECT u FROM User u JOIN u.gender g JOIN u.relationshipPreference r WHERE lower(u.city)" +
//    "like (concat(:city, '%'))"+
//    "and lower(g.genderType) like (concat(:gender,'%')) "+
//    "and  lower(r.relationshipType) like (concat(:relationshipType,'%')) ")
//    Page<User> findPageUsersByCityGenderAge(Pageable page, String city, String gender, String relationshipType);

    @Query("SELECT u FROM User u JOIN u.gender g JOIN u.relationshipPreference r WHERE lower(u.city)" +
            "like (concat(:city, '%'))"+
            "and lower(g.genderType) like lower(concat(:gender,'%')) "+
            "and  lower(r.relationshipType) like lower(concat(:relationshipType,'%')) ")
    Page<User> findPageUsersByCityGenderAge(Pageable page, String city, String gender, String relationshipType);


    @Query("SELECT u FROM User u WHERE (u.id)= :id")
    User findUsersById(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT * from User", nativeQuery = true)
    List<User> getUsers();
    @Query
    List<User> findByGender(Gender gender);

    @Query("SELECT u FROM User u WHERE lower(u.firstName) like lower(concat(:name, '%'))")
    Page<User> findPageUsers(Pageable pageable, String name);

    Optional<User> findByUsername(String username);


}

