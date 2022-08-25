import ContentHeader from "../components/ContentHeader";
import DeCake from "../components/DeCake";
import MyPageLeft from "../components/MyPageLeft";

const MyCake = () => {
    const title = "내가 만든 케이크";
    const content = "내가 제작/자랑한 케이크를 관리할 수 있습니다. 왕관 표시는 자랑한 케이크입니다. "
    return (
        <div className="MyCake">
            <MyPageLeft />
            <div className="MyCakeAll">
                <ContentHeader title={title} content={content} className="head"/>
                <DeCake />
                <DeCake />
            </div>
        </div>
    );
};

export default MyCake;