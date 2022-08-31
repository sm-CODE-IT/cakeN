import React, { Component } from "react";
import ApiService from "../ApiService";

class UsersListComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            users: [],
            message: null
        }
    }

    componentDidMount() {
        this.reloadUserList();
    }

    reloadUserList = () => {
        ApiService.fetchUsers()
            .then(res => {
                this.setState({
                    users: res.data
                })
            })
            .catch(err => {
                console.log("reloadUserList() error!", err);
            })
    }

    deleteUser = (id) => {
        ApiService.deleteUser(id)
            .then(res => {
                this.setState({
                    message: "User Deleted SuccessFully"
                });
                this.setState({
                    users: this.state.users.filter(user => user.userId !== id)
                });
            })
            .catch(err => {
                console.log("deletedUser() error!", err);
            })
    }

    updateUser = (id) => {
        window.localStorage.setItem("userId", id);
        this.props.history.push('/update-user');
    }

    createUser = () => {
        window.localStorage.removeItem("userId");
        this.props.history.push('/create-user');
    }

    render() {
        return (
            <div>
                <h2>User List</h2>
                <button onClick={this.createUser}>회원가입</button>
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>이메일</th>
                        <th>비밀번호</th>
                        <th>자기소개</th>
                        <th>프로필사진</th>
                        <th>닉네임</th>
                        <th>권한</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.users.map(user =>
                        <tr key={user.userId}>
                            <td>{user.email}</td>
                            <td>{user.pw}</td>
                            <td>{user.intro}</td>
                            <td>{user.image}</td>
                            <td>{user.nickname}</td>
                            <td>{user.role}</td>
                            <td>
                                <button onClick={() => this.updateUser(user.userId)}>회원정보 수정</button>
                                <button onClick={() => this.deleteUser(user.userId)}>회원 삭제</button>
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default UsersListComponent;