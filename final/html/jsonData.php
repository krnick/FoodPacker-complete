<?php
include 'dbconfig.php';

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
 die("Connection failed: " . $conn->connect_error);
} 



$Name    = htmlspecialchars($_POST['Name']);
$Mail    = htmlspecialchars($_POST['Mail']);
$Comment = htmlspecialchars($_POST['Comment']);
$sql = "INSERT INTO comments (name, mail, msg, send) VALUES('{$Name}', '{$Mail}', '{$Comment}', NOW())";

$result = $conn->query($sql);

 
 
$conn->close();
?>
