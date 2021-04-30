import storage from 'redux-persist/lib/storage';
import { persistReducer } from 'redux-persist';
import * as types from './ActionTypes';
import { _LICENZE } from './Constants';

const initialState = {
  message : "",
  messageCode : 0,
  // nome_company : null, //ragione sociale
  id_company : null,
  // email : null,
  // emailNotify : null,
  // chiave_di_registrazione : null,
  client_list : [],
  logged: false,
  token : null,
  licensesList : [{name: _LICENZE.SISTEMA_OPERATIVO.label, id: _LICENZE.SISTEMA_OPERATIVO.tipo},
    {name: _LICENZE.BACKUP.label, id: _LICENZE.BACKUP.tipo},
    {name: _LICENZE.RETE.label, id: _LICENZE.RETE.tipo},
    {name: _LICENZE.VULNERABILITA.label, id: _LICENZE.VULNERABILITA.tipo},
    {name: _LICENZE.ANTIVIRUS.label, id: _LICENZE.ANTIVIRUS.tipo}
  ],
  searched_client: "",
  places_list : [],
  categories_list : [],
  // category_vs_place : true, //true==category, false==place
  services_list : [],
  events_list : [],
  n_totali: 0,
  n_running: 0,
  n_stop: 0,
  n_monitorati: 0,
  // lista_sedi: 0,
  lista_nomi_sedi: [],
  lista_id_sedi: [],
  client_template: {
    windows_services: {
      n_monitorati: 0,
      n_esecuzione: 0,
      n_stop: 0,
      n_totali: 0
    },
    windows_events: {
      n_problemi: 0,
      n_warnings: 0
    },
    info: { },
    alert: [],
    history: {
      lastUpdate: "",  //Dinamico
      options: {
        chart: {
          id: "basic-bar"
        },
        xaxis: {
          categories: []  //Dinamico
        },
        stroke: {
          curve: 'smooth',
        },
        fill: {
          type: "gradient",
          gradient: {
            shadeIntensity: 1,
            opacityFrom: 0.7,
            opacityTo: 0.9,
            stops: [0, 90, 100]
          }
        },
        dataLabels: {
          enabled: false
        },
      },
      colors: ["#dd4b39", "#f39c12"],
      series: [
        {
          name: "N° di warnings",
          data: [],   //Dinamico
          color: "#f39c12"
        },
        {
          name: "N° di problemi",
          data: [],  //Dinamico
          color: "#dd4b39"
        }
      ]
    },
    //services, events, drives
    overview: {
      problemi: [0,0,0],
      warnings: [0,0,0],
      ok: [0,0,0]
    },
    drives: [
      {
        perc_free: 0,
        total_size_GB: 0,
        occupied_GB: 0,
        free_GB: 0,
        ultimo_aggiornamento: ""
      }
    ]
  },
  company_template: {
    client_overview: {
      n_errori: [],
      n_warnings: [],
      n_running: []
    },
    category_vs_place: true,
    history: {  
      lastUpdate: "",  //Dinamico
      options: {
        chart: {
          id: "basic-bar"
        },
        xaxis: {
          categories: []  //Dinamico
        },
        stroke: {
          curve: 'smooth',
        },
        fill: {
          type: "gradient",
          gradient: {
            shadeIntensity: 1,
            opacityFrom: 0.7,
            opacityTo: 0.9,
            stops: [0, 90, 100]
          }
        },
        dataLabels: {
          enabled: false
        },
      },
      colors: ["#dd4b39", "#f39c12"],
      series: [
        {
          name: "N° di client con warnings",
          data: [],   //Dinamico
          color: "#f39c12"
        },
        {
          name: "N° di client con problemi",
          data: [],  //Dinamico
          color: "#dd4b39"
        }
      ]
    },
    licensesList: [],
    company_data: {
      email: null,
      emailNotify: null,
      ragione_sociale: null,
      n_sedi: 0 ,
      chiave_di_registrazione: null
    }
  }
};
export function rootReducer(state = initialState, action) {
  if (action.type === types.LOGIN) {
    return Object.assign({}, state, {
      ...state,
      company_template: {
        ...state.company_template,
        company_data: {
          ...state.company_template.company_data,
          ragione_sociale: action.nome_company,
          email: action.email,
          emailNotify: action.emailNotify,
          chiave_di_registrazione: action.chiave_di_registrazione,
          n_sedi: action.lista_sedi
        }
      },
      // nome_company: action.nome_company,
      id_company: action.id_company,
      // email: action.email,
      // emailNotify: action.emailNotify,
      client_list: action.client_list,
      logged: true,
      token: action.token,
      // lista_sedi: action.lista_sedi,
      // chiave_di_registrazione: action.chiave_di_registrazione,
      lista_nomi_sedi: action.listaNomiSedi,
      lista_id_sedi: action.listaIdSedi
    });
  }
  if (action.type === types.UPDATECOMPANYDATA) {
    return Object.assign({}, state, {
      ...state,
      company_template: {
        ...state.company_template,
        company_data: {
          ...state.company_template.company_data,
          ragione_sociale: action.nome_company,
          email: action.email,
          emailNotify: action.emailNotify
        }
      },
      // nome_company: action.nome_company,
      // email: action.email,
      // emailNotify: action.emailNotify,
      token: action.token
    });
  }
  if (action.type === types.SIGNUP) {
    return Object.assign({}, state, {
      ...state,
      message: action.message,
      messageCode: action.messageCode
    });
  }
  if (action.type === types.ADDLICENSE) {
    return Object.assign({}, state, {
      ...state,
      licensesList: action.license
    });
  }
  if (action.type === types.REMOVELICENSE) {
    return Object.assign({}, state, {
      ...state,
      licensesList: action.license
    });
  }
  if (action.type === types.SEARCHCLIENT) {
    return Object.assign({}, state, {
      ...state,
      searched_client: action.client_name
    });
  }
  if (action.type === types.PLACESLIST) {
    return Object.assign({}, state, {
      ...state,
      places_list: action.places_list
    });
  }
  if (action.type === types.CATEGORIESLIST) {
    return Object.assign({}, state, {
      ...state,
      categories_list: action.categories_list
    });
  }
  if (action.type === types.CATEGORYVSPLACE) {
    return Object.assign({}, state, {
      ...state,
      company_template: {
        ...state.company_template,
        category_vs_place: action.category_vs_place
      }
    });
  }
  if (action.type === types.SERVICESLIST) {
    return Object.assign({}, state, {
      ...state,
      services_list: action.servicesList
    });
  }
  if (action.type === types.EVENTSLIST) {
    return Object.assign({}, state, {
      ...state,
      events_list: action.eventsList
    });
  }
  if (action.type === types.SERVIZIOVERVIEW) {
    return Object.assign({}, state, {
      ...state,
      n_totali: action.n_totali,
      n_running: action.n_running,
      n_stop: action.n_stop,
      n_monitorati: action.n_monitorati
    });
  }
  if (action.type === types.LISTANOMISEDI) {
    return Object.assign({}, state, {
      ...state,
      //client_list: action.tmp_list,
      lista_nomi_sedi: action.listaNomi,
      token: action.token,
      lista_id_sedi: action.listaId,
      company_template: {
        ...state.company_template,
        company_data: {
          ...state.company_template.company_data,
          n_sedi: action.listaNomi.length
        }
      }
    });
  }
  if(action.type === types.UPDATESIDEBAR) {
    return Object.assign({}, state, {
      ...state,
      client_list: action.clientList,
      token: action.token,
      lista_sedi: action.lista_sedi,
      lista_nomi_sedi: action.listaNomi,
      lista_id_sedi: action.listaSedi
    });
  }
  if(action.type === types.CLIENTTEMPLATEWINDOWSEVENTS) {
    return Object.assign({}, state, {
      ...state,
      client_template: {
        ...state.client_template,
        windows_events: action.windows_events
      }
    });
  }
  if(action.type === types.CLIENTTEMPLATEWINDOWSSERVICES) {
    return Object.assign({}, state, {
      ...state,
      client_template: {
        ...state.client_template,
        windows_services: action.windows_services
      }
    });
  }
  if(action.type === types.CLIENTTEMPLATEINFO) {
    return Object.assign({}, state, {
      ...state,
      client_template: {
        ...state.client_template,
        info: action.info
      }
    });
  }
  if(action.type === types.CLIENTTEMPLATEALERT) {
    return Object.assign({}, state, {
      ...state,
      client_template: {
        ...state.client_template,
        alert: action.alert
      }
    });
  }
  if(action.type === types.CLIENTTEMPLATERESET) {
    return Object.assign({}, state, {
      ...state,
      client_template: initialState.client_template
    });
  }
  if(action.type === types.COMPANYTEMPLATELICENZE) {
    return Object.assign({}, state, {
      ...state,
      company_template: {
        ...state.company_template,
        licensesList: action.lista_licenze
      }
    });
  }
  if(action.type === types.UPDATECLIENTOVERVIEW) {
    return Object.assign({}, state, {
      ...state,
      client_template: {
        ...state.client_template,
        overview: action.overview
      }
    });
  }
  if(action.type === types.UPDATECOMPANYOVERVIEW) {
    return Object.assign({}, state, {
      ...state,
      company_template: {
        ...state.company_template,
        client_overview: action.overview
      }
    });
  }
  if(action.type === types.FIXSEDI) {
    return Object.assign({}, state, {
      ...state,
      client_list: action.lista_client
    });
  }
  if(action.type === types.UPDATECLIENTHISTORY) {
    return Object.assign({}, state, {
      client_template: {
        ...state.client_template,
        history: {
          ...state.client_template.history,
          lastUpdate: action.history_data.last_update,
          options: {
            ...state.client_template.history.options,
            xaxis: {
              categories: action.history_data.xaxis
            }
          },
          series: [
            {
              ...state.client_template.history.series[0],
              data: action.history_data.warnings,   //Dinamico
            },
            {
              ...state.client_template.history.series[1],
              data: action.history_data.problems,  //Dinamico
            }
          ]
        }
      }
    });
  }
  if(action.type === types.UPDATECOMPANYHISTORY) {
    return Object.assign({}, state, {
      company_template: {
        ...state.company_template,
        history: {
          ...state.company_template.history,
          lastUpdate: action.history_data.last_update,
          options: {
            ...state.company_template.history.options,
            xaxis: {
              categories: action.history_data.xaxis
            }
          },
          series: [
            {
              ...state.company_template.history.series[0],
              data: action.history_data.warnings,   //Dinamico
            },
            {
              ...state.company_template.history.series[1],
              data: action.history_data.problems,  //Dinamico
            }
          ]
        }
      }
    });
  }
  //returning the state
  return state;
}

export const persistConfig = {
  key: 'root',
  storage: storage,
  blacklist: ['nome_company', 'company_template' , 'client_template', 'lista_id_sedi', 'lista_nomi_sedi', 'chiave_di_registrazione', 'id_company', 'client_list', 'logged', 'token', 'licensesList', 'searched_client', 'places_list', 'categories_list', 'services_list', 'events_list']
};

export default persistReducer(persistConfig, rootReducer);