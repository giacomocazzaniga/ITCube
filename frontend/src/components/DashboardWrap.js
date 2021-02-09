import React, { Component } from 'react';
import { connect } from 'react-redux';
import AdminLTE, { Sidebar } from 'adminlte-2-react';
import Dashboard from "./Dashboard";

const mapDispatchToProps = dispatch => ({});
const mapStateToProps = state => {
  return {
    client_list: state.client_list,
    company_id: state.company_id
  }
}

const { Item } = Sidebar;

const getSidebar = (client_list, company_id) =>{
  let item_list = [];
  client_list.map((item) => (item_list = [item_list, <Item key={item.id} text={item.name} to={"/company"+company_id+"user"+item.id} />]));
  return item_list;
}

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
