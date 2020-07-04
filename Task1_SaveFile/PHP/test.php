<?php
ini_set('user_agent','Mozilla/4.0 (compatible; MSIE 6.0)'); 
$resp = file_get_contents($_GET['url']);
$result = json_decode($resp) ;
print_r($result->product->variants[0]->price) = "215211221";
print_r($resp);
?>