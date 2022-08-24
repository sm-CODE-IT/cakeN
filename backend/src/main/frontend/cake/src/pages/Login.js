import ContentHeader from "../components/ContentHeader";

const Login = () => {

  return (
    <div className="Login">
      {/*<ContentHeader title={title} content={content} />*/}
      <br />
      <div className="cakeWrapper">
        <br />
        <img className="cake" src="https://ifh.cc/g/fOMQxW.png" />
      </div>
      <div className="form">
        <br />
        <form>
          <div className="loginField">
            <div>
                <form method='post'>
                    <div><input type = "email" name="username" placeholder="이메일" required/></div>
                    <div><input  type = "password" name="password" placeholder="비밀번호" required /></div>
                    <div><button type = "submit">{"로그인"}</button></div>
                </form>
            </div>

            <div>
                <div><button>네이버로 로그인</button></div>
                <div><button>구글로 로그인</button></div>
            </div>

            <button className="loginButton" type="submit">
              {"로그인"}
            </button>
          </div>
        </form>
        <br />
        <br />
        <br />
        <img className="dish" src="https://ifh.cc/g/gdt06p.jpg" />
      </div>
      <br />
      <br />
      <br />
      <br />
      <div className="socialWrapper">
        <h4 className="socialTitle">간편 로그인</h4>

        <div className="divNaver">
          {/* <button className="socialButton naverButton"> */}
          <a
            href="http:/localhost:3000/login/oauth2/authorization/naver"
            class="btn btn-secondary active"
            role="button"
          >
            <img className="naverImg" src="https://ifh.cc/g/jfgfkw.png" />
          </a>
          {/* </button> */}
        </div>
        <div className="divGoogle">
          {/* <button className="socialButton googleButton"> */}
          <a
            href="/oauth2/authorization/google"
            class="btn btn-success active"
            role="button"
          >
            <img className="googleImg" src="https://ifh.cc/g/HaRDS7.jpg" />
          </a>
          {/* </button> */}
        </div>
        <div className="divKakao">
          <a
            href="/oauth2/authorization/kakao"
            class="btn btn-warning active"
            role="button"
          >
            {/* <button className="socialButton kakaoButton"> */}
            <img className="kakaoImg" src="https://ifh.cc/g/vG8GLK.png" />
            {/* </button> */}
          </a>
        </div>
      </div>
      <br />
      <br />
    </div>
  );
};

export default Login;