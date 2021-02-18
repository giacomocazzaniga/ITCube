import React from 'react';
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
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

const ClientInfo = (props) => {
  return (
    <Box title="Informazioni sul client" type="primary" collapsable>
      <Col md={12} xs={12}>
        <h4><b>Nome: </b>{props.client.nome_client}</h4>
        <h4><b>Tipologia: </b>{props.client.tipo_client}</h4>
        <h4><b>MAC address: </b>{props.client.MAC_address}</h4>
        <h4><b>Licenza: </b>{props.client.codice_licenza}</h4>
        <h4><b>Tipologia Licenza: </b>{props.client.nome_tipologia_licenza}</h4>
        <h4><b>Gruppo di lavoro: </b>{props.client.category}</h4>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(ClientInfo);