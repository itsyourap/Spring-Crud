import axios from 'axios';

const EMPLOYEE_API_BASE_URL = "http://localhost:8080/api/v1/employees";

const getEmployees = async () => {
    try {
        const response = await axios.get(EMPLOYEE_API_BASE_URL);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export { getEmployees };