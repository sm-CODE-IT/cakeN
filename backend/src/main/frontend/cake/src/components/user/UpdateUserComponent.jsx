import React, { Component } from "react";
import ApiService from "../ApiService";

class UpdateUserComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            userId: '',
            email: '',
            nickname: '',
            intro: '',
            image: '',
            message: null
        }
    }

    componentDidMount() {
        this.loadUser();
    }

    loadUser = () => {
        ApiService.fetchUserById(window.localStorage.getItem("userId"))
            .then(res => {
                let user = res.data;
                this.setState({
                    userId: user.userId,
                    email: user.email,
                    nickname: user.nickname,
                    intro: user.intro,
                    image: user.image,
                })
            })
            .catch(err => {
                console.log('loadUser() 에러', err);
            });
    }

    onChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    saveUser = (e) => {
        e.preventDefault();

        let user = {
            userId: this.state.userId,
            email: this.state.email,
            nickname: this.state.nickname,
            intro: this.state.intro,
            image: this.state.image,
        }

        ApiService.updateUser(user)
            .then(res => {
                this.setState({
                    message: user.nickname + '님 정보가 수정되었습니다.'
                })
                this.props.history.push('/users');
            })
            .catch(err => {
                console.log('createUser() 에러', err);
            })
    }

    render() {
        return (
            <div>

                <div>
                    <h2>개인정보 수정</h2>
                </div>

                <h4>회원 정보 입력</h4>

                <form>

                    <div>
                        <label htmlFor="email">이메일</label>
                        <input type="text" id="email" readOnly="true" defaultValue={this.state.email}/>
                    </div>
                    <div>
                        <label htmlFor="nickname">닉네임</label>
                        <input type="text" id="nickname" name="nickname" value={this.state.nickname}
                               onChange={this.onChange}/>
                    </div>
                    <div>
                        <label htmlFor="intro">자기소개</label>
                        <input type="text" id="intro" name="intro" value={this.state.intro} onChange={this.onChange}/>
                    </div>
                    <div>
                        <label htmlFor="image">프로필 사진</label>
                        <input type="text" id="image" name="image" value={this.state.image} onChange={this.onChange}/>
                    </div>

                    <hr/>

                    <button onClick={this.saveUser} type="submit">수정</button>
                    <button type="button">취소</button>


                </form>
            </div>
        );
    }
}

export default UpdateUserComponent;