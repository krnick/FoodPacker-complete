<?php
        if($_SERVER['REQUEST_METHOD']=='POST'){
                //Getting values
                $name = $_POST['name'];
                $desg = $_POST['desg'];
                $sal = $_POST['salary'];

                //Creating an sql query
               // $sql = "INSERT INTO JsonDataTable ('id', 'name', 'phone_number', 'subject') VALUES (NOW(),'$name','$desg','$sal')";
		$sql='INSERT INTO `JsonDataTable` (`id`, `name`, `phone_number`, `subject`) VALUES (NOW(), 'Hello', 'QQQQ', 'WWW')';
                //Importing our db connection script
                require_once('dbconfig.php');
mysqli_query($con,"SET NAMES utf8");
                //Executing query to database
                if(mysqli_query($con,$sql)){
                        echo 'dy Added Successfully';
                }else{
                        echo 'Could Not Add Employee';
                }
                mysqli_close($con);
        }   
