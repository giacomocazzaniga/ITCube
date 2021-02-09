import React from 'react';
import { connect } from 'react-redux';
import { login } from '../ActionCreator';
import DashboardWrap from './DashboardWrap';

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps = dispatch => ({
  Login: (email, password) => {
    //control email-psw on database and retrieve the company name
    let company_name = "ITCube Consulting";
    dispatch(login(company_name))
  }
})


/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => {
  return {
    company_id: state.company_id
  }
}

/**
 * sign in/sign up form
 * @param {*} props 
 */
const LoginPage = (props) => {
  const [state, setState] = React.useState({
    emailLogin: "",
    pswLogin: ""
  })

  /**
   * handle email login and map it to local state emailLogin
   * @param {*} evt 
   */
  const handleEmailLogin = (evt) => {
    setState({ emailLogin: evt.target.value });
  }

  /**
   * handle password login and map it to local state pswLogin
   * @param {*} evt 
   */
  const handlePswLogin = (evt) => {
    console.log(evt.target.value);
    setState({ pswLogin: evt.target.value });
  }
  return (
    (props.company_id===null) 
    ?
      <div class="container">
        <div class="row">
          <div class="col-md-5">
            <form>
              <div class="form-group">
                <label for="LoginEmail1">Indirizzo email</label>
                <input type="email" value={state.emailLogin} class="form-control" id="LoginEmail1" aria-describedby="emailHelp" placeholder="Email" onChange={handleEmailLogin}/>
              </div>
              <div class="form-group">
                <label for="LoginPassword1">Password</label>
                <input type="password" value={state.pswLogin} class="form-control" id="LoginPassword1" placeholder="Password" onChange={handlePswLogin} />
              </div>
              <button class="btn btn-primary" onClick={() => props.Login(state.emailLogin, state.pswLogin)}>Login</button>
            </form>
          </div>
          <div class="col-md-5 col-md-offset-2">
            <form>
              <div class="form-group">
                <label for="exampleInputEmail1">Indirizzo email</label>
                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email" />
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" />
              </div>
              <div class="form-group">
                <label for="exampleInputEmail2">Indirizzo email per le comunicazioni</label>
                <input type="email" class="form-control" id="exampleInputEmail2" aria-describedby="emailHelp" placeholder="Email" />
              </div>
              <div class="form-group">
                <label for="exampleInputRagioneSociale">Ragione Sociale</label>
                <input type="email" class="form-control" id="exampleInputRagioneSociale" aria-describedby="emailHelp" placeholder="Ragione Sociale" />
              </div>
              <button type="submit" class="btn btn-primary">Registrati</button>
            </form>
          </div>
        </div>
      </div>
    :
      <DashboardWrap />
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(LoginPage);
