package com.ddev.svalka.repositories;

import com.ddev.svalka.entities.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RelationshipRepository extends JpaRepository<Relationship,Long> {
    @Query("select r FROM Relationship r WHERE lower(r.relationshipType) = :relationship")
    Set<Relationship> findRelationshipByName(String relationship);
}
