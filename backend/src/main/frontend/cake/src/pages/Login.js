import ContentHeader from "../components/ContentHeader";

const Login = () => {
  const title = "로그인";
  const content = "다양한 서비스를 이용할 수 있습니다.";

  return (
    <div className="Login">
      <ContentHeader title={title} content={content} />
      <div className="form">
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
      </div>

      <div>
        <div>
          <button>네이버로 로그인</button>
        </div>
        <div>
          <button>구글로 로그인</button>
        </div>
        <div>
          <button>카카오로 로그인</button>
        </div>
      </div>
    </div>
  );
};

export default Login;
