<?php
include 'dbconfig.php';



// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
 die("Connection failed: " . $conn->connect_error);
} 

mysqli_query($conn,"SET NAMES utf8");

$sql="SELECT * FROM HistoryOrder  ORDER BY time DESC";


$result = $conn->query($sql);

if ($result->num_rows >0) {
 // output data of each row
 while($row[] = $result->fetch_assoc()) {
 
 $tem = $row;
 
 $json = json_encode($tem);
 
 
 }
 
} else {
 echo "0 results";
}
 echo $json;
$conn->close();
?>
