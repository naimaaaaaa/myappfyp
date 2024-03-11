
import React, { useEffect, useRef, useState } from "react";
import { Link, useOutletContext, useParams } from "react-router-dom";
import "../Styles/profile.css"
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Divider from "../components/divider";
import Hobbies from "../components/hobbies";
import Badge from "../components/badge";
import Sports from "../components/sports";
import Courses from "../components/courses";
import Societies from "../components/societies";
import { useRegistrationStore } from "../store/registration.store"

export default function Profile() {
  const [loggedInUser, setLoggedinUser] = useOutletContext();
  const [file, setFile] = useState(null);
  const [errorMessage, setErrorMessage] = useState('');
  const jwt = sessionStorage.getItem('jwt');
  const [userDetails, setUserDetails] = useState({})
  const [userAbbrev, setUserAbbrev] = useState("")
  const { setHobbies, setSocieties, setSports, reset, setUserId } = useRegistrationStore()

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/userInfo/", {
          params: {
            email: loggedInUser
           

          },
          headers: {
            "Content-Type": "multipart/form-data",
            Authorization: `Bearer ${jwt}`
          },
        })
        setHobbies(response.data.hobbies)
        setSports(response.data.sports)
        setSocieties(response.data.societies)
        setUserId(response.data.id)
        setUserDetails({
          name: response.data.name,
          email: response.data.email,
          course: response.data.course,
          ethnicity: response.data.ethnicity
        })
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();

  }, []);



  useEffect(() => {
    if (userDetails.name) {
      const words = userDetails.name.split(" ");
      const abbreviated = words.map(word => word.charAt(0).toUpperCase()).join("");
      setUserAbbrev(abbreviated)

    }
  }, [userDetails])


  return (
    <div>
      <section className="details-section">
        <div className="profile-image">
          <text>{userAbbrev}</text>
        </div>
        <div class="grid-container">
          <div class="grid-item">Name</div>
          <div class="grid-item">{userDetails.name}</div>
          <div class="grid-item">Email</div>
          <div class="grid-item">{userDetails.email}</div>
          <div class="grid-item">Course</div>
          <div class="grid-item">{userDetails.course}</div>
          <div class="grid-item">Ethnicity</div>
          <div class="grid-item">{userDetails.ethnicity}</div>
        </div>
      </section>
      <Divider />
      <Hobbies />
      <Divider />
      <Sports />
      <Divider />
      <Societies />
      <Divider />
    </div>

  );
}



