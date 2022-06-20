package com.afiev.springcourse.controller;

import com.afiev.springcourse.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {
    @Autowired
    RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/api/employees";

    @RequestMapping("")
    public String getEmployees(Model model){
        ResponseEntity<List<Employee>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null
                        ,new ParameterizedTypeReference<List<Employee>>() {});
        List<Employee> employees = responseEntity.getBody();
        model.addAttribute("allEmps", employees);
        return "all-employees";
    }

    public Employee getEmployee(int id){
        Employee employee = restTemplate.getForObject(URL + "/" + id, Employee.class);
        return employee;
    }

    @RequestMapping("new")
    public String getAddEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @RequestMapping("save-or-update")
    public String saveOrUpdateEmployee(@ModelAttribute("employee") Employee employee){
        if(employee.getId() == 0){
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, employee, String.class);
        } else {
            restTemplate.put(URL, employee);
        }
        return "redirect:/";
    }

    @RequestMapping("update")
    public String getUpdateEmployeeForm(@RequestParam("empId") int id,
                                        Model model){
        Employee employee = getEmployee(id);
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @RequestMapping("delete")
    public String deleteEmployee(@RequestParam("empId") int id){
        restTemplate.delete(URL + "/" + id);
        return "redirect:/";
    }
}
