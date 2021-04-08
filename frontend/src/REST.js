const port = "8080";
const ip = "127.0.0.1";
const buildurl = "http://"+ip+":"+port+"/be/main/";
export const url_login = buildurl+"login"; //V
export const url_signup = buildurl+"registrazione"; //V
export const url_lista_sedi = buildurl+"listasedi";
export const url_lista_gruppi = buildurl+"listagruppi";
export const url_lista_servizi = buildurl+"listaservizi";
export const url_lista_tipo_dispositivi = buildurl+"listatipodispositivi";
export const url_lista_licenze = buildurl+"listalicenze";
export const url_deep_client = buildurl+"deepClient";
export const url_edit_company_data = buildurl+"editCompanyData";
export const url_deep_licenze = buildurl+"getLicenzeDeep";
export const url_shallow_licenze = buildurl+"getLicenzeShallow";
export const url_get_servizi_monitorati = buildurl+"getServiziMonitorati";
export const url_get_servizi_all = buildurl+"getServiziAll";
export const url_modifica_monitoraggio_servizio = buildurl+"modificaMonitoraggioServizio";
export const url_get_servizi_overview = buildurl+"getServiziOverview";
export const url_get_eventi_overview = buildurl+"getEventiOverview";
export const url_get_eventi = buildurl+"getEventi";


const portFake = "3001"; 
const ipFake = "127.0.0.1";
const buildurlFake = "http://"+ipFake+":"+portFake+"/";
export const url_loginFake = buildurlFake+"login";
export const url_signupFake = buildurlFake+"signup";
export const url_lista_sediFake = buildurlFake+"listasedi";
export const url_lista_gruppiFake = buildurlFake+"listagruppi";
export const url_lista_serviziFake = buildurlFake+"listaservizi";
export const url_lista_tipo_dispositiviFake = buildurlFake+"listatipodispositivi";
export const url_lista_licenzeFake = buildurlFake+"listalicenze";
export const url_shallow_licenzeFake = buildurlFake+"getLicenzeShallow";
export const url_deep_clientFake = buildurlFake+"deepClient";
export const url_edit_company_dataFake = buildurlFake+"editCompanyData";
export const url_get_servizi_monitoratiFake = buildurlFake+"getServiziMonitorati";
export const url_get_servizi_monitoratiFake2 = buildurlFake+"getServiziMonitorati2";
export const url_get_servizi_allFake = buildurlFake+"getServiziAll";
export const url_get_servizi_allFake2 = buildurlFake+"getServiziAll2";
export const url_get_servizi_overviewFake = buildurlFake+"getServiziOverview";
export const url_get_eventi_overviewFake = buildurlFake+"getEventiOverview";
export const url_get_eventiFake = buildurlFake+"getEventi";