import React, { Component } from 'react'
import EmployService from '../services/EmployService';

class UpdateEmployComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            Name: '',
            Gender:'',
            Dept: '',
            Desig: '',
            Basic: 0
        }
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeGenderHandler = this.changeGenderHandler.bind(this);
        this.changeDeptHandler = this.changeDeptHandler.bind(this);
        this.changeDesigHandler = this.changeDesigHandler.bind(this);
        this.changeBasicHandler = this.changeBasicHandler.bind(this);
        this.updateEmployee = this.updateEmployee.bind(this);
    }
    updateEmployee = (e) => {
        e.preventDefault();
        let employee = {name: this.state.Name, gender: this.state.Gender,dept: this.state.Dept,desig: this.state.Desig,basic: this.state.Basic};
        console.log('employee => ' + JSON.stringify(employee));
        EmployService.updateEmployee(employee,this.state.id).then(res => {
            this.props.history.push('/employees');
        });
    }

    componentDidMount(){
        EmployService.getEmployeeById(this.state.id).then( (res) =>{
            let employee = res.data;
            this.setState({Name: employee.name,
                Gender: employee.gender,
                Dept: employee.dept,
                Desig: employee.desig,
                Basic: employee.basic
            });
        });
    }
    changeNameHandler= (event) => {
        this.setState({Name: event.target.value});
    }
    changeGenderHandler= (event) => {
        this.setState({Gender: event.target.value});
    }
    changeDeptHandler= (event) => {
        this.setState({Dept: event.target.value});
    }
    changeDesigHandler= (event) => {
        this.setState({Desig: event.target.value});
    }
    changeBasicHandler= (event) => {
        this.setState({Basic: event.target.value});
    }
    cancel(){
        this.props.history.push('/employees');
    }
    render(){
        return(
            <div>
                
                <h3>Update Employ</h3>
                <form>
                    <div className='row'>
                    <div className='input-field col s6'>
                        <input  id="Name" type="text" name="Name" 
                        value={this.state.Name} onChange={this.changeNameHandler}/>
                        <label for="Name"> Name: </label>
                        </div>
                        <div className='input-field col s6'>
                        <input  id="Gender" name="Gender" type="text"
                        value={this.state.Gender} onChange={this.changeGenderHandler}/>
                        <label for="Gender"> Gender: </label>
                        </div>
                        <div className='input-field col s6'>
                        <input  id="Dept" name="Dept" className="validate" type="text"
                        value={this.state.Dept} onChange={this.changeDeptHandler}/>
                        <label for="Dept"> Department: </label>
                        </div>
                        <div className='input-field col s6'>
                        <input id="Desig" name="Desig" className="validate" type="text"
                        value={this.state.Desig} onChange={this.changeDesigHandler}/>
                        <label for="Desig"> Designation: </label>
                        </div>
                        <div className='input-field col s6'>
                        <input id="Basic" name="Basic" className="validate" type="number"
                        value={this.state.Basic} onChange={this.changeBasicHandler}/>
                        <label for="Basic"> Basic: </label>
                        </div>
                    </div>
                    <button className="waves-effect waves-light btn" onClick={this.updateEmployee}>Save</button>
                    <button className="waves-effect waves-light btn red litghten-2" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                    </form>
                </div>
            
        )
    }
}


export default UpdateEmployComponent