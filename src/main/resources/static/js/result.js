const $showScrapeAnswers = document.querySelector("#showScrapeAnswers")
console.log($showScrapeAnswers.dataset.scrapeAnswerCount);

$showScrapeAnswers.addEventListener('click',e => {
    if ($showScrapeAnswers.dataset.scrapeAnswerCount==0){
        alert("스크랩 문제가 없습니다!")
        e.preventDefault()
        return
    }
})
