import React, { Component } from 'react';
import { ToastProvider } from 'react-toast-notifications';
import AdminLTE, { Sidebar } from 'adminlte-2-react';
import { connect } from 'react-redux';
import Dashboard from './components/Dashboard';
import LoginPage from './components/LoginPage';
import SignUpPage from './components/SignUpPage';
import DashboardHome from './components/DashboardHome';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { placesList, searchClient } from './ActionCreator';
import { ModalProvider } from 'react-simple-hook-modal';


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
    searched_client: state.searched_client,
    categories_list: state.categories_list,
    places_list: state.places_list,
    category_vs_place: state.category_vs_place
  }
}

const { Item, Searchbar } = Sidebar;

const getSidebar = (client_list, nome_company) =>{
  let item_list = [];
  client_list.map((item) => (item_list = [item_list, <Item icon="fa-desktop" key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} />]));
  return item_list;
}


const getSidebarByCategory = (client_list, nome_company, searched_client, category) =>{
  let lastComponent = [];
  for(let i=0; i<category.length; i++){
    lastComponent = [lastComponent, <Item icon="fa-users" text={category[i].nome+" ("+category[i].n_client+")"}>
      {client_list.map((item) => {
        if(searched_client==""){
          return (item.category===category[i].nome) ? <Item icon={(item.tipo_client=="Client") ? "fa-desktop" : "fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
        }else{
          return (item.category===category[i].nome && item.nome_client.toUpperCase().includes(searched_client.toUpperCase())) ? <Item icon={(item.tipo_client=="Client") ? "fa-desktop" : "fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
        }
  })}
    </Item>];
  }
  return lastComponent;
}

const getSidebarByPlace = (client_list, nome_company, searched_client, place) =>{
  let lastComponent = [];
  for(let i=0; i<place.length; i++){
    lastComponent = [lastComponent, <Item icon="fa-map-marker-alt" text={place[i].nome+" ("+place[i].n_client+")"}>
      {client_list.map((item) => {
        if(searched_client==""){
          return (item.sede===place[i].nome) ? <Item icon={(item.tipo_client=="Client") ? "fa-desktop" : "fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
        }else{
          return (item.sede===place[i].nome && item.nome_client.toUpperCase().includes(searched_client.toUpperCase())) ? <Item icon={(item.tipo_client=="Client") ? "fa-desktop" : "fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
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
    <ModalProvider>
      <ToastProvider>
        {props.logged==true 
        ? <AdminLTE title={[<FontAwesomeIcon icon={["fas", "home"]} />, " Home"]} homeTo={"/"+props.nome_company} titleShort={<FontAwesomeIcon icon={["fas", "home"]} />} theme="blue" sidebar={<><Item icon="fa-user-alt" key="-1" text="Account" to={"/"+props.nome_company} /><Searchbar onChange={handleChange} includeButton="true" placeholder="Cerca..." />{(props.category_vs_place) ? getSidebarByCategory(props.client_list, props.nome_company, props.searched_client, props.categories_list) : getSidebarByPlace(props.client_list, props.nome_company, props.searched_client, props.places_list)}</>}>
            <DashboardHome path={"/"+props.nome_company} title={props.nome_company} />
            {props.client_list.map((item) => <Dashboard path={"/company"+props.nome_company+"user"+item.id_client} client={item} title={item.nome_client} />)}
          </AdminLTE>
        : <AdminLTE title={["nome progetto"]} theme="blue" sidebar={getSidebarUnlogged()}>
            <ToastProvider path="/accedi" title="Accedi"><LoginPage /></ToastProvider>
            <ToastProvider path="/registrati" title="Registrati"><SignUpPage /></ToastProvider>
          </AdminLTE>
        }
      </ToastProvider>
    </ModalProvider>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(App);
