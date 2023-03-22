<?php
   session_start();
   $plan_id = $_SESSION['plan_id'];
    $conn = new mysqli('james.cedarville.edu', 'cs3220_sp23', 'E57y6Z1FwAlraEmA', 'cs3220_sp23');
    $sql="select C.id, C.name, C.credits, PC.term, PC.year 
    from (`!!!_plan` as P inner join `!!!_planned_courses` as PC on P.id = PC.plan_id) 
    inner join `!!!_courses` as C on C.id = PC.course_id 
    where P.id = '$plan_id'";

    $result = $conn->query($sql);

    $myData=array(); // blank array
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
           array_push($myData,$row); // push rows to array $myData
        }
    } 

    // get array
    echo ('{"courses": ');
    echo json_encode($myData); //get json
    echo ('}');
?>