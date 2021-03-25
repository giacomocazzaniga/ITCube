import React, { useEffect } from "react";
import { connect } from 'react-redux';
import { Content, Row, Col } from 'adminlte-2-react';
import TrafficLightButtons from './TrafficLightButtons';
import Communications from './Communications';
import History from './History';
import Drive from './Drive';
import ServicesList from './OperationsList';
import ClientInfo from './ClientInfo';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { ModalProvider } from 'react-simple-hook-modal';
import { _getDeepClient, _getEventiOverview, _getServiziOverview } from "../callableRESTs";
import { useToasts } from "react-toast-notifications";
import WindowsServices from "./WindowsServices";
import WindowsEvents from "./WindowsEvents";
import OperationsList from "./OperationsList";

document.body.classList.add('fixed');

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({});

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => {
  return {
    client_list: state.client_list,
    nome_company: state.nome_company,
    token: state.token,
    logged: state.logged,
    clientOverview: {
      problems: 3,
      warnings: 12,
      running: 44
    },
    drives: [
      {
        driveLabel: "C",
        occupiedSpace: 93,
        totalSpace: 250,
        lastUpdate: "12 Feb 17:54"
      },
      {
        driveLabel: "D",
        occupiedSpace: 22,
        totalSpace: 250,
        lastUpdate: "12 Feb 18:01"
      },
    ],
    apex: {
      lastUpdate: "12 Feb 18:00",
      options: {
        chart: {
          id: "basic-bar"
        },
        xaxis: {
          categories: ["17:00", "17:05", "17:10", "17:15", "17:20", "17:25", "17:30", "17:35", "17:40", "17:45", "17:50", "17:55", "18:00"]
        },
        stroke: {
          curve: 'smooth',
        },
        fill: {
          type: "gradient",
          gradient: {
            shadeIntensity: 1,
            opacityFrom: 0.7,
            opacityTo: 0.9,
            stops: [0, 90, 100]
          }
        },
        dataLabels: {
          enabled: false
        },
      },
      colors: ["#dd4b39", "#f39c12"],
      series: [
        {
          name: "Warnings",
          data: [15, 16, 10, 9, 3, 2, 8, 3, 16, 14, 12, 10, 12],
          color: "#f39c12"
        },
        {
          name: "Problemi",
          data: [5, 4, 5, 8, 3, 3, 3, 5, 4, 2, 1, 0, 3],
          color: "#dd4b39"
        }
      ]
    }
  }
}

const Dashboard = (props) => {
  const [state, setState] = React.useState({
    clientData: null,
    n_totali: 0,
    n_running: 0,
    n_stop: 0,
    problemi_oggi: 0,
    warning_oggi: 0
  })

  const { addToast } = useToasts();

  useEffect( () => {
    //on component mount
    _getDeepClient(props.id_client, props.id_company, props.token)
    .then(function (response) {
      setState((previousState) => {
        return { ...previousState, clientData: response.data };
      });
      _getServiziOverview(props.token, props.id_client)
      .then(function (response) {
        setState((previousState) => {
          return { ...previousState, 
            n_totali: response.data.n_totali,
            n_running: response.data.n_running,
            n_stop: response.data.n_stop
          };
        });
        _getEventiOverview(props.token, props.id_client)
        .then(function (response) {
          setState((previousState) => {
            return { ...previousState, 
              problemi_oggi: response.data.problemi_oggi,
              warning_oggi: response.data.warning_oggi
            };
          });
        })
      })
      .catch(function (error) {
        addToast("Errore durante il caricamento di servizi", {appearance: 'error',autoDismiss: true});
      });
    })
    .catch(function (error) {
      addToast("Errore durante il caricamento del dispositivo", {appearance: 'error',autoDismiss: true});
    });
    return () => {
      //on component unmount
  }
 }, []);
  return (
  state.clientData != null 
  ? 
  <Content title={props.title} browserTitle={props.title}>  
    <Row>
      <ModalProvider>
        <TrafficLightButtons size={4} titles={["Problemi", "Warnings", "Servizi in esecuzione"]} problems={props.clientOverview.problems} warnings={props.clientOverview.warnings} running={props.clientOverview.running} />
        <Col md={8} xs={12}>
          <Communications />
          <History apex={props.apex}/>
        </Col>
        <Col md={4} xs={6}>
          <ClientInfo client={state.clientData}/>
        </Col>
        {props.drives.map((drive) =>  
          <Col md={4} xs={6}>
            <Drive driveLabel={drive.driveLabel} occupiedSpace={drive.occupiedSpace} lastUpdate={drive.lastUpdate} totalSpace={drive.totalSpace}/>
          </Col>
        )}
        <Col md={4} xs={6}>
          <OperationsList selected={props.title} ops={[state.clientData.op_attive, state.clientData.op_esecuzione, state.clientData.op_problemi, state.clientData.op_warnings]}/>
        </Col>
        <Col md={4} xs={6}>
          <WindowsServices selected={props.title} id_client={props.id_client} services={[state.n_totali, state.n_running, state.n_stop]}/>
        </Col>
        <Col md={4} xs={6}>
          <WindowsEvents selected={props.title} id_client={props.id_client} events={[state.problemi_oggi, state.warning_oggi]}/>
        </Col>
        {/*<Col md={3} xs={6}>
          <center class="add"><FontAwesomeIcon icon={["fas", "plus-circle"]} /></center>
        </Col>*/}
      </ModalProvider>
    </Row>
  </Content>
  : <></>
);
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Dashboard);