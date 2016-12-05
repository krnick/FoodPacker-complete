<?php

                $id = $_POST['id'];

   $sql = "DELETE FROM  JsonDataTable  WHERE auto='$id'";


$con=mysqli_connect("localhost","root","krnick","FoodDb");
// Check connection

                if(mysqli_query($con,$sql)){
                        echo  'delete ok';
                }else{
                        echo 'Could Not delete';
                }
                mysqli_close($con);


?>
