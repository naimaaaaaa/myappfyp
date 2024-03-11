import React, { useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import "../Styles/login.css"
import { useRegistrationStore } from "../store/registration.store";

export default function Registration() {
    const { setName, setEmail, setPassword, setEthnicity } = useRegistrationStore();

    const name = useRef();
    const email = useRef();
    const password = useRef();
    const repPassword = useRef();
    const ethinicity = useRef();
    const tos = useRef();
    const navaigate = useNavigate()
    const validateForm = () => {
        let formValid = false;

        if (name.current.validity.valueMissing
            || email.current.validity.valueMissing
            || password.current.validity.valueMissing
            || repPassword.current.validity.valueMissing) {
            alert("Please fill in all text fields.");
        }
        else if (email.current.validity.typeMismatch) {
            alert("Invalid e-mail address. Please enter your e-mail again.");
        } else if (password.current.validity.tooShort) {
            alert("Password is too short. Please select another password");
        }else if (repPassword.current.value !== password.current.value) {
            alert("Passwords do not match. Please retry");
        }else {
            formValid = true;
        }
        return formValid;
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        if (validateForm()) {
            setName(name.current.value)
            setEmail(email.current.value)
            setPassword(password.current.value)
            setEthnicity(ethinicity.current.value)
            navaigate("/register/part-two")
        }   
    }

    return (
        <div class="form-container">
            <div class="heading">Sign Up</div>
            <form class="form" noValidate onSubmit={handleSubmit}>
                <div class="small-heading">Part 1</div>
                <input class="input" ref={email} type="email" name="email" id="email" placeholder="E-mail" />
                <input class="input" ref={name} type="name" name="name" id="name" placeholder="Name" />
                <input required="true" class="input" ref={password} type="password" name="password" id="password" minLength="8" placeholder="Password" />
                <input required="true" class="input" ref={repPassword} type="password" name="repPassword" id="repPassword" placeholder="Confirm Password" />
                <select name="ethnicity" ref={ethinicity} placeholder="Select Ethnicity">
                    <option value="" style={{color: "#695959"}}>Select</option>
                    <option value="Asian, Asian British or Asian Welsh">Asian, Asian British or Asian Welsh</option>
                    <option value="	Black, Black British, Black Welsh, Caribbean or African: African">Black, Black British, Black Welsh, Caribbean or African: African</option>
                    <option value="	Black, Black British, Black Welsh, Caribbean or African: Carabbean">Black, Black British, Black Welsh, Caribbean or African: Carabbean</option>
                    <option value="	Black, Black British, Black Welsh, Caribbean or African: Other">Black, Black British, Black Welsh, Caribbean or African: Other</option>
                    <option value="	Mixed or Multiple ethnic groups: White and Asian">Mixed or Multiple ethnic groups: White and Asian</option>
                    <option value="	Mixed or Multiple ethnic groups: White and Black African">Mixed or Multiple ethnic groups: White and Black</option>
                    <option value="	Mixed or Multiple ethnic groups: Other Mixed or Multiple ethnic groups">Mixed or Multiple ethnic groups: Other Mixed or Multiple ethnic groups</option>
                    <option value="	White: English, Welsh, Scottish, Northern Irish or British">White: English, Welsh, Scottish, Northern Irish or British</option>
                    <option value="	White: Other White">White: Other White</option>
                    <option value="	Other ethnic group: Any other ethnic group">	Other ethnic group: Any other ethnic group</option>
                    <option value="	Prefer not to disclose">Prefer not to disclose</option>
                </select>
                <input class="login-button" type="submit" value="Next" />
            </form></div>
    );
}