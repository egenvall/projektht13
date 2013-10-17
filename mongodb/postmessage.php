<p> message post service :p
<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
	echo "messagePointID : " . $_POST["mpid"] . "<br>";
	echo "message : " . $_POST["message"] . "<br>";
	echo "name : " . $_POST["name"] . "<br>";	

	$file = fopen("messages.txt",'a');
	fwrite($file,$_POST["mpid"] . "\n");
	fwrite($file,$_POST["name"] . "\n");
	fwrite($file,$_POST["message"] ."\n");
	fwrite($file,strval(time()) . "\n");

	fwrite($file,strval("\n"));	
	fclose($file);
}else{
	echo "get";
}
?>
