const DeCake = () => {
    return (
        <div className="DeCake">
             <div className="x_btn_div"><button className="x_btn">X</button></div>
             <div className="bottom">
                <div>
                <img src={process.env.PUBLIC_URL + `images/cakes.png`} width={"150px"}alt="cakes"/>
                </div>
                <div>날짜</div>
                <div>
                    <button className="btn">불러오기</button>
                </div>
             </div>
            
        </div>
    );
};

export default DeCake;