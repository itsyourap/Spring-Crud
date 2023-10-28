import React, { useState, useEffect } from 'react'
import './employeeList.css'
import { getEmployees } from '../../services/EmployeeService';
import { Link } from 'react-router-dom';

const EmployeeList = () => {




    const [employee, setEmployee] = useState([]);

    useEffect(() => {
        getEmployees()
            .then((data) => setEmployee(data))
            .catch((error) => console.error("Error fetching employees: " + error));
    }, []);

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
                                    <td>{employee.actions}</td>
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