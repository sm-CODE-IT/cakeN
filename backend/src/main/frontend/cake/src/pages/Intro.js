import ContentHeader from "../components/ContentHeader";

const Intro = () => {
    const title = "로그인";
    const content = "다양한 서비스를 이용할 수 있습니다."
    return (
        <div>
            <ContentHeader title={title} content={content}/>
            {"사이트 소개페이지"}
        </div>
    );
};

export default Intro;