import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React from 'react';
import { connect } from 'react-redux';
import { Modal, useModal, ModalTransition } from 'react-simple-hook-modal';
import { Col, Box } from 'adminlte-2-react';


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

const PopUp = (props) => {
  const { isModalOpen, openModal, closeModal } = useModal();
  const launchAction = () => {
    return () => {
      openModal();
      props.action();
    };
  }
  return (
    <>
      <a onClick={launchAction()} class="clickable">Altro <FontAwesomeIcon icon={["fas", "arrow-circle-right"]} /></a>
      <Modal
        id="any-unique-identifier"
        isOpen={isModalOpen}
        transition={ModalTransition.SCALE}
      >
        <div class="modalWrapper">
          <Box title={props.title} style={{"margin-bottom": "0px !important"}} type="primary" footer={<a onClick={closeModal} class="clickable">Chiudi</a>}>
            <Col md={12} xs={12}>
                {props.childs.length==0 ? <p>Caricamento...</p> : props.childs.map(child => (<>{child}</>))}
            </Col>
          </Box>
        </div>
      </Modal>
    </>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(PopUp);