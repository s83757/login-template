<?php
require "Database.php";
$db = new DataBase();
if (isset($_POST['self_id']) && isset($_POST['rating']) && isset($_POST['time_zone']) && isset($_POST['primary_language'])) {
    if ($db->dbConnect()) {
        $result = $db->getMatchingUsers("Users", $_POST['self_id'], $_POST['rating'], $_POST['time_zone'], $_POST['primary_language']);
        if ($result != json_encode(["false"])) {
            echo $result;
        } else echo "Matching users not found";
    } else echo "Error: Database connection";
} else echo "All fields are required / Invalid inputs for matching users";
?>