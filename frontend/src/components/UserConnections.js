import React, { useRef } from "react";
import axios from "axios";

export default function UserConnections() {
  const userId = useRef();
  const connectionId = useRef();

  const addConnection = async (event) => {
    event.preventDefault();

    const data = {
      userId: userId.current.value,
      connectionId: connectionId.current.value,
    };

    try {
      const response = await axios.post(`http://localhost:8080/connections/${data.userId}/add`, {
        connectionId: data.connectionId
      });

      if (response.status === 200) {
        alert("Connection added successfully");
        // Optionally, you can update UI or perform any other actions upon successful addition
      } else {
        alert("Failed to add connection");
      }
    } catch (error) {
      console.error("Error adding connection:", error);
      alert("Failed to add connection");
    }
  };

  const removeConnection = async (event) => {
    event.preventDefault();

    const data = {
      userId: userId.current.value,
      connectionId: connectionId.current.value,
    };

    try {
      const response = await axios.post(`http://localhost:8080/connections/${data.userId}/remove`, {
        connectionId: data.connectionId
      });

      if (response.status === 200) {
        alert("Connection removed successfully");
        // Optionally, you can update UI or perform any other actions upon successful removal
      } else {
        alert("Failed to remove connection");
      }
    } catch (error) {
      console.error("Error removing connection:", error);
      alert("Failed to remove connection");
    }
  };

  return (
    <div>
      <h2>User Connections</h2>
      <form onSubmit={addConnection}>
        <label>User ID:</label>
        <input type="text" ref={userId} required />
        <label>Connection ID:</label>
        <input type="text" ref={connectionId} required />
        <button type="submit">Add Connection</button>
      </form>
      
      <form onSubmit={removeConnection}>
        <label>User ID:</label>
        <input type="text" ref={userId} required />
        <label>Connection ID:</label>
        <input type="text" ref={connectionId} required />
        <button type="submit">Remove Connection</button>
      </form>
    </div>
  );
}
