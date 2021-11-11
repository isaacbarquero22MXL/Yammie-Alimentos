
let navDetailsClose = document.querySelectorAll('.nav_close-details');
let details = document.querySelectorAll('.order_details');

if(navDetailsClose){
    navDetailsClose[0].addEventListener('click', () => {
        details[0].classList.remove('show_details');
    })
}

function showDetails(){
    details[0].classList.add('show_details');
}