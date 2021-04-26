import React from 'react';
import { connect } from 'react-redux';
import { signUp } from '../ActionCreator';
import { _MSGCODE } from '../Constants';
import { _performSignUp } from '../callableRESTs';
import { getErrorToast, getLoadingToast, getSuccessToast, stopLoadingToast } from '../toastManager';

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps =  dispatch => ({
    SignUp: (message, messageCode) => {
      dispatch(signUp(message, messageCode))
    }
  }
);


/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({
    nome_company: state.nome_company,
    message: state.message,
    messageCode: state.messageCode,
  }
);

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

  const SignUpController = (email, password, email_alert, ragione_sociale) => {
    const loadingToast = getLoadingToast("Registrazione in corso...");
    _performSignUp(email, password, email_alert, ragione_sociale)
    .then(function (response) {
      if(response.data.messageCode==_MSGCODE.ERR){
        //already registered
        stopLoadingToast(loadingToast);
        getErrorToast(response.data.message);
      }else{
        //signup done
        stopLoadingToast(loadingToast);
        getSuccessToast(response.data.message);
      }
      //props.SignUp(response.data.message, response.data.messageCode);
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  return (
        <div className="container">
          <div className="row">
            <br />
            <div className="col-md-6 col-md-offset-3">
              <form>
                <div className="form-group">
                  <label htmlFor="exampleInputEmail1">Indirizzo email</label>
                  <input type="email" value={state.emailSignUp} onChange={handleEmailSignUp} className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email" />
                </div>
                <div className="form-group">
                  <label htmlFor="exampleInputPassword1">Password</label>
                  <input type="password" value={state.pswSignUp} onChange={handlePswSignUp} className="form-control" id="exampleInputPassword1" placeholder="Password" />
                </div>
                <div className="form-group">
                  <label htmlFor="exampleInputEmail2">Indirizzo email per le comunicazioni</label>
                  <input type="email" value={state.emailAlertSignUp} onChange={handleEmailAlertSignUp} className="form-control" id="exampleInputEmail2" aria-describedby="emailHelp" placeholder="Email" />
                </div>
                <div className="form-group">
                  <label htmlFor="exampleInputRagioneSociale">Ragione Sociale</label>
                  <input value={state.ragioneSocialeSignUp} onChange={handleRagioneSocialeSignUp} className="form-control" id="exampleInputRagioneSociale" aria-describedby="emailHelp" placeholder="Ragione Sociale" />
                </div>
                <button type="button" onClick={() => SignUpController(state.emailSignUp, state.pswSignUp, state.emailAlertSignUp, state.ragioneSocialeSignUp)} className="btn btn-primary">Registrati</button>
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
