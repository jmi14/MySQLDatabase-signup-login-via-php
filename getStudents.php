<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbName = "mydatabase";

$conn = mysqli_connect($servername, $username, $password, $dbName);

if(!$conn){
	die ('mysql connection error');
}
$email = $_POST["email"];
	$password = $_POST["password"];
$sql = "SELECT id FROM students WHERE email = '$email' and password = '$password'";



$result=mysqli_query($conn, $sql) or die (mysqli_error($conn));
	if($result){
		echo json_encode(array("message"=>"Add success"));
	}
	mysqli_close($conn);


?>