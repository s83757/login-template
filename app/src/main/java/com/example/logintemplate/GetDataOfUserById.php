<?php
require "Database.php";
$db = new DataBase();
if (isset($_POST['self_id'])) {
    if ($db->dbConnect()) {
        $result = $db->getDataOfUserById("Users", $_POST['self_id']);
        if ($result != json_encode(["false"])) {
            echo $result;
        } else echo "Profile Update Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
