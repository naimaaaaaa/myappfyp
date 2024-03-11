
// import React, {useEffect, useState} from "react";
// import { useOutletContext } from "react-router-dom";
// import axios from "axios";

// export default function Home(){
//     const [loggedInUser, setLoggedinUser] = useOutletContext();
//     const [userdata, setUserdata] = useState("");

//     useEffect(()=>{
//         if(loggedInUser!==""){
//             const jwt = sessionStorage.getItem('jwt');
//             console.log(jwt);
//             axios({
//                 method: 'get',
//                 url: 'http://localhost:8080/user/findByEmail',
//                 params: {email: loggedInUser},
//                 headers: {"Authorization" : `Bearer ${jwt}`}
//             }).then((response) => {
//                 if (response.status === 200){
//                     console.log(response.data);
//                     console.log(response.data.userType);
//                     setUserdata(response.data.userType);
//                 }
//             }).catch(err => {
//                 console.log(err.response);
//                 setUserdata("Data failure");
//             })
//         }
//     },[loggedInUser]);

//     if (loggedInUser==="") {
//       return <div><p>Hello guest user</p></div>;
//     } else{
//       return <div><p>Hello, you are registered as {userdata} in our system. </p></div>;
//     }
// }

import React from "react";

import "../Styles/home.css";


export default function Home(){

    return (
           <div className='main'>
          
           <div className="content">
               <h1>Welcome to Student Safe Space!</h1>
               <h2>Where we encourage peer support and easy new connections</h2>
           </div>
       </div>
        );
}