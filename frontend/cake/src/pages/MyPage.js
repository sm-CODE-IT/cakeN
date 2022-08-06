import ContentHeader from "../components/ContentHeader";

const MyPage = () => {
    const title = "마이페이지";
    const content = "다양한 서비스를 이용할 수 있습니다."
    return (
        <div className="MyPage">
            <ContentHeader title={title} content={content}/>
            {"마이페이지 소개페이지"}
        </div>
    );
};

export default MyPage;