import { useState, useEffect } from "react"
import Badge from "./badge"
import { useRegistrationStore } from "../store/registration.store";
import { sportsArray } from "../assets/registrationData";
import axios from "axios";
const jwt = sessionStorage.getItem('jwt');

export default function Sports() {
    const [edit, setEdit] = useState(false)
    const { sports, userId } = useRegistrationStore();
    const [mySports, setMySports] = useState([])

    useEffect(() => {
        if (sports) {
            const sportName = sports.map((sp) => {
                return sp.name
            })
            setMySports(sportName)
        }
    }, [sports])

    const handleDelete = (value) => {
        if (edit) {
            const newSport = mySports.filter(hb => hb !== value)
            setMySports(newSport)
        }
    }

    const handleAdd = (value) => {
        const exist = mySports.includes(value)
        if (!exist) {
            setMySports(prev => [...prev, value])
        }
    }

    const handleSave = () => {
        const newSportsToCreate = mySports.map(sp => {
            return {
                name: sp
            }
        })
        try {
            axios.put(
                `http://localhost:8080/sport/update-sports/user/${userId}`,
                newSportsToCreate, {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${jwt}`
                }
            }
            )
            setEdit(false)
        } catch (e) {
            alert("Something went wrong saving hobbies, please try again later")
        }
    }

    return (
        <section>
            <div class="extra-info-title">
                <text class="info-text-header">
                    Sports
                </text>
                {
                    edit ?
                        <div class="save-and-cancel"><button className="secondary-button" style={{ marginLeft: "10px" }} onClick={() => setEdit(false)}>Cancel</button><button className="primary-button" style={{ marginLeft: "10px" }} onClick={handleSave}>Save</button></div>
                        :
                        <button className="edit-button" onClick={() => setEdit(true)}>Edit</button>
                }
            </div>
            {
                edit && (
                    <select onChange={(e) => handleAdd(e.target.value)}>
                        <option value="" disabled selected>Select Sports</option>
                        {
                            sportsArray && sportsArray.map(c => {
                                return (
                                    <option>
                                        {c}
                                    </option>
                                )
                            })
                        }
                    </select>
                )
            }
            <div class="badges-section">
                {
                    mySports && mySports.map((sport) => {
                        return <Badge title={sport} onClick={() => handleDelete(sport)} edit={edit} />;
                    })
                }
            </div>
        </section >
    )
}