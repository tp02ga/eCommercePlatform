$(function () {
  $("#addToCard").click(function () {
    $.ajax({
      url: '',
      method: 'post',
      type: 'json',
      success : function (cart) {
        // insert CSS trickery here to show a number overlay on the shopping
        //  cart icon.
        if (cart)
        {
          console.log(cart);
          // check the cart to see how many products it has so we can update
          //  the shopping cart logo in the top right
          if (cart)
          {
            var cartSize = cart.products.length;
            $("#productsInCart").text(cartSize);
          }
          
        }
        else
        {
          // shopping cart is empty because the user hasn't logged in
          //  force them to login / create account
          window.location.href = "/login";
        }
      },
      error : function () {
        console.log("There was an error adding product to cart");
      }
      
    });
  });
});