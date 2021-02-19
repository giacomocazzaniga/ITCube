import React from "react";
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Modal, useModal, ModalTransition } from 'react-simple-hook-modal';

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

const ServicesList = (props) => {
  const { isModalOpen, openModal, closeModal } = useModal();
  return (
    <Box title="Lista dei servizi" type="primary" collapsable footer={<a href="#" class="small-box-footer">Altro <FontAwesomeIcon icon={["fas", "arrow-circle-right"]} /></a>}>
      <Col md={12} xs={12}>
        <h4><FontAwesomeIcon icon={["fas", "check-circle"]} /> Servizi attivi: {props.active}</h4>
        <h4><FontAwesomeIcon icon={["fas", "play-circle"]} /> Servizi in esecuzione: {props.running}</h4>
        <h4><FontAwesomeIcon icon={["fas", "times-circle"]} /> Servizi con problemi: {props.problems}</h4>
        <h4><FontAwesomeIcon icon={["fas", "exclamation-circle"]} /> Servizi con warnings: {props.warnings}</h4>
        <button onClick={openModal}>Open</button>
        <Modal
          id="any-unique-identifier"
          isOpen={isModalOpen}
          transition={ModalTransition.SCALE}
        >
          <div style={{position:"absolute", top: "-379px", "z-index": 999999, left: "50%"}}>
            <p>Modal</p>
            <button onClick={closeModal}>Close</button>
          </div>
        </Modal>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(ServicesList);