$(document).ready(function() {

    yearsArray = [];
    transferList = [];

    $.ajax({
        url: "http://judah.cedarville.edu/~laFave/TermProject/proj4/DataGrabWithPHP/plangrab.php",
        type: "GET",
        dataType: "json",
        success: function(response) {
            processPlan(response);
        }
    });

    function processPlan(response){
        let currMaxYear = 0;
        let currMinYear = 3020;
        for (let c in response.courses){
            if (response.courses[[c]].year > currMaxYear){
                currMaxYear = response.courses[[c]].year;
            }
            if (response.courses[[c]].year < currMinYear) {
                currMinYear = response.courses[[c]].year;
            }
        }
        for (i = currMinYear; i <= currMaxYear; i++){
            yearsArray.push(new year(i));
        }

        for (let c in response.courses){
            for (let y of yearsArray){
                if(response.courses[[c]].year == y.yearNum){
                    
                    switch(response.courses[[c]].term){
                        case('Spring'):                           
                            y.semesters[0].courses.push(response.courses[[c]]);
                            break;
                        case('Summer'): 
                            y.semesters[1].courses.push(response.courses[[c]]);
                            break;
                        case('Fall'): 
                            y.semesters[2].courses.push(response.courses[[c]]);
                            break;
                        case ('Transfer'):
                            transferList.push(response.courses[[c]]);
                            break;
                        default: break;
                    }
                }
            }
        }
    }

    
    

    setTimeout(function () {
        let sumCredits = 0;
        for (let course of transferList){sumCredits += Number(course.credits)}
        let monthbox = `
                <div class="Monthbox" id="Transfer">
                <h1>Transfered Credits: ${sumCredits} Credits</h1>
                <ul>
                `;

        for (let course of transferList) {
            monthbox += `
                    <li>${course.id + " " + course.name + " " + course.credits}</li>
                    `;
        }

        monthbox += `
                </ul>
                </div>
                `;

        $('.grid-Schedule').append(monthbox);

        for (let y of yearsArray) {
            for(let s of y.semesters){
                if(y == yearsArray[0]){
                    s = y.semesters[2];
                }
                let sumCredits = 0;
                for (let course of s.courses){sumCredits += Number(course.credits)}
                let monthbox = `
                <div class="Monthbox" id="${s.term}${y.yearNum}">
                <h1>${s.term} ${y.yearNum}: ${sumCredits} Credits</h1>
                <ul>
                `;
            
                for (let course of s.courses) {
                    monthbox += `
                    <li>${course.id + " " + course.name + " " + course.credits}</li>
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
    },200)
});