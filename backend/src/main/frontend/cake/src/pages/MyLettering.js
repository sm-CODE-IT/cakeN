import ContentHeader from "../components/ContentHeader";
import MyPageLeft from "../components/MyPageLeft";

const MyLettering = () => {
<<<<<<< HEAD


    /*return (
        
    );*/
=======
    const title = "내가 좋아요한 레터링";
    const content = "내가 좋아요한 레터링을 관리할 수 있습니다. "
    return (
        <div className="MyCake">
        <MyPageLeft/>
        <div className="MyCakeAll">
            <ContentHeader title={title} content={content} className="head"/>
        </div>
    </div>
    );
>>>>>>> newJeans
};

export default MyLettering;