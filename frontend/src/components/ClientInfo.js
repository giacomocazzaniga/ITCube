import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
import { _LICENZE } from '../Constants';
import PopUp from './PopUp';
import { _getNomiSedi, _modificaSedeClient } from '../callableRESTs';
import { getErrorToast, getLoadingToast, stopLoadingToast } from '../toastManager';
import Dropdown from 'react-dropdown';

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({});

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({
  id_company: state.id_company,
  token: state.token
});

const ClientInfo = (props) => {

  const [state, setState] = React.useState({
    vecchiaSede: "",
    nuovaSede: "",
    sedi: []
  })

  useEffect(() => {
    _getNomiSedi(props.token, props.id_company)
    .then(function (response) {
      setState((previousState) => {
        return { ...previousState, sedi: response.data.sedi };
      });
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
              <input type="text" value={vecchiaSede} class="form-control" id="VecchiaSede" placeholder={vecchiaSede} onChange={handleVecchiaSede} disabled/>
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
    console.log(state.nuovaSede)
    console.log(state.vecchiaSede)
    const loadingToast = getLoadingToast("Modificando i dati...");
    return _modificaSedeClient(props.token, props.id_client, props.id_company, state.nuovaSede, state.vecchiaSede)
    .then(function (response) {
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

  return (
    <Box title="Informazioni sul client" type="primary" collapsable>
      <Col md={12} xs={12}>
        <h4><b>Nome: </b>{props.client.nome}</h4>
        <h4><b>Tipologia: </b>{props.client.nome_tipologia}</h4>
        <h4><b>MAC address: </b>{props.client.mac_address}</h4>
        <h4><b>Licenze in uso: </b></h4>
        <p>{props.client.codice_licenza.map((lic, i) => {
          return props.client.classe_licenza.map((tipo, j) => {
            return (i==j) ? <p>{i+1}) {getLicense(String(tipo))} {lic}</p> : <></>
          })
          //return <p>{i+1}) </p>
        })}</p>
        {/*<h4><b>Gruppo di lavoro: </b>{props.client.category}</h4>*/}
        <h4><b>Sede di lavoro: </b>{props.client.sede} <PopUp title="Modifica sede" messageLink={"modifica"} linkClass={"clickable"} childs={getChilds(props.client.sede)} action={()=>(null)}/></h4>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(ClientInfo);