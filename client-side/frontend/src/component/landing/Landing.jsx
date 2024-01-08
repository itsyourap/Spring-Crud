import React from 'react'
import './landing.css'
import { Link } from 'react-router-dom'

const Landing = () => {
    return (
        <>
            <div className="landing_container">

                <div className='landing_title'>Spring Crud Web App</div>

                <div className='button_div'>
                    <Link style={{ textDecoration: 'none' }} to={`employeeList`}>
                        <button className="landing_button btn">
                            Employees
                        </button>
                    </Link>

                    <Link style={{ textDecoration: 'none' }} to={`taskList`}>
                        <button className="landing_button btn">
                            Tasks
                        </button>
                    </Link>
                </div>
            </div>
        </>
    )
}

export default Landing