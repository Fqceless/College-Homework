$(document).ready(function() {
$.noConflict();
    $.ajax({
        url: "http://localhost/Project5php/theaccord.php",
      type: "GET",
        dataType: "json",
        crossDomain: true,
      success: function(response) {
          processReqs(response);
      }
  });

    function processReqs(response) {
        
    var accordion = $("#accordion");
    for (var c in response.categories) {
      var courseList = "";
      for (var i = 0; i < response.categories[c].courses.length; i++) {
        courseList += "<li>" + response.categories[c].courses[i].course_id + "  " + response.categories[c].courses[i].name + "</li>";
      }
      var content = "<h3>" + c + "</h3><div><ul>" + courseList + "</ul></div>";
      accordion.append(content);
    }
    accordion.accordion();
  }
  });