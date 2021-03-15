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

app.post('/signup', function (req, res) {
  result = {
    "message": "fakeResponse",
    "messageCode": "1"
  };
  console.log("signup");
  res.send(result);
});

// Starting our server.
app.listen(3001, () => {
 console.log('http://localhost:3001/');
});
