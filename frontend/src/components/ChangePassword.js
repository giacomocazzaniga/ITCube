import React, { useEffect, useState } from "react"
import { _cambioPassword, _isTokenValid } from "../callableRESTs";
import { getErrorToast, getLoadingToast, getSuccessToast, stopLoadingToast } from "../toastManager";

const ChangePassword = () => {
    
    let parameters = window.location.search;
    let token = parameters.substring(parameters.indexOf("token=")+"token=".length, parameters.indexOf("&id_company"));
    let id_company = parameters.substring(parameters.indexOf("id_company=")+"id_company=".length);

    const [state,setState] = useState({
        nuovaPassword: "",
        confermaPassword: ""
    });

    const [isTokenValid,setIsTokenValid] = useState(false);

    const handleNuovaPassword = (e) => {
        setState({ ...state, nuovaPassword: e.target.value});
    }

    const handleConfermaPassword = (e) => {
        setState({ ...state, confermaPassword: e.target.value});
    }

    useEffect(() => {
        _isTokenValid(token)
        .then( response => {
            if(response.data.messageCode == 0) {
                setIsTokenValid(true);
            }
        })
    },[])

    const cambiaPassword = () => {
        const loadingToast = getLoadingToast("Modifica password...");

        if(state.nuovaPassword == state.confermaPassword && state.nuovaPassword!=""){
            _cambioPassword(token,id_company,state.nuovaPassword)
            .then( response => {
                stopLoadingToast(loadingToast);
                getSuccessToast("Password cambiata correttamente");
                document.getElementsByClassName("sidebar-menu")[0].getElementsByTagName("li")[0].getElementsByTagName("a")[0].click();
            })
            .catch( error => {
                stopLoadingToast(loadingToast);
                getErrorToast(String(error));
            })
        } else {
            getErrorToast("Le password devono essere uguali")
        }
        stopLoadingToast(loadingToast);
    }

    return (
        (isTokenValid)
        ?
            <div className="container">
                <div className="row">
                <br />
                <div className="col-md-6 col-md-offset-3">
                    <form>
                    <div className="form-group">
                        <label htmlFor="LoginEmail1">Nuova password</label>
                        <input type="password" className="form-control" id="LoginEmail1" aria-describedby="emailHelp" placeholder="Email" onChange={handleNuovaPassword}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="LoginEmail1">Conferma password</label>
                        <input type="password" className="form-control" id="LoginEmail1" aria-describedby="emailHelp" placeholder="Email" onChange={handleConfermaPassword}/>
                    </div>
                    </form>
                    <button className="btn btn-primary" onClick={cambiaPassword}>Cambia password</button>
                </div>
                </div>
            </div>
        :
            <h1 style={{"text-align": "center","margin-top": "75px"}}>Token già utilizzato o non valido</h1>
    )
}

export default ChangePassword;