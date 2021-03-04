import { _FILTERS } from "./Constants";

let inputList = [
  {
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
  }, {
    "id_client" : "2",
    "nome_client" : "DESKTOP-2230",
    "tipo_client" : "Client",
    "category" : "Sviluppo",
    "MAC_address" : "00-56-SC-E4-C2-WC",
    "codice_licenza" : "ZQCQ-B0EC-TW8N-YZFT",
    "classe_licenza" : "2",
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
    "classe_licenza" : "3",
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
    "classe_licenza" : "4",
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
    "classe_licenza" : "5",
    "nome_tipologia_licenza" : "Pro",
    "sede" : "Torino",
    "servizi" : {
      "attivi" : "83",
      "esecuzione" : "44",
      "problemi" : "2",
      "warnings" : "11"
    }
  }
]


/**
 * 
 * @param {*} clientList 
 * @param {*} filterType 
 * 
      input = 
      [
        {id_client, nome_client, tipo_client, category, MAC_address, codice_licenza, classe_licenza, nome_tipologia_licenza, sede, servizi {attivi, esecuzione, problemi, warnings}},
        {id_client, nome_client, tipo_client, category, MAC_address, codice_licenza, classe_licenza, nome_tipologia_licenza, sede, servizi {attivi, esecuzione, problemi, warnings}},
        ...
        {id_client, nome_client, tipo_client, category, MAC_address, codice_licenza, classe_licenza, nome_tipologia_licenza, sede, servizi {attivi, esecuzione, problemi, warnings}}
      ]

      output by category = 
      [
        "place1" : 
        [
          "category1" : 
          [
            {id_client, nome_client, tipo_client, category, MAC_address, codice_licenza, classe_licenza, nome_tipologia_licenza, sede, servizi {attivi, esecuzione, problemi, warnings}},
            {id_client, nome_client, tipo_client, category, MAC_address, codice_licenza, classe_licenza, nome_tipologia_licenza, sede, servizi {attivi, esecuzione, problemi, warnings}}
            ...
          ],
          "category2" : 
          [
            {id_client, nome_client, tipo_client, category, MAC_address, codice_licenza, classe_licenza, nome_tipologia_licenza, sede, servizi {attivi, esecuzione, problemi, warnings}},
            {id_client, nome_client, tipo_client, category, MAC_address, codice_licenza, classe_licenza, nome_tipologia_licenza, sede, servizi {attivi, esecuzione, problemi, warnings}}
            ...
          ], 
          ...
        ],
        "place2" : ...
      ]

      output by client type = 
      [
        "place1" : 
        [
          "clients" : 
          [
            {id_client, nome_client, tipo_client, category, MAC_address, codice_licenza, classe_licenza, nome_tipologia_licenza, sede, servizi {attivi, esecuzione, problemi, warnings}},
            {id_client, nome_client, tipo_client, category, MAC_address, codice_licenza, classe_licenza, nome_tipologia_licenza, sede, servizi {attivi, esecuzione, problemi, warnings}}
            ...
          ],
          "servers" : 
          [
            {id_client, nome_client, tipo_client, category, MAC_address, codice_licenza, classe_licenza, nome_tipologia_licenza, sede, servizi {attivi, esecuzione, problemi, warnings}},
            {id_client, nome_client, tipo_client, category, MAC_address, codice_licenza, classe_licenza, nome_tipologia_licenza, sede, servizi {attivi, esecuzione, problemi, warnings}}
            ...
          ]
        ],
        "place2" : ...
      ]
 */

export const ClientFilter = (clientList, filterType) => {
  //console.log(clientList);
  let filteredList = FilterByPlace(clientList);
  switch(filterType){
    case _FILTERS.CATEGORY:
      filteredList = FilterByCategory(filteredList);
    break;
    case _FILTERS.CLIENT_TYPE:
      filteredList = FilterByClientType(filteredList);
    break;
  }
  return filteredList;
}

const FilterByPlace = (clientList) => {
  //clientList = inputList; //to be removed
  let filteredList = [];
  let places = [];
  //get the list of places
  clientList.filter(function(client){
    if(places.includes(client.sede)==false) places = [...places, client.sede];         
  });
  //filter by places
  (places.map((place) => {
    filteredList = [...filteredList, GetClientsByPlace(clientList, place)];
  }))
  return filteredList;
}

const FilterByCategory = (clientList) => {
  let filteredList = clientList;
  return filteredList;
}

const FilterByClientType = (clientList) => {
  let filteredList = clientList;
  return filteredList;
}

const GetClientsByPlace = (clientList, place) => {
  let filteredList = (clientList.filter(function(client){
    return client.sede == place;
  }));
  return {place: place, filteredList: filteredList};
}