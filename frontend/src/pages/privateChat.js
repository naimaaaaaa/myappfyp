import React, { useEffect, useState } from 'react';
import { Link, useOutletContext, useParams } from "react-router-dom";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import axios from 'axios';
import moment from 'moment';
import UserMessage from '../components/userMessage.badge';
import "../Styles/login.css";

const PrivateChat = () => {
    const [stompClient, setStompClient] = useState(null);
    const [privateChats, setPrivateChats] = useState(new Map());
    const [message, setMessage] = useState('');
    const [selectedUser, setSelectedUser] = useState('');
    const [connectedUsers, setConnectedUsers] = useState([]);
    const [error, setError] = useState(null); // New state for error handling
    const [loggedInUser, setLoggedinUser] = useOutletContext();
    const [userDetails, setUserDetails] = useState({});
    const jwt = sessionStorage.getItem('jwt');

    useEffect(() => {
        const fetchData = async () => {
            console.log("Fetching user details...");
            try {
                console.log("naima", loggedInUser)
                // Fetch user details
                const userDetailsResponse = await axios.get("http://localhost:8080/userInfo/", {
                    params: {
                        email: loggedInUser
                    },
                    headers: {
                        "Content-Type": "multipart/form-data",
                        Authorization: `Bearer ${jwt}`
                    }
                });
                console.log("User details response:", userDetailsResponse.data);
                setUserDetails(userDetailsResponse.data);

                // Fetch connected users
                const connectionsResponse = await axios.get("http://localhost:8080/userInfo/connections", {
                    params: {
                        userId: userDetailsResponse.data.id
                    },
                    headers: {
                        "Content-Type": "multipart/form-data",
                        Authorization: `Bearer ${jwt}`
                    }
                });
                console.log("naima", connectionsResponse)
                setConnectedUsers(connectionsResponse.data);
            } catch (error) {
                console.error('Error fetching data:', error);
                setError('Error fetching data. Please try again.');
            }
        };

        fetchData();

        const socket = new SockJS('http://localhost:8080/ws');
        const client = Stomp.over(socket);
        client.connect({}, () => {
            client.subscribe(`/user/${loggedInUser}/private`, (message) => {
                const receivedMessage = JSON.parse(message.body);
                setPrivateChats(prevChats => new Map(prevChats.set(receivedMessage.msgId, receivedMessage)));
            });
        });
        setStompClient(client);

        return () => {
            if (stompClient) {
                stompClient.disconnect();
            }
        };
    }, [loggedInUser, jwt]);

    const sendMessage = async () => {
        try {
            if (message.trim() && selectedUser && stompClient) {
                const chatMessage = {
                    senderId: loggedInUser.id,
                    receiverId: selectedUser,
                    message: message,
                    date: moment().toDate(),
                    status: 'Message',
                };
                // Send the message via WebSocket
                stompClient.send('/app/private-message', {}, JSON.stringify(chatMessage));
                // Add the sent message to the private chats
                setPrivateChats(prevChats => new Map(prevChats.set(moment().valueOf(), chatMessage)));
                setMessage('');
            }
        } catch (error) {
            console.error('Error sending message:', error);
            setError('Error sending message. Please try again.');
        }
    };

    console.log('Private Chat: ', privateChats);

    return (
        <div class="login-container">
        <div className="card">
            {error && <div className="error">{error}</div>}
            <div className="chat-container">
                <div className="chat-messages">
                    {/* Your existing code for displaying messages... */}
                </div>
                <div className="private-chat-input">
                    <select value={selectedUser} onChange={(e) => setSelectedUser(e.target.value)}>
                        <option value="" disabled>Select user to chat with</option>
                        {/* Modified code to display connected users */}
                        {connectedUsers.map((user) => (
                            <option key={user.id} value={user.id}>{user.name}</option>
                        ))}
                    </select>
                </div>
            </div>
            <div className="send-message">
                <input className="input" type="text" placeholder="Send message" value={message} onChange={(e) => setMessage(e.target.value)} />
                <button className="primary-button" onClick={() => sendMessage()}>Send</button>
            </div>
        </div>
        </div>
    );
}

export default PrivateChat;



// import React, { useEffect, useState } from 'react';
// import SockJS from 'sockjs-client';
// import Stomp from 'stompjs';
// import axios from 'axios';
// import moment from 'moment';
// import UserMessage from '../components/userMessage.badge';

// const PrivateChat = ({ loggedInUser }) => {
//     const [stompClient, setStompClient] = useState(null);
//     const [privateChats, setPrivateChats] = useState(new Map());
//     const [message, setMessage] = useState('');
//     const [selectedUser, setSelectedUser] = useState('');
//     const [users, setUsers] = useState([]);
//     const jwt = sessionStorage.getItem('jwt');

//     useEffect(() => {
//         const fetchUsers = async () => {
//             try {
//                 const response = await axios.get('http://localhost:8080/users', {
//                     headers: {
//                         Authorization: `Bearer ${jwt}`
//                     }
//                 });
//                 setUsers(response.data);
//             } catch (error) {
//                 console.error('Error fetching users:', error);
//             }
//         };

//         fetchUsers();

//         const socket = new SockJS('http://localhost:8080/ws');
//         const client = Stomp.over(socket);
//         client.connect({}, () => {
//             client.subscribe(`/user/${loggedInUser}/private`, (message) => {
//                 const receivedMessage = JSON.parse(message.body);
//                 setPrivateChats(prevChats => new Map(prevChats.set(receivedMessage.msgId, receivedMessage)));
//             });
//         });
//         setStompClient(client);

//         return () => {
//             if (stompClient) {
//                 stompClient.disconnect();
//             }
//         };
//     }, [loggedInUser, jwt]);

//     const sendMessage = () => {
//         if (message.trim() && selectedUser && stompClient) {
//             const chatMessage = {
//                 senderName: loggedInUser,
//                 receiverName: selectedUser,
//                 message: message,
//                 date: moment().toDate(),
//                 status: 'Message',
//             };
//             setPrivateChats(prevChats => new Map(prevChats.set(moment().valueOf(), chatMessage))); // Add the sent message to privateChats
//             stompClient.send('/app/private-message', {}, JSON.stringify(chatMessage));
//             setMessage('');
//         }
//     };

//     console.log("Private Chat: ", privateChats);

//     return (
//         <div className="card">
//             <div className="chat-container">
//                 <div className="chat-messages">
//                     {[...privateChats.values()].map((pc) => (
//                         <UserMessage
//                             key={moment(pc.date).valueOf()} // Use a unique key based on the message date
//                             message={pc.message}
//                             senderName={pc.senderName}
//                             date={pc.date}
//                         />
//                     ))}
//                 </div>
//                 <div className="private-chat-input">
//                     <select value={selectedUser} onChange={(e) => setSelectedUser(e.target.value)}>
//                         <option value="" disabled>Select user to chat with</option>
//                         {users.map((user) => (
//                             <option key={user.id} value={user.name}>{user.name}</option>
//                         ))}
//                     </select>
//                 </div>
//             </div>
//             <div className="send-message">
//                 <input className="input" type="text" placeholder="Send message" value={message} onChange={(e) => setMessage(e.target.value)} />
//                 <button className="primary-button" onClick={() => sendMessage()}>Send</button>
//             </div>
//         </div>
//     );
// }

// export default PrivateChat;
