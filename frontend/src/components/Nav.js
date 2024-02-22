import { Link, Outlet } from "react-router-dom";
import "../Styles/Nav.css";
import { useState } from "react";



export default function Nav() {
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
        
        <Link className="nav-link" to={"/ChatRoom"}>
          ChatRoom
        </Link>

        <Link className="nav-link" to={"/Profile"}>
          Profile
        </Link>

        <Link className="nav-link" to={"/ExtraInfo"}>
          ExtraInfo
        </Link>
        {/* <Link className="nav-link" to={"/UserDetails"}>
         Settings
        </Link>  */}

      </nav>
      <div className="about-drop-down" onMouseLeave={deactivate_about}>
        <div className="nav-link" id="about-button" onMouseOver={activate_about}>
          <p>About</p>
        </div>
        {aboutBool
        // && (
        //   <div className="about-details">
        //     <Link className="nav-link" to={"/aboutCollect"}>Collect Info</Link>
        //     <Link className="nav-link">Forum Info</Link>
        //     <Link className="nav-link">Quest Info</Link>
            
        //   </div>
        // )
    }        
      </div>
      <Link className="nav-link" to={"/logout"}>
        Log Out
      </Link>
    </div>
  );
}
