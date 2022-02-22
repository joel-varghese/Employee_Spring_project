import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route,Switch} from 'react-router-dom'
import ListEmployComponent from './components/ListEmployComponent'
import CreateEmployComponent from './components/CreateEmployComponent'
import UpdateEmployComponent from './components/UpdateEmployComponent'

function App() {
  return (
    <div>
      <Router>
      <div className="materialize">
        <Switch>
        <Route path = "/" exact component = {ListEmployComponent}></Route>
        <Route path = "/employees" component = {ListEmployComponent}></Route>
        <Route path = "/add-employee" component = {CreateEmployComponent}></Route>
        <Route path = "/edit-employee/:id" component = {UpdateEmployComponent}></Route>
        </Switch>
      </div>
      </Router>
    </div>
  );
}

export default App;
