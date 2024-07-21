const $exit = document.querySelector('#exit')

$exit.addEventListener('click', (e) => {
    if (!confirm('시작 화면으로 돌아가시겠습니까?')) {
        e.preventDefault()
        return
    }
})