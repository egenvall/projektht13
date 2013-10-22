<html>
<head><title>Messages</title></head>
<body>
<p> meddelanden:
<?php
echo "hej<br>";
$file = @fopen("messages.txt", 'r');  
while (!feof($file))
{
	$xPosLine = fgets($file);
	$yPosLine = fgets($file);
	$messageLine = fgets($file);
	$xPos = floatval($xPosLine);
	$yPos = floatval($yPosLine);
	echo strval($xPos) . strval($yPos) .  $messageLine . "<br>";
	$empty = fgets($file);		
}

?>
</body>
</html>

