import storage from 'redux-persist/lib/storage';
import { persistReducer } from 'redux-persist';
import * as types from './ActionTypes';

const initialState = {
  message : "",
  messageCode : 0,
  nome_company : null,
  client_list : [],
  logged: false,
  token : null
};
export function rootReducer(state = initialState, action) {
  if (action.type === types.LOGIN) {
    return Object.assign({}, state, {
      nome_company: action.nome_company,
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

  //returning the state
  return state;
}

export const persistConfig = {
  key: 'root',
  storage: storage,
  blacklist: ['nome_company', 'client_list', 'logged', 'token']
};

export default persistReducer(persistConfig, rootReducer);
