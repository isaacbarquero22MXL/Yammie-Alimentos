
// ICON ANIMATION 
const logo = document.getElementsByClassName('login_logo-img')[0];
let animationDone = 1;
function animation() {
    if (animationDone == 1) {
        animationDone = 0;
        logo.classList.add('freed');
        setTimeout(function () {
            logo.classList.remove('freed');
            animationDone = 1;
        }, 2500);
    }
}

const input = document.querySelectorAll('.input');
function focusInput(){
    let parent = this.parentNode.parentNode;
    parent.classList.add('focus');
}

function removeFocus(){
    let parent = this.parentNode.parentNode;
    if (this.value == "") {
        parent.classList.remove('focus');
    }
}

input.forEach(input => {
    input.addEventListener('focus', focusInput);
    input.addEventListener('blur', removeFocus);
});


// ==================Scroll Reveal Animation====================
const sr = ScrollReveal({
    origin: 'top',
    distance: '60px',
    duration: 2000,
    delay: 500
});

sr.reveal('.login_content');