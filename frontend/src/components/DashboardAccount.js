import React from 'react';
import { connect } from 'react-redux';
import { Content, Row, Col } from 'adminlte-2-react';
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
    logged: state.logged
  }
}

const DashboardAccount = (props) => {
  return (<Content title={props.title} browserTitle={props.title}>
    <Row>
      <Col xs={12} md={6}>
        <UserData email={props.email} emailNotify={props.emailNotify} ragioneSociale={props.nome_company}/>
      </Col>
      <Col xs={12} md={6}>
        
      </Col>
      <Col xs={12} md={6}>
        
      </Col>
    </Row>
  </Content>);
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(DashboardAccount);