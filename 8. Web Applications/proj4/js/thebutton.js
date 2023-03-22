$(document).ready(function() {
let plan_id = 0;
    $.ajax({
        url: "http://judah.cedarville.edu/~laFave/TermProject/proj4/DataGrabWithPHP/userplanlistgrab.php",
        type: "GET",
        dataType: "json",
        success: function(response) {
            processPlan(response);
        }
    });

    function processPlan(response){
        setTimeout(function () {
            for (let plan of response.plans){
                let option = `<option id="${plan.id}"> ${plan.name} </option><br>`
                $('#plans').append(option);
            }
        },200)
    }
	


	    document.getElementById("plans").onchange = function(e) {
		
		$.post("http://judah.cedarville.edu/~laFave/TermProject/proj4/DataGrabWithPHP/reload.php",
    			{
      				id: e.target[2].id
    			},function(){location.reload();
			});
       
    };

});