import { useEffect, useState } from "react"
import "../Styles/userMessage.css"


export default function UserMessage({ senderName, message, date }) {
    const [userAbbrev, setUserAbbrev] = useState("")

    useEffect(() => {
        if (senderName) {
            const words = senderName.split(" ");
            const abbreviated = words.map(word => word.charAt(0).toUpperCase()).join("");
            setUserAbbrev(abbreviated)
        }
    }, [senderName])
    return (
        <div class='userChat'>
            <div class="profile-abbrev">
                <text>{userAbbrev}</text>
            </div>
            <span class="chat-badge">{message}</span>
        </div>
    )
}