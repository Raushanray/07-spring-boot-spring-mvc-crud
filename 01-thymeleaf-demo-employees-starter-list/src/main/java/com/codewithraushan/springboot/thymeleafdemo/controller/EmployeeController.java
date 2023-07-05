package com.codewithraushan.springboot.thymeleafdemo.controller;

import com.codewithraushan.springboot.thymeleafdemo.entity.Employee;
import com.codewithraushan.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	/*
	REMOVE HARD CODE AND CONNECT WITH DATA BASE MYSQL
	// load employee data

	private List<Employee> theEmployees;

	@PostConstruct
	private void loadData() {

		// create employees
		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@luv2code.com");
		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@luv2code.com");
		Employee emp3 = new Employee("Avani", "Gupta", "avani@luv2code.com");

		// create the list
		theEmployees = new ArrayList<>();

		// add to the list
		theEmployees.add(emp1);
		theEmployees.add(emp2);
		theEmployees.add(emp3);
	}
*/

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService){
		employeeService = theEmployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		//get the employee from database
		List<Employee> theEmployees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){

		// create model attribute to bind form data
		Employee theEmployee = new Employee();

		theModel.addAttribute("employee", theEmployee);

		return "employees/employee-form";

	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel){

		//get the employee from the service
		Employee theEmployee = employeeService.findById(theId);

		//set employee in the model to prepopulate the form
		theModel.addAttribute("employee", theEmployee);

		//send over to our form
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee")Employee theEmployee){

		//save the employee
		employeeService.save(theEmployee);

		//use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";

	}
}









