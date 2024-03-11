import { Link, Outlet } from "react-router-dom";
import "../Styles/nav.css";
import { useState } from "react";

export default function RLNav(){
    const [aboutBool, setAboutBool] = useState();
    const activate_about = () => {
      setAboutBool(true);
    }
    const deactivate_about = () => {
      setAboutBool(false);
    }

    return (
        <div className="Nav-Div">
          <nav>
            <Link className="nav-link" to={"/"}>
              Home
            </Link>
            <Link className="nav-link" to={"/login"}>
              Login
            </Link>
            <Link className="nav-link" to={"/register"}>
              Register
            </Link>
          </nav>
          <div className="about-drop-down" onMouseLeave={deactivate_about}>
        <div className="nav-link" id="about-button" onMouseOver={activate_about}>
          <p>About</p>
        </div>
        {aboutBool 
        //&& (
        //   <div className="about-details">
        //     <Link className="nav-link" to={"/aboutCollect"}>Collect Info</Link>
        //     <Link className="nav-link">Forum Info</Link>
        //     <Link className="nav-link">Quest Info</Link>
            
        //   </div>
       // )
    }        
      </div>
        </div>
      );
}