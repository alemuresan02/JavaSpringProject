package org.utcn.springproject.controllers.security;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.utcn.springproject.data.UserRepository;
import org.utcn.springproject.models.User;

import java.util.Optional;

@Controller
@RequestMapping("home/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String displayLoginForm(Model model) {
        model.addAttribute("title", "LogIn");
        model.addAttribute(new User());
        return "security/login";
    }

    @PostMapping
    public String processLoginForm(@ModelAttribute @Valid User newUser,
                                   Errors errors,
                                   Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Login");
            return "security/login";
        }

        Optional<User> userOptional = userRepository.findByUsernameAndPassword(newUser.getUsername(), newUser.getPassword());
        if (userOptional.isPresent()) {

            int role = userOptional.get().getRole();

            if (role == 1) {
                return "redirect:/home/users";
            } else if (role == 2) {
                return "redirect:/home/books";
            } else {
                return "redirect:/home/customer";
            }
        }

        System.out.println("Autentificare eșuată!");
        model.addAttribute("title", "Login");
        model.addAttribute("userDoesNotExistError", "User does not exist!");
        return "security/login";
    }


}
