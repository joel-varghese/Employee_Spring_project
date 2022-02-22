import React, { Component } from 'react'
import EmployService from '../services/EmployService'


class ListEmployComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            employees: [],
            Name: null,
            inEditMode : {
                status: false,
                rowKey: null
            }
        }
        this.addEmployee = this.addEmployee.bind(this);
        this.deleteEmployee = this.deleteEmployee.bind(this);
    }
    setname(newname){
        this.setState({Name: newname});
    }
    onEdit(id,name){
        this.setState({
            inEditMode : {
                status:true,
                rowKey: id
            },
            Name : name
        });
    }
    onCancel(){
        this.setState({
            inEditMode : {
                status:false,
                rowKey: null
            },
            Name : null
        });
    }
    addEmployee(){
        this.props.history.push('/add-employee');
    }
    deleteEmployee(id){
        EmployService.deleteEmployee(id);
    }
    editEmployee(id,edname){
        EmployService.getEmployeeById(id).then( (res) =>{
            let newemp = res.data;
            newemp.name = edname;
        console.log('employee => ' + JSON.stringify(newemp));
        EmployService.updateEmployee(newemp,id)
        });
    }
    componentDidMount(){
        EmployService.getEmployees().then((res) => {
            this.setState({ employees: res.data});
        });
    }
    render() {
        return (
            <div>
                <h2 className="text-center">Employ List</h2>
                <div className='row'>
                <button className="waves-effect waves-light btn" onClick={this.addEmployee}> Create New Employee</button>
                </div>
                <br></br>
                <div className='row'>
                <table className="responsive-table striped highlight centered">
                <thead>
            <tr>
                <th>Employ No</th>
                <th>Name</th>
                <th>Department</th>
                <th>Designation</th>
                <th>Basic</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            {
                this.state.employees.map(
                    employee=>
                    <tr key = {employee.empno}>
                        <td> { employee.empno} </td>
                        <td>
                        {
                                    this.state.inEditMode.status && this.state.inEditMode.rowKey === employee.empno ? (
                                        <input value={this.state.Name}
                                               onChange={(event) => this.setname(event.target.value)}
                                        />
                                    ) : (
                                        employee.name
                                    )
                                }
                        </td>   
                        <td> {employee.dept}</td>
                        <td> {employee.desig}</td>
                        <td> {employee.basic}</td>
                        <td>
                        {
                                    this.state.inEditMode.status && this.state.inEditMode.rowKey === employee.empno ? (
                                        <React.Fragment>
                                            <button
                                                className={"waves-effect waves-light btn"}
                                                onClick={() => this.editEmployee(employee.empno,this.state.Name)}
                                            >
                                                Update
                                            </button>

                                            <button
                                                className={"waves-effect waves-light btn red litghten-2"}
                                                style={{marginLeft: 8}}
                                                onClick={() => this.onCancel()}
                                            >
                                                Cancel
                                            </button>
                                        </React.Fragment>
                                    ) : (
                                        <button
                                            className={"waves-effect waves-light btn"}
                                            onClick={() => this.onEdit(employee.empno,employee.name)}
                                        >
                                            Edit
                                        </button>
                                    )
                                }
                        <button style={{marginLeft: "8px"}} onClick={ () => this.deleteEmployee(employee.empno)} className="waves-effect waves-light btn red litghten-2">Delete </button>
                        </td>
                    </tr>
                )
            }
        </tbody>
                </table>
                </div>
            </div>
        )
    }
}
export default ListEmployComponent    