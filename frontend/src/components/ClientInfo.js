import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
import { _LICENZE } from '../Constants';
import PopUp from './PopUp';
import { _getNomiSedi, _getPlaces, _getShallowClients, _getTypeFromClient, _modificaSedeClient, _updateTier } from '../callableRESTs';
import { getErrorToast, getLoadingToast, stopLoadingToast } from '../toastManager';
import Dropdown from 'react-dropdown';
import { autenticazione_fallita, idToNomeSede, nomeToIdSede, renewToken } from '../Tools';
import { changeTipoClient, totalReset, updateCTInfo, updateSidebar } from '../ActionCreator';

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({
  UpdateSidebar: (elencoClients,sedi, token, listaNomi, listaSedi) => {
    dispatch(updateSidebar(elencoClients,sedi, token, listaNomi, listaSedi));
  },
  SetClientTemplateInfo: (info) => {
    dispatch(updateCTInfo(info))
  },
  TotalReset: () => {
    dispatch(totalReset())
  },
  ChangeTipoClient: (tier,id_client) => {
    dispatch(changeTipoClient(tier,id_client))
  }
});

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({
  id_company: state.id_company,
  token: state.token, 
  lista_id_sedi: state.lista_id_sedi,
  lista_nomi_sedi: state.lista_nomi_sedi,
  client_template: state.client_template,
  categories_list: state.categories_list
});

const ClientInfo = (props) => {

  const [state, setState] = React.useState({
    vecchiaSede: "",
    nuovaSede: "",
    sedi: [],
    oldTier: "",
    allTiers: [],
    changeTiers: ""
  })

  useEffect(() => {
    let continueUpdating = true;
    _getNomiSedi(props.token, props.id_company)
    .then(function (response) {

      if(autenticazione_fallita(response.data.messageCode)) {
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

        let listaNomi = [];
          let listaSedi = [];
          response.data.sedi.map((sede) => {
            listaNomi.push(sede.substring(sede.indexOf(",") + 1, sede.length));
            listaSedi.push(sede.substring(0,sede.indexOf(",")));
          })
        setState((previousState) => {
          return { ...previousState, sedi: listaNomi };
        });
        _getTypeFromClient(token, props.id_client)
        .then( response => {
          setState( (state) => {
            return (
              {...state,oldTier: response.data.nome_tipologia}
            )
          })
        })
      }
    })
    .catch(function (error) {
      getErrorToast(String(error));
    });
  },[])

  const getChilds = (vecchiaSede) => {
    let childList = [];
    childList = [
      <>
        <div className="col-md-12 col-xs-12">
          <form>
            <div class="form-group">
              <label htmlFor="VecchiaSede">Vecchia Sede</label>
              <input type="text" value={idToNomeSede(vecchiaSede, props.lista_nomi_sedi, props.lista_id_sedi)} class="form-control" id="VecchiaSede" placeholder={idToNomeSede(vecchiaSede, props.lista_nomi_sedi, props.lista_id_sedi)} onChange={handleVecchiaSede} disabled/>
            </div>
            <div class="form-group">
              <label htmlFor="NuovaSede">Nuova Sede</label>
              <Dropdown onChange={handleNuovaSede} options={state.sedi} placeholder="Seleziona una sede" />
            </div>
          </form>
          <br />
          <center><button class="btn btn-primary" onClick={() => clickService()}>Modifica</button></center>
        </div>
      </>
    ]
    return childList;
  }

  const clickService = () => {
    let continueUpdating = true;
    console.log(state.nuovaSede)
    console.log(state.vecchiaSede)
    const loadingToast = getLoadingToast("Modificando i dati...");
    return _modificaSedeClient(props.token, props.id_client, props.id_company, state.nuovaSede, state.vecchiaSede)
    .then(function (response) {

      if(autenticazione_fallita(response.data.messageCode)) {
        stopLoadingToast(loadingToast);
        getErrorToast("Sessione scaduta");
        props.TotalReset();
        continueUpdating = false;
      }
      if(continueUpdating != false) {
        let token= props.token;
        if(renewToken(token, response.data.token)){
          props.UpdateToken(response.data.token);
          token = response.data.token;
        }

        let info_tmp = props.client_template.info;
        info_tmp.sede = nomeToIdSede(state.nuovaSede, props.lista_nomi_sedi, props.lista_id_sedi)
        props.SetClientTemplateInfo(info_tmp);
        _getShallowClients(props.id_company, token)
        .then(function (response) {
          let elencoClients = response.data.shallowClients;
          _getPlaces(props.id_company, token)
          .then(function (response) {
            let sedi = response.data.sedi;
              _getNomiSedi(token, props.id_company)
              .then(function (response) {
                let token = (response.data.token=="" || response.data.token==null) ? props.token : response.data.token;
                let listaNomi = [];
                let listaSedi = [];
                response.data.sedi.map((sede) => {
                  listaNomi.push(sede.substring(sede.indexOf(",") + 1, sede.length));
                  listaSedi.push(sede.substring(0,sede.indexOf(",")));
                })
                props.UpdateSidebar(elencoClients,sedi, token, listaNomi, listaSedi);

              })
            .catch(function (error) {
              stopLoadingToast(loadingToast);
              getErrorToast(String(error));
            })
          })
          .catch(function (error) {
            stopLoadingToast(loadingToast);
            getErrorToast(String(error));
          })
        })
      }

        stopLoadingToast(loadingToast);
        let token = (response.data.token=="" || response.data.token==null) ? props.token : response.data.token;
        //props.UpdateCompanyData(ragioneSociale, email, emailAlert, token);
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  const handleVecchiaSede = (evt) => {
    setState((previousState) => {
      return { ...previousState, vecchiaSede: evt.target.value };  
    });
  }

  const handleNuovaSede = (evt) => {
    setState((previousState) => {
      return { ...previousState, nuovaSede: evt.value };  
    });
  }

  const getLicense = (classe_licenza) => {
    let label = "";
    switch(classe_licenza){
    case _LICENZE.SISTEMA_OPERATIVO.tipo:
      label = _LICENZE.SISTEMA_OPERATIVO.label;
      break;
    case _LICENZE.BACKUP.tipo:
      label = _LICENZE.BACKUP.label;
      break;
    case _LICENZE.ANTIVIRUS.tipo:
      label = _LICENZE.ANTIVIRUS.label;
      break;
    case _LICENZE.RETE.tipo:
      label = _LICENZE.RETE.label;
      break;
    case _LICENZE.VULNERABILITA.tipo:
      label = _LICENZE.VULNERABILITA.label;
      break;
    }
    return label;
  }

  const handleChangeTier = (evt) => {
    setState({
      ...state,
      changeTiers: evt.value
    })
  }

  const changeTier = () => {
    const loadingToast = getLoadingToast("Modificando i dati...");
    _updateTier(props.token,state.changeTiers,props.id_client)
    .then( response => {
      props.ChangeTipoClient(state.changeTiers, props.id_client);
      stopLoadingToast(loadingToast);
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  const changeTierChilds = () => {

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
                <label htmlFor="LoginEmail1">Vecchio tier</label>
                <input type="text" value={state.oldTier} class="form-control" id="VecchioTier" placeholder={state.oldTier} disabled/>
              </div>
              <div class="form-group">
                <label htmlFor="NuovaSede">Nuovo Tier</label>
                <Dropdown options={options} placeholder="Seleziona un Tier" onChange={handleChangeTier}/>
              </div>
            </form>
            <center><button className="btn btn-primary" onClick={changeTier}>Modifica</button></center>
          </div>
        </>
      ]
    )
  }

  return (
    <Box title="Informazioni sul client" type="primary" collapsable>
      <Col md={12} xs={12}>
        <h4><b>Nome: </b>{props.client.nome}</h4>
        <h4><b>Tipologia: </b>{props.client.nome_tipologia}</h4>
        <h4><b>MAC address: </b>{props.client.mac_address}</h4>
        <h4><b>Licenze in uso: </b></h4>
        {props.client.codice_licenza.map((lic, i) => {
          return props.client.classe_licenza.map((tipo, j) => {
            return (i==j) ? <p>{i+1}) {getLicense(String(tipo))} {lic}</p> : <></>
          })
          //return <p>{i+1}) </p>
        })}
        {/*<h4><b>Gruppo di lavoro: </b>{props.client.category}</h4>*/}
        <h4><b>Sede di lavoro: </b>{idToNomeSede(props.client.sede, props.lista_nomi_sedi, props.lista_id_sedi)} <PopUp title="Modifica sede" messageLink={"modifica"} linkClass={"clickable"} childs={getChilds(props.client.sede)} action={()=>(null)}/></h4>
        <div className={"btns-container"}>
          <PopUp title="Sposta Client" messageLink={"Cambia Tier per il client"} linkClass={"clickable"} childs={changeTierChilds()} action={()=>(null)}/>
        </div>
        
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(ClientInfo);