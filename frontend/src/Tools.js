import { _LICENZE } from "./Constants";

export const Backend2FrontendDateConverter = (bcknd_date) => {
  let frtnd_date = bcknd_date.replace("T", " ").substring(0,bcknd_date.indexOf("."));
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