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
});