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
export const categoryVsPlace = (toggle) => ({
  type: types.CATEGORYVSPLACE,
  category_vs_place: toggle
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
})

export const updateSidebar = (elencoClients,sedi, token, listaNomi, listaSedi) => ({
  type: types.UPDATESIDEBAR,
  clientList: elencoClients,
  token: token,
  sedi: sedi,
  listaNomi: listaNomi,
  listaSedi: listaSedi
})

export const updateCTWindowsEvents = (n_problemi, n_warnings) => ({
  type: types.CLIENTTEMPLATEWINDOWSEVENTS,
  windows_events: {
    n_problemi: n_problemi,
    n_warnings: n_warnings
  }
})

export const updateCTWindowsServices = (n_monitorati, n_esecuzione, n_stop, n_totali) => ({
  type: types.CLIENTTEMPLATEWINDOWSSERVICES,
  windows_services: {
    n_monitorati: n_monitorati,
    n_esecuzione: n_esecuzione,
    n_stop: n_stop,
    n_totali: n_totali
  }
  
})

export const resetClientTemplate = () => ({
  type: types.CLIENTTEMPLATERESET
})
