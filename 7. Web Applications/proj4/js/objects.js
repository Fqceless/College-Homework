class plan {
    constructor(planName, catYear, major, minor, studentName, currYear, currTerm, courseArray) {
        this.planName = planName;
        this.catYear = catYear;
        this.major = major;
        this.minor = minor;
        this.studentName = studentName;
        this.currYear = currYear;
        this.currTerm = currTerm;
        this.courseArray = courseArray;
    }
}

class course{
    constructor(term, year, courseId, courseName, credits){
        this.term = term;
        this.year = year;
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
    }
}

class semester{
    constructor(term){
        this.term = term;
        this.courses = [];
    }
}

class year{
    constructor(yearNum){
        this.yearNum = yearNum;
        this.semesters = [
            new semester('Spring'),
            new semester('Summer'),
            new semester('Fall')
        ];
    }
}