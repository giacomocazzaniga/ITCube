import React, { Component } from 'react';
import AdminLTE, { Sidebar } from 'adminlte-2-react';
import Dashboard from "./Dashboard";

const { Item } = Sidebar;

class App extends Component {

  sidebar = [
    <Item key="1" text="Macchina di Massimiliano" to="/company1user1" />,
    <Item key="2" text="Macchina di Francesco" to="/company1user2" />
  ]

  render() {
    return (
      <AdminLTE title={["ITCube", "Consulting"]} titleShort={["ITC", "C"]} theme="blue" sidebar={this.sidebar}>
        {this.sidebar.map((item) => <Dashboard path={item.props.to} title={item.props.text} />)}
      </AdminLTE>
    );
  }
}

export default App;
