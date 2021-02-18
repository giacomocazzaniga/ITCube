import * as types from './ActionTypes';

export const login = (nome_company, email, emailNotify, client_list, token) => ({
  type: types.LOGIN,
  nome_company: nome_company,
  email: email,
  emailNotify: emailNotify,
  client_list: client_list,
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