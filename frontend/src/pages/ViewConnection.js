import React, { useEffect, useState } from "react";
import { Link, useOutletContext, useParams } from "react-router-dom";
import "../Styles/profile.css";
import "../Styles/login.css";
import Divider from "../components/divider";
import axios from "axios";

function ViewConnection() {
  const [similarUsers, setSimilarUsers] = useState([]);
  const [connectedUsers, setConnectedUsers] = useState([]);
  const [userDetails, setUserDetails] = useState({});
  const jwt = sessionStorage.getItem('jwt');
  const [loggedInUser, setLoggedinUser] = useOutletContext();
  const [userAbbrev, setUserAbbrev] = useState("")
  const [connectionMessage, setConnectionMessage] = useState(""); // New state for connection message

  useEffect(() => {
    // Fetch user details and similar users when component mounts
    fetchData();
  }, []);

  const fetchData = async () => {
    console.log("Fetching user details...");
    try {
      // Fetch user details
      const userDetailsResponse = await axios.get("http://localhost:8080/userInfo/", {
        params: {
          email: loggedInUser
        },
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${jwt}`
        }
      });
      console.log("User details response:", userDetailsResponse.data);
      setUserDetails(userDetailsResponse.data);


      // Fetch connected users
      const connectionsResponse = await axios.get("http://localhost:8080/userInfo/connections", {
        params: {
          userId: userDetailsResponse.data.id
        },
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${jwt}`
        }
      });
      setConnectedUsers(connectionsResponse.data);
    } 
    catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    if (userDetails.name) {
      const words = userDetails.name.split(" ");
      const abbreviated = words.map(word => word.charAt(0).toUpperCase()).join("");
      setUserAbbrev(abbreviated);
    }
  }, [userDetails])






  // Define the function to get abbreviated names
  const getUserAbbrev = (name) => {
    const words = name.split(" ");
    const abbreviated = words.map(word => word.charAt(0).toUpperCase()).join("");
    return abbreviated;
  };





  const deleteConnection = async (connectionId) => {
    try {
      const response = await axios.delete(`http://localhost:8080/userInfo/connections/${userDetails.id}/${connectionId}`, {
        headers: {
          Authorization: `Bearer ${jwt}`
        }
      });
      console.log("Connection deleted:", response.data);
      const updatedConnectedUsers = connectedUsers.filter(user => user.id !== connectionId);
      setConnectedUsers(updatedConnectedUsers);
    } catch (error) {
      console.error("Error deleting connection:", error);
    }
  };

  return (
    <div class="login-container">
    <div className="centered-box">
      <div className="profile-section">
        <div className="profile-image-connect">
          <text>{userAbbrev}</text>
        </div>
        <div className="user-name">{userDetails.name}</div>
      </div>
      <Divider />
      <div className="connected-users">
        <h3>YOUR CONNECTIONS</h3>
        <Divider />
        <ul style={{ listStyleType: "none" }}>
          {connectedUsers.map((user, index) => (
            <React.Fragment key={user.id}>
              <li>
                <div className="connected-user">
                  <div className="profile-image-small">
                    <text>{getUserAbbrev(user.name)}</text>
                    {/* <img src={user.profilePictureUrl} alt={`${user.name}`} /> */}
                  </div>
                  <div className="user-details">
                    <div className="user-name">{user.name}</div>
                    <div className="user-actions">
                    <button className="rounded-button" onClick={() => deleteConnection(user.id)}>Remove Connection</button>
                    </div>
                  </div>
                </div>
              </li>
              {index !== connectedUsers.length - 1 && <Divider />}
            </React.Fragment>
          ))}
        </ul>
      </div>
    </div>
    </div>
  );
  
}

export default ViewConnection;
// return (
//     <div className="user-connections-container">
//       <div className="user-details">
//         <div>Name: {userDetails.name}</div>
       
//       </div>

      

//       <div className="connected-users">
//         <h2>Connected Users</h2>
//         <ul>
//           {connectedUsers.map((user) => (
//             <li key={user.id}>
//               {user.name} <button onClick={() => deleteConnection(user.id)}>Delete</button>
//             </li>
//           ))}
//         </ul>
//       </div>
//     </div>
//   );