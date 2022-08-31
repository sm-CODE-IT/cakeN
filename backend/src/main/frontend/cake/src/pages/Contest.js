// // const Contest = () => {
// //     return (
// //         <div>
// //             {"케이크 자랑."}
// //         </div>
// //     );
// // };

// // export default Contest;

// import React, { useState, useEffect } from "react";
// import axios from "axios";

// //백에서 넘어올 때 dummyList처럼 객체 배열로 넘어온다고 생각
// //dummyList에는 생일 태그가, dummyList2에는 생신 태그가 모아져 있다고 가정(변경 가능)

// // const dummyList = [
// //   {
// //     id: "1",
// //     message: "HBD",
// //     isToggle: false,
// //   },
// //   {
// //     id: "2",
// //     message: "Happy Birthday Changmin",
// //     isToggle: false,
// //   },
// //   {
// //     id: "3",
// //     message: "Happy 36th Birthday!",
// //     isToggle: false,
// //   },
// //   {
// //     id: "4",
// //     message: "창민아! 생일 축하한다!",
// //   },
// //   {
// //     id: "5",
// //     message: 'System.out.println("생축");',
// //     isToggle: false,
// //   },
// //   {
// //     id: "6",
// //     message: 'printf("생축");',
// //     isToggle: false,
// //   },
// // ];

// // const dummyList2 = [
// //   {
// //     id: "1",
// //     message: "사랑하는 엄마 생신 축하합니다",
// //     isToggle: false,
// //   },
// //   {
// //     id: "2",
// //     message: "생신 축하드려요~",
// //     isToggle: false,
// //   },
// //   {
// //     id: "3",
// //     message: "반백살 축하드립니다!",
// //     isToggle: false,
// //   },
// //   {
// //     id: "4",
// //     message: "인생은 70부터",
// //   },
// //   {
// //     id: "5",
// //     message: "우리가 제일 좋아하는 빵은 아빵",
// //     isToggle: false,
// //   },
// //   {
// //     id: "6",
// //     message: "엄마 사랑해요",
// //     isToggle: false,
// //   },
// // ];

// const Button = ({ id, message, isToggle }) => {
//   const [toggle, setToggle] = useState(isToggle);
//   const like = toggle ? "liked" : "disliked";

//   const onChangeColor = () => {
//     setToggle(!toggle);
//   };

//   return (
//     <pre id={id} className={like} onClick={onChangeColor}>
//       {message}
//     </pre>
//   );
// };

// const Contest = () => {
//   // axios 방식을 이용한 Back 데이터 가져오기

//   // 요청 받은 정보를 담아줄 변수 선언
//   const [letter, setLetter] = useState("");
//   const [letter2, setLetter2] = useState("");
//   const [letter3, setLetter3] = useState("");

//   // 변수 초기화
//   function getLetter(str) {
//     setLetter(str);
//   }

//   function getLetter2(str) {
//     setLetter2(str);
//   }

//   function getLetter3(str) {
//     setLetter3(str);
//   }

//   // 첫 번째 렌더링을 마친 후 실행
//   useEffect(() => {
//     axios({
//       url: "/api/Letter",
//       method: "GET",
//     })
//       .then((response) => getLetter(response.data))
//       .catch((error) => console.log(error));
//   }, []);

//   return (
//     <div className="Lettering">
//       <br />
//       <br />
//       <br />
//       <br />
//       <br />
//       <div className="lettering_type">
//         {/* 알록달록한 색깔로! */}
//         <a className="birthday" href="#birthday">
//           #생일
//         </a>
//         <a className="birthday2" href="#birthday2">
//           #생신
//         </a>
//         <a className="parentsday" href="#parentsday">
//           #어버이날
//         </a>
//       </div>
//       <br />
//       <br />
//       <br />
//       <br />
//       <div className="box" id="birthday">
//         <h2>#생일</h2>
//         <br />
//         {letter.map((it) => (
//           <Button
//             key={it.letterId}
//             {...it}
//             id={it.letterId}
//             name={it.content}
//             isToggle={it.isToggle}
//           />
//         ))}
//         <br />
//         <br />
//         <br />
//         <br />
//       </div>
//       <div className="box" id="birthday2">
//         <h2>#생신</h2>
//         <br />
//         {letter2.map((it) => (
//           <Button
//             key={it.letterId}
//             {...it}
//             id={it.letterId}
//             name={it.content}
//             isToggle={it.isToggle}
//           />
//         ))}
//         <br />
//         <br />
//         <br />
//         <br />
//       </div>

//       <div className="box" id="parentsday">
//         <h2>#어버이날</h2>
//         <br />
//         {letter3.map((it) => (
//           <Button
//             key={it.letterId}
//             {...it}
//             id={it.letterId}
//             name={it.content}
//             isToggle={it.isToggle}
//           />
//         ))}
//         <br />
//         <br />
//         <br />
//         <br />
//       </div>

//       {/* 앵커태그 제대로 달기! */}
//       <a href="/mypage">
//         <img
//           className="heartimg"
//           src={process.env.PUBLIC_URL + `images/heart.png`}
//           alt="heart"
//         />
//       </a>
//     </div>
//   );
// };

// export default Contest;

import ContentHeader from "../components/ContentHeader";

const Contest = () => {
  return (
    <div className="Login">
      {/*<ContentHeader title={title} content={content} />*/}
      <br />
      <div className="cakeWrapper">
        <br />
        <img className="cake" src="https://ifh.cc/g/fOMQxW.png" />
      </div>
      <div className="form">
        <br />
        <form>
          <div className="loginField">
            <div>
              <form method="post">
                <div>
                  <input
                    type="email"
                    name="username"
                    placeholder="이메일"
                    required
                  />
                </div>
                <div>
                  <input
                    type="password"
                    name="password"
                    placeholder="비밀번호"
                    required
                  />
                </div>
                <div>{/* <button type="submit">{"로그인"}</button> */}</div>
              </form>
            </div>

            <div>
              {/* <div><button>네이버로 로그인</button></div>
                <div><button>구글로 로그인</button></div> */}
            </div>

            <button className="loginButton" type="submit">
              {"로그인"}
            </button>
          </div>
        </form>
        <br />
        <br />
        <br />
        <img className="dish" src="https://ifh.cc/g/gdt06p.jpg" />
        <br />
        <br />
        <br />
      </div>
      <br />
      <br />
      <br />
      <br />
      <br />
      <div className="socialWrapper">
        <h4 className="socialTitle">간편 로그인</h4>

        <div className="divNaver">
          {/* <button className="socialButton naverButton"> */}
          <a
            href="http://localhost:3000/login/oauth2/authorization/naver"
            class="btn btn-secondary active"
            role="button"
          >
            <img className="naverImg" src="https://ifh.cc/g/jfgfkw.png" />
          </a>
          {/* </button> */}
        </div>
        <div className="divGoogle">
          {/* <button className="socialButton googleButton"> */}
          <a
            href="/oauth2/authorization/google"
            class="btn btn-success active"
            role="button"
          >
            <img className="googleImg" src="https://ifh.cc/g/HaRDS7.jpg" />
          </a>
          {/* </button> */}
        </div>
        <div className="divKakao">
          <a
            href="/oauth2/authorization/kakao"
            class="btn btn-warning active"
            role="button"
          >
            {/* <button className="socialButton kakaoButton"> */}
            <img className="kakaoImg" src="https://ifh.cc/g/vG8GLK.png" />
            {/* </button> */}
          </a>
        </div>
      </div>
      <br />
      <br />
    </div>
  );
};

export default Contest;
