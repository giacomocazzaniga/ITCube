import React, { useEffect, useState } from "react"
import { connect } from "react-redux";
import { Box } from "adminlte-2-react";
import { _changeMonitoraClient } from "../callableRESTs"
import { getErrorToast, getLoadingToast, getSuccessToast, stopLoadingToast } from "../toastManager";
import { getConfigurazioneAlert } from "../ActionCreator";

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
 const mapDispatchToProps = (dispatch) => ({
    GetConfigurazioneAlert: (list) => {
        dispatch(getConfigurazioneAlert(list));
    }
  });
  
  /**
   * connect the redux state to the component
   * @param {*} state 
   */
  const mapStateToProps = state => ({
    token: state.token,
  });

const NotifyToggle = (props) => {

    const changeNotifiche = (status) => {
        const loadingToast = getLoadingToast("Attendere...")
        _changeMonitoraClient(props.token,props.id_client,status)
        .then( response => {
            stopLoadingToast(loadingToast);
            status ? getSuccessToast("Notifiche abilitate") : getSuccessToast("Notifiche disabilitate");
        })
        .catch(function (error) {
            stopLoadingToast(loadingToast);
            getErrorToast(String(error));
          })
    }

    return (
        <Box title="Monitoraggio Client" type="primary" collapsable>
            <label>Disabilita notifiche del client</label>
            <button className="btn btn-primary" onClick={() => changeNotifiche(false)} >Disabilita</button>
            <label>Abilita notifiche del client</label>
            <button className="btn btn-primary" onClick={() => changeNotifiche(true)}>Abilita</button>
        </Box>
    )
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(NotifyToggle);