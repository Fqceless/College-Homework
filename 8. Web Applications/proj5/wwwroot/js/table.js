$(document).ready(function() {

    $.noConflict();
    $.ajax({
        url: "http://localhost/Project5php/tablegrab.php",
        type: "GET",
        dataType: "json",
        success: function(response) {
            processCat(response);
        }
    });
    

    function processCat(response){

        let dataList = [];
    for (c in response.courses){
        dataList.push(response.courses[[c]]);
        }
        $.noConflict();
    let catalogTable = $('#catalogTable').DataTable({
        data: dataList,
      columns: [
        { data: 'id', title: 'Course ID' },
        { data: 'name', title: 'Course Name' },
        { data: 'description', title: 'Description' },
        { data: 'credits', title: 'Credits' },
        // add more columns
      ]
    });


    $('#searchBox').on('keyup', function() {
      catalogTable.search($(this).val()).draw();
    });
}
  });