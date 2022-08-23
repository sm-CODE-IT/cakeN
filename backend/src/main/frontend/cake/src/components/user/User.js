import React from "react";
import axios from "axios";
import useAsync from "../useAsync";

async function getUsers({id}) {
    const response = await axios.get(`http://localhost:8080/api/users/${id}`);
    return response.data;
}

function User(id) {

    const [state] = useAsync(getUsers(id), [id]);

    const { loading, error, data: users } = state;
    if (loading) return <div>로딩중..</div>
    if (error) return <div>에러 발생</div>
    if (!users) return null;   // users 값이 유효하지 않는 경우

    return (
        <div>
            <h2>{users.nickname}</h2>
            <p>
                <b>Email: </b> {users.email}
            </p>
        </div>
    )
}

export default User;