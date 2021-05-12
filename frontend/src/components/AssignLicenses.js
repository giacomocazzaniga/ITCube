import React, { useState } from "react";
import { Col, Row } from "react-bootstrap";
import { connect } from "react-redux";
import { totalReset, updateClientLicenses, updateToken } from "../ActionCreator";
import { _assignLicenze } from "../callableRESTs";
import { getErrorToast, getLoadingToast, stopLoadingToast } from "../toastManager";
import { autenticazione_fallita, renewToken } from "../Tools";

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
 const mapDispatchToProps = dispatch => ({
     UpdateClientLicenses: (license,id_client) => {
        dispatch(updateClientLicenses(license,id_client))
     },
     TotalReset: () => {
        dispatch(totalReset());
      },
      UpdateToken: (token) => {
        dispatch(updateToken(token));
      }
 });

 /**
  * connect the redux state to the component
  * @param {*} state 
  */
 const mapStateToProps = state => {
     return {
        company_template: state.company_template,
        token: state.token,
        id_company: state.id_company
     }  
 };

 const isOdd = (num) => { return ((num % 2)==1) ? true : false }

const AssignLicenses = (props) => {
    
    const [state,setState] = useState(0);

    const _licenseChange = (e) => {
        props.company_template.licensesList.filter( licenza => !props.client.classe_licenza.includes(licenza.classe)).forEach( (license,i) => {
            if(i==e.target.value){
                setState({
                    "codice": license.codice,
                    "classe": license.classe
                });
            }
        })
    }

    const _assignLicense = () => {
        let continueUpdating=true;
        if(state != 0) {
            const loadingToast = getLoadingToast("Assegnando la licenza...");
            _assignLicenze(props.client.id_client,props.token,state.codice,props.id_company)
            .then( response => {

                if(autenticazione_fallita(response.data.messageCode)) {
                    stopLoadingToast(loadingToast);
                    getErrorToast("Sessione scaduta");
                    props.TotalReset();
                    continueUpdating = false;
                  }
                  if(continueUpdating != false) {
                    let token= props.token;
                    if(renewToken(props.token, response.data.token)){
                      props.UpdateToken(response.data.token);
                      token = response.data.token;
                    }

                    props.UpdateClientLicenses(state.classe,props.client.id_client)
                    stopLoadingToast(loadingToast);
                }
            })
            .catch(function (error) {
                stopLoadingToast(loadingToast);
                getErrorToast(String(error));
            })
        } else {
            getErrorToast(String("Selezionare una licenza"))
        } 
        
    }

    return (
        (isOdd(props.idx))
        ?
            <>
                <Col className="oddColor vertical-aligner col-md-3 col-xs-3">
                    <h4>{props.client.nome_client}</h4>
                </Col>
                <Col className="oddColor col-md-4 col-xs-4">
                    {props.client.classe_licenza.map( classe_licenza => props.options.map( option => (option.value == classe_licenza) ? <div>{option.label}</div> : <></>))}
                </Col>
                <Col className="oddColor vertical-aligner col-md-3 col-xs-3">
                    <select onChange={_licenseChange} >
                        <option value={0} selected hidden disabled>Seleziona una licenza</option>
                        {props.company_template.licensesList.filter( licenza => !props.client.classe_licenza.includes(licenza.classe)).map((license,i) => <option value={i}>{license.tipologia}</option>)}   
                    </select>
                </Col>
                <Col className="oddColor vertical-aligner col-md-2 col-xs-2">
                    <button onClick={_assignLicense} class="btn btn-primary" type="button" >Assegna</button>          
                </Col>
            </>
        :
            <>
                <Col className="evenColor vertical-aligner col-md-3 col-xs-3">
                    <h4>{props.client.nome_client}</h4>
                </Col>
                <Col className="evenColor col-md-4 col-xs-4">
                    {props.client.classe_licenza.map( classe_licenza => props.options.map( option => (option.value == classe_licenza) ? <div>{option.label}</div> : <></>))}
                </Col>
                <Col className="evenColor vertical-aligner col-md-3 col-xs-3">
                    <select onChange={_licenseChange} >
                        <option value={0} selected hidden disabled>Seleziona una licenza</option>
                        {props.company_template.licensesList.filter( licenza => !props.client.classe_licenza.includes(licenza.classe)).map((license,i) => <option value={i}>{license.tipologia}</option>)}   
                    </select>
                </Col>
                <Col className="evenColor vertical-aligner col-md-2 col-xs-2">
                    <button onClick={_assignLicense} class="btn btn-primary" type="button" >Assegna</button>          
                </Col>
            </>
    );
}

export default connect(
    mapStateToProps,
    mapDispatchToProps,
  )(AssignLicenses);