import React, { useRef, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import "../Styles/login.css"
import { useRegistrationStore } from "../store/registration.store";
import { courseArray, hobbiesArray, societiesArray, sportsArray } from "../assets/registrationData";
import MultiSelectBadge from "../components/multiSelectBadge";
import Badge from "../components/badge";
export default function RegistrationPartTwo() {
    // const { name, email, password, ethinicity, reset } = useRegistrationStore();
    const { name, email, password, ethnicity, reset } = useRegistrationStore();

    const [societies, setSocieties] = useState([])
    const [sports, setSports] = useState([])
    const [hobbies, setHobbies] = useState([])
    const course = useRef();
    const tos = useRef();
    const navaigate = useNavigate()


    const validateForm = () => {
        let formValid = false;

        if (course.current.validity.valueMissing
            || societies.length === 0
            || sports.length === 0
            || hobbies.length === 0) {
            alert("Please select in all text fields.");
        }
        else {
            formValid = true;
        }
        return formValid;
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        if (validateForm()) {
            const formData = {
                name,
                email,
                password,
                // ethinicity,
                ethnicity,
                course: course.current.value,
                societies: societies,
                sports: sports,
                hobbies: hobbies
            };
    
            axios.post('http://localhost:8080/auth/register', formData)
                .then(response => {
                    console.log(response);
                    if (response.status === 201) {
                        alert("Registered successfully.");
                    }
                    navaigate("/login");
                })
                .catch(error => {
                    alert("Error registering account. Please try again later.");
                    console.log(error);
                });
        }
    };

    const handleDelete = (path, value) => {
        if(path === 'societies') {
            const newSocieties = societies.filter(so => so !== value)
            setSocieties(newSocieties)
        } else if (path === 'sports') {
            const newSports = sports.filter(sp => sp !== value)
            setSports(newSports)
        } else if (path === 'hobbies') {
            const newHobbies = hobbies.filter(hb => hb !== value)
            setHobbies(newHobbies)
        }
    }

    return (
        <div class="login-container">
        <div class="form-container">
            <div class="heading">Sign Up</div>
            <form class="form" noValidate onSubmit={handleSubmit}>
                {/* <div class="small-heading">Part 2</div> */}
                <select name="course" ref={course} placeholder="Select Course">
                    <option value="" style={{ color: "#695959" }}>Select Course</option>
                    {
                        courseArray && courseArray.map(c => {
                            return (
                                <option>
                                    {c}
                                </option>
                            )
                        })
                    }
                </select>
                <select name="societies" onChange={(value) => setSocieties(prev => [...prev, value.target.value])} placeholder="Select Societies">
                    <option value="" style={{ color: "#695959" }}>Select Societies</option>
                    {
                        societiesArray && societiesArray.map(c => {
                            return (
                                <option>
                                    {c}
                                </option>
                            )
                        })
                    }
                </select>
                {
                    societies && societies.map(society => {
                        return (
                            <Badge title={society} edit={true} onClick={() => handleDelete('societies', society)}/>
                        )
                    })
                }
                <select name="sports" onChange={(value) => setSports(prev => [...prev, value.target.value])} placeholder="Select Sports">
                    <option value="" style={{ color: "#695959" }}>Select Sports</option>
                    {
                        sportsArray && sportsArray.map(c => {
                            return (
                                <option>
                                    {c}
                                </option>
                            )
                        })
                    }
                </select>
                {
                    sports && sports.map(sport => {
                        return (
                            <Badge title={sport} edit={true} onClick={() => handleDelete('sports', sport)}/>
                        )
                    })
                }
                <select name="hobbies" onChange={(value) => setHobbies(prev => [...prev, value.target.value])} placeholder="Select Hobbies">
                    <option value="" style={{ color: "#695959" }}>Select Hobbies</option>
                    {
                        hobbiesArray && hobbiesArray.map(c => {
                            return (
                                <option>
                                    {c}
                                </option>
                            )
                        })
                    }
                </select>
                {
                    hobbies && hobbies.map(hobby => {
                        return (
                            <Badge title={hobby} edit={true} onClick={() => handleDelete('hobbies', hobby)}/>
                        )
                    })
                }
                <input class="login-button" type="submit" value="Sign up" />
            </form></div>
            </div>
    );
}