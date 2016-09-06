$(function () {
  $("#addToCard").click(function () {
    $.ajax({
      url: '',
      method: 'post',
      type: 'json',
      success : function () {
        // insert CSS trickery here to show a number overlay on the shopping
        //  cart icon.
      },
      error : function () {
        console.log("There was an error adding product to cart");
      }
      
    });
  });
});