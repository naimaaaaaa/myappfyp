import "./App.css";
import {Link, Outlet} from "react-router-dom"
import React, {useState} from "react"
import Header from "./components/Header"
import Sidebar from "./components/Sidebar"
import RLNav from "./components/RLNav"
import Nav from "./components/Nav"

export default function App() {
  const [loggedInUser, setLoggedinUser] = useState("");


//
//localstorage
const jwt = sessionStorage.getItem("jwt");
  var navbar;
  if (loggedInUser === "") {
    navbar = <RLNav />;
  } else {
    navbar = <Nav />;
  }
//

//     return (
//       <div className="App">
//         <Header/>
//         <nav className="App-nav">
//           <Link className="nav-link" to={'/'}>Home</Link>
//           <Link className="nav-link" to={'/register'}>Register</Link>
//           <Link className="nav-link" to={'/login'}>Login</Link>
//           <Link className="nav-link" to={'/help'}>Help</Link>
//         </nav>
//         <main className="App-main">
//           <Sidebar/>
//           <Outlet context={[loggedInUser,setLoggedinUser]}/>
//         </main>
//       </div>
//     );
// }
return (
  <div className="App">
    <Header />
    {navbar}
    <main className="App-main">
      <Outlet context={[loggedInUser, setLoggedinUser]} />
    </main>
    {/* <Footer /> */}
  </div>
);
}

