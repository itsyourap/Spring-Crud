import React, {useEffect, useState} from 'react'
import axios from 'axios';
import './addTask.css';
import {SkipBackCircle} from "@phosphor-icons/react";
import {useNavigate} from 'react-router-dom';
import Modal from 'react-modal';
import {getEmployees} from "../../../services/EmployeeService.js";

const AddTask = () => {
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

    // Tasks
    const [taskName, setTaskName] = useState(' ');
    const [taskDescription, setTaskDescription] = useState(' ');
    const [assignedEmployees, setAssignedEmployees] = useState([]);
    const changeTaskNameHandler = (event) => {
        setTaskName(event.target.value);
    };
    const changeTaskDescriptionHandler = (event) => {
        setTaskDescription(event.target.value);
    };
    const removeEmployeeFromTask = (employee) => {
        const newEmployeeList = assignedEmployees.filter((emp) => emp.id !== employee.id);
        setAssignedEmployees(newEmployeeList);
    };
    const addEmployeeToTask = (employee) => {
        const newEmployeeList = assignedEmployees.filter((emp) => emp.id !== employee.id);
        newEmployeeList.push(employee);
        setAssignedEmployees(newEmployeeList);
    }

    const saveTask = () => {
        const taskData = {taskName, taskDescription, assignedEmployees};

        // You can use a library like Axios to make the HTTP request
        // Replace the following code with your actual API call
        axios.post(`http://localhost:8080/api/v1/tasks`, taskData)
            .then(() => {
                alert("Task added successfully");
                setTaskName('');
                setTaskDescription('');
                setAssignedEmployees([]);
            })
            .catch(() => {
                alert("Not added");
            });
    };

    return (
        <>
            <div className="task_container add_container">
                <Modal
                    isOpen={modalIsOpen}
                    onRequestClose={closeModal}
                    contentLabel="My Dialog">
                    <h2 className="section__margin">
                        Assign Employees to Task
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
                                                        assignedEmployees.find((emp) => emp.id === employee.id) ?
                                                            <button onClick={() => removeEmployeeFromTask(employee)}
                                                                    style={{fontSize: "medium"}}>Remove
                                                            </button> :
                                                            <button onClick={() => addEmployeeToTask(employee)}
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

                <div className="task_title">Add Task</div>

                <div className="task_form section__margin">

                    <form className="task_add_form" action="">

                        <label>Task Name:</label>
                        <input placeholder='Task Name' name='taskName' value={taskName}
                               onChange={changeTaskNameHandler}></input>
                        <label>Task Description:</label>
                        <input placeholder='Task Description' name='taskDescription' value={taskDescription}
                               onChange={changeTaskDescriptionHandler}></input>
                        <label>Assigned Employees:</label>
                        {
                            assignedEmployees.map((employee, index) => {
                                return (
                                    <div className="employee_list" key={index}>
                                        <div
                                            className="employee_list_name">{employee.firstName} {employee.lastName}</div>
                                        <button className="employee_list_delete"
                                                onClick={(event) => {
                                                    event.preventDefault();
                                                    removeEmployeeFromTask(employee);
                                                }}>Remove
                                        </button>
                                    </div>
                                )
                            })
                        }

                        <button className="open_employee_list_btn" onClick={(event) => {
                            event.preventDefault();
                            openModal();
                        }}>Assign Employees
                        </button>
                    </form>
                    <button className="submit_btn" onClick={saveTask}>Submit</button>
                </div>
            </div>
        </>
    )
}

export default AddTask