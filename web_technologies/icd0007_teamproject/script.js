$(document).ready(function() {
    $('#login-trigger').click(function() {
        $(this).next('#login-content').slideToggle();
        $(this).toggleClass('active');

        if ($(this).hasClass('active')) $(this).find('span').html('&#x25B2;')
        else $(this).find('span').html('&#x25BC;')
    })
});

if (document.getElementById("error").innerHTML !== null) {
    console.log(5);
    $('#login-trigger').next('#login-content').slideToggle();
    $('#login-trigger').toggleClass('active');
    $('#login-trigger').find('span').html('&#x25B2;')
}