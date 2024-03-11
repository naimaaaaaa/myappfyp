import { Link, Outlet, useNavigate } from "react-router-dom";
import "../Styles/nav.css";
import { useState } from "react";

export default function Nav() {
  const [aboutBool, setAboutBool] = useState();
  const activate_about = () => {
    setAboutBool(true);
  };
  const deactivate_about = () => {
    setAboutBool(false);
  };

  const navigate = useNavigate;

  const handleLogOut = async () => {
    // navigate("/");
    sessionStorage.clear("email")
    sessionStorage.clear("jwt")
    window.location.reload()
};

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

        {/* <Link className="nav-link" to={"/ExtraInfo"}>
          ExtraInfo
        </Link> */}
        {/* <Link className="nav-link" to={"/UserDetails"}>
         Settings
        </Link>  */}
      </nav>
      <div className="about-drop-down" onMouseLeave={deactivate_about}>
        <div
          className="nav-link"
          id="about-button"
          onMouseOver={activate_about}
        >
          <p>About</p>
        </div>
        {aboutBool}
      </div>
      <Link className="nav-link" onClick={handleLogOut}>
        Log Out
      </Link>
    </div>
  );
}
