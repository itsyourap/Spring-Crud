import axios from 'axios';

const TASK_API_BASE_URL = "http://localhost:8080/api/v1/tasks";

const getTasks = async () => {
    try {
        const response = await axios.get(TASK_API_BASE_URL);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export { getTasks };