import React from 'react';
import { connect } from 'react-redux';
import { Content, Row, Col } from 'adminlte-2-react';
import TrafficLightButtons from './TrafficLightButtons';
import History from './History';
import LicensesList from './LicensesList';
import UserData from './UserData';

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
    email: state.email,
    emailNotify: state.emailNotify,
    token: state.token,
    logged: state.logged,
    clientOverview: {
      problems: 2,
      warnings: 2,
      running: 26
    },
    licenses: [
      {
        codice: "ATRJ-95SX-LQQ6-IRRV",
        classe: 0,
        nome_tipologia: "Free"
      },
      {
        codice: "ZQCQ-B0EC-TW8N-YZFT",
        classe: 0,
        nome_tipologia: "Free"
      },
      {
        codice: "SQVH-F0H2-ZDHH-3GLR",
        classe: 1,
        nome_tipologia: "Premium"
      },
      {
        codice: "LE1P-42KI-PY9L-1FZP",
        classe: 2,
        nome_tipologia: "Pro"
      },
      {
        codice: "TLCU-UMMR-83JL-YORW",
        classe: 0,
        nome_tipologia: "Free"
      },
      {
        codice: "WIC3-9FST-SLDX-XUMA",
        classe: 1,
        nome_tipologia: "Premium"
      },
      {
        codice: "VXLL-RPEA-5HMH-GEEQ",
        classe: 2,
        nome_tipologia: "Pro"
      }
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
          name: "Client con warnings",
          data: [2, 1, 1, 0, 0, 0, 4, 2, 4, 5, 6, 3, 2],
          color: "#f39c12"
        },
        {
          name: "Client con problemi",
          data: [2, 4, 1, 0, 0, 0, 0, 2, 4, 2, 1, 1, 2],
          color: "#dd4b39"
        }
      ]
    }
  }
}

const DashboardHome = (props) => {
  return (<Content title={props.title} browserTitle={props.title}>
    <Row>
      <TrafficLightButtons titles={["Client con problemi", "Client con warnings", "Client senza problemi e warnings"]} problems={props.clientOverview.problems} warnings={props.clientOverview.warnings} running={props.clientOverview.running} />
      <Col xs={12} md={4}>
        <LicensesList title="Gestione delle licenze" list={props.licenses}/>
      </Col>
      <Col md={8} xs={12}>
        <History apex={props.apex}/>
      </Col>
      <Col xs={12} md={6}>
        <UserData email={props.email} emailNotify={props.emailNotify} ragioneSociale={props.nome_company}/>
      </Col>
    </Row>
  </Content>);
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(DashboardHome);