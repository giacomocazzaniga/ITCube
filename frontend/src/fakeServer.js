//node fakeServer.js

const express = require('express');
const bodyParser = require('body-parser');
const mysql = require('mysql');
var cors = require('cors');

const connection = mysql.createPool({
  host     : 'localhost',
  user     : 'root',
  password : '',
  database : 'itcubeproject'
});

// Starting our app.
const app = express();

app.use(cors());

app.post('/clientdetails', function (req, res) {
  //select ... from ... where id_client=... + token
  result = {
    "client" : {
        "id_client" : "1",
        "nome_client" : "DESKTOP-3874",
        "tipo_client" : "Client",
        "category" : "Sviluppo",
        "MAC_address" : "00-50-FC-A0-67-2C",
        "codice_licenza" : "ATRJ-95SX-LQQ6-IRRV",
        "classe_licenza" : "1",
        "nome_tipologia_licenza" : "Free",
        "sede" : "Venezia",
        "servizi" : {
          "attivi" : "83",
          "esecuzione" : "44",
          "problemi" : "2",
          "warnings" : "11"
        }
      }
  };
  res.send(result);
});

app.post('/login', function (req, res) {
  result = {
    "ragione_sociale":"ITCube Consulting",
    "id_company": "1",
    "emailNotify": "alert@itcubeconsulting.it",
    "token" : "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890",
    "message" : "Login avvenuto",
    "messageCode" : "0"
  };
  res.send(result);
});

app.post('/listasedi', function (req, res) {
  result = {
    "sedi" : [
      {"nome" : "1",
       "n_client" : 1},
      {"nome" : "2",
       "n_client" : 1},
      {"nome" : "3",
       "n_client" : 1},
      {"nome" : "4",
       "n_client" : 1},
      {"nome" : "5",
       "n_client" : 1},
    ],
    "token" : ""
  };
  res.send(result);
});

app.post('/editCompanyData', function (req, res) {
  console.log("/editCompanyData")
  result = {
    "token" : "",
    "message" : "Modifica dei dati avvenuta con successo",
    "messageCode" : "0"
  };
  res.send(result);
});

app.post('/listalicenze', function (req, res) {
  result = {
    "sedi" : [
      {"nome" : "1",
       "n_client" : 1},
      {"nome" : "2",
       "n_client" : 1},
      {"nome" : "3",
       "n_client" : 1},
      {"nome" : "4",
       "n_client" : 1},
      {"nome" : "5",
       "n_client" : 1},
    ],
    "token" : ""
  };
  res.send(result);
});

app.post('/listagruppi', function (req, res) {
  result = {
    "categories" : [
      {"nome" : "Client",
       "n_client" : 3},
      {"nome" : "Server",
      "n_client" : 2}
    ],
    "token" : ""
  };
  res.send(result);
});

app.post('/listatipodispositivi', function (req, res) {
  result = {
    "categories" : [
      {"nome" : "Client",
       "n_client" : 3},
      {"nome" : "Server",
      "n_client" : 2}
    ],
    "token" : ""
  };
  res.send(result);
});

app.post('/deepClient', function (req, res) {
  console.log("/deepClient")
  result = {
    "id_client" : "1",
    "nome_client" : "DESKTOP-3874",
    "tipo_client" : "Client",
    "MAC_address" : "00-50-FC-A0-67-2C",
    "codice_licenza" : "ATRJ-95SX-LQQ6-IRRV",
    "classe_licenza" : "1",
    "sede" : "Venezia",
    "servizi_attivi" : "83",
    "servizi_esecuzione" : "44",
    "servizi_problemi" : "2",
    "servizi_warnings" : "11",
    "op_attive" : "83",
    "op_esecuzione" : "44",
    "op_problemi" : "2",
    "op_warnings" : "11",
    "token" : "",
    "message": "",
    "messageCode": "0"
  };
  res.send(result);
});

app.post('/listaservizi', function (req, res) {
  console.log("/listaservizi")
  result = {
    "servizi" : [
      {"nome" : "Operazione 1",
       "attivo" : "true",
       "stato" : "1"}, //da definire gli stati, e.g. 0=disattivo 1=attivo, 2=attivo con problemi, 3=attivo con warning
      {"nome" : "Operazione 2",
       "attivo" : "true",
       "stato" : "2"},
      {"nome" : "Operazione 3",
       "attivo" : "false",
       "stato" : "0"},
      {"nome" : "Operazione 4",
       "attivo" : "true",
       "stato" : "3"},
      {"nome" : "Operazione 5",
       "attivo" : "false",
       "stato" : "0"},
      {"nome" : "Operazione 6",
       "attivo" : "false",
       "stato" : "1"}
    ],
    "token" : ""
  };
  res.send(result);
});

app.post('/getServiziOverview', function (req, res) {
  result = {
    "token" : "",
    "message": "",
    "messageCode": "0",
    "n_totali": "12",
    "n_running": "9",
    "n_stop": "1",
    "n_monitorati": "10"
  };
  console.log("/getServiziOverview");
  res.send(result);
});

app.post('/getEventiOverview', function (req, res) {
  result = {
    "token" : "",
    "message": "",
    "messageCode": "0",
    "problemi_oggi": "3",
    "warning_oggi": "2",
    "tot_per_sottocategoria": [
      {"sottocategoria" : "A",
      "numero" : "6"},
      {"sottocategoria" : "C",
      "numero" : "2"},
      {"sottocategoria" : "H",
      "numero" : "0"},
      {"sottocategoria" : "S",
      "numero" : "0"}
    ]
  };
  console.log("/getEventiOverview");
  res.send(result);
});

app.post('/signup', function (req, res) {
  result = {
    "message": "fakeResponse",
    "messageCode": "1"
  };
  console.log("signup");
  res.send(result);
});

app.post('/getServiziMonitorati', function (req, res) {
  result = {
    "servizi" : [
      {"nome_servizio" : "AdobeUpdateService",
       "description" : "",
       "date_and_time" : "2020-02-05 at 10:11:33 UTC",
       "stato" : "1"},
      {"nome_servizio" : "ActiveX Installer (AxInstSV)",
       "description" : "2",
       "date_and_time" : "2020-02-05 at 10:11:33 UTC",
       "stato" : "2"},
      {"nome_servizio" : "Adobe Genuine Software Integrity Service",
       "description" : "Adobe Genuine Software Integrity Service",
       "date_and_time" : "2020-02-05 at 10:11:33 UTC",
       "stato" : "1"},
      {"nome_servizio" : "Agent Activation Runtime_809c7bd",
       "description" : "Runtime for activating conversational agent applications",
       "date_and_time" : "2020-02-05 at 10:11:33 UTC",
       "stato" : "1"},
      {"nome_servizio" : "AdobeUpdateService",
       "description" : "",
       "date_and_time" : "2020-02-05 at 10:11:33 UTC",
       "stato" : "1"},
      {"nome_servizio" : "ActiveX Installer (AxInstSV)",
       "description" : "Provides User Account Control validation for the installation of ActiveX controls from the Internet and enables management of ActiveX control installation based on Group Policy settings. This service is started on demand and if disabled the installation of ActiveX controls will behave according to default browser settings.",
       "date_and_time" : "2020-02-05 at 10:11:33 UTC",
       "stato" : "2"},
      {"nome_servizio" : "Adobe Genuine Software Integrity Service",
       "description" : "Adobe Genuine Software Integrity Service",
       "date_and_time" : "2020-02-05 at 10:11:33 UTC",
       "stato" : "1"},
      {"nome_servizio" : "Agent Activation Runtime_809c7bd",
       "description" : "Runtime for activating conversational agent applications",
       "date_and_time" : "2020-02-05 at 10:11:33 UTC",
       "stato" : "1"},
      {"nome_servizio" : "AdobeUpdateService",
       "description" : "",
       "date_and_time" : "2020-02-05 at 10:11:33 UTC",
       "stato" : "1"},
      {"nome_servizio" : "ActiveX Installer (AxInstSV)",
       "description" : "Provides User Account Control validation for the installation of ActiveX controls from the Internet and enables management of ActiveX control installation based on Group Policy settings. This service is started on demand and if disabled the installation of ActiveX controls will behave according to default browser settings.",
       "date_and_time" : "2020-02-05 at 10:11:33 UTC",
       "stato" : "2"},
      {"nome_servizio" : "Adobe Genuine Software Integrity Service",
       "description" : "Adobe Genuine Software Integrity Service",
       "date_and_time" : "2020-02-05 at 10:11:33 UTC",
       "stato" : "1"},
      {"nome_servizio" : "Agent Activation Runtime_809c7bd",
       "description" : "Runtime for activating conversational agent applications",
       "date_and_time" : "2020-02-05 at 10:11:33 UTC",
       "stato" : "1"}
    ],
    "token" : "",
    "message": "",
    "messageCode": "0"
  };
  console.log("/getServiziMonitorati");
  res.send(result);
});

app.post('/getServiziAll', function (req, res) {
  result = {
    "servizi" : [
      {"nome_servizio" : "AdobeUpdateService",
        "description" : "",
        "date_and_time" : "2020-02-05 at 10:11:33 UTC",
        "stato" : "1",
        "monitora" : "true"},
      {"nome_servizio" : "ActiveX Installer (AxInstSV)",
        "description" : "Provides User Account Control validation for the installation of ActiveX controls from the Internet and enables management of ActiveX control installation based on Group Policy settings. This service is started on demand and if disabled the installation of ActiveX controls will behave according to default browser settings.",
        "date_and_time" : "2020-02-05 at 10:11:33 UTC",
        "stato" : "2",
        "monitora" : "true"},
      {"nome_servizio" : "Adobe Genuine Software Integrity Service",
        "description" : "Adobe Genuine Software Integrity Service",
        "date_and_time" : "2020-02-05 at 10:11:33 UTC",
        "stato" : "1",
        "monitora" : "true"},
      {"nome_servizio" : "Agent Activation Runtime_809c7bd",
        "description" : "Runtime for activating conversational agent applications",
        "date_and_time" : "2020-02-05 at 10:11:33 UTC",
        "stato" : "1",
        "monitora" : "true"},
      {"nome_servizio" : "AdobeUpdateService",
        "description" : "",
        "date_and_time" : "2020-02-05 at 10:11:33 UTC",
        "stato" : "1",
        "monitora" : "true"},
      {"nome_servizio" : "ActiveX Installer (AxInstSV)",
        "description" : "Provides User Account Control validation for the installation of ActiveX controls from the Internet and enables management of ActiveX control installation based on Group Policy settings. This service is started on demand and if disabled the installation of ActiveX controls will behave according to default browser settings.",
        "date_and_time" : "2020-02-05 at 10:11:33 UTC",
        "stato" : "2",
        "monitora" : "true"},
      {"nome_servizio" : "Adobe Genuine Software Integrity Service",
        "description" : "Adobe Genuine Software Integrity Service",
        "date_and_time" : "2020-02-05 at 10:11:33 UTC",
        "stato" : "1",
        "monitora" : "true"},
      {"nome_servizio" : "Agent Activation Runtime_809c7bd",
        "description" : "Runtime for activating conversational agent applications",
        "date_and_time" : "2020-02-05 at 10:11:33 UTC",
        "stato" : "1",
        "monitora" : "true"},
      {"nome_servizio" : "AdobeUpdateService",
        "description" : "",
        "date_and_time" : "2020-02-05 at 10:11:33 UTC",
        "stato" : "1",
        "monitora" : "true"},
      {"nome_servizio" : "Non monitorato test 1",
        "description" : "Provides User Account Control validation for the installation of ActiveX controls from the Internet and enables management of ActiveX control installation based on Group Policy settings. This service is started on demand and if disabled the installation of ActiveX controls will behave according to default browser settings.",
        "date_and_time" : "2020-02-05 at 10:11:33 UTC",
        "stato" : "2",
        "monitora" : "false"},
      {"nome_servizio" : "Non monitorato test 2",
        "description" : "Adobe Genuine Software Integrity Service",
        "date_and_time" : "2020-02-05 at 10:11:33 UTC",
        "stato" : "1",
        "monitora" : "false"},
      {"nome_servizio" : "Non monitorato test 3",
        "description" : "Runtime for activating conversational agent applications",
        "date_and_time" : "2020-02-05 at 10:11:33 UTC",
        "stato" : "1",
        "monitora" : "false"}
    ],
    "token" : "",
    "message": "",
    "messageCode": "0"
  };
  console.log("/getServiziAll");
  res.send(result);
});

app.post('/getEventi', function (req, res) {
  result = {
    "eventi" : [
      {"sottocategoria" : "A",
      "level" : "1",
      "date_and_time" : "2020-02-05 at 10:11:33 UTC",
      "date_and_time_evento" : "2020-02-05 at 11:11:33 UTC",
      "source" : "Bonjour Service",
      "id_event" : "100",
      "task_category" : "None",
      "info" : "mDNSCoreReceiveResponse: Resetting to Probing:   16 DESKTOP-RSM5I11.local. AAAA FE80:0000:0000:0000:A87E:5736:E5E0:502E"},
      {"sottocategoria" : "C",
      "level" : "2",
      "date_and_time" : "2020-02-05 at 10:11:33 UTC",
      "date_and_time_evento" : "2020-02-05 at 11:11:33 UTC",
      "source" : "SQLISService120",
      "id_event" : "274",
      "task_category" : "Service Control",
      "info" : "Microsoft SSIS Service: Registry setting specifying configuration file does not exist. Attempting to load default config file."},
      {"sottocategoria" : "A",
      "level" : "1",
      "date_and_time" : "2020-02-05 at 10:11:33 UTC",
      "date_and_time_evento" : "2020-02-05 at 11:11:33 UTC",
      "source" : "Bonjour Service",
      "id_event" : "100",
      "task_category" : "None",
      "info" : "mDNSCoreReceiveResponse: Resetting to Probing:   16 DESKTOP-RSM5I11.local. AAAA FE80:0000:0000:0000:A87E:5736:E5E0:502E"},
      {"sottocategoria" : "A",
      "level" : "2",
      "date_and_time" : "2020-02-05 at 10:11:33 UTC",
      "date_and_time_evento" : "2020-02-05 at 11:11:33 UTC",
      "source" : "SQLISService120",
      "id_event" : "274",
      "task_category" : "Service Control",
      "info" : "Microsoft SSIS Service: Registry setting specifying configuration file does not exist. Attempting to load default config file."},
      {"sottocategoria" : "C",
      "level" : "4",
      "date_and_time" : "2020-02-05 at 10:11:33 UTC",
      "date_and_time_evento" : "2020-02-05 at 11:11:33 UTC",
      "source" : "Bonjour Service",
      "id_event" : "100",
      "task_category" : "None",
      "info" : "mDNSCoreReceiveResponse: Resetting to Probing:   16 DESKTOP-RSM5I11.local. AAAA FE80:0000:0000:0000:A87E:5736:E5E0:502E"},
      {"sottocategoria" : "A",
      "level" : "8",
      "date_and_time" : "2020-02-05 at 10:11:33 UTC",
      "date_and_time_evento" : "2020-02-05 at 11:11:33 UTC",
      "source" : "SQLISService120",
      "id_event" : "274",
      "task_category" : "Service Control",
      "info" : "Microsoft SSIS Service: Registry setting specifying configuration file does not exist. Attempting to load default config file."},
      {"sottocategoria" : "A",
      "level" : "1",
      "date_and_time" : "2020-02-05 at 10:11:33 UTC",
      "date_and_time_evento" : "2020-02-05 at 11:11:33 UTC",
      "source" : "Bonjour Service",
      "id_event" : "100",
      "task_category" : "None",
      "info" : "mDNSCoreReceiveResponse: Resetting to Probing:   16 DESKTOP-RSM5I11.local. AAAA FE80:0000:0000:0000:A87E:5736:E5E0:502E"},
      {"sottocategoria" : "A",
      "level" : "16",
      "date_and_time" : "2020-02-05 at 10:11:33 UTC",
      "date_and_time_evento" : "2020-02-05 at 11:11:33 UTC",
      "source" : "SQLISService120",
      "id_event" : "274",
      "task_category" : "Service Control",
      "info" : "Microsoft SSIS Service: Registry setting specifying configuration file does not exist. Attempting to load default config file."}
    ],
    "token" : "",
    "message": "",
    "messageCode": "0"
  };
  console.log("/getEventi");
  res.send(result);
});

// Starting our server.
app.listen(3001, () => {
 console.log('http://localhost:3001/');
});
