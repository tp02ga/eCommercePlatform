$(function () {
  $("button[id*='product']").click(function () {
    var productId = $(this).prop("id");
    productId = productId.split("-")[1];
    
    window.location.href = "/products/" + productId;
  });
  
});