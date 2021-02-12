import React from 'react';
import { connect } from 'react-redux';
import { Content, Row, Col, Box, Button, Infobox2 } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import Knob from 'react-canvas-knob';

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
    values: [93, 18]
  }
}

const Dashboard = (props) => {
  return (<Content title={props.title} browserTitle={props.title}>
    <Row>
      <Col xs={4}>
        <div class="small-box bg-red">
          <div class="inner">
            <h3>3</h3>
            <p>Problemi</p>
          </div>
          <div class="icon">
            <FontAwesomeIcon icon={["fas", "times-circle"]} />
          </div>
          <a href="#" class="small-box-footer">
            Altro <FontAwesomeIcon icon={["fas", "arrow-circle-right"]} />
          </a>
        </div>
      </Col>
      <Col xs={4}>
        <div class="small-box bg-yellow">
          <div class="inner">
            <h3>12</h3>
            <p>Warnings</p>
          </div>
          <div class="icon">
            <FontAwesomeIcon icon={["fas", "exclamation-circle"]} />
          </div>
          <a href="#" class="small-box-footer">
            Altro <FontAwesomeIcon icon={["fas", "arrow-circle-right"]} />
          </a>
        </div>
      </Col>
      <Col xs={4}>
        <div class="small-box bg-green">
          <div class="inner">
            <h3>44</h3>
            <p>Servizi in esecuzione</p>
          </div>
          <div class="icon">
            <FontAwesomeIcon icon={["fas", "check-circle"]} />
          </div>
          <a href="#" class="small-box-footer">
            Altro <FontAwesomeIcon icon={["fas", "arrow-circle-right"]} /> 
          </a>
        </div>
      </Col>
      <Col xs={8}>
        <Box title="Comunicazioni" type="primary" collapsable>
          <div class="card card-danger direct-chat direct-chat-danger">
            <div class="card-body">
              <div class="direct-chat-messages">
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
              </div>
            </div>
          </div>
        </Box>
      </Col>
      <Col xs={4}>
        <Box title="Drive C:" type="primary" collapsable footer={"Lo spazio disponibile è pari a 7%, ultimo controllo 12 Feb 17:54"}>
          <Col xs={4}>
            <Knob value={props.values[0]} fgColor="#dd4b39" height="97" width="97"/>
          </Col>
          <Col xs={8}>
            <h4>Dimensione disco: 250GB</h4>
            <h4>Spazio occupato: 232.5GB</h4>
            <h4>Spazio libero: 17.5GB</h4>
          </Col>
        </Box>
      </Col>
      <Col xs={4}>
        <Box title="Drive D:" type="primary" collapsable footer={"Lo spazio disponibile è pari a 82%, ultimo controllo 12 Feb 18:01"}>
          <Col xs={4}>
            <Knob value={props.values[1]} fgColor="#00a65a" height="97" width="97"/>
          </Col>
          <Col xs={8}>
            <h4>Dimensione disco: 250GB</h4>
            <h4>Spazio occupato: 45GB</h4>
            <h4>Spazio libero: 205GB</h4>
          </Col>
        </Box>
      </Col>
    </Row>
  </Content>);
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Dashboard);