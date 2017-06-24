package org.kshrd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.kshrd.model.Role;
import org.kshrd.model.SignUpWith;
import org.kshrd.model.Student;
import org.kshrd.model.User;
import org.kshrd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller // = @Contoller + @ResponseBody
// @RequestMapping("/user")
public class MainController {
	
	/**
	 * student form Input"/student-cu"
	 * @param model
	 * @return
	 */
	@RequestMapping("/student-cu")
	public String studentCU(ModelMap model) {
		model.addAttribute("STUDENT", new Student());
		return "student-cu";
	}
	
	/**
	 * ResponseBody in Student Object
	 * @param student
	 * @return
	 */
	@RequestMapping(value = "/student-c", method = RequestMethod.POST)
	@ResponseBody
	public Student studentC(@ModelAttribute Student student) {
		return student;
	}
	/**
	 * mapping for form user register
	 * ..I have add userHash to form Input already,
	 * Then we load the form and see userHash already in the input box. 
	 * > String userHash = UUID.randomUUID().toString();
	 * > user.setUserHash(userHash);
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/user-cu")
	public String user_cu(ModelMap model) {
		User user = new User();
		SignUpWith signUpWith = new SignUpWith(1, null);
		String userHash = UUID.randomUUID().toString();
		user.setUserHash(userHash);
		user.setSignUpWith(signUpWith);
		model.addAttribute("USER", user);
		return "/admin/user-cu";
	}
	
	/**
	 * Response in User Object
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user-c", method = RequestMethod.POST)
	@ResponseBody
	public User userC(@ModelAttribute User user) {
		return user;
	}
	
	/**
	 * mapping for form role-cu
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/role-cu")
	public String roleCU(ModelMap model) {
		model.addAttribute("ROLE", new Role());
		return "/admin/role-cu";
	}
	
	/**
	 * Response in Role Object
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/role-c", method = RequestMethod.POST)
	@ResponseBody
	public Role roleC(@ModelAttribute Role role) {
		return role;
	}
	
	private UserService userService;

	/**
	 * Constructor Injection
	 * @param userService
	 */
	@Autowired
	public MainController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * mapping for form name "user-list"
	 * use userService.findAll() and return in List<user> put in Key "LIST"
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/user-list")
//	@ResponseBody
	public ModelAndView findAll(ModelAndView model) {
		List<User> user = new ArrayList<>();
		user = userService.findAll();
		for (User users : user) {
			System.out.println(users.getPassword());
		}
		model.setViewName("/admin/user-list");
		model.addObject("LIST", user);
		return model;
	}
	
	/**
	 * mapping for form detail and get userhash from form user-list 
	 * for searching database and get info to form detail.
	 * 
	 * @param userHash
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/detail/{user_hash}")
	public String search(@PathVariable("user_hash") String userHash, ModelMap model) {
		model.addAttribute("USER", userService.search(userHash));
		return "/admin/detail";
	}

	/**
	 * mapping for form update-user.
	 * use userService.search() for get info for update by userHash.
	 * 
	 * @return
	 */
	@RequestMapping("/admin/update/{user_hash}")
	public String update_user(@PathVariable("user_hash") String userHash, ModelMap model) {
		model.addAttribute("user", userService.search(userHash));
		return "/admin/update-user";
	}

	/**
	 * executed updateByUserHash().
	 * redirect to form "user-list".
	 * 
	 * @return
	 */

	@PostMapping("/update")
	public String update(@ModelAttribute User user) {
		userService.updateByUserHash(user);
		return "redirect:/admin/user-list";
	}
	
	/**
	 * mapping for "/save" and execute userService.save().
	 * redirect to form "user-list"
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	// @ResponseBody
	public String save(@ModelAttribute User user) {
		userService.save(user);
		return "redirect:/admin/user-list";
	}


	/**
	 * mapping url "../delete/...."
	 * executed function deleteByUserHash()
	 * redirect to form "user-list"
	 * 
	 * @param userHash
	 * @return
	 */
	@RequestMapping("/delete/{user_hash}")
	public String delete(@PathVariable("user_hash") String userHash, ModelAndView model) {
		userService.deleteByUserHash(userHash);
		return "redirect:/admin/user-list";
	}
	
	/**
	 * 
	 * 
	 */
	@RequestMapping({"/admin", "/admin/", "/admin/dashboard","/admin/dashboard/"})
	public String count(ModelMap model){
		model.addAttribute("COUNTTOTAL", userService.countTotal());
		model.addAttribute("COUNTMALE", userService.countMale());
		model.addAttribute("COUNTFEMALE", userService.countFemale());
		return "/admin/dashboard";
		
	}
	
	
}
