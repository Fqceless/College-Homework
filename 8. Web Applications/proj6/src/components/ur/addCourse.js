function addCourse(event){
    var r = event.target;
    var p = r.parentNode;
    var c = p.childNodes;
    var i = 0;
    var course = "";
    while(i < c.length){
        if(c[i].draggable === true){
            course = c[i].innerHTML;
            break;
        }
        i++;
    }
    var newCourse = document.createElement("li");
    newCourse.innerHTML = course;
    newCourse.draggable = true;
    newCourse.addEventListener("dragstart", function(event){
        event.dataTransfer.setData("text", event.target.innerHTML);
    });
    newCourse.addEventListener("dragover", function(event){
        event.preventDefault();
    });
    newCourse.addEventListener("drop", function(event){
        event.preventDefault();
        var data = event.dataTransfer.getData("text");
        event.target.parentNode.insertBefore(document.getElementById(data), event.target.nextSibling);
    });
    newCourse.addEventListener("dragend", function(event){
        event.preventDefault();
    });
    p.appendChild(newCourse);
}
export default addCourse;
