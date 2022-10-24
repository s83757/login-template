<?php
require "Database.php";
$db = new DataBase();
if (isset($_POST['rating']) && isset($_POST['time_zone']) && isset($_POST['language']) {
    if ($db->dbConnect()) {
        if ($db->getMatchingUsers("Users", $_POST['rating'], $_POST['time_zone'], $_POST['language']) != ["false"]) {
            echo "Find Matching Users Success";
        } else echo "Matching users not found";
    } else echo "Error: Database connection";
} else echo "All fields are required / Invalid inputs for matching users";
?>