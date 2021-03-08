import React from 'react';
import { connect } from 'react-redux';
import { ToastProvider, useToasts } from 'react-toast-notifications';
import { login, placesList, categoriesList } from '../ActionCreator';
import { url_lista_sedi, url_lista_sediFake, url_login } from '../REST';
import Dashboard from './Dashboard';
import DashboardHome from './DashboardHome';
import {_getClientTypes, _getPlaces, _performLogin} from '../callableRESTs'; 
import { _MSGCODE } from '../Constants';
import { fake_elencoClients, fake_shallowClientList } from '../fakeData';

var md5 = require('md5');

const axios = require('axios');

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps =  dispatch => {
  return{
    Login: (nome_company, email, emailNotify, client_list, token) => {
      dispatch(login(nome_company, email, emailNotify, client_list, token))
    },
    LoginWithPlacesCategories: (nome_company, id_company, email, emailNotify, client_list, token, places_list, categories_list) => {
      dispatch(categoriesList(categories_list))
      dispatch(placesList(places_list))
      dispatch(login(nome_company, id_company, email, emailNotify, client_list, token))
    }
  }
}


/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => {
  return {
    client_list: state.client_list,
    nome_company: state.nome_company

  }
}

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

  const { addToast } = useToasts();

  const LoginController = (email, psw) => {
    _performLogin(email, psw)
    .then(function (response) {
      if(response.data.messageCode==_MSGCODE.ERR){
        //login failed
        addToast(response.data.message, {appearance: 'error',autoDismiss: true});
      }else{
        //login done, need to retieve 'sedi' and 'categories'
        addToast(response.data.message, {appearance: 'success',autoDismiss: true});
        //missing from login: email, emailNotify
        let ragione_sociale = response.data.ragione_sociale;
        let id_company = response.data.id_company;
        let token = response.data.token;
        let elencoClients = fake_shallowClientList;//fake_elencoClients;//response.data.elencoClients;
        let emailNotify = response.data.emailNotify;
        _getPlaces(ragione_sociale, token)
        .then(function (response) {
          //get places
          console.log(response.data)
          let sedi = response.data.sedi;
          _getClientTypes(ragione_sociale, token)
          .then(function (response) {
            //get categories
            let categories = response.data.categories;
            console.log(categories);
            props.LoginWithPlacesCategories(ragione_sociale, id_company, email, emailNotify, elencoClients, token, sedi, categories);
          })
          .catch(function (error) {
            addToast("Errore durante il login", {appearance: 'error',autoDismiss: true});
          })
        })
        .catch(function (error) {
          addToast("Errore durante il login", {appearance: 'error',autoDismiss: true});
        });
      }
    })
    .catch(function (error) {
      addToast("Errore durante il login", {appearance: 'error',autoDismiss: true});
    });
  }

  return (
    (props.nome_company===null) 
    ?
      <ToastProvider>
        <div class="container">
          <div class="row">
            <br />
            <div class="col-md-6 col-md-offset-3">
              <form>
                <div class="form-group">
                  <label for="LoginEmail1">Indirizzo email</label>
                  <input type="email" value={state.emailLogin} class="form-control" id="LoginEmail1" aria-describedby="emailHelp" placeholder="Email" onChange={handleEmailLogin}/>
                </div>
                <div class="form-group">
                  <label for="LoginPassword1">Password</label>
                  <input type="password" value={state.pswLogin} class="form-control" id="LoginPassword1" placeholder="Password" onChange={handlePswLogin} />
                </div>
              </form>
              <button class="btn btn-primary" onClick={() => LoginController(state.emailLogin, state.pswLogin)}>Accedi</button>
            </div>
          </div>
        </div>
      </ToastProvider>
    :
      <ToastProvider>
        <DashboardHome path={"/"+props.nome_company} title={props.nome_company} />
        {props.client_list.map((item) => <Dashboard path={"/company"+props.nome_company+"user"+item.id_client} title={item.nome_client} />)}
      </ToastProvider>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(LoginPage);
