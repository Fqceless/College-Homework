$(document).ready(function() {

    yearsArray = [];

    $.ajax({
        url: "http://judah.cedarville.edu/~knoerr/cs3220/termProject/getCombined.php",
        type: "GET",
        dataType: "json",
        success: function(response) {
            processPlan(response);
        }
    });

    function processPlan(response){
        $("#infoStudent").html("<p class=\"infoP\"><strong>Student:</strong> " + response.plan.student + "</p>");
        $('#infoMajor').html("<p class=\"infoP\"><strong>Major:</strong> " + response.plan.major + "</p>");
        $('#infoCatalog').html("<p class=\"infoP\"><strong>Catalog:</strong> " + response.plan.catYear + "</p>");;
        $('#infoMinor').html("<p class=\"infoP\"><strong>Minor:</strong> Biblical Studies</p>");



        let currMaxYear = response.plan.catYear;
        for (let c in response.plan.courses){
            if (response.plan.courses[c].year > currMaxYear){
                currMaxYear = response.plan.courses[c].year;
            }
        }
        for (i = response.plan.catYear; i <= currMaxYear; i++){
            yearsArray.push(new year(i));
        }

        for (let c in response.plan.courses){
            for (let y of yearsArray){
                if(response.plan.courses[c].year == y.yearNum){
                    response.plan.courses[c] = askCatalog(response.plan.courses[c], response.catalog)
                    switch(response.plan.courses[c].term){
                        case('Spring'):                           
                            y.semesters[0].courses.push(response.plan.courses[c]);
                            break;
                        case('Summer'): 
                            y.semesters[1].courses.push(response.plan.courses[c]);
                            break;
                        case('Fall'): 
                            y.semesters[2].courses.push(response.plan.courses[c]);
                            break;
                        default: break;
                    }
                }
            }
        }
        console.log(yearsArray);
    }
    function askCatalog(askingCourse, refCatalog){
        id2try = askingCourse.id;
        let index = refCatalog.courses[id2try];
        askingCourse.name = index.name;
        return askingCourse;
    }
    setTimeout(function(){
        for (let y of yearsArray) {
            for(let s of y.semesters){
                if(y == yearsArray[0]){
                    s = y.semesters[2];
                }
                let monthbox = `
                <div class="Monthbox" id="${s.term}${y.yearNum}">
                <h1>${s.term} ${y.yearNum}</h1>
                <ul>
                `;
            
                for (let course of s.courses) {
                    monthbox += `
                    <li>${course.id + " " + course.name}</li>
                    `;
                }
            
                monthbox += `
                </ul>
                </div>
                `;

                $('.grid-Schedule').append(monthbox);

                if(y == yearsArray[0] || y == yearsArray[yearsArray.length - 1]){
                    break;
                }
            }
        }
    },300)
});