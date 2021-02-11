import * as types from './ActionTypes';

export const login = (company_name, client_list) => ({
  type: types.LOGIN,
  company_id: company_name,
  client_list: client_list
})
