import React from 'react'
import './landing.css'
import { Link } from 'react-router-dom'

const Landing = () => {


    return (
        <>
            <div className="landing_container">

                <div className='landing_title'>Spring Crud Web App  </div>
                <Link style={{ textDecoration: 'none' }} to={`employeeList`}>
                    <button className="landing_button btn">
                        Get Started
                    </button>
                </Link>

            </div>
        </>
    )
}

export default Landing