<?php
    session_start();
    $plan_id = $_SESSION['plan_id'];
    $Dmajor = "Somthing";
    $Dminor = "Sadness";
    $conn = new mysqli('james.cedarville.edu', 'cs3220_sp23', 'E57y6Z1FwAlraEmA', 'cs3220_sp23');

    //CORE MAJOR
    $sql="select M.major_name, M.course_id, C.name
    from (`!!!_plan` as P inner join `!!!_major_reqs` as M on P.major = M.major_name) inner join `!!!_courses` as C on C.id = M.course_id
    where P.id = '$plan_id' and M.course_type = 'Core'";

    $result = $conn->query($sql);

    $myData=array(); // blank array
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
           unset($row['major_name']);
           array_push($myData,$row); // push rows to array $myData
        }
    } 

    // get array
    echo ('{"categories":{ "Core":{
        "courses":');
    echo json_encode($myData); //get json
    echo ('},"Electives":{"courses":');

    //ELECTIVES MAJOR
    $sql="select M.major_name, M.course_id, C.name
    from (`!!!_plan` as P inner join `!!!_major_reqs` as M on P.major = M.major_name) inner join `!!!_courses` as C on C.id = M.course_id
    where P.id = '$plan_id' and M.course_type = 'Elective'";

    $result = $conn->query($sql);
    $myData=array(); // blank array
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
            unset($row['major_name']);
           array_push($myData,$row); // push rows to array $myData
        }
    } 

    echo json_encode($myData); //get json
    echo('},"Cognates":{"courses":');

    //COGNATES MAJOR
    $sql="select M.major_name, M.course_id, C.name
    from (`!!!_plan` as P inner join `!!!_major_reqs` as M on P.major = M.major_name) inner join `!!!_courses` as C on C.id = M.course_id
    where P.id = '$plan_id' and M.course_type = 'Cognate'";

    $result = $conn->query($sql);

    $myData=array(); // blank array
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
           //unset($row['id']); // if you dont want id use this
           array_push($myData,$row); // push rows to array $myData
        }
    } 

    echo json_encode($myData); //get json
    
    //CORE DMAJOR
    $sql="select M.major_name, M.course_id, C.name
    from (`!!!_plan` as P inner join `!!!_major_reqs` as M on P.dmajor = M.major_name) inner join `!!!_courses` as C on C.id = M.course_id
    where P.id = '$plan_id' and M.course_type = 'Core'";

    $result = $conn->query($sql);

    $myData=array(); // blank array
    if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                $Dmajor = $row['major_name'];
                //unset($row['id']); // if you dont want id use this
                array_push($myData,$row); // push rows to array $myData
            }
            
        echo('},"Core for '. $Dmajor .'":{"courses":');
        echo json_encode($myData); //get json
        echo('},"Electives for '. $Dmajor .'":{"courses":');
        //ELECTIVE DMAJOR
        $sql="select M.major_name, M.course_id, C.name
        from (`!!!_plan` as P inner join `!!!_major_reqs` as M on P.dmajor = M.major_name) inner join `!!!_courses` as C on C.id = M.course_id
        where P.id = '$plan_id' and M.course_type = 'Core'";

        $result = $conn->query($sql);

        $myData=array(); // blank array
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                //unset($row['id']); // if you dont want id use this
                array_push($myData,$row); // push rows to array $myData
            }
        } 

        echo json_encode($myData); //get json
        echo('},"Cognates for '. $Dmajor .'":{"courses":');
        //COGNATE DMAJOR
        $sql="select M.major_name, M.course_id, C.name
        from (`!!!_plan` as P inner join `!!!_major_reqs` as M on P.dmajor = M.major_name) inner join `!!!_courses` as C on C.id = M.course_id
        where P.id = '$plan_id' and M.course_type = 'Cognate'";

        $result = $conn->query($sql);

        $myData=array(); // blank array
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                //unset($row['id']); // if you dont want id use this
                array_push($myData,$row); // push rows to array $myData
            }
        } 
    
    echo json_encode($myData); //get json
    }
    echo('},"Bible Minor":{"courses":');
    //MINOR
    $sql="select M.minor_name, M.course_id, C.name
    from (`!!!_plan` as P inner join `!!!_minor_reqs` as M on P.minor = M.minor_name) inner join `!!!_courses` as C on C.id = M.course_id
    where P.id = '$plan_id'";

    $result = $conn->query($sql);

    $myData=array(); // blank array
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
           //unset($row['id']); // if you dont want id use this
           array_push($myData,$row); // push rows to array $myData
        }
    } 

    echo json_encode($myData); //get json
    //DMINOR
    $sql="select M.minor_name, M.course_id, C.name
    from (`!!!_plan` as P inner join `!!!_minor_reqs` as M on P.dminor = M.minor_name) inner join `!!!_courses` as C on C.id = M.course_id
    where P.id = '$plan_id'";

    $result = $conn->query($sql);

    $myData=array(); // blank array
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
           $Dminor = $row['major_name'];
           array_push($myData,$row); // push rows to array $myData
        }
     
        echo('},"'. $Dminor .' Minor":{"courses":');  
        echo json_encode($myData); //get json
    }
    
    echo('},"GenEds":{"courses":');
    //GENEDS
    $sql="select G.course_id, C.name
    from `!!!_gened_reqs` as G inner join `!!!_courses` as C on C.id = G.course_id";

    $result = $conn->query($sql);

    $myData=array(); // blank array
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
           //unset($row['id']); // if you dont want id use this
           array_push($myData,$row); // push rows to array $myData
        }
    } 

    echo json_encode($myData); //get json

    echo ('}}}');
?>
