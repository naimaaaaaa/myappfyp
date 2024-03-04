
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
        // onConnected()
    }, []);

    const fetchUsername = async () => {
        try {
            const jwtToken = sessionStorage.getItem("jwt");
            const userEmail = sessionStorage.getItem("email");

            if (jwtToken && userEmail) {
                const response = await axios.get('http://localhost:8080/user/findByEmail', {
                    params: {
                        email: userEmail
                    }
                });
                const { data } = response;

                if (data && data.name) {
                    setUserData({ ...userData, name: data.name });
                    connect();
                } else {
                    console.error('Data or name is null or undefined');
                }
            } else {
                console.error('JWT token or email not found in session storage');
            }
        } catch (error) {
            console.error('Error fetching username:', error);
        }
    };

    // const fetchUsername = async () => {
    //     try {
    //         const userEmail = sessionStorage.getItem("email");
    //         const response = await axios.get('http://localhost:8080/user/findByEmail', {
    //             params: {
    //                 email: 'lina@gmail.com' // Change this to the actual email
    //             }
    //         });
    //         const { data } = response;
    //         //
    //         if (data && data.name) { // Check if data and name exist
    //             setUserData({ ...userData, name: data.name });
    //             connect();
    //         } else {
    //             console.error('Data or name is null or undefined');
    //             // Handle the case where data or name is null or undefined
    //         }

    //         setUserData({ ...userData, name: data.name });
    //         connect();
    //     } catch (error) {
    //         console.error('Error fetching username:', error);
    //     }
    // };
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
               //
            //    stomp.subscribe('/chatroom/public', onMessageReceived);
            //    stomp.subscribe('/user/' + userData.name + '/private', onPrivateMessage);
            //    userJoin();
               //
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
        const payloadData = JSON.parse(payload.body);
        setPublicChats(prevChats => [...prevChats, payloadData]);
    };
    // const onMessageReceived = (payload) => {
    //     console.log('Message received:', payload);
    //     const payloadData = JSON.parse(payload.body);
    //     switch(payloadData.status) {
    //         case "JOIN":
    //             if (!privateChats.get(payloadData.senderName)) {
    //                 privateChats.set(payloadData.senderName, []);
    //                 setPrivateChats(new Map(privateChats));
    //             }
    //             break;
    //         case "MESSAGE":
    //             publicChats.push(payloadData);
    //             setPublicChats([...publicChats]);
    //             break;
    //     }
    // }

    const onPrivateMessage = (payload) => {
        console.log('Private message received:', payload);
        const payloadData = JSON.parse(payload.body);
        const senderName = payloadData.senderName;
        const message = payloadData.message;
        setPrivateChats(prevChats => {
            const updatedChats = new Map(prevChats);
            if (updatedChats.has(senderName)) {
                updatedChats.get(senderName).push(message);
            } else {
                updatedChats.set(senderName, [message]);
            }
            return updatedChats;
        });
    };
    // const onPrivateMessage = (payload) => {
    //     console.log('Private message received:', payload);
    //     const payloadData = JSON.parse(payload.body);
    //     if (privateChats.get(payloadData.senderName)) {
    //         privateChats.get(payloadData.senderName).push(payloadData);
    //         setPrivateChats(new Map(privateChats));
    //     } else {
    //         const list = [];
    //         list.push(payloadData);
    //         privateChats.set(payloadData.senderName, list);
    //         setPrivateChats(new Map(privateChats));
    //     }
    // }
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
console.log('publicChats:', publicChats); // Added to log publicChats state
console.log('privateChats:', privateChats); // Added to log privateChats state

const truevar = useState(true)

console.log("Hello world", userData)

const fakePublicChat = [{chatSenderName: "Lina", }]

return (
    <div className="container">
                {/* {userData.connected ? */}
                {userData ?
            <div className="chat-box">
                <div className="member-list">
                    <ul>
                        <li onClick={()=>{setTab("CHATROOM")}} className={`member ${tab==="CHATROOM" && "active"}`}>Chatroom</li>
                        {[...privateChats.keys()].map((name,index)=>(
                            <li onClick={()=>{setTab(name)}} className={`member ${tab===name && "active"}`} key={index}>{name}</li>
                        ))}
                    </ul>
                </div>
                {tab==="CHATROOM" && <div className="chat-content">

                    {/* Show Messages Section */}
                    <ul className="chat-messages">
                        {/* Public Chats is not being set */}
                        {publicChats.map((chat,index)=>(
                            <li className={`message ${chat.senderName === userData.name && "self"}`} key={index}>
                                {chat.senderName !== userData.name && <div className="avatar">{chat.senderName}</div>}
                                <div className="message-data">{chat.message}</div>
                                {chat.senderName === userData.name && <div className="avatar self">{chat.senderName}</div>}
                            </li>
                        ))}
                    </ul>

                    {/* Send Message Button Container */}
                    <div className="send-message">
                        <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage} /> 
                        <button type="button" className="send-button" onClick={sendPublicValue}>send</button>
                    </div>
                </div>}
                {tab!=="CHATROOM" && <div className="chat-content">
                    <ul className="chat-messages">
                        {[...(privateChats.get(tab) || [])].map((chat,index)=>(
                            <li className={`message ${chat.senderName === userData.name && "self"}`} key={index}>
                                {chat.senderName !== userData.name && <div className="avatar">{chat.senderName}</div>}
                                <div className="message-data">{chat.message}</div>
                                {chat.senderName === userData.name && <div className="avatar self">{chat.senderName}</div>}
                            </li>
                        ))}
                    </ul>
                    <div className="send-message">
                        <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage} /> 
                        <button type="button" className="send-button" onClick={sendPrivateValue}>send</button>
                    </div>
                </div>}
            </div>
            : null
        }
    </div>
);


}
            
export default ChatRoom;
















// return (
    //     <div className="chat-room">
    //         <h2>Welcome, {userData.name}!</h2>
    //         <div className="chat-container">
    //             <div className="chat-sidebar">
    //                 <ul className="user-list">
    //                     {/* Display list of users */}
    //                     <li className={tab === "CHATROOM" ? "active" : ""} onClick={() => setActiveTab("CHATROOM")}>Chatroom</li>
    //                     {Array.from(privateChats.keys()).map((user, index) => (
    //                         <li key={index} className={tab === user ? "active" : ""} onClick={() => setActiveTab(user)}>{user}</li>
    //                     ))}
    //                 </ul>
    //             </div>
    //             <div className="chat-messages">
    //                 <ul>
    //                     {/* Display public or private messages based on active tab */}
    //                     {tab === "CHATROOM" && publicChats.map((chat, index) => (
    //                         <li key={index}><strong>{chat.senderName}:</strong> {chat.message}</li>
    //                     ))}
    //                     {tab !== "CHATROOM" && privateChats.get(tab).map((message, index) => (
    //                        <li key={index}><strong>{userData.username}:</strong> {message}</li>
    //                     ))}
    //                 </ul>
    //             </div>
    //         </div>
    //         <div className="chat-input">
    //             <input type="text" placeholder="Type your message..." value={userData.message} onChange={handleMessage} />
    //             <button onClick={tab === "CHATROOM" ? sendPublicValue : sendPrivateValue}>Send</button>
    //         </div>
    //     </div>
    // );



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



    // import React, { useEffect, useState } from 'react';
// import SockJS from 'sockjs-client';
// import Stomp from 'stompjs';
// import axios from 'axios';

// const ChatRoom = ({email}) => {
//     const [stompClient, setStompClient] = useState(null);
//     const [privateChats, setPrivateChats] = useState(new Map());     
//     const [publicChats, setPublicChats] = useState([]); 
//     const [activeTab, setActiveTab] = useState("CHATROOM");
//     const [userData, setUserData] = useState({ email, connected: false, message: "" });
//     const jwt = sessionStorage.getItem("jwt");

//     useEffect(() => {
//         fetchUsername();
//         connectToWebSocket();
//     }, []);

//     const fetchUsername = async () => {
//         try {
//             const response = await axios.get('http://localhost:8080/get-name');
//             const { name } = response.data;
//             setUserData({ ...userData, name });
//         } catch (error) {
//             console.error('Error fetching username:', error);
//         }
//     };

//     const connectToWebSocket = () => {
//         const socket = new SockJS('http://localhost:8080/ws');
//         const stomp = Stomp.over(socket);
//         stomp.connect({ Authorization: `Bearer ${jwt}` }, onConnected, onError);
//         setStompClient(stomp);
//     };

//     const onConnected = () => {
//         console.log('Connected!');
//         setUserData({...userData, connected: true });
//         if (stompClient) {
//             stompClient.subscribe('/chatroom/public', onMessageReceived);
//             stompClient.subscribe('/user/' + userData.name + '/private', onPrivateMessage);
//             userJoin();
//         } else {
//             console.error('Error: Stomp client is null');
//         }
//     }
//     ;

//     const onError = (error) => {
//         console.error('WebSocket connection error:', error);
//     };

//     const userJoin = () => {
//         console.log('User joined.');
//         const chatMessage = {
//             senderName: userData.name,
//             status: "JOIN"
//         };
//         stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
//     };

//     const onMessageReceived = (payload) => {
//         console.log('Public message received:', payload);
//         const payloadData = JSON.parse(payload.body);
//         switch(payloadData.status) {
//             case "JOIN":
//                 if (!privateChats.get(payloadData.senderName)) {
//                     privateChats.set(payloadData.senderName, []);
//                     setPrivateChats(new Map(privateChats));
//                 }
//                 break;
//             case "MESSAGE":
//                 setPublicChats(prevChats => [...prevChats, payloadData]);
//                 break;
//         }
//     };

//     const onPrivateMessage = (payload) => {
//         console.log('Private message received:', payload);
//         const payloadData = JSON.parse(payload.body);
//         const senderName = payloadData.senderName;
//         const message = payloadData.message;
//         if (privateChats.get(senderName)) {
//             privateChats.get(senderName).push(message);
//         } else {
//             privateChats.set(senderName, [message]);
//         }
//         setPrivateChats(new Map(privateChats));
//     };

//     const handleMessageChange = (event) => {
//         setUserData({...userData, message: event.target.value });
//     };

//     const sendMessage = () => {
//         if (stompClient && userData.message.trim() !== '') {
//             const chatMessage = {
//                 senderName: userData.name,
//                 message: userData.message,
//                 status: "MESSAGE"
//             };
//             console.log('Sending message:', chatMessage);
//             stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
//             setUserData({...userData, message: ""});
//         }
//     };

//     return (
//         <div className="chat-room">
//              <ChatRoom email={userEmail} />
//             <h2>Welcome, {userData.userEmail}!</h2>
//             <div className="chat-container">
//                 <div className="chat-sidebar">
//                     <ul className="user-list">
//                         <li className={activeTab === "CHATROOM" ? "active" : ""} onClick={() => setActiveTab("CHATROOM")}>Chatroom</li>
//                         {Array.from(privateChats.keys()).map((user, index) => (
//                             <li key={index} className={activeTab === user ? "active" : ""} onClick={() => setActiveTab(user)}>{user}</li>
//                         ))}
//                     </ul>
//                 </div>
//                 <div className="chat-messages">
//                     <ul>
//                         {activeTab === "CHATROOM" ? (
//                             publicChats.map((chat, index) => (
//                                 <li key={index}><strong>{chat.senderName}:</strong> {chat.message}</li>
//                             ))
//                         ) : (
//                             privateChats.get(activeTab).map((message, index) => (
//                                 <li key={index}><strong>{activeTab}:</strong> {message}</li>
//                             ))
//                         )}
//                     </ul>
//                 </div>
//             </div>
//             <div className="chat-input">
//                 <input type="text" placeholder="Type your message..." value={userData.message} onChange={handleMessageChange} />
//                 <button onClick={sendMessage}>Send</button>
//             </div>
//         </div>
//     );
// };

// export default ChatRoom;
