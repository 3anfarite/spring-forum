package org.example.again.controller;

import org.example.again.Entities.User;
import org.example.again.services.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value ="/register")
public class RegistrationController {
	@Autowired
    private UserServiceimpl userService;

	
    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }


    @PostMapping
    public String registerUser(@Valid @ModelAttribute ("user")  User user, BindingResult bindingResult ,
    		Model model) {
        User userExists = userService.findUserByUserName(user.getUsername());
        if (userExists != null) {
        	 model.addAttribute("user", new User());
            bindingResult.rejectValue("username", "error.user",
                            "There is already a user registered with the user name provided");
            return "redirect:";
        }
            model.addAttribute("user", new User());
            userService.save(user);
            return "redirect:/register?success";
    }
 }
