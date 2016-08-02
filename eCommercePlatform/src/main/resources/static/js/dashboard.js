$(function()
{
  $('.navbar-toggle').click(function()
  {
    $('.navbar-nav').toggleClass('slide-in');
    $('.side-body').toggleClass('body-slide-in');
    $('#search').removeClass('in').addClass('collapse').slideUp(200);

    // / uncomment code for absolute positioning tweek see top comment in css
    // $('.absolute-wrapper').toggleClass('slide-in');

  });

  // Remove menu for searching
  $('#search-trigger').click(function()
  {
    $('.navbar-nav').removeClass('slide-in');
    $('.side-body').removeClass('body-slide-in');

    // / uncomment code for absolute positioning tweek see top comment in css
    // $('.absolute-wrapper').removeClass('slide-in');

  });

  $("#slider-range").slider({
    range : true,
    min : 0,
    max : 500,
    values : [ 75, 300 ],
    slide : function(event, ui)
    {
      $("#amount").val("$" + ui.values[0] + " - $" + ui.values[1]);
    }
  });
  $("#amount").val(
      "$" + $("#slider-range").slider("values", 0) + " - $"
          + $("#slider-range").slider("values", 1));
  
  $("body").on("click", "button[id*='createProduct']", function () {
    var id = getId($(this));
    
    $.ajax({
      url: "dashboard/products",
      method: "post",
      type: "json",
      success: function (product) {
        console.log(product);
        
        // go through each generic product placeholder card and replace
        //  the generic id (0) with the id saved in the DB.
        $("[id*='-0']").each(function () {
          var elementId = $(this).prop("id");
          elementId = elementId.replace("\-0", "-"+product.id);
          $(this).prop("id", elementId);
        });
        
        $("#placeholderCard1-" + product.id).addClass("hidden");
        $("#placeholderCard2-" + product.id).removeClass("hidden");
      },
      error: function () {
        console.log("error");
      }
    });
  });
  
  $("body").on("click", "button[id*='addImageUrl']", function () {
    var id = getId($(this));
    var imageVal = $("#imageUrl-"+id).val();
    
    $.ajax({
      url: "dashboard/products/"+id,
      method: "post",
      type: "json",
      data: { "fieldName": "imageUrl", 
        "fieldValue": imageVal},
      success: function (product) {
        console.log(product);
        
        $("#placeholderCard2-" + product.id).addClass("hidden");
        $("#placeholderCard3-" + product.id).removeClass("hidden");
      },
      error: function () {
        console.log("error");
      }
    });
    
    /*
     1. {"imageUrl" : imageVal}   (the specific way of sending data)
     2. {"fieldName" : "imageUrl",
         "fieldValue" : imageVal} (the generic way of sending data)
      */
  });
  
  $("body").on("click", "button[id*='addShortDesc']", function () {
    var id = getId($(this));
    var shortDescVal = $("#shortDesc-"+id).val();
    
    $.ajax({
      url: "dashboard/products/"+id,
      method: "post",
      type: "json",
      data: { "fieldName": "shortDescription", 
        "fieldValue": shortDescVal},
      success: function (product) {
        console.log(product);
        $("#placeholderCard3-" + product.id).addClass("hidden");
        $("#placeholderCard4-" + product.id).removeClass("hidden");
      },
      error: function () {
        console.log("error");
      }
    });
  });
  
  $("body").on("click", "button[id*='addPrice']", function () {
    var id = getId($(this));
    var priceVal = $("#price-"+id).val();
    
    $.ajax({
      url: "dashboard/products/"+id,
      method: "post",
      type: "json",
      data: { "fieldName": "price", 
        "fieldValue": priceVal},
      success: function (product) {
        console.log(product);
        $("#placeholderCard4-" + product.id).addClass("hidden");
        showFinishedProduct(product);
        createPlaceholderCard();
      },
      error: function () {
        console.log("error");
      }
    });
  });

});

function showFinishedProduct(product) {
  $("#placeholderCard4-"+product.id).after("<div class=\"col-xs-12 col-sm-6 col-md-3 col-lg-2 card\" id=\"productCard-"+product.id+"\">" +
  		"<img class=\"center-block\" src='"+product.imageUrl+"'/>" +
  		"<p>"+product.shortDescription+"</p>" +
  		"$" + product.price +
  		"</div>");
  
}

function getId(obj) {
  var id = $(obj).prop("id");
  
  return id.split("-")[1];
}

function createPlaceholderCard() {
  $("#products").append(
    "<div id=\"placeholderCard1-0\" class=\"col-xs-12 col-sm-6 col-md-3 col-lg-2 placeholderCard step1\">"+
    "  <button id=\"createProduct-0\" class=\"btn btn-primary btn-block\">Create Product</button>"+
    "</div>"+
    "<div id=\"placeholderCard2-0\" class=\"col-xs-12 col-sm-6 col-md-3 col-lg-2 placeholderCard step2 hidden\">"+
    "  <p>Please type in the image URL: </p>"+
    "  <input type=\"text\" id=\"imageUrl-0\" placeholder=\"paste img url\"/>"+
    "  <button id=\"addImageUrl-0\" class=\"btn btn-primary btn-block\">Next</button>"+
    "</div>"+
    "<div id=\"placeholderCard3-0\" class=\"col-xs-12 col-sm-6 col-md-3 col-lg-2 placeholderCard step2 hidden\">"+
    "  <p>Please type in a short description of your product: </p>"+
    "  <input type=\"text\" id=\"shortDesc-0\" placeholder=\"this is a widget that will make your life better\"/>"+
    "  <button id=\"addShortDesc-0\" class=\"btn btn-primary btn-block\">Next</button>"+
    "</div>"+
    "<div id=\"placeholderCard4-0\" class=\"col-xs-12 col-sm-6 col-md-3 col-lg-2 placeholderCard step2 hidden\">"+
    "  <p>Please type in the price of your product: </p>"+
    "  <input type=\"text\" id=\"price-0\" placeholder=\"$40\"/>"+
    "  <button id=\"addPrice-0\" class=\"btn btn-primary btn-block\">Finish</button>"+
    "</div>");
  
}