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
import { idToNomeLicenza, idToNomeSede } from './Tools';

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
    nome_company: state.company_template.company_data.ragione_sociale,
    logged: state.logged,
    searched_client: state.searched_client,
    categories_list: state.categories_list,
    places_list: state.places_list,
    category_vs_place: state.company_template.category_vs_place,
    token: state.token,
    id_company: state.id_company,
    lista_nomi_sedi: state.lista_nomi_sedi,
    lista_id_sedi: state.lista_id_sedi
  }
}

const { Item, Searchbar } = Sidebar;

const getSidebarByLicense2 = (client_list, nome_company, searched_client, licenses_list) =>{
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

  const getSidebarByType = (client_list, nome_company, searched_client, categories_list) =>{
    /**
     * {"Client": 1,
     * "Server": 2}
     */
    let categories = ["Client","Server"];
    let categoriesIcons = ["fa-desktop","fa-server"]
    let lastComponent = [];
    if(props.lista_id_sedi != undefined){ 
      props.lista_id_sedi.map((sede, j) => {
      (client_list.filter(function(o) { return (o.sede == sede && o.nome_client.includes(searched_client.toUpperCase()))}).length > 0 || searched_client==="")
      ? lastComponent = [lastComponent,<Item icon="fa-map-marker-alt" text={idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi)+" ("+client_list.filter(function(o) { return o.sede == sede }).length+")"}>
        {categories.map((category,i) => {
          return (client_list.filter(function(o) { return (o.sede == sede && o.tipo_client==category)}).length > 0 && client_list.filter(function(o) { return (o.sede == sede && o.nome_client.includes(searched_client.toUpperCase()))}).length > 0)
          ? <Item icon="fa-users" text={category+ " ("+client_list.filter(function(o) { return (o.sede == sede && o.tipo_client==category)}).length+")"}>
          {props.client_list.map((client) => {
            //if((client.sede == sede && client.tipo_client===category) || (client.sede == props.lista_nomi_sedi[j]  && client.tipo_client===category)){
            //    console.log(client.sede)
            //  console.log(props.lista_nomi_sedi[j])
            //  console.log(sede)
            //}
            
            return ((client.sede == sede && client.tipo_client===category) || (client.sede == props.lista_nomi_sedi[j]  && client.tipo_client===category))
            ? (searched_client=="")
              ? <Item icon={categoriesIcons[i]} key={client.id_client} text={<>{client.nome_client} <FontAwesomeIcon icon={["far", "dot-circle"]} /></>} to={"/company"+nome_company+"user"+client.id_client} />
              : (client.nome_client.toUpperCase().includes(searched_client.toUpperCase())) ? <Item icon={categoriesIcons[i]} key={client.id_client} text={<>{client.nome_client} <FontAwesomeIcon icon={["far", "dot-circle"]} /></>} to={"/company"+nome_company+"user"+client.id_client} /> : <></>
            : <></>
            })
          }
          </Item>
        : <></>  
        })}
      </Item>]
      : lastComponent = lastComponent;
    })
  }
    return lastComponent;
  }


  const getSidebarByLicense = (client_list, nome_company, searched_client, licenses_list) =>{
    /**
     * {"1": 1,
     * "2": 2,
     * ...}
     */
    let categories = [1,2,3,4,5];
    let lastComponent = [];
    if(props.lista_id_sedi != undefined){ 
      props.lista_id_sedi.map((sede, j) => {
      (client_list.filter(function(o) { return (o.sede == sede && o.nome_client.includes(searched_client.toUpperCase()))}).length > 0 || searched_client==="")
      ? lastComponent = [lastComponent,<Item icon="fa-map-marker-alt" text={idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi)+" ("+client_list.filter(function(o) { return o.sede == sede }).length+")"}>
        {categories.map((category) => {
          return (client_list.filter(function(o) { return (o.sede == sede && o.classe_licenza.includes(category))}).length > 0 && client_list.filter(function(o) { return (o.sede == sede && o.nome_client.includes(searched_client.toUpperCase()))}).length > 0)
          ? <Item icon="fa-users" text={idToNomeLicenza(category)+ " ("+client_list.filter(function(o) { return (o.sede == sede && o.classe_licenza.includes(category))}).length+")"}>
          {props.client_list.map((client) => {
            return ((client.sede == sede && client.classe_licenza.includes(category)) || (client.sede == props.lista_nomi_sedi[j] && client.classe_licenza.includes(category)))
            ? (searched_client=="")
              ? <Item icon="fa-desktop" key={client.id_client} text={<>{client.nome_client} <FontAwesomeIcon icon={["far", "dot-circle"]} /></>} to={"/company"+nome_company+"user"+client.id_client} />
              : (client.nome_client.toUpperCase().includes(searched_client.toUpperCase())) ? <Item icon="fa-desktop" key={client.id_client} text={<>{client.nome_client} <FontAwesomeIcon icon={["far", "dot-circle"]} /></>} to={"/company"+nome_company+"user"+client.id_client} /> : <></>
            : <></>
            })
          }
          </Item>
        : <></>  
        })}
      </Item>]
      : lastComponent = lastComponent;
    })
  }
    return lastComponent;
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
