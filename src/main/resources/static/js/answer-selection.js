const $scrap_btns = document.querySelectorAll(".scrap-btn");
const $form = document.querySelector('form')
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

$form.addEventListener('submit', (e) => {
//    e.preventDefault()
    $form.scrap.value = JSON.stringify(scraps)
    debugger
    alert('hi')
})
