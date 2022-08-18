const Home = () => {
  // 사이트 소개랑 홈 똑같으니까 사이트 소개 탭은 Nav에서도 js파일도 삭제하자! 라고 유민이에게 전달하기..
  // 생각해보니까 디자인페이지도 삭제해야함 하 하 하 하 하

  return (
    <div className="Home">
      <br />
      <br />
      <br />
      <br />
      <br />
      {/* <h1>cakeN에 오신 것을 환영합니다!</h1> */}

      <br />
      {/* 이쯤에 대충 케이크 이미지 넣고 */}

      {/* <h2>
        cakeN은 케이크를 제작할 때 필요한 레터링 문구를 상황별로 추천해 드리는
        사이트입니다.
      </h2> */}
      <img className="cakeLogo" src="https://ifh.cc/g/ODK4d0.png" />
      <br />
      <h2 className="mark">나만의 케이크를 만들어보세요!</h2>

      <div className="spaceship"></div>
      <h3>
        케이크를 처음 만들어 봐서 어떻게 해야 하는지 모르겠다면
        <br />
        레터링에 쓸 만한 적당한 문구가 떠오르지 않는다면 <br />
        참신한 아이디어가 필요하다면
        <br />
      </h3>
      <a href="/letter">
        <button className="toLetterButton">레터링 구경하러 가기</button>
      </a>
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
    </div>
  );
};

export default Home;
