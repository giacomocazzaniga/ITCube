import storage from 'redux-persist/lib/storage';
import { persistReducer } from 'redux-persist';
import * as types from './ActionTypes';

const initialState = {
  message : "",
  messageCode : 0,
  nome_company : null, //ragione sociale
  email : null,
  emailNotify : null,
  client_list : [],
  logged: false,
  token : null,
  licensesList : [{name: 'Free', id: 0},{name: 'Premium', id: 1},{name: 'Pro', id: 2}],
  searched_client: ""
};
export function rootReducer(state = initialState, action) {
  if (action.type === types.LOGIN) {
    return Object.assign({}, state, {
      nome_company: action.nome_company,
      email: action.email,
      emailNotify: action.emailNotify,
      client_list: action.client_list,
      logged: true,
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

  //returning the state
  return state;
}

export const persistConfig = {
  key: 'root',
  storage: storage,
  blacklist: ['nome_company', 'client_list', 'logged', 'token', 'licensesList', 'searched_client']
};

export default persistReducer(persistConfig, rootReducer);
