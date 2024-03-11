import { useState } from "react"
import Badge from "./badge"

export default function Courses() {
    const [edit, setEdit] = useState(false)
    return (
        <section>
            <div class="extra-info-title">
                <text class="info-text-header">
                    Courses
                </text>
                {
                    edit ?
                        <div class="save-and-cancel"><button className="secondary-button" style={{ marginLeft: "10px" }} onClick={() => setEdit(false)}>Cancel</button><button className="primary-button" style={{ marginLeft: "10px" }} onClick={() => setEdit(true)}>Save</button></div>
                        :
                        <button className="edit-button" onClick={() => setEdit(true)}>Edit</button>
                }
            </div>
             {
                edit && (
                    <select>
                        <option value="" disabled selected>Select Courses</option>
                        <option value="option1">Option 1</option>
                        <option value="option2">Option 2</option>
                        <option value="option3">Option 3</option>
                    </select>
                )
            }
            <div class="badges-section">
                <Badge title={"computer science"} />
                <Badge title={"Mathematics"} />
                <Badge title={"Law"} />
            </div>
        </section >
    )
}