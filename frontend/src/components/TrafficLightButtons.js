import React from 'react';
import { connect } from 'react-redux';
import { Col } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import PopUp from './PopUp';


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
      <Col xs={props.size}>
        <div className="small-box bg-red">
          <div className="inner">
            <h3>{props.problems}</h3>
            <p>{props.titles[0]}</p>
          </div>
          <div className="icon">
            <FontAwesomeIcon icon={["fas", "times-circle"]} />
          </div>
          <span href="#" className="small-box-footer">
            <PopUp title="Problemi" linkClass={"whiteLink"} childs={[]} action={()=>(console.log("action"))}/>
          </span>
        </div>
      </Col>
      <Col xs={props.size}>
        <div className="small-box bg-yellow">
          <div className="inner">
            <h3>{props.warnings}</h3>
            <p>{props.titles[1]}</p>
          </div>
          <div className="icon">
            <FontAwesomeIcon icon={["fas", "exclamation-circle"]} />
          </div>
          <span href="#" className="small-box-footer">
            <PopUp title="Warnings" linkClass={"whiteLink"} childs={[]} action={()=>(console.log("action"))}/>
          </span>
        </div>
      </Col>
      <Col xs={props.size}>
        <div className="small-box bg-green">
          <div className="inner">
            <h3>{props.running}</h3>
            <p>{props.titles[2]}</p>
          </div>
          <div className="icon">
            <FontAwesomeIcon icon={["fas", "play-circle"]} />
          </div>
          <span href="#" className="small-box-footer">
            <h2></h2>
          </span>
        </div>
      </Col>
    </div>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(TrafficLightButtons);