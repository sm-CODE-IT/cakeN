import axios from "axios";
// 293

const USER_API_BASE_URL = `http://localhost:8080/api/users`;
const LETTER_API_BASE_URL = `http://localhost:8080/api/letters`;

class ApiService {
    // User API URL
    fetchUsers() {
        return axios.get(USER_API_BASE_URL);
    }

    fetchUserById(userId) {
        return axios.get(USER_API_BASE_URL + "/" + userId);
    }

    deleteUser(userId) {
        return axios.delete(USER_API_BASE_URL + "/" + userId);
    }

    createUser(user) {
        return axios.post(USER_API_BASE_URL, user);
    }

    updateUser(user) {
        return axios.put(USER_API_BASE_URL + "/" + user.userId, user);
    }

    // 레터링 API URL
    deleteLetter(letterId) {
        return axios.delete(LETTER_API_BASE_URL + "/" + letterId);
    }

    updateLetter(letter) {
        return axios.put(LETTER_API_BASE_URL + "/" + letter.letter_id, letter);
    }
}

export function call(api, method, request) {
    let options = {
        headers: new Headers({
            "Content-type": "application/json",
        }),
        url: USER_API_BASE_URL + api,
        method: method,
    };
}

export function signin(userDTO) {
    return call("/api/users/login", "POST", userDTO).then((response) => {
        console.log("response : ", response);
        alert("로그인 토큰: " + response.token);
    });
}

export default new ApiService();