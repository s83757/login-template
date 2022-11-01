<?php
require "Database.php";
$db = new DataBase();
if (isset($_POST['self_id']) && isset($_POST['username']) && isset($_POST['person_name']) && isset($_POST['time_zone']) && isset($_POST['email']) && isset($_POST['primary_language']) && isset($_POST['DOB']) && isset($_POST['city']) && isset($_POST['country']) && isset($_POST['phone']) && isset($_POST['Pfp']) && isset($_POST['Bio']) && isset($_POST['language_to_teach'])) {
    if ($db->dbConnect()) {
        if ($db->sendProfileUpdate("Users", $_POST['self_id'], $_POST['username'], $_POST['person_name'], $_POST['time_zone'], $_POST['email'], $_POST['primary_language'], $_POST['DOB'], $_POST['city'], $_POST['country'], $_POST['phone'], $_POST['Pfp'], $_POST['Bio']), $_POST['language_to_teach']) {
            echo "Profile Update Success";
        } else echo "Profile Update Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
