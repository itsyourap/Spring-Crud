import axios from 'axios';
import API_BASE_URL from "../constants/apiConstant.js";

const TASK_API_BASE_URL = API_BASE_URL + "/tasks";

const getTasks = async () => {
    try {
        const response = await axios.get(TASK_API_BASE_URL);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export { getTasks };