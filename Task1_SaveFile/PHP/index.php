<?php

$curl = curl_init();
curl_setopt_array($curl, array(
    CURLOPT_RETURNTRANSFER => 0,
    CURLOPT_URL => $_GET['url'],
    CURLOPT_USERAGENT => 'XuanThuLab test cURL Request',
    CURLOPT_SSL_VERIFYPEER => false
));
$resp = curl_exec($curl);
// $weather = json_decode($resp);
// var_dump($weather);
// curl_close($curl);
// $jsonString= file_put_contents(curl_exec($curl));
$result = json_decode($resp);
// echo $result->product->variants[0]->price ;
// $result['product']['variants']['price'] = "3213212312" ;
?>