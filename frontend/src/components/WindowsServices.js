import React from "react";
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { ToastProvider, useToasts } from 'react-toast-notifications';
import PopUp from "./PopUp";
import { servicesList } from "../ActionCreator";
import { url_lista_servizi, url_lista_serviziFake } from "../REST";
import axios from "axios";
import { Accordion, Alert, Card } from "react-bootstrap";
import { _getServiziAll, _getServiziMonitorati, _modificaMonitoraggioServizio } from "../callableRESTs";

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => {
  return{
    ServicesList: (services) => {
      dispatch(servicesList(services))
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
    token: state.token,
    services_list: state.services_list
  }
}

const WindowsServices = (props) => {

  const isOdd = (num) => { return ((num % 2)==1) ? true : false }

  const { addToast } = useToasts();

  const getServicesList = () => {
    _getServiziAll(props.token, props.id_client)
    .then(function (response) {
      console.log(response.data.servizi)
      let list = servicesListMaker(response.data.servizi);
      props.ServicesList(list)
    })
    .catch(function (error) {
      addToast("Errore durante il caricamento dei servizi", {appearance: 'error',autoDismiss: true});
    });
  }

  const servicesListMaker = (services) => {
    let returnList = [<><Col xs={12} md={12}>Ultimo aggiornamento: {services[0].date_and_time}<hr/></Col><Col xs={1} md={1}><strong><h5>MONITORA</h5></strong></Col><Col xs={4} md={4}><strong><h5>NOME SERVIZIO</h5></strong></Col><Col xs={2} md={2}><strong><h5>STATO</h5></strong></Col><Col xs={5} md={5}><strong><h5>DESCRIZIONE</h5></strong></Col></>];
    let status = ["", "Running", "Stop"]
    services.map((service, i) => {
      if(service.hasOwnProperty('monitora')){
        returnList = getCard(returnList, service.nome_servizio, service.stato, i+1, status, service.description, service.monitora);
      }else{
        returnList = getCard(returnList, service.nome_servizio, service.stato, i+1, status, service.description, null);
      }
    })
    return returnList
  }
  
  const toggleMonitora = (servizio, actualValue) => {
    alert(actualValue)
    let monitora
    if(actualValue==true) monitora = false
    else monitora = true
    _modificaMonitoraggioServizio(props.token, servizio, props.id_client, monitora)
    .then(function (response) {
      getServicesList()
    }).catch(function (error) {
      addToast("Errore durante la modifica del servizio", {appearance: 'error',autoDismiss: true});
    });
  }

  const getCard = (returnList, opname, stato, i, status, description, monitora) => {
    if (description == "") description = " "
    return (!isOdd(i))
    ? 
      monitora != null
      ?
        monitora == "true"
        ?
          returnList = [returnList, 
            <>
              <Col className="oddColor col-md-1 col-xs-1"><input type="checkbox" onChange={()=>toggleMonitora(opname, true)} id={"monitora"+opname} name={"monitora"+opname} value={"monitora"+opname} defaultChecked="checked"/></Col>
              <Col className="oddColor col-md-4 col-xs-4"><p>{opname}</p></Col>
              <Col className="oddColor col-md-2 col-xs-2"><p>{status[parseInt(stato)]}</p></Col>
              <Col className="oddColor col-md-5 col-xs-5"><p>{description}</p></Col>
            </>
          ]
        :
          returnList = [returnList, 
            <>
              <Col className="oddColor col-md-1 col-xs-1"><input type="checkbox" onChange={()=>toggleMonitora(opname, false)} id={"monitora"+opname} name={"monitora"+opname} value={"monitora"+opname}/></Col>
              <Col className="oddColor col-md-4 col-xs-4"><p>{opname}</p></Col>
              <Col className="oddColor col-md-2 col-xs-2"><p>{status[parseInt(stato)]}</p></Col>
              <Col className="oddColor col-md-5 col-xs-5"><p>{description}</p></Col>
            </>
          ]
      :
        returnList = [returnList, 
          <>
            <Col className="oddColor col-md-1 col-xs-1"><input type="checkbox" id={"monitora"+opname} name={"monitora"+opname} value={"monitora"+opname} defaultChecked="checked"/></Col>
            <Col className="oddColor col-md-4 col-xs-4"><p>{opname}</p></Col>
            <Col className="oddColor col-md-2 col-xs-2"><p>{status[parseInt(stato)]}</p></Col>
            <Col className="oddColor col-md-5 col-xs-5"><p>{description}</p></Col>
          </>
        ]
    : 
      monitora != null
      ?
        monitora == "true"
        ?
          returnList = [returnList, 
            <>
              <Col className="evenColor col-md-1 col-xs-1"><input type="checkbox" onChange={()=>toggleMonitora(opname, true)} id={"monitora"+opname} name={"monitora"+opname} value={"monitora"+opname} defaultChecked="checked"/></Col>
              <Col className="evenColor col-md-4 col-xs-4"><p>{opname}</p></Col>
              <Col className="evenColor col-md-2 col-xs-2"><p>{status[parseInt(stato)]}</p></Col>
              <Col className="evenColor col-md-5 col-xs-5"><p>{description}</p></Col>
            </>
          ]
        :
          returnList = [returnList, 
            <>
              <Col className="evenColor col-md-1 col-xs-1"><input type="checkbox" onChange={()=>toggleMonitora(opname, false)} id={"monitora"+opname} name={"monitora"+opname} value={"monitora"+opname}/></Col>
              <Col className="evenColor col-md-4 col-xs-4"><p>{opname}</p></Col>
              <Col className="evenColor col-md-2 col-xs-2"><p>{status[parseInt(stato)]}</p></Col>
              <Col className="evenColor col-md-5 col-xs-5"><p>{description}</p></Col>
            </>
          ]
      :
        returnList = [returnList, 
          <>
            <Col className="evenColor col-md-1 col-xs-1"><input type="checkbox" id={"monitora"+opname} name={"monitora"+opname} value={"monitora"+opname} defaultChecked="checked"/></Col>
            <Col className="evenColor col-md-4 col-xs-4"><p>{opname}</p></Col>
            <Col className="evenColor col-md-2 col-xs-2"><p>{status[parseInt(stato)]}</p></Col>
            <Col className="evenColor col-md-5 col-xs-5"><p>{description}</p></Col>
          </>
        ]
  }

  return (
    <Box title="Lista dei servizi di Windows" type="primary" collapsable footer={<PopUp title="Gestione dei servizi di Windows" linkClass={"clickable"} childs={props.services_list} action={()=>getServicesList()}/>}>
      <Col md={12} xs={12}>
        <h4><FontAwesomeIcon icon={["fas", "check-circle"]} /> Servizi attivi: {props.services[0]}</h4>
        <h4><FontAwesomeIcon icon={["fas", "play-circle"]} /> Servizi in esecuzione: {props.services[1]}</h4>
        <h4><FontAwesomeIcon icon={["fas", "times-circle"]} /> Servizi non in esecuzione: {props.services[2]}</h4>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(WindowsServices);