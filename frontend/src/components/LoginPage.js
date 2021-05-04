import React from 'react';
import { connect } from 'react-redux';
import toast from 'react-hot-toast';
import { login, placesList, categoriesList } from '../ActionCreator';
import Dashboard from './Dashboard';
import DashboardHome from './DashboardHome';
import {_getClientTypes, _getNomiSedi, _getNSedi, _getPlaces, _getShallowClients, _performLogin} from '../callableRESTs'; 
import { _MSGCODE } from '../Constants';
import { fake_shallowClientList } from '../fakeData';
import { getErrorToast, getLoadingToast, getSuccessToast, stopLoadingToast } from '../toastManager';

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps =  dispatch => ({
    Login: (nome_company, email, emailNotify, client_list, token) => {
      dispatch(login(nome_company, email, emailNotify, client_list, token))
    },
    LoginWithPlacesCategories: (nome_company, id_company, email, emailNotify, client_list, token, places_list, categories_list, lista_sedi, chiaveRegistrazione, listaNomiSedi, listaIdSedi) => {
      dispatch(categoriesList(categories_list))
      dispatch(placesList(places_list))
      dispatch(login(nome_company, id_company, email, emailNotify, client_list, token, lista_sedi, chiaveRegistrazione, listaNomiSedi, listaIdSedi))
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
    logged: state.logged
  }
);

/**
 * sign in/sign up form
 * @param {*} props 
 */
const LoginPage = (props) => {

  const [state, setState] = React.useState({
    emailLogin: "",
    pswLogin: ""
  })

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
   * handle password login and map it to local state pswLogin
   * @param {*} evt 
   */
  const handlePswLogin = (evt) => {
    setState((previousState) => {
      return { ...previousState, pswLogin: evt.target.value };
    });
  }

  //const { addToast } = useToasts();

  const LoginController = (email, psw) => {
    const loadingToast = getLoadingToast("Accesso in corso...");
    _performLogin(email, psw)
    .then(function (response) {
      if(response.data.messageCode==_MSGCODE.ERR){
        //login failed
        stopLoadingToast(loadingToast);
        getErrorToast(response.data.message);
      }else{
        //login done, need to retieve 'sedi' and 'categories'
        stopLoadingToast(loadingToast);
        getSuccessToast(response.data.message);
        //missing from login: email, emailNotify
        let ragione_sociale = response.data.ragione_sociale;
        let id_company = response.data.id_company;
        let token = response.data.token;
        let emailNotify = response.data.emailNotify;
        let chiaveRegistrazione = response.data.chiave_di_registrazione;
        _getShallowClients(id_company, token)
        .then(function (response) {
          console.log(response.data.shallowClients)
          let elencoClients = response.data.shallowClients;
          _getPlaces(id_company, token)
          .then(function (response) {
            //get places
            //console.log(response.data)
            let sedi = response.data.sedi;
            _getClientTypes(id_company, token)
            .then(function (response) {
              //get categories
              let categories = response.data.categories;
              _getNSedi(token, id_company)
              .then(function (response) {
                //get n sedi
                let n_sedi = response.data.nsedi;
                  _getNomiSedi(token, id_company)
                  .then(function (response) {
                    let listaNomi = [];
                      let listaSedi = [];
                      response.data.sedi.map((sede) => {
                        listaNomi.push(sede.substring(sede.indexOf(",") + 1, sede.length));
                        listaSedi.push(sede.substring(0,sede.indexOf(",")));
                      })
                      props.LoginWithPlacesCategories(ragione_sociale, id_company, email, emailNotify, elencoClients, token, sedi, categories, n_sedi, chiaveRegistrazione, listaNomi, listaSedi);
                    })
                  .catch(function (error) {
                    getErrorToast(String(error));
                  });

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
          .catch(function (error) {
            stopLoadingToast(loadingToast);
            getErrorToast(String(error));
          })
        })
        .catch(function (error) {
          stopLoadingToast(loadingToast);
          getErrorToast(String(error));
        })
      }
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  return (
    (props.logged===false) 
    ?
      <div className="container">
        <div className="row">
          <br />
          <div className="col-md-6 col-md-offset-3">
            <form>
              <div className="form-group">
                <label htmlFor="LoginEmail1">Indirizzo email</label>
                <input type="email" value={state.emailLogin} className="form-control" id="LoginEmail1" aria-describedby="emailHelp" placeholder="Email" onChange={handleEmailLogin}/>
              </div>
              <div className="form-group">
                <label htmlFor="LoginPassword1">Password</label>
                <input type="password" value={state.pswLogin} className="form-control" id="LoginPassword1" placeholder="Password" onChange={handlePswLogin} />
              </div>
            </form>
            <button className="btn btn-primary" onClick={() => LoginController(state.emailLogin, state.pswLogin)}>Accedi</button>
          </div>
        </div>
      </div>
    :
      <div>
        <DashboardHome path={"/"+props.nome_company} title={props.nome_company} />
        {props.client_list.map((item) => <Dashboard path={"/company"+props.nome_company+"user"+item.id_client} title={item.nome_client} />)}
      </div>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(LoginPage);
