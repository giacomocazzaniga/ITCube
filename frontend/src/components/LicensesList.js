import React, { useEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
import { Multiselect } from 'multiselect-react-dropdown';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';
import { addLicense, removeLicense, totalReset, updateCompanyTemplateLicenze, updateToken } from '../ActionCreator';
import PopUp from './PopUp';
import { _LICENZE } from '../Constants';
import { getErrorToast, getLoadingToast, getSuccessToast, stopLoadingToast } from '../toastManager';
import { _compraLicenza, _getLicenzeShallow } from '../callableRESTs';
import { Row } from 'react-bootstrap';
import AssignLicenses from "./AssignLicenses";
import { autenticazione_fallita, renewToken } from '../Tools';


/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({
    AddLicense: (license) => {
      dispatch(addLicense(license))
    },
    RemoveLicense: (license) => {
      dispatch(removeLicense(license))
    },
    CompanyTemplateLicenze: (lista_licenze) => {
      dispatch(updateCompanyTemplateLicenze(lista_licenze))
    },
    TotalReset: () => {
      dispatch(totalReset());
    },
    UpdateToken: (token) => {
      dispatch(updateToken(token));
    }
  }
);

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => ({
    options: [{name: _LICENZE.SISTEMA_OPERATIVO.label, id: _LICENZE.SISTEMA_OPERATIVO.tipo},
              {name: _LICENZE.BACKUP.label, id: _LICENZE.BACKUP.tipo},
              {name: _LICENZE.RETE.label, id: _LICENZE.RETE.tipo},
              {name: _LICENZE.VULNERABILITA.label, id: _LICENZE.VULNERABILITA.tipo},
              {name: _LICENZE.ANTIVIRUS.label, id: _LICENZE.ANTIVIRUS.tipo}
    ],
    selectedValue: state.licensesList,
    licensesList: state.licensesList,
    token: state.token,
    id_company: state.id_company,
    company_template: state.company_template,
    client_list: state.client_list
  }
);

const isOdd = (num) => { return ((num % 2)==1) ? true : false }

const LicensesList = (props) => {

  useEffect( () => {
    //get list of licenses types
    //const loadingToast = getLoadingToast("Caricamento...");
    /*return _service()
    .then(function (response) {
      stopLoadingToast(loadingToast);
      getSuccessToast(response.data.message);
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });*/
  }, []);

  const [state, setState] = React.useState({
    selectedValue: 0,
    selectedLicense: []
  })

  const clickService = () => {
    console.log(state.selectedValue);
    let continueUpdating = true;
    //buy new license
    const loadingToast = getLoadingToast("Acquistando la licenza...");
    return _compraLicenza(props.token, props.id_company, state.selectedValue)
    .then(function (response) {

      if(autenticazione_fallita(response.data.messageCode)) {
        stopLoadingToast(loadingToast);
        getErrorToast("Sessione scaduta");
        props.TotalReset();
        continueUpdating = false;
      }
      if(continueUpdating != false) {
        let token= props.token;
        if(renewToken(props.token, response.data.token)){
          props.UpdateToken(response.data.token);
          token = response.data.token;
        }

        getSuccessToast(response.data.message);
        //getLicenze
        _getLicenzeShallow(props.id_company, token)
        .then(function (response) {
          props.CompanyTemplateLicenze(response.data.licenzeShallow)
          stopLoadingToast(loadingToast);
        })
        .catch(function (error) {
          stopLoadingToast(loadingToast);
          getErrorToast(String(error));
        })
      }
    })
    .catch(function (error) {
      stopLoadingToast(loadingToast);
      getErrorToast(String(error));
    });
  }

  const _onSelect = (e) => {
    setState(() => {
      return {...state,selectedValue: e.value };  
    });
  }

  const getChilds = () => {
    let childList = [];
    let options = [
      { value: '1', label: 'SISTEMA OPERATIVO' },
      { value: '2', label: 'BACKUP' },
      { value: '3', label: 'ANTIVIRUS' },
      { value: '4', label: 'RETE' },
      { value: '5', label: 'VUNERABILITA\'' },
    ];
    childList = [
    <>
      <Col xs={8} md={6}><strong><h4>CODICE LICENZA</h4></strong></Col>
      <Col xs={4} md={6}><strong><h4>CATEGORIA</h4></strong></Col>
      {props.company_template.licensesList.map((license, i) => {
        return (isOdd(i)) 
        ? <><Col xs={8} md={6} className="oddColor vertical-aligner col-md-6 col-xs-8">{license.codice}</Col><Col className="oddColor vertical-aligner col-md-6 col-xs-4" xs={4} md={6} key={i}>{license.tipologia}</Col></>
        : <><Col xs={8} md={6} className="evenColor vertical-aligner col-md-6 col-xs-8">{license.codice}</Col><Col className="evenColor vertical-aligner col-md-6 col-xs-4" xs={4} md={6} key={i}>{license.tipologia}</Col></>
      })
      }
      <Col xs={12} md={12}><p></p></Col>
      <Col xs={8} md={6}>
        {state.selectedValue==0
          ? <button className="btn btn-primary" onClick={() => clickService()} disabled>Acquista licenza</button>
          : <button className="btn btn-primary" onClick={() => clickService()}>Acquista licenza</button>
        }
      </Col>
      <Col xs={4} md={6}>
        <Dropdown options={options} onChange={_onSelect} placeholder="Seleziona una tipologia" />
      </Col>

      <Col xs={12} md={12}><strong><h4>ASSEGNA LICENZE</h4></strong></Col>
      <Row>
        <Col xs={3} md={3}><h4>NOME CLIENT</h4></Col>
        <Col xs={4} md={4}><h4>LICENZE IN USO</h4></Col>
      </Row>


      {props.client_list.map( (client,i) => {        
        return(
          <AssignLicenses key={i} idx={i} client={client} options={options} />
        )
      })}
    </>
    ]
    return childList;
  }

  const onSelect = (selectedList, selectedItem) => {
    props.AddLicense(selectedList);
  }
  
  const onRemove = (selectedList, removedItem) => {
    props.RemoveLicense(selectedList);
  }

  

  return (
    <Box title={props.title} type="primary" collapsable footer={<span href="#" className="small-box-footer"><PopUp title="Gestione delle licenze" linkClass={"clickable"} childs={getChilds()} action={()=>(console.log("action"))}/></span>}>
      <Multiselect
        placeholder="Filtra per tipologia"
        emptyRecordMsg="Nessun filtro disponibile"
        hidePlaceholder="true"
        closeIcon="cancel"
        options={props.options} // Options to display in the dropdown
        selectedValues={props.selectedValue} // Preselected value to persist in dropdown
        onSelect={onSelect} // Function will trigger on select event
        onRemove={onRemove} // Function will trigger on remove event
        displayValue="name" // Property name to display in the dropdown options
      />
      <br />
      {props.company_template.licensesList.map((license) => {
        return (JSON.stringify(props.licensesList).toUpperCase().includes(license.tipologia.toUpperCase())) 
        ? <>
            {/* {console.log(JSON.stringify(props.selectedValue)+" "+license.tipologia+" "+license.codice)} */}
            <Col xs={8} md={6}>{license.codice}</Col>
            <Col xs={4} md={6}>{license.tipologia}</Col>
          </>
        : <></>
      })
      }
      {(JSON.stringify(props.licensesList)==="[]")
        ? <Col xs={12}>Nessuna licenza trovata in base ai filtri selezionati.</Col>
        : <></>
      }     
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(LicensesList);