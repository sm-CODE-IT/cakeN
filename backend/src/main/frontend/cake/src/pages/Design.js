import React, { useState } from "react";
import BaseMenu from "../components/BaseMenu";

const Design = () => {
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
    <div className="Design">
      <br /> <br /> <br /> <br /> <br /> <br /> <br />
      <div className="sectionWrapper">
        <section className="leftSection">
          <h2>여긴 캔버스 섹션</h2>
        </section>
        <section className="rightSection">
          <div className="cakestyle">
            <br />
            <button
              className="menuButton"
              onClick={() => {
                setVisible(!visible);
              }}
            >
              <img className="menu" src="https://ifh.cc/g/KMqHRw.png" />
            </button>
            {visible && <BaseMenu />}
            
          </div>
        </section>
      </div>
    </div>
  );
};

export default Design;