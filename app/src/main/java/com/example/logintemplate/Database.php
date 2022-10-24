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
        $password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (username, email, user_password) VALUES ('" . $username. "','" . $email . "','" . $password . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else {
            echo "Signup failure";
            return false;
        }
    }

    function getMatchingUsers($table, $rating, $timezone, $language)
        {
            $rating = $this->prepareData($rating);
            $timezone = $this->prepareData($timezone); // this is an int
            $language = $this->prepareData($language);
            $this->sql = "select * from " . $table . " where rating >= '" . $rating . "' and time_zone = '" . $timezone . "' and language = '" . $language . "'";
            $result = mysqli_query($this->connect, $this->sql);
            if ($result == false) {
                echo "Get matching users failure";
                return array("false");
            }
            $UsersArray = mysqli_fetch_all($result, MYSQLI_NUM);
            return $UsersArray;
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
        mysqli_close();
        return $UsersArray;
    }
    function getUserData($table, $userID) {
        $userId = $this->prepareData($userId);
        $userId = $this->prepareData($userId);
        $this->sql = "select * from " . $table . " where id = '" . $userId . "'";
        $result = mysqli_query($this->connect, $this->sql);
        if ($result == false) {
            echo "Get user data failure";
            return false;
        }
        $UsersData = mysqli_fetch_all($result, MYSQLI_NUM);
        mysqli_close();
        return $UsersData;
    }
    //mysqli_close();

}

?>