import ContentHeader from "../components/ContentHeader";


const SignUp = () => {
    const title = "회원가입";
    const content = "다양한 서비스를 이용할 수 있습니다.";
    return (
        <div className="SignUp">
            <ContentHeader title={title} content={content} />
            <div className="SignUpAll">
                <div className="text">*은 필수 입력사항입니다.</div>
                <div className="table">
                    <form>
                        <div className="tr">
                            <div className="tableData">
                                <div className="th">이메일*</div>
                                <div className="td">
                                    <div className="inLine">
                                        <input className="content"/>
                                        <button type="button" className="okButton">중복확인</button>
                                    </div>
                                </div>
                            </div>
                            <div className="tableText">사용가능한 이메일입니다.</div>
                        </div>

                        <div className="tr">
                            <div className="tableData">
                                <div className="th">비밀번호*</div>
                                <div className="td">
                                    <div className="inLine">
                                        <input className="content"/> 
                                    </div>   
                                </div>
                            </div>
                            <div className="tableText">영숫자 섞어서 어쩌구</div>

                            <div className="tableData">
                                <div className="th">비밀번호 재확인*</div>
                                <div className="td">
                                    <div className="inLine">
                                        <input className="content"/>
                                    </div>
                                </div>
                            </div>
                            <div className="tableText">일치하지 않습니다.</div>
                        </div>

                        <div className="tr">
                            <div className="tableData">
                                <div className="th">닉네임*</div>
                                <div className="td">
                                    <div className="inLine">
                                        <input className="content"/>
                                        <button type="button" className="okButton">중복확인</button>
                                    </div>
                                </div>
                            </div>
                            <div className="tableText">사용가능한 닉네임</div>
                        </div>
                        
                        <div className="tr">
                            <div className="tableData">
                                <div className="th">프로필 선택</div>
                                <div className="td">
                                    <div className="inLine">
                                        <button type="button" className="content">선택</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div className="tr">
                            <div className="tableData">
                                <div className="th">자기소개</div>
                                <div className="td">
                                    <div className="inLine">
                                        <textarea className="content"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div className="tr">
                            <button  className="signButton">가입하기</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default SignUp;