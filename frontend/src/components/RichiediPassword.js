import React from "react";
import { _richiediPassword } from "../callableRESTs";
import { getErrorToast, getLoadingToast, getSuccessToast, stopLoadingToast } from "../toastManager";

const RichiediPassword = () => {

    const [state, setState] = React.useState({
        email: ""
    })
    
    /**
     * handle email login and map it to local state emailLogin
     * @param {*} evt 
     */
    const handleEmailLogin = (evt) => {
    setState((previousState) => {
        return { ...previousState, email: evt.target.value };  
    });
    }

    const richiediPassword = () => {
        const loadingToast = getLoadingToast("Invio mail richiesta password...");
        _richiediPassword(state.email)
        .then( () => {
            stopLoadingToast(loadingToast);
            getSuccessToast("Mail inviata correttamente");
        })
        .catch( error => {
            stopLoadingToast(loadingToast);
            getErrorToast(String(error));
        })
    }

    return (
        <div className="container">
        <div className="row">
          <br />
          <div className="col-md-6 col-md-offset-3">
            <form>
              <div className="form-group">
                <label htmlFor="LoginEmail1">Indirizzo email</label>
                <input type="email" value={state.email} className="form-control" id="LoginEmail1" aria-describedby="emailHelp" placeholder="Email" onChange={handleEmailLogin}/>
              </div>
            </form>
            <button className="btn btn-primary" onClick={richiediPassword}>Richiedi password</button>
          </div>
        </div>
      </div>
    )
}

export default RichiediPassword;