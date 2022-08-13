import heart from "../components/heart.png";
import React, { useState } from "react";

//백에서 넘어올 때 dummyList처럼 객체 배열로 넘어온다고 생각
//dummyList에는 생일 태그가, dummyList2에는 생신 태그가 모아져 있다고 가정(변경 가능)

const dummyList = [
  {
    id: "1",
    message: "HBD",
    isToggle: false,
  },
  {
    id: "2",
    message: "Happy Birthday Changmin",
    isToggle: false,
  },
  {
    id: "3",
    message: "Happy 36th Birthday!",
    isToggle: false,
  },
  {
    id: "4",
    message: "창민아! 생일 축하한다!",
  },
  {
    id: "5",
    message: 'System.out.println("생축");',
    isToggle: false,
  },
  {
    id: "6",
    message: 'printf("생축");',
    isToggle: false,
  },
];

const dummyList2 = [
  {
    id: "1",
    message: "사랑하는 엄마 생신 축하합니다",
    isToggle: false,
  },
  {
    id: "2",
    message: "생신 축하드려요~",
    isToggle: false,
  },
  {
    id: "3",
    message: "반백살 축하드립니다!",
    isToggle: false,
  },
  {
    id: "4",
    message: "인생은 70부터",
  },
  {
    id: "5",
    message: "우리가 제일 좋아하는 빵은 아빵",
    isToggle: false,
  },
  {
    id: "6",
    message: "엄마 사랑해요",
    isToggle: false,
  },
];

const Button = ({ id, message, isToggle }) => {
  const [toggle, setToggle] = useState(isToggle);
  const like = toggle ? "liked" : "disliked";

  const onChangeColor = () => {
    setToggle(!toggle);
  };

  return (
    <pre id={id} className={like} onClick={onChangeColor}>
      {message}
    </pre>
  );
};

const Lettering = () => {
  return (
    <div className="Lettering">
      <br />
      <br />
      <br />
      <br />
      <br />
      <div className="lettering_type">
        <a href="#birthday">#생일</a>
        <a href="#birthday2">#생신</a>
        <a href="#parentsday">#어버이날</a>
      </div>
      <br />
      <br />
      <br />
      <br />
      <div className="box" id="birthday">
        <h2>#생일</h2>
        <br />
        {dummyList.map((it) => (
          <Button
            key={it.id}
            {...it}
            id={it.id}
            name={it.message}
            isToggle={it.isToggle}
          />
        ))}
        <br />
        <br />
        <br />
        <br />
      </div>
      <div className="box" id="birthday2">
        <h2>#생신</h2>
        <br />
        {dummyList2.map((it) => (
          <Button
            key={it.id}
            {...it}
            id={it.id}
            name={it.message}
            isToggle={it.isToggle}
          />
        ))}
        <br />
        <br />
        <br />
        <br />
      </div>

      <div className="box" id="parentsday">
        <h2>#어버이날</h2>
      </div>
      <img className="heartimg" src={heart} />
    </div>
  );
};

export default Lettering;
