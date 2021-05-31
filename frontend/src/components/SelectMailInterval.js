import React, { useEffect, useState } from "react";
import { Col } from "react-bootstrap";
import { connect } from "react-redux";
import { _changeMailInterval, _getMailInterval } from "../callableRESTs";
import { getErrorToast, getLoadingToast, getSuccessToast, stopLoadingToast } from "../toastManager";

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
    token: state.token,
    id_company: state.id_company,
    lista_nomi_sedi: state.lista_nomi_sedi
});

const SelectMailInterval = (props) => {

    const [state,setState] = useState();

    useEffect(() => {
        const loadingToast = getLoadingToast("Caricamento...");
        _getMailInterval(props.token,props.id_company)
        .then( response => {
            setState(response.data.mailInterval);
            stopLoadingToast(loadingToast);
        })
        .catch( error => {
            stopLoadingToast(loadingToast);
            getErrorToast(String(error));
        })
    },[])

    const changeMailInterval = (e) => {
        const loadingToast = getLoadingToast("Modifica intervallo mail...");

        let stopInterval = false;
        if(e.target.value == 0){
            stopInterval = true;
        }
        if(e.target.value != -1) {
            _changeMailInterval(props.token,e.target.value,props.id_company,stopInterval)
            .then( response => {
                getSuccessToast("Intervallo mail cambiato correttamente");
                stopLoadingToast(loadingToast);
            })
            .catch( error => {
                stopLoadingToast(loadingToast);
                getErrorToast(String(error));
            })
        } else {
            stopLoadingToast(loadingToast);
        }
        
    }

    //TODO: AGGIUNGERE COLONNE INTERVALLO_MAIL E LAST_MAIL_DATE_AND_TIME A DB 

    return (    
        <Col xs={6} md={6}>
            <form onChange={changeMailInterval}>
                <div>
                    <input type="radio" value={0} id="noMail" name="interval" checked={state==0} onChange={(e) => {setState(e.target.value)}}/>
                    <label for="noMail">Non desidero ricevere mail</label>
                </div>
                <div>
                    <input type="radio" value={60000*60*24} id="1gg" name="interval" checked={state==60000*60*24} onChange={(e) => {setState(e.target.value)}}/>
                    <label for="1gg">1 giorno</label>
                </div>
                <div>
                    <input type="radio" value={60000*60*24*3} id="3gg" name="interval" checked={state==60000*60*24*3} onChange={(e) => {setState(e.target.value)}}/>
                    <label for="3gg">3 giorni</label>
                </div>
                <div>
                    <input type="radio" value={60000*60*24*7} id="1sett" name="interval" checked={state==60000*60*24*7} onChange={(e) => {setState(e.target.value)}}/>
                    <label for="1sett">1 settimana</label>
                </div>
            </form>
        </Col>
    )
}


export default connect(
    mapStateToProps,
    mapDispatchToProps,
  )(SelectMailInterval);