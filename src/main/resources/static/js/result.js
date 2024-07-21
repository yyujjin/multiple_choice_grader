const $exit = document.querySelector('#exit')
const $wrong = document.querySelector("#wrong")
$exit.addEventListener('click', (e) => {
    if (!confirm('시작 화면으로 돌아가시겠습니까?')) {
        e.preventDefault()
        return
    }
})

$wrong.addEventListener("click",event => {
    if(correctAnswerCount==totalCount){
        event.preventDefault();
        alert("틀린문제가 없습니다!")
    }else{
        window.location.href = $wrong.href;
    }

})

console.log(totalCount)
console.log(correctAnswerCount)