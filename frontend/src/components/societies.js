import { useState, useEffect } from "react"
import Badge from "./badge"
import { useRegistrationStore } from "../store/registration.store";
import { societiesArray } from "../assets/registrationData";
import axios from "axios";

export default function Societies() {
    const [edit, setEdit] = useState(false)
    const { societies, userId } = useRegistrationStore();
    const [mySocieties, setMySocieties] = useState([])
    const jwt = sessionStorage.getItem('jwt');


    useEffect(() => {
        if (societies) {
            const societyNames = societies.map((sp) => {
                return sp.name
            })
            setMySocieties(societyNames)
        }
    }, [societies])

    const handleDelete = (value) => {
        if (edit) {
            const newSocieties = mySocieties.filter(hb => hb !== value)
            setMySocieties(newSocieties)
        }
    }

    const handleAdd = (value) => {
        const exist = mySocieties.includes(value)
        if (!exist) {
            setMySocieties(prev => [...prev, value])
        }
    }

    const handleSave = () => {
        const newSocietiesToCreate = mySocieties.map(so => {
            return {
                name: so
            }
        })
        try {
            axios.put(
                `http://localhost:8080/society/update-societies/user/${userId}`,
                newSocietiesToCreate,{
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${jwt}`
                }
            }
            )
            setEdit(false)
        } catch (e) {
            alert("Something went wrong saving societies, please try again later")
        }
    }

    return (
        <section>
            <div class="extra-info-title">
                <text class="info-text-header">
                    Societies
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
                        <option value="" disabled selected>Select Societies</option>
                        {
                            societiesArray && societiesArray.map(c => {
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
                    mySocieties && mySocieties.map((society) => {
                        return <Badge title={society} onClick={() => handleDelete(society)} edit={edit} />;
                    })
                }
            </div>
        </section >
    )
}