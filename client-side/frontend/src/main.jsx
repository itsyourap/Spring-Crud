import React from 'react'
import ReactDOM from 'react-dom/client'
import './App.css'


import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";



import Landing from './component/landing/Landing';
import EmployeeList from './component/employeelist/EmployeeList';



const router = createBrowserRouter([
  {
    path: "/",
    element: <Landing />,
  },
  {
    path: "/employeeList",
    element: <EmployeeList />,
  },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
