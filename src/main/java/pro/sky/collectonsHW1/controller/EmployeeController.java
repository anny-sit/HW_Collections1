package pro.sky.collectonsHW1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.collectonsHW1.Employee;
import pro.sky.collectonsHW1.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/add")
    public Employee add(@RequestParam(value = "firstName", required = false) String fn,
                        @RequestParam(value = "lastName", required = false) String ln) {
        return employeeService.add(fn, ln);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam(value = "firstName", required = false) String fn,
                           @RequestParam(value = "lastName", required = false) String ln) {
        return employeeService.remove(fn, ln);
    }


    @GetMapping("/find")
    public Employee find(@RequestParam(value = "firstName", required = false) String fn,
                         @RequestParam(value = "lastName", required = false) String ln) {
        return employeeService.find(fn, ln);
    }
}
