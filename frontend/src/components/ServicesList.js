import React from "react";
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { ToastProvider, useToasts } from 'react-toast-notifications';
import PopUp from "./PopUp";
import { servicesList } from "../ActionCreator";
import { url_lista_servizi } from "../REST";
import axios from "axios";

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

const ServicesList = (props) => {

  const { addToast } = useToasts();

  const getServicesList = (selected, token) => {
    axios.post(url_lista_servizi, {
      nome_client: selected
    })
    .then(function (response) {
      let list = servicesListMaker(response.data.servizi);
      props.ServicesList(list)
    })
    .catch(function (error) {
      addToast("Errore durante il login", {appearance: 'error',autoDismiss: true});
    });
  }

  const servicesListMaker = (services) => {
    let returnList = [<p class="infoDisabled">* Servizio non selezionabile con la licenza in uso.</p>];
    let status = ["", "RUNNING", "PROBLEMI", "WARINNG"]
    services.map((service, i) => {
      (service.stato=="0")
      ?   (service.attivo=="true") 
          ? returnList=[returnList, <p class="disabled"><input type="checkbox" id={"service"+i} name={"service"+i} value={service.nome} checked disabled/> <span class="infoDisabled">*</span>{service.nome}</p>]
          : returnList=[returnList, <p class="disabled"><input type="checkbox" id={"service"+i} name={"service"+i} value={service.nome} disabled/> <span class="infoDisabled">*</span>{service.nome}</p>]
      :   (service.attivo=="true") 
          ? returnList=[returnList, <p><input type="checkbox" id={"service"+i} name={"service"+i} value={service.nome} checked/> <label for={"service"+i}> {service.nome}</label> {status[service.stato]}</p>]
          : returnList=[returnList, <p><input type="checkbox" id={"service"+i} name={"service"+i} value={service.nome} /> <label for={"service"+i}> {service.nome}</label></p>]
    })
    return returnList;
  }
  
  return (
    <Box title="Lista dei servizi" type="primary" collapsable footer={<PopUp title="Gestione dei servizi" childs={props.services_list} action={()=>getServicesList(props.selected, props.token)}/>}>
      <Col md={12} xs={12}>
        <h4><FontAwesomeIcon icon={["fas", "check-circle"]} /> Servizi attivi: {props.services.attivi}</h4>
        <h4><FontAwesomeIcon icon={["fas", "play-circle"]} /> Servizi in esecuzione: {props.services.esecuzione}</h4>
        <h4><FontAwesomeIcon icon={["fas", "times-circle"]} /> Servizi con problemi: {props.services.problemi}</h4>
        <h4><FontAwesomeIcon icon={["fas", "exclamation-circle"]} /> Servizi con warnings: {props.services.warnings}</h4>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(ServicesList);