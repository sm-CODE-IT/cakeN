const ContentHeader = ({title, content}) => {

    return (
      <dl className="ContentHeader">
        <dt>{title}</dt>
        <dd>{content}</dd>
      </dl>
    );
  };
  
  export default ContentHeader;