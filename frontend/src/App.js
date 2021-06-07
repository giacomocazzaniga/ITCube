import React, { useEffect } from 'react';
import AdminLTE, { Sidebar } from 'adminlte-2-react';
import { connect } from 'react-redux';
import Dashboard from './components/Dashboard';
import LoginPage from './components/LoginPage';
import SignUpPage from './components/SignUpPage';
import DashboardHome from './components/DashboardHome';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { searchClient, totalReset } from './ActionCreator';
import { _FILTERS, _LICENZE } from './Constants';
import { toaster } from './toastManager';
import { idToNomeLicenza, idToNomeSede } from './Tools';
import UnsubscribeAlertMessage from './components/UnsubscribeAlertMessage';
import RichiediPassword from './components/RichiediPassword';
import ChangePassword from './components/ChangePassword';

/**
 * connect the actions to the component
 * @param {*} dispatch 
 */
const mapDispatchToProps =  dispatch => {
  return{
    Search: (nome_client) => {
      dispatch(searchClient(nome_client))
    },
    TotalReset: () => {
      dispatch(totalReset())
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

  // useEffect(() => {
  //   document.getElementsByClassName("sidebar-menu")[0].getElementsByTagName("li")[0].getElementsByTagName("a")[0].click();
  // },[])

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
      (client_list.filter(function(o) { return ((o.sede == idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi)|| o.sede == sede) && o.nome_client.includes(searched_client.toUpperCase()))}).length > 0 || searched_client==="")
      ? lastComponent = [lastComponent,<Item icon="fa-map-marker-alt" text={idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi)+" ("+client_list.filter(function(o) {return (o.sede == idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi) || o.sede == sede) }).length+")"}>
        {categories.map((category,i) => {
          return (client_list.filter(function(o) { return ((o.sede == idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi) || o.sede == sede) && o.tipo_client==category)}).length > 0 && client_list.filter(function(o) { return ((o.sede == idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi)|| o.sede == sede) && o.nome_client.includes(searched_client.toUpperCase()))}).length > 0)
          ? <Item icon="fa-users" text={category+ " ("+client_list.filter(function(o) { return ((o.sede == idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi)|| o.sede == sede) && o.tipo_client==category)}).length+")"}>
          {props.client_list.map((client) => {
            //if((client.sede == sede && client.tipo_client===category) || (client.sede == props.lista_nomi_sedi[j]  && client.tipo_client===category)){
            //    console.log(client.sede)
            //  console.log(props.lista_nomi_sedi[j])
            //  console.log(sede)
            //}
            
            return (((client.sede == idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi) || client.sede == sede) && client.tipo_client===category) || (client.sede == props.lista_nomi_sedi[j]  && client.tipo_client===category))
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
      (client_list.filter(function(o) { return ((o.sede == idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi)|| o.sede == sede) && o.nome_client.includes(searched_client.toUpperCase()))}).length > 0 || searched_client==="")
      ? 
        lastComponent = [lastComponent,<Item icon="fa-map-marker-alt" text={idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi)+" ("+client_list.filter(function(o) { 
          return (o.sede == idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi) || o.sede == sede)
           }).length+")"}>
          {categories.map((category) => {
            return (client_list.filter(function(o) { 
              return ((o.sede == idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi)|| o.sede == sede) && o.classe_licenza.includes(category))}).length > 0 && client_list.filter(function(o) { return ((o.sede == idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi)|| o.sede == sede) && o.nome_client.includes(searched_client.toUpperCase()))}).length > 0)
            ? <Item icon="fa-users" text={idToNomeLicenza(category)+ " ("+client_list.filter(function(o) { return ((o.sede == idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi)|| o.sede == sede) && o.classe_licenza.includes(category))}).length+")"}>
            {props.client_list.map((client) => {
              return (((client.sede == idToNomeSede(sede, props.lista_nomi_sedi, props.lista_id_sedi)|| client.sede == sede) && client.classe_licenza.includes(category)) || (client.sede == props.lista_nomi_sedi[j] && client.classe_licenza.includes(category)))
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
        {(window.location.pathname=="/unsubscribeAlert")
        ?
          <UnsubscribeAlertMessage path="/unsubscribeAlert" />
        :
          <>
            {toaster}
            {props.logged==true 
            ? <AdminLTE title={[<FontAwesomeIcon icon={["fas", "home"]} />, " Home"]} homeTo={"/"+props.id_company} titleShort={<FontAwesomeIcon icon={["fas", "home"]} />} theme="blue" sidebar={<><Item icon="fa-user-alt" key="-1" text="Account" to={"/"+props.id_company} /><Searchbar onChange={handleChange} includeButton="true" placeholder="Cerca..." />{(props.category_vs_place) ? getSidebarByType(props.client_list, props.nome_company, props.searched_client, props.categories_list) : getSidebarByLicense(props.client_list, props.nome_company, props.searched_client, props.places_list)}<a id="logoutButton" onClick={() => {props.TotalReset()}}><Item className="clickable" icon="fas-sign-out-alt" text={"Logout"} to={"/accedi"}></Item></a> </>}>
                <DashboardHome path={"/"+props.id_company} title={props.nome_company} />
                {props.client_list.map((item) => <Dashboard path={"/company"+props.nome_company+"user"+item.id_client} id_client={item.id_client} id_company={props.id_company} token={props.token} client={item} title={item.nome_client} />)}  
              </AdminLTE>
            : <AdminLTE title={["IT Sentinel"]} titleShort={["ITS"]} theme="blue" sidebar={getSidebarUnlogged()}>
                <div path="/accedi" title="Accedi"><LoginPage /></div>
                <div path="/registrati" title="Registrati"><SignUpPage /></div>
                <div path="/richiediPassword" title="Richiedi nuova password"><RichiediPassword /></div>
                <div path="/changePassword" title="Cambio password"><ChangePassword /></div>
              </AdminLTE>
            }
          </>
        }
  </div>
      
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(App);
