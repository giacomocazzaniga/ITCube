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
export const url_deep_clientFake = buildurlFake+"deepClient";