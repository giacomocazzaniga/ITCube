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
import { _FILTERS, _LICENZE } from './Constants';
import { ClientFilter } from './HierarchyManager';

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


const getSidebarByType = (client_list, nome_company, searched_client, category) =>{
  let filtered_client_list = ClientFilter(client_list, _FILTERS.CLIENT_TYPE);
  let lastComponent = [];
  {filtered_client_list.map((filtered_clients) => {
    lastComponent = [lastComponent, <Item icon="fa-map-marker-alt" text={filtered_clients.place+" ("+filtered_clients.filteredList.length+")"}>
      {category.map((cat) => {
        return (<Item icon="fa-users" text={cat.nome}>
          {client_list.map((item) => {
            return (item.sede==filtered_clients.place && item.tipo_client==cat.nome)
            ? (searched_client=="")
              ?
                (item.tipo_client=="Client")
                ? <Item icon={"fa-desktop"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} />
                : <Item icon={"fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> 
              :
                (item.tipo_client=="Client")
                ? (item.nome_client.toUpperCase().includes(searched_client.toUpperCase())) ? <Item icon={"fa-desktop"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
                : (item.nome_client.toUpperCase().includes(searched_client.toUpperCase())) ? <Item icon={"fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
            : <></>
          })}
        </Item>)
      })}
    </Item>]
  })}

  //WITHOUT PLACE
  /*{category.map((cat) => {
    lastComponent = [lastComponent, <Item icon="fa-users" text={cat.nome+" ("+cat.n_client+")"}>
      {client_list.map((item) => {
        if(searched_client==""){
          return (item.tipo_client===cat.nome) ? <Item icon={(item.tipo_client=="Client") ? "fa-desktop" : "fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
        }else{
          return (item.tipo_client===cat.nome && item.nome_client.toUpperCase().includes(searched_client.toUpperCase())) ? <Item icon={(item.tipo_client=="Client") ? "fa-desktop" : "fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
        }
  })}
    </Item>];
  })}*/
  return lastComponent;
}

const getSidebarByPlace = (client_list, nome_company, searched_client, place) =>{
  /*let lastComponent = [];
  let label = "";
  console.log(place)
  for(let i=0; i<place.length; i++){
    switch (place[i].nome) {
      case _LICENZE.SISTEMA_OPERATIVO.tipo: 
        label = _LICENZE.SISTEMA_OPERATIVO.label;
        break;
      case _LICENZE.BACKUP.tipo: 
        label = _LICENZE.BACKUP.label;
        break;
      case _LICENZE.ANTIVIRUS.tipo: 
        label = _LICENZE.ANTIVIRUS.label;
        break;
      case _LICENZE.RETE.tipo: 
        label = _LICENZE.RETE.label;
        break;
      case _LICENZE.VULNERABILITA.tipo: 
        label = _LICENZE.VULNERABILITA.label;
        break;
    }

    //WITHOUT PLACE
    lastComponent = [lastComponent, <Item icon="fa-users" text={label+" ("+place[i].n_client+")"}>
      {client_list.map((item) => {
        if(searched_client==""){
          return (item.classe_licenza===place[i].nome) ? <Item icon={(item.tipo_client=="Client") ? "fa-desktop" : "fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
        }else{
          return (item.classe_licenza===place[i].nome && item.nome_client.toUpperCase().includes(searched_client.toUpperCase())) ? <Item icon={(item.tipo_client=="Client") ? "fa-desktop" : "fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
        }
  })}
    </Item>];
  }
  return lastComponent;*/
  let label = [];
  for(let i=0; i<place.length; i++){
    switch (place[i].nome) {
      case _LICENZE.SISTEMA_OPERATIVO.tipo: 
        label[_LICENZE.SISTEMA_OPERATIVO.tipo] = _LICENZE.SISTEMA_OPERATIVO.label;
        break;
      case _LICENZE.BACKUP.tipo: 
        label[_LICENZE.BACKUP.tipo] = _LICENZE.BACKUP.label;
        break;
      case _LICENZE.ANTIVIRUS.tipo: 
        label[_LICENZE.ANTIVIRUS.tipo] = _LICENZE.ANTIVIRUS.label;
        break;
      case _LICENZE.RETE.tipo: 
        label[_LICENZE.RETE.tipo] = _LICENZE.RETE.label;
        break;
      case _LICENZE.VULNERABILITA.tipo: 
        label[_LICENZE.VULNERABILITA.tipo] = _LICENZE.VULNERABILITA.label;
        break;
    }
  }

  let filtered_client_list = ClientFilter(client_list, _FILTERS.CLIENT_TYPE);
  let lastComponent = [];
  {filtered_client_list.map((filtered_clients) => {
    lastComponent = [lastComponent, <Item icon="fa-map-marker-alt" text={filtered_clients.place+" ("+filtered_clients.filteredList.length+")"}>
      {place.map((p) => {
        return (<Item icon="fa-users" text={label[p.nome]}>
          {client_list.map((item) => {
            return (item.sede==filtered_clients.place && item.classe_licenza==p.nome)
            ? (searched_client=="")
              ?
                (item.tipo_client=="Client")
                ? <Item icon={"fa-desktop"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} />
                : <Item icon={"fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> 
              :
                (item.tipo_client=="Client")
                ? (item.nome_client.toUpperCase().includes(searched_client.toUpperCase())) ? <Item icon={"fa-desktop"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
                : (item.nome_client.toUpperCase().includes(searched_client.toUpperCase())) ? <Item icon={"fa-server"} key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} /> : <></>
            : <></>
          })}
        </Item>)
      })}
    </Item>]
  })}
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
        ? <AdminLTE title={[<FontAwesomeIcon icon={["fas", "home"]} />, " Home"]} homeTo={"/"+props.nome_company} titleShort={<FontAwesomeIcon icon={["fas", "home"]} />} theme="blue" sidebar={<><Item icon="fa-user-alt" key="-1" text="Account" to={"/"+props.nome_company} /><Searchbar onChange={handleChange} includeButton="true" placeholder="Cerca..." />{(props.category_vs_place) ? getSidebarByType(props.client_list, props.nome_company, props.searched_client, props.categories_list) : getSidebarByPlace(props.client_list, props.nome_company, props.searched_client, props.places_list)}</>}>
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
