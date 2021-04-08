const { url_login, url_lista_sediFake, url_lista_gruppiFake, url_signup, url_deep_clientFake, url_loginFake, url_edit_company_dataFake, url_edit_company_data, url_shallow_licenze, url_get_servizi_monitoratiFake, url_get_servizi_allFake, url_get_servizi_overviewFake, url_get_eventi_overviewFake, url_get_eventiFake, url_get_servizi_overview, url_shallow_licenzeFake, url_get_servizi_allFake2, url_get_servizi_monitoratiFake2, url_get_servizi_all, url_get_servizi_monitorati, url_modifica_monitoraggio_servizio } = require('./REST');
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
  return axios.post(url_lista_sediFake, {
    token: token,
    ragione_sociale: user
  })
}

export const _getClientTypes = (user, token) => {
  return axios.post(url_lista_gruppiFake, {
    token: token,
    ragione_sociale: user
  })
}

export const _getLicenzeShallow = (id, token) => {
  return axios.post(url_shallow_licenze, {
    token: token,
    id_company: id
  })
}

export const _getDeepClient = (id_client, id_company, token) => {
  return axios.post(url_deep_clientFake, {
    id_client: id_client,
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
    id_client: id_client, 
    token: token
  })
}

export const _getServiziAll = (token, id_client) => {
  return axios.post(url_get_servizi_all, {
    id_client: id_client, 
    token: token
  })
}

export const _getEventi = (token, id_client, sottocategoria="*", slot=defaultSlot, n=defaultUpperBound) => {
  return axios.post(url_get_eventiFake, {
    id_client: id_client, 
    token: token,
    sottocategoria: sottocategoria,
    slot: slot,
    n: n
  })
}

export const _getServiziOverview = (token, id_client) => {
  return axios.post(url_get_servizi_overview, {
    id_client: id_client, 
    token: token
  })
}

export const _getEventiOverview = (token, id_client) => {
  return axios.post(url_get_eventi_overviewFake, {
    id_client: id_client, 
    token: token
  })
}

export const _modificaMonitoraggioServizio = (token, nome_servizio, id_client, monitora) => {
  return axios.post(url_modifica_monitoraggio_servizio, {
    id_client: id_client, 
    token: token,
    nome_servizio: nome_servizio,
    monitora: monitora
  })
}