import React from 'react';
import { connect } from 'react-redux';
import { Col, Box } from 'adminlte-2-react';
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
const mapStateToProps = state => {
  return {
    client_list: state.client_list,
    nome_company: state.nome_company,
    token: state.token,
    logged: state.logged
  }
}

const UserData = (props) => {
  return (
    <Box title="Dati dell'utente" type="primary" collapsable footer={<a href="#" class="small-box-footer">Modifica <FontAwesomeIcon icon={["fas", "pen"]} /></a>}>
      <Col md={12} xs={12}>
        <h4><b>Indirizzo email: </b>{props.email}</h4>
        <h4><b>Indirizzo email per le comunicazioni: </b>{props.emailNotify}</h4>
        <h4><b>Ragione sociale: </b>{props.ragioneSociale}</h4>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(UserData);