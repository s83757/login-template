<?php
require "/var/www/html/DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
//        $dbc = new DataBaseConfig();
        $this->servername = "match-app-database-2.cdmcvodu3d5m.us-east-1.rds.amazonaws.com";
        $this->username = "appuser";
        $this->password = "AleStebancito56";
        $this->databasename = "App";
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        //echo "sql connect code " . mysqli_connect_error() . "\n";
        //if (mysqli_connect_errno($con)) {
        //    echo "Failed to connect to MySQL: " . mysqli_connect_error();
        //}
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
        #Escapes special characters in a string for use in an SQL statement, taking into account the current charset of the connection
    }

    function logIn($table, $email, $password)
    {
        $email = $this->prepareData($email);
        $password = $this->prepareData($password);
        $this->sql = "select * from " . $table . " where email = '" . $email . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        //echo mysqli_num_rows($result);
        //echo $row;
        if (mysqli_num_rows($result) != 0) {
            $dbemail = $row['email'];
            $dbpassword = $row['user_password'];
            if ($dbemail == $email && $password == $dbpassword) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $username, $email, $password)
    {
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $email = $this->prepareData($email);
        //$password = password_hash($password, PASSWORD_DEFAULT);
        $password = $this->prepareData($password_hash);
        $this->sql =
            "INSERT INTO " . $table . " (username, email, user_password) VALUES ('" . $username. "','" . $email . "','" . $password . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else {
            echo "Signup failure";
            return false;
        }
    }

    function getMatchingUsers($table, $self_id, $rating, $timezone, $primary_language, $language_to_teach)
        {
            $self_id = $this->prepareData($self_id);
            $rating = $this->prepareData($rating);
            $timezone = $this->prepareData($timezone); // this is an int
            $primary_language = $this->prepareData($primary_language);
            $language_to_teach = $this->prepareData($language_to_teach);
            $this->sql = "select * from " . $table . " where rating >= " . $rating . " and time_zone = " . $timezone . " and primary_language = '" . $primary_language . "' and language_to_teach = " . $language_to_teach . " and id != " . $self_id;
            $result = mysqli_query($this->connect, $this->sql);
            if ($result == false) {
                echo "Get matching users failure";
                return json_encode(array("false"));
            }
            $UsersArray = mysqli_fetch_all($result, MYSQLI_NUM);

            return json_encode($UsersArray);
        }
    function getSavedUsers($table, $userId) {
    //#table = savedUsers
        $userId = $this->prepareData($userId);
        $this->sql = "select * from " . $table . " where id = '" . $userId . "'";
        $result = mysqli_query($this->connect, $this->sql);
        if ($result == false) {
            echo "Get saved users failure";
                return false;
        }
        $UsersArray = mysqli_fetch_all($result, MYSQLI_NUM);
        //mysqli_close();
        return $UsersArray;
    }
    function getDataOfUserById($table, $userId) {
            $userId = $this->prepareData($userId);
            $userId = $this->prepareData($userId);
            $this->sql = "select * from " . $table . " where id = '" . $userId . "'";
            $result = mysqli_query($this->connect, $this->sql);
            if ($result == false) {
                echo "Get user data failure";
                return json_encode(["false"]);
            }
            $UsersData = mysqli_fetch_all($result, MYSQLI_NUM);
            mysqli_close();
            return json_encode($UsersData);
        }
        //mysqli_close();
        function sendProfileUpdate($table, $self_id, $username, $person_name, $time_zone, $email, $primary_language, $DOB, $city, $country, $phone, $Pfp, $Bio)
            {
                $self_id = $this->prepareData($self_id);
                $username = $this->prepareData($username);
                $person_name = $this->prepareData($person_name);
                $time_zone = $this->prepareData($time_zone);
                $email = $this->prepareData($email);
                $primary_language = $this->prepareData($primary_language);
                $DOB = $this->prepareData($DOB);
                $city = $this->prepareData($city);
                $country = $this->prepareData($country);
                $phone = $this->prepareData($phone);
                $Pfp = $this->prepareData($Pfp);
                $Bio = $this->prepareData($Bio);

                //$this->backup_query = "select * from " . $table . " Where id = '" . $self_id . "'";
                //$backup = mysqli_fetch_all(mysqli_query($this->connect, $this->backup_query), MYSQLI_NUM);
                $query = "UPDATE " . $table . " SET username = '" . $username . "', person_name = '" . $person_name . "', time_zone = '" . $time_zone . "', email = '" . $email . "', primary_language = '" . $primary_language . "', DOB = '" . $DOB . "', city = '" . $city . "', country = '" . $country . "', phone = '" . $phone . "', Pfp = '" . $Pfp . "', Bio = '" . $Bio . "'  WHERE id = " . $self_id;
                //echo $query;
                $this->sql = $query;
                $result = false;
                if (mysqli_query($this->connect, $this->sql)) {
                    $result = true;
                } else {
                    echo "Signup failure";
                    $result = false;
                }
                //mysqli_close();
                return $result;
            }

}

?>