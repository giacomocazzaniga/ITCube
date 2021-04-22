import React from "react";
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import PopUp from "./PopUp";
import { servicesList, serviziOverview } from "../ActionCreator";
import { defaultUpperBound } from "../Constants";
import { _getServiziAll, _getServiziMonitorati, _getServiziOverview, _modificaMonitoraggioServizio } from "../callableRESTs";
import { getErrorToast, getLoadingToast, getSuccessToast, stopLoadingToast } from "../toastManager";
import ReactPaginate from "react-paginate";
import { Backend2FrontendDateConverter } from "../Tools";

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({
    ServicesList: (services) => {
      dispatch(servicesList(services))
    },
    SetOverviewServizi: (n_totali, n_running, n_stop, n_monitorati) => {
      dispatch(serviziOverview(n_totali, n_running, n_stop, n_monitorati))
    }
  }
);

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({
    client_list: state.client_list,
    token: state.token,
    services_list: state.services_list,
  }
);

const WindowsServices = (props) => {

  const isOdd = (num) => { return ((num % 2)==1) ? true : false }

  const merge_object_arrays = (arr1, arr2, match) => {
    arr1.map((elem1) => arr2.map((elem2) => {
      if(elem1.nome_servizio==elem2.nome_servizio){
        elem1.monitora = elem2.monitora
      }
    }))
    return arr1;
  }

  const getServicesList = () => {
    const loadingToast = getLoadingToast("Caricamento...");
    _getServiziAll(props.token, props.id_client)
    .then(function (response) {
      console.log(response.data.confWindowsServices)
      let tmp_list = response.data.confWindowsServices
      _getServiziMonitorati(props.token, props.id_client)
      .then(function (response) {
        stopLoadingToast(loadingToast);
        console.log(response.data.monitoraggi)
        let tmp_list2 = merge_object_arrays(tmp_list, response.data.monitoraggi, 'nome_servizio')
        let list = servicesListMaker(tmp_list2);
        props.ServicesList(list)
      })
      .catch(function (error) {
        stopLoadingToast(loadingToast);
        getErrorToast(String(error));
      });
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  const servicesListMaker = (services) => {
    let returnList = [<><Col xs={12} md={12}>Ultimo aggiornamento: {Backend2FrontendDateConverter(services[0].date_and_time)}<hr/></Col><Col xs={1} md={1}><strong><h5>MONITORA</h5></strong></Col><Col xs={5} md={5}><strong><h5>NOME SERVIZIO</h5></strong></Col><Col xs={2} md={2}><strong><h5>STATO</h5></strong></Col><Col xs={2} md={2}><strong><h5>TIPO DI AVVIO</h5></strong></Col><Col xs={2} md={2}><strong><h5>TIPO DI SERVIZIO</h5></strong></Col></>];
    let ServiceControllerStatus = ["", "Stop", "Start (pending)", "Stop (pending)", "Running", "Continue (pending)", "In pausa (pending)", "In pausa"]
    let ServiceStartMode = ["Boot", "System", "Automatico", "Manuale", "Disabilitato"] 
    let ServiceType = []
    ServiceType[1] = "KernelDriver";
    ServiceType[2] = "FileSystemDriver";
    ServiceType[4] = "Adapter";
    ServiceType[8] = "RecognizerDriver";
    ServiceType[16] = "Win32OwnProcess";
    ServiceType[32] = "Win32ShareProcess";
    ServiceType[256] = "InteractiveProcess";
    services.map((service, i) => {
      if(service.hasOwnProperty('monitora')){
        returnList = getCard(returnList, service.nome_servizio, service.stato, i+1, ServiceControllerStatus, ServiceStartMode, ServiceType, service.start_type, service.service_type, service.monitora);
      }else{
        returnList = getCard(returnList, service.nome_servizio, service.stato, i+1, ServiceControllerStatus, ServiceStartMode, ServiceType, service.start_type, service.service_type, null);
      }
    })
    let buttons = <Col className="col-xs-12 col-md-12"><br/>
        <div class="btn-group" role="group" aria-label="Basic example">
        <button type="button" class="btn btn-secondary">← Precedente</button>
        <button type="button" class="btn btn-secondary">{Math.ceil(props.services[3]/defaultUpperBound)}</button>
        <button type="button" class="btn btn-secondary">Successivo →</button>
      </div>
    </Col>
    //returnList = [returnList, buttons]
    return returnList
  }
  
  const toggleMonitora = (servizio, actualValue) => {
    //alert(actualValue)
    let monitora
    if(actualValue==true || actualValue=="true") monitora = "false"
    else monitora = "true"
    const loadingToast = getLoadingToast("Modifica monitoraggio servizi...");
    _modificaMonitoraggioServizio(props.token, servizio, props.id_client, monitora)
    .then(function (response) {
      getServicesList()
      _getServiziOverview(props.token, props.id_client)
      .then(function (response) {
        stopLoadingToast(loadingToast);
        props.SetOverviewServizi(response.data.n_totali, response.data.n_running, response.data.n_stopped, response.data.n_monitorati)
      })
      .catch(function (error) {
        stopLoadingToast(loadingToast);
        getErrorToast(String(error));
      });
    }).catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  const getCard = (returnList, opname, stato, i, ServiceControllerStatus, ServiceStartMode, ServiceType, start_type, service_type, monitora) => {
    return (!isOdd(i))
    ? 
        monitora == true
        ?
          returnList = [returnList, 
            <>
              <Col className="oddColor col-md-1 col-xs-1"><input type="checkbox" key={Math.random()+opname} onChange={()=>toggleMonitora(opname, true)} id={"monitora"+opname} name={"monitora"+opname} value={"monitora"+opname} defaultChecked="checked"/></Col>
              <Col className="oddColor col-md-5 col-xs-5"><p>{opname}</p></Col>
              <Col className="oddColor col-md-2 col-xs-2"><p>{ServiceControllerStatus[parseInt(stato)]}</p></Col>
              <Col className="oddColor col-md-2 col-xs-2"><p>{ServiceStartMode[parseInt(start_type)]}</p></Col>
              <Col className="oddColor col-md-2 col-xs-2"><p>{ServiceType[parseInt(service_type)]}</p></Col>
            </>
          ]
        :
          returnList = [returnList, 
            <>
              <Col className="oddColor col-md-1 col-xs-1"><input type="checkbox" key={Math.random()+opname} onChange={()=>toggleMonitora(opname, false)} id={"monitora"+opname} name={"monitora"+opname} value={"monitora"+opname} autocomplete="off"/></Col>
              <Col className="oddColor col-md-5 col-xs-5"><p>{opname}</p></Col>
              <Col className="oddColor col-md-2 col-xs-2"><p>{ServiceControllerStatus[parseInt(stato)]}</p></Col>
              <Col className="oddColor col-md-2 col-xs-2"><p>{ServiceStartMode[parseInt(start_type)]}</p></Col>
              <Col className="oddColor col-md-2 col-xs-2"><p>{ServiceType[parseInt(service_type)]}</p></Col>
            </>
          ]
    : 
        monitora == true
        ?
          returnList = [returnList, 
            <>
              <Col className="evenColor col-md-1 col-xs-1"><input type="checkbox" key={Math.random()+opname} onChange={()=>toggleMonitora(opname, true)} id={"monitora"+opname} name={"monitora"+opname} value={"monitora"+opname} defaultChecked="checked"/></Col>
              <Col className="evenColor col-md-5 col-xs-5"><p>{opname}</p></Col>
              <Col className="evenColor col-md-2 col-xs-2"><p>{ServiceControllerStatus[parseInt(stato)]}</p></Col>
              <Col className="evenColor col-md-2 col-xs-2"><p>{ServiceStartMode[parseInt(start_type)]}</p></Col>
              <Col className="evenColor col-md-2 col-xs-2"><p>{ServiceType[parseInt(service_type)]}</p></Col>
            </>
          ]
        :
          returnList = [returnList, 
            <>
              <Col className="evenColor col-md-1 col-xs-1"><input type="checkbox" key={Math.random()+opname} onChange={()=>toggleMonitora(opname, false)} id={"monitora"+opname} name={"monitora"+opname} value={"monitora"+opname} autocomplete="off"/></Col>
              <Col className="evenColor col-md-5 col-xs-5"><p>{opname}</p></Col>
              <Col className="evenColor col-md-2 col-xs-2"><p>{ServiceControllerStatus[parseInt(stato)]}</p></Col>
              <Col className="evenColor col-md-2 col-xs-2"><p>{ServiceStartMode[parseInt(start_type)]}</p></Col>
              <Col className="evenColor col-md-2 col-xs-2"><p>{ServiceType[parseInt(service_type)]}</p></Col>
            </>
          ]
  }

  return (
    <Box title="Lista dei servizi di Windows" type="primary" collapsable footer={<PopUp title="Gestione dei servizi di Windows" linkClass={"clickable"} childs={props.services_list} action={()=>getServicesList()}/>}>
      <Col md={12} xs={12}>
        <h4><FontAwesomeIcon icon={["fas", "check-circle"]} /> Servizi monitorati: {props.services[0]}</h4>
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