// $(document).on("click", ".addImg", function () {
//    $(".add-new-photo").click();
// });

$(document).on("keyup", '.imgPath', function () {
    var path = document.querySelectorAll('.imgPath')[0];
    var img = "<img alt='producto' class='product_added' src='" + path.value + "'/>";
    $(".product_image").html(img);
});

let contenedor = document.querySelectorAll('.delete_section')[0];
function showDialog() {
    contenedor.classList.add('showDialog');
    var url = location.href;               
    location.href = "#productTable";                 
    history.replaceState(null, null, url);
}

function removeDialog() {
    contenedor.classList.remove('showDialog');
}


let update = document.querySelectorAll('.updateBtn')[0];

function showUpdateBtn(){
    update.style.display = 'initial';
}

function removeUpdateBtn(){
    update.style.display = 'none';
}