$(document).ready(function () {

    $.ajax({
        url: "http://localhost/Project5php/home.php",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $(window).append(response);
        }
    });
});