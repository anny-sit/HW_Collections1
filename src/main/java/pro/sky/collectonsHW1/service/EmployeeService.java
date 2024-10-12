package pro.sky.collectonsHW1.service;

import org.springframework.stereotype.Service;
import pro.sky.collectonsHW1.Employee;
import pro.sky.collectonsHW1.exceptions.EmployeeAlreadyAddedException;
import pro.sky.collectonsHW1.exceptions.EmployeeNotFoundException;
import pro.sky.collectonsHW1.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private static final int AMOUNT = 10;
    private static List<Employee> employees = new ArrayList<>();

    public static Employee add(String firstName, String lastName) {
        if (employees.size() == AMOUNT) {
            throw new EmployeeStorageIsFullException();
        }
        try {
            find(firstName, lastName);
            throw new EmployeeAlreadyAddedException();
        } catch (EmployeeNotFoundException e) {
            employees.add(new Employee(firstName, lastName));
            return find(firstName, lastName);
        }
    }

    public static Employee find(String firstName, String lastName) {
        for (Employee e : employees) {
            if (e.getFirstName().equals(firstName) && e.getLastName().equals(lastName)) {
                return e;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public static Employee remove(String firstName, String lastName) {
        Employee tmp = find(firstName, lastName);
        employees.remove(tmp);
        return tmp;
    }
}