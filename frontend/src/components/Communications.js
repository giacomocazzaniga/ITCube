import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Box } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Backend2FrontendDateConverter } from '../Tools';
import { defaultSettimaneAlert, _ALERTCATEGORY } from '../Constants';
import PopUp from './PopUp';
import { _getLatestAlerts, _getMonitoraggioAlert, _updateMonitoraggioAlert } from '../callableRESTs';
import { getConfigurazioneAlert, updateCTAlert } from '../ActionCreator';
import { Col } from 'react-bootstrap';
import { getLoadingToast, stopLoadingToast, getErrorToast } from '../toastManager';


/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({
  GetConfigurazioneAlert: (list) => {
    dispatch(getConfigurazioneAlert(list));
  },
  ClientTemplateAlert: (alerts) => {
    dispatch(updateCTAlert(alerts));
  }
});

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({
  client_template: state.client_template,
  token: state.token,
  configurazione_alert: state.configurazione_alert
});

const Communications = (props) => {

  const isOdd = (num) => { return ((num % 2)==1) ? true : false }

  const [state, setState] = React.useState({
    error_icon: <FontAwesomeIcon icon={["fas", "times-circle"]} size="3x" color="#dd4b39"/>,
    warning_icon: <FontAwesomeIcon icon={["fas", "exclamation-circle"]} size="3x" color="#f39c12"/>,
    success_icon: <FontAwesomeIcon icon={["fas", "check-circle"]} size="3x" color="#00a65a"/>
  })

  const getChilds = () => {
    const loadingToast = getLoadingToast("Attendere...")
    _getMonitoraggioAlert( props.token, props.id_client)
    .then( response => {
      props.GetConfigurazioneAlert(renderChilds(response.data.listaOperazioni));
      stopLoadingToast(loadingToast);
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    })
  }

  const renderChilds = (listaOperazioni) => {
    let listaChildren = [];
      if(listaOperazioni.length > 0)
        listaChildren.push(<><Col className="oddColor vertical-aligner col-md-2 col-xs-2"><h5><strong>MONITORA</strong></h5></Col><Col className="oddColor vertical-aligner col-md-10 col-xs-10"><h5><strong>OPERAZIONE</strong></h5></Col></>)
      listaOperazioni.forEach( (operazione,i) => {
        listaChildren.push(
          (operazione.monitora)
          ?
            (isOdd(i))
            ?
              <>
                <Col className="oddColor vertical-aligner col-md-2 col-xs-2">
                  <input type="checkbox" defaultChecked="checked" onChange={(e) => toggleMonitora(e,operazione.operazione)}/>
                </Col>
                <Col className="oddColor vertical-aligner col-md-10 col-xs-10">
                  <p>{operazione.operazione}</p>
                </Col>
              </>
            :
              <>
                <Col className="evenColor vertical-aligner col-md-2 col-xs-2">
                  <input type="checkbox" defaultChecked="checked" onChange={(e) => toggleMonitora(e,operazione.operazione)}/>
                </Col>
                <Col className="evenColor vertical-aligner col-md-10 col-xs-10">
                  <p>{operazione.operazione}</p>
                </Col>
              </>
          :
            (isOdd(i))
            ?
              <>
                <Col className="oddColor vertical-aligner col-md-2 col-xs-2">
                  <input type="checkbox" autoComplete="off" onChange={(e) => toggleMonitora(e,operazione.operazione)}/>
                </Col>
                <Col className="oddColor vertical-aligner col-md-10 col-xs-10">
                  <p>{operazione.operazione}</p>
                </Col>
              </>
            :
              <>
                <Col className="evenColor vertical-aligner col-md-2 col-xs-2">
                  <input type="checkbox" autoComplete="off" onChange={(e) => toggleMonitora(e,operazione.operazione)}/>
                </Col>
                <Col className="evenColor vertical-aligner col-md-10 col-xs-10">
                  <p>{operazione.operazione}</p>
                </Col>
              </>
        )
      })
      return(listaChildren)
  }

  const toggleMonitora = (e,operazione) => {
    const loadingToast = getLoadingToast("Attendere...")
    let monitora = (e.target.checked) ? true : false; 
    _updateMonitoraggioAlert(props.token,props.id_client,monitora,operazione)
    .then( response => {
        _getLatestAlerts(props.token, props.id_client)
        .then( response => {
          props.ClientTemplateAlert(response.data.alerts)
          stopLoadingToast(loadingToast);
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
    getChilds()
    const loadingToast = getLoadingToast("Attendere...")
        _getLatestAlerts(props.token, props.id_client, defaultSettimaneAlert)
        .then( response => {
          props.ClientTemplateAlert(response.data.alerts)
          stopLoadingToast(loadingToast);
      })
      .catch(function (error) {
        stopLoadingToast(loadingToast);
        getErrorToast(String(error));
      })
  },[])

  return (
    <Box title="Comunicazioni" type="primary" collapsable footer={<PopUp title="Gestione degli alert" linkClass={"clickable"} childs={props.configurazione_alert} action={()=>getChilds()}/>}>
      <div className="card card-danger direct-chat direct-chat-danger">
        <div className="card-body">
          <div className="direct-chat-messages">
            {props.client_template.alert.map((alert, i) => {
              return(
                <div className="direct-chat-msg" key={i}>
                  <div className="direct-chat-infos clearfix">
                    <span className="direct-chat-name float-left">[{_ALERTCATEGORY[alert.categoria]}] - </span>
                    <span className="direct-chat-timestamp float-right">{Backend2FrontendDateConverter(alert.date_and_time_alert)}</span>
                  </div>
                  <span className="direct-chat-img">
                    {(alert.tipo == "ERROR") ? state.error_icon 
                    : (alert.tipo == "WARNING") ? state.warning_icon 
                    : (alert.tipo == "OK") ? state.success_icon
                    : <></>} 
                  </span>
                  <div className="direct-chat-text">
                    {alert.corpo_messaggio}
                  </div>
                </div>
              )
            })}
          </div>
        </div>
      </div>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Communications);