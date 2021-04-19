import storage from 'redux-persist/lib/storage';
import { persistReducer } from 'redux-persist';
import * as types from './ActionTypes';
import { _LICENZE } from './Constants';

const initialState = {
  message : "",
  messageCode : 0,
  nome_company : null, //ragione sociale
  id_company : null,
  email : null,
  emailNotify : null,
  chiave_di_registrazione : null,
  client_list : [],
  logged: false,
  token : null,
  licensesList : [{name: _LICENZE.SISTEMA_OPERATIVO.label, id: _LICENZE.SISTEMA_OPERATIVO.tipo},
    {name: _LICENZE.BACKUP.label, id: _LICENZE.BACKUP.tipo},
    {name: _LICENZE.RETE.label, id: _LICENZE.RETE.tipo},
    {name: _LICENZE.VULNERABILITA.label, id: _LICENZE.VULNERABILITA.tipo},
    {name: _LICENZE.ANTIVIRUS.label, id: _LICENZE.ANTIVIRUS.tipo}
  ],
  searched_client: "",
  places_list : [],
  categories_list : [],
  category_vs_place : true, //true==category, false==place
  services_list : [],
  events_list : [],
  n_totali: 0,
  n_running: 0,
  n_stop: 0,
  n_monitorati: 0,
  lista_sedi: 0,
  lista_nomi_sedi: ["Senza sede"],
  lista_id_sedi: [null]
};
export function rootReducer(state = initialState, action) {
  if (action.type === types.LOGIN) {
    return Object.assign({}, state, {
      nome_company: action.nome_company,
      id_company: action.id_company,
      email: action.email,
      emailNotify: action.emailNotify,
      client_list: action.client_list,
      logged: true,
      token: action.token,
      lista_sedi: action.lista_sedi,
      chiave_di_registrazione: action.chiave_di_registrazione,
      lista_nomi_sedi: [...action.listaNomiSedi, "Senza sede"],
      lista_id_sedi: [...action.listaIdSedi, null]
    });
  }
  if (action.type === types.UPDATECOMPANYDATA) {
    return Object.assign({}, state, {
      nome_company: action.nome_company,
      email: action.email,
      emailNotify: action.emailNotify,
      token: action.token
    });
  }
  if (action.type === types.SIGNUP) {
    return Object.assign({}, state, {
      message: action.message,
      messageCode: action.messageCode
    });
  }
  if (action.type === types.ADDLICENSE) {
    return Object.assign({}, state, {
      licensesList: action.license
    });
  }
  if (action.type === types.REMOVELICENSE) {
    return Object.assign({}, state, {
      licensesList: action.license
    });
  }
  if (action.type === types.SEARCHCLIENT) {
    return Object.assign({}, state, {
      searched_client: action.client_name
    });
  }
  if (action.type === types.PLACESLIST) {
    return Object.assign({}, state, {
      places_list: action.places_list
    });
  }
  if (action.type === types.CATEGORIESLIST) {
    return Object.assign({}, state, {
      categories_list: action.categories_list
    });
  }
  if (action.type === types.CATEGORYVSPLACE) {
    return Object.assign({}, state, {
      category_vs_place: action.category_vs_place
    });
  }
  if (action.type === types.SERVICESLIST) {
    return Object.assign({}, state, {
      services_list: action.servicesList
    });
  }
  if (action.type === types.EVENTSLIST) {
    return Object.assign({}, state, {
      events_list: action.eventsList
    });
  }
  if (action.type === types.SERVIZIOVERVIEW) {
    return Object.assign({}, state, {
      n_totali: action.n_totali,
      n_running: action.n_running,
      n_stop: action.n_stop,
      n_monitorati: action.n_monitorati
    });
  }
  if (action.type === types.LISTANOMISEDI) {
    return Object.assign({}, state, {
      lista_nomi_sedi: [...action.listaNomi, "Senza sede"],
      token: action.token,
      lista_id_sedi: action.listaId
    });
  }
  if(action.type === types.UPDATESIDEBAR) {
    return Object.assign({}, state, {
      client_list: action.clientList,
      token: action.token,
      lista_sedi: action.lista_sedi,
      lista_nomi_sedi: [...action.listaNomi, "Senza sede"],
      lista_id_sedi: [...action.listaSedi, null]
    });
  }
  //returning the state
  return state;
}

export const persistConfig = {
  key: 'root',
  storage: storage,
  blacklist: ['nome_company', 'lista_id_sedi', 'lista_nomi_sedi', 'chiave_di_registrazione', 'id_company', 'client_list', 'logged', 'token', 'licensesList', 'searched_client', 'places_list', 'categories_list', 'services_list', 'events_list']
};

export default persistReducer(persistConfig, rootReducer);
