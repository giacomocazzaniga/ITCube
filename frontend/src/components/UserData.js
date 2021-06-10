import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Col, Box } from 'adminlte-2-react';
import Dropdown from 'react-dropdown';
import PopUp from './PopUp';
import { _addTier, _cancellazioneSede, _editCompanyData, _getClientTypes, _getNomiSedi, _inserimentoSede, _removeTier } from '../callableRESTs';
import { addTier, categoriesList, fixSedi, listaNomiSedi, removeTier, totalReset, updateCompanyData, updateToken } from '../ActionCreator';
import { getErrorToast, getLoadingToast, getSuccessToast, stopLoadingToast } from '../toastManager';
import { dispatch } from 'react-hot-toast';
import { _MSGCODE } from '../Constants';
import { autenticazione_fallita, renewToken } from '../Tools';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { OverlayTrigger } from 'react-bootstrap';
import { Tooltip } from 'bootstrap';

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({
    UpdateCompanyData: (nome_company, email, emailNotify, token) => {
      dispatch(updateCompanyData(nome_company, email, emailNotify, token))
    },
    UpdateListaSedi: (listaSedi, token, listaId) => {
      dispatch(listaNomiSedi(listaSedi, token, listaId))
    },
    UpdateToken: (token) => {
      dispatch(updateToken(token));
    },
    TotalReset: () => {
      dispatch(totalReset())
    },
    AddTier: (tier) => {
      dispatch(addTier(tier))
    },
    RemoveTier: (tierToRemove) => {
      dispatch(removeTier(tierToRemove))
    },
    FixSedi: (client_list, lista_nomi_sedi, lista_id_sedi) => {
      let tmp_list = client_list;
      let tmp_list2 = []
      tmp_list.map(tmp_client => {
        lista_nomi_sedi.map((sede,i) => {
          if((tmp_client.sede === sede) || (tmp_client.sede === lista_id_sedi[i])){
            tmp_client.sede = String(lista_id_sedi[i]);
            tmp_list2.push(tmp_client)
          }
        })
      })
      dispatch(fixSedi(tmp_list2))
    }
  }
);

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({
    client_list: state.client_list,
    nome_company: state.company_template.company_data.ragione_sociale,
    token: state.token,
    logged: state.logged,
    id_company: state.id_company,
    emailNotify: state.company_template.company_data.emailNotify,
    email: state.company_template.company_data.email,
    lista_sedi: state.company_template.company_data.n_sedi,
    listaNomiSedi: state.lista_nomi_sedi,
    lista_id_sedi: state.lista_id_sedi,
    categories_list: state.categories_list
  }
);



const UserData = (props) => {


  const [state, setState] = React.useState({
    emailLogin: "",
    emailLogin2: "",
    pswLogin: "",
    nuovaSede: "",
    sedeDaCancellare: "",
    tier: "",
    tierToRemove: ""
  })

  const clickService = () => {
    let continueUpdating = true;
    let email = (state.emailLogin=="") ? props.email : state.emailLogin;
    let emailAlert = (state.emailLogin2=="") ? props.emailNotify : state.emailLogin2;
    let ragioneSociale = (state.pswLogin=="") ? props.nome_company : state.pswLogin;
    const loadingToast = getLoadingToast("Modificando i dati...");
    return _editCompanyData(props.id_company, props.token, email, emailAlert, ragioneSociale)
    .then(function (response) {

      if(autenticazione_fallita(response.data.messageCode)) {
        stopLoadingToast(loadingToast);
        getErrorToast("Sessione scaduta");
        props.TotalReset();
        continueUpdating = false;
      }
      if(continueUpdating != false) {
        let token= props.token;
        if(renewToken(props.token, response.data.token)){
          props.UpdateToken(response.data.token);
          token = response.data.token;
        }

        stopLoadingToast(loadingToast);
        let token1 = (response.data.token=="" || response.data.token==null) ? token : response.data.token;
        props.UpdateCompanyData(ragioneSociale, email, emailAlert, token1);
        getSuccessToast("Dati modificati correttamente.");
      }
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  const clickServiceAggiungi = () => {
    let continueUpdating = true;
    let nome = state.nuovaSede;
    const loadingToast = getLoadingToast("Aggiungendo una nuova sede...");
    return _inserimentoSede( props.token, props.id_company, nome)
    .then(function (response) {

      if(autenticazione_fallita(response.data.messageCode)) {
        stopLoadingToast(loadingToast);
        getErrorToast("Sessione scaduta");
        props.TotalReset();
        continueUpdating = false;
      }
      if(continueUpdating != false) {
        let token= props.token;
        if(renewToken(props.token, response.data.token)){
          props.UpdateToken(response.data.token);
          token = response.data.token;
        }

        _getNomiSedi(token, props.id_company)
        .then(function (response) {
          stopLoadingToast(loadingToast);
          let token1 = (response.data.token=="" || response.data.token==null) ? token : response.data.token;
          let listaNomi = [];
          let listaSedi = [];
          response.data.sedi.map((sede) => {
            listaNomi.push(sede.substring(sede.indexOf(",") + 1, sede.length));
            listaSedi.push(sede.substring(0,sede.indexOf(",")));
          })
          
          let myPromise = new Promise(function (myResolve,myReject) {
            props.UpdateListaSedi(listaNomi, token1, listaSedi)
            myResolve();
          });
          myPromise.then(
            function (value) {    
              let tmp_list = props.client_list;
              let tmp_list2 = []
              tmp_list.map(tmp_client => {
                props.listaNomiSedi.map((sede,i) => {
                  if((tmp_client.sede === sede) || (tmp_client.sede === props.lista_id_sedi[i])){
                    tmp_client.sede = String(props.lista_id_sedi[i]);
                    tmp_list2.push(tmp_client)
                  }
                })
              })
              props.FixSedi(tmp_list2, props.listaNomiSedi, props.lista_id_sedi);
            },
            function (error) {}
          )
          getSuccessToast("Sede aggiunta correttamente.");
        })
        .catch(function (error) {
          getErrorToast(String(error));
        });
      }
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }
  
  const clickServiceCancella = (evt) => {
    let continueUpdating = true;
    evt.preventDefault()
    let nome = state.sedeDaCancellare;
    const loadingToast = getLoadingToast("Rimuovendo la sede...");
    return _cancellazioneSede( props.token, props.id_company, nome)
    .then(function (response) {

      if(autenticazione_fallita(response.data.messageCode)) {
        stopLoadingToast(loadingToast);
        getErrorToast("Sessione scaduta");
        props.TotalReset();
        continueUpdating = false;
      }
      if(continueUpdating != false) {
        let token= props.token;
        if(renewToken(props.token, response.data.token)){
          props.UpdateToken(response.data.token);
          token = response.data.token;
        }

        if(response.data.messageCode != _MSGCODE.NO_ERR){
          stopLoadingToast(loadingToast);
          getErrorToast(String(response.data.message))
        } else {
          _getNomiSedi(props.token, props.id_company)
          .then(function (response) {
            stopLoadingToast(loadingToast);
            let token = (response.data.token=="" || response.data.token==null) ? props.token : response.data.token;
            let listaNomi = [];
            let listaSedi = [];
            response.data.sedi.map((sede) => {
              listaNomi.push(sede.substring(sede.indexOf(",") + 1, sede.length));
              listaSedi.push(sede.substring(0,sede.indexOf(",")));
            })
            let myPromise = new Promise(function (myResolve,myReject) {
              props.UpdateListaSedi(listaNomi, token, listaSedi)
              myResolve();
            });
            myPromise.then(
              function (value) {    
                let tmp_list = props.client_list;
                let tmp_list2 = []
                tmp_list.map(tmp_client => {
                  props.listaNomiSedi.map((sede,i) => {
                    if((tmp_client.sede === sede) || (tmp_client.sede === props.lista_id_sedi[i])){
                      tmp_client.sede = String(props.lista_id_sedi[i]);
                      tmp_list2.push(tmp_client)
                    }
                  })
                })
                props.FixSedi(tmp_list2, props.listaNomiSedi, props.lista_id_sedi);
              },
              function (error) {}
            )
            getSuccessToast("Sede rimossa correttamente.");
          })
          .catch(function (error) {
            getErrorToast(String(error));
          });
        }
      }
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  // const clickServiceCancella = () => {
  //   let nome = state.sedeDaCancellare;
  //   const loadingToast = getLoadingToast("Eliminando una sede...");
  //   return _cancellazioneSede( props.token, props.id_company, nome)
  //   .then(function (response) {
  //       stopLoadingToast(loadingToast);
  //       let token = (response.data.token=="" || response.data.token==null) ? props.token : response.data.token;
  //       //props.UpdateCompanyData(ragioneSociale, email, emailAlert, token);
  //       let newList = state.listaSedi.pop(state.sedeDaCancellare);
  //       setState((previousState) => {
  //         return { ...previousState, listaSedi: newList };  
  //       });
  //       getSuccessToast("Sede eliminata correttamente.");
  //   })
  //   .catch(function (error) {
  //     stopLoadingToast(loadingToast);
  //     getErrorToast(String(error));
  //   });
  // }



  /**
   * handle email login and map it to local state emailLogin
   * @param {*} evt 
   */
  const handleEmailLogin = (evt) => {
    setState((previousState) => {
      return { ...previousState, emailLogin: evt.target.value };  
    });
  }

  /**
   * handle email login and map it to local state emailLogin
   * @param {*} evt 
   */
    const handleEmailLogin2 = (evt) => {
      setState((previousState) => {
        return { ...previousState, emailLogin2: evt.target.value };  
      });
    }

  /**
   * handle password login and map it to local state pswLogin
   * @param {*} evt 
   */
  const handlePswLogin = (evt) => {
    setState((previousState) => {
      return { ...previousState, pswLogin: evt.target.value };
    });
  }

  /**
 * handle password login and map it to local state pswLogin
 * @param {*} evt 
 */
    const handleNuovaSede = (evt) => {
    setState((previousState) => {
      return { ...previousState, nuovaSede: evt.target.value };
    });
  }

    /**
 * handle password login and map it to local state pswLogin
 * @param {*} evt 
 */
    const handleRimuoviSede = (evt) => {
      setState((previousState) => {
        return { ...previousState, sedeDaCancellare: evt.value };
      });
    }

    useEffect(() => {
      _getNomiSedi(props.token, props.id_company)
      .then(function (response) {
        let token = (response.data.token=="" || response.data.token==null) ? props.token : response.data.token;
        let listaNomi = [];
        let listaSedi = [];
        response.data.sedi.map((sede) => {
          listaNomi.push(sede.substring(sede.indexOf(",") + 1, sede.length));
          listaSedi.push(sede.substring(0,sede.indexOf(",")));
          
        })
        let myPromise = new Promise(function (myResolve,myReject) {
          props.UpdateListaSedi(listaNomi, token, listaSedi)
          myResolve();
        });
        myPromise.then(
          function (value) {    
            let tmp_list = props.client_list;
            let tmp_list2 = []
            props.client_list.map(tmp_client => {
              props.listaNomiSedi.map((sede,i) => {
                if((tmp_client.sede === sede) || (tmp_client.sede === props.lista_id_sedi[i])){
                  tmp_client.sede = String(props.lista_id_sedi[i]);
                  tmp_list2.push(tmp_client)
                }
              })
            })
            props.FixSedi(tmp_list2, props.listaNomiSedi, props.lista_id_sedi);
          },
          function (error) {}
        )
      })
      .catch(function (error) {
        getErrorToast(String(error));
      });
    },[])
  
  const getChilds = () => {
    let childList = [];
    childList = [
      <>
        <div className="col-md-6 col-xs-12">
          <form>
            <div className="form-group">
              <label htmlFor="LoginEmail1">Indirizzo email</label>
              <input type="email" value={state.emailLogin} className="form-control" id="LoginEmail1" aria-describedby="emailHelp" placeholder={props.email} onChange={handleEmailLogin}/>
            </div>
            <div className="form-group">
              <label htmlFor="LoginEmail2">Indirizzo email per le comunicazioni</label>
              <input type="email" value={state.emailLogin2} className="form-control" id="LoginEmail2" aria-describedby="emailHelp" placeholder={props.emailNotify} onChange={handleEmailLogin2}/>
            </div>
            <div className="form-group">
              <label htmlFor="LoginPassword1">Ragione Sociale</label>
              <input value={state.pswLogin} className="form-control" id="LoginPassword1" placeholder={props.ragioneSociale} onChange={handlePswLogin} />
            </div>
          </form>
          <center><button className="btn btn-primary" onClick={() => clickService()}>Modifica</button></center>
        </div>
        <div className="col-md-6 col-xs-12">
          <form>
            <div className="col-md-12 col-xs-12">
              <label>Lista delle sedi</label>
            </div>
            <div className="form-group col-md-8 col-xs-8">
              <Dropdown onChange={handleRimuoviSede} options={props.listaNomiSedi} placeholder="Seleziona una sede" />           
            </div>
            <div className="col-md-4 col-xs-4">
            <button className="btn btn-primary" onClick={(evt) => clickServiceCancella(evt)}>Rimuovi</button>
            </div>
          </form>
          <form>
            <div className="form-group">
              <label htmlFor="AddPlace">Aggiungi una nuova sede</label>
              <input type="text" className="form-control" id="AddPlace" aria-describedby="AddPlace"  onChange={handleNuovaSede}/>
            </div>
          </form>
          <center><button className="btn btn-primary" onClick={() => clickServiceAggiungi()}>Aggiungi</button></center>
        </div>
      </>
    ]
    return childList;
  }

  const handleAddTier = (evt) => {
    setState((previousState) => {
      return { ...previousState, tier: evt.target.value };
    });
  }

  const addTierChilds = () => {
   return (
      [
      <>
        <div className="col-md-12 col-xs-12">
          <form>
            <div className="form-group">
              <label htmlFor="LoginEmail1">Inserisci il nome del tier:</label>
              <input type="email" value={state.tier} className="form-control" id="LoginEmail1" aria-describedby="emailHelp" placeholder={"Server Tier 1"} onChange={handleAddTier}/>
            </div>
            
          </form>
          <center><button className="btn btn-primary" onClick={() => addTierClick()}>Aggiungi</button></center>
        </div>
      </>
      ]
   )
  }

  const addTierClick = () => {
    const loadingToast = getLoadingToast("Aggiungendo il tier...");
    _addTier(props.token, props.id_company, state.tier)
    .then( response => {
      props.AddTier(state.tier)
      stopLoadingToast(loadingToast)
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  const handleRemoveTier = (evt) => {
    setState((previousState) => {
      return { ...previousState, tierToRemove: evt.value };
    });
  }

  const removeTierChilds = () => {

    const options = [];
    props.categories_list.forEach( category => {
      options.push(category.nome_categoria)
    })

    return (
       [
       <>
         <div className="col-md-12 col-xs-12">
           <form>
             <div className="form-group">
               <label htmlFor="LoginEmail1">Seleziona il tier da eliminare</label> 
               <Dropdown options={options} placeholder="Seleziona tier" onChange={handleRemoveTier}/>
             </div>
           </form>
           <center><button className="btn btn-primary" onClick={removeTierClick}>Rimuovi</button></center>
         </div>
       </>
       ]
    )
  }

  const removeTierClick = () => {
    const loadingToast = getLoadingToast("Rimuovendo il tier...");
    _removeTier(props.token, props.id_company, state.tierToRemove)
    .then( response => {
      props.RemoveTier(state.tierToRemove)
      stopLoadingToast(loadingToast)
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  return (
    <Box title="Dati aziendali" type="primary" collapsable footer={<span href="#" className="small-box-footer"><PopUp title="Modifica dati aziendali" linkClass={"clickable"} childs={getChilds()} action={()=>(console.log("action"))}/></span>}>
      <Col md={12} xs={12}>
        <h4><b>Indirizzo email: </b>{props.email}</h4>
        <h4><b>Indirizzo email per le comunicazioni: </b>{props.emailNotify}</h4>
        <h4><b>Ragione sociale: </b>{props.ragioneSociale}</h4>
        <h4><b>Sedi registrate: </b>{props.lista_sedi - 1}</h4>
        <h4><b>Chiave di registrazione: </b>{props.chiave}</h4>
        <div className="btns-container">
          <PopUp title="Aggiungi Tier" messageLink={"Aggiungi Tier"} linkClass={"clickable"} childs={addTierChilds()} action={()=>(null)}/>
          <PopUp title="Rimuovi Tier" messageLink={"Rimuovi Tier"} linkClass={"clickable"} childs={removeTierChilds()} action={()=>(null)}/>
        </div>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(UserData);