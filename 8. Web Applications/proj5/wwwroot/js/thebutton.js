$(document).ready(function() {
    let plan_id = 0;
    $.ajax({
        url: "http://localhost/Project5php/userplanlistgrab.php",
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
		
            $.post("http://localhost/Project5php/reload.php",
    			{
      				id: e.target[2].id
    			},function(){location.reload();
			});
       
    };

});