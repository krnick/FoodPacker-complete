<?php
		 $name = $_POST['name'];
                $desg = $_POST['desg'];
                $sal = $_POST['salary'];
		$menu_page2=$_POST['menu_page2'];
   $sql = "INSERT INTO JsonDataTable (name,phone_number,subject,menu_page2) VALUES ('$name','$desg','$sal','$menu_page2')";

                //Importing our db connection script

                //Executing query to database

$con=mysqli_connect("localhost","root","krnick","FoodDb");
// Check connection
 require_once('dbconfig.php');
mysqli_query($con,"SET NAMES utf8");
                //Executing query to database
                if(mysqli_query($con,$sql)){
                        echo 'dy Added Successfully';
                }else{
                        echo 'Could Not Add Employee';
                }
                mysqli_close($con);




?>
