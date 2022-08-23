import React, {useEffect, useState} from "react";
import axios from "axios";


function Users() {
    const [users, setUsers] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchUsers = async () => {
        try {
            // 요청을 시작하면 error와 users 초기화
            setError(null);
            setUsers(null);
            setLoading(true);    // loading 상태: false -> true

            const response = await axios.get(
                '/api/test'
            );

            setUsers(response.data);
        } catch (e) {
            setError(e);
        }

        setLoading(false);   // 요청이 끝났으므로 false로 다시 셋팅
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    if (loading) return <div>로딩중..</div>
    if (error) return <div>에러가 발생했습니다.</div>

    if (!users) return null;   // users 데이터가 없다면 아무것도 표시 X

    return (
        <>
            <ul>
                {users.map(user => {
                    <li key={user.userId}>
                        {user.nickname} ({user.email})
                    </li>
                })}
                <button onClick={fetchUsers}>다시 불러오기</button>
            </ul>
        </>
    )
}


export default Users;