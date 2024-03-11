
package com.example.demo.model;

import javax.persistence.*;



@Entity
@Table(name = "user_extra")
public class UserExtra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "course")
    private String course;

    @Column(name = "ethnicity")
    private String ethnicity;

    public UserExtra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}

// package com.example.demo.model;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;

// @Entity
// @Table(name = "user_extra")
// public class UserExtra {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "user_id", nullable = false)
//     private User user;

//     @Column(name = "course")
//     private String course;

//     @Column(name = "hobbies")
//     private String hobbies;

//     @Column(name = "societies")
//     private String societies;

//     @Column(name = "sports")
//     private String sports;

//     @Column(name = "ethnicity")
//     private String ethnicity;

//     public UserExtra() {
//     }

//     public UserExtra(User user, String course, String hobbies, String societies, String sports, String ethnicity) {
//         this.user = user;
//         this.course = course;
//         this.hobbies = hobbies;
//         this.societies = societies;
//         this.sports = sports;
//         this.ethnicity = ethnicity;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public User getUser() {
//         return user;
//     }

//     public void setUser(User user) {
//         this.user = user;
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

