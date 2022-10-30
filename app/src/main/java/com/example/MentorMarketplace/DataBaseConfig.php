<?php

class DataBaseConfig
{
    public $servername;
    public $username;
    public $password;
    public $databasename;

    public function __construct()
    {

        $this->servername = 'match-app-database-2.cdmcvodu3d5m.us-east-1.rds.amazonaws.com';
        $this->username = 'appuser';
        $this->password = 'AleStebancito56';
        $this->databasename = 'App';

    }
}

?>