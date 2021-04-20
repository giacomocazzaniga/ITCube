import React, { useEffect } from "react";
import { connect } from 'react-redux';
import { Content, Row, Col } from 'adminlte-2-react';
import TrafficLightButtons from './TrafficLightButtons';
import Communications from './Communications';
import History from './History';
import Drive from './Drive';
import ClientInfo from './ClientInfo';
import { ModalProvider } from 'react-simple-hook-modal';
import { _getDeepClient, _getDrives, _getEventiOverview, _getServiziOverview } from "../callableRESTs";
import WindowsServices from "./WindowsServices";
import WindowsEvents from "./WindowsEvents";
import OperationsList from "./OperationsList";
import { getErrorToast, getLoadingToast, stopLoadingToast } from "../toastManager";
import { resetClientTemplate, serviziOverview, updateCTInfo, updateCTWindowsEvents, updateCTWindowsServices } from "../ActionCreator";

document.body.classList.add('fixed');

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({
  SetOverviewServizi: (n_totali, n_running, n_stop, n_monitorati) => {
    dispatch(serviziOverview(n_totali, n_running, n_stop, n_monitorati))
  },
  SetClientTemplateWindowsServices: (n_monitorati, n_esecuzione, n_stop, n_totali) => {
    dispatch(updateCTWindowsServices(n_monitorati, n_esecuzione, n_stop, n_totali ))
  },
  SetClientTemplateWindowsEvents: (n_problemi, n_warnings) => {
    dispatch(updateCTWindowsEvents(n_problemi, n_warnings))
  },
  SetClientTemplateInfo: (info) => {
    dispatch(updateCTInfo(info))
  },
  ResetClientTemplate: () => {
    dispatch(resetClientTemplate())
  }
});

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({
    client_template: state.client_template,
    client_list: state.client_list,
    nome_company: state.nome_company,
    token: state.token,
    logged: state.logged,
    n_totali: state.n_totali,
    n_running: state.n_running,
    n_stop: state.n_stop,
    n_monitorati: state.n_monitorati,
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
});

const Dashboard = (props) => {
  const [state, setState] = React.useState({
    clientData: null,
    problemi_oggi: 0,
    warning_oggi: 0,
    tot_per_sottocategoria: [],
    drives: []
  })

  useEffect( () => {
    props.ResetClientTemplate()
    //on component mount
    const loadingToast = getLoadingToast("Caricamento...");
    _getDeepClient(props.id_client, props.id_company, props.token)
    .then(function (response) {
      props.SetClientTemplateInfo(response.data)
      setState((previousState) => {
        return { ...previousState, clientData: response.data };
      });
      _getServiziOverview(props.token, props.id_client)
      .then(function (response) {
        //n_monitorati, n_esecuzione, n_stop
        props.SetOverviewServizi(response.data.n_totali, response.data.n_running, response.data.n_stopped, response.data.n_monitorati)
        props.SetClientTemplateWindowsServices(response.data.n_monitorati, response.data.n_running, response.data.n_stopped, response.data.n_totali)
        _getEventiOverview(props.token, props.id_client)
        .then(function (response) {
          props.SetClientTemplateWindowsEvents(response.data.problemi_oggi, response.data.warning_oggi)
          setState((previousState) => {
            return { ...previousState, 
              problemi_oggi: response.data.problemi_oggi,
              warning_oggi: response.data.warning_oggi,
              tot_per_sottocategoria: response.data.tot_per_sottocategoria
            };
          });
          _getDrives(props.token, props.id_client)
          .then(function (response) {
            setState((previousState) => {
              return { ...previousState, 
                drives: response.data.drives
              };
            });
          })
          .catch(function (error) {
            stopLoadingToast(loadingToast);
            getErrorToast(String(error));
          })
        })
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
          <ClientInfo client={props.client_template.info} id_client={props.id_client}/>
        </Col>
        {state.drives != [] 
        ? state.drives.map((drive) =>  
          <Col md={4} xs={6}>
            <Drive driveLabel={drive.driveLabel} occupiedSpace={drive.occupiedSpace} lastUpdate={drive.lastUpdate} totalSpace={drive.totalSpace}/>
          </Col>
        )
        : <></>}
        {/*<Col md={4} xs={6}>
          <OperationsList selected={props.title} ops={[state.clientData.op_attive, state.clientData.op_esecuzione, state.clientData.op_problemi, state.clientData.op_warnings]}/>

        </Col>*/}
        <Col md={4} xs={6}>
          <WindowsServices selected={props.title} id_client={props.id_client} services={[props.client_template.windows_services.n_monitorati, props.client_template.windows_services.n_esecuzione, props.client_template.windows_services.n_stop, props.client_template.windows_services.n_totali]}/>
        </Col>
        <Col md={4} xs={6}>
          <WindowsEvents selected={props.title} id_client={props.id_client} events={[props.client_template.windows_events.n_problemi, props.client_template.windows_events.n_warnings]} tot_per_sottocategoria={state.tot_per_sottocategoria}/>
        </Col>
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