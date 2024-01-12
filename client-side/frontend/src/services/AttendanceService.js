import axios from 'axios';

const ATTENDANCE_API_BASE_URL = "http://localhost:8080/api/v1/attendance";

const getAttendances = async () => {
    try {
        const response = await axios.get(ATTENDANCE_API_BASE_URL);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export { getAttendances };