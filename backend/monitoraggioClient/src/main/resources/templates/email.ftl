<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Spring Boot Email using FreeMarker</title>
</head>
<body>
	<div style="margin-top: 10px">Buongiorno, ${company.ragione_sociale} </div>
	<h3>Ricevi questa mail perchè si è verificato un ${info.tipo_alert}</h3>
	<p>Dettagli:</p>
	<p><strong>Tipo: </strong> ${info.tipo_alert}</p>
	<p><strong>Operazione: </strong> ${info.tipo_operazione}</p>
	<p><strong>${info.tipo_operazione}: </strong> ${info.operazione}</p>
</body>
</html>