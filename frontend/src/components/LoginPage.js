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
    Login: (company_name, client_list) => {
      dispatch(login(company_name, client_list))
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
    company_id: state.company_id

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
      let company_name = response.data.company_id;
      let client_list = response.data.client_list;
      props.Login(company_name, client_list);
    })
    .catch(function (error) {
      addToast("Errore durante il login", {appearance: 'error',autoDismiss: true});
    });
  }

  return (
    (props.company_id===null) 
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
        {props.client_list.map((item) => <Dashboard path={"/company"+props.company_id+"user"+item.id} title={item.name} />)}
      </ToastProvider>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(LoginPage);
