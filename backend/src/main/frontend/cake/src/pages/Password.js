const Password = () => {
    return (
        <div className="Password">
            <div className="PasswordAll">
                <div className="tr">
                    <div className="td">
                        <div className="text">현재 비밀번호</div>
                        <div className="input"><input /></div>
                    </div>
                </div>
                <div className="new">
                <div className="tr">
                    <div className="td">
                        <div className="text">새 비밀번호</div>
                        <div className="input">
                            <input />
                            <div className="passText">영문자 어쩌구</div>
                        </div>
                        
                    </div>
                    
                </div>
                <div className="tr">
                    <div className="td">
                        <div className="text">새 비밀번호 확인</div>
                        <div className="input">
                            <input />
                            <div className="passText">일치하지 않습니다.</div>
                        </div>
                    </div>
                </div>   
                
            </div>
            </div>
            <button type="button">변경하기</button>
        </div>
    );
};

export default Password;