<?php

 $Myname = $_POST['Myname'];
$Cname = $_POST['Cname'];
$value = $_POST['value'];
$comment = $_POST['comment'];
 

   $sql = "INSERT INTO Rating (Myname,Cname,value,comment) VALUES('{$Myname}', '{$Cname}','{$value}' ,'{$comment}')";


$con=mysqli_connect("localhost","root","krnick","FoodDb");
// Check connection
mysqli_query($con,"SET NAMES utf8");
                if(mysqli_query($con,$sql)){
                        echo  'comment 增加完成';
                }else{
                        echo 'comment 增加失敗';
                }
                mysqli_close($con);


?>
~                                                                               
~                     
