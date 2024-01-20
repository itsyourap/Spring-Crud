import axios from 'axios';
import API_BASE_URL from "../constants/apiConstant.js";

const ATTENDANCE_API_BASE_URL = API_BASE_URL + "/attendance";

const getAttendances = async () => {
    try {
        const response = await axios.get(ATTENDANCE_API_BASE_URL);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export { getAttendances };