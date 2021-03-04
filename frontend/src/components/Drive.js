import React from 'react';
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
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
const mapStateToProps = state => ({});

const Drive = (props) => {
  return (
    <Box collapsed title={"Drive "+props.driveLabel+": "+parseInt(100-props.occupiedSpace)+"% libero"} type="primary" collapsable footer={"Ultimo aggiornamento "+props.lastUpdate}>
      <Col md={4} xs={12}>
        {parseInt(props.occupiedSpace) >= 90 
        ?<Knob value={props.occupiedSpace} fgColor="#dd4b39" height="97" width="97"/>
        : parseInt(props.occupiedSpace) >= 80 
          ? <Knob value={props.occupiedSpace} fgColor="#f39c12" height="97" width="97"/>
          : <Knob value={props.occupiedSpace} fgColor="#00a65a" height="97" width="97"/>
        }
      </Col>
      <Col md={8} xs={12}>
        <h4>Dimensione disco: {props.totalSpace}GB</h4>
        <h4>Spazio occupato: {parseInt(props.totalSpace)*(parseInt(props.occupiedSpace)/100)}GB</h4>
        <h4>Spazio libero: {parseInt(props.totalSpace)-(parseInt(props.totalSpace)*(parseInt(props.occupiedSpace)/100))}GB</h4>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Drive);