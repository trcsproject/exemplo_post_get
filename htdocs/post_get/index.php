<?php

header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept');
header('Content-Type: application/json');

if(isset($_POST) && isset($_GET)){
	if(!empty($_GET)){
		
		if($_GET['json'] == "1"){
			echo "GET - OK \n";
			print_r(json_encode($_GET));	
		} else if($_GET['json'] == "2") {
			print_r(json_encode($_GET));	
		}else{
			echo "GET - OK \n";
			print_r($_GET);
		}
		
	}
	if(!empty($_POST)){
		echo "POST - OK \n";
		print_r($_POST);
	}
	//Recebendo Json
	$params = (array) json_decode(file_get_contents('php://input'), TRUE);
	if(!empty($params)){
		//Retorno em Json
		echo json_encode($params);
		echo "\n"; 	
		echo json_encode("Enviado");
	}
}


?>