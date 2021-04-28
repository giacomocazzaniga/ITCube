import React from "react";
import Collapsible from 'react-collapsible';
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import ReactPaginate from 'react-paginate';
import PopUp from "./PopUp";
import { eventsList } from "../ActionCreator";
import { defaultUpperBound } from "../Constants";
import { _getEventi, _getServiziAll, _getServiziMonitorati, _modificaMonitoraggioServizio } from "../callableRESTs";
import { getErrorToast, getLoadingToast, stopLoadingToast } from "../toastManager";
import { Pagination } from "react-bootstrap";
import { Backend2FrontendDateConverter, sortResults } from "../Tools";

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({
    EventsList: (events) => {
      dispatch(eventsList(events))
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
    events_list: state.events_list
  }
);

const WindowsEvents = (props) => {

  const [state, setState] = React.useState({
    currentSlot : [1,1,1,1]
  })

  const isOdd = (num) => { return ((num % 2)==1) ? true : false }

  const getEventsList = (currentSlot=[1,1,1,1]) => {
    console.log(currentSlot)
    const loadingToast = getLoadingToast("Caricamento...");
    _getEventi(props.token, props.id_client, "A", currentSlot[0])
    .then(function (response) {
      stopLoadingToast(loadingToast);
      //console.log(response.data.eventi.filter(function(o) { return o.sottocategoria == 'A' }).length)
      let listA = response.data.eventi;
      sortResults("date_and_time_evento",false,listA)
      console.log(listA)
      _getEventi(props.token, props.id_client, "C", currentSlot[1])
      .then(function (response) {
        stopLoadingToast(loadingToast);
        //console.log(response.data.eventi.filter(function(o) { return o.sottocategoria == 'C' }).length)
        let listC = response.data.eventi;
        sortResults("date_and_time_evento",false,listC)
        _getEventi(props.token, props.id_client, "H", currentSlot[2])
        .then(function (response) {
          stopLoadingToast(loadingToast);
          //console.log(response.data.eventi.filter(function(o) { return o.sottocategoria == 'H' }).length)
          let listH = response.data.eventi;
          sortResults("date_and_time_evento",false,listH)
          _getEventi(props.token, props.id_client, "S", currentSlot[3])
          .then(function (response) {
            stopLoadingToast(loadingToast);
            //console.log(response.data.eventi.filter(function(o) { return o.sottocategoria == 'S' }).length)
            let listS = response.data.eventi;
            sortResults("date_and_time_evento",false,listS)
            let merged = [...listA, ...listC, ...listH, ...listS];
            //console.log(merged)
            let list = servicesListMaker(merged);
            props.EventsList(list)
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

  const openToggle = (id) => {
    var elem = document.getElementsByClassName("arrowAccordion"+id)[0];
    elem.style.transform = "rotate(90deg)";
    elem.style.transition = "transform 1s ease";
    elem.style.display = "inline-block";
  }

  const closeToggle = (id) => {
    var elem = document.getElementsByClassName("arrowAccordion"+id)[0];
    elem.style.transform = "rotate(0deg)";
    elem.style.transition = "transform 1s ease";
    elem.style.display = "inline-block";
  }

  const pagination_foo = (currentSlot, posSlot, add, n_sottocategoria) => {
    let controller = "";
    switch (posSlot){
      case 0: controller = "A"
        break;
      case 1: controller = "C"
        break;
      case 2: controller = "H"
        break;
      case 3: controller = "S"
        break;
    }
    if(add==false){
      if(currentSlot[posSlot]>1){
        currentSlot[posSlot]--; 
        setState((previousState) => {
          return { ...previousState, currentSlot: currentSlot };
        });
        console.log(currentSlot);
        getEventsList(currentSlot)
      }
    }else{
      if(currentSlot[posSlot]<Math.ceil(n_sottocategoria[controller]/defaultUpperBound)){
        currentSlot[posSlot]++; 
        setState((previousState) => {
          return { ...previousState, currentSlot: currentSlot };
        });
        console.log(currentSlot);
        getEventsList(currentSlot)
      }
    }
  }

  const servicesListMaker = (services) => {
    let returnList = [<><Col className="popminwidth col-md-12 col-xs-12">Ultimo aggiornamento: {Backend2FrontendDateConverter(services[0].date_and_time)}<hr/></Col></>];
    let status = ["", "Error", "Warning", "", "Information", "", "", "", "SuccessAudit", "", "", "", "", "", "", "", "FailureAudit"]
    //order by category
    services.sort(function (a, b) {
      return a.sottocategoria.localeCompare(b.sottocategoria);
    });
    //console.log(services)
    //for each category print
    let categoriesHeaderWasPrinted = [false, false, false, false];
    let sottocategoria = "";
    let compare_sottocategoria = "";
    let n_sottocategoria = 0;
    for(let j=0; j<4; j++){
      if(j==0){
        n_sottocategoria = props.tot_per_sottocategoria.A
      }
      if(j==1){
        n_sottocategoria = props.tot_per_sottocategoria.C
      }
      if(j==2){
        n_sottocategoria = props.tot_per_sottocategoria.H
      }
      if(j==3){
        n_sottocategoria = props.tot_per_sottocategoria.S
      }
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
      returnList = [returnList, 
        <div class="winServices">
          <Collapsible onOpen={()=>openToggle(j)} onClose={()=>closeToggle(j)} trigger={<div className="clickable"><h4><span className={"arrowAccordion"+j}><FontAwesomeIcon icon={["fas", "chevron-right"]} /></span> {sottocategoria} ({n_sottocategoria})</h4></div>}>
            <Col xs={2} md={2}><strong><h5>LEVEL</h5></strong></Col><Col xs={3} md={3}><strong><h5>DATA E ORA</h5></strong></Col><Col xs={2} md={2}><strong><h5>SOURCE</h5></strong></Col><Col xs={1} md={1}><strong><h5>ID</h5></strong></Col>{/*<Col xs={2} md={2}><strong><h5>TASK CATEGORY</h5></strong></Col>*/}<Col xs={4} md={4}><strong><h5>DESCRIZIONE</h5></strong></Col>
            {services.map((service, i) => getCategories(service.level, service.source, service.id_event, service.task_category, service.info, i, status, compare_sottocategoria, service.sottocategoria, service.date_and_time_evento))}
            <Col className="col-xs-12 col-md-12 reactPaginate"><br/>
                <div class="btn-group" role="group" aria-label="Basic example">
                <button type="button" class="btn btn-secondary" onClick={()=>pagination_foo(Math.ceil(n_sottocategoria/defaultUpperBound) - state.currentSlot + 1, j, false, props.tot_per_sottocategoria, j)}>← Precedente</button>
                <button type="button" class="btn btn-secondary">{state.currentSlot[j]}/{Math.ceil(n_sottocategoria/defaultUpperBound)}</button>
                <button type="button" class="btn btn-secondary" onClick={()=>pagination_foo(Math.ceil(n_sottocategoria/defaultUpperBound) - state.currentSlot + 1, j, true, props.tot_per_sottocategoria, j)}>Successivo →</button>
              </div>
            </Col>
          </Collapsible>
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
          <Col className="oddColor col-md-3 col-xs-3"><p>{Backend2FrontendDateConverter(date)}</p></Col>
          <Col className="oddColor col-md-2 col-xs-2"><p>{source}</p></Col>
          <Col className="oddColor col-md-1 col-xs-1"><p>{id_event}</p></Col>
          {/*<Col className="oddColor col-md-2 col-xs-2"><p>{task_category}</p></Col>*/}
          <Col className="oddColor col-md-4 col-xs-4"><p>{info}</p></Col>
        </>
      :
        <>
          <Col className="evenColor col-md-2 col-xs-2"><p>{status[parseInt(level)]}</p></Col>
          <Col className="evenColor col-md-3 col-xs-3"><p>{Backend2FrontendDateConverter(date)}</p></Col>
          <Col className="evenColor col-md-2 col-xs-2"><p>{source}</p></Col>
          <Col className="evenColor col-md-1 col-xs-1"><p>{id_event}</p></Col>
          {/*<Col className="evenColor col-md-2 col-xs-2"><p>{task_category}</p></Col>*/}
          <Col className="evenColor col-md-4 col-xs-4"><p>{info}</p></Col>
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