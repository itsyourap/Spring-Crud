import React, { useState } from 'react'
import './addEmployee.css'
import axios from 'axios';
import { SkipBackCircle } from "@phosphor-icons/react";
import { useNavigate } from 'react-router-dom';
const AddEmployee = () => {

    const navigate = useNavigate();


    const [firstName, setFirstName] = useState(' ');
    const [lastName, setLastName] = useState(' ');
    const [emailId, setEmailId] = useState(' ');

    const changeFirstNameHandler = (event) => {
        setFirstName(event.target.value);
    }
    const changeLastNameHandler = (event) => {
        setLastName(event.target.value);
    }
    const changeEmailIdHandler = (event) => {
        setEmailId(event.target.value);
    }
    const saveEmployee = () => {

        const employeeData = { firstName, lastName, emailId };

        // You can use a library like Axios to make the HTTP request
        // Replace the following code with your actual API call
        axios.post(`http://localhost:8080/api/v1/employees`, employeeData)
            .then(response => {
                alert("added successfully");
                setFirstName('');
                setLastName('');
                setEmailId('');
            })
            .catch(error => {
                alert("Not added");
            });
    };
    return (
        <>
            <div className="employee_container add_container">


                <SkipBackCircle onClick={() => navigate(-1)} size={50} style={{ color: "var(--secondary-color)", cursor: 'pointer', margin: "1rem 0 0 0.5rem" }} />

                <div className="employee_title">Add Employee</div>

                <div className="employee_form section__margin">
                    <form className="employee_add_form" action="">

                        <label>First Name:</label>
                        <input placeholder='First Name' name='firstName' value={firstName} onChange={changeFirstNameHandler}></input>
                        <label>Last Name:</label>
                        <input placeholder='First Name' name='lastName' value={lastName} onChange={changeLastNameHandler}></input>
                        <label>Email Id:</label>
                        <input placeholder='Email Id' name='email' value={emailId} onChange={changeEmailIdHandler}></input>

                    </form>

                    <button className="submit_btn" onClick={saveEmployee}>Submit</button>
                </div>
            </div>
        </>
    )
}

export default AddEmployee