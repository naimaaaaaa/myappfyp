package com.example.demo.dto;

import com.example.demo.model.Hobby;
import com.example.demo.model.Society;
import com.example.demo.model.Sport;
import com.example.demo.model.User;
import com.example.demo.model.UserExtra;

import java.util.List;


public class UserInfoDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String course;
    private String ethnicity;
    private List<Hobby> hobbies;
    private List<Sport> sports;
    private List<Society> societies;

    public UserInfoDTO() {
    }

    public UserInfoDTO(User user, List<Hobby> hobbies, List<Sport> sports,
            List<Society> societies, UserExtra userExtra) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        if (userExtra != null) {
            this.course = userExtra.getCourse();
            this.ethnicity = userExtra.getEthnicity();
        }
        this.hobbies = hobbies;
        this.societies = societies;
        this.sports = sports;
    }

    // private List<Object> formatHobbies(List<Hobby> hobbies) {
    // return hobbies.stream()
    // .map(hobby -> "{id:" + hobby.getId() + ", name:'" + hobby.getName() + "'}")
    // .collect(Collectors.toList());
    // }

    // private List<Object> formatSports(List<Sport> sports) {
    // return sports.stream()
    // .map(sport -> "{id:" + sport.getId() + ", name:'" + sport.getName() + "'}")
    // .collect(Collectors.toList());
    // }

    // private List<Object> formatSocieties(List<Society> societies) {
    // return societies.stream()
    // .map(society -> society.getId(), society.getName())
    // .collect(Collectors.toList());
    // }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public List<Sport> getSports() {
        return sports;
    }

    public List<Society> getSocieties() {
        return societies;
    }
}
