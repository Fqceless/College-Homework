$(document).ready(function() {



    $.ajax({
        url: "http://localhost/Project5php/identify.php",
        type: "GET",
        dataType: "json",
        success: function(response) {
            processPlan(response);
        }
    });

    function processPlan(response){
        $("#infoStudent").html("<p class=\"infoP\"><strong>Student:</strong> " + response.plan[0].user + "</p>");
        $('#infoCatalog').html("<p class=\"infoP\"><strong>Catalog:</strong> " + response.plan[0].cat_year + "</p>");


        if(response.dmajor != null){
            $('#infoMajor').html("<p class=\"infoP\"><strong>Major:</strong> " + response.plan[0].major + ", " + response.plan[0].dmajor + "</p>");
        }
        else{
            $('#infoMajor').html("<p class=\"infoP\"><strong>Major:</strong> " + response.plan[0].major + "</p>");
        }

        if(response.dminor != null){
            $('#infoMinor').html("<p class=\"infoP\"><strong>Major:</strong> " + response.plan[0].minor + ", " + response.plan[0].dminor + "</p>");
        }
        else{
            $('#infoMinor').html("<p class=\"infoP\"><strong>Minor:</strong> " + response.plan[0].minor + "</p>");
        }
    }

});