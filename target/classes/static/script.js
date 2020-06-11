function startProgressBarFilling() {
    const progressBar = document.querySelector('progress.overlay');
    const fillingLimit = progressBar.max * 0.8;

    var searchProgressBarInterval = setInterval(function(){
        if(progressBar.value < fillingLimit) {
            progressBar.value = progressBar.value + 1;
        };
    }, 500);
}

function completeProgressBar() {
    const progressBar = document.querySelector('progress.overlay');
    searchProgressBarInterval
    && clearInterval(searchProgressBarInterval);
    progressBar.value = 100;
}

function resetProgressBar() {
    const progressBar = document.querySelector('progress.overlay');
    searchProgressBarInterval
    && clearInterval(searchProgressBarInterval);
    progressBar.value = 0;
}
