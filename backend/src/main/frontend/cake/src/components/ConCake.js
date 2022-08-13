const ConCake = () => {
    return (
        <div className="ConCake">
            <div>
                <button>X</button>
            </div>
            <div>
               <img src={process.env.PUBLIC_URL + `images/cakes.png`} width={"150px"}alt="cakes"/>
            </div>
            <div>날짜</div>
            <div>
            <button>불러오기</button>
            </div>
        </div>
    );
};

export default ConCake;