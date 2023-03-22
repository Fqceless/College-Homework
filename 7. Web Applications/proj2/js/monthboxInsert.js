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
            <li>${course.courseId} ${course.courseName} - ${course.credits} credits</li>
            `;
        }
    
        monthbox += `
        </ul>
        </div>
        `;

        document.write(monthbox);

        if(y == yearsArray[0] || y == yearsArray[yearsArray.length - 1]){
            break;
        }
    }
}