package pro.sky.collectonsHW1.service;

import org.springframework.stereotype.Service;
import pro.sky.collectonsHW1.Employee;
import pro.sky.collectonsHW1.exceptions.EmployeeAlreadyAddedException;
import pro.sky.collectonsHW1.exceptions.EmployeeNotFoundException;
import pro.sky.collectonsHW1.exceptions.EmployeeStorageIsFullException;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    private static final int AMOUNT = 10;
    private Map<String, Employee> employees = new HashMap<>();

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    public Employee add(String firstName, String lastName) {
        if (employees.size() == AMOUNT) {
            throw new EmployeeStorageIsFullException();
        }
        Employee emp = new Employee(firstName, lastName);
        if (employees.containsKey(emp.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(emp.getFullName(), emp);
        return emp;
    }

    public Employee find(String firstName, String lastName) {
        Employee emp = new Employee(firstName, lastName);

        if (employees.containsKey(emp.getFullName())) {
            return employees.get(emp.getFullName());
        }

        throw new EmployeeNotFoundException();

    }

    public Employee remove(String firstName, String lastName) {
        Employee emp = new Employee(firstName, lastName);

        if (employees.containsKey(emp.getFullName())) {
            return employees.remove(emp.getFullName());
        }

        throw new EmployeeNotFoundException();
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }
}