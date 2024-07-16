const $scrap_btns = document.querySelectorAll(".scrap-btn");
const $form = document.querySelector('form')
const $submitButtons = document.querySelectorAll("form button[type=submit]");
const $btnMarks = document.querySelectorAll(".btn-mark");

const scraps = {};

$scrap_btns.forEach((btn) => {
  btn.addEventListener("click", ({ target }) => {
    const [chapter, id] = target.dataset.id.split("_");
    if (!scraps[chapter]) {
      scraps[chapter] = { questions: [id] }
    } else if (scraps[chapter].questions.indexOf(id) < 0) {
        scraps[chapter].questions.push(id)
    } else {
      const index = scraps[chapter].questions.findIndex((num) => num === Number(id));
      scraps[chapter].questions.splice(index, 1);
    }
    console.log(scraps);
  });
});

$submitButtons.forEach(btn => {
    btn.addEventListener('click', ({target}) => {
        if (target.dataset.type === 'next') {
            $form.action = '/questions/next'
        } else {
            $form.action = '/questions/prev'
        }
    })
})

$btnMarks.forEach(btn => {
    btn.addEventListener('click', ({target}) => {
        // 알쏭달쏭 또는 모르겠다 버튼 중 하나만 선택 가능
        const beforeChecked = target.dataset.checked

        // 모든 마크 버튼 비활성화
        document.querySelectorAll(`.btn-mark[name='${target.name}']`).forEach(mark => {
            mark.dataset.checked = false
            mark.checked = false
        })

        if (beforeChecked === "true") return

        // 누른 버튼 활성화
        target.dataset.checked = true
        target.checked = true
    })
})

$form.addEventListener('submit', (e) => {
    $form.scrap.value = JSON.stringify(scraps)
})
