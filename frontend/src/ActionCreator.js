import * as types from './ActionTypes';

export const login = (nome_company, client_list, token) => ({
  type: types.LOGIN,
  nome_company: nome_company,
  client_list: client_list,
  token: token
})
export const signUp = (message, messageCode) => ({
  type: types.SIGNUP,
  message: message,
  messageCode: messageCode
})