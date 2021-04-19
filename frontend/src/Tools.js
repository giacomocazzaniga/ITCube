export const Backend2FrontendDateConverter = (bcknd_date) => {
  let frtnd_date = bcknd_date.replace("T", " ");
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