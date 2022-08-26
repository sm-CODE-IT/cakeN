// import ContentHeader from "../components/ContentHeader";
// import React from "react";
// import { signin } from "../components/ApiService";
// // import Button from "@material-ui/core/Button";
// // import TextField from "@material-ui/core/TextField";
// // import Grid from "@material-ui/core/Grid";
// // import Typography from "@material-ui/core/Typography";
// // import { Container } from "@material-ui/core";

// class Login2 extends React.Component {
//   constructor(props) {
//     super(props);
//     this.handleSubmit = this.handleSubmit.bind(this);
//   }

//   handleSubmit(event) {
//     event.preventDefault();
//     const data = new FormData(event.target);
//     const email = data.get("email");
//     const password = data.get("password");
//     //ApiService의 signIn 메서드를 사용해 로그인
//     signin({ email: email, password: password });
//   }

//   render() {
//     return (
//       <div>
//         <h2>로그인</h2>
//         <form noValidate onSubmit={this.handleSubmit}>
//           {/* Submit 버튼을 클릭하면 handleSubmit이 실행됨 */}
//           <input>이메일</input>
//           <input>패스워드</input>
//           <button type="submit">로그인</button>
//         </form>
//       </div>
//     );
//   }
// }

// export default Login2;

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
