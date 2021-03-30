package com.ddev.svalka.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="relationship")
public class Relationship {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="relationship_type")
    private String relationshipType;

    @ManyToMany
    (mappedBy = "relationshipPreference")
    @JsonIgnore
    private Set<User> userSet;

    public Relationship() {
    }

    public Relationship(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String name) {
        this.relationshipType = name;
    }



}
