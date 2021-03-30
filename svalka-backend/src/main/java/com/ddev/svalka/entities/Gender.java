package com.ddev.svalka.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="gender")
public class Gender {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="gender_type")
    private String genderType;

    @ManyToMany(mappedBy ="genderPreference")
    @JsonIgnore
    Set<User> usersPreferenceSet=new HashSet<User>();

    @OneToMany(mappedBy = "gender")
    private Set<User> usersSet=new HashSet<>();

    public Gender() {
    }

    public Gender(String name) {
        this.genderType = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenderType() {
        return genderType;
    }

    public void setGenderType(String name) {
        this.genderType = name;
    }
}
