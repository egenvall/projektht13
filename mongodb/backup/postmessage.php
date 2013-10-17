<p> message post service :p
<?php
echo "hello world<br>";
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
	echo "post";
}else{
	echo "get";
}
?>
