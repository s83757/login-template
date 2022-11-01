<?php
require "Database.php";
$db = new DataBase();
if (isset($_POST['email']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        $result = $db->logIn("Users", $_POST['email'], $_POST['password']);
        $result2 = json_decode($result);

        if ($result2[0]) {
            echo "Login Success!" . $result2[1];
        } else echo "Username or Password wrong";
    } else echo "Error: Database connection";
} else echo "All fields are required / Incorrect username / password";
?>