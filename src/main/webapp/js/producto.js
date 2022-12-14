let images;
let imgContainer = document.querySelectorAll('.img_container');
let buttons;

let addPlus = document.getElementById('add_item');
let animationDone = 1;

//function refreshImages(){
//    images = document.querySelectorAll('.product_img');
//    buttons = document.querySelectorAll('.addCart');
//
//    for (let index = 0; index < images.length; index++) {
//        buttons[index].addEventListener('click', () => {
//            imgContainer[0].innerHTML = "<img alt='producto' src='" + images[index].src + "'>";
//            add();
//        }) 
//    }
//}

function addCartImg(source) {
    imgContainer[0].innerHTML = "<img alt='producto' src='" + source + "'>";
}

//window.onload = refreshImages;

function add() {
    if (animationDone == 1) {
        addPlus.style.animation = 'add 1s ease-in-out';
        animationDone = 0;
        setTimeout(() => {
            addPlus.style.animation = 'none';
            animationDone = 1;
        }, 2000);
    }
}

function addRemoveAnimation() {
    let cartData = document.querySelectorAll('.removeCart');
    cartData.forEach(cart => {
        cart.addEventListener('click', () => {
            var parent = cart.parentNode.parentNode.parentNode;
            parent.classList.add('cart_remove');
        });
    });
}

// submenu

let submenus = document.querySelectorAll('.submenu');
let itemSubmenu = document.querySelectorAll('.item_submenu');
let user = document.querySelectorAll('.user')[0];
let cart = document.querySelectorAll('.cart')[0];
let userShowed = 0;
let cartshowed = 0;

function showUserMenu() {
    if (cartshowed == 1) {
        showCartMenu();
    }

    if (userShowed == 0) {
        submenus[0].classList.add('submenu_show');
        itemSubmenu[0].style.width = "100%";
        userShowed = 1;
    } else {
        submenus[0].classList.remove('submenu_show');
        itemSubmenu[0].style.width = "2rem"
        userShowed = 0;
    }
}

function showCartMenu() {
    if (userShowed == 1) {
        showUserMenu();
    }
    if (cartshowed == 0) {
        submenus[1].classList.add('submenu_show');
        itemSubmenu[1].style.width = "100%";
        cartshowed = 1;
    } else {
        submenus[1].classList.remove('submenu_show');
        itemSubmenu[1].style.width = "2rem"
        cartshowed = 0;
    }
}

let main = document.getElementById('c');
main.addEventListener('click', () => {
    submenus.forEach(menu => menu.classList.remove('submenu_show'));
    itemSubmenu.forEach(item => item.style.width = "1rem");
    userShowed = 0;
    cartshowed = 0;
});


// ======== articulos carrito ======
let point = document.querySelectorAll('.cart_full');

function addFullCart() {
    point[0].style.opacity = "1";
}

function removeFullCart() {
    point[0].style.opacity = "0";
}

let panelCarrito = document.querySelectorAll('.cart_items-panel');
let cartClose = document.getElementById('nav-close-cart');
if (cartClose) {
    cartClose.addEventListener('click', () => {
        panelCarrito[0].classList.remove('show_cart_item')
    })
}

function showCartItemPanel() {
    panelCarrito[0].classList.add('show_cart_item');
    removeFullCart();
}

let filterClose = document.getElementById('nav-close-filter');
let panelFilter = document.querySelectorAll('.filter_container');

if (filterClose) {
    filterClose.addEventListener('click', () => {
        panelFilter[0].classList.remove("show_filter");
    })
}

function showFilterPanel() {
    panelFilter[0].classList.add("show_filter");
}

//  ========= scroll reveal ===========
//const sr = ScrollReveal({
//    origin: 'top',
//    distance: '60px',
//    duration: 2000,
//    delay: 300,
//})
sr.reveal('.section_title');
sr.reveal('.product_filter');
sr.reveal('.footer_copy', {origin: 'bottom'});
sr.reveal('.product_data', {interval: 100});


let confirmClose = document.querySelectorAll('.confirm-close');
let panelConfirm = document.querySelectorAll('.confirm_main-container');


if (confirmClose) {
    confirmClose[0].addEventListener('click', () => {
        panelConfirm[0].classList.remove("show_filter");
    });
}

function showConfirmPanel() {
    panelConfirm[0].classList.add("show_filter");
}

// ============ Confirm swiper ========= 
let confirmSwiper = new Swiper(".confirm-swiper", {
    centeredSlides: true,
    slidesPerView: 'auto',
    spaceBetween: 30,
    loop: 'true',
    pagination: {
        el: ".swiper-pagination",
        clickable: true
    },
});

function confirmAnimations() {
    var panelConfirmed = document.querySelectorAll('.confirm_container')[0];
    var panelAccepted = document.querySelectorAll('.accept_container')[0];
    panelConfirmed.classList.add('removeConfirmAnimation');
    panelAccepted.classList.add('showAcceptAnimation');
}

function removeAnimation() {
    setTimeout(() => {
        var panelConfirmed = document.querySelectorAll('.confirm_container')[0];
        var panelAccepted = document.querySelectorAll('.accept_container')[0];
        panelConfirmed.classList.remove('removeConfirmAnimation');
        panelAccepted.classList.remove('showAcceptAnimation');
    }, 500);
}

