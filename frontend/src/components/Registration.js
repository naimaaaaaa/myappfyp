import React, {useRef} from "react";
import {Link} from "react-router-dom";
import axios from "axios";

export default function Registration(){
    const name=useRef();
    const email=useRef();
    const password=useRef();
    const repPassword=useRef();
   // const buyer=useRef();
    //const seller=useRef();
    const tos=useRef();

    const validateForm = () => {
        let formValid = false;

        if (name.current.validity.valueMissing 
            || email.current.validity.valueMissing 
            || password.current.validity.valueMissing
            || repPassword.current.validity.valueMissing){
                alert("Please fill in all text fields.");
        }
        else if (email.current.validity.typeMismatch){
            alert("Invalid e-mail address. Please enter your e-mail again.");
        }else if (password.current.validity.tooShort){
            alert("Password is too short. Please select another password");
        } 

        else if(repPassword.current.value!==password.current.value) {
            alert("Passwords do not match. Please retry");
        } 
        
        else if (tos.current.validity.valueMissing){
            alert("Please agree to the Terms and Conditions, and Privacy Policy.")
        }else{
            formValid = true;
        }
        return formValid;
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        if(validateForm()){
            //let buyer_seller=[buyer.current.checked,seller.current.checked]
           // console.log("Buyer/Seller Data: ", buyer_seller);
            axios.post('http://localhost:8080/user',{
                name: name.current.value,
                email: email.current.value,
                password: password.current.value,
                //repPassword:repPassword.current.value,
                //buyer_seller: buyer_seller,
            }, ).then(response=>{
                console.log(response);
                if (response.status === 201){
                    alert("Registered successfully.")
                }
            }).then(()=>{
                name.current.value="";
                email.current.value="";
                password.current.value="";
                repPassword.current.value="";
               // buyer.current.checked=false;
               // seller.current.checked=false;
                tos.current.checked=false;
            })
            .catch(error=>{
                alert("User already exists.")
                console.log(error);
            })
        }
      }

    return (
        <div className="container">
        <h2>REGISTER</h2>  
        <form className="Registration" noValidate onSubmit={handleSubmit}>

            {/* <label className="labelText">Name: </label> */}
          
          

            {/* <label className="labelText">Email:</label> */}
            <div className="input-control1reg">
            <input type="email" ref={email} name="email" required placeholder="Email"/><br/><br/>
            </div>

            <div className="input-controlreg">
            <input type="text" ref={name} required placeholder="Name" /><br/><br/>
            </div>

            {/* <label className="labelText">Password:</label> */}
            <div className="input-control2reg">
            <input type="password" ref={password} name="password" required minLength="8" placeholder="Password"/><br/><br/>
            </div>
            

            {/* <label className="labelText">Re-type password:</label> */}
            <div className="input-control3reg">
            <input type="password" ref={repPassword} name="repPassword" required placeholder="Confirm Password"/><br/><br/>
            </div>


            <input type="checkbox" ref={tos} name="tos" value="tos" required/>
            <label>I agree to the Terms of Use and Privacy Policy.</label>
            <br/><br/>

            <input type="submit" value="Register"/>
            {/* <Link to={'/help'}>Learn more</Link> */}
        </form>
        </div>
    ); 
}

















// import React, {useRef} from "react";
// import {Link} from "react-router-dom";
// import axios from "axios";

// export default function Registration(){
//     const name=useRef();
//     const email=useRef();
//     const password=useRef();
//     const repPassword=useRef();
//     // const buyer=useRef();
//     // const seller=useRef();
//     const tos=useRef();

//     const validateForm = () => {
//         let formValid = false;

//         if (name.current.validity.valueMissing 
//             || email.current.validity.valueMissing 
//             || password.current.validity.valueMissing
//             || repPassword.current.validity.valueMissing){
//                 alert("Please fill in all text fields.");
//         }
//         else if (email.current.validity.typeMismatch)
//         {
//             alert("Invalid e-mail address. Please enter your e-mail again.");
//         }
//         else if (password.current.validity.tooShort)
//         {
//             alert("Password is too short. Please select another password");
//         } 
//         else if(password.value !== repPassword.value) 
//         {
//             alert("Passwords do not match. Please retry");
//         }
//         //  else if (!buyer.current.checked && !seller.current.checked)
//         //  {
//         //     alert("Please check at least one checkbox to select being a seller or a buyer in the system.")
//         // } 
//         else if (tos.current.validity.valueMissing)
//         {
//             alert("Please agree to the Terms and Conditions, and Privacy Policy.")
//         }
//         else
//         {
//             formValid = true;
//         }
//         return formValid;
//     }

//     const handleSubmit = (event) => {
//         event.preventDefault();

//         if(validateForm()){
//             //let buyer_seller=[buyer.current.checked,seller.current.checked]
//             axios.post('http://localhost:8080/user',{
//                 name: name.current.value,
//                 email: email.current.value,
//                 password: password.current.value,
//                // buyer_seller: buyer_seller,
//             }).then(response=>{
//                 console.log(response);
//                 if (response.status === 201){
//                     alert("Registered successfully.")
//                 }
//             }).then(()=>{
//                 name.current.value="";
//                 email.current.value="";
//                 password.current.value="";
//                 repPassword.current.value="";
//                 // buyer.current.checked=false;
//                 // seller.current.checked=false;
//                 tos.current.checked=false;
//             })
//             .catch(error=>{
//                 console.log(error);
//             })
//         }
//       }

//     return (
//         <div className="container">
//         <h2>REGISTER</h2>  
//         <form className="registration" noValidate onSubmit={handleSubmit}>
//             <label className="labelText">Name: </label>
//             <input type="text" ref={name} required/><br/><br/>

//             <label className="labelText">Email:</label>
//             <input type="email" ref={email} name="email" required/><br/><br/>

//             <label className="labelText">Password:</label>
//             <input type="password" ref={password} name="password" required minLength="8"/><br/><br/>

//             <label className="labelText">Re-type password:</label>
//             <input type="password" ref={repPassword} name="repPassword" required/><br/><br/>

//             {/* <input type="checkbox" ref={buyer} name="buyer" value="buyer"/>
//             <label>I want to buy produce directly from allotment owners.</label><br/>

//             <input type="checkbox" ref={seller} name="seller" value="seller"/>
//             <label>I want to sell produce from my allotment.</label><br/><br/> */}

//             <input type="checkbox" ref={tos} name="tos" value="tos" required/>
//             <label>I agree to the Terms of Use and Privacy Policy.</label>
//             <br/><br/>

//             <input type="submit" value="Register"/>
//             <Link to={'/help'}>Learn more</Link>
//         </form>
//         </div>
//     )
// }