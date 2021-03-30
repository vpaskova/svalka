package com.ddev.svalka.controlers;

import com.ddev.svalka.entities.User;
import com.ddev.svalka.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.*;

@CrossOrigin(origins = "*" , maxAge = 3600)
@RestController
@RequestMapping("/app")
public class UsersController{

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    RelationshipRepository relationshipRepository;

    @Autowired
    ZodiacSignRepository zodiacSignRepository;
    @Autowired
    GenderPreferenceRepository genderPreferenceRepository;


    @GetMapping(value="/user")
//    @PreAuthorize("hasRole('USER')")
    public List<User> getUsers(){
     return  usersRepository.findAll();
    }


    @GetMapping("/search/id")
//    @PreAuthorize("hasRole('USER')")
    public Optional<User> getUserById(@RequestParam(required = false)  Long id){
        return usersRepository.findById(id==null? 2:id);
    }


    @GetMapping("/search/firstname")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUserByFirstName(@RequestParam(required = false) String firstName){
       if(firstName == null || firstName.isBlank())
        return ResponseEntity.ok("User with that name doesn't exist");

       return ResponseEntity.ok(usersRepository.findUsersByFirstName(firstName.toLowerCase()));
    }


    @GetMapping("/search/pages")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> paginateUsers
            (@RequestParam(value = "currentPage",defaultValue ="1") int currentPage,
            @RequestParam(value = "perPage",defaultValue ="8") int perPage,
             @RequestParam(required = false) String city,
             @RequestParam(required = false) String gender,
             @RequestParam(required = false) String relationship) {

        Pageable pageable = PageRequest.of(currentPage -1,perPage);
//        if (city!=null)  {
//            city = city.toLowerCase();
//        } else {
//            city = "";
//        }
//        if (gender!=null)  {
//            gender = gender.toLowerCase();
//        } else {
//            gender="";
//        }
//        if (relationship!=null)  {
//            relationship = relationship.toLowerCase();
//        }else {
//            relationship="";
//        }

        Page<User> users=usersRepository.findPageUsersByCityGenderAge
                (pageable, city.toLowerCase(),gender.toLowerCase(),relationship.toLowerCase());

        Map<String,Object> response = new HashMap<>();
        response.put("users",users.getContent());
        response.put("currentPage", users.getNumber());
        response.put("totalItems", users.getTotalElements());
        response.put("totalPages", users.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }



    @DeleteMapping("/delete")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteUser(@RequestParam Long id){
        if(id==null) return ResponseEntity.badRequest().body("Не сте подали ID!");

        usersRepository.deleteById(id);
        return ResponseEntity.ok("Изтрит успешно!");
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveOrUpdate(@RequestParam(required = false) Long id,
                                          @RequestParam(required = false) String firstName,
                                          @RequestParam(required=false) String lastName,
                                          @RequestParam(required = false) Integer age,
                                          @RequestParam(required = false) String email,
                                          @RequestParam(required = false) String moto,
                                          @RequestParam(required = false) String city,
                                          @RequestParam(required = false) String phone,
                                          @RequestParam(required = false) String gender,
                                          @RequestParam(required = false) String genderPreference,
                                          @RequestParam(required = false) String zodiacSign,
                                          @RequestParam(required = false) String relationshipPreference,
                                          @RequestParam(required = false) String description) {

        User user = usersRepository.findUsersById(id);

        if (firstName!=null) {
            user.setFirstName(firstName);
        }
        if (lastName!=null) {
            user.setLastName(lastName);
        }
        if (age!=null) {
            user.setAge(age);
        }
        if (email!=null) {
            user.setEmail(email);
        }
        if (moto!=null) {
            user.setMoto(moto);
        }
        if (city!=null) {
            user.setCity(city);
        }
        if (phone!=null) {
            user.setPhoneNumber(phone);
        }
        if (description!=null) {
            user.setDescription(description);
        }
        if(zodiacSign!=null){
            user.setZodiacSign(zodiacSignRepository.findZodiacSignByName(zodiacSign.toLowerCase()));
        }
        if(gender!=null){
            user.setGender(genderRepository.findGenderByName(gender.toLowerCase()));
        }
        if(genderPreference!=null){
            user.setGenderPreference(genderPreferenceRepository.findGenderPreferenceByName(genderPreference.toLowerCase()));
        }
        if(relationshipPreference!=null){
            user.setRelationshipPreference(relationshipRepository.findRelationshipByName(relationshipPreference.toLowerCase()));
        }

        user=usersRepository.save(user);

        Map<String,Object> response=new HashMap<>();
        response.put("userId", user.getId());
        response.put("message","Успешно записан!");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
