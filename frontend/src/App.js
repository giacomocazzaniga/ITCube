import React, { Component } from 'react';
import { ToastProvider } from 'react-toast-notifications';
import AdminLTE, { Sidebar } from 'adminlte-2-react';
import { connect } from 'react-redux';
import Dashboard from './components/Dashboard';
import LoginPage from './components/LoginPage';
import SignUpPage from './components/SignUpPage';
import DashboardHome from './components/DashboardHome';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { categoriesList, searchClient } from './ActionCreator';
import { _FILTERS, _LICENZE } from './Constants';
import { ClientFilter } from './HierarchyManager';
import { toaster } from './toastManager';

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
    category_vs_place: state.category_vs_place,
    token: state.token,
    id_company: state.id_company
  }
}

const { Item, Searchbar } = Sidebar;

const getSidebarByType = (client_list, nome_company, searched_client, categories_list) =>{
  /**
   * {"Client": 1,
   * "Server": 2}
   */
  let category = [{"nome": "Client", "n_client": 0}, {"nome": "Server", "n_client": 0}]
  category.map((cat) => {
    if(cat.nome=="Client"){cat.n_client = categories_list["Client"]}
    else if(cat.nome=="Server"){cat.n_client = categories_list["Server"]}
  })
  let filterMap = ClientFilter(client_list, _FILTERS.CLIENT_TYPE);
  let filtered_client_list = filterMap[0];
  let map = filterMap[1];
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
                ? <Item icon={"fa-desktop"} key={item.id_client} text={<>{item.nome_client} <FontAwesomeIcon icon={["far", "dot-circle"]} /></>} to={"/company"+nome_company+"user"+item.id_client} />
                : <Item icon={"fa-server"} key={item.id_client} text={<>{item.nome_client} <FontAwesomeIcon icon={["far", "dot-circle"]} /></>} to={"/company"+nome_company+"user"+item.id_client} /> 
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

const getSidebarByLicense = (client_list, nome_company, searched_client, licenses_list) =>{
  /**
   * {"1": 1,
   * "2": 2,
   * ...}
   */
  let place = [{"nome": "1", "n_client": 0}, {"nome": "2", "n_client": 0}, {"nome": "3", "n_client": 0}, {"nome": "4", "n_client": 0}, {"nome": "5", "n_client": 0}]
  place.map((cat) => {
    if(cat.nome=="1"){cat.n_client = licenses_list["1"]}
    if(cat.nome=="2"){cat.n_client = licenses_list["2"]}
    if(cat.nome=="3"){cat.n_client = licenses_list["3"]}
    if(cat.nome=="4"){cat.n_client = licenses_list["4"]}
    if(cat.nome=="5"){cat.n_client = licenses_list["5"]}
  })
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

  let filterMap = ClientFilter(client_list, _FILTERS.CATEGORY);
  let filtered_client_list = filterMap[0];
  let map = filterMap[1];
  let lastComponent = [];
  {filtered_client_list.map((filtered_clients) => {
    lastComponent = [lastComponent, <Item icon="fa-map-marker-alt" text={filtered_clients.place+" ("+filtered_clients.filteredList.length+")"}>
      {place.map((p) => {
        //se map[place] include _licenze.RETE.tipo ecc...
        return (<Item icon="fa-users" text={label[p.nome]}>
          {client_list.map((item) => {
            return (item.sede==filtered_clients.place && item.classe_licenza==p.nome)
            ? (searched_client=="")
              ?
                (item.tipo_client=="Client")
                ? <Item icon={"fa-desktop"} key={item.id_client} text={<>{item.nome_client} <FontAwesomeIcon icon={["far", "dot-circle"]} /></>} to={"/company"+nome_company+"user"+item.id_client} />
                : <Item icon={"fa-server"} key={item.id_client} text={<>{item.nome_client} <FontAwesomeIcon icon={["far", "dot-circle"]} /></>} to={"/company"+nome_company+"user"+item.id_client} /> 
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
    
      <div>
        {toaster}
        {props.logged==true 
        ? <AdminLTE title={[<FontAwesomeIcon icon={["fas", "home"]} />, " Home"]} homeTo={"/"+props.id_company} titleShort={<FontAwesomeIcon icon={["fas", "home"]} />} theme="blue" sidebar={<><Item icon="fa-user-alt" key="-1" text="Account" to={"/"+props.id_company} /><Searchbar onChange={handleChange} includeButton="true" placeholder="Cerca..." />{(props.category_vs_place) ? getSidebarByType(props.client_list, props.nome_company, props.searched_client, props.categories_list) : getSidebarByLicense(props.client_list, props.nome_company, props.searched_client, props.places_list)}</>}>
            <DashboardHome path={"/"+props.id_company} title={props.nome_company} />
            {props.client_list.map((item) => <Dashboard path={"/company"+props.nome_company+"user"+item.id_client} id_client={item.id_client} id_company={props.id_company} token={props.token} client={item} title={item.nome_client} />)}  
          </AdminLTE>
        : <AdminLTE title={["IT Sentinel"]} titleShort={["ITS"]} theme="blue" sidebar={getSidebarUnlogged()}>
            <div path="/accedi" title="Accedi"><LoginPage /></div>
            <div path="/registrati" title="Registrati"><SignUpPage /></div>
          </AdminLTE>
        }
      </div>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(App);
