const NaviHeader = () => {

    return (
        <header className="NaviHeader" id="navbar">
            <div className="left">
                <a href={"/"} id="logo">cakeN</a>
            </div>
            <div className="center">
                <a href="/intro" className="menu">사이트 소개</a>
                <a href="/design" className="menu">케이크 제작</a>
                <a href="/contest" className="menu">케이크 자랑</a>
                <a href="/letter" className="menu">레터링 선택</a>
            </div>
            <div className="right">
                <a href={"/login"} className="menu" >로그인</a>
                <a href={"/signUp"} className="menu" >회원가입</a>
                    
                <a href={"/myPage"}  >                
                    <img src={process.env.PUBLIC_URL + `images/profile_left.png`} alt="profile" width={60} title="마이페이지로"/>
                </a>
                <a href={"/myCake"} >
                    <img src={process.env.PUBLIC_URL + `images/profile_right.png`} alt="cake" width={60} title="마이케이크로"/>
                </a>
            </div>
            
        </header>
    );
  };
  
  export default NaviHeader;