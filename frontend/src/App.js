import React, { Component } from 'react';
import { ToastProvider } from 'react-toast-notifications';
import AdminLTE, { Sidebar } from 'adminlte-2-react';
import { connect } from 'react-redux';
import Dashboard from './components/Dashboard';
import LoginPage from './components/LoginPage';
import SignUpPage from './components/SignUpPage';
import DashboardHome from './components/DashboardHome';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { searchClient } from './ActionCreator';


/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps =  dispatch => {
  return{
    Search: (nome_client) => {
      dispatch(searchClient(nome_client))
    }
  }
}


/**
 * connect the redux state to the component
 * @param {*} state 
 */
const mapStateToProps = state => {
  return {
    client_list: state.client_list,
    nome_company: state.nome_company,
    logged: state.logged,
    searched_client: state.searched_client
  }
}

const { Item, Header, Searchbar } = Sidebar;

const getSidebar = (client_list, nome_company) =>{
  let item_list = [];
  client_list.map((item) => (item_list = [item_list, <Item icon="fa-desktop" key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} />]));
  return item_list;
}

const getSidebarByCategory = (client_list, nome_company, searched_client) =>{
  let lastComponent = [];
  var workGroups = client_list.map( (value) => value.category).filter( (value, index, _arr) => _arr.indexOf(value) == index);
  for(let i=0; i<workGroups.length; i++){
    lastComponent = [lastComponent, <Item icon="fa-users" text={workGroups[i]}>
      {client_list.map((item) => {
        //return (item.category===workGroups[i]) ? <Item icon={(item.tipo_client=="Client") ? "fa-desktop" : "fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
        if(searched_client==""){
          return (item.category===workGroups[i]) ? <Item icon={(item.tipo_client=="Client") ? "fa-desktop" : "fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
        }else{
          return (item.category===workGroups[i] && item.nome_client.toUpperCase().includes(searched_client.toUpperCase())) ? <Item icon={(item.tipo_client=="Client") ? "fa-desktop" : "fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
        }
  })}
    </Item>];
  }
  return lastComponent;
}

const getSidebarUnlogged = () =>{
  let item_list = [];
  item_list = [ <Item icon="fa-sign-in-alt" key="1" text="Accedi" to="/accedi"/>, 
                <Item icon="fa-user-plus" key="2" text="Registrati" to="/registrati" />];
  return item_list;
}

const App = (props) => {
  const handleChange = event => {
    props.Search(event.target.value);
  }
  return (
    <ToastProvider>
      {props.logged==true 
      ? <AdminLTE title={[<FontAwesomeIcon icon={["fas", "home"]} />, " Home"]} homeTo={"/"+props.nome_company} titleShort={<FontAwesomeIcon icon={["fas", "home"]} />} theme="blue" sidebar={<><Item icon="fa-user-alt" key="-1" text="Account" to={"/"+props.nome_company} /><Searchbar onChange={handleChange} includeButton="true" placeholder="Cerca..." />{getSidebarByCategory(props.client_list, props.nome_company, props.searched_client)}</>}>
          <DashboardHome path={"/"+props.nome_company} title={props.nome_company} />
          {props.client_list.map((item) => <Dashboard path={"/company"+props.nome_company+"user"+item.id_client} client={item} title={item.nome_client} />)}
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
