package pro.sky.collectonsHW1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.collectonsHW1.exceptions.DepartmentNotFoundException;
import pro.sky.collectonsHW1.service.DepartmentService;
import pro.sky.collectonsHW1.service.EmployeeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentService out;
    Employee testE = new Employee("Ivan", "Ivanov", 100.1, 1);
    HashMap<String, Employee> emplMap = new HashMap<>() {{
        put("Ivan Ivanov", testE);
    }};

    List<Employee> emplList = new ArrayList<>(List.of(testE));

    HashMap<Integer, List<Employee>> emplMap1 = new HashMap<>() {{
        put(1, emplList);
    }};

    @Test
    public void getDepMonthlySalarySumTest() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(emplMap);
        assertEquals(out.getDepMonthlySalarySum(1), 100.1);
    }

    @Test
    public void getDepMinSalaryTest() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(emplMap);
        assertEquals(out.getDepMinSalary(1), 100.1);
    }

    @Test
    public void getDepMaxSalaryTest() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(emplMap);
        assertEquals(out.getDepMaxSalary(1), 100.1);
    }

    @Test
    public void getDepEmployeesListTest() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(emplMap);
        assertEquals(emplList, out.getDepEmployeesList(1));
    }

    @Test
    public void getSortedByDeptEmployeesListTest() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(emplMap);
        assertEquals(emplMap1, out.getSortedByDeptEmployeesList());

    }

    @Test
    public void DepartmentNotFoundTest() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(emplMap);
        assertThrows(DepartmentNotFoundException.class, () -> out.getDepEmployeesList(2));
        assertThrows(DepartmentNotFoundException.class, () -> out.getDepMaxSalary(2));
        assertThrows(DepartmentNotFoundException.class, () -> out.getDepMinSalary(2));
        assertThrows(DepartmentNotFoundException.class, () -> out.getDepMonthlySalarySum(2));
    }

}
