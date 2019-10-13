<?php
include 'conexion.php';
$cedula=$_GET['cedula'];
$consulta="select nombres,apellidos,cedula,provincia,direccion from datos where cedula ='&cedula'";
$resultado=$conexion->query($consulta);
while($fila=$resultado->fetch_array()){
	$dato[]=array_map('utf8_encode',$fila);
}
echo json_encode($dato);
$resultado->close();

?>