import React from 'react';
import { connect } from 'react-redux';
import { Col } from 'adminlte-2-react';
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

const TrafficLightButtons = (props) => {
  return (
    <div>
      <Col xs={4}>
        <div class="small-box bg-red">
          <div class="inner">
            <h3>{props.problems}</h3>
            <p>{props.titles[0]}</p>
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
            <h3>{props.warnings}</h3>
            <p>{props.titles[1]}</p>
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
            <h3>{props.running}</h3>
            <p>{props.titles[2]}</p>
          </div>
          <div class="icon">
            <FontAwesomeIcon icon={["fas", "play-circle"]} />
          </div>
          <a href="#" class="small-box-footer">
            Altro <FontAwesomeIcon icon={["fas", "arrow-circle-right"]} /> 
          </a>
        </div>
      </Col>
    </div>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(TrafficLightButtons);