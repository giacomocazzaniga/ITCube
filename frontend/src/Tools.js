export const Backend2FrontendDateConverter = (bcknd_date) => {
  let frtnd_date = bcknd_date.replace("T", " ");
  return frtnd_date;
}