import React from "react";
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { ToastProvider, useToasts } from 'react-toast-notifications';
import PopUp from "./PopUp";
import { eventsList, servicesList } from "../ActionCreator";
import { url_lista_servizi, url_lista_serviziFake } from "../REST";
import axios from "axios";
import { Accordion, Alert, Card } from "react-bootstrap";
import { _getEventi, _getServiziAll, _getServiziMonitorati, _modificaMonitoraggioServizio } from "../callableRESTs";

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => {
  return{
    EventsList: (events) => {
      dispatch(eventsList(events))
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
    events_list: state.events_list
  }
}

const WindowsEvents = (props) => {

  const isOdd = (num) => { return ((num % 2)==1) ? true : false }

  const { addToast } = useToasts();

  const getEventsList = () => {
    _getEventi(props.token, props.id_client)
    .then(function (response) {
      console.log(response.data.eventi)
      let list = servicesListMaker(response.data.eventi);
      props.EventsList(list)
    })
    .catch(function (error) {
      addToast("Errore durante il caricamento dei servizi", {appearance: 'error',autoDismiss: true});
    });
  }

  const servicesListMaker = (services) => {
    let returnList = [<><Col xs={12} md={12}>Ultimo aggiornamento: {services[0].date_and_time}<hr/></Col><Col xs={2} md={2}><strong><h5>LEVEL</h5></strong></Col><Col xs={3} md={3}><strong><h5>SOURCE</h5></strong></Col><Col xs={1} md={1}><strong><h5>ID</h5></strong></Col><Col xs={2} md={2}><strong><h5>TASK CATEGORY</h5></strong></Col><Col xs={4} md={4}><strong><h5>DESCRIZIONE</h5></strong></Col></>];
    let status = ["", "Error", "Warning", "", "Information", "", "", "", "SuccessAudit", "", "", "", "", "", "", "", "FailureAudit"]
    services.map((service, i) => {
      returnList = getCard(returnList, service.sottocategoria, service.level, i+1, service.source, service.id_event, service.task_category, service.info, status);
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
      getEventsList()
    }).catch(function (error) {
      addToast("Errore durante la modifica del servizio", {appearance: 'error',autoDismiss: true});
    });
  }

  const getCard = (returnList, sottocategoria, level, i, source, id_event, task_category, info, status) => {
    if (info == "") info = " "
    return (!isOdd(i))
    ? 
      returnList = [returnList, 
        <>
          <Col className="oddColor col-md-2 col-xs-2"><p>{status[parseInt(level)]}</p></Col>
          <Col className="oddColor col-md-3 col-xs-3"><p>{source}</p></Col>
          <Col className="oddColor col-md-1 col-xs-1"><p>{id_event}</p></Col>
          <Col className="oddColor col-md-2 col-xs-2"><p>{task_category}</p></Col>
          <Col className="oddColor col-md-4 col-xs-4"><p>{info}</p></Col>
        </>
      ]
    :
      returnList = [returnList, 
        <>
          <Col className="evenColor col-md-2 col-xs-2"><p>{status[parseInt(level)]}</p></Col>
          <Col className="evenColor col-md-3 col-xs-3"><p>{source}</p></Col>
          <Col className="evenColor col-md-1 col-xs-1"><p>{id_event}</p></Col>
          <Col className="evenColor col-md-2 col-xs-2"><p>{task_category}</p></Col>
          <Col className="evenColor col-md-4 col-xs-4"><p>{info}</p></Col>
        </>
      ]
  }

  return (
    <Box title="Lista dei servizi di Windows" type="primary" collapsable footer={<PopUp title="Gestione dei servizi di Windows" linkClass={"clickable"} childs={props.events_list} action={()=>getEventsList()}/>}>
      <Col md={12} xs={12}>
        <h4><FontAwesomeIcon icon={["fas", "times-circle"]} /> Problemi rilevati oggi: {props.events[0]}</h4>
        <h4><FontAwesomeIcon icon={["fas", "exclamation-circle"]} /> Warnings rilevati oggi: {props.events[1]}</h4>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(WindowsEvents);