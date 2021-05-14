import { totalReset } from "./ActionCreator";
import { _LICENZE, _MSGCODE } from "./Constants";

export const Backend2FrontendDateConverter = (bcknd_date) => {
  let frtnd_date = bcknd_date;
  if(bcknd_date.indexOf(".") != -1){
    frtnd_date = bcknd_date.substring(0,bcknd_date.indexOf("."));
  }
  if(bcknd_date.indexOf("T") != -1){
    frtnd_date = frtnd_date.replace("T", " ")
  }
  return frtnd_date;
}

export const nomeToIdSede = (nome, listaNomi, listaId) => {
  for(let i=0; i<listaNomi.length; i++){
    if(listaNomi[i] == nome)
      return listaId[i];
  }
}

export const idToNomeSede = (id, listaNomi, listaId) => {
  for(let i=0; i<listaId.length; i++){
    if(listaId[i] == id)
      return listaNomi[i];
  }
}

export const idToNomeLicenza = (id) => {
  switch (String(id)) {
    case _LICENZE.SISTEMA_OPERATIVO.tipo:
      return _LICENZE.SISTEMA_OPERATIVO.label
    case _LICENZE.BACKUP.tipo:
      return _LICENZE.BACKUP.label
    case _LICENZE.RETE.tipo:
      return _LICENZE.RETE.label
    case _LICENZE.VULNERABILITA.tipo:
      return _LICENZE.VULNERABILITA.label
    case _LICENZE.ANTIVIRUS.tipo:
      return _LICENZE.ANTIVIRUS.label
  }
}

export const sortResults = (prop, asc, list) => {
  list.sort(function(a, b) {
      if (asc) {
          return (a[prop] > b[prop]) ? 1 : ((a[prop] < b[prop]) ? -1 : 0);
      } else {
          return (b[prop] > a[prop]) ? 1 : ((b[prop] < a[prop]) ? -1 : 0);
      }
  });
}

export const autenticazione_fallita = (msgCode) => {
  if((msgCode == _MSGCODE.AUTENTICAZIONE_FALLITA) || (String(msgCode) == String(_MSGCODE.AUTENTICAZIONE_FALLITA))) {
    // console.log(true)
    // console.log(msgCode)
    return true;
  } else {
    return false;
  }
}

export const renewToken = (oldToken, newToken) => {
  if(newToken != null && oldToken != newToken)
    return true;
  return false;
}

export const Backend2FrontendAlertConverter = (backend_alert) => {
    let frontend_alert;
    switch(backend_alert) {
      case "WINDOWS_SERVICES":
        frontend_alert = "Servizi di Windows"
        break;
      case "WINDOWS_EVENTS":
        frontend_alert = "Eventi di Windows"
        break;
      case "DRIVES":
        frontend_alert = "Drives"
        break;
      default:
        frontend_alert = backend_alert
    }
    return frontend_alert;
}