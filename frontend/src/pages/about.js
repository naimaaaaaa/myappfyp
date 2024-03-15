import React from 'react';
import "../Styles/login.css";
import "../Styles/about.css"; // Import CSS file for additional styling

const About = () => {
  return (
    <div className="login-container">
      <div className="about-container">
        <div className="about-box">
          <div className="about-content">
            <h2>About Us</h2>
            <p>Welcome to Student Space! ... At Student Space, we encourage healthy connections between university students.
              We are dedicated to providing a safe space for university students to connect based on similarities ...</p>
            <h3>Our Mission</h3>
            <p>Our mission is to provide students a platform to support each other and share experiences.</p>
            <h3>Contact Us</h3>
            <p>[2134714@brunel.ac.uk]</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default About;
