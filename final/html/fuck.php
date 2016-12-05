<?php

 $Myname = $_POST['Myname'];
$Dealname = $_POST['Dealname'];
$Menu = $_POST['Menu'];
$price = $_POST['price'];


   $sql = "INSERT INTO HistoryOrder (Myname,Dealname,Menu,price) VALUES('{$Myname}', '{$Dealname}','{$Menu}' ,'{$price}')";


$con=mysqli_connect("localhost","root","krnick","FoodDb");
// Check connection
mysqli_query($con,"SET NAMES utf8");



                if(mysqli_query($con,$sql)){
                        echo  '歷史紀錄 增加完成';
                }else{
                        echo '歷史紀錄 增加失敗';
                }
                mysqli_close($con);
?>
