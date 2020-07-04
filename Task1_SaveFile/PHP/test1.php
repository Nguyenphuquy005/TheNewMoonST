<?php
ini_set('user_agent','Mozilla/4.0 (compatible; MSIE 6.0)'); 
$resp = file_get_contents($_GET['url']);
$result = json_decode($resp) ;
$result->product->variants[0]->price = "4564567845";
$result->product->variants[1]->price = "555665566556";
header("Content-type:application/json");
echo json_encode ($result) ;

?>