import React from 'react'
import ReactDOM from 'react-dom/client'
import './App.css'


import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";


import Landing from './component/landing/Landing';
import EmployeeList from './component/employeelist/EmployeeList';
import AddEmployee from './component/addEmployee/AddEmployee';
import UpdateEmployee from './component/updateEmployee/UpdateEmployee';
import AddTask from "./component/addTask/AddTask.jsx";
import UpdateTask from "./component/updateTask/UpdateTask.jsx";
import TaskList from "./component/taskList/TaskList.jsx";
import AttendanceList from "./component/attendance/attendanceList/AttendanceList.jsx";
import AddAttendance from "./component/attendance/addAttendance/AddAttendance.jsx";
import UpdateAttendance from "./component/attendance/updateAttendance/UpdateAttendance.jsx";


const router = createBrowserRouter([
    {
        path: "/",
        element: <Landing/>,
    },
    {
        path: "/employeeList",
        element: <EmployeeList/>,
    },
    {
        path: "employeeList/addEmployee",
        element: <AddEmployee/>,
    },
    {
        path: "employeeList/updateEmployee/:id",
        element: <UpdateEmployee/>,
    },
    {
        path: "/taskList",
        element: <TaskList/>
    },
    {
        path: "taskList/addTask",
        element: <AddTask/>
    },
    {
        path: "taskList/updateTask/:id",
        element: <UpdateTask/>
    },
    {
        path: "/attendanceList",
        element: <AttendanceList/>
    },
    {
        path: "attendanceList/addAttendance",
        element: <AddAttendance/>
    },
    {
        path: "attendanceList/updateAttendance/:id",
        element: <UpdateAttendance/>
    }
]);

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <RouterProvider router={router}/>
    </React.StrictMode>,
)
