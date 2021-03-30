package com.ddev.svalka.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="zodiac_sign")
public class ZodiacSign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="zodiac_type")
    private String zodiacType;

    @OneToMany(mappedBy="zodiacSign")
    @JsonIgnore
    private Set<User> userSet=new HashSet<User>();

    public ZodiacSign() {
    }

    public ZodiacSign(String name) {
        this.zodiacType = name;
    }

    public ZodiacSign(String name, Set<User> userSet) {
        this.zodiacType = name;
        this.userSet = userSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZodiacType() {
        return zodiacType;
    }

    public void setZodiacType(String zodiacType) {
        this.zodiacType = zodiacType;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
