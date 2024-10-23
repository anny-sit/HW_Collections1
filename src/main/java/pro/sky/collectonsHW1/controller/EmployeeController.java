package pro.sky.collectonsHW1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.collectonsHW1.Employee;
import pro.sky.collectonsHW1.service.EmployeeService;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String hello() {
        return "hey";
    }


    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String fn,
                        @RequestParam("lastName") String ln) {
        return employeeService.add(fn, ln);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String fn,
                           @RequestParam("lastName") String ln) {
        return employeeService.remove(fn, ln);
    }


    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String fn,
                         @RequestParam("lastName") String ln) {
        return employeeService.find(fn, ln);
    }

    @GetMapping("/print")
    public Map<String, Employee> print() {
        return employeeService.getEmployees();
    }
}
