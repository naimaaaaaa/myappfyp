import React, { useEffect, useState } from "react";
import { Link, useOutletContext, useParams } from "react-router-dom";
import "../Styles/profile.css";
import "../Styles/login.css";
import Divider from "../components/divider";
import axios from "axios";
import { useRegistrationStore } from "../store/registration.store";
import Hobbies from "../components/hobbies";
import Sports from "../components/sports";
import Societies from "../components/societies";

function MakeConnection() {
  const [similarUsers, setSimilarUsers] = useState([]);
  const [userDetails, setUserDetails] = useState({});
  const jwt = sessionStorage.getItem('jwt');
  const [loggedInUser, setLoggedinUser] = useOutletContext();
  const [userAbbrev, setUserAbbrev] = useState("");
  const { setHobbies, setSocieties, setSports, setUserId } = useRegistrationStore();

  
  useEffect(() => {
    fetchData();
  }, []);
  
  const fetchData = async () => {
    try {
      const userDetailsResponse = await axios.get("http://localhost:8080/userInfo/", {
        params: { email: loggedInUser },
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${jwt}`,
        },
      });
      setUserDetails(userDetailsResponse.data);

      const similarUsersResponse = await axios.get("http://localhost:8080/userInfo/similarUsers", {
        params: { userId: userDetailsResponse.data.id },
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${jwt}`,
        },
      });

      const updatedSimilarUsers = await Promise.all(similarUsersResponse.data.map(async (user) => {
        try {
          const userResponse = await axios.get("http://localhost:8080/userInfo/", {
            params: { email: user.email },
            headers: {
              "Content-Type": "multipart/form-data",
              Authorization: `Bearer ${jwt}`,
            },
          });

          return {
            ...user,
            hobbies: userResponse.data.hobbies,
            societies: userResponse.data.societies,
            sports: userResponse.data.sports,
            course: userResponse.data.course,
            ethnicity: userResponse.data.ethnicity,
          };
        } catch (error) {
          console.error(`Error fetching user data for ${user.name}:`, error);
          return user; // Return the user with existing data if fetching fails
        }
      }));
      setSimilarUsers(updatedSimilarUsers);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    if (userDetails.name) {
      const words = userDetails.name.split(" ");
      const abbreviated = words.map(word => word.charAt(0).toUpperCase()).join("");
      setUserAbbrev(abbreviated);
    }
  }, [userDetails]);

  const getUserAbbrev = (name) => {
    const words = name.split(" ");
    const abbreviated = words.map(word => word.charAt(0).toUpperCase()).join("");
    return abbreviated;
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
      fetchData();
    } catch (error) {
      console.error("Error connecting with user:", error);
    }
  };

  return (
    <div className="login-container">
      <div className="centered-box">
        <div className="profile-section">
          <div className="profile-image-connect">
            <text>{userAbbrev}</text>
          </div>
          <div className="user-name">
            <text> {userDetails.name}</text>
          </div>
          <Divider />
          <Hobbies hobbies={userDetails.hobbies} />
          <Divider />
          <Sports sports={userDetails.sports} />
          <Divider />
          <Societies societies={userDetails.societies} />
        </div>
        <Divider />
        <div className="similar-users">
          <h3>START MAKING CONNECTIONS</h3>
          <Divider />
          {similarUsers.length === 0 ? (
            <p>
              You have no similarities to others yet, add more hobbies, sports, or
              societies to your profile
            </p>
          ) : (
            <ul>
{similarUsers.map((user, index) => (
  <React.Fragment key={user.id}>
    <li>
      <div className="connected-user">
        <div className="profile-image-small">
          <text>{getUserAbbrev(user.name)}</text>
        </div>
        <div className="user-details">
          <div className="user-name">{user.name}</div>
          <div className="user-info">
            <div className="user-actions">
              <button className="rounded-button" onClick={() => connectWithUser(user.id)}>Connect</button>
            </div>
            <div className="additional-info">
              {user.course && (
                <button className="unclickable-button">{`Course: ${user.course}`}</button>
              )}
              {user.hobbies && user.hobbies.length > 0 && (
                <button className="unclickable-button">{`Hobbies: ${user.hobbies.map(hobby => hobby.name).join(", ")}`}</button>
              )}
              {user.societies && user.societies.length > 0 && (
                <button className="unclickable-button">{`Societies: ${user.societies.map(society => society.name).join(", ")}`}</button>
              )}
              {user.sports && user.sports.length > 0 && (
                <button className="unclickable-button">{`Sports: ${user.sports.map(sport => sport.name).join(", ")}`}</button>
              )}
              {user.ethnicity && (
                <button className="unclickable-button">{`Ethnicity: ${user.ethnicity}`}</button>
              )}
            </div>
          </div>
        </div>
      </div>
    </li>
    {index !== similarUsers.length - 1 && <Divider />}
  </React.Fragment>
))}


            </ul>
          )}
        </div>
      </div>
    </div>
  );
}

export default MakeConnection;


// import React, { useEffect, useState } from "react";
// import { Link, useOutletContext, useParams } from "react-router-dom";
// import "../Styles/profile.css";
// import "../Styles/login.css";
// import Divider from "../components/divider";
// import axios from "axios";
// import { useRegistrationStore } from "../store/registration.store";

// function MakeConnection() {
//   const [similarUsers, setSimilarUsers] = useState([]);
//   const [connectedUsers, setConnectedUsers] = useState([]);
//   const [userDetails, setUserDetails] = useState({});
//   const jwt = sessionStorage.getItem('jwt');
//   const [loggedInUser, setLoggedinUser] = useOutletContext();
//   const [userAbbrev, setUserAbbrev] = useState("");
//   const [connectionMessage, setConnectionMessage] = useState("");
//   const { setUserId } = useRegistrationStore();

//   useEffect(() => {
//     fetchData();
//   }, []);

//   const fetchData = async () => {
//     try {
//       const userDetailsResponse = await axios.get("http://localhost:8080/userInfo/", {
//         params: { email: loggedInUser },
//         headers: {
//           "Content-Type": "multipart/form-data",
//           Authorization: `Bearer ${jwt}`
//         }
//       });
//       setUserDetails(userDetailsResponse.data);

//       const similarUsersResponse = await axios.get("http://localhost:8080/userInfo/similarUsers", {
//         params: { userId: userDetailsResponse.data.id },
//         headers: {
//           "Content-Type": "multipart/form-data",
//           Authorization: `Bearer ${jwt}`
//         }
//       });

//       const updatedSimilarUsers = await Promise.all(similarUsersResponse.data.map(async (user) => {
//         const userResponse = await axios.get("http://localhost:8080/userInfo/", {
//           params: { email: user.email },
//           headers: {
//             "Content-Type": "multipart/form-data",
//             Authorization: `Bearer ${jwt}`
//           }
//         });

//         return {
//           ...user,
//           hobbies: userResponse.data.hobbies,
//           societies: userResponse.data.societies,
//           sports: userResponse.data.sports,
//           course: userResponse.data.course,
//           ethnicity: userResponse.data.ethnicity
//         };
//       }));
//       setSimilarUsers(updatedSimilarUsers);
//     } catch (error) {
//       console.error('Error fetching data:', error);
//     }
//   };

//   useEffect(() => {
//     if (userDetails.name) {
//       const words = userDetails.name.split(" ");
//       const abbreviated = words.map(word => word.charAt(0).toUpperCase()).join("");
//       setUserAbbrev(abbreviated);
//     }
//   }, [userDetails]);

//   const getUserAbbrev = (name) => {
//     const words = name.split(" ");
//     const abbreviated = words.map(word => word.charAt(0).toUpperCase()).join("");
//     return abbreviated;
//   };

//   const connectWithUser = async (selectedUserId) => {
//     try {
//       const response = await axios.post(
//         "http://localhost:8080/userInfo/connect",
//         null,
//         {
//           params: { userId: userDetails.id, selectedUserId },
//           headers: {
//             "Content-Type": "application/json",
//             Authorization: `Bearer ${jwt}`
//           }
//         }
//       );
//       console.log("Connection successful:", response.data);
//       setConnectionMessage("Connection successful!");
//       fetchData();
//     } catch (error) {
//       console.error("Error connecting with user:", error);
//     }
//   };

//   return (
//     <div class="login-container">
//       <div className="centered-box">
//         <div className="profile-section">
//           <div className="profile-image-connect">
//             <text>{userAbbrev}</text>
//           </div>
//           <div className="user-name">
//             <text> {userDetails.name}</text>
//           </div>
//         </div>
//         <Divider />
//         <div className="similar-users">
//           <h3>START MAKING CONNECTIONS</h3>
//           <Divider />
//           {similarUsers.length === 0 ? (
//             <p>
//               You have no similarities to others yet, add more hobbies, sports, or
//               societies to your profile
//             </p>
//           ) : (
//             <ul>
//               {similarUsers.map((user, index) => (
//                 <React.Fragment key={user.id}>
//                   <li>
//                     <div className="connected-user">
//                       <div className="profile-image-small">
//                         <text>{getUserAbbrev(user.name)}</text>
//                       </div>
//                       <div className="user-details">
//                         <div className="user-name">{user.name}</div>
//                         <div className="user-info">
//                           {user.hobbies && user.hobbies.length > 0 && <div className="user-hobbies">Hobbies: {user.hobbies.join(", ")}</div>}
//                           {user.societies && user.societies.length > 0 && <div className="user-societies">Societies: {user.societies.join(", ")}</div>}
//                           {user.sports && user.sports.length > 0 && <div className="user-sports">Sports: {user.sports.join(", ")}</div>}
//                           {user.course && <div className="user-course">Course: {user.course}</div>}
//                           {user.ethnicity && <div className="user-ethnicity">Ethnicity: {user.ethnicity}</div>}
//                         </div>
//                         <div className="user-actions">
//                           <button className="rounded-button" onClick={() => connectWithUser(user.id)}>Connect</button>
//                         </div>
//                       </div>
//                     </div>
//                   </li>
//                   {index !== similarUsers.length - 1 && <Divider />}
//                 </React.Fragment>
//               ))}
//             </ul>
//           )}
//         </div>
//       </div>
//     </div>
//   );
// }

// export default MakeConnection;

