package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Users")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserExtra userExtra;

    @OneToMany(mappedBy = "user")
    private List<Hobby> hobbies;

    @OneToMany(mappedBy = "user")
    private List<Sport> sports;

    @OneToMany(mappedBy = "user")
    private List<Society> societies;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "profile_picture")
    private byte[] profilePicture; // Change data type to byte[]

    public User() {
        super();
    }

    public User(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and setters for other fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    // public byte[] getProfilePicture() {
    //     return profilePicture;
    // }

    // public void setProfilePicture(String fileName) {
    //     this.profilePicture = fileName;
    // }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
    }

    public boolean isPresent() {
        return false;
    }
}






// package com.example.demo.model;

// import java.io.Serializable;
// import java.util.Date;
// import java.util.Set;

// import javax.persistence.CascadeType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.EntityListeners;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.ManyToMany;
// import javax.persistence.OneToOne;
// import javax.persistence.Table;
// import javax.persistence.Temporal;
// import javax.persistence.TemporalType;
// import javax.validation.constraints.NotBlank;

// import org.springframework.data.annotation.CreatedDate;
// import org.springframework.data.annotation.LastModifiedDate;
// import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// @Entity
// @Table(name = "Users")
// @EntityListeners(AuditingEntityListener.class)
// public class User implements Serializable {
//     private static final long serialVersionUID = 1L;

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//     // private UserExtra userExtra;

//     @NotBlank
//     private String name;

//     @NotBlank
//     @Column(unique = true)
//     private String email;

//     @NotBlank
//     private String password;

//     @Column(nullable = false, updatable = false)
//     @Temporal(TemporalType.TIMESTAMP)
//     @CreatedDate
//     private Date createdAt;

//     @Column(nullable = false)
//     @Temporal(TemporalType.TIMESTAMP)
//     @LastModifiedDate
//     private Date updatedAt;

//     @Column(name = "profile_picture")
//     private byte[] profilePicture; // Change data type to byte[]

//     public User() {
//         super();
//     }

//     public User(String name, String email, String password) {
//         super();
//         this.name = name;
//         this.email = email;
//         this.password = password;
//     }

//     // Getters and setters for other fields

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

    

//     @Override
//     public String toString() {
//         return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
//     }

//     public boolean isPresent() {
//         return false;
//     }
// }
