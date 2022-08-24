import ContentHeader from "../components/ContentHeader";
import axios from 'axios';
import { useState } from "react";
import { Navigate } from "react-router-dom";



const SignUp = () => {
    const title = "회원가입";
    const content = "다양한 서비스를 이용할 수 있습니다.";

    // 각 useState
    const [email, setEmail] = useState("")
    const [nickname, setNickname] = useState("")
    const [pw, setPassword] = useState("")
    const [pwConfirm, setPwConfirm] = useState("")
    const [intro, setIntro] = useState("")

    const [emailError, setEmailError] = useState(false);
    const [passwordError, setPasswordError] = useState(true); // passwordError -> 초기 참 , 에러메세지 뜸
    const [confirmPasswordError, setConfirmPasswordError] = useState(true); // confirmPasswordError 초기 참 에러메세지


    // 각 핸들러
    const onEmailHandler = (e) => {
        const emailRegex = /^(([^<>()\[\].,;:\s@"]+(\.[^<>()\[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
        if (!e.target.value || emailRegex.test(e.target.value)) setEmailError(false);
        else setEmailError(true);
        setEmail(e.target.value);
    };
    const onNicknameHandler = (event) => {
        setNickname(event.currentTarget.value)
    }
    const onPasswordHandler = (e) => {
        const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,12}$/;
        if ((!e.target.value || (passwordRegex.test(e.target.value)))) setPasswordError(false);
        else setPasswordError(true);

        if (e.target.value === pwConfirm) setConfirmPasswordError(false);
        else setConfirmPasswordError(true);
        setPassword(e.target.value);
    };

    const onConfirmPwdHandler = (e) => {
        if (pw === e.target.value) setConfirmPasswordError(false);
        else setConfirmPasswordError(true);
        setPwConfirm(e.target.value);
    };

    const onIntroHandler = (event) => {
        setIntro(event.currentTarget.value)
    }


    //유효성 검사
    const validation = () => {
        if(!email) setEmailError(true); //에러베세지 뜸
        if(!pw) setPasswordError(true); // 에러메세지 뜸
        if(!pwConfirm) setConfirmPasswordError(true); // 에러메세지 뜸

        if(pw && pwConfirm && email) return true;
        else return false;
    }

    //회원가입 API
    const onSubmit = (e) => {
        e.preventDefault();
        if(!validation()) {
            window.alert("폼 제대로 입력하세요.");
            // return;
        };

        axios.post("/api/users", {
            email: email,
            pw: pw,
            pwConfirm: pwConfirm,
            nickname: nickname,
            intro: intro,
        }, {
            withCredentials: true
        }).then(function (response) {
            if(response.data.code === 0){
                Navigate("/login");
            }
            return response;
        }).catch(function (error) {
            console.log(error);
        });
    }
    //const onSubmit = useCallback(
    //     async (e) => {
    //       e.preventDefault()
    //       try {
    //         await axios
    //           .post(REGISTER_USERS_URL, {
    //             username: nickname,
    //             password: password,
    //             email: email,
    //           })
    //           .then((res) => {
    //             console.log('response:', res)
    //             if (res.status === 200) {
    //               router.push('/login')
    //             }
    //           })
    //       } catch (err) {
    //         console.error(err)
    //       }
    //     },
    //     [email, nickname, password, router]
    //   )

    return (
        <div className="SignUp">
            <ContentHeader title={title} content={content} />
            <div className="SignUpAll">
                <div className="text">*은 필수 입력사항입니다.</div>
                <div className="table">
                    <form onSubmit={onSubmit}>
                        <div className="tr">
                            <div className="tableData">
                                <div className="th">이메일<span>*</span></div>
                                <div className="td">
                                    <div className="inLine">
                                        <input className="content" type="email" value={email} onChange={onEmailHandler} />
                                        <button type="button" className="okButton">중복확인</button>
                                    </div>
                                </div>
                            </div>
                            {emailError && <div class="tableText">이메일 형식에 맞게 입력하세요.</div>}
                            {/* <div className="tableText">사용가능한 이메일입니다.</div> */}
                        </div>

                        <div className="tr">
                            <div className="tableData">
                                <div className="th">비밀번호<span>*</span></div>
                                <div className="td">
                                    <div className="inLine">
                                        <input className="content" type="password" value={pw} onChange={onPasswordHandler}/>
                                    </div>
                                </div>
                            </div>
                            {passwordError?
                                <div class="tableText">영어와 숫자를 조합한 8~12자의 비밀번호를 입력하세요.</div>
                                :<div class="tableText">조합 가능한 비밀번호입니다.</div>}

                            <div className="tableData">
                                <div className="th">비밀번호 재확인<span>*</span></div>
                                <div className="td">
                                    <div className="inLine">
                                        <input className="content" type="password" value={pwConfirm} onChange={onConfirmPwdHandler}/>
                                    </div>
                                </div>
                            </div>
                            {confirmPasswordError?
                                <div class="tableText">비밀번호가 일치하지 않습니다.</div>:
                                <div class="tableText">비밀번호가 일치합니다.</div>}
                        </div>

                        <div className="tr">
                            <div className="tableData">
                                <div className="th">닉네임<span>*</span></div>
                                <div className="td">
                                    <div className="inLine">
                                        <input className="content" value={nickname} onChange={onNicknameHandler} />
                                        <button type="button" className="okButton">중복확인</button>
                                    </div>
                                </div>
                            </div>
                            <div className="tableText">사용가능한 닉네임</div>
                        </div>

                        {/* <div className="tr">
                            <div className="tableData">
                                <div className="th">프로필 선택</div>
                                <div className="td">
                                    <div className="inLine">
                                        <button type="button" className="content"><img value={image} /> 선택</button>
                                    </div>
                                </div>
                            </div>
                        </div> */}

                        <div className="tr">
                            <div className="tableData">
                                <div className="th">자기소개</div>
                                <div className="td">
                                    <div className="inLine">
                                        <textarea className="content" value={intro} onChange={onIntroHandler}/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div className="tr">
                            <button  className="signButton" type="submit">가입하기</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default SignUp;