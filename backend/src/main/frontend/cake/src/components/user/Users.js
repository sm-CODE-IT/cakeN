import React, {useState} from "react";
import axios from "axios";
import useAsync from "../useAsync";
import User from "./User";

async function getUsers() {
    const response = await axios.get("http://localhost:8080/api/test");
    return response.data;
}

function Users() {

    const [state, refetch] = useAsync(getUsers, [], true);
    const [userId, setUserId] = useState(null);    // 마이페이지 정보를 불러오기 위한 useState
    
    const { loading, error, data: users } = state;   // data 값이 users로 들어감
    if (loading) return <div>로딩중..</div>
    if (error) return <div>에러 발생</div>
    if (!users) return <button onClick={refetch}>불러오기</button>;   // users 값이 유효하지 않는 경우

    return (
        <>
            <ul>
                {users.map(user=><li key={user.userId} onClick={() => setUserId(user.userId)}>
                    {user.nickname} ({user.email})
                </li>)}
            </ul>
            <button onClick={refetch}>다시 불러오기</button>
            {userId && <User id={userId} />}
        </>
    )
}

export default Users;