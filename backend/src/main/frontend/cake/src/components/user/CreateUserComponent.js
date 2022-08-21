import React, { Component } from "react";
import ApiService from "../ApiService";

class CreateUserComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      pw: "",
      pwConfirm: "",
      intro: "",
      image: "",
      nickname: "",
      role: "",
      message: null,
    };
  }

  onChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value,
    });
  };

  createUser = (e) => {
    e.preventDefault();

    let user = {
      email: this.state.email,
      pw: this.state.pw,
      pwConfirm: this.state.pwConfirm,
      intro: this.state.intro,
      image: this.state.image,
      nickname: this.state.nickname,
      role: this.state.role,
    };

    ApiService.createUser(user)
      .then((res) => {
        this.setState({
          message: user.nickname + "님이 성공적으로 가입되었습니다.",
        });
        console.log(this.state.message);
        this.props.history.push("/users");
      })
      .catch((err) => {
        console.log("createUser() 에러", err);
      });
  };

  render() {
    return (
      <div>
        <h2>회원 가입</h2>

        <h4>회원 정보 입력</h4>

        <form>
          <div>
            <label for="email">로그인 ID</label>
            <input
              type="text"
              id="email"
              name="email"
              value={this.state.username}
              onChange={this.onChange}
            />
          </div>

          <div>
            <label for="pw">비밀번호</label>
            <input
              type="password"
              id="pw"
              name="pw"
              value={this.state.pw}
              onChange={this.onChange}
            />
          </div>

          <div>
            <label for="pwConfirm">비밀번호 재확인</label>
            <input
              type="password"
              id="pwConfirm"
              name="pwConfirm"
              value={this.state.pwConfirm}
              onChange={this.onChange}
            />
          </div>

          <div>
            <label for="nickname">닉네임</label>
            <input
              type="text"
              id="nickname"
              name="nickname"
              value={this.state.nickname}
              onChange={this.onChange}
            />
          </div>

          <div>
            <label for="intro">자기소개</label>
            <input
              type="text"
              id="intro"
              name="intro"
              value={this.state.intro}
              onChange={this.onChange}
            />
          </div>

          <div>
            <label for="image">프로필사진</label>
            <input
              type="text"
              id="image"
              name="image"
              value={this.state.image}
              onChange={this.onChange}
            />
          </div>

          <hr />

          <button onClick={this.createUser} type="submit">
            회원 가입
          </button>

          <button onClick={(location.href = "/")} type="button">
            취소
          </button>
        </form>
      </div>
    );
  }
}

export default CreateUserComponent;
