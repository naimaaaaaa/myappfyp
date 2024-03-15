import {Routes, Route, BrowserRouter as Router} from "react-router-dom"
import App from "./App";
import Registration from "./pages/registration";
import Home from "./pages/home"
import Login from "./pages/login"
import Help from "./pages/help"
import ChatRoom from "./pages/chatRoom"
import Profile from "./pages/profile"
import ExtraInfo from "./pages/extraInfo";
import RegistrationPartTwo from "./pages/registrationPartTwo";
import PrivateChat from "./pages/privateChat";
import UserConnections from "./components/UserConnections";
import MakeConnection from "./pages/MakeConnection";
import ViewConnection from "./pages/ViewConnection";
import About from "./pages/about";

export default function AppRouter(){
  return (
  <Router>
    <Routes>
        <Route path={'/'} element={<App/>}>
          <Route index element={<Home />} />
          <Route path={'/register'} element={<Registration/>}/>
          <Route path={'/register/part-two'} element={<RegistrationPartTwo/>}/>
          <Route path={'/login'} element={<Login />}/>
          <Route path={'/help'} element={<Help />}/>
          <Route path={'/chatroom'} element={<ChatRoom />}/>
          <Route path={'/Profile'}  element={<Profile />} />
          <Route path={'/ExtraInfo'} element={<ExtraInfo />}/>
          <Route path={'/privateChat'} element={<PrivateChat />}/>
          <Route path={'/UserConnections'} element={<UserConnections />}/>
          <Route path={'/MakeConnection'} element={<MakeConnection />}/>
          <Route path={'/ViewConnection'} element={<ViewConnection />}/>
          <Route path={'/about'} element={<About />}/>

        </Route>
      </Routes>
    </Router>
  )
}