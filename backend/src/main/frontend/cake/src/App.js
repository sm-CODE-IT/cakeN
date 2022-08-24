import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login2 from "./components/Login2";
import "./App.css";

import NaviHeader from "./components/NaviHeader";
import Home from "./pages/Home";
import Intro from "./pages/Intro";
import Design from "./pages/Design";
import Contest from "./pages/Contest";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import Lettering from "./pages/Lettering";
import MyPage from "./pages/MyPage";
import MyPassword from "./pages/MyPassword";
import MyCake from "./pages/MyCake";
import { useEffect, useState } from "react";
import axios from "axios";

function App() {
  window.onscroll = function () {
    scrollFunction();
  };

  function scrollFunction() {
    if (
      document.body.scrollTop > 80 ||
      document.documentElement.scrollTop > 80
    ) {
      //document.getElementById("navbar").style.padding = "0";
      //document.getElementById("logo").style.fontSize = "25px";
      document.getElementById("navbar").style.top = "-200px";
    } else {
      //document.getElementById("navbar").style.padding = "10px 10px";
      //document.getElementById("logo").style.fontSize = "35px";
      document.getElementById("navbar").style.top = "0";
    }
  }

  /**
   * axios 방식을 이용한 Back 데이터 가져오기
   */
  // 요청 받은 정보를 담아줄 변수 선언
  const [testStr, setTestStr] = useState("");

  // 변수 초기화
  function callback(str) {
    setTestStr(str);
  }

  // 첫 번째 렌더링을 마친 후 실행
  useEffect(() => {
    axios({
      url: "/api/hello",
      method: "GET",
    })
      .then((response) => callback(response.data))
      .catch((error) => console.log(error));
  }, []);

  return (
    <div className="App">
      <NaviHeader />

      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/intro" element={<Intro />} />
          <Route path="/design" element={<Design />} />
          <Route path="/contest" element={<Contest />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signUp" element={<SignUp />} />
          <Route path="/letter" element={<Lettering />} />
          <Route path="/myPage" element={<MyPage />} />
          <Route path="/password" element={<MyPassword />} />
          <Route path="/myCake" element={<MyCake />} />
        </Routes>
      </BrowserRouter>

      {testStr}
    </div>
  );
}

export default App;
