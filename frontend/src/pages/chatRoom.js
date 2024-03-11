import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { useOutletContext } from "react-router-dom";
import axios from 'axios';
import "../Styles/chatRoom.css"
import moment from "moment"
import UserMessage from '../components/userMessage.badge';
import { fakeMessages, topics, fakeUserNames } from '../assets/registrationData';

const ChatRoom = ({ name }) => {
    const [stompClient, setStompClient] = useState(null);
    const [privateChats, setPrivateChats] = useState(new Map());
    const [publicChats, setPublicChats] = useState([]);
    const [tab, setTab] = useState("publicChats");
    const [userName, setUserName] = useState('');
    const [userData, setUserData] = useState({});
    const [message, setMessage] = useState('');
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

        const socket = new SockJS('http://localhost:8080/ws');
        const client = Stomp.over(socket);
        client.connect(
            {},
            () => {
                client.subscribe('/chatroom/public', (message) => {
                    const receivedMessage = JSON.parse(message.body);
                    setPublicChats(prevChats => {
                        const existingMessage = prevChats.find(chat => chat.msgId === receivedMessage.msgId);
                        if (existingMessage) {
                            return prevChats;
                        } else {
                            return [...prevChats, receivedMessage];
                        }
                    });
                })
            }
        );
        setStompClient(client)
        startFakeChat(client);

        return () => {
            if (stompClient) {
                stompClient.disconnect();
            }
        };
    }, []);

    const startFakeChat = (client) => {
        let fakeMessageCounter = 0; // Initialize a counter for fake messages
        const maxFakeMessages = 10; // Set the maximum number of fake messages

        const sendFakeMessage = () => {
            if (fakeMessageCounter < maxFakeMessages) {
                const randomTopic = topics[Math.floor(Math.random() * topics.length)];
                const randomUserName = fakeUserNames[Math.floor(Math.random() * fakeUserNames.length)];
                const randomMessage = fakeMessages[Math.floor(Math.random() * fakeMessages.length)];
                const fakeMessage = {
                    senderName: randomUserName,
                    receiverName: 'Public Chat',
                    message: randomMessage.replace('${topic}', randomTopic),
                    date: moment().toDate(),
                    status: 'Message',
                };

                if (client) {
                    client.send('/app/message', {}, JSON.stringify(fakeMessage));
                }

                fakeMessageCounter++; // Increment the counter after sending a message
            } else {
                clearInterval(fakeMessageInterval); // Stop the interval when the limit is reached
            }
        };

        const randomInterval = Math.floor(Math.random() * (30000 - 10000 + 1)) + 10000; // Random interval between 10 and 30 seconds
        const fakeMessageInterval = setInterval(sendFakeMessage, randomInterval);
    };

    const sendMessage = () => {
        if (message.trim() && stompClient) {
            console.log('here now')
            const chatMessage = {
                senderName: userName,
                receiverName: 'Public Chat',
                message: message,
                date: moment().toDate(),
                status: 'Message'
            }
            stompClient.send('/app/message', {}, JSON.stringify(chatMessage))
        }
        setMessage('')
    }

    return (
        <><div class="radio-inputs">
            <label class="radio">
                <input type="radio" name="radio" onClick={() => setTab('publicChats')} />
                <span class="name">Public Chat</span>
            </label>

            {/* <label class="radio">
                <input type="radio" name="radio" onClick={() => setTab('privateChats')} />
                <span class="name">Private</span>
            </label> */}
        </div>
            {
                tab === 'publicChats' && (
                    <div class="card">
                        <div className="chat-container">
                            <div className="chat-messages">
                                {publicChats &&
                                    publicChats.map((pc) => (
                                        <UserMessage
                                            key={pc.msgId}
                                            message={pc.message}
                                            senderName={pc.senderName}
                                            date={pc.date}
                                        />
                                    ))}
                            </div>
                        </div>
                        <div class="send-message">
                            <input class="input" type="email" name="email" id="email" placeholder="send message" value={message} onChange={(e) => setMessage(e.target.value)} />
                            <button className="primary-button" style={{ marginLeft: "10px" }} onClick={() => sendMessage()}>Send</button>
                        </div>
                    </div>
                )
            }
        </>
    );
}

export default ChatRoom;




// import React, { useEffect, useState } from 'react';
// import SockJS from 'sockjs-client';
// import Stomp from 'stompjs';
// import { useOutletContext } from "react-router-dom";
// import axios from 'axios';
// import "../Styles/chatRoom.css"
// import moment from "moment"
// import UserMessage from '../components/userMessage.badge';
// import { fakeMessages, topics, fakeUserNames } from '../assets/registrationData';

// const ChatRoom = ({ name }) => {
//     const [stompClient, setStompClient] = useState(null);
//     const [privateChats, setPrivateChats] = useState(new Map());
//     const [publicChats, setPublicChats] = useState([]);
//     const [tab, setTab] = useState("publicChats");
//     const [userName, setUserName] = useState('');
//     const [userData, setUserData] = useState({});
//     const [message, setMessage] = useState('');
//     const [loggedInUser, setLoggedinUser] = useOutletContext();
//     const jwt = sessionStorage.getItem('jwt');

//     useEffect(() => {
//         //fetchUsername(); // Fetch the username when the component mounts
//         const socket = new SockJS('http://localhost:8080/ws');
//         const client = Stomp.over(socket);
//         client.connect(
//             {},
//             () => {
//                 client.subscribe('/chatroom/public', (message) => {
//                     const receivedMessage = JSON.parse(message.body);
//                     setPublicChats(prevChats => {
//                         const existingMessage = prevChats.find(chat => chat.msgId === receivedMessage.msgId);
//                         if (existingMessage) {
//                             return prevChats;
//                         } else {
//                             return [...prevChats, receivedMessage];
//                         }
//                     });
//                 })

//             }
//         );
//         setStompClient(client)
//         startFakeChat(client);


//         return () => {
//             if (stompClient) {
//                 stompClient.disconnect();
//             }
//         };
//     }, []);

//     const startFakeChat = (client) => {
//         let fakeMessageCounter = 0; // Initialize a counter for fake messages
//         const maxFakeMessages = 10; // Set the maximum number of fake messages

//         const sendFakeMessage = () => {
//             if (fakeMessageCounter < maxFakeMessages) {
//                 const randomTopic = topics[Math.floor(Math.random() * topics.length)];
//                 const randomUserName = fakeUserNames[Math.floor(Math.random() * fakeUserNames.length)];
//                 const randomMessage = fakeMessages[Math.floor(Math.random() * fakeMessages.length)];
//                 const fakeMessage = {
//                     senderName: randomUserName,
//                     receiverName: 'Public Chat',
//                     message: randomMessage.replace('${topic}', randomTopic),
//                     date: moment().toDate(),
//                     status: 'Message',
//                 };

//                 if (client) {
//                     client.send('/app/message', {}, JSON.stringify(fakeMessage));
//                 }

//                 fakeMessageCounter++; // Increment the counter after sending a message
//             } else {
//                 clearInterval(fakeMessageInterval); // Stop the interval when the limit is reached
//             }
//         };

//         const randomInterval = Math.floor(Math.random() * (30000 - 10000 + 1)) + 10000; // Random interval between 10 and 30 seconds
//         const fakeMessageInterval = setInterval(sendFakeMessage, randomInterval);
//     };

//     const sendMessage = () => {
//         if (message.trim() && stompClient) {
//             console.log('here now')
//             const chatMessage = {
//                 senderName: 'Naima',
//                 //senderName:userName,

//                 receiverName: 'Public Chat',
//                 message: message,
//                 date: moment().toDate(),
//                 status: 'Message'
//             }
//             stompClient.send('/app/message', {}, JSON.stringify(chatMessage))
//         }
//         setMessage('')
//     }

//     // const fetchUsername = async () => {
//     //     try {

//     //         const response = await axios.get('http://localhost:8080/userInfo/', {
//     //             params: {
//     //                 email: loggedInUser
//     //             },
//     //             headers: {
//     //                 "Content-Type": "multipart/form-data",
//     //                 Authorization: `Bearer ${jwt}`
//     //             }
//     //         })
//     //         const userDataFromResponse = response.data;
//     //         setUserData(prevUserData => ({
//     //             ...prevUserData,
//     //             name: userDataFromResponse.name,
//     //         }));
//     //     } catch (error) {
//     //         console.error('Error fetching username:', error);
//     //     }
//     // };

//     return (
//         <><div class="radio-inputs">
//             <label class="radio">
//                 <input type="radio" name="radio" onClick={() => setTab('publicChats')} />
//                 <span class="name">Public Chat</span>
//             </label>

//             {/* <label class="radio">
//                 <input type="radio" name="radio" onClick={() => setTab('privateChats')} />
//                 <span class="name">Private</span>
//             </label> */}
//         </div>
//             {
//                 tab === 'publicChats' && (
//                     <div class="card">
//                         <div className="chat-container">
//                             <div className="chat-messages">
//                                 {publicChats &&
//                                     publicChats.map((pc) => (
//                                         <UserMessage
//                                             key={pc.msgId}
//                                             message={pc.message}
//                                             senderName={pc.senderName}
//                                             date={pc.date}
//                                         />
//                                     ))}
//                             </div>
//                         </div>
//                         <div class="send-message">
//                             <input class="input" type="email" name="email" id="email" placeholder="send message" value={message} onChange={(e) => setMessage(e.target.value)} />
//                             <button className="primary-button" style={{ marginLeft: "10px" }} onClick={() => sendMessage()}>Send</button>
//                         </div>
//                     </div>
//                 )
//             }
//         </>
//     );


// }

// export default ChatRoom;












