package com.ssm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.dao.EmployeeDAO;
import com.ssm.pojo.Employee;

@Controller
public class EmployeeController
{
    @Autowired
    private EmployeeDAO employeeDAO;

    @RequestMapping(value = "/employee",method=RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute("employee") Employee employee)
    {
        try
        {
            if(employeeDAO.getEmployeeById(employee.getId()) != null);
            employeeDAO.updateEmployee(employee);
        }
        catch(EmptyResultDataAccessException e)
        {
            System.out.println("inside catch");
            employeeDAO.saveEmployee(employee);
        }
        return new ModelAndView("redirect:/employee");
    }    


    @RequestMapping(value = "/employee")
    public ModelAndView listEmployees(@ModelAttribute("employee") Employee employee)
    {
        ModelAndView model = new ModelAndView("employee");

        List<Employee> employeeList = employeeDAO.getAllEmployees();
        System.out.println(employeeList);
        model.addObject("employeeList", employeeList);
        
        return model;
    }
}
