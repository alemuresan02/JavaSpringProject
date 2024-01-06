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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

@Controller
@RequestMapping("home/signup")
public class SignupController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String displaySignupForm(Model model) {
        model.addAttribute("title", "SignUp");
        model.addAttribute(new User());
        return "security/signup";
    }

    @PostMapping
    public String processSignupForm(@ModelAttribute @Valid User newUser,
                                   Errors errors,
                                   Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "SignUp");
            return "security/signup";
        }

        newUser.setRole(3);
        newUser.setPassword(hashPassword(newUser.getPassword()));

        Optional<User> userOptional = userRepository.findByUsernameAndPassword(newUser.getUsername(), newUser.getPassword());
        if (userOptional.isPresent()) {
            System.out.println("Utilizatorul exista deja!");
            model.addAttribute("title", "SignUp");
            model.addAttribute("userExistsError", "User already exists!");
            return "security/signup";
        } else if (userRepository.existsByUsername(newUser.getUsername())) {
            model.addAttribute("title", "SignUp");
            model.addAttribute("userExistsError", "Username is used!");
            return "security/signup";
        }

        userRepository.save(newUser);

        System.out.println("Account created successfully");
        return "redirect:/home/customer";
    }

    public String hashPassword(String password) {
        try {
            // Sercured Hash Algorithm - 256
            // 1 byte = 8 biți
            // 1 byte = 1 char
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
