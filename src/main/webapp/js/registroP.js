// $(document).on("click", ".addImg", function () {
//    $(".add-new-photo").click();
// });

 $(document).on("keyup", '.imgPath', function () {
    var path = document.querySelectorAll('.imgPath')[0];
    var img = "<img alt='producto' class='product_added' src='" + path.value + "'/>";
    $(".product_image").html(img);
});