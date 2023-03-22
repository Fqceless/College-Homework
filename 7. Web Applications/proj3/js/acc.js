$(document).ready(function() {

    $.ajax({
      url: "http://judah.cedarville.edu/~knoerr/cs3220/termProject/getRequirements.php",
      type: "GET",
      dataType: "json",
      success: function(response) {
          processReqs(response);
      }
  });

  function processReqs(response){
    var accordion = $("#accordion");
    for (var c in response.categories) {
      var courseList = "";
      for (var i = 0; i < response.categories[c].courses.length; i++) {
        courseList += "<li>" + response.categories[c].courses[i] + "</li>";
      }
      var content = "<h3>" + c + "</h3><div><ul>" + courseList + "</ul></div>";
      accordion.append(content);
    }
    accordion.accordion();
  }
  });