package com.ddev.svalka.repositories;

import com.ddev.svalka.entities.Gender;
import com.ddev.svalka.entities.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface GenderPreferenceRepository extends JpaRepository<Relationship,Long> {
    @Query("select g FROM Gender g WHERE lower(g.genderType) = :gender")
    Set<Gender> findGenderPreferenceByName(String gender);
}
