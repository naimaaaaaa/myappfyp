import React, { useEffect, useState } from 'react';
import { useOutletContext } from "react-router-dom";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import axios from 'axios';
import moment from 'moment';
import UserMessage from '../components/userMessage.badge';

// const PrivateChat = ({ loggedInUser }) => {
const PrivateChat = ({ name }) => {

    const [stompClient, setStompClient] = useState(null);
    const [selectedUser, setSelectedUser] = useState(null);
    const [message, setMessage] = useState('');
    const [users, setUsers] = useState([]);
    const [userName, setUserName] = useState('');
    const [privateChats, setPrivateChats] = useState([]);
    const [tab, setTab] = useState("privateChats");
    const [loggedInUser, setLoggedinUser] = useOutletContext();

    const jwt = sessionStorage.getItem('jwt');

    useEffect(() => {
        const fetchUsername = async () => {
            try {
                const response = await axios.get('http://localhost:8080/userInfo/', {
                    params: {
                        email: loggedInUser
                    },
                    headers: {
                        "Content-Type": "multipart/form-data",
                        Authorization: `Bearer ${jwt}`
                    }
                })
                const userDataFromResponse = response.data;
                setUserName(userDataFromResponse.name);
            } catch (error) {
                console.error('Error fetching username:', error);
            }
        };
        fetchUsername(); // Fetch the username when the component mounts


        const fetchUsers = async () => {
            try {
                const response = await axios.get('http://localhost:8080/users', {
                    headers: {
                        Authorization: `Bearer ${jwt}`
                    }
                });
                setUsers(response.data);
            } catch (error) {
                console.error('Error fetching users:', error);
            }
        };

        fetchUsers();

        const socket = new SockJS('http://localhost:8080/ws');
        const client = Stomp.over(socket);
        client.connect({}, () => {
           // client.subscribe(`/user/${loggedInUser}/private`, (message) => {
            client.subscribe('/private-message', (message) => {

                const receivedMessage = JSON.parse(message.body);
                const sender = receivedMessage.senderName;
                setPrivateChats((prevChats) => {
                    const updatedChats = { ...prevChats };
                    updatedChats[sender] = [...(updatedChats[sender] || []), receivedMessage];
                    return updatedChats;
                });
            });
        });
        setStompClient(client);

        return () => {
            if (stompClient) {
                stompClient.disconnect();
            }
        };
    }, [userName, jwt]);

    const sendMessage = () => {
        if (message.trim() && stompClient && selectedUser) {
            const chatMessage = {
                senderName: userName,
                receiverName: selectedUser,
                message: message,
                date: moment().toDate(),
                status: 'Message',
            };
            stompClient.send('/app/private-message', {}, JSON.stringify(chatMessage));
            setPrivateChats((prevChats) => {
                const updatedChats = { ...prevChats };
                updatedChats[selectedUser] = [...(updatedChats[selectedUser] || []), chatMessage];
                return updatedChats;
            });
            setMessage('');
        }
    };

    const renderChatMessages = () => {
        if (selectedUser && privateChats[selectedUser]) {
            return privateChats[selectedUser].map((pc, index) => (
                <UserMessage
                    key={index}
                    message={pc.message}
                    senderName={pc.senderName}
                    date={pc.date}
                />
            ));
        }
        return null;
    };

    return (
        
         tab === 'privateChats' && ( 
            
        <div className="private-chat-container">
            
            <div className="user-list">
                <h2>Select User</h2>
                <select value={selectedUser} onChange={(e) => setSelectedUser(e.target.value)}>
                    <option value="" disabled>Select user to chat with</option>
                    {users.map((user) => (
                        <option key={user.id} value={user.name}>
                            {user.name}
                        </option>
                    ))}
                </select>
            </div>
            
            
            {/* tab === 'privateChats' && ( */}
            <div className="chat-window">
            <div class="card">
            <div className="chat-container">
                {selectedUser && (
                    <>
                        <h2>Chat with {selectedUser}</h2>
                        {/* <div className="chat-messages">

                            {renderChatMessages()}
                        </div>

                        <div className="private-chat-input">
                            <input
                                type="text"
                                placeholder="Type your message..."
                                message={message}
                                onChange={(e) => setMessage(e.target.value)}
                            />
                            <button onClick={sendMessage}>Send</button>
                        </div> */}
                            <div className="chat-messages">
                                {privateChats &&
                                    privateChats.map((pc) => (
                                        <UserMessage
                                            key={pc.msgId}
                                            message={pc.message}
                                            senderName={pc.senderName}
                                            date={pc.date}
                                        />
                                    ))}
                            </div>
                    </>
                )}
                 </div>
                </div>
            )
            </div>
        </div>
         )
    );
    
};

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
