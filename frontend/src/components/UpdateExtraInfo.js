import React, { useState } from "react";
import axios from "axios";
export default function UpdateExtraInfo() {
  const jwt = sessionStorage.getItem("jwt");
  const userData = JSON.parse(sessionStorage.getItem("userData"));
  const [formData, setFormData] = useState({
    course: "",
    societies: "",
    sports: "",
    hobbies: "",
    ethnicity: "",
    otherSocieties: "",
    otherHobbies: "",
    successMessage: "",
    errorMessage: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
      successMessage: "",
      errorMessage: "",
    }));
    console.log("Updated formData:", formData);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {  
      console.log(userData);
      const response = await axios.post(
        `http://localhost:8080/updateUserExtra/${userData.id}`,
        {
          ...formData,
          societies:
            formData.societies === "Other"
              ? formData.otherSocieties
              : formData.societies,
          sports:
            formData.sports === "Other"
              ? formData.otherSports
              : formData.sports,
          hobbies:
            formData.hobbies === "Other"
              ? formData.otherHobbies
              : formData.hobbies,
          user: userData,
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${jwt}`,
          },
        }
      );
      console.log("Response:", response.data);
      setFormData({
        course: "",
        hobbies: "",
        societies: "",
        sports: "",
        ethnicity: "",
        //
        otherSocieties: "",
        otherSports: "",
        otherHobbies: "",
        //
        successMessage: "User extra profile created successfully!",
        errorMessage: "",
      });
    } catch (error) {
      console.error("Error creating user extra profile:", error);
      setFormData((prevState) => ({
        ...prevState,
        errorMessage: "Failed to create user extra profile",
      }));
    }
  };



  return (
    <div>
      <h2>Extra Information</h2>
      {formData.successMessage && <p>{formData.successMessage}</p>}
      {formData.errorMessage && <p>{formData.errorMessage}</p>}
      <form onSubmit={handleSubmit}>
        <label>
          Course:
          <select name="course" value={formData.course} onChange={handleChange}>
            <option value="">Select</option>
            <option value="Arts and Humanities">Arts and Humanities</option>
            <option value="Biology">Biology</option>
            <option value="Business and Management">
              Business and Management
            </option>
            <option value="Chemistry">Chemistry</option>
            <option value="Combined Studies">Combined Studies</option>
            <option value="Computing and IT">Computing and IT</option>
            <option value="Counselling">Counselling</option>
            <option value="Creative Writing">Creative Writing</option>
            <option value="Criminology">Criminology</option>
            <option value="Design">Design</option>
            <option value="Early Years">Early Years</option>
            <option value="Economics">Economics</option>
            <option value="Education">Education</option>
            <option value="Engineering">Engineering</option>
            <option value="English">English</option>
            <option value="Environment">Environment</option>
            <option value="Geography">Geography</option>
            <option value="Health and Social Care">
              Health and Social Care
            </option>
            <option value="Health and Wellbeing">Health and Wellbeing</option>
            <option value="Health Sciences">Health Sciences</option>
            <option value="History">History</option>
            <option value="International Studies">International Studies</option>
            <option value="Languages">Languages</option>
            <option value="Law'">Law</option>
            <option value="Mathematics">Mathematics</option>
            <option value="Mental Health ">Mental Health </option>
            <option value="Music">Music</option>
            <option value="Nursing and Healthcare">
              Nursing and Healthcare{" "}
            </option>
            <option value="Philosophy">Philosophy</option>
            <option value="Physics ">Physics </option>
            <option value="Politics">Politics</option>
            <option value="Psychology">Psychology</option>
            <option value="Science">Science</option>
            <option value="Social Sciences">Social Sciences</option>
            <option value="Social Work">Social Work</option>
            <option value="Sport and Fitness">Sport and Fitness</option>
            <option value="Statistics">Statistics</option>
            <option value="Other">Other</option>
          </select>
          {formData.course === "Other" && (
            <input
              type="text"
              name="otherCourse"
              value={formData.otherCourse}
              onChange={handleChange}
              placeholder="Enter other course"
            />
          )}
        </label>
        <br />

        <label>
          Societies:
          {/* <select name="societies" value={formData.societies} onChange={handleCheckboxChange} multiple> */}
          {/* <select name="societies" value={formData.societies} onChange={(e) => handleCheckboxChange(e, "societies")} multiple> */}
          {/* <select name="Societies" value={formData.Societies} onChange={handleChange}> */}
          <select
            name="societies"
            value={formData.societies}
            onChange={handleChange}
          >
            <option value="">Select</option>
            <option value="Accounting">Accounting</option>
            <option value="Advocacy">Advocacy</option>
            <option value="African and Carabean (ACS)">ACS</option>
            <option value="AI">AI</option>
            <option value="Albanian">Albanian</option>
            <option value="Animation">Animation</option>
            <option value="Anime & Games">Anime & Games</option>
            <option value="Architecture">Architecture</option>
            <option value="Arts">Arts</option>
            <option value="Baking & Cooking">Baking & Cooking</option>
            <option value="Baltic">Baltic</option>
            <option value="Bangladesh">Bangladesh</option>
            <option value="Bar">Bar</option>
            <option value="Bengali">Bengali</option>
            <option value="Biomedical">Biomedical</option>
            <option value="Bollywood Dance">Bollywood Dance</option>
            <option value="Christian">Christian</option>
            <option value="Civil & Environmental Engineering Society ">
              Civil & Environmental Engineering Society{" "}
            </option>
            <option value="Commercial">Commercial</option>
            <option value="Computer and Technology ">
              Computer and Technology{" "}
            </option>
            <option value="Creative Writing">Creative Writing</option>
            <option value="Criminology">Criminology</option>
            <option value="Design">Design</option>
            <option value="Disabled Students'">Disabled Students'</option>
            <option value="Economics and Business">
              Economics and Business
            </option>
            <option value="Engineering ">Engineering </option>
            <option value="English Language">English Language</option>
            <option value="English Literature ">English Literature </option>
            <option value="Events">Events</option>
            <option value="Filipino ">Filipino </option>
            <option value="Formula 1 ">Formula 1 </option>
            <option value="Greek-Cypriot ">Greek-Cypriot </option>
            <option value="Hindu ">Hindu </option>
            <option value="History">History</option>
            <option value="Intercultural ">Intercultural </option>
            <option value="Islamic">Islamic</option>
            <option value="Italian">Italian</option>
            <option value="Law">Law</option>
            <option value="LGBTQ+">LGBTQ+</option>
            <option value="Marketing+">Marketing+</option>
            <option value="Middle East & North African +">
              Middle East & North African +
            </option>
            <option value="Music+">Music+</option>
            <option value="Nepali">Nepali+</option>
            <option value="Nigerian">Nigerian +</option>
            <option value="Pakistani +">Pakistani +</option>
            <option value="Performing Arts+">Performing Arts+</option>
            <option value="Persian +">Persian +</option>
            <option value="Photographers and Models +">
              Photographers and Models +
            </option>
            <option value="Ping Pong+">Ping Pong+</option>
            <option value="Psychology ">Psychology +</option>
            <option value="Sociology ">Sociology +</option>
            <option value="Sport">Sport+</option>
            <option value="Sri Lankan ">Sri Lankan +</option>
            <option value="Tamil">Tamil+</option>
            <option value="Tourism and Hospitality">
              Tourism and Hospitality+
            </option>
            <option value="Turkish">Turkish+</option>
            <option value="Ukrainian ">Ukrainian +</option>
            <option value="Nepali">Nepali+</option>
            <option value="Palestine">Palestine+</option>
            <option value="Women in STEM">Women in STEM+</option>
            <option value="Other">Other</option>
          </select>
          {/* {formData.societies.includes ('Other') && (
                        <input type="text" name="otherSocieties" value={formData.otherSocieties} onChange={handleChange} placeholder="Enter other society" />
                    )} */}
          {formData.societies === "Other" && (
            <input
              type="text"
              name="otherSocieties"
              value={formData.otherSocieties}
              onChange={handleChange}
              placeholder="Enter other society"
            />
          )}
        </label>
        <br />

        <label>
          Sports:
          {/* <select name="sports" value={formData.sports} onChange={handleCheckboxChange} multiple> */}
          {/* <select name="sports" value={formData.sports} onChange={(e) => handleCheckboxChange(e, "sports")} multiple> */}
          {/* <select name="Sports" value={formData.Sports} onChange={handleChange}> */}
          <select name="sports" value={formData.sports} onChange={handleChange}>
            <option value="">Select</option>
            <option value="American Football">American Football</option>
            <option value="Artistic Gymnastics">Artistic Gymnastics</option>
            <option value="Artistic Swimming">Artistic Swimming</option>
            <option value="Ballet">Ballet</option>
            <option value="Baseball">Baseball</option>
            <option value="Basketball">Basketball</option>
            <option value="Bodybuilding">Bodybuilding</option>
            <option value="Bowling">Bowling</option>
            <option value="Boxing">Boxing</option>
            <option value="Canoeing">Canoeing</option>
            <option value="Cheerleading">Cheerleading</option>
            <option value="Climbing">Climbing</option>
            <option value="Cricket">Cricket</option>
            <option value="CrossFit">CrossFit</option>
            <option value="Cycling">Cycling</option>
            <option value="Dance">Dance</option>
            <option value="Deadlifting">Deadlifting</option>
            <option value="Diving">Diving</option>
            <option value="Dodgeball">Dodgeball</option>
            <option value="Equestrian">Equestrian</option>
            <option value="Esports">Esports</option>
            <option value="Figure Skating">Figure Skating</option>
            <option value="Fishing">Fishing</option>
            <option value="Formula 1">Formula 1</option>
            <option value="Freediving">Freediving</option>
            <option value="Golf">Golf</option>
            <option value="Gymnastics">Gymnastics</option>
            <option value="Handball">Handball</option>
            <option value="High Jump">High Jump</option>
            <option value="Hockey">Hockey</option>
            <option value="Jiu-Jitsu">Jiu-Jitsu</option>
            <option value="Judo">Judo</option>
            <option value="Karate">Karate</option>
            <option value="Kayaking">Kayaking</option>
            <option value="Kick Ball">Kick Ball</option>
            <option value="Kickboxing">Kickboxing</option>
            <option value="Kung Fu">Kung Fu</option>
            <option value="Lacrosse">Lacrosse</option>
            <option value="MMA">MMA</option>
            <option value="Motorcycle Racing">Motorcycle Racing</option>
            <option value="Mountain Biking">Mountain Biking</option>
            <option value="Muay Thai">Muay Thai</option>
            <option value="Netball">Netball</option>
            <option value="PaddleBoarding">PaddleBoarding</option>
            <option value="Powerlifting">Powerlifting</option>
            <option value="Rodeo">Rodeo</option>
            <option value="Rugby">Rugby</option>
            <option value="Running">Running</option>
            <option value="Sailing">Sailing</option>
            <option value="Skateboarding">Skateboarding</option>
            <option value="Skiing">Skiing</option>
            <option value="Snowboarding">Snowboarding</option>
            <option value="Squash">Squash</option>
            <option value="Surfing">Surfing</option>
            <option value="Swimmming">Swimmming</option>
            <option value="Table Tennis">Table Tennis</option>
            <option value="Tennis">Tennis</option>
            <option value="Volleyball">Volleyball</option>
            <option value="Weightlifting">Weightlifting</option>
          </select>
          {/* {formData.sports.includes('Other') && (
                        <input type="text" name="otherSports" value={formData.otherSports} onChange={handleChange} placeholder="Enter other sports" />
                    )} */}
          {formData.sports === "Other" && (
            <input
              type="text"
              name="otherSports"
              value={formData.otherSports}
              onChange={handleChange}
              placeholder="Enter other sports"
            />
          )}
        </label>
        <br />

        <label>
          Hobbies:
          {/* <select name="hobbies" value={formData.hobbies} onChange={handleCheckboxChange} multiple> */}
          {/* <select name="hobbies" value={formData.hobbies} onChange={(e) => handleCheckboxChange(e, "hobbies")} multiple> */}
          {/* <select name="Hobbies" value={formData.Hobbies} onChange={handleChange}> */}
          <select
            name="hobbies"
            value={formData.hobbies}
            onChange={handleChange}
          >
            <option value="">Select</option>
            <option value="Astrology">Astrology</option>
            <option value="Acting">Acting</option>
            <option value="Animation">Animation</option>
            <option value="Axe Throwing">Axe</option>
            <option value="Art">Art</option>
            <option value="Board Games">Board Games</option>
            <option value="Badminton">Badminton</option>
            <option value="Backpacking">Backpacking</option>
            <option value="Barbecuing">Barbecuing</option>
            <option value="Boxing">Boxing</option>
            <option value="Bodybuilding">Bodybuilding</option>
            <option value="Baking">Baking</option>
            <option value="Basketball">Basketball</option>
            <option value="Blogging">Blogging</option>
            <option value="Bowling">Bowling</option>
            <option value="Beatboxing">Beatboxing</option>
            <option value="Candle Making">Candle Making</option>
            <option value="Canoeing">Canoeing</option>
            <option value="Chess">Chess</option>
            <option value="Coding">Coding</option>
            <option value="Coffee">Coffee</option>
            <option value="Comic Books">Comic Books</option>
            <option value="Crocheting">Crocheting</option>
            <option value="Cycling">Cycling</option>
            <option value="Camping">Camping</option>
            <option value="Dancing">Dancing</option>
            <option value="Drawing">Drawing</option>
            <option value="DJing">DJing</option>
            <option value="Dog Training">Dog Training</option>
            <option value="Drone Flying">Drone Flying</option>
            <option value="Drums">Drums</option>
            <option value="Equestrian">Equestrianism</option>
            <option value="Embroidery">Embroidery</option>
            <option value="Engraving">Engraving</option>
            <option value="Exercise">Exercise</option>
            <option value="Fishing">Fishing</option>
            <option value="Filmmaking">Filmmaking</option>
            <option value="Fencing">Fencing</option>
            <option value="Gaming">Gaming</option>
            <option value="Gardening">Gardening</option>
            <option value="Graphic Design">Graphic Design</option>
            <option value="Gymnastics">Gymnastics</option>
            <option value="Graffiti">Graffiti</option>
            <option value="Golf">Golf</option>
            <option value="Guitar">Guitar</option>
            <option value="Hula Hooping">Hula Hooping</option>
            <option value="Handball">Handball</option>
            <option value="Hammocking">Hammocking</option>
            <option value="Homebrewing Beer">Homebrewing Beer</option>
            <option value="Houseplants">Houseplants</option>
            <option value="Hiking">Hiking</option>
            <option value="Hunting">Hunting</option>
            <option value="Hockey">Hockey</option>
            <option value="Ice Skating">Ice Skating</option>
            <option value="Journaling">Journaling</option>
            <option value="Jewelry Making">Jewelry Making</option>
            <option value="Jet Skiing">jet ski</option>
            <option value="Jujitsu">Jujitsu</option>
            <option value="Judo">Judo</option>
            <option value="Karaoke">Karaoke</option>
            <option value="Karate">Karate</option>
            <option value="Knitting">Knitting</option>
            <option value="Kayaking">Kayaking</option>
            <option value="Kickboxing">Kickboxing</option>
            <option value="Kite Flying">Kite Flying</option>
            <option value="Lacrosse">Lacrosse</option>
            <option value="Languages">Languages</option>
            <option value="Live Streaming">Live Streaming</option>
            <option value="Listen to Music">Listen to Music</option>
            <option value="Lego Building">Lego Building</option>
            <option value="Magic">Magic</option>
            <option value="Martial Arts">Martial Arts</option>
            <option value="Make-Up">Make-Up</option>
            <option value="Meteorology">Meteorology</option>
            <option value="Metaverse">Metaverse</option>
            <option value="Museum">Museum</option>
            <option value="Motorcycles">Motorcycles</option>
            <option value="Mountaineering">JouMountaineeringrnaling</option>
            <option value="Music">Music</option>
            <option value="Nail Art">Nail Art</option>
            <option value="NFTs">NFTs</option>
            <option value="Origami">Origami</option>
            <option value="Online Games">Online Games</option>
            <option value="Opera">Opera</option>
            <option value="Painting">Painting</option>
            <option value="Photography">Photography</option>
            <option value="Photoshop">Photoshop</option>
            <option value="Poetry">Poetry</option>
            <option value="Pottery">Pottery</option>
            <option value="Pilates">Pilates</option>
            <option value="Pole Dancing">Pole Dancing</option>
            <option value="Podcasting">Podcasting</option>
            <option value="Poker">Poker</option>
            <option value="Power Lifting">Power Lifting</option>
            <option value="Quad Biking">Quad Biking</option>
            <option value="Rapping">Rapping</option>
            <option value="Reading">Reading</option>
            <option value="Rock Climbing">Rock Climbing</option>
            <option value="Roller Skating">Roller Skating</option>
            <option value="Rugby">Rugby</option>
            <option value="Saxophone">Saxophone</option>
            <option value="Sailing">Sailing</option>
            <option value="Scuba Diving">Scuba Diving</option>
            <option value="Sculpting">Sculpting</option>
            <option value="Sewing">Sewing</option>
            <option value="Singing">Singing</option>
            <option value="Songwriting">Songwriting</option>
            <option value="Storytelling">Storytelling</option>
            <option value="Swimming">Swimming</option>
            <option value="Shooting">Shooting</option>
            <option value="Skateboarding">Skateboarding</option>
            <option value="Skiing">Skiing</option>
            <option value="Skydiving">Skydiving</option>
            <option value="Sneakers">Sneaker</option>
            <option value="Snowboarding">Snowboarding</option>
            <option value="Surfing">Surfing</option>
            <option value="Tattooing">Tattooing</option>
            <option value="Thrifting">Thrifting</option>
            <option value="Tennis">Tennis</option>
            <option value="Travel">Travel</option>
            <option value="Upcycling">Upcycling</option>
            <option value="Video Games">Video Games</option>
            <option value="Volunteering">Videography</option>
            <option value="Watercolor Painting">Watercolor Painting</option>
            <option value="Weaving">Weaving</option>
            <option value="Web Development">Web Development</option>
            <option value="Weight Training">Weight Training</option>
            <option value="Wine">Wine</option>
            <option value="Woodworking">Woodworking</option>
            <option value="Walking"> Walking</option>
            <option value="Writing">Writing</option>
            <option value="Xbox">Xbox</option>
            <option value="Yo-yoing">Yo-yoing</option>
            <option value="Yoga">Yoga</option>
            <option value="Youtube">Youtube</option>
            <option value="Zumba">Zumba</option>
            <option value="Zip Lining">Zip Lining</option>
            <option value="Other">Other</option>
          </select>
          {/* {formData.hobbies.includes('Other') && (
                        <input type="text" name="otherHobbies" value={formData.otherHobbies} onChange={handleChange} placeholder="Enter other hobby" />
                    )} */}
          {formData.hobbies === "Other" && (
            <input
              type="text"
              name="otherHobbies"
              value={formData.otherHobbies}
              onChange={handleChange}
              placeholder="Enter other hobby"
            />
          )}
        </label>
        <br />

        <label>
          Ethnicity:
          <select
            name="ethnicity"
            value={formData.ethnicity}
            onChange={handleChange}
          >
            <option value="">Select</option>
            <option value="Asian, Asian British or Asian Welsh">
              Asian, Asian British or Asian Welsh
            </option>
            <option value="	Black, Black British, Black Welsh, Caribbean or African: African">
              Black, Black British, Black Welsh, Caribbean or African: African
            </option>
            <option value="	Black, Black British, Black Welsh, Caribbean or African: Carabbean">
              Black, Black British, Black Welsh, Caribbean or African: Carabbean
            </option>
            <option value="	Black, Black British, Black Welsh, Caribbean or African: Other">
              Black, Black British, Black Welsh, Caribbean or African: Other
            </option>
            <option value="	Mixed or Multiple ethnic groups: White and Asian">
              Mixed or Multiple ethnic groups: White and Asian
            </option>
            <option value="	Mixed or Multiple ethnic groups: White and Black African">
              Mixed or Multiple ethnic groups: White and Black
            </option>
            <option value="	Mixed or Multiple ethnic groups: Other Mixed or Multiple ethnic groups">
              Mixed or Multiple ethnic groups: Other Mixed or Multiple ethnic
              groups
            </option>
            <option value="	White: English, Welsh, Scottish, Northern Irish or British">
              White: English, Welsh, Scottish, Northern Irish or British
            </option>
            <option value="	White: Other White">White: Other White</option>
            <option value="	Other ethnic group: Any other ethnic group">
              {" "}
              Other ethnic group: Any other ethnic group
            </option>
            <option value="	Prefer not to disclose">
              Prefer not to disclose
            </option>
          </select>
        </label>
        <br />

        <button type="submit">Update</button>
      </form>
    </div>
  );
}
