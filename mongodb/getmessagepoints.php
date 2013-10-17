<?php
echo "{\n";
echo '"messagepoints":[';
$file = @fopen("messagepoints.txt", 'r');
$f = TRUE;  


while (!feof($file))
{
	$mpid_in_file = intval(fgets($file));
	$x = floatval(fgets($file));
	$y = floatval(fgets($file));
	if(!$f){
	        echo ",\n";
	}else{
	        $f = FALSE; 
	}
	echo " {\n";
	echo '   "mpid":"'. strval($mpid_in_file).'",'."\n";
	echo '   "x":"' . strval($x). '",'."\n";
	echo '   "y":"' . strval($y). '"'."\n"; 
	echo ' }';
	
	$empty = fgets($file);		
}
echo "]}\n"

?>
