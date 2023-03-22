<?php
session_start(); // Start a session to store user information
$_SESSION['plan_id'] = 0;
// Check if the user has submitted the login form
if(isset($_POST['login'])) {
    // Retrieve the username and password from the form
    $name = $_POST['name'];
    $password = $_POST['password'];
    
    // Validate the username and password against the database
    // You will need to replace 'your_db_name', 'your_username', 'your_password' with your actual database name, username, and password
    
    $conn = new mysqli('james.cedarville.edu', 'cs3220_sp23', 'E57y6Z1FwAlraEmA', 'cs3220_sp23');
    $query = "select P.id, P.user
    from `!!!_user` as U inner join `!!!_plan` as P on U.name = P.user
    where U.name = '$name' and U.password = '$password' and P.is_default = '1'";
    
    $result = $conn->query($query);
    $count = mysqli_num_rows($result);
    
    
    // If the login credentials are valid, set session variables and redirect to the home page

    
    if($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {    
        $_SESSION['plan_id'] = $row['id'];
        $_SESSION['name'] = $row['user'];
        }
        header('Location: home.php');
        exit;
    } else {
        // If the login credentials are invalid, display an error message
        echo "Invalid username or password.";
        
    }
    
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Login Page</title>
<link rel="stylesheet" href="./CSS/inline2.css">

</head>
<body style="text-align: center; padding: 400px; background: #000; color: #fff;">

    <h1>Login</h1>

    

    <form method="post" action="login.php">
        <label for="name">Username:</label>
        <input type="text" name="name" required>

        <label for="password">Password:</label>
        <input type="password" name="password" required>

        <button type="submit" name="login">Login</button>
    </form>

</body>
</html>