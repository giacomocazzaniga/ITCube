import storage from 'redux-persist/lib/storage';
import { persistReducer } from 'redux-persist';
import * as types from './ActionTypes';

const initialState = {
  company_id : null,
  client_list : [],
  logged: false
};
export function rootReducer(state = initialState, action) {
  if (action.type === types.LOGIN) {
    return Object.assign({}, state, {
      company_id: action.company_id,
      client_list: action.client_list,
      logged: true
    });
  }

  //returning the state
  return state;
}

export const persistConfig = {
  key: 'root',
  storage: storage,
  blacklist: ['company_id', 'client_list', 'logged']
};

export default persistReducer(persistConfig, rootReducer);
