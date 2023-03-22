<?php
   session_start();
if	($_POST['id'] != 0){
   $plan_id = $_POST['id'];
   $_SESSION['plan_id'] = $plan_id;
}
?>