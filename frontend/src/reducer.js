import storage from 'redux-persist/lib/storage';
import { persistReducer } from 'redux-persist';
//import * as types from './ActionTypes';

const initialState = {
  company_id : "ITCube Consulting",
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
  /*if (action.type === types.DISPLAY_USERS) {
    return Object.assign({}, state, {
      client: action.client
    });
  }*/

  //returning the state
  return state;
}

export const persistConfig = {
  key: 'root',
  storage: storage,
};

export default persistReducer(persistConfig, rootReducer);
