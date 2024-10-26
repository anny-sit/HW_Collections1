package pro.sky.collectonsHW1.service;

import org.springframework.stereotype.Service;
import pro.sky.collectonsHW1.Employee;
import pro.sky.collectonsHW1.exceptions.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public double getDepMonthlySalarySum(int department) {
        return employeeService.getEmployees().values().stream()
                .filter(employee -> (employee.getDepartment() == department))
                .map(Employee::getSalary)
                .reduce(Double::sum)
                .get();
    }

    public Employee getDepMinSalary(int department) {
        Employee emp;
        double sal = employeeService.getEmployees().values().stream()
                .filter(employee -> (employee.getDepartment() == department))
                .map(Employee::getSalary)
                .sorted()
                .findFirst().orElse(0.0);
        emp = employeeService.getEmployees().values().stream()
                .filter(employee -> (employee.getDepartment() == department))
                .filter(employee -> employee.getSalary() == sal)
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        return emp;
    }

    public Employee getDepMaxSalary(int department) {
        Employee emp;
        double sal = employeeService.getEmployees().values().stream()
                .filter(employee -> (employee.getDepartment() == department))
                .map(Employee::getSalary)
                .sorted(Comparator.reverseOrder())
                .findFirst().orElse(0.0);
        emp = employeeService.getEmployees().values().stream()
                .filter(employee -> (employee.getDepartment() == department))
                .filter(employee -> employee.getSalary() == sal)
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        return emp;
    }

    public ArrayList<Employee> getDepEmployeesList(int department) {
        return (ArrayList<Employee>) employeeService.getEmployees().values().stream()
                .filter(employee -> (employee.getDepartment() == department))
                .collect(Collectors.toList());
    }

    public ArrayList<Employee> getSortedByDeptEmployeesList() {
        return (ArrayList<Employee>) employeeService.getEmployees().values().stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .collect(Collectors.toList());
    }

}
