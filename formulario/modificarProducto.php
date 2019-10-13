<?php
include 'conexion.php';

$codigo=$_POST['codigo'];
$nombre=$_POST['nombre'];
$stock=$_POST['stock'];
$precioCosto=$_POST['precioCosto'];
$precioVenta=$_POST['precioVenta'];

$consulta="update productos set productos('".$codigo."','".$nombre."','".$stock."')";
mysqli_query($conexion,$consulta) or die(mysqli_error());
mysqli_close($conexion);
?>

