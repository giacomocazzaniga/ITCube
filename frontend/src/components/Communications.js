import React from 'react';
import { connect } from 'react-redux';
import { Box } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';


/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({});

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({});

const Communications = (props) => {
  return (
    <Box title="Comunicazioni" type="primary" collapsable>
      <div class="card card-danger direct-chat direct-chat-danger">
        <div class="card-body">
          <div class="direct-chat-messages">
            <div class="direct-chat-msg">
              <div class="direct-chat-infos clearfix">
                <span class="direct-chat-name float-left">[Host Network Service] - </span>
                <span class="direct-chat-timestamp float-right">12 Feb 17:59</span>
              </div>
              <span class="direct-chat-img">
                <FontAwesomeIcon icon={["fas", "check-circle"]} size="3x" color="#00a65a"/>
              </span>
              <div class="direct-chat-text">
                Il servizio è stato ripristinato ed è in esecuzione.
              </div>
            </div>
            <div class="direct-chat-msg">
              <div class="direct-chat-infos clearfix">
                <span class="direct-chat-name float-left">[WindowsFreeDiskSpace] - </span>
                <span class="direct-chat-timestamp float-right">12 Feb 17:54</span>
              </div>
              <span class="direct-chat-img">
                <FontAwesomeIcon icon={["fas", "times-circle"]} size="3x" color="#dd4b39"/>
              </span>
              <div class="direct-chat-text">
                Il drive C: ha il 7% di spazio libero rimanente.
              </div>
            </div>
            <div class="direct-chat-msg">
              <div class="direct-chat-infos clearfix">
                <span class="direct-chat-name float-left">[WindowsFreeDiskSpace] - </span>
                <span class="direct-chat-timestamp float-right">12 Feb 12:14</span>
              </div>
              <span class="direct-chat-img">
                <FontAwesomeIcon icon={["fas", "exclamation-circle"]} size="3x" color="#f39c12"/>
              </span>
              <div class="direct-chat-text">
                Il drive C: ha il 12% di spazio libero rimanente.
              </div>
            </div>
            <div class="direct-chat-msg">
              <div class="direct-chat-infos clearfix">
                <span class="direct-chat-name float-left">[Print Spooler] - </span>
                <span class="direct-chat-timestamp float-right">12 Feb 10:07</span>
              </div>
              <span class="direct-chat-img">
                <FontAwesomeIcon icon={["fas", "check-circle"]} size="3x" color="#00a65a"/>
              </span>
              <div class="direct-chat-text">
                Il servizio è stato ripristinato ed è in esecuzione.
              </div>
            </div>
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