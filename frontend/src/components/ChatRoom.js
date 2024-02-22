// import React, { useRef, useState, useEffect} from "react";
// import {over} from 'stompjs';
// import sockJS from 'sockjs-client';
// import { useOutletContext } from "react-router-dom";
// import SockJS from "sockjs-client";
// import axios from 'axios';












import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import axios from 'axios';

const ChatRoom = ({ name }) => {
    const [stompClient, setStompClient] = useState(null);
    const [privateChats, setPrivateChats] = useState(new Map());     
    const [publicChats, setPublicChats] = useState([]); 
    const [tab, setTab] = useState("CHATROOM");
    const [userData, setUserData] = useState({ name, connected: false, message: '' });
    const jwt = sessionStorage.getItem("jwt");// Define jwt variable



    useEffect(() => {
        // Fetch user's name from the database
        fetchUsername();
    }, []);

    const fetchUsername = async () => {
        try {
          
            const response = await axios.get('http://localhost:8080/get-name'); //Replace with your endpoint
            const { name } = response.data;
            setUserData({ ...userData, name });
            connect();
        } catch (error) {
            console.error('Error fetching username:', error);
        }
    };


    // const connect = () => {
    //     console.log('Connecting...');
    //     const socket = new SockJS('http://localhost:8080/ws');
    //     const stomp = Stomp.over(socket);
    //     stomp.connect(
    //         {Authorization: `Bearer ${jwt}`}, 
    //         onConnected, 
    //         onError);
    //     setStompClient(stomp);
    // }

    
    const connect = () => {
        console.log('Connecting...');
        const socket = new SockJS('http://localhost:8080/ws');
        const stomp = Stomp.over(socket);
        stomp.connect(
            {Authorization: `Bearer ${jwt}`}, 
            () => {
                console.log('Connected');
                // Send a message after connecting
                stomp.send('/app/message', {}, JSON.stringify({content: 'hi'}));
            }, 
            onError
        );
        setStompClient(stomp);
    };

    
    const onConnected = () => {
        console.log('Connected!');
        setUserData({...userData, connected: true });
        stompClient.subscribe('/chatroom/public', onMessageReceived);
        stompClient.subscribe('/user/' + userData.name + '/private', onPrivateMessage);
        userJoin();
    }

       // Handle connection error
       const onError = (error) => {
        console.error('Error:', error);
    };


     // Handle user join event
    const userJoin = () => {
        console.log('User joined.');
        const chatMessage = {
            senderName: userData.name,
            status: "JOIN"
        };
        stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
    }

     // Handle public message received
    const onMessageReceived = (payload) => {
        console.log('Message received:', payload);
        const payloadData = JSON.parse(payload.body);
        switch(payloadData.status) {
            case "JOIN":
                if (!privateChats.get(payloadData.senderName)) {
                    privateChats.set(payloadData.senderName, []);
                    setPrivateChats(new Map(privateChats));
                }
                break;
            case "MESSAGE":
                publicChats.push(payloadData);
                setPublicChats([...publicChats]);
                break;
        }
    }
    
    const onPrivateMessage = (payload) => {
        console.log('Private message received:', payload);
        const payloadData = JSON.parse(payload.body);
        if (privateChats.get(payloadData.senderName)) {
            privateChats.get(payloadData.senderName).push(payloadData);
            setPrivateChats(new Map(privateChats));
        } else {
            const list = [];
            list.push(payloadData);
            privateChats.set(payloadData.senderName, list);
            setPrivateChats(new Map(privateChats));
        }
    }

    const oError = (err) => {
        console.log('Error:', err);
        console.log(err);
    }

    const handleMessage = (event) => {
        const { value } = event.target;
        setUserData({...userData, message: value });
    }

    const sendPublicValue = () => {
        if (stompClient) {
            const chatMessage = {
                senderName: userData.name,
                message: userData.message,
                status: "MESSAGE"
            };
            console.log('Sending message:', chatMessage);
            stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
            setUserData({...userData, message: ""});
        }
    }

    const sendPrivateValue = () => {
        if (stompClient) {
            const chatMessage = {
                senderName: userData.name,
                receiverName: tab,
                message: userData.message,
                status: "MESSAGE"
            };
          
            if (userData.name !== tab) {
                privateChats.get(tab).push(chatMessage);
                setPrivateChats(new Map(privateChats));
            }
            console.log('Sending private message:', chatMessage);
            stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
            setUserData({...userData, message: ""});
        }
    }

    const handleUsername = (event) => {
        const { value } = event.target;
        setUserData({...userData, name: value });
    }

   // Set active tab
   const setActiveTab = (tabName) => {
    setTab(tabName);
};

    return (
        <div className="chat-room">
            <h2>Welcome, {userData.name}!</h2>
            <div className="chat-container">
                <div className="chat-sidebar">
                    <ul className="user-list">
                        {/* Display list of users */}
                        <li className={tab === "CHATROOM" ? "active" : ""} onClick={() => setActiveTab("CHATROOM")}>Chatroom</li>
                        {Array.from(privateChats.keys()).map((user, index) => (
                            <li key={index} className={tab === user ? "active" : ""} onClick={() => setActiveTab(user)}>{user}</li>
                        ))}
                    </ul>
                </div>
                <div className="chat-messages">
                    <ul>
                        {/* Display public or private messages based on active tab */}
                        {tab === "CHATROOM" && publicChats.map((chat, index) => (
                            <li key={index}><strong>{chat.senderName}:</strong> {chat.message}</li>
                        ))}
                        {tab !== "CHATROOM" && privateChats.get(tab).map((message, index) => (
                            <li key={index}><strong>{userData.username}:</strong> {message}</li>
                        ))}
                    </ul>
                </div>
            </div>
            <div className="chat-input">
                <input type="text" placeholder="Type your message..." value={userData.message} onChange={handleMessage} />
                <button onClick={tab === "CHATROOM" ? sendPublicValue : sendPrivateValue}>Send</button>
            </div>
        </div>
    );
};

export default ChatRoom;



















// import React, { useEffect, useState } from 'react'
// //import {over} from 'stompjs';
// import SockJS from 'sockjs-client';
// import Stomp from 'stompjs';


// //var stompClient =null;
// const ChatRoom = () => {
//     let stompClient=null;
//     const [privateChats, setPrivateChats] = useState(new Map());     
//     const [publicChats, setPublicChats] = useState([]); 
//     const [tab,setTab] =useState("CHATROOM");
//     const [userData, setUserData] = useState({
//         username: '',
//         receivername: '',
//         connected: false,
//         message: ''
//       });


//     useEffect(() => {
//       console.log(userData);
//     }, [userData]);

//     const connect =()=>{
//         console.log('Connecting...');
//         let Sock = new SockJS('http://localhost:8080/ws');
//         let stompClient = Stomp.over(Sock);
//         stompClient.connect({},onConnected, onError);
//     }

//     const onConnected = () => {
//         console.log('Connected!');
//         setUserData({...userData,"connected": true});
//         stompClient.subscribe('/chatroom/public', onMessageReceived);
//         stompClient.subscribe('/user/'+userData.username+'/private', onPrivateMessage);
//         userJoin();
//     }

//     const userJoin=()=>{
//         console.log('User joined.');
//           var chatMessage = {
//             senderName: userData.username,
//             status:"JOIN"
//           };
//           stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
//     }

//     const onMessageReceived = (payload)=>{
//         console.log('Message received:', payload);
//         var payloadData = JSON.parse(payload.body);
//         switch(payloadData.status){
//             case "JOIN":
//                 if(!privateChats.get(payloadData.senderName)){
//                     privateChats.set(payloadData.senderName,[]);
//                     setPrivateChats(new Map(privateChats));
//                 }
//                 break;
//             case "MESSAGE":
//                 publicChats.push(payloadData);
//                 setPublicChats([...publicChats]);
//                 break;
//         }
//     }
    
//     const onPrivateMessage = (payload)=>{
//         console.log('Private message received:', payload);
//         var payloadData = JSON.parse(payload.body);
//         if(privateChats.get(payloadData.senderName)){
//             privateChats.get(payloadData.senderName).push(payloadData);
//             setPrivateChats(new Map(privateChats));
//         }else{
//             let list =[];
//             list.push(payloadData);
//             privateChats.set(payloadData.senderName,list);
//             setPrivateChats(new Map(privateChats));
//         }
//     }

//     const onError = (err) => {
//         console.log('Error:', err);
//         console.log(err);
        
//     }

//     const handleMessage =(event)=>{
//         const {value}=event.target;
//         setUserData({...userData,"message": value});
//     }


//     const sendValue=()=>{
//             if (stompClient) {
//               var chatMessage = {
//                 senderName: userData.username,
//                 message: userData.message,
//                 status:"MESSAGE"
//               };
//               console.log('Sending message:', chatMessage);
//               stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
//               setUserData({...userData,"message": ""});
//             }
//     }

//     const sendPrivateValue=()=>{
//         if (stompClient) {
//           var chatMessage = {
//             senderName: userData.username,
//             receiverName:tab,
//             message: userData.message,
//             status:"MESSAGE"
//           };
          
//           if(userData.username !== tab){
//             privateChats.get(tab).push(chatMessage);
//             setPrivateChats(new Map(privateChats));
//           }
//           console.log('Sending private message:', chatMessage);
//           stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
//           setUserData({...userData,"message": ""});
//         }
//     }

//     const handleUsername=(event)=>{
//         const {value}=event.target;
//         setUserData({...userData,"username": value});
//     }

//     const registerUser=()=>{
//         connect();
//     }
//     return (
//     <div className="container">
//         {userData.connected?
//         <div className="chat-box">
//             <div className="member-list">
//                 <ul>
//                     <li onClick={()=>{setTab("CHATROOM")}} className={`member ${tab==="CHATROOM" && "active"}`}>Chatroom</li>
//                     {[...privateChats.keys()].map((name,index)=>(
//                         <li onClick={()=>{setTab(name)}} className={`member ${tab===name && "active"}`} key={index}>{name}</li>
//                     ))}
//                 </ul>
//             </div>
//             {tab==="CHATROOM" && <div className="chat-content">
//                 <ul className="chat-messages">
//                     {publicChats.map((chat,index)=>(
//                         <li className={`message ${chat.senderName === userData.username && "self"}`} key={index}>
//                             {chat.senderName !== userData.username && <div className="avatar">{chat.senderName}</div>}
//                             <div className="message-data">{chat.message}</div>
//                             {chat.senderName === userData.username && <div className="avatar self">{chat.senderName}</div>}
//                         </li>
//                     ))}
//                 </ul>

//                 <div className="send-message">
//                     <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage} /> 
//                     <button type="button" className="send-button" onClick={sendValue}>send</button>
//                 </div>
//             </div>}
//             {tab!=="CHATROOM" && <div className="chat-content">
//                 <ul className="chat-messages">
//                     {[...privateChats.get(tab)].map((chat,index)=>(
//                         <li className={`message ${chat.senderName === userData.username && "self"}`} key={index}>
//                             {chat.senderName !== userData.username && <div className="avatar">{chat.senderName}</div>}
//                             <div className="message-data">{chat.message}</div>
//                             {chat.senderName === userData.username && <div className="avatar self">{chat.senderName}</div>}
//                         </li>
//                     ))}
//                 </ul>

//                 <div className="send-message">
//                     <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage} /> 
//                     <button type="button" className="send-button" onClick={sendPrivateValue}>send</button>
//                 </div>
//             </div>}
//         </div>
//         :
//         <div className="register">
//             {/* <input
//                 id="user-name"
//                 placeholder="Enter your name"
//                 name="userName"
//                 value={userData.username}
//                 onChange={handleUsername}
//                 margin="normal"
//               />
//               <button type="button" onClick={registerUser}>
//                     connect
//               </button>  */}
//         </div>}
//     </div>
//     )
// }

// export default ChatRoom







