$(document).ready(function () {

    var menu = $('nav');
    var origOffsetY = menu.offset().top;

    function scroll() {
        if ($(window).scrollTop() >= origOffsetY) {
            $('nav').addClass('fixed-top');
            $('.nav-phone').removeClass('hidden-xs-up');
        } else {
            $('nav').removeClass('fixed-top');
            $('.nav-phone').addClass('hidden-xs-up');
        }
    }
    document.onscroll = scroll;
});
