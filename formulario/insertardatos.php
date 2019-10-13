<?php
include 'conexion.php';

$nombres=$_POST['nombres'];
$apellidos=$_POST['apellidos'];
$cedula=$_POST['cedula'];
$sexo=$_POST['sexo'];
$pais=$_POST['pais'];
$provincia=$_POST['provincia'];
$direccion=$_POST['direccion'];
$consulta="insert into datos values('".$nombres."','".$apellidos."','".$cedula."','".$sexo."','".$pais."','".$provincia."','".$direccion."')";
mysqli_query($conexion,$consulta) or die(mysqli_error());
mysqli_close($conexion);
?>