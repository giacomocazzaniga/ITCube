import * as types from './ActionTypes';

export const login = (company_name) => ({
  type: types.LOGIN,
  company_id: company_name
})
