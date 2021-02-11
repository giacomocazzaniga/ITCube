import React, { Component } from 'react';
import { ToastProvider } from 'react-toast-notifications';
import AdminLTE, { Sidebar } from 'adminlte-2-react';
import { connect } from 'react-redux';
import Dashboard from './components/Dashboard';
import LoginPage from './components/LoginPage';
import SignUpPage from './components/SignUpPage';


/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps =  dispatch => {
}


/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => {
  return {
    client_list: state.client_list,
    company_id: state.company_id,
    logged: state.logged
  }
}

const { Item } = Sidebar;

const getSidebar = (client_list, company_id) =>{
  let item_list = [];
  client_list.map((item) => (item_list = [item_list, <Item key={item.id} text={item.name} to={"/company"+company_id+"user"+item.id} />]));
  return item_list;
}

const getSidebarUnlogged = () =>{
  let item_list = [];
  item_list = [ <Item key="1" text="Accedi" to="/accedi" />, 
                <Item key="2" text="Registrati" to="/registrati" />];
  return item_list;
}

const App = (props) => {
  return (
    <ToastProvider>
      {props.logged==true 
      ? <AdminLTE title={[props.company_id]} titleShort={props.company_id} theme="blue" sidebar={getSidebar(props.client_list, props.company_id)}>
          {props.client_list.map((item) => <Dashboard path={"/company"+props.company_id+"user"+item.id} title={item.name} />)}
        </AdminLTE>
      : <AdminLTE title={["nome progetto"]} theme="blue" sidebar={getSidebarUnlogged()}>
          <ToastProvider path="/accedi" title="Accedi"><LoginPage /></ToastProvider>
          <ToastProvider path="/registrati" title="Registrati"><SignUpPage /></ToastProvider>
        </AdminLTE>
      }
      
    </ToastProvider>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(App);
