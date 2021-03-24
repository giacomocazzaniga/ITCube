import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Multiselect } from 'multiselect-react-dropdown';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';
import { addLicense, removeLicense } from '../ActionCreator';
import PopUp from './PopUp';
import { _LICENZE } from '../Constants';


/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => {
  return{
    AddLicense: (license) => {
      dispatch(addLicense(license))
    },
    RemoveLicense: (license) => {
      dispatch(removeLicense(license))
    }
  }
}

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => {
  return {
    options: [{name: _LICENZE.SISTEMA_OPERATIVO.label, id: _LICENZE.SISTEMA_OPERATIVO.tipo},
              {name: _LICENZE.BACKUP.label, id: _LICENZE.BACKUP.tipo},
              {name: _LICENZE.RETE.label, id: _LICENZE.RETE.tipo},
              {name: _LICENZE.VULNERABILITA.label, id: _LICENZE.VULNERABILITA.tipo},
              {name: _LICENZE.ANTIVIRUS.label, id: _LICENZE.ANTIVIRUS.tipo}
    ],
    selectedValue: state.licensesList,
    licensesList: state.licensesList
  }
}

const isOdd = (num) => { return ((num % 2)==1) ? true : false }

const LicensesList = (props) => {

  useEffect( () => {
    //get list of licenses types

    /*return _service()
    .then(function (response) {
        //addToast(String(response.data.message), {appearance: 'success',autoDismiss: true});
    })
    .catch(function (error) {
      addToast(String(error), {appearance: 'error',autoDismiss: true});
    });*/
  }, []);

  const [state, setState] = React.useState({
    selectedValue: 0
  })

  const clickService = () => {
    console.log(state.selectedValue);
    //buy new license

    /*return _service()
    .then(function (response) {
        //addToast(String(response.data.message), {appearance: 'success',autoDismiss: true});
    })
    .catch(function (error) {
      addToast(String(error), {appearance: 'error',autoDismiss: true});
    });*/
  }

  const _onSelect = (e) => {
    setState(() => {
      return {selectedValue: e.value };  
    });
  }

  const getChilds = (list) => {
    let childList = [];
    let options = [
      { value: '1', label: 'SISTEMA OPERATIVO' },
      { value: '2', label: 'BACKUP' },
      { value: '3', label: 'ANTIVIRUS' },
      { value: '4', label: 'RETE' },
      { value: '5', label: 'VUNERABILITÀ' },
    ];
    let defaultOption = options[0];
    childList = [
    <>
      <Col xs={8} md={6}><strong><h4>CODICE LICENZA</h4></strong></Col>
      <Col xs={4} md={6}><strong><h4>CATEGORIA</h4></strong></Col>
      {props.list.map((license, i) => {
        return (isOdd(i)) 
        ? <><Col xs={8} md={6} class="oddColor col-md-6 col-xs-8">{license.codice}</Col><Col class="oddColor col-md-6 col-xs-4" xs={4} md={6}>{license.tipologia}</Col></>
        : <><Col xs={8} md={6} class="evenColor col-md-6 col-xs-8">{license.codice}</Col><Col class="evenColor col-md-6 col-xs-4" xs={4} md={6}>{license.tipologia}</Col></>
      })
      }
      <Col xs={12} md={12}><p></p></Col>
      <Col xs={8} md={6}>
        {state.selectedValue==0
          ? <button class="btn btn-primary" onClick={() => clickService()} disabled>Acquista licenza</button>
          : <button class="btn btn-primary" onClick={() => clickService()}>Acquista licenza</button>
        }
      </Col>
      <Col xs={4} md={6}>
        <Dropdown options={options} onChange={_onSelect} placeholder="Seleziona una tipologia" />
      </Col>
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
    <Box title={props.title} type="primary" collapsable footer={<span href="#" class="small-box-footer"><PopUp title="Gestione delle licenze" linkClass={"clickable"} childs={getChilds(props.list)} action={()=>(console.log("action"))}/></span>}>
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
      {props.list.map((license) => {
        return (JSON.stringify(props.licensesList).toUpperCase().includes(license.tipologia.toUpperCase())) 
        ? <>
            {console.log(JSON.stringify(props.selectedValue)+" "+license.tipologia+" "+license.codice)}
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