<?php
echo "{\n";
echo '"messages":[';
$file = @fopen("messages.txt", 'r');
$f = TRUE;  

if(isset($_GET["after"])){
	$after=intval($_GET["after"]);
}else{
	$after=0;
}

$point=false;
if(isset($_GET["mpid"]) ){
	$mpid=$_GET["mpid"];
	$point=true;
}

	

while (!feof($file))
{
	$mpid_in_file = intval(fgets($file));
	$nameLine = fgets($file);
	$messageLine = fgets($file);
	$timeLine = fgets($file);
	$time = intval($timeLine);
	if(!$point || $mpid_in_file == $mpid){
		if($time > $after){
		        if(!$f){
	        	        echo ",\n";
		        }else{
		                $f = FALSE; 
	       		 }
	
			echo " {\n";
			echo '   "mpid":"'. 
strval($mpid_in_file).'",'."\n";
			echo '   "name":"' . str_replace(array("\r","\n"), '',$nameLine) . '",' . "\n";
			echo '   "message":"' . str_replace(array("\r", "\n"), '', 
$messageLine) . '",' . "\n" ;
			echo '   "time":"'. strval($time) . '"' . "\n";
			echo ' }';
		}
	}
	
	$empty = fgets($file);		
}
echo "]}\n"

?>

