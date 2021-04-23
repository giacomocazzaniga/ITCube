import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Content, Row, Col, Box } from 'adminlte-2-react';
import { ModalProvider } from 'react-simple-hook-modal';
import TrafficLightButtons from './TrafficLightButtons';
import History from './History';
import LicensesList from './LicensesList';
import UserData from './UserData';
import ToggleCategoryPlace from './ToggleCategoryPlace';
import { _getLicenzeShallow, _getNomiSedi } from '../callableRESTs';
import { getErrorToast, getLoadingToast, stopLoadingToast } from '../toastManager';
import { defaultUpdateInterval } from '../Constants';
import { updateCompanyTemplateLicenze } from '../ActionCreator';

document.body.classList.add('fixed');

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({
  CompanyTemplateLicenze: (lista_licenze) => {
    dispatch(updateCompanyTemplateLicenze(lista_licenze))
  }
});

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({
    client_list: state.client_list,
    nome_company: state.company_template.company_data.ragione_sociale,
    email: state.company_template.company_data.email,
    emailNotify: state.company_template.company_data.emailNotify,
    token: state.token,
    logged: state.logged,
    id_company: state.id_company,
    chiave_di_registrazione: state.company_template.company_data.chiave_di_registrazione,
    // clientOverview: {
    //   problems: 2,
    //   warnings: 2,
    //   running: 26
    // },
    apex: state.company_template.history,
    company_template: state.company_template
  }

);

const DashboardHome = (props) => {

  const updateData = () => {
    const loadingToast = getLoadingToast("Caricamento...");
    _getLicenzeShallow(props.id_company, props.token)
    .then(function (response) {
      props.CompanyTemplateLicenze(response.data.licenzeShallow)
      stopLoadingToast(loadingToast);
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    })
  }

  useEffect(() => {
    updateData();
    let idInterval = setInterval(function(){ 
      updateData();
    }, defaultUpdateInterval);

    //component unmount
    return () => {
      clearInterval(idInterval);
    }

  }, [])

  return (<Content title={props.title} browserTitle={props.title}>
    <Row>
      <ModalProvider>
      <TrafficLightButtons size={3} titles={["Client con problemi", "Client con warnings", "Client senza problemi e warnings"]} problems={props.company_template.client_overview.n_errori} warnings={props.company_template.client_overview.n_warnings} running={props.company_template.client_overview.n_running} />
          <Col xs={6} md={3}>
            <Box title="Personalizzazione" type="primary" collapsable>
              <Col md={12} xs={12}>
                <b>Visualizzazione client: </b><ToggleCategoryPlace />
              </Col>
            </Box>
          </Col>
        
        <Col xs={12} md={6}>
          <LicensesList title="Gestione delle licenze" />
        </Col>
        <Col md={6} xs={12}>
          <History apex={props.apex}/>
        </Col>
        <Col xs={12} md={6}>
          <UserData email={props.email} emailNotify={props.emailNotify} ragioneSociale={props.nome_company} chiave={props.chiave_di_registrazione}/>
        </Col>
        
        {/*<Col md={3} xs={6}>
          <center className="add"><FontAwesomeIcon icon={["fas", "plus-circle"]} /></center>
        </Col>*/}
      </ModalProvider>
    </Row>
  </Content>);
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(DashboardHome);