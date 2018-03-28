<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
$servername = "localhost"; 
$username = "root";  
$password = "";  
$dbname = "mydatabase";

$conn = mysqli_connect($servername, $username, $password, $dbname) or die ('Error connecting to MySQL Server!');

if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
	registration();
}

function registration()
{
	global $conn;
	$name = $_POST["name"];
	$email = $_POST["email"];
	$password = $_POST["password"];
	
	$query = " INSERT INTO students (name, email, password) VALUES ('$name', '$email', '$password');";
	$result=mysqli_query($conn, $query) or die (mysqli_error($conn));
	if($result){
		echo json_encode(array("message"=>"Add success"));
	}
	mysqli_close($conn);
}

?>