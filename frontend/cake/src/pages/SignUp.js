import ContentHeader from "../components/ContentHeader";

const SignUp = () => {
    const title = "회원가입";
    const content = "다양한 서비스를 이용할 수 있습니다.";
    return (
        <div className="SignUp">
            <ContentHeader title={title} content={content} />
            <div>
                <p>*은 필수 입력사항입니다.</p>
                <form>
                    <table border>
                        <tbody>
                            <tr>
                                <th>이메일*</th>
                                <td><input /></td>
                            </tr>
                            <tr>
                                <th>비밀번호*</th>
                                <td><input /></td>
                            </tr>
                            <tr>
                                <th>비밀번호 재확인*</th>
                                <td><input /></td>
                            </tr>
                            <tr>
                                <th>닉네임*</th>
                                <td><input /></td>
                            </tr>
                            <tr>
                                <th>프로필 선택</th>
                                <td><button>선택</button></td>
                            </tr>
                            <tr>
                                <th>자기소개</th>
                                <td><textarea /></td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colSpan={2}><button className="signBtn">가입하기</button></td>
                            </tr>
                        </tfoot>
                    </table>
                </form>
            </div>
        </div>
    );
};

export default SignUp;