import axios from 'axios';

const EMPLOYEE_API_BASE_URL = "http://localhost:9911";

class EmployService {

    getEmployees(){
        return axios.get(EMPLOYEE_API_BASE_URL);
    }
    createEmployee(employee){
        return axios.post(EMPLOYEE_API_BASE_URL + '/addEmploy', employee);
    }
    updateEmployee(employee, employeeId){
        return axios.put(EMPLOYEE_API_BASE_URL + '/update/' + employeeId, employee);
    }
    getEmployeeById(employeeId){
        return axios.get(EMPLOYEE_API_BASE_URL + '/employ/' + employeeId);
    }
    deleteEmployee(employeeId){
        return axios.delete(EMPLOYEE_API_BASE_URL + '/delete/' + employeeId);
    }

}

export default new EmployService()