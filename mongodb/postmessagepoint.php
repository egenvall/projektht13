<p> message post service :p
<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
	echo "messagePointID : " . $_POST["mpid"] . "<br>";
	echo "x : " . $_POST["x"] . "<br>";
	echo "y : " . $_POST["y"] . "<br>";

	$file = fopen("messagepoints.txt",'a');
	fwrite($file,$_POST["mpid"] . "\n");
	fwrite($file,$_POST["x"] ."\n");
	fwrite($file,$_POST["y"] . "\n");

	fwrite($file,strval("\n"));	
	fclose($file);
}else{
	echo "get";
}
?>
