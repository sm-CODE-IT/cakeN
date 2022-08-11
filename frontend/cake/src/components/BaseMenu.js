import { useState } from "react";

const BaseMenu = () => {
  const [visible, setVisible] = useState(false);
  const [x, setX] = useState([]);
  const [y, setY] = useState([]);

  const handleClickRadioButton = (e) => {
    console.log(e.target.value);
    setX(e.target.value);
  };

  const handleClickRadioButton2 = (e) => {
    console.log(e.target.value);
    setY(e.target.value);
  };

  return (
    <div className="cakestyle">
      {/* 베이스 윗줄 */}
      {/* <img className="menu" src="https://ifh.cc/g/jqyhtd.png" /> */}
      {/* <img className="menu" src="https://ifh.cc/g/Cy46Jj.png" /> */}
      <div className="menubar">
        <label>
          <input
            type="radio"
            value="circle"
            checked={x === "circle"}
            onChange={handleClickRadioButton}
          />
          <img
            className="designImageButton"
            src="https://ifh.cc/g/7bOWmA.png"
          />
        </label>
        <label>
          <input
            type="radio"
            value="square"
            checked={x === "square"}
            onChange={handleClickRadioButton}
          />
          <img
            className="designImageButton"
            src="https://ifh.cc/g/AFyaYS.png"
          />
        </label>
        <label>
          <input
            type="radio"
            value="heart"
            checked={x === "heart"}
            onChange={handleClickRadioButton}
          />
          <img
            className="designImageButton"
            src="https://ifh.cc/g/pzRqXa.png"
          />
        </label>
      </div>
      {/* 베이스 아랫줄 */}
      <div className="menubar2">
        <label>
          <input
            type="radio"
            value="fullcolor"
            checked={y === "fullcolor"}
            onChange={handleClickRadioButton2}
          />
          <img
            className="designImageButton"
            src="https://ifh.cc/g/y5g4Tw.png"
          />
        </label>
        <label>
          <input
            type="radio"
            value="gradient"
            checked={y === "gradient"}
            onChange={handleClickRadioButton2}
          />
          <img
            className="designImageButton"
            src="https://ifh.cc/g/3ma3ps.png"
          />
        </label>
        <label>
          <input
            type="radio"
            value="half"
            checked={y === "half"}
            onChange={handleClickRadioButton2}
          />
          <img
            className="designImageButton"
            src="https://ifh.cc/g/ggKYX5.png"
          />
        </label>
      </div>
      <img className="line" src="https://ifh.cc/g/JRg2th.png" />
    </div>
  );
};

export default BaseMenu;
