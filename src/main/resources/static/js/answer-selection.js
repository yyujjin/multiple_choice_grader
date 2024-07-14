const $scrap_btns = document.querySelectorAll(".scrap-btn");
const scraps = {};

$scrap_btns.forEach((btn) => {
  btn.addEventListener("click", ({ target }) => {
    const [chapter, id] = target.dataset.id.split("_");
    // TODO
    if (scraps[chapter]?.questions.indexOf(id)) {
      scraps[chapter].push(id);
    } else {
      const index = scraps[chapter].findIndex((num) => num === Number(id));
      scraps[chapter].splice(index, 1);
    }
    console.log(scraps[chapter]);
    alert("scrap");
  });
});
