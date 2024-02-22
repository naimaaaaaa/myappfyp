//import javax.persistence.*;
package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_extra")
public class UserExtra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String course;

    @Column(columnDefinition = "TEXT")
    private String hobbies;

    @Column(columnDefinition = "TEXT")
    private String societies;

    @Column(columnDefinition = "TEXT")
    private String sports;

    @Column(columnDefinition = "TEXT")
    private String ethnicity;

    public UserExtra() {
    }

    // Getters and setters

    // Convert list attributes to JSON string
    public void setCourse(List<String> course) {
        this.course = convertListToJson(course);
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = convertListToJson(hobbies);
    }

    public void setSocieties(List<String> societies) {
        this.societies = convertListToJson(societies);
    }

    public void setSports(List<String> sports) {
        this.sports = convertListToJson(sports);
    }

    public void setEthnicity(List<String> ethnicity) {
        this.ethnicity = convertListToJson(ethnicity);
    }

    // Convert JSON string to list attributes
    public List<String> getCourse() {
        return convertJsonToList(course);
    }

    public List<String> getHobbies() {
        return convertJsonToList(hobbies);
    }

    public List<String> getSocieties() {
        return convertJsonToList(societies);
    }

    public List<String> getSports() {
        return convertJsonToList(sports);
    }

    public List<String> getEthnicity() {
        return convertJsonToList(ethnicity);
    }

    // Helper method to convert list to JSON string
    private String convertListToJson(List<String> list) {
        // You can implement your own logic to convert list to JSON string
        // Here we are just joining the elements with a comma
        return String.join(",", list);
    }

    // Helper method to convert JSON string to list
    private List<String> convertJsonToList(String json) {
        // You can implement your own logic to convert JSON string to list
        // Here we are just splitting the string by comma
        return List.of(json.split(","));
    }
}







// package com.example.demo.model;

// import java.io.Serializable;
// import java.util.Date;
// import java.util.Set;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.EntityListeners;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToMany;
// import javax.persistence.OneToOne;
// import javax.persistence.Table;
// import javax.persistence.Temporal;
// import javax.persistence.TemporalType;
// import javax.validation.constraints.NotBlank;

// import org.springframework.data.annotation.CreatedDate;
// import org.springframework.data.annotation.LastModifiedDate;
// import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// import com.example.demo.UserType;

// //Let's create a simple User class
// @Entity
// @Table(name = "UserExtra")
// public class UserExtra {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

// 	@OneToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "user_id", referencedColumnName = "id")
//     private User user;


// 	@NotBlank
// 	String course;
	
// 	@NotBlank
// 	//@Column(unique=true)
// 	String hobbies;
	
// 	@NotBlank
// 	String societies;

//     @NotBlank
// 	String sports;

//     @NotBlank
// 	String ethnicity;
	
// 	//UserType userType;
	
// 	// @Column(nullable = false, updatable = false)
// 	// @Temporal(TemporalType.TIMESTAMP)
// 	// @CreatedDate
// 	// private Date createdAt;

// 	// @Column(nullable = false)
// 	// @Temporal(TemporalType.TIMESTAMP)
// 	// @LastModifiedDate
// 	// private Date updatedAt;
	
// 	// @Column(name = "profile_picture")
// 	// private String profilePicture;
	
	
// 	 public UserExtra() {
// 			super();
// 			// TODO Auto-generated constructor stub
// 	}
		
	 
// 	 public UserExtra(Long id, String course, String hobbies, String societies,String sports, String ethnicity ) {
// 		super();
//         this.id=id;
// 		this.course = course;
// 		this.hobbies = hobbies;
// 		this.societies = societies;
//         this.sports = sports;
// 		this.ethnicity = ethnicity;

// 	//	this.userType = userType;
// 	}
	
	
// 	public Long getId() {
// 		return id;
// 	}

// 	public void setId(Long id) {
// 		this.id = id;
// 	}

// 	public String getCourse() {
// 		return course;
// 	}

// 	public void setCourse(String course) {
// 		this.course = course;
// 	}


// 	public String getHobbies() {
// 		return hobbies;
// 	}


// 	public void setHobbies(String hobbies) {
// 		this.hobbies = hobbies;
// 	}


// 	public String getSocieties() {
// 		return societies;
// 	}


// 	public void setSocieties(String societies) {
// 		this.societies = societies;
// 	}

// 	public String getSports() {
// 		return sports;
// 	  }
	
// 	  public void setSports(String sports) {
// 		this.sports = sports;
// 	  }


//       public String getEthnicity() {
// 		return ethnicity;
// 	  }
	
// 	  public void setEthnicity(String ethnicity) {
// 		this.ethnicity = ethnicity;
// 	  }


// 	@Override
// 	public String toString() {
// 		return "User [id=" + id + ", course=" + course + ", hobbies=" + hobbies + ", societies=" + societies + ", sports=" + sports + ", ethnicity=" + ethnicity +"]";
// 	}
	
	
// }
