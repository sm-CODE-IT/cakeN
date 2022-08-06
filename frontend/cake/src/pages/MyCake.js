import ContentHeader from "../components/ContentHeader";

const MyCake = () => {
    const title = "마이케이크";
    const content = "다니다."
    return (
        <div className="MyCake">
            <ContentHeader title={title} content={content}/>
            {"마이 케이크 소개페이지"}
        </div>
    );
};

export default MyCake;