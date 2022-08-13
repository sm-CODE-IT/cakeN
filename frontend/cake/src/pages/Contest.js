const Contest = () => {
  const str = `
  <div class="colorContainer">
  <label for="favcolor"> favorite color:</label>
  <input type="color" class="color" value="#FFC0CB">
  <input type="color" class="color" value="#FFF8DC" >
  </div>`;

  return (
    <div>
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <div dangerouslySetInnerHTML={{ __html: str }}></div>
    </div>
  );
};

export default Contest;
