package com.example.demo.dto;

import java.util.List;

public class UserExtraDTO {
    private List<String> course;
    private List<String> hobbies;
    private List<String> societies;
    private List<String> sports;
    private List<String> ethnicity;

    public UserExtraDTO() {
        // Default constructor
    }

    public UserExtraDTO(List<String> course, List<String> hobbies, List<String> societies, List<String> sports, List<String> ethnicity) {
        this.course = course;
        this.hobbies = hobbies;
        this.societies = societies;
        this.sports = sports;
        this.ethnicity = ethnicity;
    }

    public List<String> getCourse() {
        return course;
    }

    public void setCourse(List<String> course) {
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

    public List<String> getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(List<String> ethnicity) {
        this.ethnicity = ethnicity;
    }
}

















// package com.example.demo.dto;

// import java.util.List;

// public class UserExtraDTO {
//     private String course;
//     private String hobbies;
//     private String societies;
//     private String sports;
//     private String ethnicity;

//     public UserExtraDTO() {
//         // Default constructor
//     }

//     public UserExtraDTO(String course, String hobbies, String societies, String sports, String ethnicity) {
//         this.course = course;
//         this.hobbies = hobbies;
//         this.societies = societies;
//         this.sports = sports;
//         this.ethnicity = ethnicity;
//     }

//     public String getCourse() {
//         return course;
//     }

//     public void setCourse(String course) {
//         this.course = course;
//     }

//     public String getHobbies() {
//         return hobbies;
//     }

//     public void setHobbies(String hobbies) {
//         this.hobbies = hobbies;
//     }

//     public String getSocieties() {
//         return societies;
//     }

//     public void setSocieties(String societies) {
//         this.societies = societies;
//     }

//     public String getSports() {
//         return sports;
//     }

//     public void setSports(String sports) {
//         this.sports = sports;
//     }

//     public String getEthnicity() {
//         return ethnicity;
//     }

//     public void setEthnicity(String ethnicity) {
//         this.ethnicity = ethnicity;
//     }
// }
