import { useEffect, useState } from "react"
import Badge from "./badge"
import { hobbiesArray } from "../assets/registrationData";
import { useRegistrationStore } from "../store/registration.store";
import axios from "axios";

export default function Hobbies() {
    const [edit, setEdit] = useState(false)
    const { hobbies, userId } = useRegistrationStore();
    const [myHobbies, setHobbies] = useState([])
    const jwt = sessionStorage.getItem('jwt');

    useEffect(() => {
        if (hobbies) {
            const hobbyName = hobbies.map((hb) => {
                return hb.name
            })
            setHobbies(hobbyName)
        }
    }, [hobbies])

    const handleDelete = (value) => {
        if (edit) {
            const newhb = myHobbies.filter(hb => hb !== value)
            console.log("newhn", newhb)
            setHobbies(newhb)
        }
    }

    const handleAdd = (value) => {
        const exist = myHobbies.includes(value)
        if (!exist) {
            setHobbies(prev => [...prev, value])
        }
    }

    const handleSave = () => {
        const newHobbiesToCreate = myHobbies.map(hb => {
            return {
                name: hb
            }
        })
        try {
            axios.put(
                `http://localhost:8080/hobby/update-hobbies/user/${userId}`,
                newHobbiesToCreate, {
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
            <div className="extra-info-title">
                <text className="info-text-header">
                    Hobbies
                </text>
                {
                    edit ?
                        <div className="save-and-cancel"><button className="secondary-button" style={{ marginLeft: "10px" }} onClick={() => setEdit(false)}>Cancel</button><button className="primary-button" style={{ marginLeft: "10px" }} onClick={handleSave}>Save</button></div>
                        :
                        <button className="edit-button" onClick={() => setEdit(true)}>Edit</button>
                }
            </div>
            {
                edit && (
                    <select onChange={(e) => handleAdd(e.target.value)}>
                        <option value="" disabled selected>Select Hobbies</option>
                        {
                            hobbiesArray && hobbiesArray.map(c => {
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
            <div className="badges-section">
                {
                    myHobbies && myHobbies.map((hobbyString) => {
                        return <Badge title={hobbyString} onClick={() => handleDelete(hobbyString)} edit={edit} />;
                    })
                }
            </div>
        </section>
    )
}