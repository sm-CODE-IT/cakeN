import ContentHeader from "../components/ContentHeader";
import { React, useState } from "react";
import { useSpring, animated } from "react-spring";
import { Link } from "react-router-dom";


const Intro = () => {
  const title = "로그인";
  const content = "다양한 서비스를 이용할 수 있습니다."

  const [open1, setOpen1] = useState(false);
  const toggleHandler1 = (e) => {
    //switch state
    setOpen1(!open1);
  };
  const [open2, setOpen2] = useState(false);
  const toggleHandler2 = (e) => {
    //switch state
    setOpen2(!open2);
  };
  const [open3, setOpen3] = useState(false);
  const toggleHandler3 = (e) => {
    //switch state
    setOpen3(!open3);
  };
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
    <div className="Intro">
      <ContentHeader title={title} content={content}/>
      <div className="main">
      <br /><br /><br /><br />


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
      
        <br />
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
       
        <br />
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
      </div>
    </div>
  );
};

export default Intro;


