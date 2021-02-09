import storage from 'redux-persist/lib/storage';
import { persistReducer } from 'redux-persist';
import * as types from './ActionTypes';

const initialState = {
  company_id : null,//"ITCube Consulting",
  client_list : [
    {
      id : "1",
      name : "Macchina di Massimiliano"
    }, {
      id : "2",
      name : "Macchina di Francesco"
    }
  ]
};
export function rootReducer(state = initialState, action) {
  if (action.type === types.LOGIN) {
    return Object.assign({}, state, {
      company_id: action.company_id
    });
  }

  //returning the state
  return state;
}

export const persistConfig = {
  key: 'root',
  storage: storage,
  blacklist: ['company_id', 'client_list']
};

export default persistReducer(persistConfig, rootReducer);
