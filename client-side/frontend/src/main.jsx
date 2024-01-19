import React from 'react'
import ReactDOM from 'react-dom/client'
import './App.css'


import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";


import Landing from './component/landing/Landing';
import EmployeeList from './component/employee/employeelist/EmployeeList';
import AddEmployee from './component/employee/addEmployee/AddEmployee';
import UpdateEmployee from './component/employee/updateEmployee/UpdateEmployee';
import AddTask from "./component/task/addTask/AddTask.jsx";
import UpdateTask from "./component/task/updateTask/UpdateTask.jsx";
import TaskList from "./component/task/taskList/TaskList.jsx";
import AttendanceList from "./component/attendance/attendanceList/AttendanceList.jsx";
import AddAttendance from "./component/attendance/addAttendance/AddAttendance.jsx";
import UpdateAttendance from "./component/attendance/updateAttendance/UpdateAttendance.jsx";

// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyAL_EGDlr1gNE6pap986DyKqnycSMl5wmQ",
    authDomain: "spring-crud-ne0gi02.firebaseapp.com",
    projectId: "spring-crud-ne0gi02",
    storageBucket: "spring-crud-ne0gi02.appspot.com",
    messagingSenderId: "927258230546",
    appId: "1:927258230546:web:6f6fc0cfb1d2702d7b3dcb",
    measurementId: "G-D9X46QD0Z3"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

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
