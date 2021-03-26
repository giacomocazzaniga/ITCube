import React from 'react';
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
import { _LICENZE } from '../Constants';

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
  const getLicense = (classe_licenza) => {
    let label = "";
    switch(classe_licenza){
    case _LICENZE.SISTEMA_OPERATIVO.tipo:
      label = _LICENZE.SISTEMA_OPERATIVO.label;
      break;
    case _LICENZE.BACKUP.tipo:
      label = _LICENZE.BACKUP.label;
      break;
    case _LICENZE.ANTIVIRUS.tipo:
      label = _LICENZE.ANTIVIRUS.label;
      break;
    case _LICENZE.RETE.tipo:
      label = _LICENZE.RETE.label;
      break;
    case _LICENZE.VULNERABILITA.tipo:
      label = _LICENZE.VULNERABILITA.label;
      break;
    }
    return label;
  }

  return (
    <Box title="Informazioni sul client" type="primary" collapsable>
      <Col md={12} xs={12}>
        <h4><b>Nome: </b>{props.client.nome_client}</h4>
        <h4><b>Tipologia: </b>{props.client.tipo_client}</h4>
        <h4><b>MAC address: </b>{props.client.MAC_address}</h4>
        <h4><b>Licenza: </b>{props.client.codice_licenza}</h4>
        <h4><b>Tipologia Licenza: </b>{getLicense(props.client.classe_licenza)}</h4>
        {/*<h4><b>Gruppo di lavoro: </b>{props.client.category}</h4>*/}
        <h4><b>Sede di lavoro: </b>{props.client.sede}</h4>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(ClientInfo);