import { ActionType } from 'react-hot-toast';
import * as types from './ActionTypes';

export const login = (nome_company, id_company, email, emailNotify, client_list, token, lista_sedi, chiave_di_registrazione, listaNomiSedi, listaIdSedi) => ({
  type: types.LOGIN,
  id_company: id_company,
  nome_company: nome_company,
  email: email,
  emailNotify: emailNotify,
  client_list: client_list,
  token: token, 
  lista_sedi: lista_sedi,
  chiave_di_registrazione: chiave_di_registrazione,
  listaNomiSedi: listaNomiSedi,
  listaIdSedi: listaIdSedi
})
export const updateCompanyData = (nome_company, email, emailNotify, token) => ({
  type: types.UPDATECOMPANYDATA,
  nome_company: nome_company,
  email: email,
  emailNotify: emailNotify,
  token: token
})
export const signUp = (message, messageCode) => ({
  type: types.SIGNUP,
  message: message,
  messageCode: messageCode
})
export const addLicense = (license) => ({
  type: types.ADDLICENSE,
  license: license
})
export const removeLicense = (license) => ({
  type: types.REMOVELICENSE,
  license: license
})
export const searchClient = (client_name) => ({
  type: types.SEARCHCLIENT,
  client_name: client_name
})
export const placesList = (places_list) => ({
  type: types.PLACESLIST,
  places_list: places_list
})
export const categoriesList = (categories_list) => ({
  type: types.CATEGORIESLIST,
  categories_list: categories_list
})
export const servicesList = (servicesList) => ({
  type: types.SERVICESLIST,
  servicesList: servicesList
})
export const eventsList = (eventsList) => ({
  type: types.EVENTSLIST,
  eventsList: eventsList
})
export const serviziOverview = (n_totali, n_running, n_stop, n_monitorati) => ({
  type: types.SERVIZIOVERVIEW,
  n_totali: n_totali,
  n_running: n_running,
  n_stop: n_stop,
  n_monitorati: n_monitorati
})

export const listaNomiSedi = (listaNomi, token, listaId) => ({
  type: types.LISTANOMISEDI,
  listaNomi: listaNomi,
  token: token,
  listaId: listaId
  //tmp_list: tmp_list
})

export const updateSidebar = (elencoClients,sedi, token, listaNomi, listaSedi) => ({
  type: types.UPDATESIDEBAR,
  clientList: elencoClients,
  token: token,
  sedi: sedi,
  listaNomi: listaNomi,
  listaSedi: listaSedi
})

export const updateCTcategoryVsPlace = (toggle) => ({
  type: types.CATEGORYVSPLACE,
  category_vs_place: toggle
})

export const updateCTWindowsEvents = (n_problemi, n_warnings) => ({
  type: types.CLIENTTEMPLATEWINDOWSEVENTS,
  windows_events: {
    n_problemi: n_problemi,
    n_warnings: n_warnings
  }
})

export const updateCTWindowsServices = ( n_totali, n_running, n_stop, n_monitorati) => ({
  type: types.CLIENTTEMPLATEWINDOWSSERVICES,
  windows_services: {
    n_monitorati: n_monitorati,
    n_esecuzione: n_running,
    n_stop: n_stop,
    n_totali: n_totali
  }
  
})

export const updateCTInfo = (info) => ({
  type: types.CLIENTTEMPLATEINFO,
  info: info
  
})

export const updateCTAlert = (alert) => ({
  type: types.CLIENTTEMPLATEALERT,
  alert: alert
})

export const resetClientTemplate = () => ({
  type: types.CLIENTTEMPLATERESET
})

export const updateCompanyTemplateLicenze = (lista_licenze) => ({
  type: types.COMPANYTEMPLATELICENZE,
  lista_licenze: lista_licenze
})

export const updateClientOverview = (n_errori,n_warnings,n_ok) => ({
  type: types.UPDATECLIENTOVERVIEW,
    overview: {
      problemi: n_errori,
      warnings: n_warnings,
      ok: n_ok
  }
})

export const updateCompanyOverview = (n_errori, n_warning, n_ok) => ({
  type: types.UPDATECOMPANYOVERVIEW,
    overview: {
      n_errori: n_errori,
      n_running: n_ok,
      n_warnings: n_warning
  }
})

export const fixSedi = (lista_client) => ({
  type: types.FIXSEDI,
  lista_client: lista_client
})

export const updateClientHistory = (history_data) => ({
  type: types.UPDATECLIENTHISTORY,
  history_data: history_data
})

export const updateCompanyHistory = (history_data) => ({
  type: types.UPDATECOMPANYHISTORY,
  history_data: history_data
})

export const updateClientLicenses = (license, id_client) => ({
  type: types.UPDATECLIENTLICENSES,
  license: license,
  id_client: id_client
})

export const totalReset = () => ({
  type: types.TOTALRESET,
})

export const updateToken = (token) => ({
  type: types.UPDATETOKEN,
  token: token
})

export const getConfigurazioneAlert = (list) => ({
  type: types.GETCONFIGURAZIONEALERT,
  operazioni: list
})

export const getLastDate = (date) => ({
  type: types.GETLASTDATE,
  date: date
})

export const getLastMailDate = (last_mail_date) => ({
  type: types.GETLASTMAILDATE,
  last_mail_date: last_mail_date
})