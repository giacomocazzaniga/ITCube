import React from "react";
import { connect } from 'react-redux';
import { Content, Row, Col } from 'adminlte-2-react';
import TrafficLightButtons from './TrafficLightButtons';
import Communications from './Communications';
import History from './History';
import Drive from './Drive';
import ServicesList from './ServicesList';
import ClientInfo from './ClientInfo';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { ModalProvider } from 'react-simple-hook-modal';

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
    servicesOverview: {
      active: 83,
      running: 44,
      problems: 2,
      warnings: 11
    },
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
  return (
  <Content title={props.title} browserTitle={props.title}>  
    <Row>
      <ModalProvider>
        <TrafficLightButtons titles={["Problemi", "Warnings", "Servizi in esecuzione"]} problems={props.clientOverview.problems} warnings={props.clientOverview.warnings} running={props.clientOverview.running} />
        <Col md={8} xs={12}>
          <Communications />
          <History apex={props.apex}/>
        </Col>
        <Col md={4} xs={6}>
          <ClientInfo client={props.client}/>
        </Col>
        {props.drives.map((drive) =>  
          <Col md={4} xs={6}>
            <Drive driveLabel={drive.driveLabel} occupiedSpace={drive.occupiedSpace} lastUpdate={drive.lastUpdate} totalSpace={drive.totalSpace}/>
          </Col>
        )}
        <Col md={4} xs={6}>
          <ServicesList selected={props.title} services={props.client.servizi}/>
        </Col>
        <Col md={4} xs={6}>
          <center class="add"><FontAwesomeIcon icon={["fas", "plus-circle"]} /></center>
        </Col>
      </ModalProvider>
    </Row>
  </Content>
);
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Dashboard);