import React, { Component } from 'react';
import AdminLTE, { Sidebar } from 'adminlte-2-react';
import Dashboard from "./Dashboard";

const { Item } = Sidebar;

class App extends Component {
  state = {
    company_id : "ITCube Consulting",
    client_list : [
      {
        id : "1",
        name : "Macchina di Massimiliano"
      },
      {
        id : "2",
        name : "Macchina di Francesco"
      }
    ]
  }

  getSidebar(client_list, company_id){
    let item_list = [];
    client_list.map((item) => (item_list = [item_list, <Item key={item.id} text={item.name} to={"/company"+company_id+"user"+item.id} />]));
    return item_list;
  }

  render() {
    return (
      <AdminLTE title={[this.state.company_id]} titleShort={this.state.company_id} theme="blue" sidebar={this.getSidebar(this.state.client_list, this.state.company_id)}>
        {this.state.client_list.map((item) => <Dashboard path={"/company"+this.state.company_id+"user"+item.id} title={item.name} />)}
      </AdminLTE>
    );
  }
}

export default App;
