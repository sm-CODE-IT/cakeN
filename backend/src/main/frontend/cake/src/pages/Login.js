import ContentHeader from "../components/ContentHeader";

const Login = () => {
  const title = "로그인";
  const content = "다양한 서비스를 이용할 수 있습니다.";

  return (
    <div className="Login">
      <ContentHeader title={title} content={content} />
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
              <input type="email" placeholder="이메일" required />
            </div>
            <div>
              <input type="password" placeholder="비밀번호" required />
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
          <button className="socialButton naverButton">
            <img className="naverImg" src="https://ifh.cc/g/jfgfkw.png" />
          </button>
        </div>
        <div className="divGoogle">
          <button className="socialButton googleButton">
            <img className="googleImg" src="https://ifh.cc/g/HaRDS7.jpg" />
          </button>
        </div>
        <div className="divKakao">
          <button className="socialButton kakaoButton">
            <img className="kakaoImg" src="https://ifh.cc/g/vG8GLK.png" />
          </button>
        </div>
      </div>
      <br />
      <br />
    </div>
  );
};

export default Login;
