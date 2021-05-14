import React, { useState} from "react"
import { Col } from "adminlte-2-react";
import { connect } from "react-redux";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { getErrorToast, getLoadingToast, getSuccessToast, stopLoadingToast } from "../toastManager";
import { _updateMonitoraAllAlerts, _updateMonitoraAllServices } from "../callableRESTs";
import { Backend2FrontendAlertConverter } from "../Tools";

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
    lista_nomi_sedi: state.lista_nomi_sedi,
    lista_id_sedi: state.lista_id_sedi
});

const ClientHandlerRowAlert = (props) => {

    const licenze = [
        {
            tipo:1,
            label:"Sistema operativo"
        },
        {
            tipo : "2",
            label : "Backup"
        },
        {
            tipo : "3",
            label : "Antivirus"
        },
        {
            tipo : "4",
            label : "Rete"
        },
        {
            tipo : "5",
            label : "Vulnerabilita\'"
        }
    ]
    
    const tipologie = [
        {
            id_tipologia: 1,
            tipologia:"Client"
        },
        {
            id_tipologia: 2,
            tipologia:"Server"
        }
    ];

    const [selectState, setSelectState] = useState({
        tipologia: null,
        licenza: null,
        sede: null
    })

    const isOdd = (num) => { return ((num % 2)==1) ? true : false }

    const tipologiaChange = (e) => {
        setSelectState( {
            ...selectState,
            tipologia: e.target.value
        })
    }

    const licenzaChange = (e) => {
        setSelectState( {
            ...selectState,
            licenza: e.target.value
        })
    }
    const sedeChange = (e) => {
        setSelectState( {
            ...selectState,
            sede: e.target.value
        })
    }

    const updateMonitora = (monitora) => {
        // console.log(selectState)
        const loadingToast = getLoadingToast("Modifica monitoraggio servizi...");
        _updateMonitoraAllAlerts(props.id_company,props.token,selectState.tipologia,selectState.licenza,selectState.sede,props.tipologia_alert,monitora)
        .then( response => {
            stopLoadingToast(loadingToast);
            getSuccessToast(response.data.message);
        })
        .catch( error => {
            stopLoadingToast(loadingToast);
            getErrorToast(String(error));
        })
    }


    return(
        
        (isOdd(props.index))
        ?
            <>
                <Col className="oddColor vertical-aligner col-md-2 col-xs-2"><h5>{Backend2FrontendAlertConverter(props.tipologia_alert)}</h5></Col>
                <Col className="oddColor noHorizontalPadding vertical-aligner col-md-2 col-xs-2">
                    <select onChange={tipologiaChange}>
                        <option selected hidden disabled>Seleziona tipologia</option>
                        {tipologie.map( tipologia => <option value={tipologia.id_tipologia}>{tipologia.tipologia}</option>)}
                    </select>
                </Col>
                <Col className="oddColor vertical-aligner col-md-2 col-xs-2">
                    <select onChange={licenzaChange}>
                        <option selected hidden disabled>Seleziona licenza</option>
                        {licenze.map( licenza => <option value={licenza.tipo}>{licenza.label}</option>)}
                    </select>
                </Col>
                <Col className="oddColor noHorizontalPadding vertical-aligner col-md-2 col-xs-2">
                    <select onChange={sedeChange}>
                        <option selected hidden disabled>Seleziona sede</option>
                        {props.lista_nomi_sedi.map( (nome_sede,i) => <option value={props.lista_id_sedi[i]}>{nome_sede}</option>)}
                    </select>
                </Col>
                <Col className="oddColor vertical-aligner col-md-2 col-xs-2">
                    <button className="btn btn-primary" onClick={()=> updateMonitora(true)}>Aggiungi a tutti</button>    
                </Col>
                <Col className="oddColor vertical-aligner col-md-2 col-xs-2">
                    <button className="btn btn-primary" onClick={()=> updateMonitora(false)}>Rimuovi a tutti</button>
                </Col>
            </>
        :
            <>
                <Col className="evenColor vertical-aligner col-md-2 col-xs-2"><h5>{Backend2FrontendAlertConverter(props.tipologia_alert)}</h5></Col>
                <Col className="evenColor noHorizontalPadding vertical-aligner col-md-2 col-xs-2">
                    <select onChange={tipologiaChange}>
                        <option selected hidden disabled>Seleziona tipologia</option>
                        {tipologie.map( tipologia => <option value={tipologia.id_tipologia}>{tipologia.tipologia}</option>)}
                    </select>
                </Col>
                <Col className="evenColor vertical-aligner col-md-2 col-xs-2">
                    <select onChange={licenzaChange}>
                        <option selected hidden disabled>Seleziona licenza</option>
                        {licenze.map( licenza => <option value={licenza.tipo}>{licenza.label}</option>)}
                    </select>
                </Col>
                <Col className="evenColor noHorizontalPadding vertical-aligner col-md-2 col-xs-2">
                    <select onChange={sedeChange}>
                        <option selected hidden disabled>Seleziona sede</option>
                        {props.lista_nomi_sedi.map( (nome_sede,i) => <option value={props.lista_id_sedi[i]}>{nome_sede}</option>)}
                    </select>
                </Col>
                <Col className="evenColor vertical-aligner col-md-2 col-xs-2">
                    <button className="btn btn-primary" onClick={()=> updateMonitora(true)}>Aggiungi a tutti</button>    
                </Col>
                <Col className="evenColor vertical-aligner col-md-2 col-xs-2">
                    <button className="btn btn-primary" onClick={()=> updateMonitora(false)}>Rimuovi a tutti</button>
                </Col>
            </>
    )
}

export default connect(
    mapStateToProps,
    mapDispatchToProps,
  )(ClientHandlerRowAlert);