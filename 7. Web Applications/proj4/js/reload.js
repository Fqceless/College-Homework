$(document).ready(function() {

    document.getElementById("plans").onchange = function() {
        $.ajax({
            url: "http://judah.cedarville.edu/~laFave/TermProject/proj4/DataGrabWithPHP/reload.php",
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