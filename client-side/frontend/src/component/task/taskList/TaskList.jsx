import React, {useState, useEffect} from 'react'
import './taskList.css'
import {getTasks} from '../../../services/TaskService.js';
import {Link} from 'react-router-dom';
import axios from 'axios';

const TaskList = () => {
    const [task, setTask] = useState([]);

    useEffect(() => {
        getTasks()
            .then((data) => setTask(data))
            .catch((error) => console.error("Error fetching tasks: " + error));
    }, []);

    const deleteTask = (id) => {
        if (window.confirm("Are you sure you want to delete this task?")) {
            // Make an HTTP DELETE request to delete the task
            axios
                .delete(`http://localhost:8080/api/v1/tasks/${id}`)
                .then(() => {
                    // If the delete request is successful, update the state to remove the deleted task
                    setTask((prevTasks) =>
                        prevTasks.filter((task) => task.id !== id)
                    );
                    alert('Task deleted successfully');
                })
                .catch(() => {
                    alert('Failed to delete task');
                });
        }
    }

    return (
        <>
            <div className="task_container">
                <div className="task_title">Task List</div>
                <div className="task_table section__margin">
                    <Link style={{textDecoration: 'none'}} to={`addTask`}>
                        <button className='task_btn'>Add Task</button>
                    </Link>

                    <table>
                        <tbody>
                        <tr>
                            <th>Task Name</th>
                            <th>Task Description</th>
                            <th>Assigned Employees</th>
                            <th>Actions</th>
                        </tr>
                        </tbody>

                        <tbody>
                        {task.map((task) =>
                            (
                                <tr key={task.id}>
                                    <td>{task.taskName}</td>
                                    <td>{task.taskDescription}</td>
                                    <td>{task.assignedEmployees.map((employee, index) => {
                                        return (
                                            <div className="assigned_employee_td" key={index}>
                                                {employee.firstName} {employee.lastName}
                                                {index + 1 !== task.assignedEmployees.length ? "," : ""}
                                            </div>
                                        );
                                    })}</td>
                                    <td>

                                        <div className="btn_container">
                                            <Link style={{textDecoration: 'none'}} to={`updateTask/${task.id}`}>
                                                <button style={{fontSize: "medium"}}>Update</button>
                                            </Link>

                                            <button onClick={() => deleteTask(task.id)}
                                                    style={{fontSize: "medium"}}>Delete
                                            </button>

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

export default TaskList