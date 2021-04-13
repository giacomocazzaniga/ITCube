const { url_login, url_lista_sediFake, url_lista_gruppiFake, url_signup, url_deep_clientFake, url_loginFake, url_edit_company_dataFake, url_edit_company_data, url_shallow_licenze, url_get_servizi_monitoratiFake, url_get_servizi_allFake, url_get_servizi_overviewFake, url_get_eventi_overviewFake, url_get_eventiFake, url_get_servizi_overview, url_shallow_licenzeFake, url_get_servizi_allFake2, url_get_servizi_monitoratiFake2, url_get_servizi_all, url_get_servizi_monitorati, url_modifica_monitoraggio_servizio, url_get_eventi, url_get_eventi_overview, url_lista_sedi, url_lista_gruppi, url_deep_client, url_shallow_clients, url_modifica_sede, url_get_drives, url_get_n_sediFake, url_get_n_sedi } = require('./REST');
const axios = require('axios');
var md5 = require('md5');

export const defaultUpperBound = 20;
export const defaultSlot = 1;

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
  console.log(user)
  return axios.post(url_lista_sedi, {
    token: token,
    id_company: user
  })
}

export const _getClientTypes = (user, token) => {
  console.log(user)
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
    id_company: id_company,
    token: token
  })
}

export const _getShallowClients = (id_company, token) => {
  return axios.post(url_shallow_clients, {
    id_company: id_company,
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

export const _modificaSedeClient = (token, id_client, id_company, nuovaSede, vecchiaSede) => {
  return axios.post(url_modifica_sede, {
    id_client: String(id_client), 
    token: token,
    id_company: String(id_company), 
    nuova_sede: nuovaSede,
    vecchia_sede: vecchiaSede
  })
}

export const _getDrives = (token, id_client) => {
  return axios.post(url_get_drives, {
    id_client: String(id_client), 
    token: token
  })
}