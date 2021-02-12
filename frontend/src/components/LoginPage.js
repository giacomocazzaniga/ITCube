import React from 'react';
import { connect } from 'react-redux';
import { ToastProvider, useToasts } from 'react-toast-notifications';
import { login } from '../ActionCreator';
import { url_login } from '../REST';
import Dashboard from './Dashboard';

const axios = require('axios');

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps =  dispatch => {
  return{
    Login: (nome_company, client_list, token) => {
      dispatch(login(nome_company, client_list, token))
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
    setState({ emailLogin: evt.target.value });
  }

  /**
   * handle password login and map it to local state pswLogin
   * @param {*} evt 
   */
  const handlePswLogin = (evt) => {
    setState({ pswLogin: evt.target.value });
  }

  const { addToast } = useToasts();

  const LoginController = (email, psw) => {
    axios.post(url_login, {
      email: email,
      password: psw
    })
    .then(function (response) {
      //i campi json di risposta vanno gestiti qui
      props.Login(response.data.nome_company, response.data.client, response.data.token);
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
        {props.client_list.map((item) => <Dashboard path={"/company"+props.nome_company+"user"+item.id_client} title={item.nome_client} />)}
      </ToastProvider>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(LoginPage);
