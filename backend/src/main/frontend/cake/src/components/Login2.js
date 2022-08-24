import React from "react";
import { signin } from "../components/ApiService";

class Login2 extends React.Component {
    constructor(props) {
        super(props);
    }

    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        const email = data.get("email");
        const password = data.get("password");
        //ApiService의 signIn 메서드를 사용해 로그인
        signin({ email: email, password: password });
    }

    render() {
        return (
            <div>
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <p>로그인 페이지</p>
                <form noValidate onSubmit={this.handleSubmit}>
                    <input type="email" name="username" placeholder="이메일" required />
                    <input
                        type="password"
                        name="password"
                        placeholder="비밀번호"
                        required
                    />
                    <button className="loginButton" type="submit">
                        {"로그인"}
                    </button>
                </form>
            </div>
        );
    }
}

export default Login2;