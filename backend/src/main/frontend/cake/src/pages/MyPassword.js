import ContentHeader from "../components/ContentHeader";
import MyPageLeft from "../components/MyPageLeft";
import Password from "./Password";

const MyPassword = () => {
  const title = "비밀번호 변경";
  const content = "내 비밀번호를 변경할 수 있습니다.";
  return (
    <div className="MyPage">
      <MyPageLeft />
      <div>
        <ContentHeader title={title} content={content} />
        <Password />
      </div>
    </div>
  );
};

export default MyPassword;
