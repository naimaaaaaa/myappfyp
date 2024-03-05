package com.example.demo.dto;

import java.util.List;

public class UserExtraDTO {
    private Long userId;
    private String course;
    private String hobbies;
    private String societies;
    private String sports;
    private String ethnicity;
   // private String email;

   public UserExtraDTO() {
    // Default constructor
}

public UserExtraDTO(Long userId, String course, String hobbies, String societies, String sports, String ethnicity) {
    this.userId = userId;
    this.course = course;
    this.hobbies = hobbies;
    this.societies = societies;
    this.sports = sports;
    this.ethnicity = ethnicity;
}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getSocieties() {
        return societies;
    }

    public void setSocieties(String societies) {
        this.societies = societies;
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
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
//    // private String email;

//     public UserExtraDTO() {
//         // Default constructor
//     }

//     public UserExtraDTO(String course, String hobbies, String societies, String sports, String ethnicity) {//String email
//         this.course = course;
//         this.hobbies = hobbies;
//         this.societies = societies;
//         this.sports = sports;
//         this.ethnicity = ethnicity;
//        // this.email= email;
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

//     // public String getEmail() {
//     //     return email;
//     // }

//     // public void setEmail(String email) {
//     //     this.email = email;
//     // }

// }

















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
