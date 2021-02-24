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

/*// Creating a GET route that returns data from the 'users' table.
app.get('/elenco_client', function (req, res) {
    // Connecting to the database.
    connection.getConnection(function (err, connection) {
    console.log("/elenco_client")
    // Executing the MySQL query
    connection.query('SELECT * FROM elenco_client', function (error, results, fields) {
      // If some error occurs, we throw an error.
      if (error) throw error;

      // Getting the 'response' from the database and sending it to our route. This is were the data is.
      res.send(results)
    });
  });
});*/



app.post('/login', function (req, res) {
  result = {
    "nome_company":"ITCube Consulting",
    "id_company": "1",
    "email": "francesco.done@itcubeconsulting.it",
    "emailNotify": "alert@itcubeconsulting.it",
    "client" : [
      {
        "id_client" : "1",
        "nome_client" : "DESKTOP-3874",
        "tipo_client" : "Client",
        "category" : "Sviluppo",
        "MAC_address" : "00-50-FC-A0-67-2C",
        "codice_licenza" : "ATRJ-95SX-LQQ6-IRRV",
        "classe_licenza" : "0",
        "nome_tipologia_licenza" : "Free",
        "sede" : "Venezia",
        "servizi" : {
          "attivi" : "83",
          "esecuzione" : "44",
          "problemi" : "2",
          "warnings" : "11"
        }
      }, {
        "id_client" : "2",
        "nome_client" : "DESKTOP-2230",
        "tipo_client" : "Client",
        "category" : "Sviluppo",
        "MAC_address" : "00-56-SC-E4-C2-WC",
        "codice_licenza" : "ZQCQ-B0EC-TW8N-YZFT",
        "classe_licenza" : "0",
        "nome_tipologia_licenza" : "Free",
        "sede" : "Venezia",
        "servizi" : {
          "attivi" : "83",
          "esecuzione" : "44",
          "problemi" : "2",
          "warnings" : "11"
        }
      }, {
        "id_client" : "3",
        "nome_client" : "SERVER-3512",
        "tipo_client" : "Server",
        "category" : "Produzione",
        "MAC_address" : "00-41-5E-E4-2Z-C2",
        "codice_licenza" : "LE1P-42KI-PY9L-1FZP",
        "classe_licenza" : "2",
        "nome_tipologia_licenza" : "Pro",
        "sede" : "Milano",
        "servizi" : {
          "attivi" : "83",
          "esecuzione" : "44",
          "problemi" : "2",
          "warnings" : "11"
        }
      }, {
        "id_client" : "4",
        "nome_client" : "DESKTOP-3562",
        "tipo_client" : "Client",
        "category" : "Produzione",
        "MAC_address" : "00-41-5E-E4-2Z-C2",
        "codice_licenza" : "LE1P-42KI-PY9L-1FZP",
        "classe_licenza" : "2",
        "nome_tipologia_licenza" : "Pro",
        "sede" : "Milano",
        "servizi" : {
          "attivi" : "83",
          "esecuzione" : "44",
          "problemi" : "2",
          "warnings" : "11"
        }
      }, {
        "id_client" : "5",
        "nome_client" : "SERVER-8290",
        "tipo_client" : "Server",
        "category" : "Sviluppo",
        "MAC_address" : "00-41-5E-E4-2Z-C2",
        "codice_licenza" : "LE1P-42KI-PY9L-1FZP",
        "classe_licenza" : "2",
        "nome_tipologia_licenza" : "Pro",
        "sede" : "Torino",
        "servizi" : {
          "attivi" : "83",
          "esecuzione" : "44",
          "problemi" : "2",
          "warnings" : "11"
        }
      }
    ],
    "sedi" : [
      {"nome" : "Venezia",
       "n_client" : 2},
      {"nome" : "Milano",
      "n_client" : 2},
      {"nome" : "Torino",
       "n_client" : 1},
    ],
    "categories" : [
      {"nome" : "Sviluppo",
       "n_client" : 3},
      {"nome" : "Produzione",
      "n_client" : 2}
    ],
    "token" : "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
  };
  res.send(result);
});

app.post('/listasedi', function (req, res) {
  result = {
    "sedi" : [
      {"nome" : "Venezia",
       "n_client" : 2},
      {"nome" : "Milano",
      "n_client" : 2},
      {"nome" : "Torino",
       "n_client" : 1},
    ],
    "token" : ""
  };
  res.send(result);
});

app.post('/listagruppi', function (req, res) {
  result = {
    "categories" : [
      {"nome" : "Sviluppo",
       "n_client" : 3},
      {"nome" : "Produzione",
      "n_client" : 2}
    ],
    "token" : ""
  };
  res.send(result);
});

app.post('/listaservizi', function (req, res) {
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
