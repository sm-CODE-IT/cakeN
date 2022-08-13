import ContentHeader from "../components/ContentHeader";
import MyPageLeft from "../components/MyPageLeft";
import MyCake from "./MyCake";
import Password from "./Password";
import ViewInfo from "./ViewInfo";

const MyPage = () => {
    const title = "내 프로필 보기";
    const content = "내 프로필을 확인할 수 있습니다."
    return (
        <div className="MyPage">
            <MyPageLeft />
            <div className="MyPageAll">
                <ContentHeader title={title} content={content}/>
                <ViewInfo />
                <Password />
                <MyCake />
            </div>
            
        </div>
    );
};

export default MyPage;