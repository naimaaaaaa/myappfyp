import React, { useEffect, useState } from "react";
import { Link, useOutletContext, useParams } from "react-router-dom";
import axios from "axios";

function UserConnections() {
  const [similarUsers, setSimilarUsers] = useState([]);
  const [connectedUsers, setConnectedUsers] = useState([]);
  const [userDetails, setUserDetails] = useState({});
  const jwt = sessionStorage.getItem('jwt');
  const [loggedInUser, setLoggedinUser] = useOutletContext();
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

      // Fetch similar users
      console.log("Fetching similar users...");
      const similarUsersResponse = await axios.get("http://localhost:8080/userInfo/similarUsers", {
        params: {
          userId: userDetailsResponse.data.id
          
        }, 
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${jwt}`
        }
      });
      console.log("Similar users response:", similarUsersResponse.data);
    
      setSimilarUsers(similarUsersResponse.data);

          
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

  const connectWithUser = async (selectedUserId) => {
    try {
      const response = await axios.post(
        "http://localhost:8080/userInfo/connect",
        null,
        {
          params: { userId: userDetails.id, selectedUserId },
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${jwt}`
          }
        }
      );
      console.log("Connection successful:", response.data);
      setConnectionMessage("Connection successful!");
      fetchData();
    } catch (error) {
      console.error("Error connecting with user:", error);
    }
  };


  const deleteConnection = async (connectionId) => {
    try {
      const response = await axios.delete(`http://localhost:8080/userInfo/connections/${userDetails.id}/${connectionId}`, {
        headers: {
          Authorization: `Bearer ${jwt}`
        }
      });
      console.log("Connection deleted:", response.data);
      fetchData();
    } catch (error) {
      console.error("Error deleting connection:", error);
    }
  };

  return (
    <div className="user-connections-container">
      <div className="user-details">
        <div>Name: {userDetails.name}</div>
        <div>Email: {userDetails.email}</div>
      </div>

      <div className="similar-users">
        <h2>Similar Users</h2>
        {similarUsers.length === 0 ? (
          <p>You have no similarities to others yet, add more hobbies, sports, or societies to your profile</p>
        ) : (
          <ul>
            {similarUsers.map((user) => (
              <li key={user.id}>
                {user.name} <button onClick={() => connectWithUser(user.id)}>Connect</button>
              </li>
            ))}
          </ul>
        )}
      </div>

      <div className="connected-users">
        <h2>Connected Users</h2>
        <ul>
          {connectedUsers.map((user) => (
            <li key={user.id}>
              {user.name} <button onClick={() => deleteConnection(user.id)}>Delete</button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
  
}

export default UserConnections;
