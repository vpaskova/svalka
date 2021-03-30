package com.ddev.svalka.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="age")
    private Integer age;

    @Email
    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="city")
    private String city;

    @Column(name="description")
    private String description;

    @Column(name="moto")
    private String moto;


    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @ManyToOne
    @JoinColumn(name="zodiac_sign_id", nullable=false)
    private ZodiacSign zodiacSign;

    @ManyToOne
    @JoinColumn(name="gender_id", nullable=false)
    private Gender gender;
//
//    @OneToMany(mappedBy="user")
//    private Set<Image> images=new HashSet<>();

    @ManyToMany
    @JoinTable(name="user_gender_preference",
    joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name="gender_id"))
    private Set<Gender> genderPreference=new HashSet<Gender>();

    @ManyToMany
    @JoinTable(name="user_relationship_preference",
    joinColumns  = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name="relationship_id"))
    private Set<Relationship> relationshipPreference=new HashSet<Relationship>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(Long id, String firstName, String lastName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(Long id, String firstName, String lastName, Integer age, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
    }

    public User(String firstName, String lastName, Integer age, @Email String email, String phoneNumber, String city, String description, String moto, ZodiacSign zodiacSign, Gender gender, Set<Relationship> relationshipPreference) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.description = description;
        this.moto = moto;
        this.zodiacSign = zodiacSign;
        this.gender = gender;
        this.relationshipPreference = relationshipPreference;
    }

    public User(Long id, String firstName, String lastName, Integer age, String mail, String city, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = mail;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public User(String city, Gender gender, Set<Relationship> relationshipPreference) {
        this.city = city;
        this.gender = gender;
        this.relationshipPreference = relationshipPreference;
    }

    public User() {
    }

    public User(String username, String email, String encode) {

        this.username=username;
        this.email=email;
        this.password=encode;
    }

    public User(Long id, String firstName, String lastName, Integer age, String email, String moto, String city, String phone, String description) {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.email=email;
        this.moto=moto;
        this.city=city;
        this.phoneNumber=phone;
        this.description=description;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {return email;}

    public void setEmail(String mail) {this.email = mail;}

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public String getMoto() {return moto;}

    public void setMoto(String moto) {this.moto = moto;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public ZodiacSign getZodiacSign() {return zodiacSign;}

    public void setZodiacSign(ZodiacSign zodiacSign) {this.zodiacSign = zodiacSign;}

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<Gender> getGenderPreference() {
        return genderPreference;
    }

    public void setGenderPreference(Set<Gender> genderPreference) {
        this.genderPreference = genderPreference;
    }

    public Set<Relationship> getRelationshipPreference() {
        return relationshipPreference;
    }

    public void setRelationshipPreference(Set<Relationship> relationshipPreference) {
        this.relationshipPreference = relationshipPreference;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
//    public Set<Image> getImages() {
//        return images;
//    }
//
//    public void setImages(Set<Image> images) {
//        this.images = images;
//    }
}
