
package com.example.demo.dto;

import java.util.List;

public class RegistrationDTO {
    private String name;
    private String email;
    private String password;
    private String course;
    private List<String> hobbies;
    private List<String> societies;
    private List<String> sports;
    private String ethnicity;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String name, String email, String password, String course, List<String> hobbies, List<String> societies, List<String> sports, String ethnicity) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.course = course;
        this.hobbies = hobbies;
        this.societies = societies;
        this.sports = sports;
        this.ethnicity = ethnicity;
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

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<String> getSocieties() {
        return societies;
    }

    public void setSocieties(List<String> societies) {
        this.societies = societies;
    }

    public List<String> getSports() {
        return sports;
    }

    public void setSports(List<String> sports) {
        this.sports = sports;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }
}