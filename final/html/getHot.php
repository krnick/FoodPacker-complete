<?php 
	//Importing Database Script 
	require_once('dbConnect.php');
	
	//Creating sql query
	$sql = "SELECT restaurant,COUNT(restaurant) as num FROM `DataAnalize` GROUP BY restaurant ORDER by num desc";
	mysqli_query($con,"SET NAMES utf8");	
	//getting result 
	$r = mysqli_query($con,$sql);
	
	//creating a blank array 
	$result = array();
	
	//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			"restaurant"=>$row['restaurant'],
			"num"=>$row['num']
		));
	}
	
	//Displaying the array in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
