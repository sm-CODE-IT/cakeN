//const idea_id = JSON.parse(document.getElementById('idea_id').textContent);
// 관심도 ajax
const plusBtn = document.getElementById('plus_btn');
const minusBtn = document.getElementById('minus_btn');

onClickUp = async(ideaId, type) => {
    const url = '/idea_ajax/';
    const { data } = await axios.post(url, {
        ideaId, type
    });
    likeHandleResponse(data.ideaId, data.total_likes);
}

likeHandleResponse = (ideaId, total_likes) => {
    document.getElementById(`interest_cnt-${ ideaId }`).innerHTML = `${total_likes}`
}

// IDEA 찜하기 기능 ajax
const onClickStar = async (ideaId) => {
    const url = '/idea_star_ajax/';
    const { data } = await axios.post(url, {
        ideaId
    });
    starHandleResponse(data.ideaId, data.is_star)
}

const starButton = document.querySelectorAll('.idea__star');
starButton.forEach(function(btn) {
    btn.addEventListener('click', function() {
        const [txt1, txt2, ideaId] = btn.getAttribute('id').split('-');

        onClickStar(ideaId);
    })
})

const starHandleResponse = (ideaId, is_star) => {
    const ideaStar = document.querySelector(`.idea__star--${ ideaId }`);

    if(is_star) {
        ideaStar.innerHTML = `<i class="fa-solid fa-star fa-lg"></i>`;
    } else {
        ideaStar.innerHTML = `<i class="fa-regular fa-star fa-lg"></i>`;
    }
}

// // 정렬 기능 선택한 값이 표시되도록 하는 ajax
// var sort = document.getElementById('sort_select');
// var selected_sort = sort.options[sort.selectedIndex].value;
// console.log(selected_sort);

