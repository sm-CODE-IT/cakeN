const USER_API_BASE_URL = `http://localhost:8080/api/users`;

function getUser() {
    return fetch(USER_API_BASE_URL)
        .then(res => {
            return res.json();
        })
        .then(user => {
            return user;
        })
        .catch(err => console.log(err));
}

function hello() {
    return fetch("http://localhost:8080/api/hello")
        .then(res => {
            return res.json();
        })
        .then(hello => {
            return hello;
        })
        .catch(err => console.log(err));
}

export {
    hello, getUser
}
