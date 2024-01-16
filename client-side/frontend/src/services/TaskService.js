import axios from 'axios';
import API_HOST from '../Constants';

const TASK_API_BASE_URL = API_HOST + "/api/v1/tasks";

const getTasks = async () => {
    try {
        const response = await axios.get(TASK_API_BASE_URL);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export { getTasks };