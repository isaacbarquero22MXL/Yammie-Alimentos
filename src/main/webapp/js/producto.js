let images;
let imgContainer = document.querySelectorAll('.img_container');
let buttons;

let addPlus = document.getElementById('add_item');
let animationDone = 1;

function refreshImages(){
    images = document.querySelectorAll('.product_img');
    buttons = document.querySelectorAll('.addCart');

    for (let index = 0; index < images.length; index++) {
        buttons[index].addEventListener('click', () => {
            imgContainer[0].innerHTML = "<img alt='producto' src='" + images[index].src + "'>";
            add();
        }) 
    }
}

window.onload = refreshImages;

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


// submenu

let submenus = document.querySelectorAll('.submenu');
let itemSubmenu = document.querySelectorAll('.item_submenu');
let user = document.querySelectorAll('.user')[0];
let cart = document.querySelectorAll('.cart')[0];
let userShowed = 0;
let cartshowed = 0;

function showUserMenu(){
    if(cartshowed == 1){
        showCartMenu();
    }

    if(userShowed == 0){
        submenus[0].classList.add('submenu_show');
        itemSubmenu[0].style.width = "100%";
        userShowed = 1;
    }else{
        submenus[0].classList.remove('submenu_show');
        itemSubmenu[0].style.width = "2rem"
        userShowed = 0;
    }
}

function showCartMenu(){
    if(userShowed == 1){
        showUserMenu();
    }
    if(cartshowed == 0){
        submenus[1].classList.add('submenu_show');
        itemSubmenu[1].style.width = "100%";
        cartshowed = 1;
    }else{
        submenus[1].classList.remove('submenu_show');
        itemSubmenu[1].style.width = "2rem"
        cartshowed = 0;
    }
}

let main = document.getElementById('main');
main.addEventListener('click', () => {
    submenus.forEach(menu => menu.classList.remove('submenu_show'));
    itemSubmenu.forEach(item => item.style.width = "1rem");
    userShowed = 0;
    cartshowed = 0;
});


// ======== articulos carrito ======

let panelCarrito = document.querySelectorAll('.cart_items-panel');
let cartClose = document.getElementById('nav-close-cart');
 if(cartClose){
    cartClose.addEventListener('click', () => {
        panelCarrito[0].classList.remove('show_cart_item')
    })
 }

 function showCartItemPanel(){
     panelCarrito[0].classList.add('show_cart_item');
 }

let filterClose = document.getElementById('nav-close-filter');
let panelFilter = document.querySelectorAll('.filter_container');

if(filterClose){
    filterClose.addEventListener('click', () => {
        panelFilter[0].classList.remove("show_filter");
    })
}

function showFilterPanel() {
    panelFilter[0].classList.add("show_filter");
}

//  ========= scroll reveal ===========
// const sr = ScrollReveal({
//     origin: 'top',
//     distance: '60px',
//     duration: 2000,
//     delay: 300,
// })
sr.reveal('.section_title');
sr.reveal('.product_filter');
sr.reveal('.footer_copy', {origin: 'bottom'});
sr.reveal('.product_data', {interval: 100}); 