package com.pixogram.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pixogram.user.entity.User;
import com.pixogram.user.service.UserService;

//@Controller
@RequestMapping("/users")
@CrossOrigin("http://localhost:9002")
@RestController
public class LoginController {

  @Autowired UserService service;

  @GetMapping("/login")
  public ModelAndView index() {
    ModelAndView model = new ModelAndView();
    User user = new User();
    model.addObject("user", user);
    model.setViewName("login");
    return model;
  }

  @GetMapping("/verifyUser")
  public ModelAndView index2(User user, BindingResult bindingResult) {
    ModelAndView model = new ModelAndView();
    boolean userExists = service.verifyPerson(user.getUserName(), user.getPassword());
    if (!userExists) {
      bindingResult.rejectValue("userName", "error.userName", "Invalid User or Password");
    }
    if (bindingResult.hasErrors()) {
      User user1 = new User();
      model.addObject("user", user1);
      model.setViewName("login");
    } else {
      model.setViewName("dashboard");
    }
    return model;
  }

  @GetMapping("/home")
  public String viewHomePage(Model model) {
    return "home";
  }
}
