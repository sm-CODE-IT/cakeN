import {useEffect, useState} from "react";
import axios from "axios";


const ViewInfo = () => {

    const [user, setState] = useState("");

    function getUser(str) {
        setState(str);
    }

    useEffect(() => {
        axios({
            url: '/api/users/1',
            method: 'GET'
        }).then(response => getUser(response.data))
            .catch(err => console.log(err))
    }, []);


    return (
        <div className="ViewInfo">
            <div className="ViewInfoAll">
                <div className="tr">
                    <div className="td">
                        <div className="text">이메일</div>
                        <div className="input">
                            <input value={user.email} />
                        </div>
                    </div>
                    <div>
                        <div className="td">
                            <div className="text">닉네임</div>
                            <div className="input">
                                <input value={user.nickname} />
                            </div>
                        </div>
                        <div className="nickText">이미 존재하는 닉네임입니다.</div>
                    </div>
                </div>   
                <div className="tr">
                        <div className="text">자기소개</div>
                        <div className="textarea"><textarea value={user.intro}/></div>
                </div>
            </div>
            <button type="button" onClick={axios.put('/api/users/1')}>수정하기</button>
        </div>
    );
};

export default ViewInfo;