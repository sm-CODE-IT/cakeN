const ViewInfo = () => {
    return (
        <div className="ViewInfo">
            <div className="ViewInfoAll">
                <div className="tr">
                        <div className="td">
                            <div className="text">이메일</div>
                            <div className="input">
                                <input />
                            </div>
                        </div>
                        <div>
                            <div className="td">
                                <div className="text">닉네임</div>
                                <div className="input">
                                    <input /> 
                                </div>
                            </div>  
                            <div className="nickText">이미 존재하는 닉네임입니다.</div>  
                        </div>
                </div>   
                <div className="tr">
                        <div className="text">자기소개</div>
                        <div className="textarea"><textarea /></div>
                </div>
            </div>
            <button type="button">수정하기</button>
        </div>
    );
};

export default ViewInfo;