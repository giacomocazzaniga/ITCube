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
  const percentage = props.occupiedSpace;
  return(
    <Box title={"Drive "+props.driveLabel+": "+parseInt(100-props.occupiedSpace)+"% libero"} type="primary" collapsable footer={"Ultimo aggiornamento "+props.lastUpdate}>
      <Col className="col-md-4 col-xs-6 offset-xs-3">
        {parseInt(props.occupiedSpace) >= 90 
        ? <CircularProgressbar value={percentage} text={`${percentage}%`} styles={{path: {stroke: '#dd4b39'}, text: {fill: '#dd4b39'}}}/>
        : parseInt(props.occupiedSpace) >= 80 
          ? <CircularProgressbar value={percentage} text={`${percentage}%`} styles={{path: {stroke: '#f39c12'}, text: {fill: '#f39c12'}}}/>
          : <CircularProgressbar value={percentage} text={`${percentage}%`} styles={{path: {stroke: '#00a65a'}, text: {fill: '#00a65a'}}}/>
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