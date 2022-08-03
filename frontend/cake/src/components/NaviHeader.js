const NaviHeader = () => {

    return (
      <header>
          <div>
              <a href={"/"}>cakeN</a>
              {/* <a href="./../">사이트 소개</a> */}
              {/* <a href="">케이크 제작</a> */}
              {/* <a href="">케이크 자랑</a> */}
          </div>
          <div>
              <a href={"/login"}>로그인</a>
              <a href={"/signUp"}>회원가입</a>
          </div>
      </header>
    );
  };
  
  export default NaviHeader;