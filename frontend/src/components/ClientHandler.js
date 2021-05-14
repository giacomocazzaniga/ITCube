import React, { useState } from "react";
import { Box, Col, Slider } from "adminlte-2-react";
import { connect } from "react-redux";
import PopUp from "./PopUp";
import Collapsible from "react-collapsible";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { _getAllNomiAlertConfigurazione, _getAllServicesOfCompany } from "../callableRESTs";
import { getErrorToast, getLoadingToast, stopLoadingToast } from "../toastManager";
import { Row, Carousel } from "react-bootstrap";
import ClientHandlerRowServices from "./ClientHandlerRowServices";
import ClientHandlerRowAlert from "./ClientHandlerRowAlert";


/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
 const mapDispatchToProps = dispatch => ({});

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({
    token: state.token,
    id_company: state.id_company,
    lista_nomi_sedi: state.lista_nomi_sedi
});

const ClientHandler = (props) => {

    const [childs, setChilds] = useState([]);


    const openToggle = (id) => {
        var elem = document.getElementsByClassName("arrowAccordion"+id)[0];
        elem.style.transform = "rotate(90deg)";
        elem.style.transition = "transform 1s ease";
        elem.style.display = "inline-block";
    }
    
    const closeToggle = (id) => {
        var elem = document.getElementsByClassName("arrowAccordion"+id)[0];
        elem.style.transform = "rotate(0deg)";
        elem.style.transition = "transform 1s ease";
        elem.style.display = "inline-block";
    }

    const getClientHandler = () => {
        const loadingToast = getLoadingToast("Modifica monitoraggio servizi...");
        _getAllServicesOfCompany(props.id_company, props.token)
        .then( response1 => {
            _getAllNomiAlertConfigurazione(props.id_company, props.token)
            .then( response2 => {
                setChilds(renderChilds(response1.data.nomi_servizi,response2.data.tipologie_alert));
                stopLoadingToast(loadingToast);
            })
            .catch( error => {
                stopLoadingToast(loadingToast);
                getErrorToast(String(error));
            })
        })
        .catch( error => {
            stopLoadingToast(loadingToast);
            getErrorToast(String(error));
        })
    }

    const renderChilds = (nomi_servizi, tipologie_alert) => {
        const fields = ["Alert","Servizi di Windows"];
        const intestazione = fields.map( (field,j) => {
            return (
                <div className="popminwidth">
                    <Collapsible onOpen={()=>openToggle(j)} onClose={()=>closeToggle(j)} trigger={<div className="clickable"><h4><span className={"arrowAccordion"+j}><FontAwesomeIcon icon={["fas", "chevron-right"]} /></span> {field} </h4></div>}>
                        <Row>
                            {(field=="Alert")
                            ?   <Col xs={2} md={2}><strong><h5>ALERT</h5></strong></Col>
                            :   <Col xs={2} md={2}><strong><h5>SERVIZIO</h5></strong></Col>
                            }    
                            <><Col xs={2} md={2}><strong><h5>TIPOLOGIA</h5></strong></Col><Col xs={2} md={2}><strong><h5>LICENZA</h5></strong></Col><Col xs={2} md={2}><strong><h5>SEDE</h5></strong></Col></>
                        </Row>
                            {(field=="Servizi di Windows") 
                            ?
                                nomi_servizi.map( (nome_servizio,i) => {
                                    return <ClientHandlerRowServices nome_servizio={nome_servizio} index={i}/>
                                })
                            :
                                tipologie_alert.map( (tipologia_alert,i) => {
                                    return <ClientHandlerRowAlert tipologia_alert={tipologia_alert} index={i}/>
                                })
                            }
                            
                    </Collapsible>
                </div>
            )
        })
        return intestazione
    }

    return (
        <Box title="Gestione di:" type="primary" collapsable footer={<PopUp title="Gestione dei client" linkClass={"clickable"} childs={childs} action={()=>getClientHandler()}/>}>
            <h4 className="boxBody">-Alert</h4>
            <h4 className="boxBody">-Monitoring dei servizi di Windows</h4>
        </Box>
    )
} 

export default connect(
    mapStateToProps,
    mapDispatchToProps,
  )(ClientHandler);