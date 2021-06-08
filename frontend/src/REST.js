const port = "8080";
const ip = "127.0.0.1" //"217.160.240.146";
const buildurl = "http://"+ip+":"+port+"/be/main/";
export const url_login = buildurl+"login"; //V
export const url_signup = buildurl+"registrazione"; //V
export const url_lista_sedi = buildurl+"clientLicenseList";
export const url_lista_gruppi = buildurl+"getClientTypes";
export const url_lista_servizi = buildurl+"listaservizi";
export const url_lista_tipo_dispositivi = buildurl+"listatipodispositivi";
export const url_lista_licenze = buildurl+"listalicenze";
export const url_deep_client = buildurl+"deepClient";
export const url_shallow_clients = buildurl+"shallowClients";
export const url_edit_company_data = buildurl+"editCompanyData";
export const url_deep_licenze = buildurl+"getLicenzeDeep";
export const url_shallow_licenze = buildurl+"getLicenzeShallow";
export const url_get_servizi_monitorati = buildurl+"getServiziMonitorati";
export const url_get_servizi_all = buildurl+"getServiziAll";
export const url_modifica_monitoraggio_servizio = buildurl+"modificaMonitoraggioServizio";
export const url_get_servizi_overview = buildurl+"getServiziOverview";
export const url_get_eventi_overview = buildurl+"getEventiOverview";
export const url_get_eventi = buildurl+"getEventi";
export const url_modifica_sede = buildurl+"modificaSedeClient";
export const url_get_drives = buildurl+"getDrives";
export const url_get_n_sedi = buildurl+"getNSedi";
export const url_inserimento_sede = buildurl+"inserimentoSede";
export const url_cancellazione_sede = buildurl+"cancellazioneSede";
export const url_get_nomi_sedi = buildurl+"getNomiSedi";
export const url_get_latest_alert = buildurl+"getLatestAlerts";
export const url_compra_licenza = buildurl+"compraLicenza";
export const url_get_client_overview_services = buildurl+"getClientOverview";
export const url_get_company_overview = buildurl+"getCompanyOverview";
export const url_get_client_history = buildurl + "getClientHistory";
export const url_get_company_history = buildurl + "getCompanyHistory";
export const url_assign_license = buildurl + "assegnaLicenza";
export const url_modifica_monitoraggio_alert = buildurl + "modificaMonitoraggioAlert";
export const url_get_monitoraggio_alert = buildurl + "getMonitoraggioAlert";
export const url_update_monitoraggio_alert = buildurl + "updateMonitoraggioAlert";
export const url_get_last_date = buildurl + "getLastDate";
export const url_get_all_services_of_company = buildurl + "getAllServicesOfCompany";
export const url_get_all_nomi_alert_configurazione = buildurl + "getAllNomiAlertConfigurazione";
export const url_update_monitora_all_services = buildurl + "updateMonitoraAllServices";
export const url_update_monitora_all_alerts = buildurl + "updateMonitoraAllAlerts";
export const url_unsubscribe_alert = buildurl + "unsubscribeAlert";
export const url_change_mail_interval = buildurl + "changeMailInterval";
export const url_get_mail_interval = buildurl + "getMailInterval";
export const url_richiedi_password = buildurl + "richiediPassword";
export const url_cambio_password = buildurl + "changePassword";
export const url_is_token_valid = buildurl + "isTokenValid";
export const url_get_last_mail_date_and_time = buildurl + "getLastMailDate";
export const url_add_tier = buildurl + "addTier";
export const url_remove_tier = buildurl + "removeTier";
export const url_get_type_from_client = buildurl + "getTierFromClient";
export const url_update_tier = buildurl + "updateTier";



const portFake = "3001"; 
const ipFake = "127.0.0.1";//"217.160.240.146";
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
export const url_get_n_sediFake = buildurlFake+"getNSedi";