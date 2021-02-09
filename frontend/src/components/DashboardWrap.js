import React from 'react';
import { connect } from 'react-redux';
import AdminLTE, { Sidebar } from 'adminlte-2-react';
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
    company_id: state.company_id
  }
}

const { Item } = Sidebar;

/**
 * get the lateral sidebar with the list of client and their link to the dashboard
 * @param {*} client_list 
 * @param {*} company_id 
 */
const getSidebar = (client_list, company_id) =>{
  let item_list = [];
  client_list.map((item) => (item_list = [item_list, <Item key={item.id} text={item.name} to={"/company"+company_id+"user"+item.id} />]));
  return item_list;
}

/**
 * for each client, define a dashboard passing some info, like the name of the client and the company_id
 * @param {*} props 
 */
const DashboardWrap = (props) => {
  return (
    <AdminLTE title={[props.company_id]} titleShort={props.company_id} theme="blue" sidebar={getSidebar(props.client_list, props.company_id)}>
      {props.client_list.map((item) => <Dashboard path={"/company"+props.company_id+"user"+item.id} title={item.name} />)}
    </AdminLTE>
  );
}

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(DashboardWrap);
