import axios from 'axios';
import React from 'react';
import { connect } from 'react-redux';
import { ToastProvider, useToasts } from 'react-toast-notifications';
import { signUp } from '../ActionCreator';
import DashboardWrap from './DashboardWrap';
import { url_signup } from '../REST';

var md5 = require('md5');

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps =  dispatch => {
  return{
    SignUp: (message, messageCode) => {
      dispatch(signUp(message, messageCode))
    }
  }
}


/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => {
  return {
    nome_company: state.nome_company,
    message: state.message,
    messageCode: state.messageCode,
  }
}

const SignUpPage = (props) => {
  
  const [state, setState] = React.useState({
    emailSignUp: "",
    pswSignUp: "",
    emailAlertSignUp: "",
    ragioneSocialeSignUp: ""
  })

  /**
   * handle email login and map it to local state emailSignUp
   * @param {*} evt 
   */
  const handleEmailSignUp = (evt) => {
    setState((previousState) => {
      return { ...previousState, emailSignUp: evt.target.value };
    });
  }

  /**
   * handle password login and map it to local state pswSignUp
   * @param {*} evt 
   */
  const handlePswSignUp = (evt) => {
    setState((previousState) => {
      return { ...previousState, pswSignUp: evt.target.value };
    });
  }

    /**
   * handle email login and map it to local state emailAlertSignUp
   * @param {*} evt 
   */
  const handleEmailAlertSignUp = (evt) => {
    setState((previousState) => {
      return { ...previousState, emailAlertSignUp: evt.target.value };
    });
  }

  /**
   * handle password login and map it to local state ragioneSocialeSignUp
   * @param {*} evt 
   */
  const handleRagioneSocialeSignUp = (evt) => {
    setState((previousState) => {
      return { ...previousState, ragioneSocialeSignUp: evt.target.value };
    });
  }

  const { addToast } = useToasts();

  const SignUpController = (email, password, email_alert, ragione_sociale) => {
    let encryptedPsw = md5(password);
    axios.post(url_signup, {
      email: email,
      password: encryptedPsw,
      email_alert: email_alert,
      ragione_sociale: ragione_sociale
    })
    .then(function (response) {
      //i campi json di risposta vanno gestiti qui
      if(response.data.messageCode=="1"){
        //already registered
        addToast(response.data.message, {appearance: 'error',autoDismiss: true});
      }else{
        //done
        addToast(response.data.message, {appearance: 'success',autoDismiss: true});
      }
      //props.SignUp(response.data.message, response.data.messageCode);
    })
    .catch(function (error) {
      addToast("Errore durante la registrazione.", {appearance: 'error',autoDismiss: true});
    });
  }

  return (
        <div class="container">
          <div class="row">
            <br />
            <div class="col-md-6 col-md-offset-3">
              <form>
                <div class="form-group">
                  <label for="exampleInputEmail1">Indirizzo email</label>
                  <input type="email" value={state.emailSignUp} onChange={handleEmailSignUp} class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email" />
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <input type="password" value={state.pswSignUp} onChange={handlePswSignUp} class="form-control" id="exampleInputPassword1" placeholder="Password" />
                </div>
                <div class="form-group">
                  <label for="exampleInputEmail2">Indirizzo email per le comunicazioni</label>
                  <input type="email" value={state.emailAlertSignUp} onChange={handleEmailAlertSignUp} class="form-control" id="exampleInputEmail2" aria-describedby="emailHelp" placeholder="Email" />
                </div>
                <div class="form-group">
                  <label for="exampleInputRagioneSociale">Ragione Sociale</label>
                  <input value={state.ragioneSocialeSignUp} onChange={handleRagioneSocialeSignUp} class="form-control" id="exampleInputRagioneSociale" aria-describedby="emailHelp" placeholder="Ragione Sociale" />
                </div>
                <button type="button" onClick={() => SignUpController(state.emailSignUp, state.pswSignUp, state.emailAlertSignUp, state.ragioneSocialeSignUp)} class="btn btn-primary">Registrati</button>
              </form>
            </div>
          </div>
        </div>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(SignUpPage);
