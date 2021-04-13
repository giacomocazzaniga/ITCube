import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Content, Row, Col, Box } from 'adminlte-2-react';
import { ModalProvider } from 'react-simple-hook-modal';
import TrafficLightButtons from './TrafficLightButtons';
import History from './History';
import LicensesList from './LicensesList';
import UserData from './UserData';
import ToggleCategoryPlace from './ToggleCategoryPlace';
import { _getLicenzeShallow } from '../callableRESTs';
import { getErrorToast, getLoadingToast, stopLoadingToast } from '../toastManager';

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
const mapStateToProps = state => ({
    client_list: state.client_list,
    nome_company: state.nome_company,
    email: state.email,
    emailNotify: state.emailNotify,
    token: state.token,
    logged: state.logged,
    id_company: state.id_company,
    clientOverview: {
      problems: 2,
      warnings: 2,
      running: 26
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
    },
    sedi: ["Milano", "Venezia", "Torino"]
  }
);

const DashboardHome = (props) => {
  const [state, setState] = React.useState({
    licenses: []
  })
  useEffect(() => {
    const loadingToast = getLoadingToast("Caricamento...");
    _getLicenzeShallow(props.id_company, props.token)
    .then(function (response) {
      stopLoadingToast(loadingToast);
      setState(() => {
        return { licenses: response.data.licenzeShallow };  
      });
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    })
  }, [])

  return (<Content title={props.title} browserTitle={props.title}>
    <Row>
      <ModalProvider>
      <TrafficLightButtons size={3} titles={["Client con problemi", "Client con warnings", "Client senza problemi e warnings"]} problems={props.clientOverview.problems} warnings={props.clientOverview.warnings} running={props.clientOverview.running} />
          <Col xs={6} md={3}>
            <Box title="Personalizzazione" type="primary" collapsable>
              <Col md={12} xs={12}>
                <b>Visualizzazione client: </b><ToggleCategoryPlace />
              </Col>
            </Box>
          </Col>
        
        <Col xs={12} md={6}>
          <LicensesList title="Gestione delle licenze" list={state.licenses}/>
        </Col>
        <Col md={6} xs={12}>
          <History apex={props.apex}/>
        </Col>
        <Col xs={12} md={6}>
          {(state.licenses.length>=1)
          ? <UserData email={props.email} emailNotify={props.emailNotify} ragioneSociale={props.nome_company} sedi={props.sedi} chiave={(state.licenses.filter(function(item){return item.tipologia.toUpperCase() == "SISTEMA OPERATIVO";}))[0]}/>
          : <UserData email={props.email} emailNotify={props.emailNotify} ragioneSociale={props.nome_company} sedi={props.sedi} chiave={{codice:""}}/>
          }
        </Col>
        
        {/*<Col md={3} xs={6}>
          <center class="add"><FontAwesomeIcon icon={["fas", "plus-circle"]} /></center>
        </Col>*/}
      </ModalProvider>
    </Row>
  </Content>);
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(DashboardHome);