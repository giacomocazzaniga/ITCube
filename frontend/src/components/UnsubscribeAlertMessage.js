import React, { useEffect, useState } from 'react'
import { _unsubscribeAlert } from '../callableRESTs';

const UnsubscribeAlertMessage = (props) => {

    const [unsubscribeParams, setUnsubscribeParams] = useState();
    const [requestResponse, setRequestResponse] = useState();

    useEffect(() => {
        let parameters = window.location.search
        let token = parameters.substring(parameters.indexOf("token=")+"token=".length, parameters.indexOf("&tipologia_alert"))
        let tipologia_alert = parameters.substring(parameters.indexOf("tipologia_alert=")+"tipologia_alert=".length, parameters.indexOf("&id_client"))
        let id_client = parameters.substring(parameters.indexOf("id_client=")+"id_client=".length, parameters.length)
        setUnsubscribeParams({
            token: token,
            tipologia_alert: tipologia_alert,
            id_client: id_client
        })
        _unsubscribeAlert(token,tipologia_alert,id_client)
        .then( response => {
            setRequestResponse(response.data.messageCode);
        })
        .catch( error => {

        })
    },[])

    return (
    <div>
    <table role="presentation" border="0" cellpadding="0" cellspacing="0" style={{"position":"absolute","width":"100%","height":"100%","backgroundColor":"antiquewhite"}}>
      <tr>
        <td>&nbsp;</td>
        <td class="container">
          <div class="content">
                <center><h1>ITSentinel</h1></center>
            <table role="presentation" class="main" style={{"margin":"auto"}}>
                <center>    
                    <tr>
                        <td class="wrapper">
                        <table role="presentation" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                            <td style={{"textAlign":"center"}}>
                            
                                    {(requestResponse==0)
                                    ?    
                                        <center><p>La disattivazione delle mail per {unsubscribeParams && unsubscribeParams.tipologia_alert.toLowerCase().replace("_", " ")} è andata a buon fine.</p></center>
                                    :
                                        <center><p>Impossibile disattivare le email per {unsubscribeParams && unsubscribeParams.tipologia_alert.toLowerCase().replace("_", " ")}. Token già utilizzato o non valido.</p></center>
                                    }

                                
                                <table role="presentation" border="0" cellpadding="0" cellspacing="0" class="btn btn-primary">
                                <tbody>
                                    <tr>
                                    <td align="center">
                                        <table style={{"margin": "0 auto;"}} role="presentation" border="0" cellpadding="0" cellspacing="0">
                                        <tbody>
                                            <tr>
                                            <td> <center><a href="http://217.160.240.146/" target="_blank" style={{"color":"white"}}>Vai alla dashboard</a></center> </td>
                                            </tr>
                                        </tbody>
                                        </table>
                                    </td>
                                    </tr>
                                </tbody>
                                </table>
                                <table style={{"margin":"auto"}}>
                                    <tbody>
                                        <tr>
                                            <td><center><small>{new Date().toUTCString()}</small></center></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                </center>
            </table>

          </div>
        </td>
        <td>&nbsp;</td>
      </tr>
    </table>
    </div>
    )
};

export default UnsubscribeAlertMessage;