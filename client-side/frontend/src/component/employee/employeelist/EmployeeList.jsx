import React, { useState, useEffect } from 'react'
import './employeeList.css'
import { getEmployees } from '../../../services/EmployeeService.js';
import { Link } from 'react-router-dom';
import axios from 'axios';
import API_BASE_URL from "../../../constants/apiConstant.js";


const EmployeeList = () => {
    const [employee, setEmployee] = useState([]);

    useEffect(() => {
        getEmployees()
            .then((data) => setEmployee(data))
            .catch((error) => console.error("Error fetching employees: " + error));
    }, []);


    const deleteEmployee = (id) => {
        if (window.confirm("Are you sure you want to delete this employee?")) {
            // Make an HTTP DELETE request to delete the employee
            axios
                .delete(`${API_BASE_URL}/employees/${id}`)
                .then((response) => {
                    // If the delete request is successful, update the state to remove the deleted employee
                    setEmployee((prevEmployees) =>
                        prevEmployees.filter((employee) => employee.id !== id)
                    );
                    alert('Employee deleted successfully');
                })
                .catch((error) => {
                    alert('Failed to delete employee');
                });
        }
    }

    return (
        <>
            <div className="employee_container">
                <div className="employee_title">Employee List</div>
                <div className="employee_table section__margin">
                    <Link style={{ textDecoration: 'none' }} to={`addEmployee`}>

                        <button className='employee_btn'>Add Employee</button>
                    </Link>

                    <table>
                        <tr>
                            <th>Employee First Name</th>
                            <th>Employee Last Name</th>
                            <th>Employee Email ID</th>
                            <th>Actions</th>
                        </tr>

                        <tbody>
                            {employee.map((employee) =>
                            (
                                <tr key={employee.id}>
                                    <td>{employee.firstName}</td>
                                    <td>{employee.lastName}</td>
                                    <td>{employee.emailId}</td>
                                    <td>

                                        <div className="btn_container">
                                            <Link style={{ textDecoration: 'none' }} to={`updateEmployee/${employee.id}`}>
                                                <button style={{ fontSize: "medium" }}>Update</button>
                                            </Link>

                                            <button onClick={() => deleteEmployee(employee.id)} style={{ fontSize: "medium" }}>Delete</button>

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

export default EmployeeList