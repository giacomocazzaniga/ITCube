import React, { useEffect } from 'react';
import AdminLTE, { Sidebar } from 'adminlte-2-react';
import { connect } from 'react-redux';
import { Content, Row, Col, Box } from 'adminlte-2-react';
import { ModalProvider } from 'react-simple-hook-modal';
import TrafficLightButtons from './TrafficLightButtons';
import History from './History';
import LicensesList from './LicensesList';
import UserData from './UserData';
import ToggleCategoryPlace from './ToggleCategoryPlace';
import { _getCompanyHistory, _getCompanyOverview, _getLicenzeShallow, _getNomiSedi } from '../callableRESTs';
import { getErrorToast, getLoadingToast, stopLoadingToast } from '../toastManager';
import { defaultUpdateInterval } from '../Constants';
import { updateCompanyTemplateLicenze, updateCompanyOverview, fixSedi, updateCompanyHistory } from '../ActionCreator';

document.body.classList.add('fixed');

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({
  CompanyTemplateLicenze: (lista_licenze) => {
    dispatch(updateCompanyTemplateLicenze(lista_licenze));
  },
  UpdateCompanyOverview: (n_errori, n_warnings, n_ok) => {
    dispatch(updateCompanyOverview(n_errori, n_warnings, n_ok))
  },
  UpdateCompanyHistory: (history_data) => {
    dispatch(updateCompanyHistory(history_data))
  },
  FixSedi: (client_list, lista_nomi_sedi, lista_id_sedi) => {
    let tmp_list = client_list;
    let tmp_list2 = []
    tmp_list.map(tmp_client => {
      lista_nomi_sedi.map((sede,i) => {
        if((tmp_client.sede === sede) || (tmp_client.sede === lista_id_sedi[i])){
          tmp_client.sede = String(lista_id_sedi[i]);
          tmp_list2.push(tmp_client)
        }
      })
    })
    dispatch(fixSedi(tmp_list2))
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
    company_template: state.company_template,
    lista_nomi_sedi: state.lista_nomi_sedi,
    lista_id_sedi: state.lista_id_sedi,
  }

);

const { Item } = Sidebar;

const DashboardHome = (props) => {

  const updateData = () => {
    const loadingToast = getLoadingToast("Caricamento...");
    _getLicenzeShallow(props.id_company, props.token)
    .then(function (response) {
      let tmp_list = props.client_list;
      let tmp_list2 = []
      tmp_list.map(tmp_client => {
        props.lista_nomi_sedi.map((sede,i) => {
          if((tmp_client.sede === sede) || (tmp_client.sede === props.lista_id_sedi[i])){
            tmp_client.sede = String(props.lista_id_sedi[i]);
            tmp_list2.push(tmp_client)
          }
        })
      })
        props.CompanyTemplateLicenze(response.data.licenzeShallow)

        _getCompanyHistory(props.token,props.id_company)
        .then( response => {
          props.UpdateCompanyHistory(response)
          _getCompanyOverview(props.token, props.id_company)
          .then(function (response) {
            let myPromise = new Promise(function (myResolve,myReject) {
              props.UpdateCompanyOverview(response.data.errori, response.data.warning,response.data.ok )
              myResolve();
            });
            myPromise.then(
              function (value) {    
                let tmp_list = props.client_list;
                let tmp_list2 = []
                tmp_list.map(tmp_client => {
                  props.lista_nomi_sedi.map((sede,i) => {
                    if((tmp_client.sede === sede) || (tmp_client.sede === props.lista_id_sedi[i])){
                      tmp_client.sede = String(props.lista_id_sedi[i]);
                      tmp_list2.push(tmp_client)
                    }
                  })
                })
                props.FixSedi(tmp_list2, props.lista_nomi_sedi, props.lista_id_sedi);
              },
              function (error) {}
            )
            stopLoadingToast(loadingToast);
          })
        })
        .catch(function (error) {
          stopLoadingToast(loadingToast);
          getErrorToast(String(error));
        })
      .catch(function (error) {
        stopLoadingToast(loadingToast);
        getErrorToast(String(error));
      })  
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

  const isOdd = (num) => { return ((num % 2)==1) ? true : false }

  const getChilds = (listId) => {
    //console.log(listId.length)
    let listClients = [];
    let tmp_client;
    props.client_list.map( client => {
      listId.map( id => {
        if(id == client.id_client){
          tmp_client = client;
          props.lista_id_sedi.map( (id_sede,i) => {
            if((id_sede == client.sede)||(props.lista_nomi_sedi[i] == client.sede)){
              tmp_client.sede = props.lista_nomi_sedi[i];
              listClients.push(tmp_client);
            }
          })
        }
      })
    })
    //console.log(listClients);
    let list = [<Col className="oddColor col-md-4 col-xs-4"><strong>Nome client</strong></Col>, <Col className="oddColor col-md-4 col-xs-4"><strong>Sede</strong></Col>, <Col className="oddColor col-md-4 col-xs-4"><strong>Tipologia</strong></Col>];
    
    listClients.map((client,i) => {
      (isOdd(i)) 
      ?
        list = [...list, <Col className="oddColor col-md-4 col-xs-4"><Item key={client.id_client} text={client.nome_client} to={"/company"+props.nome_company+"user"+client.id_client} /></Col>, <Col className="oddColor col-md-4 col-xs-4">{client.sede}</Col>, <Col className="oddColor col-md-4 col-xs-4">{client.tipo_client}</Col>]
      :
        list = [...list, <Col className="evenColor col-md-4 col-xs-4"><Item key={client.id_client} text={client.nome_client} to={"/company"+props.nome_company+"user"+client.id_client} /></Col>,  <Col className="evenColor col-md-4 col-xs-4">{client.sede}</Col>, <Col className="evenColor col-md-4 col-xs-4">{client.tipo_client}</Col>]
      })
    return list;
  }

  return (<Content title={props.title} browserTitle={props.title}>
    <Row>
      <ModalProvider>
      <TrafficLightButtons size={3} titles={["Client con problemi", "Client con warnings", "Client senza problemi e warnings"]} problems={props.company_template.client_overview.n_errori.length} warnings={props.company_template.client_overview.n_warnings.length} running={props.company_template.client_overview.n_running.length} popUpChildsWarnings={[]} popUpChildsProblemi={[]} idClientsWarnings={getChilds(props.company_template.client_overview.n_warnings)} idClientsProblemi={getChilds(props.company_template.client_overview.n_errori)} isHome={true} />
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