const MyPageLeft = () => {
    return (
        <div className="MyPageLeft">
            <div className="profile">
                <img src={process.env.PUBLIC_URL + `images/profile.png`} alt="profile" width={150}/>
                <div className="user_nick">개발새발자</div>
                <div className="user_emil">dogfootbirdfoot</div>
            </div>
            <div className="menu">
                <div className="item">
                    <div className="section">
                        <img src={process.env.PUBLIC_URL + `images/under.png`} alt="arrow" width={20} />
                        <div>My Page</div>
                    </div>
                    <ul>
                        <li>내 프로필 보기</li>
                        <li>내 프로필 수정</li>
                        <li>비밀번호 변경</li>
                    </ul>
                </div>
                <div className="item">
                    <div className="section">
                        <img src={process.env.PUBLIC_URL + `images/top.png`} alt="arrow" width={20} />
                        <div>My Cake</div>
                    </div>
                    <ul>
                        <li>내가 만든 케이크</li>
                        <li>스크랩한 케이크</li>
                        <li>좋아요한 레터링</li>
                    </ul>
                </div>
                <div className="item">
                    <div className="section">
                        <img src={process.env.PUBLIC_URL + `images/under.png`} alt="arrow" width={20} />
                        <div>Etc</div>
                    </div>
                    <ul>
                        <li>회원탈퇴</li>
                        <li>약관정보</li>
                    </ul>
                </div>
            </div>
            <div>
                <button type="button" >로그아웃</button>
            </div>
        </div>
    );
};

export default MyPageLeft;