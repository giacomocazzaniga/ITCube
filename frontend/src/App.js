import React, { Component } from 'react';
import { ToastProvider } from 'react-toast-notifications';
import AdminLTE, { Sidebar } from 'adminlte-2-react';
import { connect } from 'react-redux';
import Dashboard from './components/Dashboard';
import LoginPage from './components/LoginPage';
import SignUpPage from './components/SignUpPage';
import DashboardHome from './components/DashboardHome';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import DashboardAccount from './components/DashboardAccount';


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
    nome_company: state.nome_company,
    logged: state.logged
  }
}

const { Item, Header, Searchbar } = Sidebar;

const getSidebar = (client_list, nome_company) =>{
  let item_list = [];
  client_list.map((item) => (item_list = [item_list, <Item icon="fa-desktop" key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} />]));
  return item_list;
}

const getSidebarUnlogged = () =>{
  let item_list = [];
  item_list = [ <Item icon="fa-sign-in-alt" key="1" text="Accedi" to="/accedi"/>, 
                <Item icon="fa-user-plus" key="2" text="Registrati" to="/registrati" />];
  return item_list;
}

const App = (props) => {
  return (
    <ToastProvider>
      {props.logged==true 
      ? <AdminLTE title={[<FontAwesomeIcon icon={["fas", "home"]} />, " Home"]} homeTo={"/"+props.nome_company} titleShort={<FontAwesomeIcon icon={["fas", "home"]} />} theme="blue" sidebar={<><Item icon="fa-user-alt" key="-1" text="Account" to={"/"+props.nome_company} /><Header text="Lista dei client"></Header><Searchbar includeButton="true" placeholder="Cerca..." />{getSidebar(props.client_list, props.nome_company)}</>}>
          <DashboardHome path={"/"+props.nome_company} title={props.nome_company} />
          <DashboardAccount path={"/account"} title="Account" />
          {props.client_list.map((item) => <Dashboard path={"/company"+props.nome_company+"user"+item.id_client} title={item.nome_client} MAC_address={item.MAC_address} codice_licenza={item.codice_licenza} nome_tipologia_licenza={item.nome_tipologia_licenza}/>)}
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