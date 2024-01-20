import axios from 'axios';
import API_BASE_URL from "../constants/apiConstant.js";

const EMPLOYEE_API_BASE_URL = API_BASE_URL + "/employees";

const getEmployees = async () => {
    try {
        const response = await axios.get(EMPLOYEE_API_BASE_URL);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export { getEmployees };