import React from 'react';
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
import Knob from 'react-canvas-knob';
import { CircularProgressbar, buildStyles  } from 'react-circular-progressbar';
import 'react-circular-progressbar/dist/styles.css';


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
  let totalGB = (parseInt(props.totalSpace)/(1073741824)).toFixed(2);
  let freeGB = (parseInt(props.occupiedSpace)/(1073741824)).toFixed(2);
  let occupiedSpace = (totalGB-freeGB).toFixed(2);
  const percentage = ((occupiedSpace*100)/totalGB).toFixed(0);
  return(
    <Box title={"Drive "+props.driveLabel+" "+parseInt(100-percentage)+"% libero"} type="primary" collapsable footer={"Ultimo aggiornamento "+props.lastUpdate}>
      <Col md={4} xs={12}>
        {percentage >= 90 
        ? <center><CircularProgressbar value={percentage} text={`${percentage}%`} styles={{path: {stroke: '#dd4b39'}, text: {fill: '#dd4b39'}}}/></center>
        : percentage >= 80 
          ? <center><CircularProgressbar value={percentage} text={`${percentage}%`} styles={{path: {stroke: '#f39c12'}, text: {fill: '#f39c12'}}}/></center>
          : <center><CircularProgressbar value={percentage} text={`${percentage}%`} styles={{path: {stroke: '#00a65a'}, text: {fill: '#00a65a'}}}/></center>
        }
      </Col>
      <Col md={8} xs={12}>
        <h4>Dimensione disco: {totalGB}GB</h4>
        <h4>Spazio occupato: {occupiedSpace}GB</h4>
        <h4>Spazio libero: {freeGB}GB</h4>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Drive);