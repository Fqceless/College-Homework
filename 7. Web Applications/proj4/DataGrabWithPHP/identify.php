<?php
    session_start();
    $plan_id = $_SESSION['plan_id'];
    $conn = new mysqli('james.cedarville.edu', 'cs3220_sp23', 'E57y6Z1FwAlraEmA', 'cs3220_sp23');
    $sql="select user, major, minor, cat_year, dmajor, dminor
    from `!!!_plan`
    where id = '$plan_id'";

    $result = $conn->query($sql);

    $myData=array(); // blank array
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
           //unset($row['id']); // if you dont want id use this
           array_push($myData,$row); // push rows to array $myData
        }
    } 

    // get array
    echo ('{"plan": ');
    echo json_encode($myData); //get json
    echo ('}');
?>