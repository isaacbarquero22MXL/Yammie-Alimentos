// ================== MUESTRA EL MENU =================
const navMenuBar = document.getElementById('nav-menu'),
        navToggle = document.getElementById('nav-toggle'),
        navClose = document.getElementById('nav-close')

/*===== MOSTRAR MENU =====*/
/* Valida si la constante existe */
if (navToggle) {
    navToggle.addEventListener('click', () => {
        navMenuBar.classList.add('show-menu')
    })
}

/*===== OCULTAR MENU =====*/
/* Valida si la constante existe */
if (navClose) {
    navClose.addEventListener('click', () => {
        navMenuBar.classList.remove('show-menu')
    })
}

const navLink = document.querySelectorAll('.nav_link');
function linkAction() {
    navMenuBar.classList.remove('show-menu');
}
navLink.forEach(n => n.addEventListener('click', linkAction));

// =================== HOME SWIPER ===============
let homeSwiper = new Swiper(".home-swiper", {
    spaceBetween: 30,
    loop: 'true',
    pagination: {
        el: ".swiper-pagination",
        clickable: true
    },
});



// =================== Transición de scroll ============
function scrollHeader() {
    const header = document.getElementById('header');
    if (this.scrollY >= 50)
        header.classList.add('scroll-header');
    else
        header.classList.remove('scroll-header');
}
window.addEventListener('scroll', scrollHeader);

function scrollUp() {
    const scrollUp = document.getElementById('scroll-up');
    if (this.scrollY >= 260)
        scrollUp.classList.add('show-scroll');
    else
        scrollUp.classList.remove('show-scroll');
}
window.addEventListener('scroll', scrollUp);


// ==================Scroll Reveal Animation====================
const sr = ScrollReveal({
    origin: 'top',
    distance: '60px',
    duration: 2000,
    delay: 300,
})

sr.reveal('.home-swiper');
sr.reveal('.category_data, .footer_content', {interval: 100});
sr.reveal('.about_company', {origin: 'left'});
sr.reveal('.about_image', {origin: 'right'});
sr.reveal('.start', {origin: 'bottom', delay: 500});
sr.reveal('.footer_copy', {origin: 'bottom'});




