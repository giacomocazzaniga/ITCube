import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Col, Box } from 'adminlte-2-react';
import Dropdown from 'react-dropdown';
import PopUp from './PopUp';
import { _cancellazioneSede, _editCompanyData, _getNomiSedi, _inserimentoSede } from '../callableRESTs';
import { listaNomiSedi, updateCompanyData } from '../ActionCreator';
import { getErrorToast, getLoadingToast, getSuccessToast, stopLoadingToast } from '../toastManager';
import { dispatch } from 'react-hot-toast';

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
    }
  }
);

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({
    client_list: state.client_list,
    nome_company: state.nome_company,
    token: state.token,
    logged: state.logged,
    id_company: state.id_company,
    emailNotify: state.emailNotify,
    email: state.email,
    lista_sedi: state.lista_sedi,
    listaNomiSedi: state.lista_nomi_sedi
  }
);



const UserData = (props) => {


  const [state, setState] = React.useState({
    emailLogin: "",
    emailLogin2: "",
    pswLogin: "",
    nuovaSede: "",
    sedeDaCancellare: ""
  })

  const clickService = () => {
    let email = (state.emailLogin=="") ? props.email : state.emailLogin;
    let emailAlert = (state.emailLogin2=="") ? props.emailNotify : state.emailLogin2;
    let ragioneSociale = (state.pswLogin=="") ? props.nome_company : state.pswLogin;
    const loadingToast = getLoadingToast("Modificando i dati...");
    return _editCompanyData(props.id_company, props.token, email, emailAlert, ragioneSociale)
    .then(function (response) {
        stopLoadingToast(loadingToast);
        let token = (response.data.token=="" || response.data.token==null) ? props.token : response.data.token;
        props.UpdateCompanyData(ragioneSociale, email, emailAlert, token);
        getSuccessToast("Dati modificati correttamente.");
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  const clickServiceAggiungi = () => {
    let nome = state.nuovaSede;
    const loadingToast = getLoadingToast("Aggiungendo una nuova sede...");
    return _inserimentoSede( props.token, props.id_company, nome)
    .then(function (response) {
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
          props.UpdateListaSedi(listaNomi, token, listaSedi);
          getSuccessToast("Sede aggiunta correttamente.");
        })
        .catch(function (error) {
          getErrorToast(String(error));
        });
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }
  
  const clickServiceCancella = () => {
    let nome = state.sedeDaCancellare;
    const loadingToast = getLoadingToast("Rimuovendo la sede...");
    return _cancellazioneSede( props.token, props.id_company, nome)
    .then(function (response) {
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
        props.UpdateListaSedi(listaNomi, token, listaSedi);
        getSuccessToast("Sede rimossa correttamente.");
      })
      .catch(function (error) {
        getErrorToast(String(error));
      });
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
        props.UpdateListaSedi(listaNomi, token, listaSedi);
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
            <div class="form-group">
              <label htmlFor="LoginEmail1">Indirizzo email</label>
              <input type="email" value={state.emailLogin} class="form-control" id="LoginEmail1" aria-describedby="emailHelp" placeholder={props.email} onChange={handleEmailLogin}/>
            </div>
            <div class="form-group">
              <label htmlFor="LoginEmail2">Indirizzo email per le comunicazioni</label>
              <input type="email" value={state.emailLogin2} class="form-control" id="LoginEmail2" aria-describedby="emailHelp" placeholder={props.emailNotify} onChange={handleEmailLogin2}/>
            </div>
            <div class="form-group">
              <label htmlFor="LoginPassword1">Ragione Sociale</label>
              <input value={state.pswLogin} class="form-control" id="LoginPassword1" placeholder={props.ragioneSociale} onChange={handlePswLogin} />
            </div>
          </form>
          <center><button class="btn btn-primary" onClick={() => clickService()}>Modifica</button></center>
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
            <button className="btn btn-primary" onClick={() => clickServiceCancella()}>Rimuovi</button>
            </div>
          </form>
          <form>
            <div class="form-group">
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
  return (
    <Box title="Dati aziendali" type="primary" collapsable footer={<span href="#" class="small-box-footer"><PopUp title="Modifica dati aziendali" linkClass={"clickable"} childs={getChilds()} action={()=>(console.log("action"))}/></span>}>
      <Col md={12} xs={12}>
        <h4><b>Indirizzo email: </b>{props.email}</h4>
        <h4><b>Indirizzo email per le comunicazioni: </b>{props.emailNotify}</h4>
        <h4><b>Ragione sociale: </b>{props.ragioneSociale}</h4>
        <h4><b>Sedi registrate: </b>{props.lista_sedi}</h4>
        <h4><b>Chiave di registrazione: </b>{props.chiave}</h4>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(UserData);