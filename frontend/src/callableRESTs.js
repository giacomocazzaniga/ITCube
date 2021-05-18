import { defaultDaysAlert, defaultSettimaneAlert, defaultSlot, defaultUpperBound } from "./Constants.js";
const { url_login, url_get_nomi_sedi, url_lista_sediFake, url_lista_gruppiFake, url_signup, url_deep_clientFake, url_loginFake, url_edit_company_dataFake, url_edit_company_data, url_shallow_licenze, url_get_servizi_monitoratiFake, url_get_servizi_allFake, url_get_servizi_overviewFake, url_get_eventi_overviewFake, url_get_eventiFake, url_get_servizi_overview, url_shallow_licenzeFake, url_get_servizi_allFake2, url_get_servizi_monitoratiFake2, url_get_servizi_all, url_get_servizi_monitorati, url_modifica_monitoraggio_servizio, url_get_eventi, url_get_eventi_overview, url_lista_sedi, url_lista_gruppi, url_deep_client, url_shallow_clients, url_modifica_sede, url_get_drives, url_get_n_sediFake, url_get_n_sedi, url_inserimento_sede, url_cancellazione_sede, url_get_latest_alert, url_compra_licenza, url_get_client_overview, url_get_client_overview_drives, url_get_client_overview_services, url_get_client_overview_events, url_get_company_overview, url_get_client_history, url_get_company_history, url_assign_license, url_modifica_monitoraggio_alert, url_get_monitoraggio_alert, url_update_monitoraggio_alert, url_get_last_date, url_get_all_services_of_company, url_get_all_nomi_alert_configurazione, url_update_monitora_all_services, url_update_monitora_all_alerts, url_unsubscribe_alert } = require('./REST');
const axios = require('axios');
var md5 = require('md5');

export const _performSignUp = (email, password, email_alert, ragione_sociale) => {
  let encryptedPsw = md5(password);
  return axios.post(url_signup, {
    email: email,
    password: encryptedPsw,
    email_alert: email_alert,
    ragione_sociale: ragione_sociale
  })
}

export const _performLogin = (email, password) => {
    let encryptedPsw = md5(password);
    return axios.post(url_login, {
      email: email,
      password: encryptedPsw
    })
}

export const _getPlaces = (user, token) => {
  return axios.post(url_lista_sedi, {
    token: token,
    id_company: user
  })
}

export const _getClientTypes = (user, token) => {
  return axios.post(url_lista_gruppi, {
    token: token,
    id_company: user
  })
}

export const _getLicenzeShallow = (id, token) => {
  return axios.post(url_shallow_licenze, {
    token: token,
    id_company: id
  })
}

export const _getDeepClient = (id_client, id_company, token) => {
  return axios.post(url_deep_client, {
    id_client: String(id_client),
    id_company: String(id_company),
    token: token
  })
}

export const _getShallowClients = (id_company, token) => {
  return axios.post(url_shallow_clients, {
    id_company: String(id_company),
    token: token
  })
}

export const _editCompanyData = (id_company, token, email, emailAlert, ragioneSociale) => {
  return axios.post(url_edit_company_data, {
    id_company: id_company, 
    token: token, 
    email: email, 
    emailAlert: emailAlert, 
    ragioneSociale: ragioneSociale
  })
}

export const _getServiziMonitorati = (token, id_client) => {
  return axios.post(url_get_servizi_monitorati, {
    id_client: String(id_client), 
    token: token
  })
}

export const _getServiziAll = (token, id_client) => {
  return axios.post(url_get_servizi_all, {
    id_client: String(id_client), 
    token: token
  })
}

export const _getEventi = (token, id_client, sottocategoria="*", slot=defaultSlot, n=defaultUpperBound) => {
  return axios.post(url_get_eventi, {
    id_client: String(id_client), 
    token: token,
    sottocategoria: sottocategoria,
    slot: String(slot),
    n: String(n)
  })
}

export const _getServiziOverview = (token, id_client) => {
  return axios.post(url_get_servizi_overview, {
    id_client: String(id_client), 
    token: token
  })
}

export const _getNSedi = (token, id_company) => {
  return axios.post(url_get_n_sedi, {
    id_company: String(id_company), 
    token: token
  })
}


export const _getEventiOverview = (token, id_client) => {
  return axios.post(url_get_eventi_overview, {
    id_client: String(id_client), 
    token: token
  })
}

export const _modificaMonitoraggioServizio = (token, nome_servizio, id_client, monitora) => {
  return axios.post(url_modifica_monitoraggio_servizio, {
    id_client: String(id_client), 
    token: token,
    nome_servizio: nome_servizio,
    monitora: monitora
  })
}

export const _modificaMonitoraggioAlert = (token, nome_operazione, id_client, monitora) => {
  return axios.post(url_modifica_monitoraggio_alert, {
    id_client: String(id_client), 
    token: token,
    nome_operazione: nome_operazione,
    monitora: monitora
  })
}

export const _getMonitoraggioAlert = (token, id_client) => {
  return axios.post(url_get_monitoraggio_alert, {
    id_client: String(id_client), 
    token: token
  })
}

export const _updateMonitoraggioAlert = (token, id_client, monitora, operazione) => {
  return axios.post(url_update_monitoraggio_alert, {
    id_client: String(id_client), 
    token: token,
    monitora: monitora,
    operazione: operazione
  })
}

export const _modificaSedeClient = (token, id_client, id_company, nuovaSede, vecchiaSede) => {
  return axios.post(url_modifica_sede, {
    id_client: String(id_client), 
    token: token,
    id_company: String(id_company), 
    nuovaSede: nuovaSede,
    vecchiaSede: vecchiaSede
  })
}

export const _getDrives = (token, id_client) => {
  return axios.post(url_get_drives, {
    id_client: String(id_client), 
    token: token
  })
}

export const _inserimentoSede = (token, id_company, nome) => {
  return axios.post(url_inserimento_sede, {
    id_company: String(id_company), 
    nome: nome,
    token: token
  })
}

export const _getNomiSedi = (token, id_company) => {
  return axios.post(url_get_nomi_sedi, {
    id_company: String(id_company),
    token: token
  })
}

export const _cancellazioneSede = (token, id_company, nome) => {
  return axios.post(url_cancellazione_sede, {
    id_company: String(id_company), 
    nome: nome,
    token: token
  })
}

export const _getLatestAlerts = (token, id_client, n_settimane = defaultSettimaneAlert) => {
  return axios.post(url_get_latest_alert, {
    id_client: String(id_client), 
    n_settimane: String(n_settimane),
    token: token,
  })
}

export const _compraLicenza = (token, id_company, classe_licenza) => {
  return axios.post(url_compra_licenza, {
    id_company: String(id_company), 
    classe_licenza: String(classe_licenza),
    token: token
  })
}

export const _getClientOverview = async (token, id_client) => {
  let response;

  const res_services = await axios.post(url_get_client_overview_services, {
    id_client: String(id_client), 
    token: token
  })

  return response = {
    errori: [res_services.data.servicesResponse.errori,  res_services.data.eventiResponse.errori,  res_services.data.drivesResponse.errori],
    warnings:  [res_services.data.servicesResponse.warning,  res_services.data.eventiResponse.warning,  res_services.data.drivesResponse.warning],
    ok:  [res_services.data.servicesResponse.ok, 0, res_services.data.drivesResponse.ok]
  } 
}

export const _getCompanyOverview = (token, id_company) => {
  return axios.post(url_get_company_overview, {
    id_company: String(id_company), 
    token: token
  })
}

export const _getClientHistory = async(token, id_client, slot=defaultDaysAlert) => {
  const response = await axios.post(url_get_client_history, {
    id_client: String(id_client), 
    token: token,
    slot: String(slot)
  })

  let res = {
    xaxis: [],
    problems: [],
    warnings: []
  }

  response.data.history.forEach(el => {
    res.xaxis.push(el.date);
    res.problems.push(el.errors);
    res.warnings.push(el.warnings);
  });
  
  res.last_update = response.data.last_update;

  return res;
}

export const _getCompanyHistory = async(token, id_company, slot=defaultDaysAlert) => {
  const response = await axios.post(url_get_company_history, {
    id_company: String(id_company), 
    token: token,
    slot: String(slot)
  })

  let res = {
    xaxis: [],
    problems: [],
    warnings: [],
    last_update: ""
  }

  response.data.history.forEach(el => {
    res.xaxis.push(el.date);
    res.problems.push(el.errors);
    res.warnings.push(el.warnings);
  });

  res.last_update = response.data.last_update;

  return res;
}

export const _assignLicenze = (id_client,token,codice,id_company) => {
  return axios.post(url_assign_license, {
    id_client: String(id_client),
    token: token,
    codice: codice,
    id_company: String(id_company),
  })
}

export const _getLastDate = (id_client,token) => {
  return axios.post(url_get_last_date, {
    id_client: String(id_client),
    token: token,
  })
}

export const _getAllServicesOfCompany = (id_company,token) => {
  return axios.post(url_get_all_services_of_company, {
    id_company: String(id_company),
    token: token,
  })
}

export const _getAllNomiAlertConfigurazione = (id_company,token) => {
  return axios.post(url_get_all_nomi_alert_configurazione, {
    id_company: String(id_company),
    token: token,
  })
}

export const _updateMonitoraAllServices = (id_company,token,tipologia,licenza,sede,nome_servizio,monitora) => {
  return axios.post(url_update_monitora_all_services, {
    id_company: String(id_company),
    token: token,
    tipologia: tipologia,
    licenza: licenza,
    sede: sede,
    nome_servizio: nome_servizio,
    monitora: monitora
  })
}

export const _updateMonitoraAllAlerts = (id_company,token,tipologia,licenza,sede,nome_operazione,monitora) => {
  return axios.post(url_update_monitora_all_alerts, {
    id_company: String(id_company),
    token: token,
    tipologia: tipologia,
    licenza: licenza,
    sede: sede,
    nome_operazione: nome_operazione,
    monitora: monitora
  })
}

export const _unsubscribeAlert = (token,tipologia_alert,id_client) => {
  return axios.get(url_unsubscribe_alert + "?token=" + token + "&tipologia_alert=" + tipologia_alert + "&id_client=" + id_client)
}