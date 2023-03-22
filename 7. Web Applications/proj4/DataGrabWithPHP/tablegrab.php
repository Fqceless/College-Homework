<?php
    session_start();
    $plan_id = $_SESSION['plan_id'];
    $conn = new mysqli('james.cedarville.edu', 'cs3220_sp23', 'E57y6Z1FwAlraEmA', 'cs3220_sp23');
    $sql="select C.id, C.name, C.description, C.credits
    from (`!!!_courses` as C inner join `!!!_catalog_courses` as CC on C.ID = CC.course_id) inner join `!!!_plan` as P on CC.cat_year = P.cat_year
    where P.id = '$plan_id'";

    $result = $conn->query($sql);

    $myData=array(); // blank array
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
           //unset($row['id']); // if you dont want id use this
           array_push($myData,$row); // push rows to array $myData
        }
    } 

    // get array
    echo ('{"courses": ');
    echo json_encode($myData); //get json
    echo ('}');
?>