import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React from 'react';
import { connect } from 'react-redux';
import { Modal, useModal, ModalTransition } from 'react-simple-hook-modal';
import { Col, Box } from 'adminlte-2-react';
import { Accordion } from 'react-bootstrap';


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
      setTimeout(function(){ 
        try {
          document.getElementsByClassName("modalWrapper")[0].style.marginTop = parseInt(window.scrollY)+"px";
        } catch (error) {
          console.error(error);
        }
      }, 100);
      window.onscroll = function (e) {
        var vertical_position = 0;
        if (window.pageYOffset)//usual
          vertical_position = window.pageYOffset;
        else if (document.documentElement.clientHeight)//ie
          vertical_position = document.documentElement.scrollTop;
        else if (document.body)//ie quirks
          vertical_position = document.body.scrollTop;
        try {
          document.getElementsByClassName("modalWrapper")[0].style.marginTop = parseInt(window.scrollY)+"px";
        } catch (error) {
          console.error(error);
        }
      }      
    };
  }
  return (
    <>
      {props.messageLink==null ? <a onClick={launchAction()} className={props.linkClass}>Altro <FontAwesomeIcon icon={["fas", "arrow-circle-right"]} /></a>
      : <a onClick={launchAction()} className={props.linkClass}>{props.messageLink}</a>
      }
      <Modal
        id="any-unique-identifier"
        isOpen={isModalOpen}
        transition={ModalTransition.SCALE}
      >
        <div className="modalWrapper">
          <Box title={props.title} style={{"marginBottom": "0px !important"}} type="primary" footer={<a onClick={closeModal} className="clickable">Chiudi</a>}>
            <Col className="col-md-12 col-xs-12">
              <Accordion>
                {props.childs.length==0 ? <p>Nessun dato trovato.</p> : props.childs.map((child, i) => (<div key={i}>{child}</div>))}
              </Accordion>
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