package org.kshrd.controller;

import org.kshrd.model.Role;
import org.kshrd.model.Student;
import org.kshrd.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping({"/","/home"})
	public String homePage(ModelMap model){
		model.addAttribute("MESSAGE","HELLO");
		return "home";
	}
	@RequestMapping("/student-cu")
	public String studentCU(ModelMap model){
		model.addAttribute("STUDENT", new Student());
		return "student-cu";
	}
	
	@RequestMapping(value="/student-c", method=RequestMethod.POST)
	@ResponseBody
	public Student studentC(@ModelAttribute Student student){
		return student;
	}
	
	@RequestMapping("/admin/user-cu")
	public String userCU(ModelMap model){
		model.addAttribute("USER", new User());
		return "/admin/user-cu";
	}
	@RequestMapping(value="/user-c", method=RequestMethod.POST)
	@ResponseBody
	public User userC(@ModelAttribute User user){
		return user;
	}
	
	@RequestMapping("/admin/role-cu")
	public String roleCU(ModelMap model){
		model.addAttribute("ROLE", new Role());
		return "/admin/role-cu";
	}
	@RequestMapping(value="/role-c", method=RequestMethod.POST)
	@ResponseBody
	public Role roleC(@ModelAttribute Role role){
		return role;
	}
	
	
	
}
