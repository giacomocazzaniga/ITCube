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

const WindowsEvents = (props) => {

  const isOdd = (num) => { return ((num % 2)==1) ? true : false }

  const { addToast } = useToasts();

  const getServicesList = (selected, token) => {
    axios.post(url_lista_serviziFake, {
      nome_client: selected
    })
    .then(function (response) {
      let list = servicesListMaker(response.data.servizi);
      props.ServicesList(list)
    })
    .catch(function (error) {
      addToast("Errore durante il caricamento delle operazioni", {appearance: 'error',autoDismiss: true});
    });
  }

  const servicesListMaker = (services) => {
    let returnList = [<><Col xs={8} md={6}><strong><h4>NOME SERVIZIO</h4></strong></Col><Col xs={4} md={6}><strong><h4>STATO</h4></strong></Col></>];
    let status = ["", "RUNNING", "PROBLEMI", "WARINNG"]
    services.map((service, i) => {
      switch(service.stato){
        case "0":
          returnList = getCard(returnList, service.nome, false, i+1);
          break;
        default:
          returnList = getCard(returnList, service.nome, true, i+1);
      }
    })
    return returnList;
  }
  
  const getCard = (returnList, opname, enabled, i) => {

    return (isOdd(i))
    ? (enabled) 
      ? returnList = [returnList, 
          <><Col xs={8} md={6} className="oddColor col-md-6 col-xs-8">{opname}</Col><Col className="oddColor col-md-6 col-xs-4" xs={4} md={6}>ATTIVO</Col></>
        ]
      : returnList = [returnList, 
        <><Col xs={8} md={6} className="oddColor col-md-6 col-xs-8">{opname}</Col><Col className="oddColor col-md-6 col-xs-4" xs={4} md={6}>DISATTIVO</Col></>
      ]
    : (enabled) 
      ? returnList = [returnList, 
        <><Col xs={8} md={6} className="evenColor col-md-6 col-xs-8">{opname}</Col><Col className="evenColor col-md-6 col-xs-4" xs={4} md={6}>ATTIVO</Col></>
      ]
      : returnList = [returnList, 
        <><Col xs={8} md={6} className="evenColor col-md-6 col-xs-8">{opname}</Col><Col className="evenColor col-md-6 col-xs-4" xs={4} md={6}>DISATTIVO</Col></>
      ]
  }

  return (
    <Box title="Lista degli eventi di Windows" type="primary" collapsable footer={<PopUp title="Gestione degli eventi di Windows" linkClass={"clickable"} childs={props.services_list} action={()=>getServicesList(props.selected, props.token)}/>}>
      <Col md={12} xs={12}>
        <h4><FontAwesomeIcon icon={["fas", "times-circle"]} /> Problemi rilevati oggi: {props.services[0]}</h4>
        <h4><FontAwesomeIcon icon={["fas", "exclamation-circle"]} /> Warnings rilevati oggi: {props.services[1]}</h4>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(WindowsEvents);