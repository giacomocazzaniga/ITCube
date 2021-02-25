import React from 'react';
import { connect } from 'react-redux';
import { Box, Col } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Multiselect } from 'multiselect-react-dropdown';
import { addLicense, removeLicense } from '../ActionCreator';
import PopUp from './PopUp';


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
    options: [{name: 'Free', id: 0},{name: 'Premium', id: 1},{name: 'Pro', id: 2}],
    selectedValue: state.licensesList,
    licensesList: state.licensesList
  }
}

const LicensesList = (props) => {
  const onSelect = (selectedList, selectedItem) => {
    props.AddLicense(selectedList);
  }
  
  const onRemove = (selectedList, removedItem) => {
    props.RemoveLicense(selectedList);
  }
  return (
    <Box title={props.title} type="primary" collapsable footer={<span href="#" class="small-box-footer"><PopUp title="Gestione delle licenze" linkClass={"clickable"} childs={[]} action={()=>(console.log("action"))}/></span>}>
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
        return (JSON.stringify(props.licensesList).includes(license.nome_tipologia)) 
        ? <>
            {console.log(JSON.stringify(props.selectedValue)+" "+license.nome_tipologia+" "+license.codice)}
            <Col xs={8} md={6}>{license.codice}</Col>
            <Col xs={4} md={6}>{license.nome_tipologia}</Col>
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