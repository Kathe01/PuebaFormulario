<?php
include 'conexion.php';

$codigo=$_POST['codigo'];
$nombre=$_POST['nombre'];
$stock=$_POST['stock'];
$precioCosto=$_POST['precioCosto'];
$precioVenta=$_POST['precioVenta'];
$ganancia=$_POST['ganancia'];

$consulta="delete from productos('".$codigo."','".$nombre."','".$stock."','".$precioCosto."','".$precioVenta."','".$ganancia."')";
mysqli_query($conexion,$consulta) or die(mysqli_error());
mysqli_close($conexion);
?>
