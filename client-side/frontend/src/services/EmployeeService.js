import axios from 'axios';
import API_HOST from '../Constants';

const EMPLOYEE_API_BASE_URL = API_HOST + "/api/v1/employees";

const getEmployees = async () => {
    try {
        const response = await axios.get(EMPLOYEE_API_BASE_URL);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export { getEmployees };