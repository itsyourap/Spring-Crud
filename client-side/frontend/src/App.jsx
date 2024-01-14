import React from 'react'
import './App.css'
import Landing from './component/landing/Landing'
import EmployeeList from './component/employeelist/EmployeeList'
import AttendanceList from "./component/attendance/attendanceList/AttendanceList.jsx";

const App = () => {
    return (
        <>
            <Landing/>
            <EmployeeList/>
            <AttendanceList/>
        </>
    )
}

export default App