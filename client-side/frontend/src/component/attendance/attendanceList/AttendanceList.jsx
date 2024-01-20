import React, {useState, useEffect} from 'react'
import './attendanceList.css'
import {getAttendances} from '../../../services/AttendanceService';
import {Link} from 'react-router-dom';
import axios from 'axios';
import API_BASE_URL from "../../../constants/apiConstant.js";

const AttendanceList = () => {
    const [attendance, setAttendance] = useState([]);

    useEffect(() => {
        getAttendances()
            .then((data) => setAttendance(data))
            .catch((error) => console.error("Error fetching Attendance Records: " + error));
    }, []);

    const deleteAttendanceRecord = (id) => {
        if (window.confirm("Are you sure you want to delete this Attendance Record?")) {
            // Make an HTTP DELETE request to delete the Attendance Record
            axios
                .delete(`${API_BASE_URL}/attendance/${id}`)
                .then(() => {
                    // If the delete request is successful, update the state to remove the deleted attendance record
                    setAttendance((prevAttendance) =>
                        prevAttendance.filter((attendance) => attendance.id !== id)
                    );
                    alert('Attendance Record deleted successfully');
                })
                .catch(() => {
                    alert('Failed to delete attendance record');
                });
        }
    }

    return (
        <>
            <div className="attendance_container">
                <div className="attendance_title">Attendance Records</div>
                <div className="attendance_table section__margin">
                    <Link style={{textDecoration: 'none'}} to={`addAttendance`}>
                        <button className='attendance_btn'>Add Attendance Record</button>
                    </Link>

                    <table>
                        <tbody>
                        <tr>
                            <th>Attendance Date</th>
                            <th>Present Employees</th>
                            <th>Actions</th>
                        </tr>
                        </tbody>

                        <tbody>
                        {attendance.map((attendance) =>
                            (
                                <tr key={attendance.id}>
                                    <td>{attendance.date}</td>
                                    <td>{attendance.presentEmployees.map((employee, index) => {
                                        return (
                                            <div className="present_employee_td" key={index}>
                                                {employee.firstName} {employee.lastName}
                                                {index + 1 !== attendance.presentEmployees.length ? "," : ""}
                                            </div>
                                        );
                                    })}</td>
                                    <td>

                                        <div className="btn_container">
                                            <Link style={{textDecoration: 'none'}} to={`updateAttendance/${attendance.id}`}>
                                                <button style={{fontSize: "medium"}}>Update</button>
                                            </Link>

                                            <button onClick={() => deleteAttendanceRecord(attendance.id)}
                                                    style={{fontSize: "medium"}}>Delete
                                            </button>

                                        </div>
                                    </td>
                                </tr>
                            ))}
                        </tbody>

                    </table>
                </div>
            </div>
        </>
    )
}

export default AttendanceList