import React from 'react';
import { connect } from 'react-redux';
import { Box } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Backend2FrontendDateConverter } from '../Tools';


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
  client_template: state.client_template
});

const Communications = (props) => {

  const [state, setState] = React.useState({
    error_icon: <FontAwesomeIcon icon={["fas", "times-circle"]} size="3x" color="#dd4b39"/>,
    warning_icon: <FontAwesomeIcon icon={["fas", "exclamation-circle"]} size="3x" color="#f39c12"/>,
    success_icon: <FontAwesomeIcon icon={["fas", "check-circle"]} size="3x" color="#00a65a"/>
  })

  return (
    <Box title="Comunicazioni" type="primary" collapsable>
      <div class="card card-danger direct-chat direct-chat-danger">
        <div class="card-body">
          <div class="direct-chat-messages">
            {props.client_template.alert.map((alert) => {
              return(
                <div class="direct-chat-msg">
                  <div class="direct-chat-infos clearfix">
                    <span class="direct-chat-name float-left">[{alert.titolo}] - </span>
                    <span class="direct-chat-timestamp float-right">{Backend2FrontendDateConverter(alert.data)}</span>
                  </div>
                  <span class="direct-chat-img">
                    {(alert.categoria == "ERROR") ? state.error_icon 
                    : (alert.categoria == "WARNING") ? state.warning_icon 
                    : state.success_icon} 
                  </span>
                  <div class="direct-chat-text">
                    {alert.messaggio}
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