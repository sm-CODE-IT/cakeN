document.title = "HELLO! From JS!"
const title = document.getElementById("title");
const titles = document.getElementsByClassName("title");
const title_tag = document.getElementsByTagName("h1");

title.innerText = "Got you!"

function handleTitleClick() {
    titles[0].style.color = "blue";
    console.log("title was clicked!");
}

title.addEventListener("click", handleTitleClick);

handleTitleClick();
console.log(titles.className);
// console.log(title.className);
