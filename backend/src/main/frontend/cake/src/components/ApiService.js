import axios from 'axios';

const USER_API_BASE_URL = "http://localhost:8080/api/users";
const LETTER_API_BASE_URL = "https://localhost:8080/api/letter";

class ApiService {

    fetchUsers() {
        return axios.get(USER_API_BASE_URL);
    }

    fetchUserById(userId) {
        return axios.get(USER_API_BASE_URL + '/' + userId);
    }

    deleteUser(userId) {
        return axios.delete(USER_API_BASE_URL + '/' + userId);
    }

    createUser(user) {
        return axios.post(USER_API_BASE_URL, user);
    }

    updateUser(user) {
        return axios.put(USER_API_BASE_URL + '/' + user.userId, user);
    }
}

export default new ApiService();