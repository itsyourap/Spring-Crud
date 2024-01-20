import React, {useEffect, useState} from 'react'
import axios from 'axios';
import './addAttendance.css';
import {SkipBackCircle} from "@phosphor-icons/react";
import {useNavigate} from 'react-router-dom';
import Modal from 'react-modal';
import {getEmployees} from "../../../services/EmployeeService.js";
import API_BASE_URL from "../../../constants/apiConstant.js";

const AddAttendance = () => {
    const navigate = useNavigate();

    // Modal Controls
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const openModal = () => {
        setModalIsOpen(true);
    };
    const closeModal = () => {
        setModalIsOpen(false);
    };

    // Employees
    const [employee, setEmployee] = useState([]);
    useEffect(() => {
        getEmployees()
            .then((data) => setEmployee(data))
            .catch((error) => console.error("Error fetching employees: " + error));
    }, []);

    // Attendance Records
    const [attendanceDate, setAttendanceDate] = useState(' ');
    const [presentEmployees, setPresentEmployees] = useState([]);
    const changeAttendanceDateHandler = (event) => {
        setAttendanceDate(event.target.value);
    };
    const markEmployeeAsAbsent = (employee) => {
        const newEmployeeList = presentEmployees.filter((emp) => emp.id !== employee.id);
        setPresentEmployees(newEmployeeList);
    };
    const markEmployeeAsPresent = (employee) => {
        const newEmployeeList = presentEmployees.filter((emp) => emp.id !== employee.id);
        newEmployeeList.push(employee);
        setPresentEmployees(newEmployeeList);
    }

    const saveAttendanceRecord = () => {
        if (attendanceDate.trim() === '') {
            alert("Please enter a valid date");
            return;
        }
        const attendanceData = {date: attendanceDate, presentEmployees: presentEmployees};

        // You can use a library like Axios to make the HTTP request
        // Replace the following code with your actual API call
        axios.post(`${API_BASE_URL}/attendance`, attendanceData)
            .then(() => {
                alert("Attendance Record added successfully");
                setAttendanceDate('');
                setPresentEmployees([]);
            })
            .catch(() => {
                alert("Not added");
            });
    };

    return (
        <>
            <div className="attendance_container add_container">
                <Modal
                    isOpen={modalIsOpen}
                    onRequestClose={closeModal}
                    contentLabel="My Dialog">
                    <h2 className="section__margin">
                        Mark Present Employees
                    </h2>
                    <div className="employee_table section__margin">

                        <table>
                            <tbody>
                            <tr>
                                <th>Employee First Name</th>
                                <th>Employee Last Name</th>
                                <th>Employee Email ID</th>
                                <th>Action</th>
                            </tr>
                            </tbody>

                            <tbody>
                            {
                                employee.map((employee) =>
                                    (
                                        <tr key={employee.id}>
                                            <td style={{color: 'black'}}>{employee.firstName}</td>
                                            <td style={{color: 'black'}}>{employee.lastName}</td>
                                            <td style={{color: 'black'}}>{employee.emailId}</td>
                                            <td style={{color: 'black'}}>

                                                <div className="btn_container">
                                                    {
                                                        presentEmployees.find((emp) => emp.id === employee.id) ?
                                                            <button onClick={() => markEmployeeAsAbsent(employee)}
                                                                    style={{fontSize: "medium"}}>Remove
                                                            </button> :
                                                            <button onClick={() => markEmployeeAsPresent(employee)}
                                                                    style={{fontSize: "medium"}}>Add
                                                            </button>
                                                    }
                                                </div>
                                            </td>
                                        </tr>
                                    ))}
                            </tbody>
                        </table>
                        <button onClick={closeModal} className='close_employee_list_btn'>Close</button>
                    </div>

                </Modal>

                <SkipBackCircle onClick={() => navigate(-1)} size={50} style={{
                    color: "var(--secondary-color)",
                    cursor: 'pointer',
                    margin: "1rem 0 0 0.5rem"
                }}/>

                <div className="attendance_title">Add Attendance Record</div>

                <div className="attendance_form section__margin">

                    <form className="attendance_add_form" action="">

                        <label>Attendance Date:</label>
                        <input placeholder='Attendance Date' required={true} name='attendanceDate' type='date' value={attendanceDate}
                               onChange={changeAttendanceDateHandler}></input>
                        <label>Present Employees:</label>
                        {
                            presentEmployees.map((employee, index) => {
                                return (
                                    <div className="employee_list" key={index}>
                                        <div
                                            className="employee_list_name">{employee.firstName} {employee.lastName}</div>
                                        <button className="employee_list_delete"
                                                onClick={(event) => {
                                                    event.preventDefault();
                                                    markEmployeeAsAbsent(employee);
                                                }}>Remove
                                        </button>
                                    </div>
                                )
                            })
                        }

                        <button className="open_employee_list_btn" onClick={(event) => {
                            event.preventDefault();
                            openModal();
                        }}>Choose Present Employees
                        </button>
                    </form>
                    <button className="submit_btn" onClick={saveAttendanceRecord}>Submit</button>
                </div>
            </div>
        </>
    )
}

export default AddAttendance