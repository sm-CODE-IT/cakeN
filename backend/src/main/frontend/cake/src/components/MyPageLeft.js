import { React, useState } from "react";
import { useSpring, animated } from "react-spring";
import { Link } from "react-router-dom";

const MyPageLeft = () => {
    // 상태바꿈(1~3)
    const [open1, setOpen1] = useState(false);
    const toggleHandler1 = (e) => {
    
        setOpen1(!open1);
    };
    const [open2, setOpen2] = useState(false);
    const toggleHandler2 = (e) => {
        setOpen2(!open2);
    };
    const [open3, setOpen3] = useState(false);
    const toggleHandler3 = (e) => {
        setOpen3(!open3);
    };

    // 메뉴 애니메이션 효과(1~3)
    const openAnimation1 = useSpring({
        from: { opacity: "0", maxHeight: "30px" },
        to: { opacity: "1", maxHeight: open1 ? "150px" : "30px" },
        config: { duration: "300" }
    });
    const openAnimation2 = useSpring({
        from: { opacity: "0", maxHeight: "30px" },
        to: { opacity: "1", maxHeight: open2 ? "150px" : "30px" },
        config: { duration: "300" }
    });
    const openAnimation3 = useSpring({
        from: { opacity: "0", maxHeight: "30px" },
        to: { opacity: "1", maxHeight: open3 ? "150px" : "30px" },
        config: { duration: "300" }
    });

    // 아이콘 애니메이션 효과(1~3)
    const iconAnimation1 = useSpring({
        from: {
        transform: "rotate(0deg)"
        },
        to: {
        transform: open1 ? "rotate(180deg)" : "rotate(0deg)"
        },
        config: { duration: "120" }
    });
    const iconAnimation2 = useSpring({
        from: {
        transform: "rotate(0deg)"
        },
        to: {
        transform: open2 ? "rotate(180deg)" : "rotate(0deg)"
        },
        config: { duration: "120" }
    });
    const iconAnimation3 = useSpring({
        from: {
        transform: "rotate(0deg)"
        },
        to: {
        transform: open3 ? "rotate(180deg)" : "rotate(0deg)"
        },
        config: { duration: "120" }
    });
    
    return (
        <div className="MyPageLeft">
            <div className="profile">
                <img src={process.env.PUBLIC_URL + `images/profile.png`} alt="profile" width={150}/>
                <div className="user_nick">개발새발자</div>
                <div className="user_emil">dogfootbirdfoot</div>
            </div>

            <div className="accordion">
                <animated.div className="accordion__item" style={openAnimation1}>
                    <div className="accordion__header" onClick={toggleHandler1}>
                        <animated.i style={iconAnimation1}>
                        <img src={process.env.PUBLIC_URL + `images/under.png`} alt="arrow" width={20} />
                        </animated.i>
                        <h4>{"My Page"}</h4>                
                    </div>
                    <Link to="/myPage" className="accordion__content" >{"내 정보 수정/변경"}</Link>
                    <Link to="/password" className="accordion__content">{"비밀번호 변경"}</Link>            
                </animated.div>
      
                <animated.div className="accordion__item" style={openAnimation2}>
                    <div className="accordion__header" onClick={toggleHandler2}>
                        <animated.i style={iconAnimation2}>
                        <img src={process.env.PUBLIC_URL + `images/under.png`} alt="arrow" width={20} />
                        </animated.i>
                        <h4>{"My Cage"}</h4>                
                    </div>
                    <Link to="/myPage" className="accordion__content" >{"내가 만든 케이크"}</Link>
                    <Link to="/password" className="accordion__content">{"좋아요한 레터링"}</Link>            
                </animated.div>
       
                <animated.div className="accordion__item" style={openAnimation3}>
                    <div className="accordion__header" onClick={toggleHandler3}>
                        <animated.i style={iconAnimation3}>
                        <img src={process.env.PUBLIC_URL + `images/under.png`} alt="arrow" width={20} />
                        </animated.i>
                        <h4>{"Etc"}</h4>                
                    </div>
                    <Link to="/myPage" className="accordion__content" >{"약관 동의"}</Link>
                    <Link to="/password" className="accordion__content">{"회원 탈퇴"}</Link>            
                </animated.div>
            </div>
            <div>
                <button>로그아웃</button>
            </div>
      </div>
    );
};

export default MyPageLeft;