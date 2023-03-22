<?php
    session_start();
    $plan_id = $_SESSION['plan_id'];
    $name = $_SESSION['name'];
    $conn = new mysqli('james.cedarville.edu', 'cs3220_sp23', 'E57y6Z1FwAlraEmA', 'cs3220_sp23');
    $sql="select P.name, P.id
    from `!!!_plan` as P inner join `!!!_user` as U on P.user = U.name
    where '$name' = P.user";

    $result = $conn->query($sql);

    $myData=array(); // blank array
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
            $_SESSION['plan_id'] = $row['id'];
           array_push($myData,$row); // push rows to array $myData
        }
    } 
    

    // get array
    echo ('{"plans": ');
    echo json_encode($myData); //get json
    echo ('}');
?>