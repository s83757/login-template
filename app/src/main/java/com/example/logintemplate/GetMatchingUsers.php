<?php
require "Database.php";
$db = new DataBase();
if (isset($_POST['rating']) && isset($_POST['timezone']) && isset($_POST['language']) {
    if ($db->dbConnect()) {
        if ($db->getMatchingUsers("Users", $_POST['rating'], $_POST['timezone'], $_POST['language']).length() > 1) {
            echo "Find Matching Users Success";
        } else echo "Matching users not found";
    } else echo "Error: Database connection";
} else echo "All fields are required / Invalid inputs for matching users";
?>