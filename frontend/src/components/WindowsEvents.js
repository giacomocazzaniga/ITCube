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
      addToast("Errore durante il caricamento degli eventi.", {appearance: 'error',autoDismiss: true});
    });
  }

  const servicesListMaker = (services) => {
    let returnList = [<><Col className="popminwidth col-md-12 col-xs-12">Ultimo aggiornamento: {services[0].date_and_time}<hr/></Col></>];
    let status = ["", "Error", "Warning", "", "Information", "", "", "", "SuccessAudit", "", "", "", "", "", "", "", "FailureAudit"]
    //order by category
    console.log(services)
    services.sort(function (a, b) {
      return a.sottocategoria.localeCompare(b.sottocategoria);
    });
    console.log(services)
    //for each category print
    let categoriesHeaderWasPrinted = [false, false, false, false];
    let sottocategoria = "";
    let compare_sottocategoria = "";
    for(let j=0; j<4; j++){
      categoriesHeaderWasPrinted[j] = true
      switch(j){
        case 0:
          sottocategoria = "Application";
          compare_sottocategoria = "A";
          break;
        case 1:
          sottocategoria = "Security";
          compare_sottocategoria = "C";
          break;
        case 2:
          sottocategoria = "Hardware";
          compare_sottocategoria = "H";
          break;
        case 3:
          sottocategoria = "System";
          compare_sottocategoria = "S";
          break;
      }
      
      //returnList = getCard(returnList, service.sottocategoria, service.level, i+1, service.source, service.id_event, service.task_category, service.info, status, categoriesHeaderWasPrinted);
      returnList = [returnList, 
        <div class="winServices">
          <Card>
            <Accordion.Toggle as={Card.Header} eventKey={sottocategoria}>
              <div className="clickable"><h4>{sottocategoria}</h4></div>
            </Accordion.Toggle>
            <Accordion.Collapse eventKey={sottocategoria}>
              <Card.Body>
              <Col xs={2} md={2}><strong><h5>LEVEL</h5></strong></Col><Col xs={2} md={2}><strong><h5>DATA E ORA</h5></strong></Col><Col xs={2} md={2}><strong><h5>SOURCE</h5></strong></Col><Col xs={1} md={1}><strong><h5>ID</h5></strong></Col><Col xs={2} md={2}><strong><h5>TASK CATEGORY</h5></strong></Col><Col xs={3} md={3}><strong><h5>DESCRIZIONE</h5></strong></Col>
                {services.map((service, i) => getCategories(service.level, service.source, service.id_event, service.task_category, service.info, i, status, compare_sottocategoria, service.sottocategoria, service.date_and_time))}
              </Card.Body>
            </Accordion.Collapse>
          </Card>
        </div>
      ]
    }
    return returnList
  }

  const getCategories = (level, source, id_event, task_category, info, i, status, compare_sottocategoria, sottocategoria, date) => {
    if (info == "") info = " ";
    let oddcolor = isOdd(i);
    if(compare_sottocategoria == sottocategoria)
      return (oddcolor==true)
      ? 
        <>
          <Col className="oddColor col-md-2 col-xs-2"><p>{status[parseInt(level)]}</p></Col>
          <Col className="oddColor col-md-2 col-xs-2"><p>{date}</p></Col>
          <Col className="oddColor col-md-2 col-xs-2"><p>{source}</p></Col>
          <Col className="oddColor col-md-1 col-xs-1"><p>{id_event}</p></Col>
          <Col className="oddColor col-md-2 col-xs-2"><p>{task_category}</p></Col>
          <Col className="oddColor col-md-3 col-xs-3"><p>{info}</p></Col>
        </>
      :
        <>
          <Col className="evenColor col-md-2 col-xs-2"><p>{status[parseInt(level)]}</p></Col>
          <Col className="evenColor col-md-2 col-xs-2"><p>{date}</p></Col>
          <Col className="evenColor col-md-2 col-xs-2"><p>{source}</p></Col>
          <Col className="evenColor col-md-1 col-xs-1"><p>{id_event}</p></Col>
          <Col className="evenColor col-md-2 col-xs-2"><p>{task_category}</p></Col>
          <Col className="evenColor col-md-3 col-xs-3"><p>{info}</p></Col>
        </>
    else return <></>
  }

  return (
    <Box title="Lista degli eventi di Windows" type="primary" collapsable footer={<PopUp title="Gestione degli eventi di Windows" linkClass={"clickable"} childs={props.events_list} action={()=>getEventsList()}/>}>
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