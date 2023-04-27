$(document).ready(function() {

    document.getElementById("plans").onchange = function() {
        $.ajax({
            url: "http://localhost/Project5php/reload.php",
            type: "GET",
            dataType: "json",
            success: function(response) {
                processClick(response);
            }
        });
    
        function processClick(response){
            location.reload();
        }
    };

});