import React, {useEffect} from 'react';
import { connect } from 'react-redux';
import { Col, Box } from 'adminlte-2-react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import PopUp from './PopUp';
import { _editCompanyData } from '../callableRESTs';
import { useToasts } from 'react-toast-notifications';

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({});

/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => {
  return {
    client_list: state.client_list,
    nome_company: state.nome_company,
    token: state.token,
    logged: state.logged,
    id_company: state.id_company,
    emailNotify: state.emailNotify,
    email: state.email
  }
}

const UserData = (props) => {
  const { addToast } = useToasts();

  const clickService = () => {
    return _editCompanyData(props.id_company, props.token, props.email, props.emailNotify, props.nome_company)
    .then(function (response) {
        alert(response.data.message);
    })
    .catch(function (error) {
      addToast(error, {appearance: 'error',autoDismiss: true});
    });
  }

  const [state, setState] = React.useState({
    emailLogin: "",
    emailLogin2: "",
    pswLogin: ""
  })

  /**
   * handle email login and map it to local state emailLogin
   * @param {*} evt 
   */
  const handleEmailLogin = (evt) => {
    setState((previousState) => {
      return { ...previousState, emailLogin: evt.target.value };  
    });
  }

  /**
   * handle email login and map it to local state emailLogin
   * @param {*} evt 
   */
    const handleEmailLogin2 = (evt) => {
    setState((previousState) => {
      return { ...previousState, emailLogin2: evt.target.value };  
    });
  }

  /**
   * handle password login and map it to local state pswLogin
   * @param {*} evt 
   */
  const handlePswLogin = (evt) => {
    setState((previousState) => {
      return { ...previousState, pswLogin: evt.target.value };
    });
  }
  
  const getChilds = () => {
    let childList = [];
    childList = [
    <>
      <form>
        <div class="form-group">
          <label for="LoginEmail1">Indirizzo email</label>
          <input type="email" value={state.emailLogin} class="form-control" id="LoginEmail1" aria-describedby="emailHelp" placeholder={props.email} onChange={handleEmailLogin}/>
        </div>
        <div class="form-group">
          <label for="LoginEmail2">Indirizzo email per le comunicazioni</label>
          <input type="email" value={state.emailLogin2} class="form-control" id="LoginEmail2" aria-describedby="emailHelp" placeholder={props.emailNotify} onChange={handleEmailLogin2}/>
        </div>
        <div class="form-group">
          <label for="LoginPassword1">Ragione Sociale</label>
          <input value={state.pswLogin} class="form-control" id="LoginPassword1" placeholder={props.ragioneSociale} onChange={handlePswLogin} />
        </div>
      </form>
      <button class="btn btn-primary" onClick={() => clickService()}>Modifica</button>
    </>
    ]
    return childList;
  }

  return (
    <Box title="Dati dell'utente" type="primary" collapsable footer={<span href="#" class="small-box-footer"><PopUp title="Dati dell'utente" linkClass={"clickable"} childs={getChilds()} action={()=>(console.log("action"))}/></span>}>
      <Col md={12} xs={12}>
        <h4><b>Indirizzo email: </b>{props.email}</h4>
        <h4><b>Indirizzo email per le comunicazioni: </b>{props.emailNotify}</h4>
        <h4><b>Ragione sociale: </b>{props.ragioneSociale}</h4>
      </Col>
    </Box>
  );
}
export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(UserData);