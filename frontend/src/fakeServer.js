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
    "company_id":"ITCube Consulting",
    "client_list" : [
      {
        "id" : "1",
        "name" : "Macchina di Massimiliano"
      }, {
        "id" : "2",
        "name" : "Macchina di Francesco"
      }, {
        "id" : "3",
        "name" : "Macchina di Matteo"
      }, {
        "id" : "4",
        "name" : "Macchina di Stefano"
      }
    ]
  };
  res.send(result);
});

// Starting our server.
app.listen(3001, () => {
 console.log('http://localhost:3001/');
});
