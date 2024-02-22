package com.example.demo.dto;

import com.example.demo.UserType;


// package com.example.demo.dto;

public class UserPostDTO {
    private String name;
    private String email;
    private String password;
    private String profilePicture;

    public UserPostDTO() {
    }

    public UserPostDTO(String name, String email, String password, String profilePicture) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}








// public class UserPostDTO {
// 	String name;
// 	String email;
// 	String password;
	
	
// 	public UserPostDTO(String name, String email, String password) {
// 		super();
// 		this.name = name;
// 		this.email = email;
// 		this.password = password;
		
// 	}
	
// 	public String getName() {
// 		return name;
// 	}

// 	public void setName(String name) {
// 		this.name = name;
// 	}

// 	public String getEmail() {
// 		return email;
// 	}

// 	public void setEmail(String email) {
// 		this.email = email;
// 	}











// 	public String getPassword() {
// 		return password;
// 	}

// 	public void setPassword(String password) {
// 		this.password = password;
// 	}
// }
	// public UserType convertType() {
		
	// 	if(this.userType == null || (this.userType[0]==false && this.userType[1]==false))
	// 		return UserType.NONE;
		
	// 	if(this.userType[0]==true && this.userType[1]==false)
	// 		return UserType.BUYER;
		
	// 	if(this.userType[0] == false && this.userType[1]== true)
	// 		return UserType.SELLER;
	
	// 	return UserType.BOTH;
		
		
	//}

