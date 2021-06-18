import React, { useEffect, useState } from "react"
import { Col } from "adminlte-2-react";
import { connect } from "react-redux";
import { _getClientOfTier } from "../callableRESTs";
import { getErrorToast, getLoadingToast, stopLoadingToast } from "../toastManager";
/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
 const mapDispatchToProps = dispatch => ({
 });
    
  /**
   * connect the redux state to the component
   * @param {*} state 
   */
  const mapStateToProps = state => ({
      categories_list: state.categories_list,
      token: state.token,
      id_company: state.id_company
  });

export const ClientOfTier = (props) => {

    const [clients, setClients] = useState([]);

    useEffect(() => {
        const loadingToast = getLoadingToast("Caricamento...");
        _getClientOfTier(props.token,props.category,props.id_company)
        .then( response => {
            console.log(response.data.clientsOfTier)
            setClients(response.data.clientsOfTier)
            stopLoadingToast(loadingToast);
        })
        .catch(function (error) {
            stopLoadingToast(loadingToast);
            getErrorToast(String(error));
        });
    },[props.state])

    const selectClient = (e) => {
        if(e.target.checked) {
            props.setState({
                ...props.state,
                clientsToMove: [...props.state.clientsToMove, e.target.value]
            });
        } else {
            props.setState({
                ...props.state,
                clientsToMove: props.state.clientsToMove.filter( (client) => { if(client!=e.target.value) {return client} })
            })
        }
    }

    return (
        <div>
            {clients && clients.map( (client,i) => {
                return (
                    <Col key={i} md={4} sm={4}>
                        <label>{client.nome}</label>
                        <input value={client.id} type="checkbox" onChange={selectClient} />
                    </Col>
                )
            })}
        </div>
    )
} 

export default connect(
    mapStateToProps,
    mapDispatchToProps,
  )(ClientOfTier);