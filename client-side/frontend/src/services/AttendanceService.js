import axios from 'axios';
import API_HOST from '../Constants';

const ATTENDANCE_API_BASE_URL = API_HOST + "/api/v1/attendance";

const getAttendances = async () => {
    try {
        const response = await axios.get(ATTENDANCE_API_BASE_URL);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export { getAttendances };