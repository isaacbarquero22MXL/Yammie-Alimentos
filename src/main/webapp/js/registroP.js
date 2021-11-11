 $(document).on("click", ".addImg", function () {
    $(".add-new-photo").click();
 });

 $(document).on("change", '.add-new-photo', function () {
    var files = this.files;
    var element = files[0];
    var imgCodified = URL.createObjectURL(element);
    var img = "<img alt='producto' class='product_added' src='" + imgCodified + "'/>";
    $(".product_image").html(img);
});