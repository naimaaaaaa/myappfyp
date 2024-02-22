// package com.example.demo.model;

// import java.io.Serializable;
// import java.util.Date;

// // import java.sql.Date;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.EntityListeners;
// import javax.persistence.Id;
// import javax.persistence.Table;
// import javax.persistence.Temporal;
// import javax.persistence.TemporalType;
// import javax.validation.constraints.NotBlank;

// import org.springframework.data.annotation.CreatedDate;
// import org.springframework.data.annotation.LastModifiedDate;
// import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// //Let's create a simple User class
// @Entity
// @Table(name = "ExtraInfo")
// @EntityListeners(AuditingEntityListener.class)
// public class extraInfo implements Serializable {
// 	private static final long serialVersionUID = 1L;

// 	@Id
//     //@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	//Long id;
	
// 	@NotBlank
// 	String course;
	
// 	@NotBlank
// 	//@Column(unique=true)
// 	String societies;
	
// 	@NotBlank
// 	String sports;
	
//     @NotBlank
// 	String hobbies;
// 	//UserType userType;
	
// 	@Column(nullable = false, updatable = false)
// 	@Temporal(TemporalType.TIMESTAMP)
// 	@CreatedDate
// 	private Date createdAt;

// 	@Column(nullable = false)
// 	@Temporal(TemporalType.TIMESTAMP)
// 	@LastModifiedDate
// 	private Date updatedAt;
	
// 	// @Column(name = "profile_picture")
// 	// private String profilePicture;
	
	
// 	 public extraInfo() {
// 			super();
// 			// TODO Auto-generated constructor stub
// 	}
		
	 
// 	 public extraInfo(String course, String societies, String sports, String hobbies) {
// 		super();
// 		this.course = course;
// 		this.societies = societies;
// 		this.sports = sports;
//         this.hobbies= hobbies;
// 	//	this.userType = userType;
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
// 	}


// 	public void setSports(String sports) {
// 		this.sports = sports;
// 	}

	
	
//     @Override
// 	public String toString() {
// 		return "User [course=" + course + ", societies=" + societies + ", sports=" + sports + ", hobbies=" + hobbies + "]";
// 	}
	
	
// }