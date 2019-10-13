<?php
include('functions.php'); 
$user=$_GET['user'];
if ($resultset = getSQLResultSet("SELECT contra FROM `usuarios` WHERE usuario='$user'")) {
	
    	while ($row = $resultset->fetch_array(MYSQLI_NUM)) {
    	echo json_encode($row);
		
    	
    	}
    	
   }
   
?>
