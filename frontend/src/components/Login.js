import React, {useRef} from "react";
import { Link, useOutletContext } from "react-router-dom";
import "../Styles/Login.css"
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function Login(props){   
    const navigate = useNavigate();
    const email=useRef();
    const password=useRef();
    const [loggedInUser, setLoggedinUser] = useOutletContext();

    const validateForm = () => {
        let formValid = false;
        if (email.current.validity.valueMissing
            || password.current.validity.valueMissing){
                alert("Please fill in all text fields.");
        }else if (email.current.validity.typeMismatch){
            alert("Invalid e-mail address. Please enter your e-mail again.");
        }else{
            formValid = true;
        }
        return formValid;
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        const dataLogin = {
            username: email.current.value,
            password: password.current.value
        };

        if(validateForm()){
            axios({
                method: 'post',
                url: 'http://localhost:8080/login',
                data: dataLogin
            })
            .then(response=>{
                if (response.status === 200){
                    const jwtToken = response.headers.authorization.split(' ')[1]
                    if (jwtToken !== null) {
                        console.log(jwtToken);
                        sessionStorage.setItem("jwt", jwtToken);
                        setLoggedinUser(email.current.value);
                    } 
                    
                    else
                    {
                        alert("Token failure!");
                        setLoggedinUser("");
                    }
                }else
                {
                    alert("Login error!")
                    setLoggedinUser("");
                }
            }).then(()=>{
                email.current.value="";
                password.current.value="";
                navigate("/");
            })
            .catch(error=>{
                alert("Login error!")
                setLoggedinUser("");
                console.log(error);
            })
        }
      }

    return (
        //  <div className="container"> 
        <div className="container"> 
        <h2>LOGIN</h2>
        <form className="Login" noValidate onSubmit={handleSubmit}>
       

            <div className="input-control1">
            {/* <label className="labelText">Email:</label> */}
            <input type="email" ref={email} name="email" required placeholder="Email" /><br/><br/>
            </div>

            <div className="input-control2">
            {/* <label className="labelText">Password:</label> */}
            <input type="password" ref={password} name="password" required placeholder="Password" /><br/><br/>
            </div>

            <div className="buttonlogin" >
            <input type="submit" value="Login"/>
            </div>
            
        <div className="reglink">
        <Link to={"/registertest"}>
          Create a new account.
        </Link>
        </div>
       


        </form>
         </div>
    )
}













// import React, {useRef} from "react";
// import { Link, useOutletContext } from "react-router-dom";
// import "../Styles/Login.css"
// import { useNavigate } from "react-router-dom";
// import axios from "axios";

// export default function Login(props){   
//     const navigate = useNavigate();
//     const email=useRef();
//     const password=useRef();
//     const [loggedInUser, setLoggedinUser] = useOutletContext();

//     const validateForm = () => {
//         let formValid = false;
//         if (email.current.validity.valueMissing
//             || password.current.validity.valueMissing){
//                 alert("Please fill in all text fields.");
//         }else if (email.current.validity.typeMismatch){
//             alert("Invalid e-mail address. Please enter your e-mail again.");
//         }else{
//             formValid = true;
//         }
//         return formValid;
//     }

//     const handleSubmit = (event) => {
//         event.preventDefault();

//         const dataLogin = {
//             username: email.current.value,
//             password: password.current.value
//         };

//         if(validateForm()){
//             axios({
//                 method: 'post',
//                 url: 'http://localhost:8080/login',
//                 data: dataLogin
//             })
//             .then(async response=>{
//                 if (response.status === 200){
//                     const jwtToken = response.headers.authorization.split(' ')[1]
//                     if (jwtToken !== null) {
//                         console.log(jwtToken);
//                         sessionStorage.setItem("jwt", jwtToken);
//                         //
//                         //sessionStorage.set
//                         sessionStorage.setItem("email", email.current.value); // Store email in session storage

//                         // Fetch user
//           const userDataResponse = await axios.get(`/user/findByEmail/${email.current.value}`, {
//             headers: {
//               Authorization: localStorage.getItem("jwt"),
//             },
//           });
 
//           if (userDataResponse.status === 200) {
//             const userData = userDataResponse.data;
//             setLoggedinUser(userData);
//             console.log("Logged in successfully. User data:", userData);
//             localStorage.setItem('userData', JSON.stringify(userData));
//             navigate("/projects");
//           } else {
//             alert("Failed to fetch user data");
//             setLoggedinUser("");
//           }
  
//                         navigate("/");
//                         //
//                         setLoggedinUser(email.current.value);
//                     } 
                    
//                     else
//                     {
//                         alert("Token failure!");
//                         setLoggedinUser("");
//                     }
//                 }else
//                 {
//                     alert("Login error!")
//                     setLoggedinUser("");
//                 }
//             }).then(()=>{
//                 email.current.value="";
//                 password.current.value="";
//                 navigate("/");
//             })
//             .catch(error=>{
//                 alert("Login error!")
//                 setLoggedinUser("");
//                 console.log(error);
//             })
//         }
//       }

//     return (
//         //  <div className="container"> 
//         <div className="container"> 
//         <h2>LOGIN</h2>
//         <form className="Login" noValidate onSubmit={handleSubmit}>
       

//             <div className="input-control1">
//             {/* <label className="labelText">Email:</label> */}
//             <input type="email" ref={email} name="email" required placeholder="Email" /><br/><br/>
//             </div>

//             <div className="input-control2">
//             {/* <label className="labelText">Password:</label> */}
//             <input type="password" ref={password} name="password" required placeholder="Password" /><br/><br/>
//             </div>

//             <div className="buttonlogin" >
//             <input type="submit" value="Login"/>
//             </div>
            
//         <div className="reglink">
//         <Link to={"/registertest"}>
//           Create a new account.
//         </Link>
//         </div>
       


//         </form>
//          </div>
//     )
// }
