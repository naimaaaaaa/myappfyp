
import React, {useRef, useState} from "react";
import { Link, useOutletContext, useParams} from "react-router-dom";
import "../Styles/Login.css"
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function Profile() {
  const [loggedInUser, setLoggedinUser] = useOutletContext();
  const [file, setFile] = useState(null);
  const [errorMessage, setErrorMessage] = useState('');
  const jwt = sessionStorage.getItem('jwt');
  //const email = loggedInUser.email;



  
  const handleFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const handleSubmitpost = async (event) => {
    event.preventDefault();
    
    const formData = new FormData();
    formData.append("file", event.target.file.files[0]);
    formData.append("email", loggedInUser);

       try {
      const response = await axios.post(`http://localhost:8080/picture/${loggedInUser}`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${jwt}`,
        },
      });
      console.log(response.data);
      alert("Profile picture uploaded successfully!");
    } 
      catch (error) {
      console.error(error);
      alert("Failed to upload profile picture.");
    }
  };
  const handleSubmitGet = async (event) => {
    event.preventDefault();

       try {
      const response = await axios.get(`http://localhost:8080/displaypicture/${loggedInUser}`, {
        headers: {
          email: loggedInUser,
          Authorization: `Bearer ${jwt}`,
        },
      });
      console.log(response.data);
      alert("Profile picture displayed successfully!");
      document.getElementById("profile-picture").src = URL.createObjectURL(response.data);

    } 
      catch (error) {
      console.error(error);
      alert("Failed display profile picture.");
    }
  };


  return (

  <div className="profilecontainer">
    <img id="profile-image" alt="Profile picture" />
    <form onSubmit={handleSubmitpost}>
      <input type="file" name="file" onChange={handleFileChange}/>
      <button type="submit">Upload</button>
    </form>
    <button onClick={handleSubmitGet}>Display Picture</button>
  </div>
 );
}

    

