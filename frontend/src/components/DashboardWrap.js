import React from 'react';
import { connect } from 'react-redux';
import AdminLTE, { Sidebar } from 'adminlte-2-react';
import { ToastProvider, useToasts } from 'react-toast-notifications'
import Dashboard from "./Dashboard";

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
    nome_company: state.nome_company
  }
}

const { Item } = Sidebar;

/**
 * get the lateral sidebar with the list of client and their link to the dashboard
 * @param {*} client_list 
 * @param {*} nome_company 
 */
const getSidebar = (client_list, nome_company) =>{
  let item_list = [];
  client_list.map((item) => (item_list = [item_list, <Item key={item.id_client} text={item.nome_client} to={"/company"+nome_company+"user"+item.id_client} />]));
  return item_list;
}

/**
 * for each client, define a dashboard passing some info, like the name of the client and the nome_company
 * @param {*} props 
 */
const DashboardWrap = (props) => {
  const { addToast } = useToasts()
  return (
    <ToastProvider>
      <AdminLTE title={[props.nome_company]} titleShort={props.nome_company} theme="blue" sidebar={getSidebar(props.client_list, props.nome_company)}>
        {props.client_list.map((item) => <Dashboard path={"/company"+props.nome_company+"user"+item.id_client} title={item.nome_client}/>)}
      </AdminLTE>
    </ToastProvider>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(DashboardWrap);
